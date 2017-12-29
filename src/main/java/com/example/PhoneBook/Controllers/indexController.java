package com.example.PhoneBook.Controllers;

import com.example.PhoneBook.Model.DTO.CompanyDTO;
import com.example.PhoneBook.Model.DTO.EmployeeDTO;
import com.example.PhoneBook.Model.Forms.AddCompanyForm;
import com.example.PhoneBook.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class indexController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model) {
        model.addAttribute("companies", companyService.getAllCompniesDTO());
        return "index";
    }

    @RequestMapping(value = "/addCompany", method = RequestMethod.GET)
    public String getAddPage(Model model) {
        AddCompanyForm form = new AddCompanyForm();
        model.addAttribute("addForm", form);
        return "addCompany";
    }

    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public String saveCompany(Model model, @ModelAttribute("addForm") AddCompanyForm form) {
        try {
            ArrayList<EmployeeDTO> list = new ArrayList<>();
            CompanyDTO company = new CompanyDTO(form.getName(), form.getPhone(), list);
            companyService.AddCompany(company);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "addSubscriber";
        }
    }

    @RequestMapping(value = { "/companyProfile" }, method = RequestMethod.GET)
    public String getProfile(Model model, @ModelAttribute("name") String name) {
        try{
            CompanyDTO companyDTO =companyService.get(name);
            model.addAttribute("company", companyDTO);
            model.addAttribute("employees", companyDTO.getEmployees());
            return "companyPage";
        }
        catch (Exception e){
            return "redirect:/";
        }
    }
}