package medicalAnalysis.bloodMorphology;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import medicalAnalysis.main.MainService;
import medicalAnalysis.results.ResultsDto;
import medicalAnalysis.results.ResultsRepository;

@Service
public class MorphologyService {

	private final MorphologyRepository morphologyRepository;
	private final ResultsRepository resultsRepository;
	private final MainService mainService;

	public MorphologyService(MorphologyRepository morphologyRepository, ResultsRepository resultsRepository,
			MainService mainService) {
		this.morphologyRepository = morphologyRepository;
		this.resultsRepository = resultsRepository;
		this.mainService = mainService;
	}

	public MorphologyDto add(MorphologyDto morphologyDto) {
		Morphology morphology = morphologyRepository.save(mainService.toDomainMorphology(morphologyDto));
		if (Objects.nonNull(morphology)) {
			return mainService.toDtoMorphology(morphology);
		}
		return null;
	}

	public List<MorphologyDto> findAllByPatientPeselAndDoctorPesel(String pesel, String doctorPesel) {
		List<Morphology> morphologies = morphologyRepository.findAllByPatientPeselAndDoctorPesel(pesel, doctorPesel);
		if (Objects.nonNull(morphologies)) {
			List<MorphologyDto> morphologyDtoTests = new ArrayList<>();
			for (Morphology m : morphologies) {
				morphologyDtoTests.add(mainService.toDtoMorphology(m));
			}
			return morphologyDtoTests;
		}
		return null;

	}

	public MorphologyDto findOne(Long id) {
		Morphology morphology = morphologyRepository.findOne(id);
		if (Objects.nonNull(morphology)) {
			return mainService.toDtoMorphology(morphology);
		}
		return null;
	}

	public void addDiagnosis(MorphologyDto saved) {
		ResultsDto resultsDto = new ResultsDto();
		resultsDto.setTestName("Morphology test");
		resultsDto.setPatientDto(saved.getPatientDto());
		resultsDto.setMorphologyDto(saved);
		checkWBC(saved.getWBC(), resultsDto);
		checkLYMPH(saved.getLYMPH(), resultsDto);
		checkMONO(saved.getMONO(), resultsDto);
		checkEOS(saved.getEOS(), resultsDto);
		checkBASO(saved.getBASO(), resultsDto);

		if (resultsDto.getDiagnosis() == null) {
			healthy(resultsDto);
		}
	}

	private void healthy(ResultsDto resultsDto) {
		resultsDto.setDiagnosis("Healthy");
		resultsDto.setHealthStatus("Lack");
		resultsRepository.save(mainService.toDomainResults(resultsDto));
	}

	private void checkWBC(Double WBC, ResultsDto resultsDto) {
		if (WBC > 10 || WBC < 4) {
			resultsDto.setDiagnosis("Cancer");
			resultsDto.setHealthStatus("High");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Bacterial infection");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}
	}

	private void checkLYMPH(Double LYMPH, ResultsDto resultsDto) {
		if (LYMPH > 4.5) {
			resultsDto.setDiagnosis("Cancer");
			resultsDto.setHealthStatus("High");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Leukemia");
			resultsDto.setHealthStatus("High");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Tuberculosis"); // gruzlica
			resultsDto.setHealthStatus("Medium");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}
	}

	private void checkMONO(Double MONO, ResultsDto resultsDto) {
		if (MONO > 0.8) {
			resultsDto.setDiagnosis("Cancer");
			resultsDto.setHealthStatus("High");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Tuberculosis");
			resultsDto.setHealthStatus("Medium");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}

		if (MONO < 0.3) {
			resultsDto.setDiagnosis("Infection");
			resultsDto.setHealthStatus("Low");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}
	}

	private void checkEOS(Double EOS, ResultsDto resultsDto) {
		if (EOS > 0.3) {
			resultsDto.setDiagnosis("Asthma");
			resultsDto.setHealthStatus("Medium");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}
	}

	private void checkBASO(Double BASO, ResultsDto resultsDto) {
		if (BASO > 0.13) {
			resultsDto.setDiagnosis("Leukemia");
			resultsDto.setHealthStatus("High");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
			resultsDto.setDiagnosis("Thyroid problems");
			resultsDto.setHealthStatus("Medium");
			resultsRepository.save(mainService.toDomainResults(resultsDto));
		}
	}

	public void delete(Long id) {
		morphologyRepository.delete(id);

	}

}
