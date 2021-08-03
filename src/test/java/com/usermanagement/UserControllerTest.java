package com.usermanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.api.UserApiController;
import com.usermanagement.dto.UserDto;
import com.usermanagement.exception.CustomException;
import com.usermanagement.service.UserService;
import com.usermanagement.utils.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class has mockito test method for all the methods of UserApiController class
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserApiController.class)
public class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @InjectMocks
    UserApiController userApiController;

    UserDto userDto;

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
    }

    /**
     * This test method includes:
     * Testcase1: null userDto object as input
     * Testcase2: Successful registration
     * Testcase3: null username
     * Testcase4: null birthdate
     * Testcase5; null country
     * Testcase6: user already with given username
     *
     * @throws Exception throws exception in the execution failure testcases
     */
    @Test
    public void testAddUser() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user-management/api/v1/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockRequest = MockMvcRequestBuilders.post("/user-management/api/v1/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(userDto));

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.doThrow(new CustomException(HttpStatus.EXPECTATION_FAILED, 41701, Constants.USERNAME_NULL)).when(userService).saveUser(Mockito.any(UserDto.class));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed());

        Mockito.doThrow(new CustomException(HttpStatus.EXPECTATION_FAILED, 41702, Constants.BIRTHDATE_NULL)).when(userService).saveUser(Mockito.any(UserDto.class));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed());

        Mockito.doThrow(new CustomException(HttpStatus.EXPECTATION_FAILED, 41703, Constants.COUNTRY_NULL)).when(userService).saveUser(Mockito.any(UserDto.class));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed());

        Mockito.doThrow(new CustomException(HttpStatus.CONFLICT, 40901, Constants.USERNAME_EXISTS)).when(userService).saveUser(Mockito.any(UserDto.class));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    /**
     * This test method includes:
     * Testcase1: Successful retrieval of user
     * Testcase2: null username as input
     * Testcase3: User does not exist
     *
     * @throws Exception throws exception in the execution failure testcases
     */
    @Test
    public void testFindByUsername() throws Exception {
        Mockito.when(userService.findByUsername("Anand")).thenReturn(userDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-management/api/v1/user/Anand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.when(userService.findByUsername("Anand")).thenThrow(new CustomException(HttpStatus.EXPECTATION_FAILED, 41701, Constants.USERNAME_NULL));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-management/api/v1/user/Anand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed());

        Mockito.doThrow(new CustomException(HttpStatus.NOT_FOUND, 40401, Constants.USER_NOT_EXISTS)).when(userService).findByUsername(Mockito.any(String.class));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-management/api/v1/user/Anand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
