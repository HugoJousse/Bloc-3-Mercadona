package com.mercadona.webapp;

import com.mercadona.webapp.model.User;
import com.mercadona.webapp.repository.UserRepository;
import com.mercadona.webapp.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setPseudo("myUserTest");
        user.setPassword("myPasswordTest");
        userService.saveUser(user);
        when(userRepository.findByPseudo("myUserTest")).thenReturn(user);
        User userSaved = userService.getUserByPseudo("myUserTest");
        assertEquals(user.getPseudo(),userSaved.getPseudo());

    }

    @Test
    public void testIsUserIsRegistered() {
        //Case of no users registered
        when(userRepository.count()).thenReturn(0L);
        boolean result = userService.isUsersRegistered();

        assertFalse(result);
    }

    @Test
    public void testIsPseudoExist() {
        //Case no user using this pseudo
        String test = "test";
        when(userRepository.findByPseudo(test)).thenReturn(null);
        boolean result = userService.isPseudoExist(test);

        assertFalse(result);
    }
}
