package app.jjickda.api.result.service;

import app.jjickda.api.result.dto.response.ExamDetailResultDto;
import app.jjickda.api.result.dto.response.ExamResultDto;
import app.jjickda.api.result.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }


    // 시험결과 조회
    public ExamResultDto result(long idx, String token) {
        ExamResultDto examResultDto = new ExamResultDto();

        examResultDto.setExamSubjectResultList(resultRepository.selectExamSubjectResultList(idx, token));

        examResultDto.setExamAllResultDto(resultRepository.selectExamAllResult(idx, token));

        return examResultDto;
    }

    // 시험결과 상세조회
    public List<ExamDetailResultDto> resultDetail(long idx, String token) {
        return resultRepository.selectExamDetailResultList(idx, token);
    }
}
