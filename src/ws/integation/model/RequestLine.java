package ws.integation.model;

public class RequestLine {

    public HeaderData headerData;
    public RequestRecord requestRecord;

    public HeaderData getHeaderData() {
        return headerData;
    }

    public void setHeaderData(HeaderData data) {
        this.headerData = data;
    }

    public RequestRecord getRequestRecord() {
        return requestRecord;
    }

    public void setRequestRecord(RequestRecord data) {
        this.requestRecord = data;
    }
}
