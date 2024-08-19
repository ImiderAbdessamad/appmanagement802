package ma.zyn.app.unit.dao.facade.core.app;

import ma.zyn.app.bean.core.app.ApplicationUser;
import ma.zyn.app.dao.facade.core.app.ApplicationUserDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zyn.app.bean.core.app.App ;
import ma.zyn.app.bean.core.app.ApplicationUserState ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ApplicationUserDaoTest {

@Autowired
    private ApplicationUserDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        ApplicationUser entity = new ApplicationUser();
        entity.setId(id);
        underTest.save(entity);
        ApplicationUser loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ApplicationUser entity = new ApplicationUser();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ApplicationUser loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ApplicationUser> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ApplicationUser> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ApplicationUser given = constructSample(1);
        ApplicationUser saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
