package com.usermanagement;

import com.usermanagement.dto.UserDto;
import com.usermanagement.enums.GenderEnum;
import com.usermanagement.exception.CustomException;
import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.impl.UserServiceImpl;
import com.usermanagement.utils.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class has mockito test method for all the methods of UserService class
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    UserDto userDto;
    User userExpected;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void init() {
        userDto = new UserDto();
        userDto.setUsername("Anand");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse("1991-05-20");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userDto.setBirthDate(date);
        userDto.setCountry("France");
        userDto.setGender("male");
        userDto.setPhoneNumber("+33744948883");

        userExpected = new User();
        userExpected.setUserId(Long.valueOf(1));
        userExpected.setPhoneNumber("+33744948883");
        userExpected.setBirthDate(date);
        userExpected.setCountry("France");
        userExpected.setGender(GenderEnum.MALE);
    }

    /**
     * This test method includes all testcases of findByUsername method:
     * Testcase1: null username
     * Testcase2: User does not exist with given username
     * Testcase3: User exists with given username
     *
     * @throws CustomException throws error when no user exist
     */
    @Test
    public void testFindByUsername() throws CustomException {
        //Test case: input username is null
        try {
            userService.findByUsername(null);
        } catch (CustomException exception) {
            validateCustomException(exception, 41701, 417, Constants.USERNAME_NULL);
        }

        //Test case: User does not exist with given username
        Mockito.when(userRepository.findByUsername("Anand")).thenReturn(null);
        try {
            userService.findByUsername("Anand");
        } catch (CustomException exception) {
            validateCustomException(exception, 40401, 404, Constants.USER_NOT_EXISTS);
        }

        //Test case:  user exists with given username
        Mockito.when(userRepository.findByUsername("Anand")).thenReturn(userExpected);
        try {
            userService.findByUsername("Anand");
        } catch (CustomException exception) {
            Assert.assertNull(exception);
        }
    }

    /**
     * This test method includes all testcases of input validation
     * of username, birthdate and country during the user registration
     * Testcase1: null username
     * Testcase2: null birthdate
     * Testcase3: null country
     *
     * @throws ParseException throws error if there is a problem in date parsing
     */
    @Test
    public void testSaveUserInput() throws ParseException {
        //Test case: Username null
        userDto.setUsername(null);
        try {
            userService.saveUser(userDto);
        } catch (CustomException exception) {
            validateCustomException(exception, 41701, 417, Constants.USERNAME_NULL);
        }

        //Test case: Birthdate null
        userDto.setUsername("Anand");
        userDto.setBirthDate(null);
        try {
            userService.saveUser(userDto);
        } catch (CustomException exception) {
            validateCustomException(exception, 41702, 417, Constants.BIRTHDATE_NULL);
        }

        //Test case: Country null
        userDto.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1991-05-20"));
        userDto.setCountry(null);
        try {
            userService.saveUser(userDto);
        } catch (CustomException exception) {
            validateCustomException(exception, 41703, 417, Constants.COUNTRY_NULL);
        }
    }

    /**
     * This test method includes
     * Testcase: successful registration
     *
     * @throws CustomException raise appropriate exceptions
     */
    @Test
    public void testSaveUserNotExist() throws CustomException {
        //Test case: Successful save of user object
        Mockito.when(userRepository.findByUsername("Anand")).thenReturn(null);
        try {
            userService.saveUser(userDto);
        } catch (CustomException exception) {
            Assert.assertNull(exception);
        }
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.matches("Anand"));
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    /**
     * This test method includes:
     * Testcase: User with given username already exists
     *
     * @throws CustomException throw error while user with same username exists
     */
    @Test
    public void testSaveUserAlreadyExists() throws CustomException {
        Mockito.when(userRepository.findByUsername("Anand")).thenReturn(userExpected);
        try {
            userService.saveUser(userDto);
        } catch (CustomException exception) {
            validateCustomException(exception, 40901, 409, Constants.USERNAME_EXISTS);
        }
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.matches("Anand"));
        Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any(User.class));
    }


    private static void validateCustomException(CustomException exception, Integer errorCode, Integer statusCode,
                                                String errorMessage) {
        Assert.assertNotNull(exception);
        Assert.assertEquals(errorCode, exception.getErrorCode());
        Assert.assertEquals(statusCode, Integer.valueOf(exception.getStatusCode().value()));
        Assert.assertEquals(errorMessage, exception.getErrorMessage());
    }

}
