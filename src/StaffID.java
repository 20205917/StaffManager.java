import java.util.HashMap;

public class StaffID {
    private final char letter;
    private final String num;

    private final String ID;

    public String getID() {
        return ID;
    }

    //provide access to each component
    public char getLetter() {
        return letter;
    }

    public String getNum() {
        return num;
    }

    public StaffID(char a, String i) {
        letter = a;
        num = i;
        ID = a + i;
    }

}
