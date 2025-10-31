package sv.ues.cpn135.Cumulonimbus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.ues.cpn135.Cumulonimbus.entity.Employee;

/**
 * EmployeeRepository
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  boolean existsByNationalId(String nationalId);

  boolean existxByEmail(String email);

}
