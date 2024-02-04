package InsuranceManagementSystem.Insurances;

import java.util.Date;

public abstract class Insurance {
    private String name;
    private double price;
    private Date startDate, dueDate;

    public Insurance(String name, double price, Date startDate, Date dueDate) {
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public abstract void calculate();

    @Override
    public String toString() {
        return getName();
    }
}
