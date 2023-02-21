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

    static public StaffID generateStaffID( HashMap<String, Staff> staffMap){
        char letter = 'a';
        int num = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            for (int j = 100; j < 999; j++) {
                if (!staffMap.containsKey(i + String.valueOf(j))) {
                    letter = i;
                    num = j;
                    break;
                }
            }
        }
        if (letter == 'a' && num == 0)
            return null;
        return new StaffID(letter, String.valueOf(num));
    }

}
