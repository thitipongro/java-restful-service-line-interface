package ws.integation.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ResponseRecord {
	@Column(name = "party_id")
	private int partyId;
	@Column(name = "govt_id")
	private String govtId;
	private String statusCode;

	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int data) {
		this.partyId = data;
	}

	public String getGovtId() {
		return govtId;
	}

	public void setGovtId(String data) {
		this.govtId = data;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String data) {
		this.statusCode = data;
	}
}