package com.excelfile.repository;

import org.springframework.data.repository.CrudRepository;

import com.excelfile.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
}