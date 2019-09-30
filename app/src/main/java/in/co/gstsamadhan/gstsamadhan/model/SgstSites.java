package in.co.gstsamadhan.gstsamadhan.model;

public class SgstSites {
    String id,state,url,status;
    public SgstSites(){}

    public SgstSites(String id, String state, String url, String status) {
        this.id = id;
        this.state = state;
        this.url = url;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
