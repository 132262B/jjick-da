package app.jjickda.api.choice.service;

import app.jjickda.api.choice.dto.response.CertificateListDto;
import app.jjickda.api.choice.dto.response.ExamListDto;
import app.jjickda.api.choice.dto.response.SubjectListDto;
import app.jjickda.api.choice.repository.ChoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {

    private final ChoiceRepository choiceRepository;

    public ChoiceService(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    // 자격증 리스트 조회
    public List<CertificateListDto> certificate() {
        return choiceRepository.selectCertificate();
    }

    // 과목 리스트 조회
    public List<SubjectListDto> subject(long subIdx) {
        return choiceRepository.selectSubject(subIdx);
    }

    // 회차 리스트 조회
    public List<ExamListDto> exam(long subIdx) {
        return choiceRepository.selectExam(subIdx);
    }
}
