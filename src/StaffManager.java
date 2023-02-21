import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class StaffManager {

    private static StaffManager instance;

    private StaffManager() {
        this.staffMap = new HashMap<>();
        this.smartCardNumbers = new HashSet<>();
        this.moduleSet = new HashSet<>();
        this.studentSet = new HashSet<>();
    }

    //singleton
    public static StaffManager getInstance() {
        if (instance == null) {
            instance = new StaffManager();
        }
        return instance;
    }

    //store all the staff ,key staffID ,value staff
    private HashMap<String, Staff> staffMap = new HashMap<>();

    //store all the staff information, for check duplicate staff

    //store all the smart card numbers
    private Set<String> smartCardNumbers = new HashSet<>();


    private Set<Module> moduleSet = new HashSet<>();

    private Set<Name> studentSet = new HashSet<>();


    //example: CSC8014, Software Development Advanced Techniques, 2, 10
    public Set<Module> readInModules(String path) throws IOException {
        BufferedReader file = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path))));
        String line;
        if ((line = file.readLine()) != null) {
            String[] lines = line.split(",");
            if (lines.length == 4) {
                moduleSet.add(new Module(lines[0], lines[1], Integer.parseInt(lines[2]), Integer.parseInt(lines[3])));
            }
        }
        return moduleSet;
    }

    //example: James Thomas
    public Set<Name> readInStudents(String path) throws IOException {
        BufferedReader file = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path))));
        String line;
        if ((line = file.readLine()) != null) {
            String[] lines = line.split(" ");
            if (lines.length == 2) {
                studentSet.add(new Name(lines[0], lines[1]));
            }
        }
        return studentSet;
    }


    public int noOfStaff(String type) {
        int sum = 0;
        for (Staff staff : staffMap.values()) {
            if (type.equals(staff.getStaffType()))
                sum++;
        }
        return sum;
    }

    public boolean addData(StaffID id, Set<Module> modules, Set<Name> students) {
        Staff staff = staffMap.get(id.getID());
        if (staff == null)
            return false;
        if (staff.getStaffType().equals(Constants.Lecturer)) {
            Lecturer lecturer = (Lecturer) staff;
            //check if the module is in the module set
            for (Module module : modules) {
                if (moduleSet.contains(module))
                    lecturer.addModule(module);
            }
        } else {
            Researcher researcher = (Researcher) staff;
            //check if the student is in the student set
            for (Name student : students) {
                if (studentSet.contains(student))
                    researcher.addStudent(student);
            }
        }
        return true;
    }


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

        StaffID staffID = getStaffID();
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


    public Collection<Staff> getAllStaff() {
        return staffMap.values();
    }


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


    private StaffID getStaffID() {
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
        return new SmartCard(name, dob, employmentStatus, smartCardNumber);
    }

}
