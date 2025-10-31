package sv.ues.cpn135.Cumulonimbus.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sv.ues.cpn135.Cumulonimbus.dto.EmployeeDTO;
import sv.ues.cpn135.Cumulonimbus.entity.Employee;
import sv.ues.cpn135.Cumulonimbus.mapper.EmployeeMapper;
import sv.ues.cpn135.Cumulonimbus.repository.EmployeeRepository;
import sv.ues.cpn135.Cumulonimbus.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;

  @Override
  public EmployeeDTO createOrUpdateEmployee(EmployeeDTO employeeDTO) {
    if (employeeDTO.getId() != null) {
      employeeRepository.findById(employeeDTO.getId())
          .orElseThrow(() -> new RuntimeException(("El Empleado con id: " + employeeDTO.getId() + " no existe")));
    }
    if (employeeRepository.existsByNationalId(employeeDTO.getNationalId())) {
      throw new RuntimeException("Ya existe un abonado con Identificacion: " + employeeDTO.getNationalId());
    }
    if (employeeRepository.existxByEmail(employeeDTO.getEmail())) {
      throw new RuntimeException("El email: " + employeeDTO.getEmail() + "Ya se encuentra registrado");
    }
    Employee employee = employeeRepository.save(employeeMapper.toEntity(employeeDTO));

    return employeeMapper.toDTO(employee);

  }

  @Override
  public List<EmployeeDTO> getAllEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    List<EmployeeDTO> employeesDTOs = employees.stream().map(employeeMapper::toDTO).collect(Collectors.toList());
    return employeesDTOs;
  }

  @Override
  public EmployeeDTO getEmployee(Long id) {
    Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("El Empleado con id: " + id + " no existe"));
    return employeeMapper.toDTO(employee);

  }

  @Override
  public boolean deleteEmployee(Long id) {
    Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("El Empleado con id: " + id + " no existe"));
    employeeRepository.delete(employee);
    return true;
  }

}
