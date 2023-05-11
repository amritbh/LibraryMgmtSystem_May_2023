package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private List<CheckoutRecord> records ;
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;	
		this.records = new ArrayList<>();
	}
	
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public List<CheckoutRecord> getRecords() {
		return records;
	}


	public void setRecords(List<CheckoutRecord> records) {
		this.records = records;
	}


	public String getMemberId() {
		return memberId;
	}

	

	@Override
	public String toString() {
		return "LibraryMember [memberId=" + memberId + ", records=" + records + "]";
	}



	private static final long serialVersionUID = -2226197306790714013L;
}
