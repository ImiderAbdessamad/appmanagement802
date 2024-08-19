package ma.zyn.app.unit.service.impl.admin.app;

import ma.zyn.app.bean.core.app.ApplicationUser;
import ma.zyn.app.dao.facade.core.app.ApplicationUserDao;
import ma.zyn.app.service.impl.admin.app.ApplicationUserAdminServiceImpl;

import ma.zyn.app.bean.core.app.App ;
import ma.zyn.app.bean.core.app.ApplicationUserState ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class ApplicationUserAdminServiceImplTest {

    @Mock
    private ApplicationUserDao repository;
    private AutoCloseable autoCloseable;
    private ApplicationUserAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ApplicationUserAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllApplicationUser() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveApplicationUser() {
        // Given
        ApplicationUser toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteApplicationUser() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetApplicationUserById() {
        // Given
        Long idToRetrieve = 1L; // Example ApplicationUser ID to retrieve
        ApplicationUser expected = new ApplicationUser(); // You need to replace ApplicationUser with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ApplicationUser result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ApplicationUser constructSample(int i) {
		ApplicationUser given = new ApplicationUser();
        given.setFirstName("firstName-"+i);
        given.setLastName("lastName-"+i);
        given.setEmail("email-"+i);
        given.setApplication(new App(1L));
        given.setApplicationUserState(new ApplicationUserState(1L));
        return given;
    }

}
