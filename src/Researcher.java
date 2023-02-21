import java.util.HashSet;
import java.util.Set;

/**
 * Researcher class, extends UniversityStaff class
 * researchers can supervise students’ projects , contains a set of students
 *
 * @author nil
 * @version 1.0
 * {@inheritDoc}
 */
public class Researcher extends UniversityStaff {

    private final HashSet<Name> students = new HashSet<>();

    public Researcher(StaffID staffID, SmartCard smartCard) {
        super(staffID, smartCard, Constants.Researcher);
    }

    /**
     * add student to researcher's student list
     *
     * @param name student's name
     * @throws IllegalArgumentException if name is null
     */
    public void addStudent(Name name) {
        if (name == null) {
            return;
        }
        students.add(name);
    }

    /**
     * delete student from researcher's student list
     *
     * @param name student's name
     * @throws IllegalArgumentException if name is null
     */
    public void deleteStudent(Name name) {
        if (name == null) {
            return;
        }
        students.remove(name);
    }

    /**
     * delete all students from researcher's student list
     */
    public void deleteAll() {
        students.clear();
    }

    /**
     * check if researcher has enough students
     *
     * @return true if researcher has enough students
     * false if researcher has not enough students
     */
    public boolean isEnough() {
        return students.size() >= 10;
    }

    /**
     * get researcher's student list , print all students
     */
    public void listStudents() {
        for (Name name : students) {
            System.out.println(name);
        }
    }
}