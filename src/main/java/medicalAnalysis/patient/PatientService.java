package medicalAnalysis.patient;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import medicalAnalysis.doctor.Doctor;
import medicalAnalysis.doctor.DoctorDto;
import medicalAnalysis.doctor.DoctorRepository;
import medicalAnalysis.main.MainService;
import medicalAnalysis.timetable.Timetable;
import medicalAnalysis.timetable.TimetableDto;
import medicalAnalysis.timetable.TimetableRepository;

@Service
public class PatientService {

	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;
	private final TimetableRepository timetableRepository;
	private final MainService mainService;

	public PatientService(PatientRepository patientRepository, DoctorRepository doctorRepository,
			TimetableRepository timetableRepository, MainService mainService) {
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
		this.timetableRepository = timetableRepository;
		this.mainService = mainService;

	}

	public PatientDto register(PatientDto patientDto) {
		Patient patient = patientRepository.save(mainService.toDomainPatient(patientDto));
		if (Objects.nonNull(patient)) {
			return mainService.toDtoPatient(patient);
		}
		return null;
	}

	public List<PatientDto> showAllPatients() {
		List<Patient> patients = patientRepository.findAll();
		if (Objects.nonNull(patients)) {
			List<PatientDto> patientsDto = new ArrayList<PatientDto>();
			for (Patient p : patients) {
				patientsDto.add(mainService.toDtoPatient(p));
			}
			return patientsDto;
		}
		return null;
	}

	public PatientDto findByPesel(String pesel) {
		Patient patient = patientRepository.findPatientByPesel(pesel);
		if (Objects.nonNull(patient)) {
			return mainService.toDtoPatient(patient);
		}
		return null;
	}

	public PatientDto getDetails(String pesel, DoctorDto doctorDto) {
		PatientDto patientDto = findByPesel(pesel);
		if (Objects.nonNull(patientDto)) {
			if (patientDto.getDoctorDto().equals(doctorDto)) {
				return patientDto;
			}

		}

		return null;
	}

	public PatientDto logIn(PatientDto patientDto) {
		Patient patient = patientRepository.findPatientByPesel(patientDto.getPesel());
		if (Objects.nonNull(patient)) {
			String password = Base64.getEncoder().encodeToString(patientDto.getPassword().getBytes());
			if (password.equals(patient.getPassword())) {
				return mainService.toDtoPatient(patient);
			}
		}
		return null;
	}

	public PatientDto findByPeselAndDoctorPesel(String pesel, String doctorPesel) {
		Patient patient = patientRepository.findPatientByPeselAndDoctorPesel(pesel, doctorPesel);
		if (Objects.nonNull(patient)) {
			return mainService.toDtoPatient(patient);
		}
		return null;
	}

	public PatientDto changePassword(PatientDto patientDto, String prev, String newP) {
		Patient founded = patientRepository.findOne(patientDto.getId());
		if (Objects.nonNull(founded)) {
			String hashPrev = Base64.getEncoder().encodeToString(prev.getBytes());
			if (founded.getPassword().equals(hashPrev)) {
				String hashNew = Base64.getEncoder().encodeToString(newP.getBytes());
				founded.setPassword(hashNew);
				Patient patient = patientRepository.save(founded);
				if (Objects.nonNull(patient)) {
					return mainService.toDtoPatient(patient);
				}
			}

		}
		return null;
	}

	public PatientDto changeDoctor(Long id, PatientDto patientDto) {
		Doctor doctor = doctorRepository.findOne(id);
		if (Objects.nonNull(doctor)) {
			Patient patient = patientRepository.findOne(patientDto.getId());
			if (Objects.nonNull(patient)) {
				patient.setDoctor(doctor);
				Patient saved = patientRepository.save(patient);
				if (Objects.nonNull(saved)) {
					return mainService.toDtoPatient(saved);
				}
			}

		}
		return null;
	}

	public List<TimetableDto> showReservations(PatientDto patientDto) {
		List<Timetable> timetables = timetableRepository.findAllByPatientPesel(patientDto.getPesel());
		if (Objects.nonNull(timetables)) {
			List<TimetableDto> timetablesDto = new ArrayList<>();
			for (Timetable t : timetables) {
				timetablesDto.add(mainService.toDtoTimetable(t));
			}

			return timetablesDto;
		}
		return null;
	}

	public String addReservation(PatientDto patientDto, Long id) {
		Timetable timetable = timetableRepository.findOne(id);
		if (Objects.nonNull(timetable)) {
			if (timetable.getReservation() == null) {
				timetable.setPatient(mainService.toDomainPatient(patientDto));
				timetable.setReservation(true);
				Timetable saved = timetableRepository.save(timetable);
				if (Objects.nonNull(saved)) {
					return "r";
				}
				return "cr";
			}
			return "hr";
		}
		return "cr";
	}

}
