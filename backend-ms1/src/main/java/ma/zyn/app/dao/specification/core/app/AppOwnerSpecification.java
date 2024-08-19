package  ma.zyn.app.dao.specification.core.app;

import ma.zyn.app.dao.criteria.core.app.AppOwnerCriteria;
import ma.zyn.app.bean.core.app.AppOwner;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class AppOwnerSpecification extends  AbstractSpecification<AppOwnerCriteria, AppOwner>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicate("avatar", criteria.getAvatar(),criteria.getAvatarLike());
        addPredicate("fullName", criteria.getFullName(),criteria.getFullNameLike());
        addPredicate("about", criteria.getAbout(),criteria.getAboutLike());
    }

    public AppOwnerSpecification(AppOwnerCriteria criteria) {
        super(criteria);
    }

    public AppOwnerSpecification(AppOwnerCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
