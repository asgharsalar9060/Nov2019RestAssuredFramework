package com.qa.api.gorest.pojo;

public class UserInfo {
	
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private BookingDates bookingdates;
	
	public UserInfo(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingdates) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.bookingdates = bookingdates;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the totalprice
	 */
	public int getTotalprice() {
		return totalprice;
	}

	/**
	 * @param totalprice the totalprice to set
	 */
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	/**
	 * @return the depositpaid
	 */
	public boolean isDepositpaid() {
		return depositpaid;
	}

	/**
	 * @param depositpaid the depositpaid to set
	 */
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	/**
	 * @return the bookingdates
	 */
	public BookingDates getBookingdates() {
		return bookingdates;
	}

	/**
	 * @param bookingdates the bookingdates to set
	 */
	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}
	
	
}
