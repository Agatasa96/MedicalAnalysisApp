package medicalAnalysis.results;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import medicalAnalysis.bloodGlucose.BloodGlucose;
import medicalAnalysis.bloodMorphology.Morphology;

import medicalAnalysis.patient.Patient;
import medicalAnalysis.thyrotropin.Thyrotropin;
import medicalAnalysis.urine.Urine;

@Entity
@Data
public class Results {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String testName;
	private String healthStatus;
	private String diagnosis;
	private Boolean shared;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@OneToOne
	@JoinColumn(name = "urine_id")
	private Urine urine;

	@OneToOne
	@JoinColumn(name = "morphology_id")
	private Morphology morphology;

	@OneToOne
	@JoinColumn(name = "bloodGlucose_id")
	private BloodGlucose bloodGlucose;

	@OneToOne
	@JoinColumn(name = "thyrotropin_id")
	private Thyrotropin thyrotropin;
}
