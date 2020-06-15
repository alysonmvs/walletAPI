package com.wallet.repository;

import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
    UserRepository repository;

    private static final String EMAIL = "email@teste.com";
    
    @Before
    public void setUp(){
        User u = new User();
        u.setName("Set up User");
        u.setPassword("senha123");
        u.setEmail(EMAIL);
        repository.save(u);
    }

    @After
    public void tearDown(){
        repository.deleteAll();
    }

    @Test
    public void testSave(){
    	User user = new User();
		user.setName("Teste");
        user.setEmail("test@teste.com.br");
        user.setPassword("123456");
        User response = repository.save(user);

        Assert.assertNotNull(response);
    }

    @Test
    public void testFindByEmail(){
        Optional<User> response = repository.findByEmailEquals(EMAIL);

        Assert.assertTrue(response.isPresent());
        Assert.assertEquals(response.get().getEmail(), EMAIL);
    }
}
