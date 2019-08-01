package demo.app.file.utils;

import org.apache.commons.io.FilenameUtils;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.String.format;

/**
 * Created by itaesu on 01/08/2019.
 */
public final class DomainFileTuils {
    private DomainFileTuils() {
    }

    public static String getEncryptedFileName(String originalName) {
        return format("%s.%s", LocalDateTime.now().toString() + "-" + UUID.randomUUID(), getExtension(originalName));
    }

    public static String getExtension(String fileName) {
        return FilenameUtils.getExtension(fileName);
    }
}
