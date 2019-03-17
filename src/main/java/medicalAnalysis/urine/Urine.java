package medicalAnalysis.urine;

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

public class Urine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Boolean correctColor;
	private Boolean correctClarity;
	private Double weight;
	private Double ph;
	private Boolean protein;
	private Boolean glucose;

	@OneToOne
	private Patient patient;

	@OneToOne
	private Doctor doctor;

}
