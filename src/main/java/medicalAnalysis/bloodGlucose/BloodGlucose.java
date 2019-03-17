package medicalAnalysis.bloodGlucose;

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
public class BloodGlucose {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Boolean emptyStomach;
	private Double glucoseLevel;
	@OneToOne
	private Patient patient;

	@OneToOne
	private Doctor doctor;

}
