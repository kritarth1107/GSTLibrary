package in.co.gstsamadhan.gstsamadhan.model;

public class Rules {
    private String id;
    private String title;
    private String catid;
    private String subcatid;
    private String section;
    private String chapter;
    private String keynote;
    private String releventact;
    private String content;
    private String userid;
    private String postdate;
    private String year;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String status;

    public Rules(){}

    public Rules(String id, String title, String catid, String subcatid, String section, String chapter, String keynote, String releventact, String content, String userid, String postdate, String year, String description, String createdAt, String updatedAt, String status) {
        this.id = id;
        this.title = title;
        this.catid = catid;
        this.subcatid = subcatid;
        this.section = section;
        this.chapter = chapter;
        this.keynote = keynote;
        this.releventact = releventact;
        this.content = content;
        this.userid = userid;
        this.postdate = postdate;
        this.year = year;
        this.description = description;
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

    public String getReleventact() {
        return releventact;
    }

    public void setReleventact(String releventact) {
        this.releventact = releventact;
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
