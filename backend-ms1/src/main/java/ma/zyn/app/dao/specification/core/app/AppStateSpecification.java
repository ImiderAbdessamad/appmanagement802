package  ma.zyn.app.dao.specification.core.app;

import ma.zyn.app.dao.criteria.core.app.AppStateCriteria;
import ma.zyn.app.bean.core.app.AppState;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class AppStateSpecification extends  AbstractSpecification<AppStateCriteria, AppState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public AppStateSpecification(AppStateCriteria criteria) {
        super(criteria);
    }

    public AppStateSpecification(AppStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
