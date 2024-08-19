package ma.zyn.app.unit.ws.facade.admin.app;

import ma.zyn.app.bean.core.app.ApplicationUserState;
import ma.zyn.app.service.impl.admin.app.ApplicationUserStateAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.app.ApplicationUserStateRestAdmin;
import ma.zyn.app.ws.converter.app.ApplicationUserStateConverter;
import ma.zyn.app.ws.dto.app.ApplicationUserStateDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserStateRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ApplicationUserStateAdminServiceImpl service;
    @Mock
    private ApplicationUserStateConverter converter;

    @InjectMocks
    private ApplicationUserStateRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllApplicationUserStateTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ApplicationUserStateDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ApplicationUserStateDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveApplicationUserStateTest() throws Exception {
        // Mock data
        ApplicationUserStateDto requestDto = new ApplicationUserStateDto();
        ApplicationUserState entity = new ApplicationUserState();
        ApplicationUserState saved = new ApplicationUserState();
        ApplicationUserStateDto savedDto = new ApplicationUserStateDto();

        // Mock the converter to return the applicationUserState object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved applicationUserState DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ApplicationUserStateDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ApplicationUserStateDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved applicationUserState DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
    }

}
