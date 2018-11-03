package project12;

import java.io.Serializable;

/**
 * 
 */

/**
 * @author Paul
 *
 */
public class studentInfo implements Serializable {
	private String ssn, firstName, mi, lastName, birthDate, street, phone, zipcode, deptId;

	/**
	 * bean
	 */
	public studentInfo() {
		// TODO Auto-generated constructor stub
	}

	public studentInfo(String ssn, String firstName, String mi, String lastName, String birthDate, String street,
			String phone, String zipcode, String deptId) {
		super();
		this.ssn = ssn;
		this.firstName = firstName;
		this.mi = mi;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.street = street;
		this.phone = phone;
		this.zipcode = zipcode;
		this.deptId = deptId;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMi() {
		return mi;
	}

	public void setMi(String mi) {
		this.mi = mi;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	
}
