package edu.usc.csci572.crawler;

public class JobsData {
	private String date;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the jobtype
	 */
	public String getJobtype() {
		return jobtype;
	}

	/**
	 * @param jobtype
	 *            the jobtype to set
	 */
	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	/**
	 * @return the applications
	 */
	public String getApplications() {
		return applications;
	}

	/**
	 * @param applications
	 *            the applications to set
	 */
	public void setApplications(String applications) {
		this.applications = applications;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param contactPerson
	 *            the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the faxNumber
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * @param faxNumber
	 *            the faxNumber to set
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * @return the location2
	 */
	public String getLocation2() {
		return location2;
	}

	/**
	 * @param location2
	 *            the location2 to set
	 */
	public void setLocation2(String location2) {
		this.location2 = location2;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the firstSeenDate
	 */
	public String getFirstSeenDate() {
		return firstSeenDate;
	}

	/**
	 * @param firstSeenDate
	 *            the firstSeenDate to set
	 */
	public void setFirstSeenDate(String firstSeenDate) {
		this.firstSeenDate = firstSeenDate;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the lastSeenDate
	 */
	public String getLastSeenDate() {
		return lastSeenDate;
	}

	/**
	 * @param lastSeenDate
	 *            the lastSeenDate to set
	 */
	public void setLastSeenDate(String lastSeenDate) {
		this.lastSeenDate = lastSeenDate;
	}

	/**
	 * @return the postedDate
	 */
	public String getPostedDate() {
		return postedDate;
	}

	/**
	 * @param postedDate
	 *            the postedDate to set
	 */
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	/**
	 * @return the dummy
	 */
	public String getDummy() {
		return dummy;
	}

	/**
	 * @param dummy
	 *            the dummy to set
	 */
	public void setDummy(String dummy) {
		this.dummy = dummy;
	}

	@Override
	public String toString() {
		String toReturn = "Posted Date: " + getPostedDate() + "\n" + "Dummy: "
				+ getDummy() + "\n" + "Location: " + getLocation() + "\n"
				+ "Location2: " + getLocation2() + "\n" + "Department: "
				+ getDepartment() + "\n" + "Salary: " + getSalary() + "\n"
				+ "Title: " + getTitle() + "\n" + "Start: " + getStart() + "\n"
				+ "Duration: " + getDuration() + "\n" + "Jobtype: "
				+ getJobtype() + "\n" + "Applications: " + getApplications()
				+ "\n" + "Company: " + getCompany() + "\n" + "Contact Person: "
				+ getContactPerson() + "\n" + "Phone Number: "
				+ getPhoneNumber() + "\n" + "Fax Number: " + getFaxNumber()
				+ "\n" + "Latitude: " + getLatitude() + "\n" + "Longitude: "
				+ getLongitude() + "\n" + "FirstSeenDate: "
				+ getFirstSeenDate() + "\n" + "URL: " + getUrl() + "\n"
				+ "LastSeenDate: " + getLastSeenDate() + "\n";
		return toReturn;

	};

	private String postedDate = "";
	private String dummy = "";
	private String location = "";
	private String department = "";
	private String title = "";
	private String salary = "";
	private String start = "";
	private String duration = "";
	private String jobtype = "";
	private String applications = "";
	private String company = "";
	private String contactPerson = "";
	private String phoneNumber = "";
	private String faxNumber = "";
	private String location2 = "";
	private String latitude = "";
	private String longitude = "";
	private String firstSeenDate = "";
	private String url = "";
	private String lastSeenDate = "";

}
