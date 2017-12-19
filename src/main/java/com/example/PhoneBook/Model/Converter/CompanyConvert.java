package com.example.PhoneBook.Model.Converter;

import com.example.PhoneBook.Model.Company;
import com.example.PhoneBook.Model.DTO.CompanyDTO;
import com.example.PhoneBook.Model.DTO.EmployeeDTO;
import com.example.PhoneBook.Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class CompanyConvert {

    public static Company ToEntity(CompanyDTO companyDTO)
    {
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setPhone(companyDTO.getPhone());

        if(companyDTO.getEmployees()!=null)
        {
            List<EmployeeDTO> employeeDTOS = companyDTO.getEmployees();
            List<Employee> employees = new ArrayList<>();
            for (EmployeeDTO employeeDTO : employeeDTOS
                    ) {
                employees.add(EmployeeConvert.ToEntity(employeeDTO));
            }
            company.setEmployees(employees);
        }
        else
        {
            company.setEmployees(null);
        }
        return company;
    }

    public static CompanyDTO ToDTO(Company company)
    {
        ArrayList<EmployeeDTO> employeeDTOS;
        if (company.getEmployees()!=null)
        {
            employeeDTOS = new ArrayList<EmployeeDTO>();
            List<Employee> employees=company.getEmployees();
            for (Employee employee:employees
                 ) {
                employeeDTOS.add(EmployeeConvert.ToDTO(employee));
            }
        }
        else
        {
            employeeDTOS=null;
        }

        return new CompanyDTO(company.getName(),company.getPhone(),employeeDTOS);
}
}
