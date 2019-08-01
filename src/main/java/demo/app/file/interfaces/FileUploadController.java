package demo.app.file.interfaces;

import demo.app.file.interfaces.dto.FileUploadResponse;
import demo.app.file.service.FileUploadToDiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by itaesu on 01/08/2019.
 */
@RestController @RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadToDiskService fileUploadToDiskService;

    @PostMapping("/uploads")
    public ResponseEntity<FileUploadResponse> upload(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.fileUploadToDiskService.upload(request));
    }
}
