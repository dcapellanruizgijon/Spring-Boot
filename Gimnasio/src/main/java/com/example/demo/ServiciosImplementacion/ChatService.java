package com.example.demo.ServiciosImplementacion;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    //genera la respuesta de la ia
    public String generarRespuesta(String mensajeUsuario) {
        try {
            Prompt prompt = new Prompt(mensajeUsuario);
            ChatResponse response = chatModel.call(prompt);
            
            // Forma correcta de obtener el contenido en versiones recientes
            return response.getResult().getOutput().getText();
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Lo siento, hubo un error al procesar tu mensaje. Por favor, intenta de nuevo.";
        }
    }
}