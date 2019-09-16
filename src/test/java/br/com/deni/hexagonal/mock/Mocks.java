package br.com.deni.hexagonal.mock;


import br.com.deni.hexagonal.core.domain.User;

public class Mocks {

    public static final Integer ID = 123;
    public static final String USER_TESTE = "Usuário Teste";


    public static User criaUserMock() {
        return criaUserMock(ID, USER_TESTE);
    }


    public static User criaUserMock(Integer id, String name) {
        User user = new User();
        user.setId(ID);
        user.setName(USER_TESTE);
        return user;
    }



    public static User criaUser(Integer id, String name) {
        User user = new User();
        user.setId(ID);
        user.setName(USER_TESTE);
        return user;
    }
}