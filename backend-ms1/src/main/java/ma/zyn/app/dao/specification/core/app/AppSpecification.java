package  ma.zyn.app.dao.specification.core.app;

import ma.zyn.app.dao.criteria.core.app.AppCriteria;
import ma.zyn.app.bean.core.app.App;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class AppSpecification extends  AbstractSpecification<AppCriteria, App>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicateFk("appState","id", criteria.getAppState()==null?null:criteria.getAppState().getId());
        addPredicateFk("appState","id", criteria.getAppStates());
        addPredicateFk("appState","code", criteria.getAppState()==null?null:criteria.getAppState().getCode());
        addPredicateFk("appOwner","id", criteria.getAppOwner()==null?null:criteria.getAppOwner().getId());
        addPredicateFk("appOwner","id", criteria.getAppOwners());
    }

    public AppSpecification(AppCriteria criteria) {
        super(criteria);
    }

    public AppSpecification(AppCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
