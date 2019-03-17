package medicalAnalysis.urine;

import java.util.ArrayList;
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
import medicalAnalysis.patient.PatientService;

@Controller
@RequestMapping("/urine")
@SessionAttributes({ "patientDto", "patientPesel", "doctorDto", "logged" })
public class UrineController {

	private final UrineService urineService;
	private final PatientService patientService;

	public UrineController(UrineService urineService, PatientService patientService) {
		this.urineService = urineService;
		this.patientService = patientService;
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("urineDto", new UrineDto());
		return "addUrine";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("urineDto") UrineDto urineDto, BindingResult bindingResult,
			@SessionAttribute("patientPesel") String pesel, @SessionAttribute("logged") String logged) {
		if (bindingResult.hasErrors()) {
			return "addUrine";
		} else {
			PatientDto patientDto = patientService.findByPesel(pesel);

			urineDto.setPatientDto(patientDto);
			if (logged.equals("doctor")) {
				urineDto.setDoctorDto(patientDto.getDoctorDto());
			}
			UrineDto saved = urineService.add(urineDto);
			if (Objects.nonNull(saved)) {
				saved.setPatientDto(patientDto);
				urineService.addDiagnosis(saved);
				JOptionPane.showMessageDialog(null, "Added");
				if (logged.equals("doctor")) {
					return "redirect:/patient/details/" + pesel;
				}
				return "patientMainPage";

			}
			return "addUrine";

		}
	}

	@GetMapping("/showAll")
	public String showAll(@SessionAttribute("patientPesel") String pesel, Model model,
			@SessionAttribute("logged") String logged) {
		List<UrineDto> urineTests = new ArrayList<>();
		PatientDto patientDto = patientService.findByPesel(pesel);
		if (logged.equals("doctor")) {
			urineTests = urineService.findAllByPatientPeselAndDoctorPesel(pesel, patientDto.getDoctorDto().getPesel());

		} else {
			urineTests = urineService.findAllByPatientPeselAndDoctorPesel(pesel, null);

		}
		if (Objects.nonNull(urineTests)) {
			model.addAttribute("tests", urineTests);

			return "urineTests";
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/patient/details/" + pesel;

	}

	@GetMapping("/showTest/{id}")
	public String showTest(@PathVariable("id") Long id, Model model) {
		UrineDto urineDto = urineService.findOne(id);
		if (Objects.nonNull(urineDto)) {
			model.addAttribute("urineTest", urineDto);
			return "patientUrineTest";
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/results/showSharedResults";
	}

	@GetMapping("/delete/{id}")
	public String deleteTest(@PathVariable("id") Long id, @SessionAttribute("patientPesel") String pesel) {
		urineService.delete(id);
		JOptionPane.showMessageDialog(null, "Deleted");
		return "redirect:/patient/details/" + pesel;
	}
}
