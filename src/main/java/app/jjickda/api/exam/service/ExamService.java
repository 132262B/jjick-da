package app.jjickda.api.exam.service;

import app.jjickda.api.exam.dto.request.ChoiceInfoDto;
import app.jjickda.api.exam.dto.response.QuestionListDto;
import app.jjickda.api.exam.repository.ExamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    // 시험문제 조회
    public QuestionListDto examQuestion(ChoiceInfoDto choiceInfoDto) {
        QuestionListDto questionListDto = new QuestionListDto();

        log.debug(choiceInfoDto.toString());

        questionListDto.setExamInfoDto(examRepository.examQuestion(choiceInfoDto));

        return questionListDto;
    }
}
