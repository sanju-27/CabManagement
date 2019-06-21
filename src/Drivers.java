import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Drivers {

    int id, age;
    String name;
    private String pwd;
    String gender;
    boolean rest = false;
    String loc;
    List<Ride> rides = new ArrayList<>();

    public Drivers(int id, int age, String name, String pwd, String gender, String loc) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.pwd = pwd;
        this.gender = gender;
        this.loc = loc;
    }

    public String getPwd() {
        return pwd;
    }

    public Drivers(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drivers drivers = (Drivers) o;
        return id == drivers.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void viewHistory() {

        System.out.println("Cab ID: "+id);
        System.out.println("Cab Driver Name: "+name);
        System.out.println("Trip details:");
        if(rides.isEmpty())
        {
            System.out.println("No rides yet");
            return;
        }
        System.out.println(String.format("%15s%15s%15s%15s%15s","Source","Destination","Cab Detail","Fare","ZULA Commission"));
        for(Ride r: rides)
        {
            System.out.println(String.format("%15s%15s%15s%15s%15s",r.from.name,r.to.name,r.c.id,r.fare,r.com));
        }
    }
}
