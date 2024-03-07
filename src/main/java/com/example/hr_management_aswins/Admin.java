package com.example.hr_management_aswins;

public class Admin {
private int adminid;
private String username;
private String email;
private int salary;

    public Admin(int adminid, String username, String email, int salary) {
        this.adminid = adminid;
        this.username = username;
        this.email = email;
        this.salary = salary;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
