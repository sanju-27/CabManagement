import java.util.Objects;

public class Location {

    int id;
    String name;
    int distance;
    int cabId;

    public Location(int id, String name, int distance, int cabId) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.cabId = cabId;
    }

    public Location(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", distance=" + distance +
                ", cabId=" + cabId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
