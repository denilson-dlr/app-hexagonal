package br.com.deni.hexagonal.resources;

import br.com.deni.hexagonal.core.domain.User;
import br.com.deni.hexagonal.ports.ISenderPort;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdapterResourceTest {

    private static final String DADOS_OBRIGATORIOS = "Obrigatório enviar os dados para cadastro conforme documentacão do Swagger";
    private static final String NOME_NULO = "Nome não pode ser nulo";
    private static final String USUARIO_EXISTENTE = "Usuário já existe";

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ISenderPort senderPort;

    @ApiOperation(value = "send", nickname = "Enviar usuário para fila.")
    @ApiResponses(value = {
            @ApiResponse(code = 202, response = User.class, message = "Usuário postado em fila com sucesso"),
            @ApiResponse(code = 400, message = DADOS_OBRIGATORIOS+ ", " +NOME_NULO +"."),
            @ApiResponse(code = 409, message = USUARIO_EXISTENTE),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @PostMapping(path = "/send")
    public ResponseEntity<?> send(@RequestBody User payload) {
        senderPort.send(new Gson().toJson(payload));
        return ResponseEntity.accepted().build();
    }
}
