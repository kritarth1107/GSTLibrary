package in.co.gstsamadhan.gstsamadhan.model;

public class Generate {
    String success,mobile,otp;

    public Generate(String success, String mobile, String otp) {
        this.success = success;
        this.mobile = mobile;
        this.otp = otp;
    }
      public Generate(){}

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
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
}
