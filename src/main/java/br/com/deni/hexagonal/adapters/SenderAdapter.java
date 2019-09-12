package br.com.deni.hexagonal.adapters;

import br.com.deni.hexagonal.ports.ISenderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class SenderAdapter implements ISenderPort {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String message){
        jmsTemplate.convertAndSend("colocar.fila", message);
        System.out.println("Passou a seguinte mensagem:"+message);
    }
}
