package ma.zyn.app.service.impl.admin.app;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.app.App;
import ma.zyn.app.dao.criteria.core.app.AppCriteria;
import ma.zyn.app.dao.facade.core.app.AppDao;
import ma.zyn.app.dao.specification.core.app.AppSpecification;
import ma.zyn.app.service.facade.admin.app.AppAdminService;
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

import ma.zyn.app.service.facade.admin.app.ApplicationUserAdminService ;
import ma.zyn.app.bean.core.app.ApplicationUser ;
import ma.zyn.app.service.facade.admin.app.AppStateAdminService ;
import ma.zyn.app.bean.core.app.AppState ;
import ma.zyn.app.service.facade.admin.app.AppOwnerAdminService ;
import ma.zyn.app.bean.core.app.AppOwner ;

import java.util.List;
@Service
public class AppAdminServiceImpl implements AppAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public App update(App t) {
        App loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{App.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public App findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public App findOrSave(App t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            App result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<App> findAll() {
        return dao.findAll();
    }

    public List<App> findByCriteria(AppCriteria criteria) {
        List<App> content = null;
        if (criteria != null) {
            AppSpecification mySpecification = constructSpecification(criteria);
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


    private AppSpecification constructSpecification(AppCriteria criteria) {
        AppSpecification mySpecification =  (AppSpecification) RefelexivityUtil.constructObjectUsingOneParam(AppSpecification.class, criteria);
        return mySpecification;
    }

    public List<App> findPaginatedByCriteria(AppCriteria criteria, int page, int pageSize, String order, String sortField) {
        AppSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AppCriteria criteria) {
        AppSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<App> findByAppStateCode(String code){
        return dao.findByAppStateCode(code);
    }
    public int deleteByAppStateCode(String code){
        return dao.deleteByAppStateCode(code);
    }
    public long countByAppStateCode(String code){
        return dao.countByAppStateCode(code);
    }
    public List<App> findByAppOwnerId(Long id){
        return dao.findByAppOwnerId(id);
    }
    public int deleteByAppOwnerId(Long id){
        return dao.deleteByAppOwnerId(id);
    }
    public long countByAppOwnerId(Long id){
        return dao.countByAppOwnerId(id);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        applicationUserService.deleteByAppId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<App> delete(List<App> list) {
		List<App> result = new ArrayList();
        if (list != null) {
            for (App t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public App create(App t) {
        App loaded = findByReferenceEntity(t);
        App saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getApplicationUser() != null) {
                t.getApplicationUser().forEach(element-> {
                    element.setApp(saved);
                    applicationUserService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public App findWithAssociatedLists(Long id){
        App result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setApplicationUser(applicationUserService.findByAppId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<App> update(List<App> ts, boolean createIfNotExist) {
        List<App> result = new ArrayList<>();
        if (ts != null) {
            for (App t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    App loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, App t, App loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(App app){
    if(app !=null && app.getId() != null){
        List<List<ApplicationUser>> resultApplicationUser= applicationUserService.getToBeSavedAndToBeDeleted(applicationUserService.findByAppId(app.getId()),app.getApplicationUser());
            applicationUserService.delete(resultApplicationUser.get(1));
        emptyIfNull(resultApplicationUser.get(0)).forEach(e -> e.setApp(app));
        applicationUserService.update(resultApplicationUser.get(0),true);
        }
    }








    public App findByReferenceEntity(App t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(App t){
        if( t != null) {
            t.setAppState(appStateService.findOrSave(t.getAppState()));
            t.setAppOwner(appOwnerService.findOrSave(t.getAppOwner()));
        }
    }



    public List<App> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<App>> getToBeSavedAndToBeDeleted(List<App> oldList, List<App> newList) {
        List<List<App>> result = new ArrayList<>();
        List<App> resultDelete = new ArrayList<>();
        List<App> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<App> oldList, List<App> newList, List<App> resultUpdateOrSave, List<App> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                App myOld = oldList.get(i);
                App t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                App myNew = newList.get(i);
                App t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ApplicationUserAdminService applicationUserService ;
    @Autowired
    private AppStateAdminService appStateService ;
    @Autowired
    private AppOwnerAdminService appOwnerService ;

    public AppAdminServiceImpl(AppDao dao) {
        this.dao = dao;
    }

    private AppDao dao;
}
