import java.util.HashMap;

/**
 * StaffID is a combination of a letter and a 3-digit number
 * The letter is a lower case letter from a to z
 * The number is a 3-digit number from 100 to 999
 * The letter and number are concatenated to form the ID
 * The class provides a static method to generate a new StaffID
 *
 * @author nil
 * @version 1.0
 */
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

    /**
     * generateStaffID generate a new StaffID
     *
     * @param staffMap a HashMap of all the staff
     *                 the key is the ID, the value is the staff
     *                 the method will check if the generated ID is already in the HashMap
     *                 if it is, it will generate a new ID
     *                 if it is not, it will return the new ID
     * @return a new StaffID
     */
    static public StaffID generateStaffID(HashMap<String, Staff> staffMap) {
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
