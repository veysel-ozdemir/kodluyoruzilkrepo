package PatikaStore;

import java.util.TreeSet;

public class Brand implements Comparable<Brand> {
    private int id;
    private String name;
    private static TreeSet<Brand> brands;

    static {
        brands = new TreeSet<>();
        String[] brandNames = {"Samsung", "Lenovo", "Apple", "Huawei", "Casper", "Asus", "HP", "Xiaomi", "Monster"};
        int i = 0;
        for (String brandName : brandNames) {
            brands.add(new Brand(++i, brandName));
        }
    }

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static TreeSet<Brand> getBrands() {
        return brands;
    }

    @Override
    public int compareTo(Brand o) {
        return this.getName().compareTo(o.getName());
    }


}
