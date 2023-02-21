import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

public class Test {
    /*
    charlie chaplin
    Olivia Jones
    Emma Taylor

    Charlotte Brown
    Oliver Evans
    James Thomas
     */
    public static void main(String[] args) throws IOException {
        Staff[] staffs = new Staff[10];
        StaffManager SM = StaffManager.getInstance();
        SM.readInModules("modules.TXT");
        SM.readInStudents("students.TXT");
        Assertions.assertNotNull(staffs[0] = SM.employStaff("charlie", "chaplin", new Date(1990), Constants.Researcher, Constants.Fixed));
        Assertions.assertNotNull(staffs[1] = SM.employStaff("Olivia", "Jones", new Date(1991), Constants.Researcher, Constants.Permanent));
        Assertions.assertNotNull(staffs[2] = SM.employStaff("Emma", "Taylor", new Date(1992), Constants.Lecturer, Constants.Fixed));
        Assertions.assertNotNull(staffs[3] = SM.employStaff("Charlotte", "Brown", new Date(1993), Constants.Lecturer, Constants.Permanent));
        Assertions.assertNull(staffs[4] = SM.employStaff("Oliver", "Evans", new Date(1980), Constants.Lecturer, Constants.Fixed));

        HashSet<Staff> staffSet = new HashSet<>(SM.getAllStaff());
        for (int i = 0; i < 4; i++)
            Assertions.assertTrue(staffSet.contains(staffs[i]));

        Assertions.assertEquals(2, SM.noOfStaff(Constants.Researcher));
        Assertions.assertEquals(3, SM.noOfStaff(Constants.Lecturer));
        Assertions.assertNull(staffs[5] = SM.employStaff("James", "Thomas", new Date(1945), Constants.Lecturer, Constants.Permanent));
        Assertions.assertNull(staffs[6] = SM.employStaff("Olivia", "Jones", new Date(1991), Constants.Lecturer, Constants.Permanent));
        Assertions.assertNull(staffs[7] = SM.employStaff("Emma", "Taylor", new Date(1992), Constants.Researcher, Constants.Fixed));
    }
}
