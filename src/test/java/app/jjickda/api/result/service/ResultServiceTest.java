package app.jjickda.api.result.service;

import app.jjickda.api.exam.dto.response.OptionsDto;
import app.jjickda.api.exam.repository.ExamRepository;
import app.jjickda.api.result.dto.response.ExamAllResultDto;
import app.jjickda.api.result.dto.response.ExamDetailResultDto;
import app.jjickda.api.result.dto.response.ExamResultDto;
import app.jjickda.api.result.dto.response.ExamSubjectResultListDto;
import app.jjickda.api.result.repository.ResultRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@DisplayName("선택 테스트")
@ExtendWith({MockitoExtension.class})
public class ResultServiceTest {

    @Mock
    ResultRepository resultRepository;

    @Mock
    ExamRepository examRepository;

    @InjectMocks
    ResultService resultService;

    private final long TEST_IDX = 486;

    private final String TEST_TOKEN = "9d75a3df-7f39-4ebd-97b0-e94a9460d062";

    private final String EXAM_PASS = "합격";

    private final String EXAM_FAIL = "과락";

    @DisplayName("시험 종료후 전체결과 조회 테스트")
    @Test
    public void resultTest() {

        List<ExamSubjectResultListDto> predictedExamSubjectResultList = Arrays.asList(
                ExamSubjectResultListDto.builder().subjectName("소프트웨어 설계").subjectScore(50).subjectCutOffScore(40).passYn(EXAM_PASS).build(),
                ExamSubjectResultListDto.builder().subjectName("소프트웨어 개발").subjectScore(90).subjectCutOffScore(40).passYn(EXAM_PASS).build(),
                ExamSubjectResultListDto.builder().subjectName("데이터베이스 구축").subjectScore(60).subjectCutOffScore(40).passYn(EXAM_PASS).build(),
                ExamSubjectResultListDto.builder().subjectName("프로그래밍 언어활용").subjectScore(70).subjectCutOffScore(40).passYn(EXAM_PASS).build(),
                ExamSubjectResultListDto.builder().subjectName("정보시스템 구축 관리").subjectScore(100).subjectCutOffScore(40).passYn(EXAM_PASS).build());

        ExamAllResultDto predictedExamAllResultDto = ExamAllResultDto.builder()
                .averageScore(74)
                .passYn(EXAM_PASS)
                .build();

        ExamResultDto predictedExamResultDto = new ExamResultDto();
        predictedExamResultDto.setExamAllResultDto(predictedExamAllResultDto);
        predictedExamResultDto.setExamSubjectResultList(predictedExamSubjectResultList);

        // when
        when(resultRepository.selectExamSubjectResultList(TEST_IDX, TEST_TOKEN)).thenReturn(predictedExamSubjectResultList);
        when(resultRepository.selectExamAllResult(TEST_IDX, TEST_TOKEN)).thenReturn(predictedExamAllResultDto);

        ExamResultDto serviceReturnExamResultDto = resultService.result(TEST_IDX, TEST_TOKEN);

        // then
        Assertions.assertEquals(serviceReturnExamResultDto, predictedExamResultDto);

        verify(resultRepository, times(1)).selectExamSubjectResultList(TEST_IDX, TEST_TOKEN);
        verify(resultRepository, times(1)).selectExamAllResult(TEST_IDX, TEST_TOKEN);
    }

