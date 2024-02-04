package InsuranceManagementSystem.Insurances;

import java.util.Date;

public class HealthInsurance extends Insurance {
    public HealthInsurance(String name, double price, Date startDate, Date dueDate) {
        super(name, price, startDate, dueDate);
    }

    @Override
    public void calculate() {

    }

}
