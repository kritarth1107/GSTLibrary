package in.co.gstsamadhan.gstsamadhan.model;

public class PressRelease {
    private String id;
    private String serial;
    private String category;
    private String dated;
    private String atachment;
    private String hindilink;
    private String description;
    private String document;
    private String createdAt;
    private String updatedAt;
    private String status;

    public PressRelease(){}

    public PressRelease(String id, String serial, String category, String dated, String atachment, String hindilink, String description, String document, String createdAt, String updatedAt, String status) {
        this.id = id;
        this.serial = serial;
        this.category = category;
        this.dated = dated;
        this.atachment = atachment;
        this.hindilink = hindilink;
        this.description = description;
        this.document = document;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getAtachment() {
        return atachment;
    }

    public void setAtachment(String atachment) {
        this.atachment = atachment;
    }

    public String getHindilink() {
        return hindilink;
    }

    public void setHindilink(String hindilink) {
        this.hindilink = hindilink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
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
