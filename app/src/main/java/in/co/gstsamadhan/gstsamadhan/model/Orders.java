package in.co.gstsamadhan.gstsamadhan.model;

public class Orders {
    private String id;
    private String serial;
    private String file_number;
    private String notificationno;
    private String category;
    private String description;
    private String subcategory;
    private String month;
    private String year;
    private String section;
    private String epdfurl;
    private String hpdfurl;
    private String ruleno;
    private String createdAt;
    private String updatedAt;
    private String status;
    private String hashtag;

    public Orders(){}

    public Orders(String id, String serial, String file_number, String notificationno, String category, String description, String subcategory, String month, String year, String section, String epdfurl, String hpdfurl, String ruleno, String createdAt, String updatedAt, String status, String hashtag) {
        this.id = id;
        this.serial = serial;
        this.file_number = file_number;
        this.notificationno = notificationno;
        this.category = category;
        this.description = description;
        this.subcategory = subcategory;
        this.month = month;
        this.year = year;
        this.section = section;
        this.epdfurl = epdfurl;
        this.hpdfurl = hpdfurl;
        this.ruleno = ruleno;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.hashtag = hashtag;
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

    public String getFile_number() {
        return file_number;
    }

    public void setFile_number(String file_number) {
        this.file_number = file_number;
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

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
