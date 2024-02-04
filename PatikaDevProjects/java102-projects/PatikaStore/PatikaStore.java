package PatikaStore;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PatikaStore {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        SmartPhone samsung = new SmartPhone(1, 3199, 3, 128, 5500, 8, 0.3, 6.5, "GALAXY A51", "blue", Brand.getBrands().stream().toList().get(0));
        SmartPhone iPhone = new SmartPhone(2, 7379, 1, 64, 3200, 4, 0.15, 6.1, "11", "white", Brand.getBrands().stream().toList().get(2));
        SmartPhone redmi = new SmartPhone(3, 4012, 5, 128, 5000, 8, 0.35, 6.5, "NOTE 10 PRO", "black", Brand.getBrands().stream().toList().get(7));

        SmartPhone[] smartPhones = {samsung, iPhone, redmi};

        Notebook huawei = new Notebook(1, 7000, 3, 512, 2000, 16, 0, 14.0, "MATEBOOK 14", "black", Brand.getBrands().stream().toList().get(3));
        Notebook lenovo = new Notebook(2, 3699, 13, 1024, 2300, 8, 0, 14.0, "V14 IGL", "black", Brand.getBrands().stream().toList().get(1));
        Notebook asus = new Notebook(3, 8199, 10, 2048, 4000, 32, 0, 15.6, "TUF GAMING", "black", Brand.getBrands().stream().toList().get(5));

        Notebook[] notebooks = {huawei, lenovo, asus};

        start(smartPhones, notebooks);
    }

    private static void start(SmartPhone[] smartPhones, Notebook[] notebooks) {
        byte userSelection = 0;
        System.out.println("\n-- PatikaStore Product Selection --");
        System.out.print("1 - Notebook Process\n2 - Smart Phone Process\n3 - List Brands\n0 - Exit\nYour selection: ");

        try {
            userSelection = SCANNER.nextByte();
            if (userSelection < 0 || userSelection > 3) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid value entered");
        }

        if (userSelection == 0) {
            System.exit(0);
        } else if (userSelection == 1) {
            System.out.println("\nNotebooks\n------------------------------------------------------------------------------------------------------");
            System.out.format("| %-2s | %-30s | %-9s | %-9s | %-9s | %-9s | %-12s |\n", "ID", "Product Name", "Unit Price", "Brand", "Memory", "Display", "RAM");
            System.out.println("------------------------------------------------------------------------------------------------------");
            for (Notebook notebook : notebooks) {
                System.out.format("| %-2d | %-30s | %-7d TL | %-9s | %-9d | %-9.1f | %-12d |\n", 1, notebook.getBrand().getName() + " " + notebook.getName(), notebook.getUnitPrice(), notebook.getBrand().getName(), notebook.getMemory(), notebook.getDisplayInch(), notebook.getRam());
            }
            System.out.println("------------------------------------------------------------------------------------------------------");
        } else if (userSelection == 2) {
            System.out.println("\nSmart Phones\n-------------------------------------------------------------------------------------------------------------------------------");
            System.out.format("| %-2s | %-30s | %-9s | %-9s | %-9s | %-9s | %-9s | %-9s | %-9s |\n", "ID", "Product Name", "Unit Price", "Brand", "Memory", "Display", "POWER SUPPLY", "RAM", "COLOR");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            for (SmartPhone smartPhone : smartPhones) {
                System.out.format("| %-2d | %-30s | %-7d TL | %-9s | %-9d | %-9.1f | %-12d | %-9d | %-9s |\n", smartPhone.getId(), smartPhone.getBrand().getName() + " " + smartPhone.getName(), smartPhone.getUnitPrice(), smartPhone.getBrand().getName(), smartPhone.getMemory(), smartPhone.getDisplayInch(), smartPhone.getPowerSupply(), smartPhone.getRam(), smartPhone.getColor());
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("\nBrands\n---------");
            for (Brand brand : Brand.getBrands()) {
                System.out.println("- " + brand.getName());
            }
        }
    }
}
