package com.fidelitas.fdp_market;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class StorageConfig {
    // lee json de credencial y se conecta a servidores de google

    @Value("${firebase.json.path}")
    private String jsonPath;

    @Value("${firebase.json.file}")
    private String jsonFile;

    @Bean
    public Storage storage() throws IOException {

        // forzar separador /, por defecto de springen vez de por defecto \ de file separator
        String rutaCompleta = jsonPath + "/" + jsonFile;
        ClassPathResource resource = new ClassPathResource(rutaCompleta);
        
        try (InputStream is = resource.getInputStream()) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(is);
            return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        }
    }

}
