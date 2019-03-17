package medicalAnalysis.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class CheckIdentyficationNumber implements ConstraintValidator<CheckIdentyficationNumberValidator, String> {

	public void initialize(CheckIdentyficationNumberValidator constraintAnnotation) {

	}

	public boolean isValid(String value, ConstraintValidatorContext context) {

		return value.equals("2203");
	}

}
