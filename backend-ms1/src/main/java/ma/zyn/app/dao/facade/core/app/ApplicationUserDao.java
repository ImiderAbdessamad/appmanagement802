package ma.zyn.app.dao.facade.core.app;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.app.ApplicationUser;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ApplicationUserDao extends AbstractRepository<ApplicationUser,Long>  {

    List<ApplicationUser> findByApplicationId(Long id);
    int deleteByApplicationId(Long id);
    long countByApplicationId(Long id);
    List<ApplicationUser> findByApplicationUserStateCode(String code);
            public int deleteByApplicationUserStateCode(String code);
    long countByApplicationUserStateCode(String code);


}
