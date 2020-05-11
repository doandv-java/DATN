package haui.stores.framework;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.settings")
public class Settings {
    private ImageRoot image = new ImageRoot();

    @Data
    public static class ImageRoot {
        String path;
    }
}
