import java.util.Calendar;
import java.util.Date;

/**
 * SmartCard class, which is used to represent a smart card for a staff member.
 * It contains the following information:
 * - Name   the name of the staff member
 * - SmartCardNumber  the number of the smart card
 * - StaffBirth       the birthday of the staff member
 * - IssueDate    the date the smart card was issued
 * - ExpiryDate   the date the smart card expires
 * - EmploymentStatus   the employment status of the staff member
 * <p>
 * The smart card is issued for a period of 2 years for temporary staff members and 10 years for permanent staff members.
 * The smart card is issued on the day it is created.
 * The smart card is issued with a unique smart card number.
 *
 * @author nil
 * @version 1.0
 */
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

    /**
     * set the expiry date of the smart card
     * if the employment status is permanent, the expiry date is 10 years after the issue date
     * if the employment status is temporary, the expiry date is 2 years after the issue date
     */
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

    /**
     * issue a smart card for a staff member
     *
     * @param name the name of the staff member
     *             date of birth of the staff member
     *             employment status of the staff member
     *             smart card number of the staff member
     * @return a smart card for the staff member
     */
    public static SmartCard IssueSmartCard(Name name, Date staffBirth, String employmentStatus, SmartCardNumber smartCardNumber) {
        SmartCard smartCard = new SmartCard(name, staffBirth, employmentStatus, smartCardNumber);
        smartCard.setExpiryDate();
        return smartCard;
    }
}
