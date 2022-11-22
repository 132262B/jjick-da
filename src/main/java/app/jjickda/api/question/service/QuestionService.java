package app.jjickda.api.question.service;

import app.jjickda.api.question.dto.response.CertificateListDto;
import app.jjickda.api.question.dto.response.ExamListDto;
import app.jjickda.api.question.dto.response.SubjectListDto;
import app.jjickda.api.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<CertificateListDto> questionSelect() { return questionRepository.questionSelect(); }

    public List<SubjectListDto> subjectSelect(long subIdx) { return questionRepository.subjectSelect(subIdx); }

    public List<ExamListDto> examSelect(long subIdx) { return questionRepository.examSelect(subIdx); }
}
