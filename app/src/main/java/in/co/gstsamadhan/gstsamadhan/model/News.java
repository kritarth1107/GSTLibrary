package in.co.gstsamadhan.gstsamadhan.model;

public class News {
    private String id;
    private String title;
    private String hindiTitle;
    private String slug;
    private String thumbnail;
    private String content;
    private String author;
    private String attachment;
    private String dated;
    private String tags;
    private String createdAt;
    private String updatedAt;
    private String status;

    public News(){}

    public News(String id, String title, String hindiTitle, String slug, String thumbnail, String content, String author, String attachment, String dated, String tags, String createdAt, String updatedAt, String status) {
        this.id = id;
        this.title = title;
        this.hindiTitle = hindiTitle;
        this.slug = slug;
        this.thumbnail = thumbnail;
        this.content = content;
        this.author = author;
        this.attachment = attachment;
        this.dated = dated;
        this.tags = tags;
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

    public String getHindiTitle() {
        return hindiTitle;
    }

    public void setHindiTitle(String hindiTitle) {
        this.hindiTitle = hindiTitle;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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
