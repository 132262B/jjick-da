package app.jjickda.api.admin.repository;

import app.jjickda.api.admin.dto.request.AddMainCategoryDto;
import app.jjickda.api.admin.dto.request.AddSubCategoryDto;
import app.jjickda.api.admin.dto.response.GetMainCategoryDto;
import app.jjickda.api.admin.dto.response.GetSubCategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {
    int registMain(AddMainCategoryDto main_question);

    List<GetMainCategoryDto> getMainList();

    int registSub(AddSubCategoryDto sub_question);

    List<GetSubCategoryDto> getSubList();
}
