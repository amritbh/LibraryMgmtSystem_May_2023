package dataaccess;


import business.Book;
import business.BookCopy;
import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataAccessFacade implements DataAccess {

	enum StorageType {
		BOOKS, MEMBERS, USERS, CHECKOUT_RECORD;
	}

	public static final String OUTPUT_DIR = System.getProperty("user.dir") + "/src/dataaccess/storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	public static final DataAccess da = new DataAccessFacade();


	// implement: other save operations
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);

	}

	@Override
	public void saveNewBook(Book newBook) {
		HashMap<String, Book> books = readBooksMap();
		books.put(newBook.getIsbn(), newBook);
		saveToStorage(StorageType.BOOKS, books);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Book> readBooksMap() {
		// Returns a Map with name/value pairs being
		// isbn -> Book
		return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		// Returns a Map with name/value pairs being
		// memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(StorageType.MEMBERS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		// Returns a Map with name/value pairs being
		// userId -> User
		return (HashMap<String, User>) readFromStorage(StorageType.USERS);
	}

	///// load methods - these place test data into the storage area
	///// - used just once at startup

	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}

	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}

	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}

	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}

	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return retVal;
	}

	final static class Pair<S, T> implements Serializable {

		S first;
		T second;

		Pair(S s, T t) {
			first = s;
			second = t;
		}

		@Override
		public boolean equals(Object ob) {
			if (ob == null)
				return false;
			if (this == ob)
				return true;
			if (ob.getClass() != getClass())
				return false;
			@SuppressWarnings("unchecked")
			Pair<S, T> p = (Pair<S, T>) ob;
			return p.first.equals(first) && p.second.equals(second);
		}

		@Override
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}

		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}

		private static final long serialVersionUID = 5399827794066637059L;
	}
	
	public static boolean isMember( String memberId ) {
        return Objects.isNull(da.getLibraryMemberById( memberId ) );
    }

	@Override
	public boolean updateMember(LibraryMember newMember) {
		HashMap<String, LibraryMember> membersList = readMemberMap();
		LibraryMember oldMember = readMemberMap().get(newMember.getMemberId());
		if (oldMember != null) {
			membersList.replace(newMember.getMemberId(), oldMember, newMember);
			saveToStorage(StorageType.MEMBERS, membersList);
			return true;
		}
		return false;
	}

	@Override
	public LibraryMember getLibraryMemberById(String id) {
		LibraryMember member = readMemberMap().get(id);
		try {
			if (member != null)
				return member;
			else
				throw new Exception("User not found");
		} catch (Exception e) {
			System.out.println("User not found");
		}
		return null;
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		Book book = readBooksMap().get(isbn);
		if (book != null)
			return book;
		return null;
	}

	public String createCheckoutRecord(String isbn, String memberId) {
		BookCopy bookCopy = getBookCopy(isbn);
		bookCopy.changeAvailability();
		CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(bookCopy);
		LibraryMember member = getLibraryMember(memberId);
		CheckoutRecord checkoutRecord = new CheckoutRecord(memberId);
		checkoutRecord.getCheckoutRecordEntries().add(checkoutRecordEntry);
		addNewCheckoutRecord(checkoutRecord);
		updateBookCopyAvailability(bookCopy.getCopyNum());
		member.getRecords().addAll(Collections.singletonList(checkoutRecord));
		updateMember(member);
		return findProperties(Arrays.asList("checkoutId", "memberId", "bookCopyId", "checkoutDate", "dueDate"),
				checkoutRecord.toString());
	}
	
	public static String findProperties(List<String> properties, String source) {
		AtomicReference<String> response = new AtomicReference<>("");
		properties.forEach(property -> {
            Matcher m = Pattern.compile(property+"=.*?(?=,|})").matcher(source);
            while (m.find())
                response.set(response.get().concat(m.group(0).replace("'","").concat("\n")));
        });
        return response.get();
	}

	@Override
	public void addBookCopy(String isbn) {
		HashMap<String, Book> booksList = readBooksMap();
		Book book = booksList.get(isbn);
		try {
			booksList.get(isbn).addCopy();
			saveToStorage(StorageType.BOOKS, booksList);
		} catch (Exception e) {
			System.out.println("addBookCopy exception ");
		}
	}

	@SuppressWarnings("unchecked")
	public LibraryMember getLibraryMember(String LibraryMemberId) {
		HashMap<String, LibraryMember> members = (HashMap<String, LibraryMember>) readFromStorage(StorageType.MEMBERS);
		if (members.containsKey(LibraryMemberId)) {
			return members.get(LibraryMemberId);
		} else {
			System.out.println(LibraryMemberId + "Not found");
			return null;
		}
	}
	
	public static boolean isBookAvailable(String Isbn ) {
        return Objects.isNull(da.getBookCopy( Isbn ) );
    }

	public void addNewCheckoutRecord(CheckoutRecord record) {
		try {
			if (!Files.exists(
					Paths.get(DataAccessFacade.OUTPUT_DIR + File.separator + StorageType.CHECKOUT_RECORD.toString()))) {
				Files.createFile(Paths
						.get(DataAccessFacade.OUTPUT_DIR + File.separator + StorageType.CHECKOUT_RECORD.toString()));
			}
		} catch (IOException ioe) {
			System.err.println(ioe + "Exception output input");
		}
		HashMap<String, CheckoutRecord> checkoutRecord = Optional.ofNullable(readCheckoutRecordMap())
				.orElseGet(() -> new HashMap<String, CheckoutRecord>());
		checkoutRecord.put(record.getCheckoutId(), record);
		saveToStorage(StorageType.CHECKOUT_RECORD, checkoutRecord);
	}
	
	@SuppressWarnings("unchecked")
	public BookCopy getBookCopy(String isbn) {
		HashMap<String, Book> booksList = (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
		for (Book book : booksList.values()) {
			if (book.getIsbn() != null && book.getIsbn().equals(isbn.trim())) {
				for (BookCopy bookCopy : book.getCopies()) {
					if (bookCopy.isAvailable()) {
						return bookCopy;
					}
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap() {
		return (HashMap<String, CheckoutRecord>) readFromStorage(StorageType.CHECKOUT_RECORD);
	}

	@SuppressWarnings("unchecked")
	public boolean updateBookCopyAvailability(int bookCopyId) {
		HashMap<String, Book> booksList = (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
		for (Book book : booksList.values()) {
			for (BookCopy bookCopy : book.getCopies()) {
				if (bookCopy.getCopyNum() == bookCopyId) {
					bookCopy.setAvailable(false);
					booksList.replace(book.getIsbn(), book);
					saveToStorage(StorageType.BOOKS, booksList);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public HashMap<String, String> getBookCopyCheckoutRecord(BookCopy bookCopy) {
		HashMap<String, String> foundBookCopyCheckoutRecord = new HashMap<>();
		HashMap<String, CheckoutRecord> checkoutRecordMap = readCheckoutRecordMap();
		checkoutRecordMap.forEach((key, record) -> record.getCheckoutRecordEntries().forEach(entry -> {
			if (entry.getBookCopy().getCopyNum() == bookCopy.getCopyNum()) {
				LibraryMember member = getLibraryMember(record.getMemberId());
				foundBookCopyCheckoutRecord.put(member.getFirstName().concat(" ").concat(member.getLastName()),
						entry.getDueDate().toString());
			}
		}));
		return foundBookCopyCheckoutRecord;
	}
	
	public static boolean isIsbnExist(String isbn ) {
        return Objects.isNull(da.getBookByIsbn(isbn ) );
    }

	public String getBookCopiesWithCheckoutRecord(String isbn) {
		StringBuilder responseBuilder = new StringBuilder();
		if (isIsbnExist(isbn)) {
			return null;
		} else {
			Book foundBook = getBookByIsbn(isbn);
			responseBuilder.append("book Isbn = " + foundBook.getIsbn() + "\n")
					.append("book Title = " + foundBook.getTitle() + "\n")
					.append("Total Copies = " + foundBook.getCopies().length + "\n");

			List<BookCopy> bookCopies = Arrays.asList(foundBook.getCopies());
			bookCopies.forEach(bookCopy -> {
				HashMap<String, String> bookCopyCheckoutRecord = getBookCopyCheckoutRecord(bookCopy);
				if (bookCopyCheckoutRecord.isEmpty()) {
					responseBuilder
						.append("CopyId =" + bookCopy.getCopyNum() + "\n")
						.append("CheckoutBy = Available \n").append("Due Date   =  Available\n");
				} else {
					bookCopyCheckoutRecord.forEach((memberName, dueDate) -> {
						responseBuilder
							.append("CopyId =" + bookCopy.getCopyNum() + "\n")
							.append("CheckoutBy =" + memberName + "\n").append("Due Date =" + dueDate + "\n");
					});
				}
			});
			return responseBuilder.toString();
		}
	}

	 

	

}
