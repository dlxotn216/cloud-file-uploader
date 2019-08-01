package demo.app.file.interfaces.dto;

import demo.app.file.domain.model.DomainFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itaesu on 01/08/2019.
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class FileUploadResponse {
    private List<UploadedFile> uploadedFiles = new ArrayList<>();

    @Data @NoArgsConstructor @AllArgsConstructor
    public final static class UploadedFile {

        private String fileName;
        private String diskLocation;
        private String status;
        private String cloudLocation;
    }

    public void addUploadedFile(DomainFile file) {
        this.uploadedFiles.add(new UploadedFile(file.getName(),
                                                file.getDiskLocation(),
                                                file.getStatus().toString(),
                                                file.getCloudLocation()));
    }
}