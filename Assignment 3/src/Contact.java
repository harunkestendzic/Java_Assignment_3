public class Contact {
protected String id;
protected String fullName;
private String email;

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Contact(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }
}
