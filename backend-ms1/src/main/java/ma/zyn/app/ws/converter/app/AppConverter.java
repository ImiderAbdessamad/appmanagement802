package  ma.zyn.app.ws.converter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.app.ApplicationUserConverter;
import ma.zyn.app.bean.core.app.ApplicationUser;
import ma.zyn.app.ws.converter.app.AppStateConverter;
import ma.zyn.app.bean.core.app.AppState;
import ma.zyn.app.ws.converter.app.AppOwnerConverter;
import ma.zyn.app.bean.core.app.AppOwner;
import ma.zyn.app.ws.converter.app.ApplicationUserStateConverter;
import ma.zyn.app.bean.core.app.ApplicationUserState;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.app.App;
import ma.zyn.app.ws.dto.app.AppDto;

@Component
public class AppConverter {

    @Autowired
    private ApplicationUserConverter applicationUserConverter ;
    @Autowired
    private AppStateConverter appStateConverter ;
    @Autowired
    private AppOwnerConverter appOwnerConverter ;
    @Autowired
    private ApplicationUserStateConverter applicationUserStateConverter ;
    private boolean appState;
    private boolean appOwner;
    private boolean applicationUser;

    public  AppConverter() {
        init(true);
    }

    public App toItem(AppDto dto) {
        if (dto == null) {
            return null;
        } else {
        App item = new App();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.appState && dto.getAppState()!=null)
                item.setAppState(appStateConverter.toItem(dto.getAppState())) ;

            if(this.appOwner && dto.getAppOwner()!=null)
                item.setAppOwner(appOwnerConverter.toItem(dto.getAppOwner())) ;


            if(this.applicationUser && ListUtil.isNotEmpty(dto.getApplicationUser()))
                item.setApplicationUser(applicationUserConverter.toItem(dto.getApplicationUser()));


        return item;
        }
    }


    public AppDto toDto(App item) {
        if (item == null) {
            return null;
        } else {
            AppDto dto = new AppDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.appState && item.getAppState()!=null) {
                dto.setAppState(appStateConverter.toDto(item.getAppState())) ;

            }
            if(this.appOwner && item.getAppOwner()!=null) {
                dto.setAppOwner(appOwnerConverter.toDto(item.getAppOwner())) ;

            }
        if(this.applicationUser && ListUtil.isNotEmpty(item.getApplicationUser())){
            applicationUserConverter.init(true);
            applicationUserConverter.setApp(false);
            dto.setApplicationUser(applicationUserConverter.toDto(item.getApplicationUser()));
            applicationUserConverter.setApp(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.applicationUser = value;
    }
    public void initObject(boolean value) {
        this.appState = value;
        this.appOwner = value;
    }
	
    public List<App> toItem(List<AppDto> dtos) {
        List<App> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AppDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AppDto> toDto(List<App> items) {
        List<AppDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (App item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AppDto dto, App t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getAppState() == null  && dto.getAppState() != null){
            t.setAppState(new AppState());
        }else if (t.getAppState() != null  && dto.getAppState() != null){
            t.setAppState(null);
            t.setAppState(new AppState());
        }
        if(t.getAppOwner() == null  && dto.getAppOwner() != null){
            t.setAppOwner(new AppOwner());
        }else if (t.getAppOwner() != null  && dto.getAppOwner() != null){
            t.setAppOwner(null);
            t.setAppOwner(new AppOwner());
        }
        if (dto.getAppState() != null)
        appStateConverter.copy(dto.getAppState(), t.getAppState());
        if (dto.getAppOwner() != null)
        appOwnerConverter.copy(dto.getAppOwner(), t.getAppOwner());
        if (dto.getApplicationUser() != null)
            t.setApplicationUser(applicationUserConverter.copy(dto.getApplicationUser()));
    }

    public List<App> copy(List<AppDto> dtos) {
        List<App> result = new ArrayList<>();
        if (dtos != null) {
            for (AppDto dto : dtos) {
                App instance = new App();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ApplicationUserConverter getApplicationUserConverter(){
        return this.applicationUserConverter;
    }
    public void setApplicationUserConverter(ApplicationUserConverter applicationUserConverter ){
        this.applicationUserConverter = applicationUserConverter;
    }
    public AppStateConverter getAppStateConverter(){
        return this.appStateConverter;
    }
    public void setAppStateConverter(AppStateConverter appStateConverter ){
        this.appStateConverter = appStateConverter;
    }
    public AppOwnerConverter getAppOwnerConverter(){
        return this.appOwnerConverter;
    }
    public void setAppOwnerConverter(AppOwnerConverter appOwnerConverter ){
        this.appOwnerConverter = appOwnerConverter;
    }
    public ApplicationUserStateConverter getApplicationUserStateConverter(){
        return this.applicationUserStateConverter;
    }
    public void setApplicationUserStateConverter(ApplicationUserStateConverter applicationUserStateConverter ){
        this.applicationUserStateConverter = applicationUserStateConverter;
    }
    public boolean  isAppState(){
        return this.appState;
    }
    public void  setAppState(boolean appState){
        this.appState = appState;
    }
    public boolean  isAppOwner(){
        return this.appOwner;
    }
    public void  setAppOwner(boolean appOwner){
        this.appOwner = appOwner;
    }
    public boolean  isApplicationUser(){
        return this.applicationUser ;
    }
    public void  setApplicationUser(boolean applicationUser ){
        this.applicationUser  = applicationUser ;
    }
}
