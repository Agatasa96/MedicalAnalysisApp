package medicalAnalysis.thyrotropin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import medicalAnalysis.main.MainService;
import medicalAnalysis.results.ResultsDto;
import medicalAnalysis.results.ResultsRepository;

@Service
public class ThyrotropinService {

	private final ThyrotropinRepository thyrotropinRepository;
	private final ResultsRepository resultsRepository;
	private final MainService mainService;

	public ThyrotropinService(ThyrotropinRepository thyrotropinRepository, ResultsRepository resultsRepository,
			MainService mainService) {
		this.thyrotropinRepository = thyrotropinRepository;
		this.resultsRepository = resultsRepository;
		this.mainService = mainService;
	}

	public ThyrotropinDto add(ThyrotropinDto thyrotropinDto) {
		Thyrotropin thyrotropin = thyrotropinRepository.save(mainService.toDomainThyrotropin(thyrotropinDto));
		if (Objects.nonNull(thyrotropin)) {
			return mainService.toDtoThyrotropin(thyrotropin);
		}
		return null;
	}

	public List<ThyrotropinDto> findAllByPatientPeselAndDoctorPesel(String pesel, String doctorPesel) {
		List<Thyrotropin> thyrotropins = thyrotropinRepository.findAllByPatientPeselAndDoctorPesel(pesel, doctorPesel);
		if (Objects.nonNull(thyrotropins)) {
			List<ThyrotropinDto> thyrotropinTest = new ArrayList<>();
			for (Thyrotropin t : thyrotropins) {
				thyrotropinTest.add(mainService.toDtoThyrotropin(t));
			}
			return thyrotropinTest;
		}
		return null;
	}

	public ThyrotropinDto findOne(Long id) {
		Thyrotropin thyrotropin = thyrotropinRepository.findOne(id);
		if (Objects.nonNull(thyrotropin)) {
			return mainService.toDtoThyrotropin(thyrotropin);
		}
		return null;
	}

	public void addDiagnosis(ThyrotropinDto saved) {
		ResultsDto resultsDto = new ResultsDto();
		resultsDto.setThyrotropinDto(saved);
		resultsDto.setTestName("TSH test");
		resultsDto.setPatientDto(saved.getPatientDto());
		checkTSHparameter(saved.getTSH(), resultsDto);
	}

	private void checkTSHparameter(Double TSH, ResultsDto resultsDto) {
		if (TSH > 4) {
			resultsDto.setDiagnosis("Hypothyroidism"); // niedoczynnosc tarczycy
			resultsDto.setHealthStatus("Medium");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		} else if (TSH < 0.4) {
			resultsDto.setDiagnosis("Hyperthyroidism"); // nadczynnosc tarczycy
			resultsDto.setHealthStatus("Medium");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		} else {
			resultsDto.setDiagnosis("Healthy");
			resultsDto.setHealthStatus("Lack");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}
	}

	public void delete(Long id) {
		thyrotropinRepository.delete(id);

	}

}
