package model;

public class Admin extends User {

    public Admin(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }

    @Override
    public String getRole() {
        return "Admin";
    }
    @Override
    public String toString() {
        return String.format(
            "Admin{id='%s', name='%s', email='%s', phone='%s', role='%s'}",
            getId(), getName(), getEmail(), getPhone(), getRole()
        );
    }
