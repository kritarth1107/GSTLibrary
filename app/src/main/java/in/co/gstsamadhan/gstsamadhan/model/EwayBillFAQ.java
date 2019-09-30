package in.co.gstsamadhan.gstsamadhan.model;

public class EwayBillFAQ {

    String id,serial,que,ans,status,created_at,updated_at;

    public EwayBillFAQ(){}

    public EwayBillFAQ(String id, String serial, String que, String ans, String status, String created_at, String updated_at) {
        this.id = id;
        this.serial = serial;
        this.que = que;
        this.ans = ans;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
