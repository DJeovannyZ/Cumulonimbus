package sv.ues.cpn135.Cumulonimbus.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Employee {

  private Long id;

  private String name;

  private String lastname;

  private String nationalId;

  private String phone;

  private String email;

  private LocalDate hiringDate;

}
