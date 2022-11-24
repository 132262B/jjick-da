package app.jjickda.domain.common.controller;

import app.jjickda.domain.common.dto.response.DefaultResultDto;
import app.jjickda.domain.common.service.CommonService;
import app.jjickda.global.component.FileStore;
import app.jjickda.global.config.model.ApiResponse;
import app.jjickda.global.config.model.UpLoadFileInfo;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.net.MalformedURLException;

@ApiIgnore
@RestController
@RequestMapping("/api")
public class ViewController {
    private final CommonService service;
    private final FileStore fileStore;

    public ViewController(CommonService service, FileStore fileStore) {
        this.service = service;
        this.fileStore = fileStore;
    }

    @PostMapping("/upload/file")
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        UpLoadFileInfo upLoadFileInfo = fileStore.uploadFile(multipartFile);

        return ResponseEntity.ok(new ApiResponse<>(service.addMultiMedia(upLoadFileInfo)));
    }

    @GetMapping("/image/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullFilePath(filename));
    }
}
