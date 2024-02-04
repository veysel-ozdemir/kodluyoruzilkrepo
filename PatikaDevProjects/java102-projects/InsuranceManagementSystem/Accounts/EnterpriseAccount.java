package InsuranceManagementSystem.Accounts;

import InsuranceManagementSystem.Insurances.Insurance;
import InsuranceManagementSystem.User;
import java.util.List;

public class EnterpriseAccount extends Account {
    public EnterpriseAccount(int accountID, User user, List<Insurance> insurances, AuthenticationStatus authenticationStatus) {
        super(accountID, user, insurances, authenticationStatus);
    }

    @Override
    void addInsurancePolicy() {

    }

}
