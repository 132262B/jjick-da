package app.jjickda.domain.common.service;

import app.jjickda.domain.common.repository.CommonRepository;
import app.jjickda.domain.user.dto.response.User;
import app.jjickda.global.config.model.UpLoadFileInfo;
import app.jjickda.global.utils.SessionUtil;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    private final CommonRepository commonRepository;

    public CommonService(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
    }


    public UpLoadFileInfo uploadFile(UpLoadFileInfo upLoadFileInfo) {
        User user = SessionUtil.getUserAttribute();
        commonRepository.insertMultiMedia(upLoadFileInfo, user);

        return upLoadFileInfo;
    }
}
