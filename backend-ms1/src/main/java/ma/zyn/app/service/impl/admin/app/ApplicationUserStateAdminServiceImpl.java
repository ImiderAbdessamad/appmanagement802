package ma.zyn.app.service.impl.admin.app;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.app.ApplicationUserState;
import ma.zyn.app.dao.criteria.core.app.ApplicationUserStateCriteria;
import ma.zyn.app.dao.facade.core.app.ApplicationUserStateDao;
import ma.zyn.app.dao.specification.core.app.ApplicationUserStateSpecification;
import ma.zyn.app.service.facade.admin.app.ApplicationUserStateAdminService;
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
public class ApplicationUserStateAdminServiceImpl implements ApplicationUserStateAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ApplicationUserState update(ApplicationUserState t) {
        ApplicationUserState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ApplicationUserState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ApplicationUserState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ApplicationUserState findOrSave(ApplicationUserState t) {
        if (t != null) {
            ApplicationUserState result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ApplicationUserState> findAll() {
        return dao.findAll();
    }

    public List<ApplicationUserState> findByCriteria(ApplicationUserStateCriteria criteria) {
        List<ApplicationUserState> content = null;
        if (criteria != null) {
            ApplicationUserStateSpecification mySpecification = constructSpecification(criteria);
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


    private ApplicationUserStateSpecification constructSpecification(ApplicationUserStateCriteria criteria) {
        ApplicationUserStateSpecification mySpecification =  (ApplicationUserStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(ApplicationUserStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<ApplicationUserState> findPaginatedByCriteria(ApplicationUserStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        ApplicationUserStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ApplicationUserStateCriteria criteria) {
        ApplicationUserStateSpecification mySpecification = constructSpecification(criteria);
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
    public List<ApplicationUserState> delete(List<ApplicationUserState> list) {
		List<ApplicationUserState> result = new ArrayList();
        if (list != null) {
            for (ApplicationUserState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ApplicationUserState create(ApplicationUserState t) {
        ApplicationUserState loaded = findByReferenceEntity(t);
        ApplicationUserState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ApplicationUserState findWithAssociatedLists(Long id){
        ApplicationUserState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ApplicationUserState> update(List<ApplicationUserState> ts, boolean createIfNotExist) {
        List<ApplicationUserState> result = new ArrayList<>();
        if (ts != null) {
            for (ApplicationUserState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ApplicationUserState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ApplicationUserState t, ApplicationUserState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ApplicationUserState findByReferenceEntity(ApplicationUserState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ApplicationUserState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ApplicationUserState>> getToBeSavedAndToBeDeleted(List<ApplicationUserState> oldList, List<ApplicationUserState> newList) {
        List<List<ApplicationUserState>> result = new ArrayList<>();
        List<ApplicationUserState> resultDelete = new ArrayList<>();
        List<ApplicationUserState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ApplicationUserState> oldList, List<ApplicationUserState> newList, List<ApplicationUserState> resultUpdateOrSave, List<ApplicationUserState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ApplicationUserState myOld = oldList.get(i);
                ApplicationUserState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ApplicationUserState myNew = newList.get(i);
                ApplicationUserState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ApplicationUserStateAdminServiceImpl(ApplicationUserStateDao dao) {
        this.dao = dao;
    }

    private ApplicationUserStateDao dao;
}
