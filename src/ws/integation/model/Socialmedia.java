package ws.integation.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Socialmedia {
	@Column(name = "party_id")
	private int partyId;

	@Column(name = "sequence")
	private int sequence;

	@Column(name = "socialmedia")
	private int socialmedia;

	@Column(name = "account_reference")
	private String accountReference;

	@Column(name = "create_user_code")
	private String createUserCode;

	@Column(name = "create_time")
	private String createTime;

	@Column(name = "update_user_code")
	private String updateUserCode;

	@Column(name = "last_update")
	private String lastUpdate;

	@Column(name = "system_id")
	private int systemid;

	@Column(name = "system_key")
	private String systemKey;

	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int data) {
		this.partyId = data;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int data) {
		this.sequence = data;
	}

	public int getSocialmedia() {
		return socialmedia;
	}

	public void setSocialmedia(int data) {
		this.socialmedia = data;
	}

	public String getAccountReference() {
		return accountReference;
	}

	public void setAccountReference(String data) {
		this.accountReference = data;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String data) {
		this.createUserCode = data;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String data) {
		this.createTime = data;
	}

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String data) {
		this.updateUserCode = data;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String data) {
		this.lastUpdate = data;
	}

	public int getSystemId() {
		return systemid;
	}

	public void setSystemId(int data) {
		this.systemid = data;
	}

	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String data) {
		this.systemKey = data;
	}
}