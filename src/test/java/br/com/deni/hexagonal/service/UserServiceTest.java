package br.com.deni.hexagonal.service;

import br.com.deni.hexagonal.ApplicationTest;
import br.com.deni.hexagonal.core.domain.User;
import br.com.deni.hexagonal.core.repositories.UserRepository;
import br.com.deni.hexagonal.core.services.UserService;
import br.com.deni.hexagonal.mock.Mocks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = ApplicationTest.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void salvar(){
        User user = Mocks.criaUserMock();

    }

}
