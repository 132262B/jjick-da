package app.jjickda.api.exam.service;

import app.jjickda.api.exam.dto.request.ChoiceInfoDto;
import app.jjickda.api.exam.dto.response.ExamInfoAndQuestionListDto;
import app.jjickda.api.exam.dto.response.OptionsDto;
import app.jjickda.api.exam.dto.response.QuestionDto;
import app.jjickda.api.exam.repository.ExamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    // 시험문제 조회
    public ExamInfoAndQuestionListDto examQuestion(ChoiceInfoDto choiceInfoDto) {

        ExamInfoAndQuestionListDto examInfoAndQuestionListDto = new ExamInfoAndQuestionListDto();

        examInfoAndQuestionListDto.setOngoingExamInfoDto(examRepository.selectOngoingExamInfo(choiceInfoDto));

        int questionNumber = 1;
        for (long subjectIdx : choiceInfoDto.getSubjectIdxArray()) {
            List<QuestionDto> questionListDto = examRepository.selectQuestionList(choiceInfoDto, subjectIdx);

            Collections.shuffle(questionListDto);
            long subjectQuestionCnt = examRepository.selectSubjectQuestionCnt(subjectIdx);
            questionListDto = questionListDto.stream().limit(subjectQuestionCnt).collect(Collectors.toList());

            List<OptionsDto> optionsDtoList = examRepository.selectOptionsList(questionListDto);

            for (QuestionDto repeatedQuestionDto : questionListDto) {
                repeatedQuestionDto.setOptionsList(optionsDtoList.stream()
                        .filter(optionsDto -> optionsDto.getQuestionIdx() == repeatedQuestionDto.getQuestionIdx())
                        .collect(Collectors.toList()));

                repeatedQuestionDto.setQuestionNumber(questionNumber++);

                examInfoAndQuestionListDto.addQuestionList(repeatedQuestionDto);
            }
        }

        return examInfoAndQuestionListDto;
    }
}
