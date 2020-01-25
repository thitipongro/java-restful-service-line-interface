package ws.integation.model;

import java.util.List;

public class Message<T>
{
	private String msg;
	private String msgTh;
	private int status;
	private int totalRecord;
	private String dataExcept;
	private List<T> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgTh() {
		return msgTh;
	}

	public void setMsgTh(String msgTh) {
		this.msgTh = msgTh;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	public String getDataExcept() {
		return dataExcept;
	}

	public void setDataExcept(String dataExcept) {
		this.dataExcept = dataExcept;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}

