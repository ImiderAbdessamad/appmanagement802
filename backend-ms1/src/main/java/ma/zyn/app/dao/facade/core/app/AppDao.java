package ma.zyn.app.dao.facade.core.app;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.app.App;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AppDao extends AbstractRepository<App,Long>  {

    List<App> findByAppStateCode(String code);
            public int deleteByAppStateCode(String code);
    long countByAppStateCode(String code);
    List<App> findByAppOwnerId(Long id);
    int deleteByAppOwnerId(Long id);
    long countByAppOwnerId(Long id);


}
