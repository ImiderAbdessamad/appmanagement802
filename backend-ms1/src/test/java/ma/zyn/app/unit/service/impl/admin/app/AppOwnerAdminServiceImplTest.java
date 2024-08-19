package ma.zyn.app.unit.service.impl.admin.app;

import ma.zyn.app.bean.core.app.AppOwner;
import ma.zyn.app.dao.facade.core.app.AppOwnerDao;
import ma.zyn.app.service.impl.admin.app.AppOwnerAdminServiceImpl;

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
class AppOwnerAdminServiceImplTest {

    @Mock
    private AppOwnerDao repository;
    private AutoCloseable autoCloseable;
    private AppOwnerAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AppOwnerAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllAppOwner() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveAppOwner() {
        // Given
        AppOwner toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteAppOwner() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetAppOwnerById() {
        // Given
        Long idToRetrieve = 1L; // Example AppOwner ID to retrieve
        AppOwner expected = new AppOwner(); // You need to replace AppOwner with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        AppOwner result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private AppOwner constructSample(int i) {
		AppOwner given = new AppOwner();
        given.setDescription("description-"+i);
        given.setCredentialsNonExpired(false);
        given.setEnabled(false);
        given.setAccountNonExpired(false);
        given.setAccountNonLocked(false);
        given.setPasswordChanged(false);
        given.setUsername("username-"+i);
        given.setPassword("password-"+i);
        given.setAvatar("avatar-"+i);
        given.setFullName("fullName-"+i);
        given.setAbout("about-"+i);
        return given;
    }

}
