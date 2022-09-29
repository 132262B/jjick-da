package com.jjickda.domain.admin.repository;

import com.jjickda.domain.admin.dto.QuestionAddDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminRepository {
    int registMain(@Param(value = "question") QuestionAddDto question);
}
