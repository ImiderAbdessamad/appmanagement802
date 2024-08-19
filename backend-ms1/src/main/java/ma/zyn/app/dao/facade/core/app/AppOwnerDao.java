package ma.zyn.app.dao.facade.core.app;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.app.AppOwner;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AppOwnerDao extends AbstractRepository<AppOwner,Long>  {

    AppOwner findByUsername(String username);


}
