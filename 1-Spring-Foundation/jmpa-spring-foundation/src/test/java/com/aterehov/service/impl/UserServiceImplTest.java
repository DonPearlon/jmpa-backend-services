package com.aterehov.service.impl;

import com.aterehov.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@SpringBootTest
//@ActiveProfiles("test")
@ActiveProfiles("dev")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:script/drop_users.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testSaveSingleUser() {
        //Given
        var id = 1;
        var userToSave = new User();
        userToSave.setName("user1");
        userToSave.setAge(20);

        var expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setName("user1");
        expectedUser.setAge(20);

        //When
        userService.save(userToSave);
        var actualUser = userService.getById(id);

        //Then
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testSaveMultipleUsers() {
        //Given
        var userToSave1 = new User();
        userToSave1.setName("user1");
        userToSave1.setAge(20);

        var userToSave2 = new User();
        userToSave2.setName("user2");
        userToSave2.setAge(30);

        var userToSave3 = new User();
        userToSave3.setName("user3");
        userToSave3.setAge(40);

        var expectedUser1 = new User();
        expectedUser1.setId(1);
        expectedUser1.setName("user1");
        expectedUser1.setAge(20);

        var expectedUser2 = new User();
        expectedUser2.setId(2);
        expectedUser2.setName("user2");
        expectedUser2.setAge(30);

        var expectedUser3 = new User();
        expectedUser3.setId(3);
        expectedUser3.setName("user3");
        expectedUser3.setAge(40);

        var expectedUsers = new ArrayList<>(3);
        expectedUsers.add(expectedUser1);
        expectedUsers.add(expectedUser2);
        expectedUsers.add(expectedUser3);

        //When
        userService.save(userToSave1);
        userService.save(userToSave2);
        userService.save(userToSave3);
        var actualUsers = userService.getAll();

        //Then
        assertEquals(expectedUsers, actualUsers);
    }

}
