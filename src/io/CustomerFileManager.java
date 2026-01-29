package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Customer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomerFileManager {
    private static final String FILE_PATH = "data/customers.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Customer> loadCustomers() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Customer>>() {}.getType();
            List<Customer> customers = gson.fromJson(reader, listType);
            return customers != null ? customers : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error loading customers: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveCustomers(List<Customer> customers) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(customers, writer);
            System.out.println("Customers saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    public static void addCustomer(Customer customer) {
        List<Customer> customers = loadCustomers();
        customers.add(customer);
        saveCustomers(customers);
        System.out.println("Customer added: " + customer.getName());
    }

    public static void removeCustomer(String customerId) {
        List<Customer> customers = loadCustomers();
        customers.removeIf(c -> c.getId().equals(customerId));
        saveCustomers(customers);
        System.out.println("Customer removed: " + customerId);
    }

    public static Customer findCustomerById(String customerId) {
        List<Customer> customers = loadCustomers();
        for (Customer c : customers) {
            if (c.getId().equals(customerId)) {
                return c;
            }
        }
        return null;
    }
}
