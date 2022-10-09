package app.jjickda.api.exam.repository;

import app.jjickda.api.exam.dto.response.TestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamRepository {


    TestDto select();

}
