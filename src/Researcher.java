import java.util.HashSet;
import java.util.Set;

public class Researcher extends UniversityStaff {

    HashSet<Name> students = new HashSet<>();

    public Researcher(StaffID staffID, SmartCard smartCard) {
        super(staffID, smartCard, Constants.Researcher);
    }

    public void addStudent(Name name) {
        students.add(name);
    }

    public void deleteStudent(Name name) {
        students.remove(name);
    }

    public void deleteAll() {
        students.clear();
    }

    // check if researcher supervise enough students
    public boolean isEnough() {
        return students.size() >= 10;
    }

    // list all students researcher supervise
    public void listStudents() {
        for (Name name : students) {
            System.out.println(name);
        }
    }
}