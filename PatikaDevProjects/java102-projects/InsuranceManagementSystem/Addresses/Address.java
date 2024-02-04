package InsuranceManagementSystem.Addresses;

public abstract class Address {
    private final String address;

    public Address(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
