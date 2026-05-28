package com.rahulscripts.learningrestfullapis.controllers;

import com.rahulscripts.learningrestfullapis.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @GetMapping
    public String mySpecialSuperSecretMessage(){
        return "superSecretMessage = jedsjnfs@12413389ensd323";
    }

//    @GetMapping(path = "{employeeId}")
//    public String getEmployee(@PathVariable(name = "employeeId") Integer id){
//        return "employee age "+id;
//    }

    @GetMapping(path = "/getEmployeeData")
    public String getEmployee(@RequestParam Integer age,
                              @RequestParam(required = false) String sortBy
    ){
        return "employee age "+age+" "+sortBy;
    }

    @PutMapping
    public String updateEmployeeById(){
        return "put method called";
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        inputEmployee.setDateOfJoining(LocalDate.of(2026,3,9));
        return inputEmployee;
    }

}
