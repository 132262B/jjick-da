package app.jjickda.api.choice.repository;

import app.jjickda.api.choice.dto.response.CertificateListDto;
import app.jjickda.api.choice.dto.response.ExamListDto;
import app.jjickda.api.choice.dto.response.SubjectListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChoiceRepository {

    List<CertificateListDto> questionChoice();

    List<SubjectListDto> subjectChoice(long subIdx);

    List<ExamListDto> examChoice(long subIdx);
}
