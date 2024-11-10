package poriaDev.PracticeQuerySpringBoot.employee;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String department) {

        List<Employee> employees = employeeService
                .findEmployeesWithCustomMatcher(firstName, department);
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/search/findEmployee")
    public List<Employee> findByData(@RequestBody @Valid Employee employee) {
        return employeeService.findEmployee(employee);
    }

    @PostMapping("/search/example/one")
    public Optional<Employee> findOneByExample(@RequestBody @Valid Employee employee) {
        return employeeService.findOneEmployeeByExample(employee);
//                .orElseThrow(() -> new EmployeeNotFoundException("No employee found matching the example"));
    }

    @PostMapping("/count")
    public long countByExample(@RequestBody @Valid Employee employee) {
        return employeeService.countEmployeesByExample(employee);
    }

    @PostMapping("/exists")
    public boolean existsByExample(@RequestBody @Valid Employee employee) {
        return employeeService.existsByExample(employee);
    }


}
