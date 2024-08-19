package ma.zyn.app.dao.facade.core.app;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.app.ApplicationUserState;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.app.ApplicationUserState;
import java.util.List;


@Repository
public interface ApplicationUserStateDao extends AbstractRepository<ApplicationUserState,Long>  {
    ApplicationUserState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ApplicationUserState(item.id,item.libelle) FROM ApplicationUserState item")
    List<ApplicationUserState> findAllOptimized();

}
