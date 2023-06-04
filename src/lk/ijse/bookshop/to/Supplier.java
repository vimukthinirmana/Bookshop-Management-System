package lk.ijse.bookshop.to;

public class Supplier {
    private String supplierId;
    private String name;
    private String email;
    private int phoneNo;

    private String employeId;

    public Supplier(String supplierId){
    }

    public Supplier(String supplierId, String name, String email, int phoneNo,String employeId){
        this.supplierId = supplierId;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.employeId = employeId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }



    public String getEmployeId() {
        return employeId;
    }

    public void setEmployeId(String employeId) {
        this.employeId = employeId;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId='" + supplierId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", employeId=" + employeId +
                '}';
    }
}
