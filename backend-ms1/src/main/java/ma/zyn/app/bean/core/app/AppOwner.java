package ma.zyn.app.bean.core.app;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zyn.app.zynerator.security.bean.User;

@Entity
@Table(name = "app_owner")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="app_owner_seq",sequenceName="app_owner_seq",allocationSize=1, initialValue = 1)
public class AppOwner  extends User    {


    public AppOwner(String username) {
        super(username);
    }


    @Column(length = 500)
    private String description;













    public AppOwner(){
        super();
    }

    public AppOwner(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="app_owner_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppOwner appOwner = (AppOwner) o;
        return id != null && id.equals(appOwner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

