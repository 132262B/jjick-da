package com.jjickda.domain.admin.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminRepository {
    int registMain(@Param(value = "main_name")String main_name);
}
