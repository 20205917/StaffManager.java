import java.util.HashSet;
import java.util.Set;

/**
 * Lecturer class extends UniversityStaff class
 * Lecturer can teach modules and have a set of modules , and the number of credits they teach
 *
 * @author nil
 * @version 1.0
 */
public class Lecturer extends UniversityStaff {
    private final HashSet<Module> moduleSet;
    int credits = 0;

    public Lecturer(StaffID staffID, SmartCard smartCard) {
        super(staffID, smartCard, Constants.Lecturer);
        this.moduleSet = new HashSet<>();
    }

    /**
     * add module to lecturer's module set,
     * the number of credits will be added to the total credits
     *
     * @param module the module to be added
     *               the module must not be null
     */
    public void addModule(Module module) {
        if (module == null) {
            return;
        }
        credits += module.getCredit();
        moduleSet.add(module);
    }

    /**
     * delete module from lecturer's module set,
     * the number of credits will be deducted from the total credits
     *
     * @param module the module to be deleted
     *               the module must not be null
     */
    public void deleteStudent(Module module) {
        if (module == null) {
            return;
        }
        credits -= module.getCredit();
        moduleSet.remove(module);
    }

    /**
     * delete all modules from lecturer's module set,
     * the number of credits will be set to 0
     */
    public void deleteAll() {
        moduleSet.clear();
        credits = 0;
    }

    /**
     * check if the lecturer has enough credits to teach
     *
     * @return true if the lecturer has enough credits to teach
     * false if the lecturer does not have enough credits to teach
     */
    public boolean isEnough() {
        return credits >= 40;
    }

    /**
     * list all modules lecturer teach
     */
    // list all modules lecturer teach
    public void listModules() {
        for (Module module : moduleSet) {
            System.out.println(module);
        }
    }

}
