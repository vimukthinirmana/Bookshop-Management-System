package lk.ijse.bookshop.to;

public class Author {
    private String authorId;
    private String name;
    private String email;
    private int phoneNo;

    public Author(String authorId){
    }

    public Author(String authorId, String name, String email, int phoneNo){
        this.authorId = authorId;
        this.name = name;
        this.email = email;
        this.phoneNo =phoneNo;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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

    @Override
    public String toString() {
        return "Author{" +
                "authorId='" + authorId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                '}';
    }
}
