package br.com.deni.hexagonal.core.domain.dto;


import br.com.deni.hexagonal.core.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "Objeto Usuário",subTypes = {User.class})
public class UserDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(dataType = "Inteiro", notes = "Esse campo não é necessário quando o método for POST (inclusão de usuário).", example = "01", position = 1)
    private Integer id;
    @ApiModelProperty(dataType = "String", notes = "Nome completo do usuário.", example = "Marie Green White", required = true, position = 2)
    private String name;

    public UserDTO() {

    }

    public UserDTO(User obj) {
        id = obj.getId();
        name = obj.getName();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

