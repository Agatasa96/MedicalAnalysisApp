package medicalAnalysis.bloodGlucose;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import medicalAnalysis.main.MainService;
import medicalAnalysis.results.ResultsDto;
import medicalAnalysis.results.ResultsRepository;

@Service
public class BloodGlucoseService {

	private final BloodGlucoseRepository bloodGlucoseRepository;
	private final ResultsRepository resultsRepository;
	private final MainService mainService;

	public BloodGlucoseService(BloodGlucoseRepository bloodGlucoseRepository, ResultsRepository resultsRepository,
			MainService mainService) {
		this.bloodGlucoseRepository = bloodGlucoseRepository;
		this.resultsRepository = resultsRepository;
		this.mainService = mainService;
	}

	public BloodGlucoseDto add(BloodGlucoseDto bloodGlucoseDto) {
		BloodGlucose bloodGlucose = bloodGlucoseRepository.save(mainService.toDomainGlucose(bloodGlucoseDto));
		if (Objects.nonNull(bloodGlucose)) {
			return mainService.toDtoGlucose(bloodGlucose);
		}
		return null;
	}

	public List<BloodGlucoseDto> findAllByPatientPeselAndDoctorPesel(String pesel, String doctorPesel) {
		List<BloodGlucose> bloodGlucoses = bloodGlucoseRepository.findAllByPatientPeselAndDoctorPesel(pesel,
				doctorPesel);
		if (Objects.nonNull(bloodGlucoses)) {
			List<BloodGlucoseDto> bloodGlucoseTests = new ArrayList<>();
			for (BloodGlucose b : bloodGlucoses) {
				bloodGlucoseTests.add(mainService.toDtoGlucose(b));
			}
			return bloodGlucoseTests;
		}
		return null;
	}

	public BloodGlucoseDto findOne(Long id) {
		BloodGlucose bloodGlucose = bloodGlucoseRepository.findOne(id);
		if (Objects.nonNull(bloodGlucose)) {
			return mainService.toDtoGlucose(bloodGlucose);
		}
		return null;
	}

	public void addDiagnosis(BloodGlucoseDto saved) {
		ResultsDto resultsDto = new ResultsDto();
		resultsDto.setTestName("Glucose level");
		resultsDto.setBloodGlucoseDto(saved);
		resultsDto.setPatientDto(saved.getPatientDto());

		if (saved.getEmptyStomach() == true) {
			checkGlucoseLevelOnEmptyStomach(saved.getGlucoseLevel(), resultsDto);
		} else {
			checkGlucoseLevel(saved.getGlucoseLevel(), resultsDto);
		}

		if (resultsDto.getDiagnosis() == null) {
			healthy(resultsDto);
		}
	}

	private void checkGlucoseLevelOnEmptyStomach(Double glucoseLevel, ResultsDto resultsDto) {
		if (glucoseLevel >= 100 || glucoseLevel <= 125) {
			resultsDto.setDiagnosis("Irregular sugar level");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));

		}

		if (glucoseLevel > 125) {
			resultsDto.setDiagnosis("Diabetes");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));

		}

	}

	private void checkGlucoseLevel(Double glucoseLevel, ResultsDto resultsDto) {
		if (glucoseLevel >= 200) {
			resultsDto.setDiagnosis("Diabetes");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));

		}

	}

	private void healthy(ResultsDto resultsDto) {
		resultsDto.setDiagnosis("Healthy");
		resultsDto.setHealthStatus("Lack");
		resultsRepository.save(mainService.toDomainResults(resultsDto));
	}

	public void delete(Long id) {
		bloodGlucoseRepository.delete(id);

	}

}
