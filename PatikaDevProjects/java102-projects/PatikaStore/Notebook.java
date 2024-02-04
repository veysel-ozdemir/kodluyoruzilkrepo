package PatikaStore;

public class Notebook extends Category {
    public Notebook(int id, int unitPrice, int stockAmount, int memory, int powerSupply, int ram, double discount, double displayInch, String name, String color, Brand brand) {
        super(id, unitPrice, stockAmount, memory, powerSupply, ram, discount, displayInch, name, color, brand);
    }
}
