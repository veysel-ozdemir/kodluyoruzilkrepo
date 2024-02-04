package InsuranceManagementSystem;

import InsuranceManagementSystem.Addresses.Address;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private String fistName, lastName, email, password, occupation;
    private int age;
    private ArrayList<Address> addresses;
    private Date lastEntry;

    public User(String fistName, String lastName, String email, String password, String occupation, int age, ArrayList<Address> addresses, Date lastEntry) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.occupation = occupation;
        this.age = age;
        this.addresses = addresses;
        this.lastEntry = lastEntry;
    }

    public String getFistName() {
        return fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public Date getLastEntry() {
        return lastEntry;
    }
}
