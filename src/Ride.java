public class Ride {
    int id;
    Location from, to;
    double fare, com;
    Customer c;
    Drivers d;

    public Ride(int id, Location from, Location to, Customer c, Drivers d) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.c = c;
        this.d = d;
        this.fare = Math.abs(this.from.distance - this.to.distance)*10;
        this.com = this.fare*0.3;
    }
}
