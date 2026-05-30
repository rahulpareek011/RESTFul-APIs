package com.rahulscripts.learningrestfullapis.services;

import com.rahulscripts.learningrestfullapis.dto.EmployeeDTO;
import com.rahulscripts.learningrestfullapis.entities.EmployeeEntity;
import com.rahulscripts.learningrestfullapis.repositories.EmployeeRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.apache.el.util.ReflectionUtil;
import org.aspectj.util.Reflection;
import org.springframework.data.util.ReflectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.sql.Ref;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository
                .findById(id)
                .map(employeeEntity ->
                modelMapper.map(employeeEntity, EmployeeDTO.class)
        );
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map((employeeEntity) -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity employeeEntity = modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployee =  employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployee,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(Long id,EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    public Boolean isEmployeeExistById(Long id){
        return employeeRepository.existsById(id);
    }

    public Boolean deleteEmployee(Long id) {
        Boolean isPresent = isEmployeeExistById(id);
        employeeRepository.deleteById(id);
        return isPresent;
    }

    public EmployeeDTO updateEmployeeField(Long id, Map<String,Object> updates){
        Boolean isPresent = isEmployeeExistById(id);
        if(!isPresent) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        updates.forEach((field,value) -> {
            Field fieldsRequired = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldsRequired.setAccessible(true);
            ReflectionUtils.setField(fieldsRequired,employeeEntity,value);
        });
        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }
}
