package  ma.zyn.app.dao.specification.core.app;

import ma.zyn.app.dao.criteria.core.app.ApplicationUserStateCriteria;
import ma.zyn.app.bean.core.app.ApplicationUserState;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ApplicationUserStateSpecification extends  AbstractSpecification<ApplicationUserStateCriteria, ApplicationUserState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public ApplicationUserStateSpecification(ApplicationUserStateCriteria criteria) {
        super(criteria);
    }

    public ApplicationUserStateSpecification(ApplicationUserStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
