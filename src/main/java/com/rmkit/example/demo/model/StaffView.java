package com.rmkit.example.demo.model;

public class StaffView {
    private final String fullName;
    private final String designation;
    private final String department;
    private final String email;
    private final String image;

    public StaffView(String fullName, String designation, String department, String email, String image) {
        this.fullName = fullName;
        this.designation = designation;
        this.department = department;
        this.email = email;
        this.image = image;
    }

    public String getFullName() { return fullName; }
    public String getDesignation() { return designation; }
    public String getDepartment() { return department; }
    public String getEmail() { return email; }
    public String getImage() { return image; }
}