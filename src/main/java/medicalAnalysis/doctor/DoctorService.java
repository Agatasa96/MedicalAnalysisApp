package medicalAnalysis.doctor;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import medicalAnalysis.main.MainService;
import medicalAnalysis.patient.Patient;
import medicalAnalysis.patient.PatientDto;
import medicalAnalysis.patient.PatientRepository;

@Service
public class DoctorService {

	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	private final MainService mainService;

	public DoctorService(DoctorRepository doctorRepository, PatientRepository patientRepository,
			MainService mainService) {
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
		this.mainService = mainService;

	}

	public DoctorDto register(DoctorDto doctorDto) {
		Doctor doctor = doctorRepository.save(mainService.toDomainDoctor(doctorDto));
		if (Objects.nonNull(doctor)) {
			return mainService.toDtoDoctor(doctor);
		}
		return null;
	}

	public DoctorDto logIn(DoctorDto doctorDto) {
		Doctor foundedDoctor = doctorRepository.findDoctorByPesel(doctorDto.getPesel());
		if (Objects.nonNull(foundedDoctor)) {
			String password = Base64.getEncoder().encodeToString(doctorDto.getPassword().getBytes());

			if (password.equals(foundedDoctor.getPassword())) {

				return mainService.toDtoDoctor(foundedDoctor);
			}
		}
		return null;
	}

	public List<PatientDto> findAllPatients(DoctorDto doctorDto) {
		List<Patient> patients = patientRepository.findAllPatientByDoctorPesel(doctorDto.getPesel());
		if (Objects.nonNull(patients)) {
			List<PatientDto> patientsDto = new ArrayList<PatientDto>();
			for (Patient p : patients) {
				patientsDto.add(mainService.toDtoPatient(p));
			}
			return patientsDto;
		}
		return null;
	}

	public DoctorDto findDoctorById(Long id) {
		Doctor founded = doctorRepository.findOne(id);
		if (Objects.nonNull(founded)) {
			return mainService.toDtoDoctor(founded);
		}
		return null;
	}

	public List<DoctorDto> findAllDoctorsWithoutOne(String pesel) {
		List<Doctor> doctors = doctorRepository.findAllDoctorsWithoutOne(pesel);
		if (Objects.nonNull(doctors)) {
			List<DoctorDto> doctorsDto = new ArrayList<>();
			for (Doctor d : doctors) {
				doctorsDto.add(mainService.toDtoDoctor(d));
			}
			return doctorsDto;
		}
		return null;
	}

	public String delete(String pesel, String doctorPesel) {
		Patient patient = patientRepository.findPatientByPeselAndDoctorPesel(pesel, doctorPesel);
		if (Objects.nonNull(patient)) {
			patientRepository.delete(patient);
			return "d";
		}
		return "np";

	}

}
