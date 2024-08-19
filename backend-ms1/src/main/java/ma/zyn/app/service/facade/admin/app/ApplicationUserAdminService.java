package ma.zyn.app.service.facade.admin.app;

import java.util.List;
import ma.zyn.app.bean.core.app.ApplicationUser;
import ma.zyn.app.dao.criteria.core.app.ApplicationUserCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ApplicationUserAdminService {



    List<ApplicationUser> findByApplicationId(Long id);
    int deleteByApplicationId(Long id);
    long countByApplicationId(Long id);
    List<ApplicationUser> findByApplicationUserStateCode(String code);
    int deleteByApplicationUserStateCode(String code);
    long countByApplicationUserStateCode(String code);




	ApplicationUser create(ApplicationUser t);

    ApplicationUser update(ApplicationUser t);

    List<ApplicationUser> update(List<ApplicationUser> ts,boolean createIfNotExist);

    ApplicationUser findById(Long id);

    ApplicationUser findOrSave(ApplicationUser t);

    ApplicationUser findByReferenceEntity(ApplicationUser t);

    ApplicationUser findWithAssociatedLists(Long id);

    List<ApplicationUser> findAllOptimized();

    List<ApplicationUser> findAll();

    List<ApplicationUser> findByCriteria(ApplicationUserCriteria criteria);

    List<ApplicationUser> findPaginatedByCriteria(ApplicationUserCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ApplicationUserCriteria criteria);

    List<ApplicationUser> delete(List<ApplicationUser> ts);

    boolean deleteById(Long id);

    List<List<ApplicationUser>> getToBeSavedAndToBeDeleted(List<ApplicationUser> oldList, List<ApplicationUser> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
