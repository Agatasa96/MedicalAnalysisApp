package medicalAnalysis.bloodMorphology;

import javax.validation.constraints.NotNull;

import lombok.Data;
import medicalAnalysis.doctor.DoctorDto;
import medicalAnalysis.patient.PatientDto;

@Data
public class MorphologyDto {
	private Long id;
	@NotNull
	private Double WBC;
	@NotNull
	private Double LYMPH;
	@NotNull
	private Double MONO;
	@NotNull
	private Double EOS;
	@NotNull
	private Double BASO;

	private PatientDto patientDto;
	private DoctorDto doctorDto;
}
