package medicalAnalysis.patient;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.pl.PESEL;

import lombok.Data;

import medicalAnalysis.doctor.DoctorDto;
import medicalAnalysis.results.ResultsDto;
import medicalAnalysis.validator.PatientPeselValid;

@Data
public class PatientDto {

	private Long id;
	@PESEL
	@PatientPeselValid
	private String pesel;
	@Size(min = 3)
	private String name;
	private String surname;
	@Size(min = 3)
	private String password;
	private LocalDateTime date;
	private DoctorDto doctorDto;
	private List<ResultsDto> resultsDto;
}
