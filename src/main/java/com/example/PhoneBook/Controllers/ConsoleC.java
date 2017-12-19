package com.example.PhoneBook.Controllers;

import com.example.PhoneBook.Model.Company;
import com.example.PhoneBook.Model.Converter.CompanyConvert;
import com.example.PhoneBook.Model.Converter.EmployeeConvert;
import com.example.PhoneBook.Model.DTO.CompanyDTO;
import com.example.PhoneBook.Model.DTO.EmployeeDTO;
import com.example.PhoneBook.Model.Employee;
import com.example.PhoneBook.Repository.CompanyRepository;
import com.example.PhoneBook.Repository.EmployeeRepository;
import com.example.PhoneBook.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleC implements CommandLineRunner{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private CompanyService companyService;
    private Scanner scan=new Scanner(System.in);

    @Override
    public void run (String... args)
    {
        companyService=new CompanyService(companyRepository);
        String whatToDo="";
        System.out.println("\n==================================================================\n" +
                "Enter any command:\n" +
                "1. show_db - to show data base\n" +
                "2. add_company - to add company\n" +
                "3. del_company - to delete company\n" +
                "4. edit_company - to edit company\n" +
                "5. exit - to exit\n");

         do {
            whatToDo=scan.next();

            switch (whatToDo) {
                case "show_db":
                    ExeShowDb();
                    break;
                case "add_company":
                    ExeAddCompany();
                    break;
                case "del_company":
                    ExeDeleteCompany();
                    break;
                case "edit_company":
                    ExeEditCompany();
                    break;
                case "exit":
                    break;
                case "test": test();
                    break;

                default:
                    System.out.println("unknown command");
                    break;
            }
        }while (!whatToDo.equals("exit"));
    }

    private void ExeShowDb()
    {

       for (CompanyDTO company: companyService.getAllCompniesDTO()) {
            System.out.printf("%s",company.toString());

            if (company.getEmployees()!=null) {
                for (Object o : company.getEmployees()) {
                    EmployeeDTO employee = (EmployeeDTO) o;
                    if (employee != null) {
                        System.out.print("\n" + employee.toString());
                    }
                }
            }
            System.out.println();
        }
    }

    private void ExeAddCompany()
    {
        System.out.println("Enter information line in this format:\n" +
                "{company name} {company phone}\n");
        String companyName=scan.next();
        String companyPhone= scan.next();

        if(companyService.Exist(companyName))
            System.out.println("Data base already has it");
        else {
            CompanyDTO companyDTO=new CompanyDTO(companyName, companyPhone, ExeAddEmployees());
            companyService.AddCompany(companyDTO);
            System.out.println("Company added");
        }
    }

    private ArrayList<EmployeeDTO> ExeAddEmployees()
    {
        ArrayList<EmployeeDTO> employees=new ArrayList<>();
        System.out.println("To stop adding employees type \"end\"");
        System.out.println("Enter name and phones(up to 3) of the employee\n" +
            "Input format: {name} {phone} {phone} {phone}");

        scan.nextLine();
        String name=scan.next();
        while (!name.equals("end"))
        {
            String phones=scan.nextLine();
            try {

                phones = phones.substring(1, phones.length());
                String[] phonesArr = phones.split(" ");
                String[] nullArr = {null, null, null};
                for (int i = 0; i < 3; i++) {
                    try {
                        nullArr[i] = phonesArr[i];
                    } catch (Exception e) {
                    }
                }
                EmployeeDTO employee = new EmployeeDTO(name, nullArr[0], nullArr[1], nullArr[2]);
                employees.add(employee);
                System.out.println("Added\n");

                System.out.println("Enter name and phones(up to 3) of the employee\n" +
                        "Input format: {name} {phone} {phone} {phone}");
            }catch (Exception e)
            {System.out.println(e.getMessage());}
            name=scan.next();
        }
        return  employees;
    }

    private void ExeDeleteCompany()
    {
        System.out.println("Enter company name");
        String name=scan.next();
        if (companyService.Exist(name))
        {
            if (companyService.DeleteCompany(name))
                System.out.println("Deleted");
            else System.out.println("Cant delete");
        }else System.out.println("Unknown company");
    }

    private void ExeEditCompany() {
        System.out.println("Enter company name");
        String name = scan.next();
        CompanyDTO companyDTO = companyService.get(name);
        if (companyDTO != null)
        {
            ExeEditOptionsFor(companyDTO);
        }
        else System.out.println("Unknown company");
    }

    private void ExeEditOptionsFor(CompanyDTO companyDTO)
    {
        System.out.println("Choose what to edit\n" +
                "name - to edit company name\n" +
                "phone - to edit company phone\n" +
                "add_employees - to add several employees\n" +
                "del_employee - to delete one employee");
        String option = scan.next();
        switch (option)
        {
            case "name":
                System.out.println("Enter new name");
                String newName=scan.next();
                if (companyService.Exist(newName))
                    System.out.println("Choose another name. This company already exists");
                else
                    companyService.EditName(companyDTO, newName);
                break;
            case "phone":
                System.out.println("Enter new phone");
                companyService.EditPhone(companyDTO, scan.next());
                break;
            case "add_employees":
                System.out.println("Enter new stuff list");
                ArrayList<EmployeeDTO> employeeDTOS=ExeAddEmployees();
                companyService.EditEmployees(companyDTO, employeeDTOS);
                break;
            case "del_employee":
                System.out.println("Enter name of employee");
                companyService.DeleteEmployee(companyDTO,scan.next());
                break;
            default: System.out.println("Unknown option");
        }
    }

    private void test()
    {
        CompanyDTO company=new CompanyDTO("magnit","123", ExeAddEmployees());
        companyService.AddCompany(company);

        System.out.println("Delete name->");
        companyService.DeleteEmployee(company, scan.next());
    }

}
