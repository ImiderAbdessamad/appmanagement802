package ma.zyn.app.dao.facade.core.app;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.app.AppState;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.app.AppState;
import java.util.List;


@Repository
public interface AppStateDao extends AbstractRepository<AppState,Long>  {
    AppState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW AppState(item.id,item.libelle) FROM AppState item")
    List<AppState> findAllOptimized();

}
