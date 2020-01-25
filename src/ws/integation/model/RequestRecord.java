package ws.integation.model;

public class RequestRecord {
	private String citizenId;
	private String govtId;
	private String firstName;
	private String lastName;
	private String fnameTh;
	private String lnameTh;
	private String dob;
	private String mobilePhone;
	private String email;
	private String line_id;
	private String policy_no;
	private String certNo;

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String data) {
		this.citizenId = data;
	}

	public String getGovtId() {
		return govtId;
	}

	public void setGovtId(String data) {
		this.govtId = data;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String data) {
		this.firstName = data;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String data) {
		this.lastName = data;
	}

	public String getFnameTh() {
		return fnameTh;
	}

	public void setFnameTh(String data) {
		this.fnameTh = data;
	}

	public String getLnameTh() {
		return lnameTh;
	}

	public void setLnameTh(String data) {
		this.lnameTh = data;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String data) {
		this.dob = data;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String data) {
		this.mobilePhone = data;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String data) {
		this.email = data;
	}

	public String getLineId() {
		return line_id;
	}

	public void setLineId(String data) {
		this.line_id = data;
	}

	public String getPolicyNo() {
		return policy_no;
	}

	public void setPolicyNo(String data) {
		this.policy_no = data;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setcertNo(String data) {
		this.certNo = data;
	}
}
