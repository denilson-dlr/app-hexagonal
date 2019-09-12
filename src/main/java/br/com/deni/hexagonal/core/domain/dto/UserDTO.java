package br.com.deni.hexagonal.core.domain.dto;


import br.com.deni.hexagonal.core.domain.User;

import java.io.Serializable;


public class UserDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
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

