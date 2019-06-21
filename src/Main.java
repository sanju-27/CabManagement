import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int rideId = 0;

    public static void main(String[] args) throws IOException {
        Admin admin = new Admin();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lch;
        do {
            System.out.println("0.Admin\n1.Driver\n2.Customer\n3.Exit\nEnter choice...");
            lch = Integer.parseInt(br.readLine());
            switch (lch) {
                case 0:
                    System.out.println("Welcome Admin");
                    int ch1;
                    do {
                        System.out.println("1. View Cabs\n2.View Customer\n3.New Cab\n4.New Location\n5.View Locations\n6.Exit");
                        ch1 = Integer.parseInt(br.readLine());
                        switch (ch1) {
                            case 1:
                                admin.viewCabs();
                                break;
                            case 2:
                                admin.viewHistory();
                                break;
                            case 3:
                                admin.addCab();
                                break;
                            case 4:
                                admin.addLoc();
                                break;
                            case 5:
                                admin.viewLoc();
                                break;
                        }
                    } while (ch1 < 6);
                    break;
                case 1:
                    Drivers d = admin.dLogin();
                    if (d != null) {
                        System.out.println("Welcome " + d.name);
                        d.viewHistory();
                    }
                    break;
                case 2:
                    Customer c;
                    System.out.println("1.Existing\n2.New User");
                    ch1 = Integer.parseInt(br.readLine());
                    int id;
                    if (ch1 == 2) {
                        c = admin.newCust();
                    } else {
                        System.out.println("Enter your ID");
                        id = Integer.parseInt(br.readLine());
                        c = admin.customer.get(id - 1);
                        System.out.println("Enter Password");
                        if (br.readLine().equals(c.getPwd()))
                            System.out.println("Success...");
                        else {
                            System.out.println("Password Incorrect");
                            break;
                        }
                    }
                    int ch2;
                    do {
                        System.out.println("1.Hail Cab\n2.Ride Summary\n3.Exit");
                        ch2 = Integer.parseInt(br.readLine());
                        if (ch2 == 1) {
                            admin.hail(c, rideId++);
                        } else if (ch2 == 2)
                            c.viewHistory();
                    } while (ch2 < 3);

            }

        } while (lch < 3);
    }
}
