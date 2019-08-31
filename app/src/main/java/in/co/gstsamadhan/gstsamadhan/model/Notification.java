package in.co.gstsamadhan.gstsamadhan.model;

public class Notification {
    public Notification(){}

    private String id;
    private String serial;
    private String notificationno;
    private String category;
    private String description;
    private String subcategory;
    private String notification_date;
    private String month;
    private String year;
    private String section;
    private String epdfurl;
    private String hpdfurl;
    private String ruleno;
    private String created_at;
    private String updated_at;
    private String status;

    public Notification(String id, String serial, String notificationno, String category, String description, String subcategory, String notification_date, String month, String year, String section, String epdfurl, String hpdfurl, String ruleno, String created_at, String updated_at, String status) {
        this.id = id;
        this.serial = serial;
        this.notificationno = notificationno;
        this.category = category;
        this.description = description;
        this.subcategory = subcategory;
        this.notification_date = notification_date;
        this.month = month;
        this.year = year;
        this.section = section;
        this.epdfurl = epdfurl;
        this.hpdfurl = hpdfurl;
        this.ruleno = ruleno;
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

    public String getNotificationno() {
        return notificationno;
    }

    public void setNotificationno(String notificationno) {
        this.notificationno = notificationno;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getnotification_date() {
        return notification_date;
    }

    public void setnotification_date(String notification_date) {
        this.notification_date = notification_date;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getEpdfurl() {
        return epdfurl;
    }

    public void setEpdfurl(String epdfurl) {
        this.epdfurl = epdfurl;
    }

    public String getHpdfurl() {
        return hpdfurl;
    }

    public void setHpdfurl(String hpdfurl) {
        this.hpdfurl = hpdfurl;
    }

    public String getRuleno() {
        return ruleno;
    }

    public void setRuleno(String ruleno) {
        this.ruleno = ruleno;
    }

    public String getcreated_at() {
        return created_at;
    }

    public void setcreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getupdated_at() {
        return updated_at;
    }

    public void setupdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
