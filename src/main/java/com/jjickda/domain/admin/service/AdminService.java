package com.jjickda.domain.admin.service;

import com.jjickda.domain.admin.dto.QuestionAddDto;
import com.jjickda.domain.admin.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public int registMain(QuestionAddDto question) {
        return adminRepository.registMain(question);

    }


}
