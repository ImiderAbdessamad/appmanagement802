package  ma.zyn.app.dao.criteria.core.app;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class AppCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String description;
    private String descriptionLike;

    private AppStateCriteria appState ;
    private List<AppStateCriteria> appStates ;
    private AppOwnerCriteria appOwner ;
    private List<AppOwnerCriteria> appOwners ;


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNameLike(){
        return this.nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }


    public AppStateCriteria getAppState(){
        return this.appState;
    }

    public void setAppState(AppStateCriteria appState){
        this.appState = appState;
    }
    public List<AppStateCriteria> getAppStates(){
        return this.appStates;
    }

    public void setAppStates(List<AppStateCriteria> appStates){
        this.appStates = appStates;
    }
    public AppOwnerCriteria getAppOwner(){
        return this.appOwner;
    }

    public void setAppOwner(AppOwnerCriteria appOwner){
        this.appOwner = appOwner;
    }
    public List<AppOwnerCriteria> getAppOwners(){
        return this.appOwners;
    }

    public void setAppOwners(List<AppOwnerCriteria> appOwners){
        this.appOwners = appOwners;
    }
}
