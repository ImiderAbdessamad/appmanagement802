package ma.zyn.app.service.facade.admin.app;

import java.util.List;
import ma.zyn.app.bean.core.app.AppState;
import ma.zyn.app.dao.criteria.core.app.AppStateCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface AppStateAdminService {







	AppState create(AppState t);

    AppState update(AppState t);

    List<AppState> update(List<AppState> ts,boolean createIfNotExist);

    AppState findById(Long id);

    AppState findOrSave(AppState t);

    AppState findByReferenceEntity(AppState t);

    AppState findWithAssociatedLists(Long id);

    List<AppState> findAllOptimized();

    List<AppState> findAll();

    List<AppState> findByCriteria(AppStateCriteria criteria);

    List<AppState> findPaginatedByCriteria(AppStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AppStateCriteria criteria);

    List<AppState> delete(List<AppState> ts);

    boolean deleteById(Long id);

    List<List<AppState>> getToBeSavedAndToBeDeleted(List<AppState> oldList, List<AppState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
