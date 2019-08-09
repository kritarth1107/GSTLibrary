package in.co.gstsamadhan.gstsamadhan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Acts {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("serial")
    @Expose
    private String serial;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("catid")
    @Expose
    private String catid;

    @SerializedName("subcatid")
    @Expose
    private String subcatid;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("chapter")
    @Expose
    private String chapter;
    @SerializedName("keynote")
    @Expose
    private String keynote;
    @SerializedName("releventrule")
    @Expose
    private String releventrule;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("postdate")
    @Expose
    private String postdate;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("act_created")
    @Expose
    private String actCreated;
    @SerializedName("act_updated")
    @Expose
    private String actUpdated;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public Acts() {
    }

    /**
     *
     * @param postdate
     * @param status
     * @param actCreated
     * @param userid
     * @param releventrule
     * @param actUpdated
     * @param section
     * @param id
     * @param content
     * @param title
     * @param chapter
     * @param description
     * @param year
     * @param serial
     * @param catid
     * @param subcatid
     * @param keynote
     */
    public Acts(String id, String serial, String title, String catid, String subcatid, String section, String chapter, String keynote, String releventrule, String content, String userid, String postdate, String year, String description, String actCreated, String actUpdated, String status) {
        super();
        this.id = id;
        this.serial = serial;
        this.title = title;
        this.catid = catid;
        this.subcatid = subcatid;
        this.section = section;
        this.chapter = chapter;
        this.keynote = keynote;
        this.releventrule = releventrule;
        this.content = content;
        this.userid = userid;
        this.postdate = postdate;
        this.year = year;
        this.description = description;
        this.actCreated = actCreated;
        this.actUpdated = actUpdated;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getSubcatid() {
        return subcatid;
    }

    public void setSubcatid(String subcatid) {
        this.subcatid = subcatid;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getKeynote() {
        return keynote;
    }

    public void setKeynote(String keynote) {
        this.keynote = keynote;
    }

    public String getReleventrule() {
        return releventrule;
    }

    public void setReleventrule(String releventrule) {
        this.releventrule = releventrule;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActCreated() {
        return actCreated;
    }

    public void setActCreated(String actCreated) {
        this.actCreated = actCreated;
    }

    public String getActUpdated() {
        return actUpdated;
    }

    public void setActUpdated(String actUpdated) {
        this.actUpdated = actUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}