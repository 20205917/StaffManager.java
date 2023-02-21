import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
