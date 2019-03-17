package medicalAnalysis.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import medicalAnalysis.patient.Patient;
import medicalAnalysis.patient.PatientRepository;

public class PatientPeselValidator implements ConstraintValidator<PatientPeselValid, String> {
	@Autowired
	private PatientRepository patientRepository;

	public void initialize(PatientPeselValid constraintAnnotation) {

	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		Patient patient = patientRepository.findPatientByPesel(value);
		return Objects.isNull(patient);
	}

}
