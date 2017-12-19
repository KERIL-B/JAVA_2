package com.example.PhoneBook.Model;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EMPLOYEE_ID")
    private Long id;

    @Column(name = "EMPLOYEE_NAME")
    private String name;

    @Column(name="EMPLOEE_PHONE_1")
    private String phone1;

    @Column(name="EMPLOEE_PHONE_2")
    private String phone2;

    @Column(name="EMPLOEE_PHONE_3")
    private String phone3;

    @ManyToOne
    @JoinColumn(name="COMPANY_ID", nullable = false)
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id=id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
