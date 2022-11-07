package app.jjickda.global.component;

import app.jjickda.global.config.model.UpLoadFileInfo;
import app.jjickda.global.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class FileStore {

    @Value("${file.directory}")
    private String FILE_DIRECTORY;


    public UpLoadFileInfo uploadFile(MultipartFile multipartFile) throws IOException {


        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        long fileSize = multipartFile.getSize();
        String serverName = createStoreFileName();
        String fileExtension = this.getExtension(originalFileName);

        this.mkdir(serverName);

        multipartFile.transferTo(new File(getFullFilePath(serverName)));
        return UpLoadFileInfo.builder()
                             .originalName(originalFileName)
                             .serverName(serverName)
                             .extension(fileExtension)
                             .size(fileSize)
                             .build();
    }

    /**
     * 경로를 던지면 폴더가 없을때 폴더를 생성하는 메서드
     */
    private void mkdir(String serverName) {
        String fullPath = getFullFilePath(serverName);
        int listIndex = fullPath.lastIndexOf("/");
        File file = new File(fullPath.substring(0, listIndex));

        if(!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * 오리지널 File 이름을 던지면 확장자를 return
     *
     * @return 파일 확장자
     */
    private String getExtension(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }


    /**
     * File 이름을 던지면 File 전체 경로를 return
     *
     * @return File 경로
     */
    public String getFullFilePath(String serverFileName) {
        try {
            String fileDate = serverFileName.substring(0, 8);
            return FILE_DIRECTORY + this.getFilePath(fileDate) + serverFileName;
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * 오늘 날짜와 시간으로 파일 이름을 생성한다. (자리)
     *
     * @return server에서 사용할 File 이름
     */
    public String createStoreFileName() {

        String newFileName = DateUtil.getDate_YYYYMMDD() + System.currentTimeMillis();
        String newFilePath = this.getFullFilePath(newFileName);

        boolean existenceCheckYn = this.fileExistenceCheck(newFilePath);

        while (existenceCheckYn) {

            // 파일이 존재하면 1 증가 후 다시 체크.
            long filePathLong =  Long.parseLong(newFileName)+1;

            newFileName = String.valueOf(filePathLong);

            newFilePath = this.getFullFilePath(newFileName);
            existenceCheckYn = this.fileExistenceCheck(newFilePath);

        }

        return newFileName;
    }

    /**
     * 서버에서 사용하는 파일 이름을 던지면 이미 존재하는 파일인지 체크.
     *
     * <pre>
     *     존재 하면 : true
     *     존재 하지 않으면 : false
     * </pre>
     *
     * @param serverFileName server에서 사용할 File 이름
     * @return 파일 존재 유무
     */
    public boolean fileExistenceCheck(String serverFileName) {
        File file = new File(this.getFullFilePath(serverFileName));
        return file.exists();
    }

    /**
     * YYYYMMDD 형태로 날짜를 던지면 Path 형태로 return.
     * <pre>
     *  ex) 20221009 -> 2022/10/09/
     * </pre>
     *
     * @param fileDate YYYYMMDD 형태의 날짜
     * @return server에서 사용할 File 이름
     */
    public String getFilePath(String fileDate) {
        if (fileDate.length() != 8) {
            return "";
        }

        return fileDate.substring(0, 4) + "/" + fileDate.substring(4, 6) + "/" + fileDate.substring(6) + "/";
    }
}
