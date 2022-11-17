package app.jjickda.api.admin.service;

import app.jjickda.api.admin.dto.request.AddSubCategoryDto;
import app.jjickda.api.admin.dto.request.AddSubjectDto;
import app.jjickda.api.admin.dto.response.GetMainCategoryDto;
import app.jjickda.api.admin.dto.response.GetSubCategoryDto;
import app.jjickda.api.admin.dto.response.GetSubjectDto;
import app.jjickda.api.admin.repository.AdminRepository;
import app.jjickda.api.admin.dto.request.AddMainCategoryDto;
import app.jjickda.domain.common.dto.response.DefaultResultDto;
import app.jjickda.domain.user.dto.response.User;
import app.jjickda.global.utils.SessionUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // 메인 카테고리 등록
     public DefaultResultDto registMain(AddMainCategoryDto main_question) {
        User user = SessionUtil.getUserAttribute();
        adminRepository.registMain(main_question, user);
        return DefaultResultDto.builder()
                .message("1건의 메인 카테고리가 등록 되었습니다.")
                .success(true)
                .build();
    }

    // 메인 카테고리 리스트
    public List<GetMainCategoryDto> getMainList() {
        return adminRepository.getMainList();
    }


    public DefaultResultDto registSub(AddSubCategoryDto sub_question) {
        User user = SessionUtil.getUserAttribute();
        adminRepository.registSub(sub_question, user);
        return DefaultResultDto.builder()
                .message("1건의 서브 카테고리가 등록 되었습니다.")
                .success(true)
                .build();
    }

    public DefaultResultDto registSubject(AddSubjectDto subject) {
        User user = SessionUtil.getUserAttribute();
        adminRepository.registSubject(subject, user);
        return DefaultResultDto.builder()
                .message("1건의 과목이 등록 되었습니다.")
                .success(true)
                .build();
    }

    public List<GetSubCategoryDto> getSubList() {
        return adminRepository.getSubList();
    }

    public List<GetSubCategoryDto> getSubList(long mainIdx) {
        return adminRepository.getSubCategory(mainIdx);
    }

    public GetSubCategoryDto getSubDetail(long idx) {
        return adminRepository.getSubDetail(idx);
    }

    public List<GetSubjectDto> getSubjectCategory(long subIdx) {
        return adminRepository.getSubjectCategory(subIdx);
    }
}
