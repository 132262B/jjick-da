package app.jjickda.domain.common.controller;

import app.jjickda.domain.common.service.CommonService;
import app.jjickda.global.component.FileStore;
import app.jjickda.global.config.model.ApiResponse;
import app.jjickda.global.config.model.UpLoadFileInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@Api(tags = "공통 API")
@RestController
@RequestMapping("/api")
public class CommonController {
    private final CommonService service;
    private final FileStore fileStore;

    public CommonController(CommonService service, FileStore fileStore) {
        this.service = service;
        this.fileStore = fileStore;
    }

    @ApiOperation(value = "파일 업로드", notes = "파일업로드용 API.")
    @PostMapping("/upload/file")
    public ResponseEntity<ApiResponse<UpLoadFileInfo>> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        UpLoadFileInfo upLoadFileInfo = fileStore.uploadFile(multipartFile);

        return ResponseEntity.ok(new ApiResponse<>(service.uploadFile(upLoadFileInfo)));
    }

    @ApiOperation(value = "이미지 View", notes = "이미지 View API")
    @GetMapping("/image/{imageNumber}")
    public Resource downloadImage(@ApiParam(name = "이미지 번호 입력", type = "String", required = true) @PathVariable String imageNumber) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullFilePath(imageNumber));
    }
}
