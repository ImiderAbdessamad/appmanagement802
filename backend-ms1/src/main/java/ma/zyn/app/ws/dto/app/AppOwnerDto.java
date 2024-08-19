package  ma.zyn.app.ws.dto.app;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zyn.app.zynerator.security.bean.Role;
import java.util.Collection;
import ma.zyn.app.zynerator.security.ws.dto.UserDto;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppOwnerDto  extends UserDto {

    private String description  ;




    private Collection<Role> roles;
    public AppOwnerDto(){
        super();
    }




    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }



















    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
