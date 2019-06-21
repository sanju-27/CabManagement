import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {

    int id;
    String name;
    int age;
    String gender;
    List<Ride> rideList = new ArrayList<>();
    private String pwd;

    public Customer(int id, String name, String pwd, int age, String gender) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.age = age;
        this.gender = gender;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void viewHistory() {
        System.out.println("Customer ID: " + this.id);
        System.out.println("Customer name: " + this.name);
        System.out.println("Trip Details:");
        if (this.rideList.isEmpty()) {
            System.out.println("No rides Yet");
            return;
        }
        System.out.println(String.format("%15s%15s%15s%15s", "Source", "Destination", "Cab Detail", "Fare"));
        for (Ride r : this.rideList) {
            System.out.println(String.format("%15s%15s%15s%15s", r.from.name, r.to.name, r.d.id, r.fare));
        }

    }
}
