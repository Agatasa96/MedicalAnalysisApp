package medicalAnalysis.thyrotropin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThyrotropinRepository extends JpaRepository<Thyrotropin, Long> {

	List<Thyrotropin> findAllByPatientPeselAndDoctorPesel(String pesel, String doctorPesel);

}
