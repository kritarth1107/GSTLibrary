package in.co.gstsamadhan.gstsamadhan.model;

public class AdvanceRuling {
    private String id;
    private String category;
    private String subcategory;
    private String applicant_name;
    private String question;
    private String order_no;
    private String date;
    private String month;
    private String year;
    private String doc_url;
    private String section_sgst_act;
    private String created_at;
    private String updated_at;
    private String status;
    private String hashtag;

    public AdvanceRuling(){}

    public AdvanceRuling(String id, String category, String subcategory, String applicant_name, String question, String order_no, String date, String month, String year, String doc_url, String section_sgst_act, String created_at, String updated_at, String status, String hashtag) {
        this.id = id;
        this.category = category;
        this.subcategory = subcategory;
        this.applicant_name = applicant_name;
        this.question = question;
        this.order_no = order_no;
        this.date = date;
        this.month = month;
        this.year = year;
        this.doc_url = doc_url;
        this.section_sgst_act = section_sgst_act;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.hashtag = hashtag;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getDoc_url() {
        return doc_url;
    }

    public void setDoc_url(String doc_url) {
        this.doc_url = doc_url;
    }

    public String getSection_sgst_act() {
        return section_sgst_act;
    }

    public void setSection_sgst_act(String section_sgst_act) {
        this.section_sgst_act = section_sgst_act;
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

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
