package ws.integation.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import ws.integation.model.HeaderData;
import ws.integation.model.RequestLine;
import ws.integation.model.ResponseLine;
import ws.integation.model.ResponseRecord;
import ws.integation.model.ResponseStatus;
import ws.integation.util.CallWSUtils;
import ws.integation.util.ConnectionDB;
import ws.integation.util.DatabaseUtils;

/**
 * @author Thitipong Roongprasert
 * @date 1 ม.ค. 2563
 * @version 1.0
 */

public class LineInterfaceDao {
	/**
	 * Line-01
	 */
	public static ResponseLine checkCustomerName(RequestLine data) throws SQLException {
		/** Result object */
		ResponseLine result = new ResponseLine();
		/** Set Header data */
		List<HeaderData> headerData = new ArrayList<HeaderData>();
		HeaderData listHeader = new HeaderData();
		listHeader.setMessageId(data.getHeaderData().getMessageId());
		listHeader.setResponseDateTime(data.getHeaderData().getResponseDateTime());
		listHeader.setSentDateTime(data.getHeaderData().getSentDateTime());
		headerData.add(listHeader);
		result.setHeaderData(listHeader);
		/** Set response record */
		List<ResponseRecord> responseRecord = new ArrayList<ResponseRecord>();
		ResponseRecord listResponse = new ResponseRecord();
		/** Set response status */
		List<ResponseStatus> responseStatus = new ArrayList<ResponseStatus>();
		ResponseStatus listStatus = new ResponseStatus();
		/** Check parameter */
		if(data.getRequestRecord().getFirstName()=="" || data.getRequestRecord().getLastName()=="" || data.getRequestRecord().getDob()==""){
			/** Response status */
			listStatus.setStatusCode("E");
			listStatus.setErrorCode("500");
			listStatus.setErrorMessage("เกิดข้อผิดพลาด Parameter ที่ส่งมาไม่ถูกต้อง");
			responseStatus.add(listStatus);
			result.setResponseStatus(listStatus);
		} else {
			/** Convert birth date to thai */
			String birthDay = data.requestRecord.getDob().substring(0, 2);
			String birthMonth = data.requestRecord.getDob().substring(3, 5);
			String birthYearStr = data.requestRecord.getDob().substring(6, 10);
			Integer berthYearInt = Integer.parseInt(birthYearStr);
			Integer birthYearTemp = berthYearInt - 543;
			String birthDate = String.valueOf(birthYearTemp)+'-'+birthMonth+'-'+birthDay;
			/** Set connect DB */
			Integer checkGovtId = 0;
			ResultSet rs = null;
			PreparedStatement preparedStmt = null;
			String strquery = "SELECT id FROM person "
					+ "INNER JOIN party ON person.id = party.id " + "WHERE person.fname = '"
					+ data.getRequestRecord().getFirstName() + "' AND person.lname = '" + data.getRequestRecord().getLastName() + "' AND person.birth_date between '" + birthDate
					+ "' AND '" + birthDate + "'";
			StringBuilder query = new StringBuilder(strquery);
			Connection conn = ConnectionDB.getDbCustomer();
			try {
				preparedStmt = conn.prepareStatement(query.toString());
				rs = preparedStmt.executeQuery();
				while (rs.next()) {
					checkGovtId = rs.getInt("id");
					listResponse.setPartyId(rs.getInt("party_id"));
					listResponse.setGovtId(rs.getString("govt_id"));
					responseRecord.add(listResponse);
				}
				result.setResponseRecord(listResponse);
				if(checkGovtId > 0){
					/** Response status */
					listStatus.setStatusCode("S");
					listStatus.setErrorCode("200");
					listStatus.setErrorMessage("ดำเนินการเรียบร้อย");
					responseStatus.add(listStatus);
					result.setResponseStatus(listStatus);
				} else {
					/** Response status */
					listStatus.setStatusCode("S");
					listStatus.setErrorCode("200");
					listStatus.setErrorMessage("ไม่พบข้อมูลที่ต้องการค้นหา");
					responseStatus.add(listStatus);
					result.setResponseStatus(listStatus);
				}
			} catch (SQLException err) {
				/** Response status */
				listStatus.setStatusCode("E");
				listStatus.setErrorCode("500");
				listStatus.setErrorMessage("เกิดข้อผิดพลาด " + err.getMessage());
				responseStatus.add(listStatus);
				result.setResponseStatus(listStatus);
			} finally {
				DatabaseUtils.close(conn, preparedStmt, rs);
			}
		}
		return result;
	}
	/**
	 * Line-02
	 */
	public static ResponseLine insertCustomerDetail(RequestLine data) throws SQLException {
		/** Response object */
		ResponseLine result = new ResponseLine();
		/** Set header data */
		List<HeaderData> headerData = new ArrayList<HeaderData>();
		HeaderData listHeader = new HeaderData();
		listHeader.setMessageId(data.getHeaderData().getMessageId());
		listHeader.setResponseDateTime(data.getHeaderData().getResponseDateTime());
		listHeader.setSentDateTime(data.getHeaderData().getSentDateTime());
		headerData.add(listHeader);
		result.setHeaderData(listHeader);
		/** Set response status */
		List<ResponseStatus> responseStatus = new ArrayList<ResponseStatus>();
		ResponseStatus listStatus = new ResponseStatus();
		/** Check parameter */
		if(data.getRequestRecord().getGovtId()=="" || data.getRequestRecord().getMobilePhone()=="" || data.getRequestRecord().getEmail()=="" || data.getRequestRecord().getLineId()=="" ){
			/** Response status */
			listStatus.setStatusCode("E");
			listStatus.setErrorCode("500");
			listStatus.setErrorMessage("เกิดข้อผิดพลาด Parameter ที่ส่งมาไม่ถูกต้อง");
			responseStatus.add(listStatus);
			result.setResponseStatus(listStatus);
		} else {
			/** Set connect DB */
			int party_id = 0;
			Connection conn = ConnectionDB.getDbCustomer();
			conn.setAutoCommit(false);
			try {
				ResultSet rs = null;
				PreparedStatement pstmt = null;
				String strquery = "SELECT id FROM party WHERE party.id='" + data.getRequestRecord().getGovtId() + "'";
				StringBuilder query = new StringBuilder(strquery);
				pstmt = conn.prepareStatement(query.toString());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					party_id = rs.getInt("id");
				}
				pstmt.close();
				rs.close();
				if(party_id > 0){
					int checkPartyLog = 0;
					ResultSet rs5 = null;
					PreparedStatement pstmt5 = null;
					String selectPartyLog = "SELECT COUNT(line.id) as sequence FROM line_log line WHERE id='"
							+ party_id + "'";
					StringBuilder queryPartyLog = new StringBuilder(selectPartyLog);
					pstmt5 = conn.prepareStatement(queryPartyLog.toString());
					rs5 = pstmt5.executeQuery();
					while (rs5.next()) {
						checkPartyLog = rs5.getInt("sequence");
					}
					rs5.close();
					pstmt5.close();
					if(checkPartyLog == 0){
						int insLogCounts = 0;
						try {
							PreparedStatement pstmt2 = null;
							String insUserLog = "INSERT INTO line_log (id, govt, fname, lname, phone_number, email, line_id, create_time) "
									+ "VALUES (?,?,?,?,?,?,?,now())";
							pstmt2 = conn.prepareStatement(insUserLog);
							pstmt2.setInt(1, party_id);
							pstmt2.setString(2, data.getRequestRecord().getGovtId());
							pstmt2.setString(3, data.getRequestRecord().getFnameTh());
							pstmt2.setString(4, data.getRequestRecord().getLnameTh());
							pstmt2.setString(5, data.getRequestRecord().getMobilePhone());
							pstmt2.setString(6, data.getRequestRecord().getEmail());
							pstmt2.setString(7, data.getRequestRecord().getLineId());
							pstmt2.addBatch();
							insLogCounts = pstmt2.executeUpdate();
							if (insLogCounts > 0) {
								conn.commit();
								pstmt2.close();
							}
						} catch (Exception e) {
							/** Set Response status */
							listStatus.setStatusCode("E");
							listStatus.setErrorCode("500");
							listStatus.setErrorMessage("เกิดข้อผิดพลาด" + e.getMessage());
							responseStatus.add(listStatus);
							result.setResponseStatus(listStatus);
						}
					}
					try {
						int sequence = 0;
						ResultSet rs3 = null;
						PreparedStatement pstmt3 = null;
						String selectSequence = "SELECT COUNT(sequence) FROM socialmedia WHERE id='"
								+ party_id + "' AND socialmedia=1";
						StringBuilder queryBuilder = new StringBuilder(selectSequence);
						pstmt3 = conn.prepareStatement(queryBuilder.toString());
						rs3 = pstmt3.executeQuery();
						while (rs3.next()) {
							sequence = rs3.getInt("sequence");
						}
						rs3.close();
						pstmt3.close();
						/** Check sequence and update */
						if (sequence > 0) {
							sequence++;
						} else {
							sequence = 1;
						}
						/** Check sequence and insert into party.socialmedia */
						if (sequence > 0) {
							int insSocialCounts = 0;
							try {
								PreparedStatement pstmt4 = null;
								String insUserSocial = "INSERT INTO socialmedia (id, sequence, socialmedia, account, create_user_code, create_time, update_user_code, last_update, system_id, system_key) "
										+ "VALUES (?,?,1,?,'',now(),'',now(),65,'')";
								pstmt4 = conn.prepareStatement(insUserSocial);
								pstmt4.setInt(1, party_id);
								pstmt4.setInt(2, sequence);
								pstmt4.setString(3, data.getRequestRecord().getLineId());
								pstmt4.addBatch();
								insSocialCounts = pstmt4.executeUpdate();
								if (insSocialCounts > 0) {
									conn.commit();
									pstmt4.close();
									/** Set Response status */
									listStatus.setStatusCode("S");
									listStatus.setErrorCode("200");
									listStatus.setErrorMessage("ดำเนินการเรียบร้อย");
									responseStatus.add(listStatus);
									result.setResponseStatus(listStatus);
								}
							} catch (Exception e) {
								/** Set Response status */
								listStatus.setStatusCode("E");
								listStatus.setErrorCode("500");
								listStatus.setErrorMessage("เกิดข้อผิดพลาด" + e.getMessage());
								responseStatus.add(listStatus);
								result.setResponseStatus(listStatus);
							}
						} else {
							/** Set Response status */
							listStatus.setStatusCode("E");
							listStatus.setErrorCode("500");
							listStatus.setErrorMessage("เกิดข้อผิดพลาด 2 : ไม่สามารถบันทึกข้อมูลได้");
							responseStatus.add(listStatus);
							result.setResponseStatus(listStatus);
						}
					} catch (Exception e) {
						/** Set Response status */
						listStatus.setStatusCode("E");
						listStatus.setErrorCode("500");
						listStatus.setErrorMessage("เกิดข้อผิดพลาด" + e.getMessage());
						responseStatus.add(listStatus);
						result.setResponseStatus(listStatus);
					}
				} else {
					/** Set Response status */
					listStatus.setStatusCode("E");
					listStatus.setErrorCode("500");
					listStatus.setErrorMessage("เกิดข้อผิดพลาด 1 : ไม่สามารถบันทึกข้อมูลได้");
					responseStatus.add(listStatus);
					result.setResponseStatus(listStatus);
				}
			} catch (SQLException e) {
				/** Set Response status */
				listStatus.setStatusCode("E");
				listStatus.setErrorCode("500");
				listStatus.setErrorMessage("เกิดข้อผิดพลาด" + e.getMessage());
				responseStatus.add(listStatus);
				result.setResponseStatus(listStatus);
			} finally {
				DatabaseUtils.close(conn);
			}
		}
		return result;
	}
	/**
	 * Line-03
	 */
	public static ResponseLine updateCustomerGovId(RequestLine data) throws SQLException {
		/** Response object */
		ResponseLine result = new ResponseLine();
		/** Header data */
		List<HeaderData> headerData = new ArrayList<HeaderData>();
		HeaderData listHeader = new HeaderData();
		listHeader.setMessageId(data.getHeaderData().getMessageId());
		listHeader.setResponseDateTime(data.getHeaderData().getResponseDateTime());
		listHeader.setSentDateTime(data.getHeaderData().getSentDateTime());
		headerData.add(listHeader);
		result.setHeaderData(listHeader);
		/** Response status */
		List<ResponseStatus> responseStatus = new ArrayList<ResponseStatus>();
		ResponseStatus listStatus = new ResponseStatus();
		/** Check parameter */
		if(data.getRequestRecord().getGovtId()==""){
			/** Response status */
			listStatus.setStatusCode("E");
			listStatus.setErrorCode("500");
			listStatus.setErrorMessage("เกิดข้อผิดพลาด Parameter ที่ส่งมาไม่ถูกต้อง");
			responseStatus.add(listStatus);
			result.setResponseStatus(listStatus);
		} else {
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			Connection conn = ConnectionDB.getDbCustomer();
			conn.setAutoCommit(false);
			String insUser = "insert into line_update (id, fname, lname, policy_no, create_time) "
					+ "VALUES (?,?,?,?,now())";
			try {
				pstmt = conn.prepareStatement(insUser);
				pstmt.setString(1, data.getRequestRecord().getGovtId());
				pstmt.setString(2, data.getRequestRecord().getFnameTh());
				pstmt.setString(3, data.getRequestRecord().getLnameTh());
				pstmt.setString(4, data.getRequestRecord().getPolicyNo());
				pstmt.addBatch();
				int updateCounts = pstmt.executeUpdate();
				if (updateCounts > 0) {
					conn.commit();
					/** Set Response status */
					listStatus.setStatusCode("S");
					listStatus.setErrorCode("200");
					listStatus.setErrorMessage("ดำเนินการเรียบร้อย");
					responseStatus.add(listStatus);
					result.setResponseStatus(listStatus);
				}
			} catch (SQLException e) {
				if(e.getErrorCode()==0){
					/** Response status */
					listStatus.setStatusCode("E");
					listStatus.setErrorCode("500");
					listStatus.setErrorMessage("เกิดข้อผิดพลาด : ไม่สามารถบันทึกข้อมูลซ้ำได้");
					responseStatus.add(listStatus);
					result.setResponseStatus(listStatus);
				} else {
					/** Response status */
					listStatus.setStatusCode("E");
					listStatus.setErrorCode("500");
					listStatus.setErrorMessage("เกิดข้อผิดพลาด : Error code = " + e.getErrorCode());
					responseStatus.add(listStatus);
					result.setResponseStatus(listStatus);
				}
			} finally {
				DatabaseUtils.close(conn, pstmt, rs);
			}
		}
		return result;
	}
	/**
	 * Line-05
	 */
	public static ResponseLine checkCustomerGovId(RequestLine data) throws SQLException, IOException {
		/** Response object */
		ResponseLine result = new ResponseLine();
		/** Set header data */
		List<HeaderData> headerData = new ArrayList<HeaderData>();
		HeaderData listHeader = new HeaderData();
		listHeader.setMessageId(data.getHeaderData().getMessageId());
		listHeader.setResponseDateTime(data.getHeaderData().getResponseDateTime());
		listHeader.setSentDateTime(data.getHeaderData().getSentDateTime());
		headerData.add(listHeader);
		result.setHeaderData(listHeader);
		/** Set response record */
		List<ResponseRecord> responseRecord = new ArrayList<ResponseRecord>();
		ResponseRecord listResponse = new ResponseRecord();
		/** Set response status */
		List<ResponseStatus> responseStatus = new ArrayList<ResponseStatus>();
		ResponseStatus listStatus = new ResponseStatus();
		String wsInput = new Gson().toJson(data);
		/** Check parameter */
		if(data.getRequestRecord().getCitizenId()==""){
			/** Response status */
			listStatus.setStatusCode("E");
			listStatus.setErrorCode("500");
			listStatus.setErrorMessage("เกิดข้อผิดพลาด Parameter ที่ส่งมาไม่ถูกต้อง");
			responseStatus.add(listStatus);
			result.setResponseStatus(listStatus);
		} else {
			int rowCount = 0;
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			try {
				CallWSUtils callWs = new CallWSUtils();
				String cus20Data = callWs.callCus20(wsInput);
				JSONObject obj = new JSONObject(cus20Data.toString());
				JSONArray arrBody = new JSONArray(obj.get("responseRecord").toString());
				/** End of call service cus-20 */
				if(arrBody.length() > 0){
					/** Set response record */
					listResponse.setStatusCode("Y");
					responseRecord.add(listResponse);
					result.setResponseRecord(listResponse);
					/** Set response status */
					listStatus.setStatusCode("S");
					listStatus.setErrorCode("200");
					listStatus.setErrorMessage("ดำเนินการเรียบร้อย");
					responseStatus.add(listStatus);
					result.setResponseStatus(listStatus);
				} else {
					String strquery = "SELECT id FROM line_update WHERE id = '"
							+ data.getRequestRecord().getCitizenId() + "'";
					StringBuilder query = new StringBuilder(strquery);
					Connection conn = ConnectionDB.getDbCustomer();
					pstmt = conn.prepareStatement(query.toString());
					rs = pstmt.executeQuery();
					try {
						if (rs.next()) {
							rowCount++;
						}
						if (rowCount > 0) {
							/** Set response record */
							listResponse.setStatusCode("Y");
							responseRecord.add(listResponse);
							result.setResponseRecord(listResponse);
						} else {
							/** Set response record */
							listResponse.setStatusCode("N");
							responseRecord.add(listResponse);
							result.setResponseRecord(listResponse);
						}
						/** Set response status */
						listStatus.setStatusCode("S");
						listStatus.setErrorCode("200");
						listStatus.setErrorMessage("ดำเนินการเรียบร้อย");
						responseStatus.add(listStatus);
						result.setResponseStatus(listStatus);
					} catch (SQLException e) {
						/** Set response status */
						listStatus.setStatusCode("E");
						listStatus.setErrorCode("500");
						listStatus.setErrorMessage("เกิดข้อผิดพลาด " + e.getMessage());
						responseStatus.add(listStatus);
						result.setResponseStatus(listStatus);
					} finally {
						DatabaseUtils.close(conn, pstmt, rs);
					}
				}
			} catch (Exception e) {
				/** Set response status */
				listStatus.setStatusCode("E");
				listStatus.setErrorCode("500");
				listStatus.setErrorMessage("เกิดข้อผิดพลาด " + e.getMessage());
				responseStatus.add(listStatus);
				result.setResponseStatus(listStatus);
			}
		}
		return result;
	}
}
