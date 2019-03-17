package medicalAnalysis.thyrotropin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import medicalAnalysis.doctor.Doctor;
import medicalAnalysis.patient.Patient;

@Entity
@Data
public class Thyrotropin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double TSH;
	@OneToOne
	private Patient patient;

	@OneToOne
	private Doctor doctor;

}
