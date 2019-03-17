package medicalAnalysis.bloodGlucose;

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
@RequestMapping("/bloodGlucose")
@SessionAttributes({ "patientPesel", "logged" })
public class BloodGlucoseController {

	private final PatientService patientService;
	private final BloodGlucoseService bloodGlucoseService;

	public BloodGlucoseController(PatientService patientService, BloodGlucoseService bloodGlucoseService) {
		this.patientService = patientService;
		this.bloodGlucoseService = bloodGlucoseService;

	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("bloodGlucoseDto", new BloodGlucoseDto());
		return "addBloodGlucose";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("bloodGlucoseDto") BloodGlucoseDto bloodGlucoseDto,
			BindingResult bindingResult, @SessionAttribute("patientPesel") String pesel,
			@SessionAttribute("logged") String logged) {
		if (bindingResult.hasErrors()) {
			return "addBloodGlucose";
		} else {
			PatientDto patientDto = patientService.findByPesel(pesel);
			bloodGlucoseDto.setPatientDto(patientDto);
			if (logged.equals("doctor")) {
				bloodGlucoseDto.setDoctorDto(patientDto.getDoctorDto());
			}
			BloodGlucoseDto saved = bloodGlucoseService.add(bloodGlucoseDto);
			if (Objects.nonNull(saved)) {
				saved.setPatientDto(patientDto);
				bloodGlucoseService.addDiagnosis(saved);
				JOptionPane.showMessageDialog(null, "Added");
				if (logged.equals("doctor")) {
					return "redirect:/patient/details/" + pesel;
				}
				return "patientMainPage";
			}
			return "addBloodGlucose";
		}
	}

	@GetMapping("/showAll")
	public String showAll(@SessionAttribute("patientPesel") String pesel, @SessionAttribute("logged") String logged,
			Model model) {
		List<BloodGlucoseDto> bloodGlucoseTests = new ArrayList<>();
		PatientDto patientDto = patientService.findByPesel(pesel);
		if (logged.equals("doctor")) {
			bloodGlucoseTests = bloodGlucoseService.findAllByPatientPeselAndDoctorPesel(pesel,
					patientDto.getDoctorDto().getPesel());

		} else {
			bloodGlucoseTests = bloodGlucoseService.findAllByPatientPeselAndDoctorPesel(pesel, null);
		}
		if (Objects.nonNull(bloodGlucoseTests)) {
			model.addAttribute("tests", bloodGlucoseTests);

			return "bloodGlucoseTests";
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/patient/details/" + pesel;

	}

	@GetMapping("/showTest/{id}")
	public String showTest(@PathVariable("id") Long id, Model model) {
		BloodGlucoseDto bloodGlucoseDto = bloodGlucoseService.findOne(id);
		if (Objects.nonNull(bloodGlucoseDto)) {
			model.addAttribute("glucoseTest", bloodGlucoseDto);
			return "patientGlucoseTest";
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/results/showSharedResults";
	}

	@GetMapping("/delete/{id}")
	public String deleteTest(@PathVariable("id") Long id, @SessionAttribute("patientPesel") String pesel) {
		bloodGlucoseService.delete(id);
		JOptionPane.showMessageDialog(null, "Deleted");
		return "redirect:/patient/details/" + pesel;
	}
}
