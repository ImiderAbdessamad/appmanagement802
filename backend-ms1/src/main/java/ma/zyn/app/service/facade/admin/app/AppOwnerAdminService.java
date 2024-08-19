package ma.zyn.app.service.facade.admin.app;

import java.util.List;
import ma.zyn.app.bean.core.app.AppOwner;
import ma.zyn.app.dao.criteria.core.app.AppOwnerCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface AppOwnerAdminService {


    AppOwner findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	AppOwner create(AppOwner t);

    AppOwner update(AppOwner t);

    List<AppOwner> update(List<AppOwner> ts,boolean createIfNotExist);

    AppOwner findById(Long id);

    AppOwner findOrSave(AppOwner t);

    AppOwner findByReferenceEntity(AppOwner t);

    AppOwner findWithAssociatedLists(Long id);

    List<AppOwner> findAllOptimized();

    List<AppOwner> findAll();

    List<AppOwner> findByCriteria(AppOwnerCriteria criteria);

    List<AppOwner> findPaginatedByCriteria(AppOwnerCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AppOwnerCriteria criteria);

    List<AppOwner> delete(List<AppOwner> ts);

    boolean deleteById(Long id);

    List<List<AppOwner>> getToBeSavedAndToBeDeleted(List<AppOwner> oldList, List<AppOwner> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
