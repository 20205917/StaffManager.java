import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
        Assertions.assertNotNull(staffs[0] = SM.employStaff("charlie","chaplin",new Date(1990),Constants.Researcher,Constants.Fixed));
        Assertions.assertNotNull(staffs[1] = SM.employStaff("Olivia","Jones",new Date(1991),Constants.Researcher,Constants.Permanent));
        Assertions.assertNotNull(staffs[2] = SM.employStaff("Emma","Taylor",new Date(1992),Constants.Lecturer,Constants.Fixed));
        Assertions.assertNotNull(staffs[3] = SM.employStaff("Charlotte","Brown",new Date(1993),Constants.Lecturer,Constants.Permanent));
        Assertions.assertNotNull(staffs[4] = SM.employStaff("Oliver","Evans",new Date(1980),Constants.Lecturer,Constants.Fixed));

        //Check if all staff are included
        HashSet<Staff> staffSet = new HashSet<>(SM.getAllStaff());
        for (int i = 0; i<=1; i++){
            Assertions.assertEquals(Researcher.class,staffs[i].getClass());
            Assertions.assertTrue(staffSet.contains(staffs[i]));
        }
        for (int i = 2; i<=4; i++){
            Assertions.assertEquals(Lecturer.class,staffs[i].getClass());
            Assertions.assertTrue(staffSet.contains(staffs[i]));
        }

        //Check number of occupations
        Assertions.assertEquals(2,SM.noOfStaff(Constants.Researcher));
        Assertions.assertEquals(3,SM.noOfStaff(Constants.Lecturer));

        //  Misuse function employStaff
        Assertions.assertNull(staffs[5] = SM.employStaff("James","Thomas",new Date(1945),Constants.Lecturer,Constants.Permanent));
        Assertions.assertNull(staffs[6] = SM.employStaff("Olivia","Jones",new Date(1991),Constants.Lecturer,Constants.Permanent));
        Assertions.assertNull(staffs[7] = SM.employStaff("Emma","Taylor",new Date(1992),Constants.Researcher,Constants.Fixed));

        //Test whether the resignation is successful
        Assertions.assertTrue(staffSet.contains(staffs[4]));
        SM.terminateStaff(staffs[4].getStaffID());
        staffSet = new HashSet<>(SM.getAllStaff());
        Assertions.assertFalse(staffSet.contains(staffs[4]));
        staffs[4] = null;

        //Check number of occupations again
        Assertions.assertEquals(2,SM.noOfStaff(Constants.Researcher));
        Assertions.assertEquals(2,SM.noOfStaff(Constants.Lecturer));

        //Test add data
        ArrayList<Name> students = new ArrayList<>(SM.getStudentSet());
        ArrayList<Module> modules = new ArrayList<>(SM.getModuleSet());

        int sum = 0;
        HashSet<Name> nameHashSet = new HashSet<>();
        HashSet<Module> moduleHashSet = new HashSet<>();
        boolean isTure;

        System.out.println("StaffType: "+staffs[0].getStaffType()+"  StaffID: "+staffs[0].getStaffID().getID()+"  SmartCard: "+staffs[0].getSmartCard().toString());
        for(Name name : students){
            sum++;
            nameHashSet.clear();
            nameHashSet.add(name);
            isTure = SM.addData(staffs[0].getStaffID(),moduleHashSet,nameHashSet);
            if(sum<10){
                Assertions.assertTrue(isTure);
                Assertions.assertFalse(((Researcher)staffs[0]).isEnough());
            }

            else if(sum == 10){
                Assertions.assertTrue(isTure);
                Assertions.assertTrue(((Researcher)staffs[0]).isEnough());
                ((Researcher)staffs[0]).listStudents();
            }
            else {
                Assertions.assertFalse(isTure);
                Assertions.assertTrue(((Researcher)staffs[0]).isEnough());
            }

        }
        ((Researcher)staffs[0]).listStudents();
        System.out.println();

        sum = 0;
        System.out.println("StaffType: "+staffs[2].getStaffType()+"  StaffID: "+staffs[2].getStaffID().getID()+"  SmartCard: "+staffs[2].getSmartCard().toString());
        for(Module module : modules){
            sum+= module.getCredit();
            moduleHashSet.clear();
            moduleHashSet.add(module);
            isTure = SM.addData(staffs[0].getStaffID(),moduleHashSet,nameHashSet);
            if(sum<40){
                Assertions.assertTrue(isTure);
                Assertions.assertFalse(((Lecturer)staffs[2]).isEnough());
            }
            else if(sum == 40){
                Assertions.assertTrue(isTure);
                Assertions.assertTrue(((Researcher)staffs[0]).isEnough());
            }
            else{
                Assertions.assertFalse(isTure);
                Assertions.assertTrue(((Lecturer)staffs[2]).isEnough());
            }
        }
        ((Lecturer)staffs[2]).listModules();
        System.out.println();

        //Test add  nonexistence data
        nameHashSet.clear();
        nameHashSet.add(new Name("a","b"));
        moduleHashSet.clear();
        moduleHashSet.add(new Module("1","2",2,10));
        Assertions.assertFalse(SM.addData(staffs[0].getStaffID(),moduleHashSet,nameHashSet));
        Assertions.assertFalse(SM.addData(staffs[2].getStaffID(),moduleHashSet,nameHashSet));

        //Test ExpiryDate
        Calendar cal = Calendar.getInstance();
        for(Staff staff : SM.getAllStaff()){
            cal.setTime(staff.getSmartCard().getIssueData());
            if (staff.getStaffEmploymentStatus().equals(Constants.Permanent)) {
                cal.add(Calendar.YEAR, 10);
            } else {
                cal.add(Calendar.YEAR, 2);
            }
            Assertions.assertEquals(cal.getTime(),staff.getSmartCard().getExpiryDate());
        }

    }
}
