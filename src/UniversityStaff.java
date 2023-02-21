/**
 * UniversityStaff implements the Staff interface
 * contains the staffID, smartCard and staffType
 * is the super class of Lecturer and Researcher
 *
 * @author nil
 * @version 1.0
 */

public class UniversityStaff implements Staff {
    private final StaffID staffID;
    private final SmartCard smartCard;
    private final String staffType;

    UniversityStaff(StaffID staffID, SmartCard smartCard, String staffType) {
        this.staffID = staffID;
        this.smartCard = smartCard;
        this.staffType = staffType;
    }

    @Override
    public StaffID getStaffID() {
        return staffID;
    }

    @Override
    public SmartCard getSmartCard() {
        return smartCard;
    }

    @Override
    public String getStaffEmploymentStatus() {
        return smartCard.getEmploymentStatus();
    }

    @Override
    public String getStaffType() {
        return staffType;
    }
}
