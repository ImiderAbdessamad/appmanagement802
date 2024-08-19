package ma.zyn.app.unit.service.impl.admin.app;

import ma.zyn.app.bean.core.app.ApplicationUserState;
import ma.zyn.app.dao.facade.core.app.ApplicationUserStateDao;
import ma.zyn.app.service.impl.admin.app.ApplicationUserStateAdminServiceImpl;

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
class ApplicationUserStateAdminServiceImplTest {

    @Mock
    private ApplicationUserStateDao repository;
    private AutoCloseable autoCloseable;
    private ApplicationUserStateAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ApplicationUserStateAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllApplicationUserState() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveApplicationUserState() {
        // Given
        ApplicationUserState toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteApplicationUserState() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetApplicationUserStateById() {
        // Given
        Long idToRetrieve = 1L; // Example ApplicationUserState ID to retrieve
        ApplicationUserState expected = new ApplicationUserState(); // You need to replace ApplicationUserState with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ApplicationUserState result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ApplicationUserState constructSample(int i) {
		ApplicationUserState given = new ApplicationUserState();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
