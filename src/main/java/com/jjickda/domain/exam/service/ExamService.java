package com.jjickda.domain.exam.service;

import com.jjickda.domain.exam.dto.response.TestDto;
import com.jjickda.domain.exam.repository.ExamRepository;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public TestDto examService() {
        return examRepository.select();
    }

}
