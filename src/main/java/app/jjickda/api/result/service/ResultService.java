package app.jjickda.api.result.service;

import app.jjickda.api.exam.dto.response.OptionsDto;
import app.jjickda.api.exam.repository.ExamRepository;
import app.jjickda.api.result.dto.request.ResultRegisterDto;
import app.jjickda.api.result.dto.response.ExamDetailResultDto;
import app.jjickda.api.result.dto.response.ExamResultDto;
import app.jjickda.api.result.repository.ResultRepository;
import app.jjickda.domain.common.dto.response.DefaultResultDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    private final ExamRepository examRepository;

    public ResultService(ResultRepository resultRepository, ExamRepository examRepository) {
        this.resultRepository = resultRepository;
        this.examRepository = examRepository;
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
        List<ExamDetailResultDto> examDetailResultList = resultRepository.selectExamDetailResultList(idx, token);

        List<Long> questionIdxList = new ArrayList<>();
        for (ExamDetailResultDto question : examDetailResultList)
            questionIdxList.add(question.getQuestionIdx());

        List<OptionsDto> optionsList = examRepository.selectOptionsList(questionIdxList);

        for (ExamDetailResultDto repeatedQuestionDto : examDetailResultList) {
            repeatedQuestionDto.setOptionsList(optionsList.stream()
                    .filter(optionsDto -> optionsDto.getQuestionIdx() == repeatedQuestionDto.getQuestionIdx())
                    .collect(Collectors.toList()));
        }

        return examDetailResultList;
    }

    // 비 로그인 결과 저장
    public DefaultResultDto resultRegister(ResultRegisterDto resultRegisterDto) {
        resultRepository.updateUnLoginResultRegister(resultRegisterDto);

        return DefaultResultDto.builder()
                .success(true)
                .message("등록되었습니다.")
                .build();
    }
}
