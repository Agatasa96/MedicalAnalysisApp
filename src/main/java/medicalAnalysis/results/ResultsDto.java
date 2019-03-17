package medicalAnalysis.results;

import javax.validation.constraints.Size;

import lombok.Data;
import medicalAnalysis.bloodGlucose.BloodGlucoseDto;
import medicalAnalysis.bloodMorphology.MorphologyDto;

import medicalAnalysis.patient.PatientDto;
import medicalAnalysis.thyrotropin.ThyrotropinDto;
import medicalAnalysis.urine.UrineDto;

@Data
public class ResultsDto {
	private Long id;
	@Size(min = 5)
	private String testName;
	private String healthStatus;
	private String diagnosis;
	private Boolean shared;
	private PatientDto patientDto;
	private UrineDto urineDto;
	private MorphologyDto morphologyDto;
	private BloodGlucoseDto bloodGlucoseDto;
	private ThyrotropinDto thyrotropinDto;

}
