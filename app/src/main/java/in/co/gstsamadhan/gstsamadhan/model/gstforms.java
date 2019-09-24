package in.co.gstsamadhan.gstsamadhan.model;

public class gstforms {
    private String id;
    private String serial;
    private String formname;
    private String description;
    private String relevantRule;
    private String relevantNotification;
    private String pdf_url;
    private String doc_url;
    private String createdAt;
    private String updatedAt;
    private String status;
     public gstforms(){}

    public gstforms(String id, String serial, String formname, String description, String relevantRule, String relevantNotification, String pdf_Url, String doc_Url, String createdAt, String updatedAt, String status) {
        this.id = id;
        this.serial = serial;
        this.formname = formname;
        this.description = description;
        this.relevantRule = relevantRule;
        this.relevantNotification = relevantNotification;
        this.pdf_url = pdf_Url;
        this.doc_url = doc_Url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelevantRule() {
        return relevantRule;
    }

    public void setRelevantRule(String relevantRule) {
        this.relevantRule = relevantRule;
    }

    public String getRelevantNotification() {
        return relevantNotification;
    }

    public void setRelevantNotification(String relevantNotification) {
        this.relevantNotification = relevantNotification;
    }

    public String getPdf_Url() {
        return pdf_url;
    }

    public void setPdf_Url(String pdf_Url) {
        this.pdf_url = pdf_Url;
    }

    public String getDoc_Url() {
        return doc_url;
    }

    public void setDoc_Url(String doc_Url) {
        this.doc_url = doc_Url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
