package medicalAnalysis.urine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import medicalAnalysis.main.MainService;
import medicalAnalysis.results.ResultsDto;
import medicalAnalysis.results.ResultsRepository;

@Service
public class UrineService {

	private final UrineRepository urineRepository;
	private final ResultsRepository resultsRepository;
	private final MainService mainService;

	public UrineService(UrineRepository urineRepository, ResultsRepository resultsRepository, MainService mainService) {
		this.urineRepository = urineRepository;
		this.resultsRepository = resultsRepository;
		this.mainService = mainService;
	}

	public UrineDto add(UrineDto urineDto) {
		Urine urine = urineRepository.save(mainService.toDomainUrine(urineDto));
		if (Objects.nonNull(urine)) {
			return mainService.toDtoUrine(urine);
		}
		return null;
	}

	public List<UrineDto> findAllByPatientPeselAndDoctorPesel(String pesel, String doctorPesel) {

		List<Urine> urineTests = urineRepository.findAllByPatientPeselAndDoctorPesel(pesel, doctorPesel);
		if (Objects.nonNull(urineTests)) {
			List<UrineDto> urineDtotests = new ArrayList<>();
			for (Urine u : urineTests) {
				urineDtotests.add(mainService.toDtoUrine(u));
			}
			return urineDtotests;
		}
		return null;

	}

	public UrineDto findOne(Long id) {
		Urine urine = urineRepository.findOne(id);
		if (Objects.nonNull(urine)) {
			return mainService.toDtoUrine(urine);
		}
		return null;
	}

	public void addDiagnosis(UrineDto saved) {
		ResultsDto resultsDto = new ResultsDto();
		resultsDto.setTestName("Urine test");
		resultsDto.setPatientDto(saved.getPatientDto());
		resultsDto.setUrineDto(saved);
		checkColor(saved.getCorrectColor(), resultsDto);
		checkClarity(saved.getCorrectClarity(), resultsDto);
		chceckWeight(saved.getWeight(), resultsDto);
		chceckPh(saved.getPh(), resultsDto);
		checkProtein(saved.getProtein(), resultsDto);
		checkGlucose(saved.getGlucose(), resultsDto);

		if (resultsDto.getDiagnosis() == null) {
			healthy(resultsDto);
		}

	}

	private void healthy(ResultsDto resultsDto) {
		resultsDto.setDiagnosis("Healthy");
		resultsDto.setHealthStatus("Lack");
		resultsRepository.save(mainService.toDomainResults(resultsDto));
	}

	private void checkColor(Boolean color, ResultsDto resultsDto) {
		if (color == false) {
			resultsDto.setDiagnosis("Kidney disease");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Dehydration");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Diabetes");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));

		}
	}

	private void checkClarity(Boolean clarity, ResultsDto resultsDto) {
		if (clarity == false) {
			resultsDto.setDiagnosis("Bacterial infection");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}
	}

	private void chceckWeight(Double weight, ResultsDto resultsDto) {
		if (weight < 1.005) {
			resultsDto.setDiagnosis("Kidney disease");
			resultsDto.setHealthStatus("Medium");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}
	}

	private void chceckPh(Double ph, ResultsDto resultsDto) {
		if (ph > 8) {
			resultsDto.setDiagnosis("Kidney disease");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Asthma");
			resultsDto.setHealthStatus("Medium");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}

		if (ph < 4.6) {
			resultsDto.setDiagnosis("Diabetes");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Dehydration");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Emphysema"); // rozedma
			resultsDto.setHealthStatus("High");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}

	}

	private void checkProtein(Boolean protein, ResultsDto resultsDto) {
		if (protein == true) {
			resultsDto.setDiagnosis("Kidney disease");
			resultsDto.setHealthStatus("Medium");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Diabetes");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Cancer");
			resultsDto.setHealthStatus("High");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Heart disease");
			resultsDto.setHealthStatus("High");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Leukemia");
			resultsDto.setHealthStatus("High");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}
	}

	private void checkGlucose(Boolean glucose, ResultsDto resultsDto) {
		if (glucose == true) {
			resultsDto.setDiagnosis("Kidney disease");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Diabetes");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));

		}
	}

	public void delete(Long id) {
		urineRepository.delete(id);

	}
}
