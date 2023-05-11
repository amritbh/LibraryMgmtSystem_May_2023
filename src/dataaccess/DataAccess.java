package dataaccess;

import java.util.HashMap;

import business.Book;
import business.BookCopy;
import business.LibraryMember;

public interface DataAccess {
	public HashMap<String, Book> readBooksMap();
	public HashMap<String, User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member);
	public void saveNewBook(Book newBook);
	public void addBookCopy(String isbn);
	public Book getBookByIsbn(String isbn);
	public BookCopy getBookCopy(String isbn);
	public String getBookCopiesWithCheckoutRecord(String isbn);
	public LibraryMember getLibraryMemberById(String id);
	public String createCheckoutRecord(String isbn, String memberId);
	HashMap<String, String> getBookCopyCheckoutRecord(BookCopy bookCopy);
	public boolean updateMember(LibraryMember newMember);
}
