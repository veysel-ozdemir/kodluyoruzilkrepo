package InsuranceManagementSystem;

import InsuranceManagementSystem.Accounts.*;
import InsuranceManagementSystem.Addresses.Address;
import InsuranceManagementSystem.Addresses.BusinessAddress;
import InsuranceManagementSystem.Addresses.HomeAddress;
import InsuranceManagementSystem.Insurances.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class LoginPortal {
    final static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        ArrayList<Address> addresses1 = new ArrayList<>();
        addresses1.add(new HomeAddress("France"));
        addresses1.add(new BusinessAddress("Paris, France"));
        List<Insurance> insurances1 = new ArrayList<>();
        insurances1.add(new CarInsurance("Car Insurance", 3000, new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2013"), new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2014")));
        insurances1.add(new HealthInsurance("Health Insurance", 4500, new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2013"), new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2014")));
        insurances1.add(new ResidenceInsurance("Residence Insurance", 2500, new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2013"), new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2014")));

        ArrayList<Address> addresses2 = new ArrayList<>();
        addresses2.add(new HomeAddress("United Kingdom"));
        addresses2.add(new BusinessAddress("Southampton, UK"));
        List<Insurance> insurances2 = new ArrayList<>();
        insurances2.add(new HealthInsurance("Health Insurance", 4500, new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2013"), new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2014")));
        insurances2.add(new TravelInsurance("Travel Insurance", 2500, new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2013"), new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2014")));

        ArrayList<Address> addresses3 = new ArrayList<>();
        addresses3.add(new HomeAddress("United States"));
        addresses3.add(new BusinessAddress("New Jersey, USA"));

        List<Insurance> insurances3 = new ArrayList<>();
        insurances3.add(new HealthInsurance("Health Insurance", 4500, new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2013"), new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2014")));

        User user1 = new User("Alex", "Andre", "alex@andre.com", "alex123", "CEO", 34, addresses1, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012"));
        User user2 = new User("Chris", "Paul", "chris@paul.com", "chris123", "CTO", 28, addresses2, new SimpleDateFormat("dd-MM-yyyy").parse("10-10-2010"));
        User user3 = new User("Jane", "Johnson", "jane@johnson.com", "jane123", "System Manager", 44, addresses3, new SimpleDateFormat("dd-MM-yyyy").parse("11-11-2011"));

        Account account1 = new IndividualAccount(1, user1, insurances1, AuthenticationStatus.FAIL);
        Account account2 = new EnterpriseAccount(1, user2, insurances2, AuthenticationStatus.FAIL);
        Account account3 = new IndividualAccount(1, user3, insurances3, AuthenticationStatus.FAIL);

        TreeSet<Account> accounts = new TreeSet<>();
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        AccountManager accountManager = new AccountManager(accounts);

        String email = SCANNER.next();
        String password = SCANNER.next();

        accountManager.login(email, password);
    }
}
