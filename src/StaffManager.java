import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * StaffManager class is a singleton class that manages all staff in the university.
 * It is responsible to create a new staff, terminate a staff, and add data to a staff.
 * It also provides a method to get all staff in the university,and a method to get the number of staff of a certain type.
 * It is the central class in the system.
 *
 * @author nil
 * @version 1.0
 */
public class StaffManager {

    private static StaffManager instance;

    private StaffManager() {
        this.staffMap = new HashMap<>();
        this.smartCardNumbers = new HashSet<>();
        this.moduleSet = new HashSet<>();
        this.studentSet = new HashSet<>();
    }

    /**
     * Singleton pattern
     *
     * @return the only instance of StaffManager
     */
    public static StaffManager getInstance() {
        if (instance == null) {
            instance = new StaffManager();
        }
        return instance;
    }


    private final HashMap<String, Staff> staffMap;

    private final Set<String> smartCardNumbers;


    private final Set<Module> moduleSet;

    public Set<Name> getStudentSet() {
        return studentSet;
    }

    private final Set<Name> studentSet;

    public Set<Module> getModuleSet() {
        return moduleSet;
    }

    /**
     * Read in modules from a file, and store them in a set.
     * The file should be in the format of "moduleCode,moduleName,semester,credits"
     *
     * @param path the path of the file
     *             and file should be in the format of "moduleCode,moduleName,semester,credits"
     *             For example: "CS101,Programming,1,10"
     * @return a set of modules
     */
    public Set<Module> readInModules(String path) throws IOException {
        BufferedReader file = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path))));
        String line;
        while ((line = file.readLine()) != null) {
            String[] lines = line.split(", ");
            if (lines.length == 4) {
                moduleSet.add(new Module(lines[0], lines[1], Integer.parseInt(lines[2]), Integer.parseInt(lines[3])));
            }
        }
        return moduleSet;
    }

    /**
     * Read in students from a file, and store them in a set.
     * The file should be in the format of "firstName lastName"
     *
     * @param path the path of the file
     *             and file should be in the format of "firstName lastName"
     *             For example: "James Thomas"
     * @return a set of students
     */
    //example: James Thomas
    public Set<Name> readInStudents(String path) throws IOException {
        BufferedReader file = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path))));
        String line;
        while ((line = file.readLine()) != null) {
            String[] lines = line.split(" ");
            if (lines.length == 2) {
                studentSet.add(new Name(lines[0], lines[1]));
            }
        }
        return studentSet;
    }


    /**
     * noOfStaff method returns the number of staff of a certain type.
     *
     * @param type the type of staff
     *             For example: "Lecturer" or "Researcher"
     * @return the number of staff of a certain type
     * @throws IllegalArgumentException if the type is not "Lecturer" or "Researcher"
     */
    public int noOfStaff(String type) {
        int sum = 0;
        for (Staff staff : staffMap.values()) {
            if (type.equals(staff.getStaffType()))
                sum++;
        }
        return sum;
    }


    /**
     * addData method adds data to a staff.
     * If the staff is a lecturer, it adds modules to the lecturer.
     * If the staff is a researcher, it adds students to the researcher.
     *
     * @param id       the id of the staff
     *                 If the staff is not in the staff map, it returns false.
     * @param modules  the modules to be added to the lecturer
     *                 If the module is not in the module set, it returns false.
     * @param students the students to be added to the researcher
     *                 If the student is not in the student set, it returns false.
     * @return true if the data is added successfully, false otherwise
     * @throws IllegalArgumentException if the staff is not a lecturer or a researcher
     */
    public boolean addData(StaffID id, Set<Module> modules, Set<Name> students) {
        Staff staff = staffMap.get(id.getID());
        if (staff == null)
            return false;
        if (staff.getStaffType().equals(Constants.Lecturer)) {
            Lecturer lecturer = (Lecturer) staff;
            //check if the module is in the module set
            if (!moduleSet.containsAll(modules))
                return false;
            for (Module module : modules) {
                lecturer.addModule(module);
            }
        } else {
            Researcher researcher = (Researcher) staff;
            if (!studentSet.containsAll(students))
                return false;
            for (Name student : students) {
                if (studentSet.contains(student))
                    researcher.addStudent(student);
            }
        }
        return true;
    }

    /**
     * employStaff method employs a staff.
     * It creates a smart card number, a smart card, and a staff id.
     * It also adds the staff to the staff map.
     * If the staff is a lecturer, it creates a lecturer else it creates a researcher.
     *
     * @param firstName        the first name of the staff
     * @param lastName         the last name of the staff
     * @param dob              the date of birth of the staff
     * @param staffType        the type of the staff, "Lecturer" or "Researcher"
     * @param employmentStatus the employment status of the staff, "Permanent" or "Temporary"
     * @return the staff if the staff is employed successfully, null otherwise
     */
    public Staff employStaff(String firstName, String lastName, Date dob, String staffType, String employmentStatus) {
        Staff newStaff;
        if (!staffType.equals(Constants.Lecturer) && !staffType.equals(Constants.Researcher))
            return null;

        Name name = new Name(firstName, lastName);
        SmartCardNumber smartCardNumber = createSmartCardNumber(name);//create a smart card number
        SmartCard smartCard = IssuedSmartCard(name, dob, employmentStatus, smartCardNumber);//create a smart card

        //whether the smart card is issued
        if (smartCard == null)
            return null;

        StaffID staffID = StaffID.generateStaffID(this.staffMap);
        if (staffID == null)
            return null;

        if (staffType.equals(Constants.Lecturer))
            newStaff = new Lecturer(staffID, smartCard);
        else
            newStaff = new Researcher(staffID, smartCard);


        staffMap.put(staffID.getID(), newStaff);
        //build info and store to allStaffInfo

        return newStaff;
    }

    /**
     * getAllStaff method returns all the staff.
     *
     * @return all the staff
     */
    public Collection<Staff> getAllStaff() {
        return staffMap.values();
    }

    /**
     * terminateStaff method terminates a staff.
     * If the staff is a lecturer, all the modules that the lecturer teaches will be removed.
     * If the staff is a researcher, all the students that the researcher supervises will be removed.
     *
     * @param id the id of the staff
     */
    public void terminateStaff(StaffID id) {
        //get staff
        Staff staff = staffMap.get(id.getID());
        if (staff == null)
            return;
        //get smart card number
        SmartCardNumber smartCardNumber = staff.getSmartCard().getSmartCardNumber();
        staffMap.remove(id.getID());
        smartCardNumbers.remove(smartCardNumber.toString());
    }

    /**
     * Create a smart card number for a staff.
     * The smart card number is a 6-digit number, which is unique in the university.
     * The first 2 digits are the first letter of the first name and the first letter of the last name.
     * The next 3 digits are randomly generated.
     * The last digit is the current year.
     *
     * @param name the name of the staff
     * @return the smart card number
     */
    private SmartCardNumber createSmartCardNumber(Name name) {
        //get now year
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String firstPart = name.getFirstName().substring(0, 1).toUpperCase() + name.getLastName().substring(0, 1).toUpperCase();
        String thirdPart = String.valueOf(year);
        while (true) {
            int second = (int) (Math.random() * 1000);
            String secondPart = String.valueOf((int) (Math.random() * 1000));
            String smartCardNumber = firstPart + "-" + secondPart + "-" + thirdPart;
            if (!smartCardNumbers.contains(smartCardNumber)) {
                smartCardNumbers.add(smartCardNumber);
                return new SmartCardNumber(firstPart, second, year);
            }
        }
    }

    /**
     * Issue a smart card for a staff.
     * A staff must be at least 22 years old and at most 67 (retirement age is 68).
     * A staff must not be a replicate staff.
     *
     * @param name             the name of the staff
     * @param dob              the date of birth of the staff
     * @param employmentStatus the employment status of the staff, "Permanent" or "Temporary"
     * @param smartCardNumber  the smart card number of the staff
     * @return the smart card if the smart card is issued successfully, null otherwise
     */
    private SmartCard IssuedSmartCard(Name name, Date dob, String employmentStatus, SmartCardNumber smartCardNumber) {
        //check for dob ,A staff must be at least 22 years old and at most 67 (retirement age is 68)
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dob);
        int year = calendar.get(Calendar.YEAR);
        int nowYear = Calendar.getInstance().get(Calendar.YEAR);
        if (nowYear - year < 22 || nowYear - year > 67)
            return null;
        int hashCode = name.hashCode();
        //check for replicate staff
        for (Staff staff : staffMap.values()) {
            if (staff.getSmartCard().getName().hashCode() == hashCode && staff.getSmartCard().getStaffBirth().equals(dob))
                return null;
        }
        //build  SmartCard
        return SmartCard.IssueSmartCard(name, dob, employmentStatus, smartCardNumber);
    }

}
