package medicalAnalysis.results;

import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/results")
@SessionAttributes({ "patientPesel", "doctorDto", "logged" })
public class ResultsController {

	private final ResultsService resultsService;

	public ResultsController(ResultsService resultsService) {
		this.resultsService = resultsService;
	}

	@GetMapping("/add/{pesel}")
	public String addPanel(@PathVariable("pesel") String pesel, Model model) {
		model.addAttribute("patientPesel", pesel);
		return "addResultPanel";
	}

	@GetMapping("/showResultsPanel/{pesel}")
	public String showResultsPanel(@PathVariable("pesel") String pesel, Model model) {
		model.addAttribute("patientPesel", pesel);
		return "showResultsPanel";
	}

	@GetMapping("/show/{id}")
	public String showUrine(@PathVariable("id") Long id, Model model, @SessionAttribute("logged") String logged) {
		List<ResultsDto> results = resultsService.findAllByUrineId(id);
		if (Objects.nonNull(results)) {
			model.addAttribute("results", results);
			Object mostCommon = resultsService.mostCommonDiagnosis(results);
			model.addAttribute("mostCommon", mostCommon);
			if (logged.equals("doctor")) {
				return "diagnosis";
			} else {
				return "patientDiagnosis";
			}
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/urine/showAll";
	}

	@GetMapping("/share/{id}")
	public String shareResult(@PathVariable("id") Long id, @SessionAttribute("patientPesel") String pesel) {
		ResultsDto resultsDto = resultsService.shareResult(id);

		if (Objects.nonNull(resultsDto)) {

			JOptionPane.showMessageDialog(null, "Shared");
			return "redirect:/patient/details/" + pesel;
		}
		JOptionPane.showMessageDialog(null, "Cannot share");
		return "redirect:/patient/details/" + pesel;
	}

	@GetMapping("/showSharedResults")
	public String showSharedResults(@SessionAttribute("patientPesel") String pesel, Model model) {

		List<ResultsDto> resultsDto = resultsService.findAllByPatientPeselAndShared(pesel, true);
		if (Objects.nonNull(resultsDto)) {
			model.addAttribute("results", resultsDto);
			return "showSharedResults";
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "patientMainPage";
	}

	@GetMapping("/showTest/{id}")
	public String showTests(@PathVariable("id") Long id) {
		ResultsDto resultsDto = resultsService.findOneById(id);
		if (Objects.nonNull(resultsDto)) {
			if (Objects.nonNull(resultsDto.getUrineDto())) {
				return "redirect:/urine/showTest/" + resultsDto.getUrineDto().getId();
			}
			if (Objects.nonNull(resultsDto.getMorphologyDto())) {
				return "redirect:/morphology/showTest/" + resultsDto.getMorphologyDto().getId();
			}
			if (Objects.nonNull(resultsDto.getBloodGlucoseDto())) {
				return "redirect:/bloodGlucose/showTest/" + resultsDto.getBloodGlucoseDto().getId();

			}
			if (Objects.nonNull(resultsDto.getThyrotropinDto())) {
				return "redirect:/thyrotropin/showTest/" + resultsDto.getThyrotropinDto().getId();

			}
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/results/showSharedResults";
	}

	@GetMapping("/showMorphology/{id}")
	public String showMorphology(@PathVariable("id") Long id, Model model, @SessionAttribute("logged") String logged) {

		List<ResultsDto> results = resultsService.findAllByMorphologyId(id);
		if (Objects.nonNull(results)) {
			model.addAttribute("results", results);
			Object mostCommon = resultsService.mostCommonDiagnosis(results);
			model.addAttribute("mostCommon", mostCommon);
			if (logged.equals("doctor")) {
				return "diagnosis";
			} else {
				return "patientDiagnosis";
			}
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/morphology/showAll";

	}

	@GetMapping("/showGlucose/{id}")
	public String showGlucose(@PathVariable("id") Long id, Model model, @SessionAttribute("logged") String logged) {

		List<ResultsDto> results = resultsService.findAllByBloodGlucoseId(id);
		if (Objects.nonNull(results)) {

			model.addAttribute("results", results);
			Object mostCommon = resultsService.mostCommonDiagnosis(results);
			model.addAttribute("mostCommon", mostCommon);
			if (logged.equals("doctor")) {
				return "diagnosis";
			} else {
				return "patientDiagnosis";
			}
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/bloodGlucose/showAll";

	}

	@GetMapping("/showThyrotropin/{id}")
	public String showThyrotropin(@PathVariable("id") Long id, Model model, @SessionAttribute("logged") String logged) {

		List<ResultsDto> results = resultsService.findAllByThyrotropinId(id);
		if (Objects.nonNull(results)) {

			model.addAttribute("results", results);
			Object mostCommon = resultsService.mostCommonDiagnosis(results);
			model.addAttribute("mostCommon", mostCommon);
			if (logged.equals("doctor")) {
				return "diagnosis";
			} else {
				return "patientDiagnosis";
			}
		}
		JOptionPane.showMessageDialog(null, "Cannot load");
		return "redirect:/bloodGlucose/showAll";

	}

}
