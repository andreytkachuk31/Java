package upload;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import upload.exception.ImageUploadException;

import java.io.File;
import java.io.IOException;

/**
 * @author Andrii_Tkachuk
 * @since 5/20/2015
 */
@Controller
@RequestMapping("/upload")
public class UploadImageController {

    private static final String WEB_ROOT_PATH = "D:\\image";

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showUploadForm() {
        return "upload";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUploadForm(final MultipartFile image) {
        saveImage("image.jpg", image);
        return "uploadSuccess";
    }

    private void saveImage(String filename, MultipartFile image) {
        try {
            File file = new File(WEB_ROOT_PATH + "/" + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            throw new ImageUploadException("Unable to save image", e);
        }
    }
}
