package com.socialbook.repository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.socialbook.config.PersistenceContext;
import com.socialbook.entity.Role;
import com.socialbook.entity.User;
import com.socialbook.enumeration.*;

/**
 * @author Michael Papamichael
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("/data.xml")
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void shouldgetAllUsers() {
        List<User> users = userRepository.findAll();
        assertThat(users.size(), is(5));
    }
    
    @Test
    public void shouldGetAllUsersAndTheirConnections(){
        List<User> users = userRepository.findAllUsersAndTheirConnections();
        assertThat(users.size(), is(5));
        assertNotNull(users.get(1).getUserConnections());
    }
    
    @Test
    public void shouldGetAUsersConnections(){
        List<User> users = userRepository.findUsersAllConnections(new User(2));

        
        assertEquals(2, users.size());
        assertEquals(3, users.get(0).getId().intValue());
        assertEquals(4, users.get(1).getId().intValue());
        
        assertEquals(2, users.get(0).getUserConnections().size());
    }
    
    
    @Test 
    public void shouldPersistUser(){
        Role role = new Role();
        role.setId(Roles.USER.getId());
        
        User user = new User();
        user.setName("Michael Papamichael");
        user.setEmail("micahel4@hotmail.com");
        user.getRoles().add(role);
        
        userRepository.save(user);
        
        List<User> todoEntries = userRepository.findAll();
        assertThat(todoEntries.size(), is(6));
        
        assertThat(user.getRoles().size(), is(1));
    }
    
    @Test
    public void findUserByEmail(){
        assertNotNull(userRepository.findUserByEmail("mikelimassol@hotmail.com"));
    }
    
    @Test
    public void findUserByEmailAndPassword(){
        assertNotNull(userRepository.findUserByEmailAndPassword("mikelimassol@hotmail.com", "8imitd4ee"));
    }
    

}