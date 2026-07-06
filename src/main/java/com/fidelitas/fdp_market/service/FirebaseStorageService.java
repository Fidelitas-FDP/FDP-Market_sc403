package com.fidelitas.fdp_market.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirebaseStorageService {
    // hu7 - subir imagenes de productos
    // hu19 - evidencias MEIC (entregado/disputa)

    // Manejo de toda la logica de interaccion con firebase storage
    @Value("${firebase.bucket.name}")
    private String bucketName;

    @Value("${firebase.storage.path}")
    private String storagePath;

    // futura inyeccion en cliente de storage como bean
    private final Storage storage;

    public FirebaseStorageService(Storage storage) {
        this.storage = storage;
    }
    

    // sube archivo a almacenamiento, sin temporal
    // hu7 y hu19
    public String uploadFile(MultipartFile file, String folder) throws IOException {
        String originalName = file.getOriginalFilename();
        String extension = "";
        
        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }

        // uuid para no sobreescribir archivos
        String fileName = UUID.randomUUID().toString() + extension;
        String path = storagePath + "/" + folder + "/" + fileName;

        // metadata
        BlobId blobId = BlobId.of(bucketName, path);
        String contentType = file.getContentType();
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(contentType != null ? contentType : "application/octet-stream")
                .build();
        
        
        storage.create(blobInfo, file.getBytes());
        
        // url firmada (extraer tiempo de @Value)
        return storage.signUrl(blobInfo, 1825, TimeUnit.DAYS).toString();
    }
    
   
    // eliminar archivos
    
    
    // subir multiples archivos (hu 7)
    
    
    // renovar url firmada con duracion especifica
}
