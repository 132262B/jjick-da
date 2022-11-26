package app.jjickda.api.choice.service;

import app.jjickda.api.choice.dto.response.CertificateListDto;
import app.jjickda.api.choice.dto.response.ExamListDto;
import app.jjickda.api.choice.dto.response.SubjectListDto;
import app.jjickda.api.choice.repository.ChoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {

    private final ChoiceRepository termRepository;

    public ChoiceService(ChoiceRepository termsRepository) { this.termRepository = termsRepository; }

    // 자격증 리스트 조회
    public List<CertificateListDto> questionSelect() { return termRepository.questionChoice(); }

    // 과목 리스트 조회
    public List<SubjectListDto> subjectSelect(long subIdx) { return termRepository.subjectChoice(subIdx); }

    // 회차 리스트 조회
    public List<ExamListDto> examSelect(long subIdx) { return termRepository.examChoice(subIdx); }
}
