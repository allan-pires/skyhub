package com.skyhub; /**
 * Created by allan on 09/04/17.
 */

import com.skyhub.image.ImageController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
        ImageController.initializeImageRepository();
        SpringApplication.run(Application.class, args);
    }
}

