package InsuranceManagementSystem.Insurances;

import java.util.Date;

public class ResidenceInsurance extends Insurance {
    public ResidenceInsurance(String name, double price, Date startDate, Date dueDate) {
        super(name, price, startDate, dueDate);
    }

    @Override
    public void calculate() {

    }
}
