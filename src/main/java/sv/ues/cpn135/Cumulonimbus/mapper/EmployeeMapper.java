package sv.ues.cpn135.Cumulonimbus.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sv.ues.cpn135.Cumulonimbus.dto.EmployeeDTO;
import sv.ues.cpn135.Cumulonimbus.entity.Employee;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

  private final ModelMapper modelMapper;

  // Convierte de entidad Subscriber a DTO
  public EmployeeDTO toDTO(Employee employee) {
    return modelMapper.map(employee, EmployeeDTO.class);
  }

  // Convierte de DTO a entidad Subscriber
  public Employee toEntity(EmployeeDTO employeeDTO) {
    return modelMapper.map(employeeDTO, Employee.class);
  }

}
