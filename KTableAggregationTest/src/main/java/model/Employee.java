package model;

import org.apache.kafka.common.protocol.types.Field;

public class Employee {

    String id;
    String name;
    String company;
    long salary;

    public Employee(){}
    public Employee(Builder builder) {
        this.id=builder.id;
        this.name=builder.name;
        this.company=builder.company;
        this.salary=builder.salary;
    }

    public static Builder newBuilder()
    {return new Employee.Builder();}

    public static Builder builder(Employee data){
      Builder builder= new Builder();
      builder.id=data.getId();
      builder.company= data.company;
      builder.name=data.getName();
      builder.salary=data.getSalary();
      return builder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                '}';
    }
    public  static final class Builder{

        String id;
        String name;
        String company;
        long salary;


        public Builder withId(String val){
            id=val;
            return this;
        }

        public Builder withName(String val){
            name=val;
            return this;
        }

        public Builder withCompany(String val){
            company=val;
            return this;
        }

        public Builder withSalary(long val){
            salary=val;
            return this;
        }

        public Employee build(){
            return new Employee(this);
        }

        public Employee build(String row){
            if(null!=row){
                String[] cols = row.split(",");
                this.id=cols[0];
                this.name=cols[1];
                this.salary=Long.valueOf(cols[2]);
                this.company=cols[3];
                return new Employee(this);
            }
            return null;
        }
    }
}
