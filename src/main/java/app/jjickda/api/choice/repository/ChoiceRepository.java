package app.jjickda.api.choice.repository;

import app.jjickda.api.choice.dto.response.CertificateListDto;
import app.jjickda.api.choice.dto.response.ExamListDto;
import app.jjickda.api.choice.dto.response.SubjectListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChoiceRepository {

    // 자격증 리스트 조회
    List<CertificateListDto> selectCertificate();

    // 과목 리스트 조회
    List<SubjectListDto> selectSubject(long subIdx);

    // 회차 리스트 조회
    List<ExamListDto> selectExam(long subIdx);
}
