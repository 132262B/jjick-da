package com.jjickda.domain.question.service;

import com.jjickda.domain.question.dto.response.QuestionListDto;
import com.jjickda.domain.question.repository.QuestionRepository;
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
}
