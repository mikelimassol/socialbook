package com.socialbook.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

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
import com.socialbook.entity.User;
import com.socialbook.entity.UserConnection;


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
public class UserConnectionRepositoryTest {
    
    @Autowired
    private UserConnectionRepository userConnectionRepository;
    
    @Test
    public void shouldFindAllByUser(){
        List<UserConnection> connections = userConnectionRepository.findAllByUser(new User(2));
        assertTrue(!connections.isEmpty());
    }
    /**
     * When connection a user we create a connection for both users
     */
    @Test
    public void shouldCreateNewConnection(){
        
        UserConnection connection = new UserConnection();
        connection.setUser(new User(2));
        connection.setConnectetUser(new User(5));
        connection.setDateConnected(Calendar.getInstance().getTime());
        userConnectionRepository.save(connection);
        userConnectionRepository.flush();  
        
        UserConnection connectionInverse = new UserConnection();
        connectionInverse.setUser(new User(5));
        connectionInverse.setConnectetUser(new User(2));
        connectionInverse.setDateConnected(Calendar.getInstance().getTime());
        userConnectionRepository.save(connectionInverse);
        
        userConnectionRepository.flush();  
        
        assertEquals(8, userConnectionRepository.findAll().size());
    }
    
    

}
