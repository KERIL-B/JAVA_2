package com.example.PhoneBook.Model.DTO;

public class EmployeeDTO {

    private String name;
    private String phone1;
    private String phone2;
    private String phone3;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getPhone3() {
        return phone3;
    }

    @Override
    public String toString() {
        return "    Name: "+name+"; Phones: 1. "+phone1+" 2. "+phone2+" 3. "+phone3;
    }

    public EmployeeDTO(String name, String phone1, String phone2, String phone3)
    {
        this.name=name;
        this.phone1=phone1;
        this.phone2=phone2;
        this.phone3=phone3;
    }
}
