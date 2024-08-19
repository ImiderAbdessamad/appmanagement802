package ma.zyn.app.service.impl.admin.app;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.app.ApplicationUser;
import ma.zyn.app.dao.criteria.core.app.ApplicationUserCriteria;
import ma.zyn.app.dao.facade.core.app.ApplicationUserDao;
import ma.zyn.app.dao.specification.core.app.ApplicationUserSpecification;
import ma.zyn.app.service.facade.admin.app.ApplicationUserAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.app.AppAdminService ;
import ma.zyn.app.bean.core.app.App ;
import ma.zyn.app.service.facade.admin.app.ApplicationUserStateAdminService ;
import ma.zyn.app.bean.core.app.ApplicationUserState ;

import java.util.List;
@Service
public class ApplicationUserAdminServiceImpl implements ApplicationUserAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ApplicationUser update(ApplicationUser t) {
        ApplicationUser loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ApplicationUser.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ApplicationUser findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ApplicationUser findOrSave(ApplicationUser t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ApplicationUser result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ApplicationUser> findAll() {
        return dao.findAll();
    }

    public List<ApplicationUser> findByCriteria(ApplicationUserCriteria criteria) {
        List<ApplicationUser> content = null;
        if (criteria != null) {
            ApplicationUserSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ApplicationUserSpecification constructSpecification(ApplicationUserCriteria criteria) {
        ApplicationUserSpecification mySpecification =  (ApplicationUserSpecification) RefelexivityUtil.constructObjectUsingOneParam(ApplicationUserSpecification.class, criteria);
        return mySpecification;
    }

    public List<ApplicationUser> findPaginatedByCriteria(ApplicationUserCriteria criteria, int page, int pageSize, String order, String sortField) {
        ApplicationUserSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ApplicationUserCriteria criteria) {
        ApplicationUserSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ApplicationUser> findByApplicationId(Long id){
        return dao.findByApplicationId(id);
    }
    public int deleteByApplicationId(Long id){
        return dao.deleteByApplicationId(id);
    }
    public long countByApplicationId(Long id){
        return dao.countByApplicationId(id);
    }
    public List<ApplicationUser> findByApplicationUserStateCode(String code){
        return dao.findByApplicationUserStateCode(code);
    }
    public int deleteByApplicationUserStateCode(String code){
        return dao.deleteByApplicationUserStateCode(code);
    }
    public long countByApplicationUserStateCode(String code){
        return dao.countByApplicationUserStateCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ApplicationUser> delete(List<ApplicationUser> list) {
		List<ApplicationUser> result = new ArrayList();
        if (list != null) {
            for (ApplicationUser t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ApplicationUser create(ApplicationUser t) {
        ApplicationUser loaded = findByReferenceEntity(t);
        ApplicationUser saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ApplicationUser findWithAssociatedLists(Long id){
        ApplicationUser result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ApplicationUser> update(List<ApplicationUser> ts, boolean createIfNotExist) {
        List<ApplicationUser> result = new ArrayList<>();
        if (ts != null) {
            for (ApplicationUser t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ApplicationUser loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ApplicationUser t, ApplicationUser loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ApplicationUser findByReferenceEntity(ApplicationUser t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ApplicationUser t){
        if( t != null) {
            t.setApplication(appService.findOrSave(t.getApplication()));
            t.setApplicationUserState(applicationUserStateService.findOrSave(t.getApplicationUserState()));
        }
    }



    public List<ApplicationUser> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<ApplicationUser>> getToBeSavedAndToBeDeleted(List<ApplicationUser> oldList, List<ApplicationUser> newList) {
        List<List<ApplicationUser>> result = new ArrayList<>();
        List<ApplicationUser> resultDelete = new ArrayList<>();
        List<ApplicationUser> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<ApplicationUser> oldList, List<ApplicationUser> newList, List<ApplicationUser> resultUpdateOrSave, List<ApplicationUser> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ApplicationUser myOld = oldList.get(i);
                ApplicationUser t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ApplicationUser myNew = newList.get(i);
                ApplicationUser t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private AppAdminService appService ;
    @Autowired
    private ApplicationUserStateAdminService applicationUserStateService ;

    public ApplicationUserAdminServiceImpl(ApplicationUserDao dao) {
        this.dao = dao;
    }

    private ApplicationUserDao dao;
}
