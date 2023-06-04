package lk.ijse.bookshop.to;

public class Employe {
    private String employeId;
    private String name;
    private String address;
    private int phoneNo;
    private String roleType;
    private double salary;

    public Employe(String employeId){
    }
    public Employe(String employeId, String name, String address, int phoneNo, String roleType, double salary){
        this.employeId = employeId;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.roleType = roleType;
        this.salary = salary;
    }


    public String getEmployeId() {
        return employeId;
    }

    public void setEmployeId(String employeId) {
        this.employeId = employeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String toString(){
        return "Employe{" +
                "employeId='" + employeId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", roleType='" + roleType + '\'' +
                ", salary=" + salary +
                '}';
    }
}
