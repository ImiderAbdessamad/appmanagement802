package ma.zyn.app.service.facade.admin.app;

import java.util.List;
import ma.zyn.app.bean.core.app.App;
import ma.zyn.app.dao.criteria.core.app.AppCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface AppAdminService {



    List<App> findByAppStateCode(String code);
    int deleteByAppStateCode(String code);
    long countByAppStateCode(String code);
    List<App> findByAppOwnerId(Long id);
    int deleteByAppOwnerId(Long id);
    long countByAppOwnerId(Long id);




	App create(App t);

    App update(App t);

    List<App> update(List<App> ts,boolean createIfNotExist);

    App findById(Long id);

    App findOrSave(App t);

    App findByReferenceEntity(App t);

    App findWithAssociatedLists(Long id);

    List<App> findAllOptimized();

    List<App> findAll();

    List<App> findByCriteria(AppCriteria criteria);

    List<App> findPaginatedByCriteria(AppCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AppCriteria criteria);

    List<App> delete(List<App> ts);

    boolean deleteById(Long id);

    List<List<App>> getToBeSavedAndToBeDeleted(List<App> oldList, List<App> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
