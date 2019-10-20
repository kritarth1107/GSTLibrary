package in.co.gstsamadhan.gstsamadhan.model;

public class Generate {
    String mobile, otp;
    boolean success;


    public Generate() {
    }

    public Generate(String mobile, String otp, boolean success) {
        this.mobile = mobile;
        this.otp = otp;
        this.success = success;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

