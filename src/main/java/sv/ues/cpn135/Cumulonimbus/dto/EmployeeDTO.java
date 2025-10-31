package sv.ues.cpn135.Cumulonimbus.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

  private Long id;

  private String name;

  private String lastname;

  private String nationalId;

  private String phone;

  private String email;

  private LocalDate hiringDate;

}
