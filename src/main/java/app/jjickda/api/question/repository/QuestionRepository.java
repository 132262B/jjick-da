package app.jjickda.api.question.repository;

import app.jjickda.api.question.dto.response.CertificateListDto;
import app.jjickda.api.question.dto.response.ExamListDto;
import app.jjickda.api.question.dto.response.SubjectListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionRepository {

    List<CertificateListDto> questionSelect();

    List<SubjectListDto> subjectSelect(long subIdx);

    List<ExamListDto> examSelect(long subIdx);
}
