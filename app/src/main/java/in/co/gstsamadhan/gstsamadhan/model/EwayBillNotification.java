package in.co.gstsamadhan.gstsamadhan.model;

public class EwayBillNotification {
    private String id;
    private String serial;
    private String notification_no;
    private String title;
    private String category;
    private String dated;
    private String month;
    private String year;
    private String pdf_url;
    private String created_at;
    private String updated_at;
    private String status;

    public EwayBillNotification(){}

    public EwayBillNotification(String id, String serial, String notification_no, String title, String category, String dated, String month, String year, String pdf_url, String created_at, String updated_at, String status) {
        this.id = id;
        this.serial = serial;
        this.notification_no = notification_no;
        this.title = title;
        this.category = category;
        this.dated = dated;
        this.month = month;
        this.year = year;
        this.pdf_url = pdf_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getNotification_no() {
        return notification_no;
    }

    public void setNotification_no(String notification_no) {
        this.notification_no = notification_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
