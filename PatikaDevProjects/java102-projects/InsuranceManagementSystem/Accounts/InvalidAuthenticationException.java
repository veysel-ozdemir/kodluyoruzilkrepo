package InsuranceManagementSystem.Accounts;

public class InvalidAuthenticationException extends Exception {
    @Override
    public String toString() {
        return "InvalidAuthenticationException occurred!";
    }
}
