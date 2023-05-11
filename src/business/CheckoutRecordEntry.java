package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy bookCopy;

	public CheckoutRecordEntry(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
		this.checkoutDate = LocalDate.now();
		this.dueDate = this.checkoutDate.plusDays(this.bookCopy.getBook().getMaxCheckoutLength());
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	@Override
	public String toString() {
		return "CheckoutRecordEntry [checkoutDate=" + checkoutDate + ", dueDate=" + dueDate + ", bookCopy=" + bookCopy
				+ "]";
	}

}
