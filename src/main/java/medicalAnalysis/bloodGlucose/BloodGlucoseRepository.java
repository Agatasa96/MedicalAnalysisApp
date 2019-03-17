package medicalAnalysis.bloodGlucose;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodGlucoseRepository extends JpaRepository<BloodGlucose, Long> {

	List<BloodGlucose> findAllByPatientPeselAndDoctorPesel(String pesel, String doctorPesel);

}
