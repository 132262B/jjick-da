package app.jjickda.api.admin.service;

import app.jjickda.api.admin.dto.SubQuestionDto;
import app.jjickda.api.admin.repository.AdminRepository;
import app.jjickda.api.admin.dto.MainQuestionDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Boolean registMain(MainQuestionDto main_question) {

        int isSuccess = adminRepository.registMain(main_question);
        if(isSuccess == 0) {
            return false;
        }else{
            return true;
        }
    }


    public ArrayList<MainQuestionDto> getMainList() {
        return adminRepository.getMainList();
    }

    public Boolean registSub(SubQuestionDto sub_question) {

        int isSuccess = adminRepository.registSub(sub_question);
        if(isSuccess == 0) {
            return false;
        }else{
            return true;
        }
    }

    public ArrayList<MainQuestionDto> getSubList() {

        return adminRepository.getSubList();
    }
}
