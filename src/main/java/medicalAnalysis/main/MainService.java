package medicalAnalysis.main;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

import org.springframework.stereotype.Service;

import medicalAnalysis.bloodGlucose.BloodGlucose;
import medicalAnalysis.bloodGlucose.BloodGlucoseDto;
import medicalAnalysis.bloodGlucose.BloodGlucoseRepository;
import medicalAnalysis.bloodMorphology.Morphology;
import medicalAnalysis.bloodMorphology.MorphologyDto;
import medicalAnalysis.bloodMorphology.MorphologyRepository;
import medicalAnalysis.doctor.Doctor;
import medicalAnalysis.doctor.DoctorDto;
import medicalAnalysis.doctor.DoctorRepository;
import medicalAnalysis.patient.Patient;
import medicalAnalysis.patient.PatientDto;
import medicalAnalysis.patient.PatientRepository;
import medicalAnalysis.patient.PatientService;
import medicalAnalysis.results.Results;
import medicalAnalysis.results.ResultsDto;
import medicalAnalysis.thyrotropin.Thyrotropin;
import medicalAnalysis.thyrotropin.ThyrotropinDto;
import medicalAnalysis.thyrotropin.ThyrotropinRepository;
import medicalAnalysis.timetable.Timetable;
import medicalAnalysis.timetable.TimetableDto;
import medicalAnalysis.urine.Urine;
import medicalAnalysis.urine.UrineDto;
import medicalAnalysis.urine.UrineRepository;

@Service
public class MainService {

	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	private final BloodGlucoseRepository bloodGlucoseRepository;
	private final MorphologyRepository morphologyRepository;
	private final ThyrotropinRepository thyrotropinRepository;
	private final UrineRepository urineRepository;
	
	

	public MainService(DoctorRepository doctorRepository, PatientRepository patientRepository,
			BloodGlucoseRepository bloodGlucoseRepository, MorphologyRepository morphologyRepository,
			ThyrotropinRepository thyrotropinRepository, UrineRepository urineRepository) {
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
		this.bloodGlucoseRepository = bloodGlucoseRepository;
		this.morphologyRepository = morphologyRepository;
		this.thyrotropinRepository = thyrotropinRepository;
		this.urineRepository = urineRepository;
	}

	public Patient toDomainPatient(PatientDto patientDto) {
		Patient patient = new Patient();
		patient.setId(patientDto.getId());
		patient.setName(patientDto.getName());
		if (patientDto.getPassword() == null) {
			patient.setPassword(null);
		} else {
			patient.setPassword(Base64.getEncoder().encodeToString(patientDto.getPassword().getBytes()));
		}
		patient.setPesel(patientDto.getPesel());
		patient.setSurname(patientDto.getSurname());
		Doctor doctor = doctorRepository.findOne(patientDto.getDoctorDto().getId());
		patient.setDoctor(doctor);
		patient.setDate(LocalDateTime.now());
		return patient;
	}

	public PatientDto toDtoPatient(Patient patient) {
		PatientDto patientDto = new PatientDto();
		patientDto.setId(patient.getId());
		patientDto.setName(patient.getName());
		patientDto.setPassword(patient.getPassword());
		patientDto.setPesel(patient.getPesel());
		patientDto.setSurname(patient.getSurname());
		patientDto.setDate(patient.getDate());
		DoctorDto doctorDto = toDtoDoctor(patient.getDoctor());
		patientDto.setDoctorDto(doctorDto);
		return patientDto;
	}

	public Doctor toDomainDoctor(DoctorDto doctorDto) {
		Doctor doctor = new Doctor();
		doctor.setId(doctorDto.getId());
		doctor.setIdentyficationNumber(doctorDto.getIdentyficationNumber());
		doctor.setName(doctorDto.getName());
		doctor.setPassword(Base64.getEncoder().encodeToString(doctorDto.getPassword().getBytes()));
		doctor.setPesel(doctorDto.getPesel());
		doctor.setSurname(doctorDto.getSurname());
		return doctor;
	}

	public DoctorDto toDtoDoctor(Doctor doctor) {
		DoctorDto doctorDto = new DoctorDto();
		doctorDto.setId(doctor.getId());
		doctorDto.setIdentyficationNumber(doctor.getIdentyficationNumber());
		doctorDto.setName(doctor.getName());
		doctorDto.setPassword(doctor.getPassword());
		doctorDto.setPesel(doctor.getPesel());
		doctorDto.setSurname(doctor.getSurname());
		return doctorDto;
	}

