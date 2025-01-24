package entities.compkey;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class CompositeStudentKey {
    private String rollNo;
    private String department;

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeStudentKey that = (CompositeStudentKey) o;
        return Objects.equals(rollNo, that.rollNo) && Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo, department);
    }
}
