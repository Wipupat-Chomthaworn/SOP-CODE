package com.example.week3;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
@RestController
public class CustomerController {
        private List<Customer> customers;// = new ArrayList<Customer> ();
        public CustomerController(){
            this.customers = new ArrayList();
            this.customers.add(new Customer("1010", "John", "Male", 25));
            this.customers.add(new Customer("1018", "Peter", "Male", 24));
            this.customers.add(new Customer("1019", "Sarah", "Female", 23));
            this.customers.add(new Customer("1110", "Rose", "Female", 23));
            this.customers.add(new Customer("1001", "Emma", "Female", 30));
            System.out.println(this.customers);
        }

        @RequestMapping(value = "/customers")
        public List<Customer> getCustomers(){
            return this.customers;
        }

        @RequestMapping(value = "/customerbyid/{ID}")
        public Customer getCustomersByID(@PathVariable String ID){
            for (int i = 0; i < this.customers.size(); i++) {
                if (this.customers.get(i).getID().equals(ID)) {
                    return this.customers.get(i);
                }
            }
            return null;
        }
        @RequestMapping(value = "/customerbyname/{n}")
        public Customer getCustomersByName(@PathVariable String n){
            for (int i = 0; i < this.customers.size(); i++) {
                if (this.customers.get(i).getName().equals(n)) {
                    return this.customers.get(i);
                }
            }
            return null;
        }
        @DeleteMapping(value = "/customerDelByid/{ID}")
        public boolean  delCustomerByID(@PathVariable String ID){
            System.out.println("in");
            for (int i = 0; i < this.customers.size(); i++) {
//            System.out.println("Cust "+i+ " : "+ this.customers.get(i).getID());
                if (this.customers.get(i).getID().equals(ID)) {
                    this.customers.remove(i);
                    return true;
                }

            }
            return false;
        }
        @DeleteMapping(value = "/customerDelByname/{n}")
        public boolean delCustomersByName(@PathVariable String n){
            for (int i = 0; i < this.customers.size(); i++) {
//            System.out.println(this.customers.get(i).getName());
                if (this.customers.get(i).getName().equals(n)) {
                    this.customers.remove(i);
                    return true;
                }
            }
            return false;
        }
        @RequestMapping(value = "/addCustomer")
        public boolean  addCustomer(@RequestParam String ID, @RequestParam String n, @RequestParam String s, @RequestParam int a){
            this.customers.add(new Customer(ID, n , s, a));
            return true;
        }


    }

