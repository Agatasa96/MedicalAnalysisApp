package medicalAnalysis.doctor;

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

import medicalAnalysis.patient.PatientDto;

@Controller
@RequestMapping("/doctor")
@SessionAttributes({ "doctorDto", "logged" })
public class DoctorController {

	private final DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@GetMapping("/panel")
	public String doctorPanel() {
		return "doctorPanel";
	}

	@GetMapping("/mainPage")
	public String doctorMainPage() {
		return "mainPage";
	}

	@GetMapping("/register")
	public String doctorRegister(Model model) {
		model.addAttribute("doctorDto", new DoctorDto());
		return "doctorRegister";
	}

	@PostMapping("/register")
	public String doctorRegister(@Valid @ModelAttribute("doctorDto") DoctorDto doctorDto, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "doctorRegister";
		}

		DoctorDto savedDoctor = doctorService.register(doctorDto);
		if (Objects.nonNull(savedDoctor)) {
			model.addAttribute("doctorDto", savedDoctor);
			model.addAttribute("logged", "doctor");
			return "mainPage";
		}
		JOptionPane.showMessageDialog(null, "Cannot register");
		return "doctorRegister";

	}

	@GetMapping("/logIn")
	public String logIn(Model model) {
		model.addAttribute("doctorDto", new DoctorDto());
		return "doctorLogIn";
	}

	@PostMapping("/logIn")
	public String logIn(@ModelAttribute("doctorDto") DoctorDto doctorDto, Model model) {
		DoctorDto foundedDoctor = doctorService.logIn(doctorDto);
		if (Objects.nonNull(foundedDoctor)) {
			model.addAttribute("doctorDto", foundedDoctor);
			model.addAttribute("logged", "doctor");
			return "mainPage";
		}
		JOptionPane.showMessageDialog(null, "Wrong pesel or password");
		return "redirect:/doctor/logIn";
	}

	@GetMapping("/showMyPatients")
	public String patientsList(Model model, @SessionAttribute("doctorDto") DoctorDto doctorDto) {

		List<PatientDto> patients = doctorService.findAllPatients(doctorDto);
		if (Objects.nonNull(patients)) {
			model.addAttribute("patients", patients);
			return "patientsList";
		}
		JOptionPane.showMessageDialog(null, "Cannot load...");
		return "mainPage";
	}

	@GetMapping("/delete/{pesel}")
	public String deletePatient(@PathVariable("pesel") String pesel,
			@SessionAttribute("doctorDto") DoctorDto doctorDto) {
		String answer = doctorService.delete(pesel, doctorDto.getPesel());
		if (answer.equals("d")) {
			JOptionPane.showMessageDialog(null, "Deleted");

		} else {
			JOptionPane.showMessageDialog(null, "You do not have permission!");
		}
		return "redirect:/patient/showAllPatients";
	}

}
