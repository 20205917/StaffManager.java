import java.time.ZoneId;

/**
 * This class represents a smart card number. A smart card number is composed of
 * three components: a first component, a random component and a year component.
 * The first component is a uppercase string ,consisting of two letters.
 * The random component is a random integer
 * The year component is the issued year.
 *
 * @author nil
 */
public class SmartCardNumber {
    private final String NameComponent;
    private final int randComponent;
    private final int year;

    public SmartCardNumber(String firstComponent, int random, int year) {
        NameComponent = firstComponent;
        randComponent = random;
        this.year = year;
    }

    @Override
    public String toString() {
        return NameComponent + "-" + randComponent + "-" + year;
    }
}
