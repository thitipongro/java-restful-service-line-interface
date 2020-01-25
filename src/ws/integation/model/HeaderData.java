package ws.integation.model;

public class HeaderData {
    String messageId;
    String sentDateTime;
    String responseDateTime;
    
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String data) {
        this.messageId = data;
    }

    public String getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(String data) {
        this.sentDateTime = data;
    }

    public String getResponseDateTime() {
        return responseDateTime;
    }

    public void setResponseDateTime(String data) {
        this.responseDateTime = data;
    }
}
