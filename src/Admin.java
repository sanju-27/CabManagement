import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Admin {

    List<Drivers> driver = new ArrayList<>();
    List<Customer> customer = new ArrayList<>();
    List<Location> location = new ArrayList<>();

    public Admin() {
        Drivers d1 = new Drivers(1, 43, "aaa", "111", "M", "D");
        Drivers d2 = new Drivers(2, 31, "bbb", "222", "M", "G");
        Drivers d3 = new Drivers(3, 38, "ccc", "333", "F", "H");
        Drivers d4 = new Drivers(4, 28, "ddd", "444", "F", "A");
        driver.add(d1);
        driver.add(d2);
        driver.add(d3);
        driver.add(d4);
        Customer c1 = new Customer(1, "zz", "99", 25, "F");
        Customer c2 = new Customer(2, "yy", "88", 61, "M");
        Customer c3 = new Customer(3, "xx", "77", 22, "M");
        Customer c4 = new Customer(4, "ww", "66", 36, "F");
        customer.add(c1);
        customer.add(c2);
        customer.add(c3);
        customer.add(c4);
        Location l1 = new Location(1, "A", 0, 4);
        Location l2 = new Location(3, "C", 4, 0);
        Location l3 = new Location(4, "D", 7, 1);
        Location l4 = new Location(6, "F", 9, 0);
        Location l5 = new Location(2, "B", 15, 0);
        Location l6 = new Location(7, "G", 18, 2);
        Location l7 = new Location(5, "H", 20, 3);
        Location l8 = new Location(8, "E", 23, 0);
        location.add(l1);
        location.add(l2);
        location.add(l3);
        location.add(l4);
        location.add(l5);
        location.add(l6);
        location.add(l7);
        location.add(l8);
    }

    public Drivers dLogin() throws IOException {
        System.out.print("Enter UID: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(br.readLine());
        System.out.print("Enter Password: ");
        String pwd = (br.readLine());
        if (this.driver.contains(new Drivers(id))) {
            if (this.driver.get(this.driver.indexOf(new Drivers(id))).getPwd().equals(pwd)) {
                System.out.println("Succes...");
                return this.driver.get(this.driver.indexOf(new Drivers(id)));
            } else {
                System.out.println("incorrect Password");
                return null;
            }
        } else {
            System.out.println("Invalid UID");
            return null;
        }
    }

    public Customer newCust() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the Following...");
        System.out.println("Name\tPassword\tAge\tGender[M/F]");
        String[] s = br.readLine().split("\t");
        Customer c = new Customer(this.customer.size() + 1, s[0], s[1], Integer.parseInt(s[2]), s[3]);
        this.customer.add(c);
        return c;
    }

    public void hail(Customer c, int rideId) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter From");
        String from = br.readLine();
        Location floc = this.location.get(this.location.indexOf(new Location(from)));
        System.out.println("Enter to");
        String to = br.readLine();
        Location toloc = this.location.get(this.location.indexOf(new Location(to)));
        int i = 0;
        int min = Integer.MAX_VALUE, mid = 0;
        int[] a = new int[this.driver.size()];
        for (Drivers d : driver) {
            Location tl = location.get(location.indexOf(new Location(d.loc)));
//            System.out.println(tl);
            a[i] = Math.abs(floc.distance - tl.distance);
            if (a[i] < min && d.rest != true) {
                min = a[i];
                mid = i;
            }
            if (min == a[i]) {
                if (d.rides.size() < driver.get(mid).rides.size())
                    mid = i;
            }
            i++;
        }

        Drivers d = this.driver.get(mid);
        System.out.println("Cab is at Location"+d.loc);
        Ride r = new Ride(rideId, floc, toloc, c, d);
        System.out.println("Ride Details:");
        System.out.println("From: " + floc.name + "\tTo: " + to);
        System.out.println("Cab Driver: " + d.name);
        System.out.println("Estimate: Rs." + r.fare);
        System.out.println("Confirm Ride? [Y/N]");
        if (br.readLine().equalsIgnoreCase("N")) {
            System.out.println("Ride Cancelled");
            return;
        }
        for (Drivers dx : this.driver) {
            dx.rest = false;
        }
        d.rides.add(r);
        c.rideList.add(r);
        System.out.println("Ride Completed");
        location.get(location.indexOf(toloc)).cabId = d.id;
        driver.get(driver.indexOf(d)).loc = toloc.name;
        d.rest = true;
    }

    public void viewLoc() {
        System.out.println("Place\tDistance");
        for (Location l : location) {
            System.out.println(l.name + "\t" + l.distance);
        }
    }

    public void viewCabs() {

        if (driver.isEmpty()) {
            System.out.println("No cabs to show :(");
            return;
        }
        for (Drivers d : this.driver) {
            double fares = 0;
            double comm = 0;
            StringBuilder sb = new StringBuilder();
            System.out.println("Cab ID: " + d.id);
            System.out.println("Cab Driver Name: " + d.name);
            System.out.println("Number of Trips: " + d.rides.size());
            System.out.println("Trip details:");
            if (d.rides.isEmpty()) {
                sb.append("No rides yet");
            } else {
                sb.append(String.format("%15s%15s%15s%15s%15s\n", "Source", "Destination", "Cab Detail", "Fare", "ZULA Commission"));
                for (Ride r : d.rides) {
                    fares += r.fare;
                    comm += r.com;
                    sb.append(String.format("%15s%15s%15s%15s%15s\n", r.from.name, r.to.name, r.c.id, r.fare, r.com));
                }
            }
            System.out.println("Total Fare Collected: " + fares);
            System.out.println("Total ZULA Commission: " + comm);
            System.out.println(sb.toString());
        }

    }

    public void addCab() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Name of Driver: ");
        String name = br.readLine();
        System.out.print("Enter Password: ");
        String pwd = br.readLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(br.readLine());
        System.out.print("Enter Gender: ");
        String gen = br.readLine();
        System.out.println("Enter Initial Location");
        String l = br.readLine();
        Location loc = location.get(location.indexOf(new Location(l)));
        Drivers d = new Drivers(driver.size() + 1, age, name, pwd, gen, loc.name);
        driver.add(d);
        location.get(location.indexOf(new Location(l))).cabId = d.id;
    }

    public void addLoc() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter name of Location: ");
        String name = br.readLine();
        System.out.print("Enter Distance from Origin: ");
        int dist = Integer.parseInt(br.readLine());
        Location l = new Location(location.size() + 1, name, dist, 0);
        for (int i = 0; i < location.size(); i++) {
            if (location.get(i).distance > dist) {
                location.add(i, l);
                break;
            }
        }
        System.out.println("Success...");
    }

    public void viewHistory() {
        for (Customer c : customer) {
            double fares = 0;
            StringBuilder sb = new StringBuilder();
            System.out.println("Customer ID: " + c.id);
            System.out.println("Customer name: " + c.name);
            System.out.println("Total Trips: " + c.rideList.size());
            System.out.println("Trip Details:");
            if (c.rideList.isEmpty()) {
                sb.append("No rides Yet\n");
            } else {
                sb.append(String.format("%15s%15s%15s%15s\n", "Source", "Destination", "Cab Detail", "Fare"));
                for (Ride r : c.rideList) {
                    sb.append(String.format("%15s%15s%15s%15s\n", r.from.name, r.to.name, r.d.id, r.fare));
                    fares += r.fare;
                }
            }
            System.out.println("Total Fare Given: " + fares);
            System.out.println(sb.toString());
        }
    }
}
