package business;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CheckoutRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	String checkoutId;
	String memberId;
	private List<CheckoutRecordEntry> checkoutRecordEntries;

	public CheckoutRecord(String memberId) {
		this.checkoutRecordEntries = new ArrayList<>();
		this.memberId = memberId;
		this.checkoutId = UUID.randomUUID().toString();
	}

	public void setCheckoutId(String checkoutId) {
		this.checkoutId = checkoutId;
	}

	public String getCheckoutId() {
		return checkoutId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setCheckoutEntries(List<CheckoutRecordEntry> checkoutRecordEntries) {
		this.checkoutRecordEntries = checkoutRecordEntries;
	}

	public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
		return checkoutRecordEntries;
	}

	@Override
	public String toString() {
		return "CheckoutRecord [checkoutId=" + checkoutId + ", memberId=" + memberId + ", checkoutRecordEntries="
				+ checkoutRecordEntries + "]";
	}

}
