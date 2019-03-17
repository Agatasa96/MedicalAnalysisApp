package medicalAnalysis.doctor;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import medicalAnalysis.patient.Patient;

@Entity
@Data
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String surname;
	private String password;
	private String identyficationNumber;
	private String pesel;

	@OneToMany
	private List<Patient> patients;
}
