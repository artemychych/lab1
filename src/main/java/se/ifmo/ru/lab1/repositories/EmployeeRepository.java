package se.ifmo.ru.lab1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ifmo.ru.lab1.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
