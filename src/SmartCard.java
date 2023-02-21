import java.util.Calendar;
import java.util.Date;

public class SmartCard {
    private final Name name;
    private final SmartCardNumber smartCardNumber;

    private final Date staffBirth;
    private final Date issueData;
    private Date expiryDate;

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    private final String employmentStatus;

    public Name getName() {
        return name;
    }

    public SmartCardNumber getSmartCardNumber() {
        return smartCardNumber;
    }

    public Date getStaffBirth() {
        return staffBirth;
    }

    public Date getIssueData() {
        return issueData;
    }

    public SmartCard(Name name, Date staffBirth, String employmentStatus, SmartCardNumber smartCardNumber) {
        this.name = name;
        this.issueData = Calendar.getInstance().getTime();//issue date is today
        this.smartCardNumber = smartCardNumber;
        this.staffBirth = staffBirth;
        this.employmentStatus = employmentStatus;
    }

    private void setExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(issueData);
        if (employmentStatus.equals(Constants.Permanent)) {
            cal.add(Calendar.YEAR, 10);
        } else {
            cal.add(Calendar.YEAR, 2);
        }
        expiryDate = cal.getTime();
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public static SmartCard IssueSmartCard(Name name, Date staffBirth, String employmentStatus, SmartCardNumber smartCardNumber) {
        SmartCard smartCard = new SmartCard(name, staffBirth, employmentStatus, smartCardNumber);
        smartCard.setExpiryDate();
        return smartCard;
    }
}
