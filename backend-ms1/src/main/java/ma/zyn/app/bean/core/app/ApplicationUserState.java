package ma.zyn.app.bean.core.app;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "application_user_state")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="application_user_state_seq",sequenceName="application_user_state_seq",allocationSize=1, initialValue = 1)
public class ApplicationUserState  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;



    public ApplicationUserState(){
        super();
    }

    public ApplicationUserState(Long id){
        this.id = id;
    }

    public ApplicationUserState(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ApplicationUserState(String libelle){
        this.libelle = libelle ;
    }
    public ApplicationUserState(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="application_user_state_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUserState applicationUserState = (ApplicationUserState) o;
        return id != null && id.equals(applicationUserState.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

