package InsuranceManagementSystem.Accounts;

import InsuranceManagementSystem.Addresses.Address;
import InsuranceManagementSystem.Addresses.AddressManager;
import InsuranceManagementSystem.Insurances.Insurance;
import InsuranceManagementSystem.User;

import java.util.List;

public abstract class Account implements Comparable<Account> {
    private int accountID;
    private User user;
    private List<Insurance> insurances;
    private AuthenticationStatus authenticationStatus;

    public Account(int accountID, User user, List<Insurance> insurances, AuthenticationStatus authenticationStatus) {
        this.accountID = accountID;
        this.user = user;
        this.insurances = insurances;
        this.authenticationStatus = authenticationStatus;
    }

    final void showUserInfo() {
        System.out.println("\n-- User Information Section --\n***********************************s");
        System.out.println("- Full Name: " + user.getFistName() + " " + user.getLastName());
        System.out.println("- Email: " + user.getEmail());
        System.out.println("- Password: " + user.getPassword());
        System.out.println("- Occupation: " + user.getOccupation());
        System.out.println("- Age: " + user.getAge());
        System.out.println("- Addresses: ");
        for (Address address : user.getAddresses()){
            System.out.printf("\t+ %s: %s\n", address.toString(), address.getAddress());
        }
        System.out.println("- Account Type: " + this);
        System.out.println("- Insurances: " + insurances.stream().toList());
        System.out.println("- Authentication Status: " + authenticationStatus.name());
    }

    public int getAccountID() {
        return accountID;
    }

    public User getUser() {
        return user;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public AuthenticationStatus getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(AuthenticationStatus authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    @Override
    public int compareTo(Account o) {
        return this.accountID < o.getAccountID() ? 1 : -1;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public boolean login(String email, String password) throws InvalidAuthenticationException {
        if (getUser().getEmail().equals(email) && getUser().getPassword().equals(password)) {
            setAuthenticationStatus(AuthenticationStatus.SUCCESS);
            return true;
        } else {
            setAuthenticationStatus(AuthenticationStatus.FAIL);
            throw new InvalidAuthenticationException();
        }
    }

    public void addAddress(Address address) {
        AddressManager.addAddress(address, getUser().getAddresses());
    }

    public void deleteAddress(Address address) {
        AddressManager.deleteAddress(address, getUser().getAddresses());
    }

    abstract void addInsurancePolicy();
}
