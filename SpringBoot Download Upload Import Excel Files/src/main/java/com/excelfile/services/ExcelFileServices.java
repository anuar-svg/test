package com.excelfile.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.excelfile.model.Customer;
import com.excelfile.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excelfile.utils.ExcelUtils;

@Service
public class ExcelFileServices {

	@Autowired
	CustomerRepository customerRepository;

	public void store(MultipartFile file) {
		try {
			List<Customer> lstCustomers = ExcelUtils.parseExcelFile(file.getInputStream());
			customerRepository.saveAll(lstCustomers);
		} catch (IOException e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}

	public ByteArrayInputStream loadFile() {
		List<Customer> customers = (List<Customer>) customerRepository.findAll();

		try {
			ByteArrayInputStream in = ExcelUtils.customersToExcel(customers);
			return in;
		} catch (IOException e) {}

		return null;
	}
}
