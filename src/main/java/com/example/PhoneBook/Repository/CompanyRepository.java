package com.example.PhoneBook.Repository;

import com.example.PhoneBook.Model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company,Long> {

}
