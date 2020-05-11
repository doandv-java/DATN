package haui.stores.services.impl;

import haui.stores.framework.Constants;
import haui.stores.framework.Settings;
import haui.stores.services.ImageService;
import haui.stores.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private Settings settings;

    @Override
    public String saveImage(String imageLink, MultipartFile image) {
        //Path new image if file multipart file not empty
        String imageLinkNew = StringUtils.EMPTY;
        //Check file image not empty
        if (!FileUtils.checkNullOrEmptyImage(image)) {
            //Save image in to storage root
            imageLinkNew = FileUtils.saveImage(settings.getImage().getPath(), image);
        }
        //Check image link old empty or not empty
        if (!StringUtils.isEmpty(imageLink)) {
            //update
            return FileUtils.checkNullOrEmptyImage(image) ? imageLink : imageLinkNew;
        } else {
            //create
            return FileUtils.checkNullOrEmptyImage(image) ? Constants.IMAGE_DEFAULT.NAME : imageLinkNew;
        }
    }
}
