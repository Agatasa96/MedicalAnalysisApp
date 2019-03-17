package medicalAnalysis.thyrotropin;

import javax.validation.constraints.NotNull;

import lombok.Data;
import medicalAnalysis.doctor.DoctorDto;
import medicalAnalysis.patient.PatientDto;

@Data
public class ThyrotropinDto {
	private Long id;
	@NotNull
	private Double TSH;
	private PatientDto patientDto;
	private DoctorDto doctorDto;
}
