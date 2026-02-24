package com.example.demo.ServiciosImplementacion;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServicio {

    @Autowired
    private Cloudinary cloudinary;

    
    // Sube una imagen a Cloudinary y devuelve la URL segura
     
    public String subirImagen(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), 
            ObjectUtils.asMap(
                "folder", "rutinas",  // Organiza en carpetas
                "resource_type", "auto"
            ));
        
        // La URL segura de la imagen
        return (String) uploadResult.get("secure_url");
    }

    //Sube una imagen con un nombre personalizado 
    public String subirImagen(MultipartFile file, String nombrePersonalizado) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
            ObjectUtils.asMap(
                "public_id", "rutinas/" + nombrePersonalizado,
                "resource_type", "auto"
            ));
        
        return (String) uploadResult.get("secure_url");
    }

    //Elimina una imagen de Cloudinary por su URL
    public void eliminarImagen(String imageUrl) {
        try {
            // Extraer el public_id de la URL
            String publicId = extraerPublicId(imageUrl);
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            System.err.println("Error al eliminar imagen: " + e.getMessage());
        }
    }

    private String extraerPublicId(String imageUrl) {
        // Ejemplo: https://res.cloudinary.com/tu-cloud/image/upload/v123456/rutinas/mi-imagen.jpg
        // Extrae: "rutinas/mi-imagen"
        String[] parts = imageUrl.split("/");
        String filename = parts[parts.length - 1];
        return "rutinas/" + filename.substring(0, filename.lastIndexOf('.'));
    }
}