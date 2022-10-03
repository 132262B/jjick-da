package com.jjickda.domain.admin.repository;

import com.jjickda.domain.admin.dto.MainQuestionDto;
import com.jjickda.domain.admin.dto.SubQuestionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface AdminRepository {
    int registMain(@Param(value = "main_question") MainQuestionDto main_question);

    ArrayList<MainQuestionDto> getMainList();

    int registSub(@Param(value = "sub_question") SubQuestionDto sub_question);
}
