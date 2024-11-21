/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restaurant;

/**
 *
 * @author Mata
 */
import java.io.*;
import java.util.*;

public class Restaurant {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        Scanner sc = new Scanner(System.in);
        String filename = "employees.txt";
        int id;
        String username,password;
                
        manager.loadFromFile(filename);
        

        manager.saveToFile(filename);
        id = sc.nextInt();
        sc.nextLine();
        password = sc.nextLine();
        manager.login(id,password);

    }
}

class UserCredentials {
    //Store the username and password
    private String username;
    private String password;

    // Constructor
    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}

class EmployeeManager{
    private HashMap<Integer, UserCredentials> employeeMap; // store the name and password with Employee ID
    
    public EmployeeManager(){
        employeeMap = new HashMap<>();
    }
    public void addEmployee(int idNumber, String username, String password) {
        employeeMap.put(idNumber, new UserCredentials(username, password));
    }

    // Retrieve an employee's credentials
    public UserCredentials getEmployee(int idNumber) {
        return employeeMap.get(idNumber);
    }
        public void clearEmployees() {
        employeeMap.clear();
    }

    // Save employee data to a file
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<Integer, UserCredentials> entry : employeeMap.entrySet()) {
                int id = entry.getKey();
                UserCredentials credentials = entry.getValue();
                writer.write(id + "," + credentials.getUsername() + "," + credentials.getPassword());
                writer.newLine();
            }
            System.out.println("Employee data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Load employee data from a file
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String username = parts[1];
                    String password = parts[2];
                    addEmployee(id, username, password);
                }
            }
            System.out.println("Employee data loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
public void login(int idNumber, String password) {
    UserCredentials credentials = employeeMap.get(idNumber);
    System.out.println(password);

    if (credentials != null) {
        System.out.println("Checking password for " + credentials.getUsername());  // Debug print
        if (credentials.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome " + credentials.getUsername());
        } else {
            System.out.println("Incorrect password.");
        }
    } else {
        System.out.println("Wrong ID.");
    }
}
}
    





