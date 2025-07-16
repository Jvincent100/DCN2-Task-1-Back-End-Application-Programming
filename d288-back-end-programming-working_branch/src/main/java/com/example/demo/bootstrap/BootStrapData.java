package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //array of customers Firstname, lastname, phone#, address, zipcode, division id
        String[][] customersArray = {
                {"T'Challa", "Udaku", "(555)1234567", "101 Wakanda Ave", "10001", "2"},
                {"Storm", "Monroe", "(555)7654321", "202 X-Mansion Dr", "10002", "3"},
                {"Sam", "Wilson", "(555)2345678", "303 Falcon Blvd", "10003", "4"},
                {"Monica", "Rambeau", "(555)3456789", "404 Spectrum Ln", "10004", "5"},
                {"Riri", "Williams", "(555)4567890", "505 Ironheart St", "10005", "6"}
        };

        //loop 5 times to add the 5 customers
        for (int i = 0; i < 5; i++) {
            //first check if the Users have already been added
            if (customerRepository.count() > 5) {
                //exit loop and don't add customers
                break;
            }

            //create customer and fill data, grabbing data from customer array
            Customer newCustomer = new Customer();
            newCustomer.setFirstName(customersArray[i][0]);
            newCustomer.setLastName(customersArray[i][1]);
            newCustomer.setPhone(customersArray[i][2]);
            newCustomer.setAddress(customersArray[i][3]);
            newCustomer.setPostal_code(customersArray[i][4]);

            //sets the division for the customer
            newCustomer.setDivision(divisionRepository.findById(Long.valueOf(customersArray[i][5])).get());

            //save customer to database
            customerRepository.save(newCustomer);
        }
    }
}
