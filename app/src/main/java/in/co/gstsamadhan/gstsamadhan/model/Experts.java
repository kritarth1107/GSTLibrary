package in.co.gstsamadhan.gstsamadhan.model;

public class Experts {
    private String id;
    private String serial;
    private String name;
    private String designation;
    private String expertise;
    private String image;
    private String state;
    private String city;
    private String is_premium;
    private String created_at;
    private String updated_at;
    private String status;
    private String price;

    public Experts(){}

    public Experts(String id, String serial, String name, String designation, String expertise, String image, String state, String city, String isPremium, String created_at, String updated_at, String status, String price) {
        this.id = id;
        this.serial = serial;
        this.name = name;
        this.designation = designation;
        this.expertise = expertise;
        this.image = image;
        this.state = state;
        this.city = city;
        this.is_premium = isPremium;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsPremium() {
        return is_premium;
    }

    public void setIsPremium(String isPremium) {
        this.is_premium = isPremium;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
