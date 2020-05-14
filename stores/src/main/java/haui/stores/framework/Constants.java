package haui.stores.framework;

import lombok.Data;

@Data
public class Constants {

    @Data
    public static class IMAGE_DEFAULT {
        public static String NAME = "/upload/no_image.jpg";
        public static final String USER = "/upload/user.jpg";

    }

    @Data
    public static class DELETE {
        public static final int TRUE = 1;
        public static final int FALSE = 0;
    }
}
