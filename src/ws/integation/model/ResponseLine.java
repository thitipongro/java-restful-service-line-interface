package ws.integation.model;

public class ResponseLine {

    private HeaderData headerData;
    private ResponseRecord responseRecord;
    private ResponseStatus responseStatus;

    public HeaderData getHeaderData() {
        return headerData;
    }

    public void setHeaderData(HeaderData data) {
        this.headerData = data;
    }

    public ResponseRecord getResponseRecord() {
        return responseRecord;
    }

    public void setResponseRecord(ResponseRecord data) {
        this.responseRecord = data;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus data) {
        this.responseStatus = data;
    }
}
