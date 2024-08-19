package  ma.zyn.app.dao.specification.core.app;

import ma.zyn.app.dao.criteria.core.app.ApplicationUserCriteria;
import ma.zyn.app.bean.core.app.ApplicationUser;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ApplicationUserSpecification extends  AbstractSpecification<ApplicationUserCriteria, ApplicationUser>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("firstName", criteria.getFirstName(),criteria.getFirstNameLike());
        addPredicate("lastName", criteria.getLastName(),criteria.getLastNameLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateFk("application","id", criteria.getApplication()==null?null:criteria.getApplication().getId());
        addPredicateFk("application","id", criteria.getApplications());
        addPredicateFk("applicationUserState","id", criteria.getApplicationUserState()==null?null:criteria.getApplicationUserState().getId());
        addPredicateFk("applicationUserState","id", criteria.getApplicationUserStates());
        addPredicateFk("applicationUserState","code", criteria.getApplicationUserState()==null?null:criteria.getApplicationUserState().getCode());
    }

    public ApplicationUserSpecification(ApplicationUserCriteria criteria) {
        super(criteria);
    }

    public ApplicationUserSpecification(ApplicationUserCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
