package demo.app.file.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 * Created by itaesu on 01/08/2019.
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class FileUploadRequest {
    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return CollectionUtils.isEmpty(files)
                ? Collections.emptyList()
                : files;
    }
}
