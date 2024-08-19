package ma.zyn.app.bean.core.app;

import java.util.List;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "app")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="app_seq",sequenceName="app_seq",allocationSize=1, initialValue = 1)
public class App  extends BaseEntity     {




    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String description;

    private AppState appState ;
    private AppOwner appOwner ;

    private List<ApplicationUser> applicationUser ;

    public App(){
        super();
    }

    public App(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="app_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_state")
    public AppState getAppState(){
        return this.appState;
    }
    public void setAppState(AppState appState){
        this.appState = appState;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_owner")
    public AppOwner getAppOwner(){
        return this.appOwner;
    }
    public void setAppOwner(AppOwner appOwner){
        this.appOwner = appOwner;
    }
    @OneToMany(mappedBy = "app")
    public List<ApplicationUser> getApplicationUser(){
        return this.applicationUser;
    }

    public void setApplicationUser(List<ApplicationUser> applicationUser){
        this.applicationUser = applicationUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        App app = (App) o;
        return id != null && id.equals(app.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

