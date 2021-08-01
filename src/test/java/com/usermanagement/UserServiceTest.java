package com.usermanagement;

import com.usermanagement.dto.UserDto;
import com.usermanagement.enums.GenderEnum;
import com.usermanagement.exception.CustomException;
import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.impl.UserServiceImpl;
import com.usermanagement.utils.Constants;
import org.assertj.core.api.Assertions;
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

        userExpected =  new User();
        userExpected.setUserId(Long.valueOf(1));
        userExpected.setPhoneNumber("+33744948883");
        userExpected.setBirthDate(date);
        userExpected.setCountry("France");
        userExpected.setGender(GenderEnum.MALE);
    }

    @Test
    public void testFindByUsername() throws CustomException {
        //Test case: input username is null
        try{
            userService.findByUsername(null);
        }catch (CustomException exception){
            validateCustomException(exception, 41701, 417, Constants.USERNAME_NULL);
        }

        //Test case: User does not exist with given username
        Mockito.when(userRepository.findByUsername("Anand")).thenReturn(null);
        try{
            userService.findByUsername("Anand");
        }catch (CustomException exception){
            validateCustomException(exception, 40401, 404, Constants.USER_NOT_EXISTS);
        }

        //Test case:  user exists with given username
        Mockito.when(userRepository.findByUsername("Anand")).thenReturn(userExpected);
        try{
            userService.findByUsername("Anand");
        }catch (CustomException exception){
            Assert.assertNull(exception);
        }
    }

    @Test
    public void testSaveUserInput() throws ParseException {
        //Test case: Username null
        userDto.setUsername(null);
        try{
            userService.saveUser(userDto);
        }catch(CustomException exception){
            validateCustomException(exception, 41701, 417, Constants.USERNAME_NULL);
        }

        //Test case: Birthdate null
        userDto.setUsername("Anand");
        userDto.setBirthDate(null);
        try{
            userService.saveUser(userDto);
        }catch(CustomException exception){
            validateCustomException(exception, 41702, 417, Constants.BIRTHDATE_NULL);
        }

        //Test case: Country null
        userDto.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1991-05-20"));
        userDto.setCountry(null);
        try{
            userService.saveUser(userDto);
        }catch(CustomException exception){
            validateCustomException(exception, 41703, 417, Constants.COUNTRY_NULL);
        }
    }

    @Test
    public void testSaveUserNotExist() throws CustomException {
        //Test case: Successfull save of user object
        Mockito.when(userRepository.findByUsername("Anand")).thenReturn(null);
        try
        {
            userService.saveUser(userDto);
        }
        catch (CustomException exception)
        {
            Assert.assertNull(exception);
        }
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.matches("Anand"));
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testSaveUserAlreadyExists() throws CustomException {
        Mockito.when(userRepository.findByUsername("Anand")).thenReturn(userExpected);
        try
        {
            userService.saveUser(userDto);
        }
        catch (CustomException exception)
        {
            validateCustomException(exception, 40901, 409, Constants.USERNAME_EXISTS);
        }
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.matches("Anand"));
        Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any(User.class));
    }


    private static void validateCustomException(CustomException exception, Integer statuscode, Integer errorCode,
                                                String errorMessage){
        Assert.assertNotNull(exception);
        Assert.assertEquals(statuscode, exception.getStatusCode());
        Assert.assertEquals(errorCode, Integer.valueOf(exception.getErrorCode().value()));
        Assert.assertEquals(errorMessage, exception.getErrorMessage());
        Assertions.assertThatExceptionOfType(CustomException.class);
    }

}
