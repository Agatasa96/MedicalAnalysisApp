package medicalAnalysis.results;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Service;
import medicalAnalysis.main.MainService;

@Service
public class ResultsService {

	private final ResultsRepository resultsRepository;
	private final MainService mainService;

	public ResultsService(ResultsRepository resultsRepository, MainService mainService) {
		this.resultsRepository = resultsRepository;
		this.mainService = mainService;

	}

	public List<ResultsDto> findAllByUrineId(Long id) {
		List<Results> results = resultsRepository.findAllByUrineId(id);
		if (Objects.nonNull(results)) {
			List<ResultsDto> resultsDto = new ArrayList<>();
			for (Results r : results) {
				resultsDto.add(mainService.toDtoResults(r));
			}
			return resultsDto;
		}
		return null;
	}

	public ResultsDto shareResult(Long id) {
		Results result = resultsRepository.findOne(id);
		result.setShared(true);
		Results saved = resultsRepository.save(result);
		if (Objects.nonNull(saved)) {
			return mainService.toDtoResults(saved);
		}
		return null;
	}

	public Object mostCommonDiagnosis(List<ResultsDto> results) {
		List<String> diagnosisList = new ArrayList<>();
		for (ResultsDto r : results) {
			diagnosisList.add(r.getDiagnosis());
		}
		Map<String, Integer> wordMap = new HashMap<String, Integer>();

		for (String st : diagnosisList) {
			String input = st.toUpperCase();
			if (wordMap.get(input) != null) {
				Integer count = wordMap.get(input) + 1;
				wordMap.put(input, count);
			} else {
				wordMap.put(input, 1);
			}
		}

		Object maxEntry = Collections.max(wordMap.entrySet(), Map.Entry.comparingByValue()).getKey();

		return maxEntry;
	}

	public List<ResultsDto> findAllByPatientPeselAndShared(String pesel, boolean b) {
		List<Results> results = resultsRepository.findAllByPatientPeselAndShared(pesel, b);
		if (Objects.nonNull(results)) {
			List<ResultsDto> resultsDto = new ArrayList<>();
			for (Results r : results) {
				resultsDto.add(mainService.toDtoResults(r));
			}
			return resultsDto;
		}
		return null;
	}

	public ResultsDto findOneById(Long id) {
		Results results = resultsRepository.findOne(id);
		if (Objects.nonNull(results)) {
			return mainService.toDtoResults(results);
		}
		return null;
	}

	public List<ResultsDto> findAllByMorphologyId(Long id) {
		List<Results> results = resultsRepository.findAllByMorphologyId(id);

		if (Objects.nonNull(results)) {
			List<ResultsDto> resultsDto = new ArrayList<>();
			for (Results r : results) {
				resultsDto.add(mainService.toDtoResults(r));
			}
			return resultsDto;
		}
		return null;
	}

	public List<ResultsDto> findAllByBloodGlucoseId(Long id) {
		List<Results> results = resultsRepository.findAllByBloodGlucoseId(id);

		if (Objects.nonNull(results)) {

			List<ResultsDto> resultsDto = new ArrayList<>();
			for (Results r : results) {
				resultsDto.add(mainService.toDtoResults(r));
			}
			return resultsDto;
		}
		return null;
	}

	public List<ResultsDto> findAllByThyrotropinId(Long id) {
		List<Results> results = resultsRepository.findAllByThyrotropinId(id);

		if (Objects.nonNull(results)) {

			List<ResultsDto> resultsDto = new ArrayList<>();
			for (Results r : results) {
				resultsDto.add(mainService.toDtoResults(r));
			}
			return resultsDto;
		}
		return null;
	}

}
