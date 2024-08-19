package  ma.zyn.app.ws.dto.app;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppDto  extends AuditBaseDto {

    private String name  ;
    private String description  ;

    private AppStateDto appState ;
    private AppOwnerDto appOwner ;

    private List<ApplicationUserDto> applicationUser ;


    public AppDto(){
        super();
    }




    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public AppStateDto getAppState(){
        return this.appState;
    }

    public void setAppState(AppStateDto appState){
        this.appState = appState;
    }
    public AppOwnerDto getAppOwner(){
        return this.appOwner;
    }

    public void setAppOwner(AppOwnerDto appOwner){
        this.appOwner = appOwner;
    }



    public List<ApplicationUserDto> getApplicationUser(){
        return this.applicationUser;
    }

    public void setApplicationUser(List<ApplicationUserDto> applicationUser){
        this.applicationUser = applicationUser;
    }



}
