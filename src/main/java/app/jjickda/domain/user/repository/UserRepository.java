package app.jjickda.domain.user.repository;

import app.jjickda.domain.user.dto.request.EmailDuplicationDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    // 이메일 중복 체크
    int emailDuplicationCheck(EmailDuplicationDto emailDuplicationDto);

}
