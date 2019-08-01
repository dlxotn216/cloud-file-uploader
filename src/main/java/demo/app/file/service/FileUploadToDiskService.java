package demo.app.file.service;

import demo.app.file.domain.model.DomainFile;
import demo.app.file.domain.repository.DomainFileRepository;
import demo.app.file.interfaces.dto.FileUploadResponse;
import demo.app.file.utils.DomainFileTuils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by itaesu on 01/08/2019.
 */
@Slf4j
@Component @RequiredArgsConstructor
public class FileUploadToDiskService {

    private final ServletContext servletContext;
    private final DomainFileRepository domainFileRepository;

    public FileUploadResponse upload(HttpServletRequest request) {
        ServletFileUpload upload = new ServletFileUpload();
        FileUploadResponse response = new FileUploadResponse();

        try {
            FileItemIterator iterStream = upload.getItemIterator(request);
            while (iterStream.hasNext()) {
                FileItemStream item = iterStream.next();
                if (item.isFormField()) {
                    continue;
                }

                final String id = DomainFileTuils.getEncryptedFileName(item.getName());
                final String diskLocation = copyToDisk(id, item);

                final DomainFile build = this.domainFileRepository.save(DomainFile.builder().id(id)
                                                                                  .name(item.getName())
                                                                                  .diskLocation(diskLocation)
                                                                                  .build());
                response.addUploadedFile(build);
            }

            return response;
        } catch (IOException | FileUploadException e) {
            throw new IllegalStateException(e);
        }
    }

    private String copyToDisk(String id, FileItemStream item) {
        final Path temps = Paths.get(this.servletContext.getRealPath("/")).resolve("temps");
        try {
            Files.createDirectories(temps);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        try (final InputStream inputStream = item.openStream()) {
            final File file = temps.resolve(id).toFile();
            try (OutputStream os = new FileOutputStream(file)) {
                IOUtils.copy(inputStream, os);
            }

            return file.getPath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
