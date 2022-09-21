package com.jjickda.domain.exam.repository;

import com.jjickda.domain.exam.dto.response.TestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamRepository {


    TestDto select();

}
