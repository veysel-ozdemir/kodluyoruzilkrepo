package InsuranceManagementSystem.Insurances;

import java.util.Date;

public class CarInsurance extends Insurance {
    public CarInsurance(String name, double price, Date startDate, Date dueDate) {
        super(name, price, startDate, dueDate);
    }

    @Override
    public void calculate() {

    }
}
