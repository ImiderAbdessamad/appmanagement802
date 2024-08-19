package  ma.zyn.app.dao.criteria.core.app;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ApplicationUserCriteria extends  BaseCriteria  {

    private String firstName;
    private String firstNameLike;
    private String lastName;
    private String lastNameLike;
    private String email;
    private String emailLike;

    private AppCriteria application ;
    private List<AppCriteria> applications ;
    private ApplicationUserStateCriteria applicationUserState ;
    private List<ApplicationUserStateCriteria> applicationUserStates ;


    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstNameLike(){
        return this.firstNameLike;
    }
    public void setFirstNameLike(String firstNameLike){
        this.firstNameLike = firstNameLike;
    }

    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastNameLike(){
        return this.lastNameLike;
    }
    public void setLastNameLike(String lastNameLike){
        this.lastNameLike = lastNameLike;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }


    public AppCriteria getApplication(){
        return this.application;
    }

    public void setApplication(AppCriteria application){
        this.application = application;
    }
    public List<AppCriteria> getApplications(){
        return this.applications;
    }

    public void setApplications(List<AppCriteria> applications){
        this.applications = applications;
    }
    public ApplicationUserStateCriteria getApplicationUserState(){
        return this.applicationUserState;
    }

    public void setApplicationUserState(ApplicationUserStateCriteria applicationUserState){
        this.applicationUserState = applicationUserState;
    }
    public List<ApplicationUserStateCriteria> getApplicationUserStates(){
        return this.applicationUserStates;
    }

    public void setApplicationUserStates(List<ApplicationUserStateCriteria> applicationUserStates){
        this.applicationUserStates = applicationUserStates;
    }
}
