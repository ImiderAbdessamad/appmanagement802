package ma.zyn.app.unit.ws.facade.admin.app;

import ma.zyn.app.bean.core.app.App;
import ma.zyn.app.service.impl.admin.app.AppAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.app.AppRestAdmin;
import ma.zyn.app.ws.converter.app.AppConverter;
import ma.zyn.app.ws.dto.app.AppDto;
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
public class AppRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private AppAdminServiceImpl service;
    @Mock
    private AppConverter converter;

    @InjectMocks
    private AppRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllAppTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<AppDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<AppDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveAppTest() throws Exception {
        // Mock data
        AppDto requestDto = new AppDto();
        App entity = new App();
        App saved = new App();
        AppDto savedDto = new AppDto();

        // Mock the converter to return the app object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved app DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<AppDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        AppDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved app DTO
        assertEquals(savedDto.getName(), responseBody.getName());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
