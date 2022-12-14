package app.jjickda.api.choice.service;

import app.jjickda.api.choice.dto.response.CertificateListDto;
import app.jjickda.api.choice.dto.response.ExamListDto;
import app.jjickda.api.choice.dto.response.SubjectListDto;
import app.jjickda.api.choice.repository.ChoiceRepository;
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
public class ChoiceServiceTest {

    @Mock
    ChoiceRepository choiceRepository;

    @InjectMocks
    ChoiceService choiceService;

    private final long SUBJECT_CATEGORY_IDX = 1;

    @DisplayName("자격증 리스트 조회 테스트")
    @Test
    public void selectCertificateListTest() {
        List<CertificateListDto> predictedCertificateList = Arrays.asList(
                CertificateListDto.builder().mainIdx(1L).subIdx(1L).certificateName("기사-정보처리기사").build(),
                CertificateListDto.builder().mainIdx(1L).subIdx(2L).certificateName("기사-정보보안기사").build(),
                CertificateListDto.builder().mainIdx(2L).subIdx(1L).certificateName("기능사-정보처리기능사").build());

        // when
        when(choiceRepository.selectCertificate()).thenReturn(predictedCertificateList);
        List<CertificateListDto> serviceReturnCertificateList = choiceService.certificate();

        // then
        Assertions.assertEquals(serviceReturnCertificateList, predictedCertificateList);

        verify(choiceRepository, times(1)).selectCertificate();
    }

    @DisplayName("과목 리스트 조회 테스트")
    @Test
    public void selectSubjectListTest() {

        List<SubjectListDto> predictedSubjectList = Arrays.asList(
                SubjectListDto.builder().subjectIdx(10L).subjectName("소프트웨어 설계").build(),
                SubjectListDto.builder().subjectIdx(11L).subjectName("소프트웨어 개발").build(),
                SubjectListDto.builder().subjectIdx(12L).subjectName("데이터베이스 구축").build(),
                SubjectListDto.builder().subjectIdx(13L).subjectName("프로그래밍 언어활용").build(),
                SubjectListDto.builder().subjectIdx(14L).subjectName("정보시스템 구축 관리").build());

        // when
        when(choiceRepository.selectSubject(SUBJECT_CATEGORY_IDX)).thenReturn(predictedSubjectList);

        List<SubjectListDto> serviceReturnSubjectList = choiceService.subject(SUBJECT_CATEGORY_IDX);

        // then
        Assertions.assertEquals(serviceReturnSubjectList, predictedSubjectList);

        verify(choiceRepository, times(1)).selectSubject(SUBJECT_CATEGORY_IDX);
    }

    @DisplayName("회차 리스트 조회 테스트")
    @Test
    public void selectExamListTest() {

        List<ExamListDto> predictedExamList = Arrays.asList(
                ExamListDto.builder().examIdx(21L).examName("2021년 1회차").build(),
                ExamListDto.builder().examIdx(22L).examName("2021년 2회차").build(),
                ExamListDto.builder().examIdx(23L).examName("2021년 3회차").build());

        // when
        when(choiceRepository.selectExam(SUBJECT_CATEGORY_IDX)).thenReturn(predictedExamList);

        List<ExamListDto> serviceReturnExamList = choiceService.exam(SUBJECT_CATEGORY_IDX);

        // then
        Assertions.assertEquals(serviceReturnExamList, predictedExamList);

        verify(choiceRepository, times(1)).selectExam(SUBJECT_CATEGORY_IDX);
    }

}
