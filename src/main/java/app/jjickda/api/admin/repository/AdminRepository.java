package app.jjickda.api.admin.repository;

import app.jjickda.api.admin.dto.request.*;
import app.jjickda.api.admin.dto.response.*;
import app.jjickda.domain.user.dto.response.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {

    // 메인 카테고리 등록
    int insertMainCategory(AddMainCategoryDto addMainCategoryDto, User user);

    // 메인 카테고리 조회
    List<GetMainCategoryDto> selectMainCategoryList(String search);

    // 서브 카테고리 등록
    int insertSubCategory(AddSubCategoryDto addSubCategoryDto, User user);

    // 키워드로 서브 카테고리 조회
    List<GetSubCategoryDto> selectSubCategoryListBySearch(String search, String sort);

    // mainIdx로 서브 카테고리 조회
    List<GetSubCategoryDto> selectSubCategoryListByMainIdx(long mainIdx);

    // subIdx로 서브 디테일 조회
    GetSubCategoryDto selectSubDetail(long subIdx);

    // 과목 등록
    int insertSubject(AddSubjectDto addSubjectDto, User user);
    
    // subIdx로 과목 리스트 조회
    List<GetSubjectDto> selectSubjectCategoryListBySubIdx(long subIdx);

    // 시험 정보 등록
    void insertExamInfo(ExamInfo examInfo, User user);

    // 시험문항 등록
    void insertExamQuestions(ExamInfo examInfo, Question question);

    // 시험 선지 등록
    void insertExamOptions(Question question, List<Options> options);

    // subIdx로 선지 개수 조회
    int selectOptionsCnt(long subIdx);

    // subIdx로 과목 정보 조회
    List<SubjectInformationDto> selectSubjectInfo(long subIdx);

    // 키워드로 결재 대기중인 시험 조회
    List<UnconfirmedExamDto> selectConfirmedExamList(String search);

    // 시험 결재
    int updateConfirm(long examIdx);
}