	public Timetable toDomainTimetable(TimetableDto timetableDto) {
		Timetable timetable = new Timetable();
		timetable.setConfirmReservation(timetableDto.getConfirmReservation());
		timetable.setDate(timetableDto.getDate());
		timetable.setId(timetableDto.getId());
		timetable.setReservation(timetableDto.getReservation());
		Doctor doctor = doctorRepository.findOne(timetableDto.getDoctorDto().getId());
		timetable.setDoctor(doctor);
		if (Objects.nonNull(timetableDto.getPatientDto())) {
			Patient patient = patientRepository.findOne(timetableDto.getPatientDto().getId());
			timetable.setPatient(patient);
		}
		return timetable;
	}

	public TimetableDto toDtoTimetable(Timetable timetable) {
		TimetableDto timetableDto = new TimetableDto();
		timetableDto.setConfirmReservation(timetable.getConfirmReservation());
		timetableDto.setDate(timetable.getDate());
		timetableDto.setId(timetable.getId());
		timetableDto.setReservation(timetable.getReservation());
		if (Objects.nonNull(timetable.getPatient())) {
			timetableDto.setPatientDto(toDtoPatient(timetable.getPatient()));
		}
		timetableDto.setDoctorDto(toDtoDoctor(timetable.getDoctor()));
		return timetableDto;
	}

	public Results toDomainResults(ResultsDto resultsDto) {
		Results results = new Results();
		results.setDiagnosis(resultsDto.getDiagnosis());
		results.setHealthStatus(resultsDto.getHealthStatus());
		results.setId(resultsDto.getId());
		Patient patient = patientRepository.findOne(resultsDto.getPatientDto().getId());
		results.setPatient(patient);
		results.setTestName(resultsDto.getTestName());
		if (Objects.nonNull(resultsDto.getBloodGlucoseDto())) {
			BloodGlucose bloodGlucose = bloodGlucoseRepository.findOne(resultsDto.getBloodGlucoseDto().getId());
			results.setBloodGlucose(bloodGlucose);

		}
		if (Objects.nonNull(resultsDto.getMorphologyDto())) {
			Morphology morphology = morphologyRepository.findOne(resultsDto.getMorphologyDto().getId());
			results.setMorphology(morphology);

		}
		if (Objects.nonNull(resultsDto.getThyrotropinDto())) {
			Thyrotropin thyrotropin = thyrotropinRepository.findOne(resultsDto.getThyrotropinDto().getId());
			results.setThyrotropin(thyrotropin);			
		}
		if (Objects.nonNull(resultsDto.getUrineDto())) {
			Urine urine = urineRepository.findOne(resultsDto.getUrineDto().getId());
			results.setUrine(urine);
		}
		results.setShared(resultsDto.getShared());

		return results;
	}

	public ResultsDto toDtoResults(Results results) {
		ResultsDto resultsDto = new ResultsDto();
		resultsDto.setDiagnosis(results.getDiagnosis());
		resultsDto.setHealthStatus(results.getHealthStatus());
		resultsDto.setId(results.getId());
		resultsDto.setTestName(results.getTestName());
		if (Objects.nonNull(results.getUrine())) {
			resultsDto.setUrineDto(toDtoUrine(results.getUrine()));
		}
		if (Objects.nonNull(results.getMorphology())) {
			resultsDto.setMorphologyDto(toDtoMorphology(results.getMorphology()));
		}
		if (Objects.nonNull(results.getBloodGlucose())) {
			resultsDto.setBloodGlucoseDto(toDtoGlucose(results.getBloodGlucose()));
		}
		if (Objects.nonNull(results.getThyrotropin())) {
			resultsDto.setThyrotropinDto(toDtoThyrotropin(results.getThyrotropin()));

		}
		resultsDto.setShared(results.getShared());
		return resultsDto;
	}

	public Thyrotropin toDomainThyrotropin(ThyrotropinDto thyrotropinDto) {
		Thyrotropin thyrotropin = new Thyrotropin();
		if (Objects.nonNull(thyrotropinDto.getDoctorDto())) {
			Doctor doctor = doctorRepository.findOne(thyrotropinDto.getDoctorDto().getId());
			thyrotropin.setDoctor(doctor);
		}
		thyrotropin.setId(thyrotropinDto.getId());
		Patient patient = patientRepository.findOne(thyrotropinDto.getPatientDto().getId());
		thyrotropin.setPatient(patient);
		thyrotropin.setTSH(thyrotropinDto.getTSH());
		return thyrotropin;
	}

