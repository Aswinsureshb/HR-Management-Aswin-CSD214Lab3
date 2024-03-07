package com.example.hr_management_aswins;

public class Employee {
    private int empid;
    private String empname;
    private String empemail;
    private String empaddress;
    private int empsalary;

    public Employee(int empid, String empname, String empemail, String empaddress, int empsalary) {
        this.empid = empid;
        this.empname = empname;
        this.empemail = empemail;
        this.empaddress = empaddress;
        this.empsalary = empsalary;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmpemail() {
        return empemail;
    }

    public void setEmpemail(String empemail) {
        this.empemail = empemail;
    }

    public String getEmpaddress() {
        return empaddress;
    }

    public void setEmpaddress(String empaddress) {
        this.empaddress = empaddress;
    }

    public int getEmpsalary() {
        return empsalary;
    }

    public void setEmpsalary(int empsalary) {
        this.empsalary = empsalary;
    }
}
