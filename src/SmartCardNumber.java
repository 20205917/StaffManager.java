import java.time.ZoneId;

public class SmartCardNumber {//unique
    private final String NameComponent;
    private final int randComponent;
    private final int year;

    public SmartCardNumber(String firstComponent, int random, int year) {
        NameComponent = firstComponent;
        randComponent = random;
        this.year = year;
    }

    public String toString() {
        return NameComponent + "-" + randComponent + "-" + year;
    }
}
