package InsuranceManagementSystem.Insurances;

import java.util.Date;

public class TravelInsurance extends Insurance{
    public TravelInsurance(String name, double price, Date startDate, Date dueDate) {
        super(name, price, startDate, dueDate);
    }

    @Override
    public void calculate() {

    }
}
