package com.rahulscripts.learningrestfullapis.controllers;

import com.rahulscripts.learningrestfullapis.dto.EmployeeDTO;
import com.rahulscripts.learningrestfullapis.entities.EmployeeEntity;
import com.rahulscripts.learningrestfullapis.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    EmployeeRepository employeeRepository;

    EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

//    @GetMapping
//    public String mySpecialSuperSecretMessage(){
//        return "superSecretMessage = jedsjnfs@12413389ensd323";
//    }

//    @GetMapping(path = "{employeeId}")
//    public String getEmployee(@PathVariable(name = "employeeId") Integer id){
//        return "employee age "+id;
//    }

//    @GetMapping(path = "/getEmployeeData")
//    public String getEmployee(@RequestParam Integer age,
//                              @RequestParam(required = false) String sortBy
//    ){
//        return "employee age "+age+" "+sortBy;
//    }
//
//    @PutMapping
//    public String updateEmployeeById(){
//        return "put method called";
//    }
//
//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        inputEmployee.setDateOfJoining(LocalDate.of(2026,3,9));
//        return inputEmployee;
//    }
        //we are breaching the mvc pattern but to understand need of service layer i'm doing this
        @GetMapping(path = "{employeeId}")
        public EmployeeEntity getEmployee(@PathVariable(name = "employeeId") Long id){
            return employeeRepository.findById(id).orElse(null);
        }

        @GetMapping
        public List<EmployeeEntity> getAllEmployees(){
            return employeeRepository.findAll();
        }

        @PostMapping
        public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity){//RequestBody annotation tells spring Read the HTTP request body, convert JSON into Java object using Jackson, and put it inside employeeEntity
            return employeeRepository.save(employeeEntity);
        }
}
