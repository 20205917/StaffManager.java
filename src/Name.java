import java.util.Objects;

/**
 * A class that represents a name. The name is composed of a first name and a last name.
 *
 * @author nil
 * @version 1.0
 */
public class Name {
    private String firstName;
    private String lastName;


    public Name() {
        firstName = "";
        lastName = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Name(String first, String last) {
        firstName = first;
        lastName = last;
    }

    /**
     * rewrite the hashCode method for the Name class
     *
     * @return the hash code of the name
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
