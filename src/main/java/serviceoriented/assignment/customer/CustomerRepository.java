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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The <code>CustomerRepository</code> is data access object that manages
 * persistence of CustomerEntity 
 * It provides functionality to create and retrieve, update and delete
 * CustomerEntity from database.
 * @author Deepish Sharma
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
