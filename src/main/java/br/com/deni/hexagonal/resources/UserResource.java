package br.com.deni.hexagonal.resources;

import br.com.deni.hexagonal.core.domain.User;
import br.com.deni.hexagonal.core.domain.dto.UserDTO;
import br.com.deni.hexagonal.core.services.exception.ObjectNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.deni.hexagonal.core.services.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping(value="/users")
public class UserResource {

    private static final String DADOS_OBRIGATORIOS = "Obrigatório enviar os dados para cadastro conforme documentacão do Swagger";
    private static final String NOME_NULO = "Nome não pode ser nulo";
    private static final String USUARIO_EXISTENTE = "Usuário já existe";

    @Autowired
    private UserService service;

    @ApiOperation(value = "buscarTodos", nickname = "Buscar de todos os usuários", response = User.class)
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @ApiOperation(value = "buscarPorID", nickname = "Buscar usuário por ID", response = User.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
            User obj = service.findById(id);
            return ResponseEntity.ok().body(new UserDTO(obj));
    }


    @ApiOperation(value = "salva", nickname = "Salvar novo usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = User.class, message = "Usuário salvo com sucesso"),
            @ApiResponse(code = 400, message = DADOS_OBRIGATORIOS+ ", " +NOME_NULO +"."),
            @ApiResponse(code = 409, message = USUARIO_EXISTENTE),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody @Valid UserDTO objDto){
        User obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        System.out.println(obj.getId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "remove", nickname = "Remover usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 204, response = User.class, message = "Usuário removido com sucesso"),
            @ApiResponse(code = 400, message = NOME_NULO),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "atualiza", nickname = "Atualizar usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 204, response = User.class, message = ""),
            @ApiResponse(code = 400, message = NOME_NULO),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable Integer id){
        User obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
}
