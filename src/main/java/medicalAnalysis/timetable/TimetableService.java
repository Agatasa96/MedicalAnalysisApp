package medicalAnalysis.timetable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import org.springframework.stereotype.Service;
import medicalAnalysis.doctor.DoctorDto;
import medicalAnalysis.main.MainService;
import medicalAnalysis.patient.PatientDto;

@Service
public class TimetableService {

	private final TimetableRepository timetableRepository;
	private final MainService mainService;

	public TimetableService(TimetableRepository timetableRepository, MainService mainService) {

		this.timetableRepository = timetableRepository;
		this.mainService = mainService;
	}

	public TimetableDto addTimetable(String date, DoctorDto doctorDto, TimetableDto timetableDto) {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime formattedDate = LocalDateTime.parse(date, formatter);
			Timetable foundedTimetable = timetableRepository.findTimetableByDate(formattedDate);
			if (Objects.isNull(foundedTimetable)) {
				timetableDto.setDate(formattedDate);
				timetableDto.setDoctorDto(doctorDto);
				Timetable timetable = timetableRepository.save(mainService.toDomainTimetable(timetableDto));
				if (Objects.nonNull(timetable)) {
					return mainService.toDtoTimetable(timetable);
				}
				return null;
			} else {
				JOptionPane.showMessageDialog(null, "This timetable already exist!");
				return null;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Add correct date");
			return null;
		}

	}

	public List<TimetableDto> findAll(DoctorDto doctorDto) {
		List<Timetable> timetables = timetableRepository.findAllByDoctorPesel(doctorDto.getPesel());
		if (Objects.nonNull(timetables)) {
			List<TimetableDto> timetablesDto = new ArrayList<>();
			for (Timetable t : timetables) {
				timetablesDto.add(mainService.toDtoTimetable(t));
			}
			return timetablesDto;
		}
		return null;
	}

	public String deleteTimetable(Long id) {
		Timetable timetable = timetableRepository.findOne(id);
		if (Objects.nonNull(timetable)) {
			if (timetable.getReservation() == null || timetable.getReservation() == false) {
				timetableRepository.delete(timetable);
				return "d";
			}
			return "r";
		}
		return "cd";
	}

	public TimetableDto chechIfHasReservation(Long id) {
		Timetable timetable = timetableRepository.findOne(id);
		if (Objects.nonNull(timetable)) {
			if (timetable.getReservation() == null || timetable.getReservation() == false) {
				return mainService.toDtoTimetable(timetable);
			}
			return null;
		}
		return null;
	}

	public TimetableDto editTimetable(String date, TimetableDto toEditDto) {
		Timetable timetable = timetableRepository.findOne(toEditDto.getId());
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime formattedDate = LocalDateTime.parse(date, formatter);
			timetable.setDate(formattedDate);
			Timetable saved = timetableRepository.save(timetable);
			if (Objects.isNull(saved)) {
				return null;
			}
			return mainService.toDtoTimetable(saved);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Add correct date");
			return null;
		}

	}

	public TimetableDto addReservationByDoctor(Long id, PatientDto patientDto, DoctorDto doctorDto) {
		Timetable timetable = timetableRepository.findOne(id);
		if (Objects.nonNull(timetable)) {
			TimetableDto timetableDto = mainService.toDtoTimetable(timetable);
			timetableDto.setPatientDto(patientDto);
			timetableDto.setDoctorDto(doctorDto);
			timetableDto.setReservation(true);
			Timetable reserved = timetableRepository.save(mainService.toDomainTimetable(timetableDto));
			if (Objects.nonNull(reserved)) {
				return mainService.toDtoTimetable(timetable);
			}

		}
		return null;
	}

	public TimetableDto findOne(Long id) {
		Timetable timetable = timetableRepository.findOne(id);
		if (Objects.nonNull(timetable)) {
			if (timetable.getReservation() == null || timetable.getReservation() == false) {
				return mainService.toDtoTimetable(timetable);
			}
		}
		return null;
	}

	public List<TimetableDto> searchByDate(DoctorDto doctorDto, String startDate, String endDate) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime formattedStartDate = LocalDateTime.parse(startDate, formatter);
			LocalDateTime formattedEndDate = LocalDateTime.parse(endDate, formatter);
			List<Timetable> timetables = timetableRepository.searchByDate(doctorDto.getPesel(), formattedStartDate,
					formattedEndDate);
			if (Objects.nonNull(timetables)) {
				List<TimetableDto> timetablesDto = new ArrayList<>();
				for (Timetable t : timetables) {
					timetablesDto.add(mainService.toDtoTimetable(t));
				}
				return timetablesDto;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Add correct date");
			return null;
		}
		return null;
	}

	public List<TimetableDto> showAll() {

		List<Timetable> timetables = timetableRepository.findAllWhereConfirmReservation(LocalDateTime.now());
		if (Objects.nonNull(timetables)) {
			List<TimetableDto> timetablesDto = new ArrayList<>();
			for (Timetable t : timetables) {
				timetablesDto.add(mainService.toDtoTimetable(t));
			}
			return timetablesDto;
		}
		return null;
	}

	public List<TimetableDto> showAvailable() {

		List<Timetable> timetables = timetableRepository.findAllWhereReservation(LocalDateTime.now());
		if (Objects.nonNull(timetables)) {
			List<TimetableDto> timetablesDto = new ArrayList<>();
			for (Timetable t : timetables) {
				timetablesDto.add(mainService.toDtoTimetable(t));
			}
			return timetablesDto;
		}
		return null;
	}

	public TimetableDto confirmReservation(Long id, PatientDto patientDto) {
		Timetable timetable = timetableRepository.findOne(id);
		if (Objects.nonNull(timetable)) {
			if (timetable.getPatient().getId().equals(patientDto.getId())) {
				timetable.setConfirmReservation(true);
				Timetable saved = timetableRepository.save(timetable);
				return mainService.toDtoTimetable(saved);
			}
			return null;
		}
		return null;
	}

	public String cancelReservation(Long id, PatientDto patientDto) {
		Timetable timetable = timetableRepository.findOne(id);
		if (Objects.nonNull(timetable)) {
			if (timetable.getPatient().getId().equals(patientDto.getId())) {
				if (timetable.getConfirmReservation() == null) {
					timetable.setReservation(null);
					timetable.setPatient(null);
					timetableRepository.save(timetable);
					return "c";
				}
				return "conf";
			}
			return "ny";
		}
		return "cc";
	}

}
