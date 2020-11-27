package com.example.firstproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class FirstApi {
    @Autowired
    CustRepo repo;
    @RequestMapping("/hello")
    @ResponseBody
    public List<Customer> hello(){
            System.out.println(repo.findAll());
            return repo.findAll();
    }
    @RequestMapping("/save1")
    @ResponseBody
    public Object saveCust(@RequestBody Customer customer){
        if(!repo.existsById(customer.getId())){
            repo.save(customer);
            return customer;
        }else{
            return HttpStatus.ALREADY_REPORTED;
        }
    }
    @PostMapping("/save")
    @ResponseBody
    public Object cust(@RequestBody Customer customer){
        HashMap h = new HashMap<>();
        if(!repo.existsById(customer.getId())){
            repo.save(customer);
            h.put("status",HttpStatus.OK);
            h.put("data", customer);
            return h;
        }else{
            return HttpStatus.NOT_FOUND;
        }
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object cust1(@PathVariable("id") int id){
        HashMap h = new HashMap<>();
        if(repo.existsById(id)){
            repo.deleteById(id);
            h.put("status",200);
            h.put("data", repo.findById(id));
            return h;
        }else{
            return null ;
        }
    }
}
