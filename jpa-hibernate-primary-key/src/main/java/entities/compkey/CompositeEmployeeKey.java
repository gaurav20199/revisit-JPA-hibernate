package entities.compkey;

import java.io.Serializable;
import java.util.Objects;

public class CompositeEmployeeKey implements Serializable {

    private String empCode;
    private String department;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
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
        CompositeEmployeeKey that = (CompositeEmployeeKey) o;
        return Objects.equals(empCode, that.empCode) && Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empCode, department);
    }
}
