package upload.exception;

import java.io.IOException;

/**
 * @author Andrii_Tkachuk
 * @since 5/20/2015
 */
public class ImageUploadException extends RuntimeException {

    public ImageUploadException(String message, Throwable ex) {
        super(message, ex);
    }
}
