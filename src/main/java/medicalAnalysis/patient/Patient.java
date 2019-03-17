package medicalAnalysis.patient;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import medicalAnalysis.doctor.Doctor;

@Entity
@Data
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pesel;
	private String name;
	private String surname;
	private String password;
	private LocalDateTime date;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

}
