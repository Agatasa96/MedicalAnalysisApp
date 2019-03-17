package medicalAnalysis.bloodMorphology;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MorphologyRepository extends JpaRepository<Morphology, Long> {

	List<Morphology> findAllByPatientPeselAndDoctorPesel(String pesel, String doctorPesel);

}
