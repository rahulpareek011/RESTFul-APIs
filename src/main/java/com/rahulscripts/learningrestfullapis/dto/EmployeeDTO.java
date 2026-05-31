package com.rahulscripts.learningrestfullapis.dto;

import com.rahulscripts.learningrestfullapis.Annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name should be not blank")
    @Size(min = 3,max = 15, message = "Name should be minimum 3words & maximum 15")
    private String name;

    @NotBlank(message = "email can't be blank")
    @Email(message = "email should be valid")
    private String email;

    @Max(value = 80,message = "Age should be less than 80")
    @Min(value = 18,message = "Age should be greater than 18")
    @NotNull
    private Integer age;

    @PastOrPresent(message = "future date are not allowed")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be Active")
    private Boolean isActive;

    //@Pattern(regexp = "^(ADMIN|USER)$", message = "Role of employee can be either User or Admin")
    @EmployeeRoleValidation
    @NotBlank(message = "Role of the employee cannot be blank")
    private String role;//ADMIN OR USER

    @NotNull(message = "Salary of employee can't be null")
    @Positive(message = "Salary of employee should be positive")
    @Digits(integer = 6,fraction = 2,message = "The format of salary is XXXXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;



//    EmployeeDTO(){}
//
//    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.age = age;
//        this.dateOfJoining = dateOfJoining;
//        this.isActive = isActive;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public LocalDate getDateOfJoining() {
//        return dateOfJoining;
//    }
//
//    public void setDateOfJoining(LocalDate dateOfJoining) {
//        this.dateOfJoining = dateOfJoining;
//    }
//
//    public Boolean getIsActive() {
//        return isActive;
//    }
//
//    public void setIsActive(Boolean isActive) {
//        this.isActive = isActive;
//    }
}
