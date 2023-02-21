import java.util.HashSet;
import java.util.Set;

public class Lecturer extends UniversityStaff {
    HashSet<Module> moduleSet = new HashSet<>();
    int credits = 0;

    public Lecturer(StaffID staffID, SmartCard smartCard) {
        super(staffID, smartCard, Constants.Lecturer);
    }

    public void addModule(Module module) {
        credits += module.getCredit();
        moduleSet.add(module);
    }

    public void deleteStudent(Module module) {
        credits -= module.getCredit();
        moduleSet.remove(module);
    }

    public void deleteAll() {
        moduleSet.clear();
        credits = 0;
    }

    // check if lecturer teach enough credits
    public boolean isEnough() {
        return credits >= 40;
    }

    // list all modules lecturer teach
    public void listModules() {
        for (Module module : moduleSet) {
            System.out.println(module);
        }
    }

}
