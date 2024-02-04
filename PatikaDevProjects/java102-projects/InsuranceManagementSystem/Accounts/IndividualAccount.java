package InsuranceManagementSystem.Accounts;

import InsuranceManagementSystem.Insurances.Insurance;
import InsuranceManagementSystem.User;
import java.util.List;

public class IndividualAccount extends Account {
    public IndividualAccount(int accountID, User user, List<Insurance> insurances, AuthenticationStatus authenticationStatus) {
        super(accountID, user, insurances, authenticationStatus);
    }

    @Override
    void addInsurancePolicy() {

    }
}
