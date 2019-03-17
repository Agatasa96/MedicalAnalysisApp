package medicalAnalysis.urine;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import medicalAnalysis.doctor.DoctorDto;
import medicalAnalysis.patient.PatientDto;

@Data
@NoArgsConstructor
public class UrineDto {
	private Long id;

	private Boolean correctColor;
	private Boolean correctClarity;
	@NotNull
	private Double weight;
	@NotNull
	private Double ph;
	private Boolean protein;
	private Boolean glucose;
	private PatientDto patientDto;
	private DoctorDto doctorDto;

}
