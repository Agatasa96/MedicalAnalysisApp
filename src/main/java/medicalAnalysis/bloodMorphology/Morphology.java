package medicalAnalysis.bloodMorphology;

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
public class Morphology {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double WBC;
	private Double LYMPH;
	private Double MONO;
	private Double EOS;
	private Double BASO;

	@OneToOne
	private Patient patient;

	@OneToOne
	private Doctor doctor;
}
