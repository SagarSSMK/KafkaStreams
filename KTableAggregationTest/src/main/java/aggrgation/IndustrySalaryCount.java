package aggrgation;

import model.Employee;

public class IndustrySalaryCount {

    String company;
    long salaaryCount;
    long count=0;
    public IndustrySalaryCount(){}

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public long getSalaaryCount() {
        return salaaryCount;
    }

    public void setSalaaryCount(int salaaryCount) {
        this.salaaryCount = salaaryCount;
    }

    public IndustrySalaryCount add(Employee employee){
        this.company=employee.getCompany();
        this.salaaryCount=count+employee.getSalary();
        return this;
    }
}
