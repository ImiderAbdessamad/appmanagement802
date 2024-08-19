package ma.zyn.app.service.impl.admin.app;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.app.AppState;
import ma.zyn.app.dao.criteria.core.app.AppStateCriteria;
import ma.zyn.app.dao.facade.core.app.AppStateDao;
import ma.zyn.app.dao.specification.core.app.AppStateSpecification;
import ma.zyn.app.service.facade.admin.app.AppStateAdminService;
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


import java.util.List;
@Service
public class AppStateAdminServiceImpl implements AppStateAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AppState update(AppState t) {
        AppState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{AppState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public AppState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public AppState findOrSave(AppState t) {
        if (t != null) {
            AppState result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<AppState> findAll() {
        return dao.findAll();
    }

    public List<AppState> findByCriteria(AppStateCriteria criteria) {
        List<AppState> content = null;
        if (criteria != null) {
            AppStateSpecification mySpecification = constructSpecification(criteria);
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


    private AppStateSpecification constructSpecification(AppStateCriteria criteria) {
        AppStateSpecification mySpecification =  (AppStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(AppStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<AppState> findPaginatedByCriteria(AppStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        AppStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AppStateCriteria criteria) {
        AppStateSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public List<AppState> delete(List<AppState> list) {
		List<AppState> result = new ArrayList();
        if (list != null) {
            for (AppState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AppState create(AppState t) {
        AppState loaded = findByReferenceEntity(t);
        AppState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public AppState findWithAssociatedLists(Long id){
        AppState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AppState> update(List<AppState> ts, boolean createIfNotExist) {
        List<AppState> result = new ArrayList<>();
        if (ts != null) {
            for (AppState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    AppState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, AppState t, AppState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public AppState findByReferenceEntity(AppState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<AppState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<AppState>> getToBeSavedAndToBeDeleted(List<AppState> oldList, List<AppState> newList) {
        List<List<AppState>> result = new ArrayList<>();
        List<AppState> resultDelete = new ArrayList<>();
        List<AppState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<AppState> oldList, List<AppState> newList, List<AppState> resultUpdateOrSave, List<AppState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                AppState myOld = oldList.get(i);
                AppState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                AppState myNew = newList.get(i);
                AppState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public AppStateAdminServiceImpl(AppStateDao dao) {
        this.dao = dao;
    }

    private AppStateDao dao;
}
