package ma.zyn.app.service.facade.admin.app;

import java.util.List;
import ma.zyn.app.bean.core.app.ApplicationUserState;
import ma.zyn.app.dao.criteria.core.app.ApplicationUserStateCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ApplicationUserStateAdminService {







	ApplicationUserState create(ApplicationUserState t);

    ApplicationUserState update(ApplicationUserState t);

    List<ApplicationUserState> update(List<ApplicationUserState> ts,boolean createIfNotExist);

    ApplicationUserState findById(Long id);

    ApplicationUserState findOrSave(ApplicationUserState t);

    ApplicationUserState findByReferenceEntity(ApplicationUserState t);

    ApplicationUserState findWithAssociatedLists(Long id);

    List<ApplicationUserState> findAllOptimized();

    List<ApplicationUserState> findAll();

    List<ApplicationUserState> findByCriteria(ApplicationUserStateCriteria criteria);

    List<ApplicationUserState> findPaginatedByCriteria(ApplicationUserStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ApplicationUserStateCriteria criteria);

    List<ApplicationUserState> delete(List<ApplicationUserState> ts);

    boolean deleteById(Long id);

    List<List<ApplicationUserState>> getToBeSavedAndToBeDeleted(List<ApplicationUserState> oldList, List<ApplicationUserState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
