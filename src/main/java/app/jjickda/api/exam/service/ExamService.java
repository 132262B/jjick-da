package app.jjickda.api.exam.service;

import app.jjickda.api.exam.dto.request.ChoiceInfoDto;
import app.jjickda.api.exam.dto.request.SubmitExamDto;
import app.jjickda.api.exam.dto.response.ExamInfoAndQuestionListDto;
import app.jjickda.api.exam.dto.response.OptionsDto;
import app.jjickda.api.exam.dto.response.QuestionDto;
import app.jjickda.api.exam.dto.response.ResultTokenAndIndex;
import app.jjickda.api.exam.repository.ExamRepository;
import app.jjickda.domain.user.dto.response.User;
import app.jjickda.global.config.exception.CustomException;
import app.jjickda.global.config.exception.ErrorCode;
import app.jjickda.global.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    // 시험시작, 시험문제 조회
    public ExamInfoAndQuestionListDto start(ChoiceInfoDto choiceInfoDto) {
        ExamInfoAndQuestionListDto examInfoAndQuestionListDto = new ExamInfoAndQuestionListDto();

        try {
            examInfoAndQuestionListDto.setOngoingExamInfoDto(examRepository.selectOngoingExamInfo(choiceInfoDto));

            int questionNumber = 1;
            for (long subjectIdx : choiceInfoDto.getSubjectIdxArray()) {
                List<QuestionDto> questionList = examRepository.selectQuestionList(choiceInfoDto, subjectIdx);

                if (choiceInfoDto.getExamIdxArray().length != 1) {
                    Collections.shuffle(questionList);
                    long subjectQuestionCnt = examRepository.selectSubjectQuestionCnt(subjectIdx);
                    questionList = questionList.stream().limit(subjectQuestionCnt).collect(Collectors.toList());
                }

                List<Long> questionIdxList = new ArrayList<>();
                for (QuestionDto question : questionList)
                    questionIdxList.add(question.getQuestionIdx());

                List<OptionsDto> optionsList = examRepository.selectOptionsList(questionIdxList);

                for (QuestionDto repeatedQuestionDto : questionList) {
                    repeatedQuestionDto.setOptionsList(optionsList.stream()
                            .filter(optionsDto -> optionsDto.getQuestionIdx() == repeatedQuestionDto.getQuestionIdx())
                            .collect(Collectors.toList()));

                    repeatedQuestionDto.setQuestionNumber(questionNumber++);

                    examInfoAndQuestionListDto.addQuestionList(repeatedQuestionDto);
                }
            }

        } catch (BadSqlGrammarException e) {
            throw new CustomException("임의의 데이터가 입력되었습니다.", ErrorCode.ARBITRARY_DATA_INPUT_SERVER_ERROR);
        }

        return examInfoAndQuestionListDto;
    }

    // 시험결과 등록
    @Transactional
    public ResultTokenAndIndex submit(SubmitExamDto submitExamDto) {

        UUID uuid = UUID.randomUUID();
        try {
            User user = SessionUtil.getUserAttribute();

            examRepository.insertExamAllResult(submitExamDto, user, uuid.toString());
            examRepository.insertExamQuestionResult(submitExamDto);
            examRepository.insertExamSubjectResult(submitExamDto);

            examRepository.updateExamAllResult(submitExamDto.getExamResultIdx(), submitExamDto.getSubCategoryIdx());

        } catch (BadSqlGrammarException e) {
            throw new CustomException("임의의 데이터가 입력되었습니다.", ErrorCode.ARBITRARY_DATA_INPUT_SERVER_ERROR);
        }

        return ResultTokenAndIndex.builder()
                .resultIdx(submitExamDto.getExamResultIdx())
                .token(uuid.toString())
                .build();
    }
}
