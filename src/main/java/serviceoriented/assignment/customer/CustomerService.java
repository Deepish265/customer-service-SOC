/*
 * CustomerService.java -- Defines CustomerService class 
 * This code is implemented as part of assignment given  
 * course Service Oriented Computing of MTECH Program Software Engineering
 * Student Name : Deepish sharma
 * Student Id   : 2022MT93012
 * Course       : Scalable Services
 * Program      : MTECH Software Engineering
 * Student Email: 2022MT93012@wilp.bits-pilani.ac.in
 */
package serviceoriented.assignment.customer;


import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The <code>CustomerService</code> is microservice that provides REST/JSON 
 * interface to manage Customers. 
 * It provides functionality to create, retrieve, update and delete customers
 * @author Deepish Sharma
 */
@Slf4j
@RestController
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    /**
     * Retrieve Customer with given id
     * @param id             : Customer id of type Long
     * @return               : Customer of type CustomerEntity  
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
        log.debug("In getCustomer()... id = " + id);
        Optional<CustomerEntity> optionalEntity = customerRepository.findById(id);
        if (optionalEntity.isPresent()) {
            return ResponseEntity.ok(optionalEntity.get());
        } else {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Customer with ID " + id + " not found");
        }
    }
    
    /**
     * Retrieves list of all Customers
     * @return              : list of type List<CustomerEntity>
     */
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerEntity>> listCustomers() {
        log.debug("In listCustomer()...");
        List<CustomerEntity> customerList = customerRepository.findAll();
        return ResponseEntity.ok(customerList);
    }
    
    /**
     * Creates new customer
     * @param entity         : entity of type CustomerEntity
     * @return               : customer of type CustomerEntity  
     */
    @Transactional
    @PostMapping("/customers")
    public ResponseEntity<CustomerEntity> saveCustomer(@RequestBody CustomerEntity entity) {
        log.debug("In saveCustomer()...");
        customerRepository.save(entity);
        return ResponseEntity.ok(entity);
    } 
    
    /**
     * Updates existing customer with specified id
     * @param id             : customer id of type Long
     * @param entity         : entity of type CustomerEntity
     * @return               : customer of type CustomerEntity  
     */
    @Transactional
    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, 
        @RequestBody CustomerEntity entity) {
        log.debug("In updateCustomer()... id = " + id);
        Optional<CustomerEntity> optionalEntity = customerRepository.findById(id);
        if (optionalEntity.isPresent()) {
            CustomerEntity databaseEntity = optionalEntity.get();
            databaseEntity.setFirstName(entity.getFirstName());
            databaseEntity.setLastName(entity.getLastName());
            databaseEntity.setEmail(entity.getEmail());
            databaseEntity.setPhone(entity.getPhone());
            databaseEntity.setAddress(entity.getAddress());
            return ResponseEntity.ok(optionalEntity.get());
        } else {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Customer with ID " + id + " not found");
        }
    }
    
    /**
     * Deletes existing customer with specified id
     * @param id             : customer id of type Long
     * @return               : customer id of type Long
     */
    @Transactional
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        log.debug("In deleteCustomer()... id = " + id);
        Optional<CustomerEntity> optionalEntity = customerRepository.findById(id);
        
        if (optionalEntity.isPresent()) {
            customerRepository.delete(optionalEntity.get());
            return ResponseEntity.status(HttpStatus.OK)
                .body("Customer with ID " + id + " deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Customer with ID " + id + " not found");
        }
    }  
    
    /**
     * Method added for testing purpose. Adds 4 customers to Customer table
     * @return 
     */
    @Transactional
    @PostMapping("/customers/setup")
    public ResponseEntity<String> setup() {
        customerRepository.save(new CustomerEntity("Amit", "Shah", 
            "amit.shah@gmail.com", 9860888111l, "Gujarat"));
        customerRepository.save(new CustomerEntity("Jayesh", "Shah", 
            "jayesh.shah@gmail.com", 9860888112l, "Nasik"));   
       customerRepository.save(new CustomerEntity("Rajesh", "Shah", 
            "rajesh.shah@gmail.com", 9860888113l, "Pune"));   
       customerRepository.save(new CustomerEntity("Roshan", "Shah", 
            "roshan.shah@gmail.com", 9860888114l, "Pune"));   
                
        return ResponseEntity.ok("Added 4 customers!");
    }
    
    /**
     * Method added for testing purpose. Adds 4 customers to Customer table
     * @return 
     */
    @Transactional
    @PostMapping("/customers/reset")
    public ResponseEntity<String> reset() {
        customerRepository.deleteAll();
        return ResponseEntity.ok("All customer records deleted!");
    }    
}
