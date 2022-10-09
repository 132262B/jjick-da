package app.jjickda.api.question.service;

import app.jjickda.api.question.dto.response.QuestionListDto;
import app.jjickda.api.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionListDto> questionSelect() {
        return questionRepository.questionSelect();
    }

    public List<QuestionListDto> termSelect(long questionSeq) {
        return questionRepository.termSelect(questionSeq);
    }

    public List<QuestionListDto> subjectShow(long questionSeq) {
        return questionRepository.subjectShow(questionSeq);
    }
}
