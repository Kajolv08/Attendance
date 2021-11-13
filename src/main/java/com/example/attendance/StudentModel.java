package com.example.attendance;

public class StudentModel {
    private String Name, Std, Address, Email;
    private int Rollno, Phone;
    private boolean isActive;

    public StudentModel(int Rollno, String Name, String Std, String Address, int Phone, String Email, boolean isActive) {
        this.Name = Name;
        this.Std = Std;
        this.Rollno = Rollno;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
        this.isActive=isActive;

    }

    public StudentModel(int i, String std, String s, int parseInt, String toString, int anInt, String string) {
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "Name='" + Name + '\'' +
                ", Std='" + Std + '\'' +
                ", Address='" + Address + '\'' +
                ", Email='" + Email + '\'' +
                ", Rollno=" + Rollno +
                ", Phone=" + Phone +
                ", isActive=" + isActive +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setStd(String std) {
        Std = std;
    }

    public String getStd() {
        return Std;
    }

    public int getRollno() {
        return Rollno;
    }

    public void setRollno(int rollno) {
        Rollno = rollno;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}


