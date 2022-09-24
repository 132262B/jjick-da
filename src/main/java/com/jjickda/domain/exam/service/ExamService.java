package com.jjickda.domain.exam.service;

import com.jjickda.domain.exam.dto.response.TestDto;
import com.jjickda.domain.exam.repository.ExamRepository;
import com.jjickda.global.config.exception.CustomException;
import com.jjickda.global.config.exception.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public TestDto examService() {
        throw new CustomException(ErrorCode.TEST_ERROR);
        //return examRepository.select();
    }

}
