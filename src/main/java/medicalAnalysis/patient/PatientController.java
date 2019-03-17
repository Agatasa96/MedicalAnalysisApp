package medicalAnalysis.patient;

import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import medicalAnalysis.doctor.DoctorDto;
import medicalAnalysis.doctor.DoctorService;
import medicalAnalysis.timetable.TimetableDto;

@Controller
@RequestMapping("/patient")
@SessionAttributes({ "doctorDto", "patientDto", "logged" })
public class PatientController {

	private final PatientService patientService;
	private final DoctorService doctorService;

	public PatientController(PatientService patientService, DoctorService doctorService) {
		this.patientService = patientService;
		this.doctorService = doctorService;
	}

	@GetMapping("/mainPage")
	public String patientMainPage() {
		return "patientMainPage";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("patientDto", new PatientDto());
		return "patientRegister";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("patientDto") PatientDto patientDto, BindingResult bindingResult,
			@SessionAttribute("doctorDto") DoctorDto doctorDto) {
		if (bindingResult.hasErrors()) {
			return "patientRegister";
		}
		patientDto.setDoctorDto(doctorDto);
		PatientDto savedPatient = patientService.register(patientDto);
		if (Objects.nonNull(savedPatient)) {

			JOptionPane.showMessageDialog(null, "Added");
			return "mainPage";
		}
		return "patientRegister";
	}

	@GetMapping("/showAllPatients")
	public String showAllPatients(Model model) {
		List<PatientDto> allPatients = patientService.showAllPatients();
		if (Objects.nonNull(allPatients)) {
			model.addAttribute("patients", allPatients);
			return "patientsList";

		}
		JOptionPane.showMessageDialog(null, "Cannot load...");
		return "mainPage";
	}

	@GetMapping("/byPesel")
	public String findByPesel(Model model, @ModelAttribute("pesel") String pesel) {
		PatientDto foundedPatient = patientService.findByPesel(pesel);
		if (Objects.nonNull(foundedPatient)) {
			model.addAttribute("patient", foundedPatient);
			return "patientByPesel";
		}
		JOptionPane.showMessageDialog(null, "Cannot find...");
		return "mainPage";
	}

	@GetMapping("/details/{pesel}")
	public String getDetails(@PathVariable("pesel") String pesel, @SessionAttribute("doctorDto") DoctorDto doctorDto,
			Model model) {
		PatientDto patientDto = patientService.getDetails(pesel, doctorDto);
		if (Objects.nonNull(patientDto)) {
			model.addAttribute("patient", patientDto);
			return "patientDetails";
		}
		JOptionPane.showMessageDialog(null, "You do not have permission!");
		return "redirect:/patient/showAllPatients";

	}

	@GetMapping("/logIn")
	public String logIn(Model model) {
		model.addAttribute("patientDto", new PatientDto());
		return "patientLogIn";
	}

	@PostMapping("/logIn")
	public String logIn(Model model, @ModelAttribute("patientDto") PatientDto patientDto) {
		PatientDto foundedPatient = patientService.logIn(patientDto);
		if (Objects.nonNull(foundedPatient)) {
			model.addAttribute("patientDto", foundedPatient);
			model.addAttribute("logged", "patient");
			return "patientMainPage";

		}
		JOptionPane.showMessageDialog(null, "Wrong pesel or password");
		return "patientLogIn";
	}

	@GetMapping("/changePassword")
	public String changePassword() {
		return "changePassword";
	}

	@PostMapping("/changePassword")
	public String changePassword(@SessionAttribute("patientDto") PatientDto patientDto,
			@ModelAttribute("pPassword") String previous, @ModelAttribute("nPassword") String newP) {
		PatientDto edited = patientService.changePassword(patientDto, previous, newP);
		if (Objects.nonNull(edited)) {
			JOptionPane.showMessageDialog(null, "Success!");
			return "patientMainPage";
		}
		JOptionPane.showMessageDialog(null, "Cannot change...");
		return "changePassword";
	}

	@GetMapping("/changeDoctor")
	public String changeDoctor(Model model, @SessionAttribute("patientDto") PatientDto patientDto) {
		DoctorDto doctorDto = doctorService.findDoctorById(patientDto.getDoctorDto().getId());
		model.addAttribute("yourDoctor", doctorDto);
		List<DoctorDto> doctors = doctorService.findAllDoctorsWithoutOne(doctorDto.getPesel());
		model.addAttribute("doctorList", doctors);
		return "changeDoctor";
	}

	@PostMapping("/changeDoctor")
	public String changeDoctor(Model model, @ModelAttribute("id") Long id,
			@SessionAttribute("patientDto") PatientDto patientDto) {
		PatientDto changed = patientService.changeDoctor(id, patientDto);
		if (Objects.nonNull(changed)) {
			JOptionPane.showMessageDialog(null, "Success!");
			model.addAttribute("patientDto", changed);
			return "patientMainPage";
		}
		JOptionPane.showMessageDialog(null, "Cannot change...");
		return "changeDoctor";
	}

	@GetMapping("/showReservations")
	public String showReservations(@SessionAttribute("patientDto") PatientDto patientDto, Model model) {
		List<TimetableDto> timetables = patientService.showReservations(patientDto);
		model.addAttribute("timetables", timetables);
		return "timetableForPatient";
	}

	@GetMapping("/addReservation/{id}")
	public String addReservation(@SessionAttribute("patientDto") PatientDto patientDto, @PathVariable("id") Long id) {
		String result = patientService.addReservation(patientDto, id);
		if (result.equals("r")) {
			JOptionPane.showMessageDialog(null, "Reserved");
			return "redirect:/patient/showReservations";
		} else if (result.equals("hr")) {
			JOptionPane.showMessageDialog(null, "This date is already reserved!");
			return "redirect:/timetable/showAll";
		} else {
			JOptionPane.showMessageDialog(null, "Cannot reserved!");
			return "redirect:/timetable/showAll";
		}
	}

}
