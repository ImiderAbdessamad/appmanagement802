package ma.zyn.app.unit.dao.facade.core.app;

import ma.zyn.app.bean.core.app.AppOwner;
import ma.zyn.app.dao.facade.core.app.AppOwnerDao;

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


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AppOwnerDaoTest {

@Autowired
    private AppOwnerDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        AppOwner entity = new AppOwner();
        entity.setId(id);
        underTest.save(entity);
        AppOwner loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        AppOwner entity = new AppOwner();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        AppOwner loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<AppOwner> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<AppOwner> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        AppOwner given = constructSample(1);
        AppOwner saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
