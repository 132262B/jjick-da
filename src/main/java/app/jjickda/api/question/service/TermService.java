package app.jjickda.api.question.service;

import app.jjickda.api.question.dto.response.CertificateListDto;
import app.jjickda.api.question.dto.response.ExamListDto;
import app.jjickda.api.question.dto.response.SubjectListDto;
import app.jjickda.api.question.repository.TermRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermService {

    private final TermRepository termRepository;

    public TermService(TermRepository termsRepository) { this.termRepository = termsRepository; }

    public List<CertificateListDto> questionSelect() { return termRepository.questionSelect(); }

    public List<SubjectListDto> subjectSelect(long subIdx) { return termRepository.subjectSelect(subIdx); }

    public List<ExamListDto> examSelect(long subIdx) { return termRepository.examSelect(subIdx); }
}
