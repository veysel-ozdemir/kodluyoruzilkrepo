package PatikaStore;

public abstract class Category {
    private int id, unitPrice, stockAmount, memory, powerSupply, ram;
    private double discount, displayInch;
    private String name, color;
    private Brand brand;

    public Category(int id, int unitPrice, int stockAmount, int memory, int powerSupply, int ram, double discount, double displayInch, String name, String color, Brand brand) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.stockAmount = stockAmount;
        this.memory = memory;
        this.powerSupply = powerSupply;
        this.ram = ram;
        this.discount = discount;
        this.displayInch = displayInch;
        this.name = name;
        this.color = color;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public int getMemory() {
        return memory;
    }

    public int getPowerSupply() {
        return powerSupply;
    }

    public int getRam() {
        return ram;
    }

    public double getDiscount() {
        return discount;
    }

    public double getDisplayInch() {
        return displayInch;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Brand getBrand() {
        return brand;
    }
}
