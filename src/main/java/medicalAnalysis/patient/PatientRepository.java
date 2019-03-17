package medicalAnalysis.patient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findPatientByPesel(String value);

	List<Patient> findAllPatientByDoctorPesel(String pesel);

	Patient findPatientByPeselAndDoctorPesel(String pesel, String doctorPesel);

}
