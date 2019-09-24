package in.co.gstsamadhan.gstsamadhan.model;

public class Coupon {
    private String coupon_id;
    private String coupon_code;
    private String discount;
    private String discount_type;
    private String min_amount_limit;
    private String valid_from;
    private String valid_upto;
    private String use_limit;
    private String created_at;
    private String updated_at;
    private String status;
    private String coupon_status;

    public Coupon(){}

    public Coupon(String coupon_id, String coupon_code, String discount, String discount_type, String min_amount_limit, String valid_from, String valid_upto, String use_limit, String created_at, String updated_at, String status, String coupon_status) {
        this.coupon_id = coupon_id;
        this.coupon_code = coupon_code;
        this.discount = discount;
        this.discount_type = discount_type;
        this.min_amount_limit = min_amount_limit;
        this.valid_from = valid_from;
        this.valid_upto = valid_upto;
        this.use_limit = use_limit;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.coupon_status = coupon_status;
    }


    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public String getMin_amount_limit() {
        return min_amount_limit;
    }

    public void setMin_amount_limit(String min_amount_limit) {
        this.min_amount_limit = min_amount_limit;
    }

    public String getValid_from() {
        return valid_from;
    }

    public void setValid_from(String valid_from) {
        this.valid_from = valid_from;
    }

    public String getValid_upto() {
        return valid_upto;
    }

    public void setValid_upto(String valid_upto) {
        this.valid_upto = valid_upto;
    }

    public String getUse_limit() {
        return use_limit;
    }

    public void setUse_limit(String use_limit) {
        this.use_limit = use_limit;
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

    public String getCoupon_status() {
        return coupon_status;
    }

    public void setCoupon_status(String coupon_status) {
        this.coupon_status = coupon_status;
    }
}