	public ThyrotropinDto toDtoThyrotropin(Thyrotropin thyrotropin) {
		ThyrotropinDto thyrotropinDto = new ThyrotropinDto();

		thyrotropinDto.setId(thyrotropin.getId());
		thyrotropinDto.setTSH(thyrotropin.getTSH());
		return thyrotropinDto;
	}

	public BloodGlucose toDomainGlucose(BloodGlucoseDto bloodGlucoseDto) {
		BloodGlucose bloodGlucose = new BloodGlucose();
		if (Objects.nonNull(bloodGlucoseDto.getDoctorDto())) {
			Doctor doctor = doctorRepository.findOne(bloodGlucoseDto.getDoctorDto().getId());
			bloodGlucose.setDoctor(doctor);
		}
		bloodGlucose.setEmptyStomach(bloodGlucoseDto.getEmptyStomach());
		bloodGlucose.setGlucoseLevel(bloodGlucoseDto.getGlucoseLevel());
		bloodGlucose.setId(bloodGlucoseDto.getId());

		Patient patient = patientRepository.findOne(bloodGlucoseDto.getPatientDto().getId());
		bloodGlucose.setPatient(patient);
		return bloodGlucose;

	}

	public BloodGlucoseDto toDtoGlucose(BloodGlucose bloodGlucose) {
		BloodGlucoseDto bloodGlucoseDto = new BloodGlucoseDto();

		bloodGlucoseDto.setEmptyStomach(bloodGlucose.getEmptyStomach());
		bloodGlucoseDto.setGlucoseLevel(bloodGlucose.getGlucoseLevel());
		bloodGlucoseDto.setId(bloodGlucose.getId());

		return bloodGlucoseDto;

	}

	public Morphology toDomainMorphology(MorphologyDto morphologyDto) {
		Morphology morphology = new Morphology();
		morphology.setBASO(morphologyDto.getBASO());
		if (Objects.nonNull(morphologyDto.getDoctorDto())) {
			Doctor doctor = doctorRepository.findOne(morphologyDto.getDoctorDto().getId());
			morphology.setDoctor(doctor);
		}
		morphology.setEOS(morphologyDto.getEOS());
		morphology.setId(morphologyDto.getId());
		morphology.setLYMPH(morphologyDto.getLYMPH());
		morphology.setMONO(morphologyDto.getMONO());
		Patient patient = patientRepository.findOne(morphologyDto.getPatientDto().getId());
		morphology.setPatient(patient);
		morphology.setWBC(morphologyDto.getWBC());
		return morphology;
	}

	public MorphologyDto toDtoMorphology(Morphology morphology) {
		MorphologyDto morphologyDto = new MorphologyDto();
		morphologyDto.setBASO(morphology.getBASO());
		morphologyDto.setEOS(morphology.getEOS());
		morphologyDto.setId(morphology.getId());
		morphologyDto.setLYMPH(morphology.getLYMPH());
		morphologyDto.setMONO(morphology.getMONO());
		morphologyDto.setWBC(morphology.getWBC());
		return morphologyDto;
	}

	public Urine toDomainUrine(UrineDto urineDto) {
		Urine urine = new Urine();
		urine.setCorrectClarity(urineDto.getCorrectClarity());
		urine.setCorrectColor(urineDto.getCorrectColor());
		urine.setGlucose(urineDto.getGlucose());
		urine.setId(urineDto.getId());

		Patient patient = patientRepository.findOne(urineDto.getPatientDto().getId());
		urine.setPatient(patient);
		urine.setPh(urineDto.getPh());
		urine.setProtein(urineDto.getProtein());
		urine.setWeight(urineDto.getWeight());
		if (Objects.nonNull(urineDto.getDoctorDto())) {
			Doctor doctor = doctorRepository.findOne(urineDto.getDoctorDto().getId());
			urine.setDoctor(doctor);
		} else {
			urine.setDoctor(null);
		}

		return urine;
	}

	public UrineDto toDtoUrine(Urine urine) {
		UrineDto urineDto = new UrineDto();
		urineDto.setCorrectClarity(urine.getCorrectClarity());
		urineDto.setCorrectColor(urine.getCorrectColor());
		urineDto.setGlucose(urine.getGlucose());
		urineDto.setId(urine.getId());
		urineDto.setPh(urine.getPh());
		urineDto.setProtein(urine.getProtein());
		urineDto.setWeight(urine.getWeight());

		return urineDto;

	}

}
