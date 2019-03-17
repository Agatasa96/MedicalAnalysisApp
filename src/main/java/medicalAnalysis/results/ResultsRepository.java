package medicalAnalysis.results;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ResultsRepository extends JpaRepository<Results, Long> {

	List<Results> findAllByUrineId(Long id);

	List<Results> findAllByPatientPeselAndShared(String pesel, boolean b);

	List<Results> findAllByMorphologyId(Long id);

	List<Results> findAllByBloodGlucoseId(Long id);

	List<Results> findAllByThyrotropinId(Long id);

}
