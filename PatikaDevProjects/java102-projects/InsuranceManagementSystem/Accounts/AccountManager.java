package InsuranceManagementSystem.Accounts;

import java.util.TreeSet;

public class AccountManager {
    private TreeSet<Account> accounts;

    public AccountManager(TreeSet<Account> accounts) {
        this.accounts = accounts;
    }

    public TreeSet<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(TreeSet<Account> accounts) {
        this.accounts = accounts;
    }

    public void login(String email, String password) {
        for (Account account : accounts) {
            if (account.getUser().getEmail().equals(email) && account.getUser().getPassword().equals(password)) {
                try {
                    if (account.login(email, password)) {
                        System.out.println("\n-- Login Success --");
                        account.showUserInfo();
                    } else {
                        System.out.println("-- Login Failed --");
                    }
                } catch (InvalidAuthenticationException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }
}
