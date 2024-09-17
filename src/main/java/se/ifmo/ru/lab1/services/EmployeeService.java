package se.ifmo.ru.lab1.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.ifmo.ru.lab1.models.Employee;
import se.ifmo.ru.lab1.repositories.EmployeeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepo;
    Logger logger = LoggerFactory.getLogger(EmployeeService.class);


    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Integer id){
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        logger.info("Employee with id: {} doesn't exist", id);
        return null;
    }

    public Employee saveEmployee (Employee employee){
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        Employee savedEmployee = employeeRepo.save(employee);

        logger.info("Employee with id: {} saved successfully", employee.getId());
        return savedEmployee;
    }

    public Employee updateEmployee (Employee employee) {
        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());
        employee.setCreatedAt(existingEmployee.get().getCreatedAt());
        employee.setUpdatedAt(LocalDateTime.now());

        Employee updatedEmployee = employeeRepo.save(employee);

        logger.info("Employee with id: {} updated successfully", employee.getId());
        return updatedEmployee;
    }

    public void deleteEmployeeById (Integer id) {
        employeeRepo.deleteById(id);
    }
}
