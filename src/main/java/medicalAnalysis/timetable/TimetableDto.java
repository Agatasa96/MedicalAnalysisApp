package medicalAnalysis.timetable;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import medicalAnalysis.doctor.DoctorDto;
import medicalAnalysis.patient.PatientDto;

@Data
public class TimetableDto {

	private Long id;
	@DateTimeFormat
	private LocalDateTime date;
	private Boolean reservation;
	private Boolean confirmReservation;
	private DoctorDto doctorDto;
	private PatientDto patientDto;
}
