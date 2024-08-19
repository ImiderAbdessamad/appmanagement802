package ma.zyn.app.bean.core.app;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "application_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="application_user_seq",sequenceName="application_user_seq",allocationSize=1, initialValue = 1)
public class ApplicationUser  extends BaseEntity     {




    @Column(length = 500)
    private String firstName;

    @Column(length = 500)
    private String lastName;

    @Column(length = 500)
    private String email;

    private App application ;
    private ApplicationUserState applicationUserState ;


    public ApplicationUser(){
        super();
    }

    public ApplicationUser(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="application_user_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application")
    public App getApplication(){
        return this.application;
    }
    public void setApplication(App application){
        this.application = application;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_user_state")
    public ApplicationUserState getApplicationUserState(){
        return this.applicationUserState;
    }
    public void setApplicationUserState(ApplicationUserState applicationUserState){
        this.applicationUserState = applicationUserState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUser applicationUser = (ApplicationUser) o;
        return id != null && id.equals(applicationUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

