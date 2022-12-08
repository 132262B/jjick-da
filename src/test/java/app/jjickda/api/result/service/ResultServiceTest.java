package app.jjickda.api.result.service;

import app.jjickda.api.result.dto.response.ExamAllResultDto;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("선택 테스트")
@ExtendWith({MockitoExtension.class})
public class ResultServiceTest {

    @Mock
    ResultRepository resultRepository;

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

}
