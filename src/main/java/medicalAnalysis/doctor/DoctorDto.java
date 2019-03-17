package medicalAnalysis.doctor;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.pl.PESEL;

import lombok.Data;
import medicalAnalysis.patient.PatientDto;
import medicalAnalysis.validator.CheckIdentyficationNumberValidator;
import medicalAnalysis.validator.PeselValid;

@Data
public class DoctorDto {
	private Long id;
	@Size(min = 3)
	private String name;
	@Size(min = 3)
	private String surname;
	private String password;
	@PESEL
	@PeselValid
	private String pesel;
	@CheckIdentyficationNumberValidator
	private String identyficationNumber;
	private List<PatientDto> patientsDto;
}
