package app.jjickda.api.admin.service;

import app.jjickda.api.admin.dto.request.*;
import app.jjickda.api.admin.dto.response.*;
import app.jjickda.api.admin.repository.AdminRepository;
import app.jjickda.domain.common.dto.response.DefaultResultDto;
import app.jjickda.domain.user.dto.response.User;
import app.jjickda.global.config.exception.CustomException;
import app.jjickda.global.config.exception.ErrorCode;
import app.jjickda.global.utils.SessionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // 메인 카테고리 등록
    public DefaultResultDto addMainCategory(AddMainCategoryDto addMainCategoryDto) {
        User user = SessionUtil.getUserAttribute();
        adminRepository.insertMainCategory(addMainCategoryDto, user);
        return DefaultResultDto.builder()
                .message("1건의 메인 카테고리가 등록 되었습니다.")
                .success(true)
                .build();
    }

    // 메인 카테고리 리스트
    public List<GetMainCategoryDto> getMainCategoryList(String search) {
        return adminRepository.selectMainCategoryList(search);
    }

    // 서브 카테고리 등록
    public DefaultResultDto addSubCategory(AddSubCategoryDto addSubCategoryDto) {
        User user = SessionUtil.getUserAttribute();
        adminRepository.insertSubCategory(addSubCategoryDto, user);
        return DefaultResultDto.builder()
                .message("1건의 서브 카테고리가 등록 되었습니다.")
                .success(true)
                .build();
    }

    // 과목 등록
    public DefaultResultDto addSubject(AddSubjectDto addSubjectDto) {
        User user = SessionUtil.getUserAttribute();
        adminRepository.insertSubject(addSubjectDto, user);

        return DefaultResultDto.builder()
                .message("1건의 과목이 등록 되었습니다.")
                .success(true)
                .build();
    }

    // 키워드로 서브 카테고리 리스트
    public List<GetSubCategoryDto> getSubCategoryList(String search, String sort) {
        return adminRepository.selectSubCategoryListBySearch(search, sort);
    }

    // mainIdx로 서브 카테고리 조회
    public List<GetSubCategoryDto> getSubCategoryList(long mainIdx) {
        return adminRepository.selectSubCategoryListByMainIdx(mainIdx);
    }

    // 서브 카테고리 디테일
    public GetSubCategoryDto getSubDetail(long subIdx) {
        return adminRepository.selectSubDetail(subIdx);
    }

    // subCategoryIdx로 과목 조회
    public List<GetSubjectDto> getSubjectCategoryList(long subIdx) {
        return adminRepository.selectSubjectCategoryListBySubIdx(subIdx);
    }


    // 문제 등록
    @Transactional
    public DefaultResultDto addExam(AddExamDto addExamDto) {

        ExamInfo examInfo = addExamDto.getExamInfo();
        User user = SessionUtil.getUserAttribute();

        // 시험 정보 등록(TB_EXAM)
        adminRepository.insertExamInfo(examInfo, user);

        List<Question> questions = addExamDto.getQuestions();

        // 선지개수가 모두 일치하는지 검증하는 데이터
        int beforeOptionsCnt = 0;
        boolean optionsFlag = true;

        for (Question question : questions) {

            // 시험 문항 등록(TB_EXAM_QUESTION)
            adminRepository.insertExamQuestions(examInfo, question);

            // 시험 선지 등록(TB_EXAM_OPTIONS)
            List<Options> options = question.getOptions();

            // 선지개수가 모두 일치하는지 검증하는 로직
            if (optionsFlag) {
                beforeOptionsCnt = options.size();
                optionsFlag = false;
            } else {
                int currentOptionsCnt = options.size();
                if (beforeOptionsCnt != currentOptionsCnt)
                    throw new CustomException("선지갯수가 모두 일치하지 않습니다.", ErrorCode.NO_MATCH_OPTION_CNT_ERROR);
            }

            adminRepository.insertExamOptions(question, options);
        }

        return DefaultResultDto.builder()
                .message("1건의 문항이 등록되었습니다.")
                .success(true)
                .build();
    }

    // 시험 정보
    public ExamInformationDto getExamInformation(long subIdx) {
        ExamInformationDto examInfo = new ExamInformationDto();
        examInfo.setOptionsCnt(adminRepository.selectOptionsCnt(subIdx));
        examInfo.setSubjectInformation(adminRepository.selectSubjectInfo(subIdx));

        return examInfo;
    }

    // 결재 대기중인 시험
    public List<UnconfirmedExamDto> getConfirmedExamList(String search) {
        return adminRepository.selectConfirmedExamList(search);
    }

    // 시험 결재
    public DefaultResultDto putConfirmExam(List<Long> examIdx) {
        for (long i : examIdx) {
            int a = adminRepository.updateConfirm(i);
        }
        return DefaultResultDto.builder()
                .message("선택된 시험이 결재 되었습니다.")
                .success(true)
                .build();
    }
}
