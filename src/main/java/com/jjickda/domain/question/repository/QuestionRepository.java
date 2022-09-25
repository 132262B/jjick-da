package com.jjickda.domain.question.repository;

import com.jjickda.domain.question.dto.response.QuestionListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionRepository {
    List<QuestionListDto> questionSelect();
}
