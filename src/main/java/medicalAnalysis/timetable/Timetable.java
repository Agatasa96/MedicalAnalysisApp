package medicalAnalysis.timetable;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import medicalAnalysis.doctor.Doctor;
import medicalAnalysis.patient.Patient;

@Entity
@Data
public class Timetable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime date;
	private Boolean reservation;
	private Boolean confirmReservation;

	@ManyToOne
	private Doctor doctor;
	@ManyToOne
	private Patient patient;
}
