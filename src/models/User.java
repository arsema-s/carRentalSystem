package models;

// User class - parent class for Admin and Customer
public abstract class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    
    // Constructor
    public User(String id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    
    // Get methods
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getPassword() {
        return password;
    }
    
    // Set methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Abstract method - must be implemented by child classes
    public abstract String getRole();
    
    // To string method
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}
