package ma.zyn.app.unit.dao.facade.core.app;

import ma.zyn.app.bean.core.app.App;
import ma.zyn.app.dao.facade.core.app.AppDao;

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

import ma.zyn.app.bean.core.app.AppState ;
import ma.zyn.app.bean.core.app.AppOwner ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AppDaoTest {

@Autowired
    private AppDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        App entity = new App();
        entity.setId(id);
        underTest.save(entity);
        App loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        App entity = new App();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        App loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<App> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<App> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        App given = constructSample(1);
        App saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private App constructSample(int i) {
		App given = new App();
        given.setName("name-"+i);
        given.setDescription("description-"+i);
        given.setAppState(new AppState(1L));
        given.setAppOwner(new AppOwner(1L));
        return given;
    }

}
