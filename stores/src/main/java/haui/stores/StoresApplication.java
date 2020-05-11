package haui.stores;

import haui.stores.framework.Settings;
import haui.stores.utils.FileUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor

public class StoresApplication implements CommandLineRunner {

    private final Settings settings;

    public static void main(String[] args) {
        SpringApplication.run(StoresApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        FileUtils.createDirectoryIfNotExist(settings.getImage().getPath());
    }
}
