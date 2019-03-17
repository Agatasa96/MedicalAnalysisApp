package medicalAnalysis.urine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrineRepository extends JpaRepository<Urine, Long> {

	List<Urine> findAllByPatientPeselAndDoctorPesel(String pesel, String doctorPesel);

}
