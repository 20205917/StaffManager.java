public class Test {
    public static void main(String[] args) {
        StaffManager staffManager = new StaffManager();
        staffManager.readInModules("modules.TXT");
        staffManager.readInStudents("students.TXT");

        staffManager.employStaff()
     }
}
