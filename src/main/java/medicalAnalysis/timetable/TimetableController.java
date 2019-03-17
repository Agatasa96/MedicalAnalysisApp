package medicalAnalysis.timetable;

import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import medicalAnalysis.doctor.DoctorDto;

import medicalAnalysis.patient.PatientDto;
import medicalAnalysis.patient.PatientService;

@Controller
@SessionAttributes({ "doctorDto", "timetableToEdit", "patientDto" })
@RequestMapping("timetable")
public class TimetableController {

	private final TimetableService timetableService;
	private final PatientService patientService;

	public TimetableController(TimetableService timetableService, PatientService patientService) {
		this.timetableService = timetableService;
		this.patientService = patientService;
	}

	@GetMapping("/add")
	public String addTimetable() {
		return "addTimetable";
	}

	@PostMapping("/add")
	public String addTimetable(@ModelAttribute("date") String date,
			@SessionAttribute("doctorDto") DoctorDto doctorDto) {
		TimetableDto timetableDto = timetableService.addTimetable(date, doctorDto, new TimetableDto());
		if (Objects.isNull(timetableDto)) {
			return "addTimetable";
		}
		JOptionPane.showMessageDialog(null, "Added");
		return "mainPage";

	}

	@GetMapping("/show")
	public String showTimetable(Model model, @SessionAttribute("doctorDto") DoctorDto doctorDto) {
		List<TimetableDto> timetablesDto = timetableService.findAll(doctorDto);
		if (Objects.isNull(timetablesDto)) {
			JOptionPane.showMessageDialog(null, "Cannot load...");
			return "mainPage";
		}
		model.addAttribute("timetables", timetablesDto);
		return "showTimetable";
	}

	@GetMapping("/delete/{id}")
	public String deleteTimetable(@PathVariable("id") Long id) {
		String checked = timetableService.deleteTimetable(id);
		if (checked.equals("d")) {
			JOptionPane.showMessageDialog(null, "Deleted");

		}
		if (checked.equals("r")) {
			JOptionPane.showMessageDialog(null, "Cannot delete, this date has reservation");

		}
		if (checked.equals("cd")) {
			JOptionPane.showMessageDialog(null, "Cannot delete...");

		}

		return "redirect:/timetable/show";
	}

	@GetMapping("/edit/{id}")
	public String editTimetable(@PathVariable("id") Long id, Model model) {
		TimetableDto timetableDto = timetableService.chechIfHasReservation(id);
		if (Objects.nonNull(timetableDto)) {
			model.addAttribute("timetableToEdit", timetableDto);
			return "editTimetable";
		}

		JOptionPane.showMessageDialog(null, "Cannot edit, this date has reservation");
		return "redirect:/timetable/show";
	}

	@PostMapping("/edit")
	public String editTimetable(@ModelAttribute("date") String date,
			@SessionAttribute("timetableToEdit") TimetableDto toEditDto) {
		TimetableDto timetableDto = timetableService.editTimetable(date, toEditDto);
		if (Objects.isNull(timetableDto)) {

			return "redirect:/timetable/show";
		}
		JOptionPane.showMessageDialog(null, "Edited");
		return "redirect:/timetable/show";

	}

	@GetMapping("/addReservationByDoctor/{id}")
	public String addReservation(@PathVariable("id") Long id, Model model) {
		TimetableDto timetableDto = timetableService.findOne(id);
		if (Objects.nonNull(timetableDto)) {
			model.addAttribute("timetable", timetableDto);
			return "addReservationByDoctor";
		}
		JOptionPane.showMessageDialog(null, "Cannot add, this date has reservation!");
		return "redirect:/timetable/show";

	}

	@PostMapping("/addReservationByDoctor/{id}")
	public String addReservation(@ModelAttribute("pesel") String pesel, @PathVariable("id") Long id,
			@SessionAttribute("doctorDto") DoctorDto doctorDto) {
		PatientDto patientDto = patientService.findByPeselAndDoctorPesel(pesel, doctorDto.getPesel());
		if (Objects.nonNull(patientDto)) {
			TimetableDto timetableDto = timetableService.addReservationByDoctor(id, patientDto, doctorDto);
			if (Objects.nonNull(timetableDto)) {
				JOptionPane.showMessageDialog(null, "Added reservation");
				return "redirect:/timetable/show";
			}
			JOptionPane.showMessageDialog(null, "Cannot add reservation");
		}
		JOptionPane.showMessageDialog(null, "Cannot find patient");

		return "redirect:/timetable/show";
	}

	@GetMapping("/byDate")
	public String searchByDate(@ModelAttribute("startDate") String startDate, @ModelAttribute("endDate") String endDate,
			@SessionAttribute("doctorDto") DoctorDto doctorDto, Model model) {
		List<TimetableDto> timetablesDto = timetableService.searchByDate(doctorDto, startDate, endDate);
		if (Objects.nonNull(timetablesDto)) {
			model.addAttribute("timetables", timetablesDto);
			return "showTimetable";

		}
		JOptionPane.showMessageDialog(null, "Cannot find");

		return "redirect:/timetable/show";
	}

	@GetMapping("/showAll")
	public String showAll(Model model) {
		List<TimetableDto> timetablesDto = timetableService.showAll();
		if (Objects.nonNull(timetablesDto)) {
			model.addAttribute("timetables", timetablesDto);
			return "timetableForPatient";
		}
		JOptionPane.showMessageDialog(null, "Cannot load...");
		return "patientMainPage";
	}

	@GetMapping("/showAvailable")
	public String showAvailable(Model model) {
		List<TimetableDto> timetablesDto = timetableService.showAvailable();
		if (Objects.nonNull(timetablesDto)) {
			model.addAttribute("timetables", timetablesDto);
			return "timetableForPatient";
		}
		JOptionPane.showMessageDialog(null, "Cannot load...");
		return "patientMainPage";
	}

	@GetMapping("/confirm/{id}")
	public String confirmReservation(@PathVariable("id") Long id,
			@SessionAttribute("patientDto") PatientDto patientDto) {
		TimetableDto timetableDto = timetableService.confirmReservation(id, patientDto);
		if (Objects.nonNull(timetableDto)) {
			JOptionPane.showMessageDialog(null, "Confirmed!");
			return "redirect:/patient/showReservations";
		}
		JOptionPane.showMessageDialog(null, "It is not your reservation");
		return "redirect:/timetable/showAll";

	}

	@GetMapping("/cancel/{id}")
	public String cancelReservation(@SessionAttribute("patientDto") PatientDto patientDto,
			@PathVariable("id") Long id) {
		String result = timetableService.cancelReservation(id, patientDto);
		if (result.equals("c")) {
			JOptionPane.showMessageDialog(null, "Canceled!");
			return "redirect:/patient/showReservations";

		} else if (result.equals("conf")) {
			JOptionPane.showMessageDialog(null, "This reservation has been confirmed!");
			return "redirect:/patient/showReservations";

		} else if (result.equals("ny")) {
			JOptionPane.showMessageDialog(null, "It is not your reservation");
			return "redirect:/timetable/showAll";
		} else {
			JOptionPane.showMessageDialog(null, "Cannot cancel");
			return "redirect:/timetable/showAll";
		}

	}
}
