package sv.ues.cpn135.Cumulonimbus.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sv.ues.cpn135.Cumulonimbus.dto.EmployeeDTO;
import sv.ues.cpn135.Cumulonimbus.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping
  public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
    List<EmployeeDTO> employees = employeeService.getAllEmployees();
    return ResponseEntity.ok(employees);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
    EmployeeDTO employee = employeeService.getEmployee(id);
    return ResponseEntity.ok(employee);
  }

  @PostMapping
  public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
    EmployeeDTO created = employeeService.createOrUpdateEmployee(employeeDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
    if (employeeDTO.getId() == null) {
      employeeDTO.setId(id);
    } else if (!id.equals(employeeDTO.getId())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    EmployeeDTO updated = employeeService.createOrUpdateEmployee(employeeDTO);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    employeeService.deleteEmployee(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/batch")
  public ResponseEntity<List<EmployeeDTO>> createEmployees(@RequestBody List<EmployeeDTO> employeeDTO) {
    List<EmployeeDTO> createds = employeeService.saveEmployees(employeeDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createds);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
    String msg = ex.getMessage();
    if (msg != null && msg.toLowerCase().contains("no existe")) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
  }

}
