package ma.zyn.app.unit.ws.facade.admin.app;

import ma.zyn.app.bean.core.app.AppOwner;
import ma.zyn.app.service.impl.admin.app.AppOwnerAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.app.AppOwnerRestAdmin;
import ma.zyn.app.ws.converter.app.AppOwnerConverter;
import ma.zyn.app.ws.dto.app.AppOwnerDto;
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
public class AppOwnerRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private AppOwnerAdminServiceImpl service;
    @Mock
    private AppOwnerConverter converter;

    @InjectMocks
    private AppOwnerRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllAppOwnerTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<AppOwnerDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<AppOwnerDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveAppOwnerTest() throws Exception {
        // Mock data
        AppOwnerDto requestDto = new AppOwnerDto();
        AppOwner entity = new AppOwner();
        AppOwner saved = new AppOwner();
        AppOwnerDto savedDto = new AppOwnerDto();

        // Mock the converter to return the appOwner object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved appOwner DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<AppOwnerDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        AppOwnerDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved appOwner DTO
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getCredentialsNonExpired(), responseBody.getCredentialsNonExpired());
        assertEquals(savedDto.getEnabled(), responseBody.getEnabled());
        assertEquals(savedDto.getAccountNonExpired(), responseBody.getAccountNonExpired());
        assertEquals(savedDto.getAccountNonLocked(), responseBody.getAccountNonLocked());
        assertEquals(savedDto.getPasswordChanged(), responseBody.getPasswordChanged());
        assertEquals(savedDto.getUsername(), responseBody.getUsername());
        assertEquals(savedDto.getPassword(), responseBody.getPassword());
        assertEquals(savedDto.getAvatar(), responseBody.getAvatar());
        assertEquals(savedDto.getFullName(), responseBody.getFullName());
        assertEquals(savedDto.getAbout(), responseBody.getAbout());
    }

}
