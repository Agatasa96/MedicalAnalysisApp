package medicalAnalysis.timetable;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

	Timetable findTimetableByDate(LocalDateTime formattedDate);

	List<Timetable> findAllByDoctorPesel(String pesel);

	@Query(value = "SELECT * FROM Timetable where doctor_pesel = ?1 and date between ?2 and ?3", nativeQuery = true)
	List<Timetable> searchByDate(String pesel, LocalDateTime formattedStartDate, LocalDateTime formattedEndDate);

	@Query(value = "SELECT * FROM Timetable where confirmReservation is null and date>?1", nativeQuery = true)
	List<Timetable> findAllWhereConfirmReservation(LocalDateTime date);

	@Query(value = "SELECT * FROM Timetable where reservation is null and date>?1", nativeQuery = true)

	List<Timetable> findAllWhereReservation(LocalDateTime date);

	List<Timetable> findAllByPatientPesel(String pesel);

}
