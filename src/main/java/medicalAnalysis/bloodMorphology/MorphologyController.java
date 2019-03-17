package medicalAnalysis.bloodMorphology;

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
@RequestMapping("/morphology")
@SessionAttributes({ "patientPesel", "logged" })
public class MorphologyController {

	private final PatientService patientService;
	private final MorphologyService morphologyService;

	public MorphologyController(PatientService patientService, MorphologyService morphologyService) {
		this.patientService = patientService;
		this.morphologyService = morphologyService;

	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("morphologyDto", new MorphologyDto());
		return "addMorphology";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("morphologyDto") MorphologyDto morphologyDto, BindingResult bindingResult,
			@SessionAttribute("patientPesel") String pesel, @SessionAttribute("logged") String logged) {
		if (bindingResult.hasErrors()) {
			return "addMorphology";
		} else {
			PatientDto patientDto = patientService.findByPesel(pesel);
			morphologyDto.setPatientDto(patientDto);
			if (logged.equals("doctor")) {
				morphologyDto.setDoctorDto(patientDto.getDoctorDto());
			}

			MorphologyDto saved = morphologyService.add(morphologyDto);

			if (Objects.nonNull(saved)) {
				saved.setPatientDto(patientDto);
				morphologyService.addDiagnosis(saved);
				JOptionPane.showMessageDialog(null, "Added");
				if (logged.equals("doctor")) {
					return "redirect:/patient/details/" + pesel;
				}
				return "patientMainPage";
			}
			return "addMorphology";
		}

	}

	@GetMapping("/showAll")
	public String showAll(@SessionAttribute("patientPesel") String pesel, @SessionAttribute("logged") String logged,
			Model model) {
		List<MorphologyDto> morphologyTests = new ArrayList<>();
		PatientDto patientDto = patientService.findByPesel(pesel);
		if (logged.equals("doctor")) {
			morphologyTests = morphologyService.findAllByPatientPeselAndDoctorPesel(pesel,
					patientDto.getDoctorDto().getPesel());

		} else {
			morphologyTests = morphologyService.findAllByPatientPeselAndDoctorPesel(pesel, null);

		}
		if (Objects.nonNull(morphologyTests)) {
			model.addAttribute("tests", morphologyTests);

			return "morphologyTests";
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/patient/details/" + pesel;

	}

	@GetMapping("/showTest/{id}")
	public String showTest(@PathVariable("id") Long id, Model model) {
		MorphologyDto morphologyDto = morphologyService.findOne(id);
		if (Objects.nonNull(morphologyDto)) {
			model.addAttribute("morphologyTest", morphologyDto);
			return "patientMorphologyTest";
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/results/showSharedResults";
	}

	@GetMapping("/delete/{id}")
	public String deleteTest(@PathVariable("id") Long id, @SessionAttribute("patientPesel") String pesel) {
		morphologyService.delete(id);
		JOptionPane.showMessageDialog(null, "Deleted");
		return "redirect:/patient/details/" + pesel;
	}
}
