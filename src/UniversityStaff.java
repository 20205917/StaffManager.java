public class UniversityStaff implements Staff {
    private StaffID staffID;
    private SmartCard smartCard;

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    private String staffType;

    public void setStaffID(StaffID staffID) {
        this.staffID = staffID;
    }

    public void setSmartCard(SmartCard smartCard) {
        this.smartCard = smartCard;
    }

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
