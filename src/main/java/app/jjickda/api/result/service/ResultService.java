package app.jjickda.api.result.service;

import app.jjickda.api.result.dto.response.ExamResultDto;
import app.jjickda.api.result.repository.ResultRepository;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }


    // 시험 결과 조회
    public ExamResultDto result(long idx, String token) {
        ExamResultDto examResultDto = new ExamResultDto();

        examResultDto.setExamSubjectResultList(resultRepository.selectExamSubjectResultList(idx, token));

        examResultDto.setExamAllResultDto(resultRepository.selectExamAllResult(idx, token));

        return examResultDto;
    }
}
