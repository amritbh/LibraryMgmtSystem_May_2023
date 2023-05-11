package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import dataaccess.Auth;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	private static DataAccessFacade da = new DataAccessFacade();

	public void login(String id, String password) throws LoginException {
		HashMap<String, User> map = da.readUserMap();
		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();

	}

	@Override
	public List<String> allMemberIds() {
		List<String> members = new ArrayList<>();
		members.addAll(da.readMemberMap().keySet());
		return members;
	}

	public List<String> allMemberIdsByName() {
		List<String> members = new ArrayList<>();
		for (LibraryMember member : da.readMemberMap().values()) {
			members.add(member.getFirstName() + " " + member.getLastName() + " #" + member.getMemberId());
		}
		return members;
	}

	public List<String> allBookIdsByTitle() {
		List<String> books = new ArrayList<>();
		for (Book book : da.readBooksMap().values()) {
			books.add(book.getTitle() + " (" + book.getNumCopies() + ") " + " #" + book.getIsbn());
		}
		return books;
	}

	@Override
	public List<String> allBookIds() {
		List<String> books = new ArrayList<>();
		books.addAll(da.readBooksMap().keySet());
		return books;
	}

	@Override
	public void addNewMemberController(LibraryMember member) {
		da.saveNewMember(member);
	}

	@Override
	public HashMap<String, LibraryMember> allMembers() {
		return da.readMemberMap();
	}

	@Override
	public LibraryMember getLibraryMemberByIdController(String id) {
		return da.getLibraryMemberById(id);
	}

	@Override
	public HashMap<String, Book> allBooks() {
		return da.readBooksMap();
	}

	@Override
	public boolean updateMemberController(LibraryMember member) {
		return da.updateMember(member);
	}

	@Override
	public Book getBookByIsbnController(String isbn) {
		return da.getBookByIsbn(isbn);
	}

	public List<CheckoutRecord> findMemberCheckoutRecordController(String memberId) {
		return da.getLibraryMemberById(memberId).getRecords();
	}

	public String overDueCheckoutController(String isbn) {
		return da.getBookCopiesWithCheckoutRecord(isbn);
	}

	@Override
	public void addBookController(Book newBook) {
		da.saveNewBook(newBook);
	}

	@Override
	public void addBookCopyController(String isbn) {
		da.addBookCopy(isbn);

	}

	@Override
	public String makeCheckoutController(String memberId, String isbn) {
		String message = null;
		if (da.isMember(memberId)) {
			message = memberId + " is  not yet a member \n";
		} else if (da.isIsbnExist(isbn)) {
			message = "There is no book match this ISBN:" + isbn + " ! \n";
		} else if (da.isBookAvailable(isbn)) {
			message = "No available copy at the moment for  Isbn " + isbn + "\n";
		} else {
			message = "Checkout made successfully ! \n";
			message += da.createCheckoutRecord(isbn, memberId);
		}
		return message;
	}

}
