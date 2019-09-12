package br.com.deni.hexagonal.adapters;

import br.com.deni.hexagonal.core.domain.User;
import br.com.deni.hexagonal.ports.IReceiverPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReceiverAdapter implements IReceiverPort {

    @Override
    @JmsListener(destination="tirar.fila")
    public void receive(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User obj = objectMapper.readValue(message, User.class);
            System.out.println("Recebeu a seguinte mensagem:"+obj.getName());
            Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().create();
            System.out.println(gson.toJson(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
