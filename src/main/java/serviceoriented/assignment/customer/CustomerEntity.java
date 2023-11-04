/*
 * CustomerEntity.java -- Defines CustomerEntity class 
 * This code is implemented as part of assignment given  
 * course Service Oriented Computing of MTECH Program Software Engineering
 * Student Name : Deepish sharma
 * Student Id   : 2022MT93012
 * Course       : Scalable Services
 * Program      : MTECH Software Engineering
 * Student Email: 2022MT93012@wilp.bits-pilani.ac.in
 */
package serviceoriented.assignment.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 * Define <code>CustomerEntity</code> It is persistent entity class managed by
 * spring data and JPA.
 * @author Deepish sharma
 */
@Entity(name = "Customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private Long phone;
    private String address;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;
    
    /**
     * Default constructor
     */
    public CustomerEntity() {
    }
   
    /**
     * Parameterized constructor 1
     * @param firstName      : Customer first name as string
     * @param lastName       : Customer last name as string
     * @param email          : Customer email as string
     * @param phone          : Customer phone as long
     * @param address        : Customer address as string
     */    
    public CustomerEntity(String firstName, String lastName, 
        String email, Long phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    /**
     * Parameterized constructor 2
     * @param id             : Customer id as long
     * @param firstName      : Customer first name as string
     * @param lastName       : Customer last name as string
     * @param email          : Customer email as string
     * @param phone          : Customer phone as long
     * @param address        : Customer address as string
     */    
    public CustomerEntity(Long id, String firstName, String lastName, 
        String email, Long phone, String address) {
        this(firstName, lastName, email, phone, address);
        this.id = id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the updatedDate
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * @param updatedDate the updatedDate to set
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    /**
     * Invoked on creation of new instance before persisting in database
     */
    @PrePersist
    protected void onCreate() {
        setCreatedDate(new Date());
        setUpdatedDate(getCreatedDate());
    }

    /**
     * Invoked on updating existing instance before persisting in database
     */
    @PreUpdate
    protected void onUpdate() {
        setUpdatedDate(new Date());
    }
}