    @DisplayName("시험결과 상세조회 조회 테스트")
    @Test
    public void resultDetailTest() {

        List<ExamDetailResultDto> predictedExamDetailResultList = Arrays.asList(
                ExamDetailResultDto.builder()
                        .questionIdx(223L)
                        .questionName("1번문제")
                        .fileId("202211281669625154904")
                        .subjectName("소프트웨어 설계")
                        .questionNumber(1)
                        .inputAnswer(2)
                        .answerNumber(2)
                        .answerYn("정답")
                        .build(),
                ExamDetailResultDto.builder()
                        .questionIdx(224L)
                        .questionName("2번문제")
                        .fileId("")
                        .subjectName("소프트웨어 개발")
                        .questionNumber(2)
                        .inputAnswer(3)
                        .answerNumber(2)
                        .answerYn("틀림")
                        .build(),
                ExamDetailResultDto.builder()
                        .questionIdx(225L)
                        .questionName("3번문제")
                        .fileId("")
                        .subjectName("데이터베이스 구축")
                        .questionNumber(3)
                        .inputAnswer(4)
                        .answerNumber(4)
                        .answerYn("합격")
                        .build(),
                ExamDetailResultDto.builder()
                        .questionIdx(226L)
                        .questionName("4번문제")
                        .fileId("")
                        .subjectName("프로그래밍 언어활용")
                        .questionNumber(4)
                        .inputAnswer(2)
                        .answerNumber(1)
                        .answerYn("틀림")
                        .build(),
                ExamDetailResultDto.builder()
                        .questionIdx(227L)
                        .questionName("5번문제")
                        .fileId("")
                        .subjectName("정보시스템 구축 관리")
                        .questionNumber(5)
                        .inputAnswer(3)
                        .answerNumber(2)
                        .answerYn("틀림")
                        .build());

        List<Long> predictedQuestionIdxList = new ArrayList<>();
        for (ExamDetailResultDto question : predictedExamDetailResultList)
            predictedQuestionIdxList.add(question.getQuestionIdx());

        List<OptionsDto> predictedOptionsList = Arrays.asList(
                OptionsDto.builder().idx(2031L).questionIdx(223L).optionsNumber(1).optionsContent("1번 문제 1번 선지").build(),
                OptionsDto.builder().idx(2032L).questionIdx(223L).optionsNumber(2).optionsContent("1번 문제 2번 선지").build(),
                OptionsDto.builder().idx(2033L).questionIdx(223L).optionsNumber(3).optionsContent("1번 문제 3번 선지").build(),
                OptionsDto.builder().idx(2034L).questionIdx(223L).optionsNumber(4).optionsContent("1번 문제 4번 선지").build(),

                OptionsDto.builder().idx(2035L).questionIdx(224L).optionsNumber(1).optionsContent("2번 문제 1번 선지").build(),
                OptionsDto.builder().idx(2036L).questionIdx(224L).optionsNumber(2).optionsContent("2번 문제 2번 선지").build(),
                OptionsDto.builder().idx(2037L).questionIdx(224L).optionsNumber(3).optionsContent("2번 문제 3번 선지").build(),
                OptionsDto.builder().idx(2038L).questionIdx(224L).optionsNumber(4).optionsContent("2번 문제 4번 선지").build(),

                OptionsDto.builder().idx(2039L).questionIdx(225L).optionsNumber(1).optionsContent("3번 문제 1번 선지").build(),
                OptionsDto.builder().idx(2040L).questionIdx(225L).optionsNumber(2).optionsContent("3번 문제 2번 선지").build(),
                OptionsDto.builder().idx(2041L).questionIdx(225L).optionsNumber(3).optionsContent("3번 문제 3번 선지").build(),
                OptionsDto.builder().idx(2042L).questionIdx(225L).optionsNumber(4).optionsContent("3번 문제 4번 선지").build(),

                OptionsDto.builder().idx(2043L).questionIdx(226L).optionsNumber(1).optionsContent("4번 문제 1번 선지").build(),
                OptionsDto.builder().idx(2044L).questionIdx(226L).optionsNumber(2).optionsContent("4번 문제 2번 선지").build(),
                OptionsDto.builder().idx(2045L).questionIdx(226L).optionsNumber(3).optionsContent("4번 문제 3번 선지").build(),
                OptionsDto.builder().idx(2046L).questionIdx(226L).optionsNumber(4).optionsContent("4번 문제 4번 선지").build(),

                OptionsDto.builder().idx(2047L).questionIdx(227L).optionsNumber(1).optionsContent("5번 문제 1번 선지").build(),
                OptionsDto.builder().idx(2048L).questionIdx(227L).optionsNumber(2).optionsContent("5번 문제 2번 선지").build(),
                OptionsDto.builder().idx(2049L).questionIdx(227L).optionsNumber(3).optionsContent("5번 문제 3번 선지").build(),
                OptionsDto.builder().idx(2050L).questionIdx(227L).optionsNumber(4).optionsContent("5번 문제 4번 선지").build());


        for (ExamDetailResultDto repeatedQuestionDto : predictedExamDetailResultList) {
            repeatedQuestionDto.setOptionsList(predictedOptionsList.stream()
                    .filter(optionsDto -> optionsDto.getQuestionIdx() == repeatedQuestionDto.getQuestionIdx())
                    .collect(Collectors.toList()));
        }

        // when
        when(resultRepository.selectExamDetailResultList(TEST_IDX, TEST_TOKEN)).thenReturn(predictedExamDetailResultList);
        when(examRepository.selectOptionsList(predictedQuestionIdxList)).thenReturn(predictedOptionsList);

        List<ExamDetailResultDto> serviceReturnDetailResultList = resultService.resultDetail(TEST_IDX, TEST_TOKEN);

        // then
        Assertions.assertEquals(serviceReturnDetailResultList, predictedExamDetailResultList);

        verify(resultRepository, times(1)).selectExamDetailResultList(TEST_IDX, TEST_TOKEN);
        verify(examRepository, times(1)).selectOptionsList(predictedQuestionIdxList);
    }

}
