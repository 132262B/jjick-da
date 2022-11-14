package app.jjickda.api.question.repository;

import app.jjickda.api.question.dto.response.CertificateListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionRepository {

    List<CertificateListDto> questionSelect();

}
