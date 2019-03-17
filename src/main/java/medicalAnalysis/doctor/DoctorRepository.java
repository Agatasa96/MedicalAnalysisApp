package medicalAnalysis.doctor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	Doctor findDoctorByPesel(String value);

	@Query(value = "SELECT*FROM Doctor where not pesel=?1", nativeQuery = true)
	List<Doctor> findAllDoctorsWithoutOne(String pesel);
}
