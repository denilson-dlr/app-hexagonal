package br.com.deni.hexagonal.service;

import br.com.deni.hexagonal.ApplicationTest;
import br.com.deni.hexagonal.core.domain.User;
import br.com.deni.hexagonal.core.repositories.UserRepository;
import br.com.deni.hexagonal.core.services.UserService;
import br.com.deni.hexagonal.mock.Mocks;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = ApplicationTest.class)
public class UserServiceTest {

    private static final String ALTERADO = "Alterado";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void salvar(){
        User user = Mocks.criaUserMock();
        User userSalvo = userService.insert(user);

        Assert.assertNotNull(userSalvo);
    }

    @Test
    public void atualizar(){
        User user = Mocks.criaUserMock();
        User userSalva = userService.update(user);
        User userAtualizado = userService.update(user);
        userSalva.setName(ALTERADO);
        Assert.assertNotNull(userAtualizado);
        Assert.assertTrue(userAtualizado.getName().equals(ALTERADO));
        Assert.assertTrue(userSalva.getId().equals(userAtualizado.getId()));
    }

    @Test
    public void buscar(){
        User user = Mocks.criaUserMock();
        User userBusca = userService.findById(user.getId());

        Assert.assertNotNull(userBusca);
        Assert.assertNotNull(user.getName());
    }

    @Test
    public void excluir(){
        User user = Mocks.criaUserMock();
        userService.delete(user.getId());
        verify(userService, times(1)).delete(user.getId());
        verifyNoMoreInteractions(userService);
    }
}
