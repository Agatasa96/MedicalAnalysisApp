package medicalAnalysis.bloodGlucose;

import javax.validation.constraints.NotNull;

import lombok.Data;
import medicalAnalysis.doctor.DoctorDto;
import medicalAnalysis.patient.PatientDto;

@Data
public class BloodGlucoseDto {

	private Long id;

	private Boolean emptyStomach;
	@NotNull
	private Double glucoseLevel;
	private PatientDto patientDto;
	private DoctorDto doctorDto;
}
