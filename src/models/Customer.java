package model;

public class Customer extends User {

    public Customer(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    @Override
public String toString() {
    return String.format(
        "Customer [ID=%s, Name=%s, Email=%s, Phone=%s]",
        id, name, email, phoneNumber
    );
}

