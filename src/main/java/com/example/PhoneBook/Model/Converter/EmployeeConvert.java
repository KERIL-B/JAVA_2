package com.example.PhoneBook.Model.Converter;

import com.example.PhoneBook.Model.DTO.EmployeeDTO;
import com.example.PhoneBook.Model.Employee;

public class EmployeeConvert {

    public static Employee ToEntity(EmployeeDTO employeeDTO)
    {
        Employee empl=new Employee();
        empl.setName(employeeDTO.getName());
        empl.setPhone1(employeeDTO.getPhone1());
        empl.setPhone2(employeeDTO.getPhone2());
        empl.setPhone3(employeeDTO.getPhone3());
        return empl;
    }

    public static EmployeeDTO ToDTO(Employee employee)
    {
        return new EmployeeDTO(employee.getName(),employee.getPhone1(),employee.getPhone2(),employee.getPhone3());
    }
}
