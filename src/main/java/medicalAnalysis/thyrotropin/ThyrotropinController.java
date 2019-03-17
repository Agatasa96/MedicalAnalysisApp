package medicalAnalysis.thyrotropin;

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
@RequestMapping("/thyrotropin")
@SessionAttributes({ "patientPesel", "logged" })
public class ThyrotropinController {

	private final PatientService patientService;
	private final ThyrotropinService thyrotropinService;

	public ThyrotropinController(PatientService patientService, ThyrotropinService thyrotropinService) {
		this.patientService = patientService;
		this.thyrotropinService = thyrotropinService;

	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("thyrotropinDto", new ThyrotropinDto());
		return "addThyrotropin";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("thyrotropinDto") ThyrotropinDto thyrotropinDto,
			BindingResult bindingResult, @SessionAttribute("patientPesel") String pesel,
			@SessionAttribute("logged") String logged) {
		if (bindingResult.hasErrors()) {
			return "addThyrotropin";
		} else {
			PatientDto patientDto = patientService.findByPesel(pesel);
			thyrotropinDto.setPatientDto(patientDto);
			if (logged.equals("doctor")) {
				thyrotropinDto.setDoctorDto(patientDto.getDoctorDto());
			}
			ThyrotropinDto saved = thyrotropinService.add(thyrotropinDto);
			if (Objects.nonNull(saved)) {
				saved.setPatientDto(patientDto);
				thyrotropinService.addDiagnosis(saved);
				JOptionPane.showMessageDialog(null, "Added");
				if (logged.equals("doctor")) {
					return "redirect:/patient/details/" + pesel;
				}
				return "patientMainPage";
			}
			return "addThyrotropin";
		}
	}

	@GetMapping("/showAll")
	public String showAll(@SessionAttribute("patientPesel") String pesel, @SessionAttribute("logged") String logged,
			Model model) {
		List<ThyrotropinDto> thyrotropinTests = new ArrayList<>();
		PatientDto patientDto = patientService.findByPesel(pesel);
		if (logged.equals("doctor")) {
			thyrotropinTests = thyrotropinService.findAllByPatientPeselAndDoctorPesel(pesel,
					patientDto.getDoctorDto().getPesel());

		} else {
			thyrotropinTests = thyrotropinService.findAllByPatientPeselAndDoctorPesel(pesel, null);
		}

		if (Objects.nonNull(thyrotropinTests)) {
			model.addAttribute("tests", thyrotropinTests);

			return "thyrotropinTests";
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/patient/details/" + pesel;

	}

	@GetMapping("/showTest/{id}")
	public String showTest(@PathVariable("id") Long id, Model model) {
		ThyrotropinDto thyrotropinDto = thyrotropinService.findOne(id);
		if (Objects.nonNull(thyrotropinDto)) {
			model.addAttribute("thyrotropinTest", thyrotropinDto);
			return "patientThyrotropinTest";
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/results/showSharedResults";
	}

	@GetMapping("/delete/{id}")
	public String deleteTest(@PathVariable("id") Long id, @SessionAttribute("patientPesel") String pesel) {
		thyrotropinService.delete(id);
		JOptionPane.showMessageDialog(null, "Deleted");
		return "redirect:/patient/details/" + pesel;
	}
}
