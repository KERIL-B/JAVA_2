package com.example.PhoneBook.Service;

import com.example.PhoneBook.Model.Company;
import com.example.PhoneBook.Model.Converter.CompanyConvert;
import com.example.PhoneBook.Model.DTO.CompanyDTO;
import com.example.PhoneBook.Model.DTO.EmployeeDTO;
import com.example.PhoneBook.Model.Employee;
import com.example.PhoneBook.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository)
    {this.companyRepository=companyRepository;}

    public List<CompanyDTO> getAllCompniesDTO()
    {
       Iterable<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companyDTOS=new ArrayList<>();

        for (Company company: companies
             ) {
            companyDTOS.add(CompanyConvert.ToDTO(company));
        }
        return companyDTOS;
    }

    public void AddCompany(CompanyDTO companyDTO)
    {
        Company company=CompanyConvert.ToEntity(companyDTO);

        for (Employee employee:company.getEmployees()
             ) {
            employee.setCompany(company);
        }
        companyRepository.save(company);
    }

    public boolean Exist(String name)
    {
        List<CompanyDTO> list=getAllCompniesDTO();
       boolean rez=false;
        for (CompanyDTO company:list
             ) {
            if(company.getName().equals(name))
                rez=true;
        }
        return rez;
    }

    public boolean DeleteCompany(String name)
    {
        boolean rez=false;
        for (Company company:companyRepository.findAll()
             ) {
            if (company.getName().equals(name)) {
                companyRepository.delete(company);
                rez=true;
                break;
            }

        }
        return rez;
    }

    public CompanyDTO get(String name)
    {
        for (CompanyDTO company:getAllCompniesDTO()
             ) {
            if (company.getName().equals(name))
                return company;
        }
        return null;
    }

    public void EditName(CompanyDTO companyDTO, String name)
    {
        String oldName=companyDTO.getName();
        companyDTO.setName(name);
        DeleteCompany(oldName);
        AddCompany(companyDTO);
    }

    public void EditPhone(CompanyDTO companyDTO, String phone)
    {
        String oldPhone=companyDTO.getName();
        companyDTO.setPhone(phone);
        DeleteCompany(oldPhone);
        AddCompany(companyDTO);
    }

    public void EditEmployees(CompanyDTO companyDTO, ArrayList<EmployeeDTO> stuff)
    {
        String oldStuff=companyDTO.getName();
        companyDTO.setEmployees(stuff);
        DeleteCompany(oldStuff);
        AddCompany(companyDTO);
    }

    public  void AddEmployees(CompanyDTO companyDTO, ArrayList<EmployeeDTO> employeeDTOS)
    {
        String oldStuff=companyDTO.getName();
        if (companyDTO.getEmployees()==null)
        {
            companyDTO.setEmployees(employeeDTOS);
        }
        else companyDTO.getEmployees().addAll(employeeDTOS);
        DeleteCompany(oldStuff);
        AddCompany(companyDTO);
    }

    public  void DeleteEmployee(CompanyDTO companyDTO, String name)
    {
        String oldStuff=companyDTO.getName();

        if (companyDTO.DeleteEmployee(name)) {
            DeleteCompany(oldStuff);
            AddCompany(companyDTO);
            System.out.println("Deleted");
        }
        else System.out.println("Unknown employee");


    }

}
