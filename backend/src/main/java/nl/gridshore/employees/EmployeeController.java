package nl.gridshore.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(origins = "http://localhost:8000", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping(method = GET)
    public List<Employee> employees(@RequestParam(defaultValue = "") String name) {
        return service.queryForEmployees(name);
    }

    @RequestMapping(method = GET, value = "/_search")
    public List<Employee> searchEmployees(@RequestParam String term) {
        return service.queryForEmployees(term);
    }

    @RequestMapping(method = GET, value = "/{id}")
    public Employee employeeById(@PathVariable String id) {
        return service.loadEmployeeById(id);
    }

    @RequestMapping(method = POST)
    public Employee storeEmployee(@RequestBody Employee employee) {
        String id = service.storeEmployee(employee);
        employee.setId(id);
        return employee;
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public void removeEmployee(@PathVariable String id) {
        service.removeEmployee(id);
    }
}
