package com.example.PhoneBook.Model.DTO;

import com.example.PhoneBook.Model.Employee;

import java.util.ArrayList;

public class CompanyDTO {

    private String name;
    private String phone;
    private ArrayList<EmployeeDTO> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<EmployeeDTO> employees) {
        this.employees = employees;
    }

    @Override
    public String toString()
    {
        String size;
        if(employees==null)
            size="0";
        else size=employees.size()+"";

                return "Company: "+name+"; Phone: "+ phone +"; Stuff: "+size;
    }

    public CompanyDTO(String name, String phone, ArrayList<EmployeeDTO> employees)
    {
        this.name=name;
        this.phone = phone;
        this.employees=employees;
    }

    public boolean DeleteEmployee(String name)
    {
        boolean rez=false;
        EmployeeDTO falseEmployee=null;

        for (EmployeeDTO employee:employees
             ) {
            if (employee.getName().equals(name)) {
                falseEmployee=employee;
                rez=true;
            }
        }
        if (rez)
         {
            employees.remove(falseEmployee);
         }
        return rez;
    }
}
