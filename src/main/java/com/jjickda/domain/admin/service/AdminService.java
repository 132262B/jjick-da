package com.jjickda.domain.admin.service;

import com.jjickda.domain.admin.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public int registMain(String main_name) {
        return adminRepository.registMain(main_name);

    }


}
