package app.jjickda.domain.common.repository;

import app.jjickda.domain.user.dto.response.User;
import app.jjickda.global.config.model.UpLoadFileInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonRepository {
    void addMultiMedia(UpLoadFileInfo upLoadFileInfo, User user);
}
