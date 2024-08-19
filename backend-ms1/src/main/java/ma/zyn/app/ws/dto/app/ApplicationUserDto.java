package  ma.zyn.app.ws.dto.app;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationUserDto  extends AuditBaseDto {

    private String firstName  ;
    private String lastName  ;
    private String email  ;

    private AppDto application ;
    private ApplicationUserStateDto applicationUserState ;



    public ApplicationUserDto(){
        super();
    }




    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }


    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }


    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }


    public AppDto getApplication(){
        return this.application;
    }

    public void setApplication(AppDto application){
        this.application = application;
    }
    public ApplicationUserStateDto getApplicationUserState(){
        return this.applicationUserState;
    }

    public void setApplicationUserState(ApplicationUserStateDto applicationUserState){
        this.applicationUserState = applicationUserState;
    }






}
