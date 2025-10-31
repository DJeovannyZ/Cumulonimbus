package sv.ues.cpn135.Cumulonimbus.service;

import java.util.List;

import sv.ues.cpn135.Cumulonimbus.dto.EmployeeDTO;

/**
 * EmployeeService
 */
public interface EmployeeService {

  EmployeeDTO createOrUpdateEmployee(EmployeeDTO employeeDTO);

  List<EmployeeDTO> getAllEmployees();

  EmployeeDTO getEmployee(Long id);

  boolean deleteEmployee(Long id);

  public List<EmployeeDTO> saveEmployees(List<EmployeeDTO> employeesDTO);
}
