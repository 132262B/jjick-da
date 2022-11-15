package app.jjickda.api.admin.service;

import app.jjickda.api.admin.dto.request.AddSubCategoryDto;
import app.jjickda.api.admin.dto.response.GetMainCategoryDto;
import app.jjickda.api.admin.dto.response.GetSubCategoryDto;
import app.jjickda.api.admin.repository.AdminRepository;
import app.jjickda.api.admin.dto.request.AddMainCategoryDto;
import app.jjickda.domain.common.dto.response.DefaultResultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public DefaultResultDto registMain(AddMainCategoryDto main_question) {
        adminRepository.registMain(main_question);
        return DefaultResultDto.builder()
                .message("1건이 등록 되었습니다.")
                .success(true)
                .build();
    }


    public List<GetMainCategoryDto> getMainList() {
        return adminRepository.getMainList();
    }

    public DefaultResultDto registSub(AddSubCategoryDto sub_question) {

        adminRepository.registSub(sub_question);
        return DefaultResultDto.builder()
                .message("1건이 등록 되었습니다.")
                .success(true)
                .build();
        }

    public List<GetSubCategoryDto> getSubList() {
        return adminRepository.getSubList();
    }

    public GetSubCategoryDto getSubDetail(long subIdx) {
        return adminRepository.getSubDetail(subIdx);
    }
}
