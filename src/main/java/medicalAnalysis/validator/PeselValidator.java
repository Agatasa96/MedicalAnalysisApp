package medicalAnalysis.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import medicalAnalysis.doctor.Doctor;
import medicalAnalysis.doctor.DoctorRepository;

@Component
public class PeselValidator implements ConstraintValidator<PeselValid, String> {

	@Autowired
	private DoctorRepository doctorRepository;

	public void initialize(PeselValid constraintAnnotation) {

	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		Doctor doctor = doctorRepository.findDoctorByPesel(value);
		return Objects.isNull(doctor);
	}

}
