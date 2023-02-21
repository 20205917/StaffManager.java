import java.util.Objects;

public class Module {
    private String name;
    private String code;
    private int semester;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    private int credit;
    public Module(String code,String name,int semester,int credit){
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Module:" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", semester=" + semester +
                ", credit=" + credit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, semester, credit);
    }
}
