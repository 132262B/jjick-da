package app.jjickda.api.exam.service;

import app.jjickda.api.exam.dto.response.TestDto;
import app.jjickda.api.exam.repository.ExamRepository;
import app.jjickda.global.config.exception.CustomException;
import app.jjickda.global.config.exception.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public TestDto examService() {
        throw new CustomException("에러이름", ErrorCode.TEST_ERROR);
        //return examRepository.select();
    }

}
