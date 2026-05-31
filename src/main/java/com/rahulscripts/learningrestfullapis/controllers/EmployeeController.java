package com.rahulscripts.learningrestfullapis.controllers;

import com.rahulscripts.learningrestfullapis.dto.EmployeeDTO;
import com.rahulscripts.learningrestfullapis.entities.EmployeeEntity;
import com.rahulscripts.learningrestfullapis.repositories.EmployeeRepository;
import com.rahulscripts.learningrestfullapis.services.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    //EmployeeRepository employeeRepository;

    private EmployeeService employeeService;


//    EmployeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
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

//    //we are breaching the mvc pattern but to understand need of service layer i'm doing this
//        @GetMapping(path = "{employeeId}")
//        public EmployeeEntity getEmployee(@PathVariable(name = "employeeId") Long id){
//            return employeeRepository.findById(id).orElse(null);
//        }
//
//        @GetMapping
//        public List<EmployeeEntity> getAllEmployees(){
//            return employeeRepository.findAll();
//        }
//
//        @PostMapping
//        public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity){//RequestBody annotation tells spring Read the HTTP request body, convert JSON into Java object using Jackson, and put it inside employeeEntity
//            return employeeRepository.save(employeeEntity);
//        }


    @GetMapping(path = "{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name = "employeeId") Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        System.out.println("Controller: "+employeeDTO+" "+employeeDTO.toString());
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.createNewEmployee(employeeDTO));
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> updateExistingEmployee(@RequestParam Long id,@RequestBody @Valid EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.updateEmployee(id,employeeDTO));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteExistingEmployee(@RequestParam Long id){
        boolean isDeleted = employeeService.deleteEmployee(id);
        if (isDeleted)
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.notFound().build();
    }

    @PatchMapping
    public ResponseEntity<EmployeeDTO> updateEmployeeField(@RequestParam Long id,@RequestBody Map<String,Object> updates){
        EmployeeDTO employeeDTO = employeeService.updateEmployeeField(id,updates);
        if(employeeDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
