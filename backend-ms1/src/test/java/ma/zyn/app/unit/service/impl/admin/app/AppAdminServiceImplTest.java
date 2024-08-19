package ma.zyn.app.unit.service.impl.admin.app;

import ma.zyn.app.bean.core.app.App;
import ma.zyn.app.dao.facade.core.app.AppDao;
import ma.zyn.app.service.impl.admin.app.AppAdminServiceImpl;

import ma.zyn.app.bean.core.app.ApplicationUser ;
import ma.zyn.app.bean.core.app.App ;
import ma.zyn.app.bean.core.app.AppState ;
import ma.zyn.app.bean.core.app.AppOwner ;
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
class AppAdminServiceImplTest {

    @Mock
    private AppDao repository;
    private AutoCloseable autoCloseable;
    private AppAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AppAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllApp() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveApp() {
        // Given
        App toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteApp() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetAppById() {
        // Given
        Long idToRetrieve = 1L; // Example App ID to retrieve
        App expected = new App(); // You need to replace App with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        App result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private App constructSample(int i) {
		App given = new App();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setAppState(new AppState(1L));
        given.setAppOwner(new AppOwner(1L));
        List<ApplicationUser> applicationUser = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                ApplicationUser element = new ApplicationUser();
                                                element.setId((long)id);
                                                element.setFirstName("firstName"+id);
                                                element.setLastName("lastName"+id);
                                                element.setEmail("email"+id);
                                                element.setApplication(new App(Long.valueOf(4)));
                                                element.setApplicationUserState(new ApplicationUserState(Long.valueOf(5)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setApplicationUser(applicationUser);
        return given;
    }

}
