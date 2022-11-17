package app.jjickda.api.admin.repository;

import app.jjickda.api.admin.dto.request.AddMainCategoryDto;
import app.jjickda.api.admin.dto.request.AddSubCategoryDto;
import app.jjickda.api.admin.dto.request.AddSubjectDto;
import app.jjickda.api.admin.dto.response.GetMainCategoryDto;
import app.jjickda.api.admin.dto.response.GetSubCategoryDto;
import app.jjickda.api.admin.dto.response.GetSubjectDto;
import app.jjickda.domain.user.dto.response.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminRepository {
    int registMain(AddMainCategoryDto mainQuestion, User user);

    List<GetMainCategoryDto> getMainList();

    int registSub(AddSubCategoryDto subQuestion, User user);

    List<GetSubCategoryDto> getSubList();

    List<GetSubCategoryDto> getSubCategory(long mainIdx);

    GetSubCategoryDto getSubDetail(long idx);

    int registSubject(AddSubjectDto subject, User user);

    List<GetSubjectDto> getSubjectCategory(long subIdx);
}
