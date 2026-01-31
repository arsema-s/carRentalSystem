package service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Admin;
import model.Customer;
import model.User;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private static final String ADMIN_FILE = "data/admins.json";
    private static final String CUSTOMER_FILE = "data/customers.json";

    // Configures Gson for date formatting and pretty printing
  
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, type, context) ->
                    new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, context) ->
                    LocalDate.parse(json.getAsString()))
            .create();

  
    private static User loggedInUser;
// Validate credentials against Admin then Customer tables
    
    public boolean login(String email, String password) {
       //check admin
        for (Admin admin : loadAdmins()) {
            if (admin.getEmail().equalsIgnoreCase(email) && admin.getPassword().equals(password)) {
                loggedInUser = admin;
                System.out.println("AuthService: Admin session started for -> " + admin.getName());
                return true;
            }
        }

       //check customer
        for (Customer customer : loadCustomers()) {
            if (customer.getEmail().equalsIgnoreCase(email) && customer.getPassword().equals(password)) {
                loggedInUser = customer;
                System.out.println("AuthService: Customer session started for -> " + customer.getName());
                return true;
            }
        }

        System.out.println("AuthService: Authentication failed for -> " + email);
        return false;
    }
// Terminate session
    public void logout() {
        if (loggedInUser != null) {
            System.out.println("AuthService: Session ended for -> " + loggedInUser.getName());
            loggedInUser = null;
        }
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

   

    private List<Admin> loadAdmins() {
        try (FileReader reader = new FileReader(ADMIN_FILE)) {
            Type listType = new TypeToken<ArrayList<Admin>>() {}.getType();
            List<Admin> admins = gson.fromJson(reader, listType);
            return admins != null ? admins : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Critical Error: Could not load admin database. " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<Customer> loadCustomers() {
        try (FileReader reader = new FileReader(CUSTOMER_FILE)) {
            Type listType = new TypeToken<ArrayList<Customer>>() {}.getType();
            List<Customer> customers = gson.fromJson(reader, listType);
            return customers != null ? customers : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Critical Error: Could not load customer database. " + e.getMessage());
            return new ArrayList<>();
        }
    }
}


