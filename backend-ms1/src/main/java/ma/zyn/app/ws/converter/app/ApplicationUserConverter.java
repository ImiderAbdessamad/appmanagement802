package  ma.zyn.app.ws.converter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.app.AppConverter;
import ma.zyn.app.bean.core.app.App;
import ma.zyn.app.ws.converter.app.ApplicationUserStateConverter;
import ma.zyn.app.bean.core.app.ApplicationUserState;

import ma.zyn.app.bean.core.app.App;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.app.ApplicationUser;
import ma.zyn.app.ws.dto.app.ApplicationUserDto;

@Component
public class ApplicationUserConverter {

    @Autowired
    private AppConverter appConverter ;
    @Autowired
    private ApplicationUserStateConverter applicationUserStateConverter ;
    private boolean application;
    private boolean applicationUserState;

    public  ApplicationUserConverter() {
        initObject(true);
    }

    public ApplicationUser toItem(ApplicationUserDto dto) {
        if (dto == null) {
            return null;
        } else {
        ApplicationUser item = new ApplicationUser();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getFirstName()))
                item.setFirstName(dto.getFirstName());
            if(StringUtil.isNotEmpty(dto.getLastName()))
                item.setLastName(dto.getLastName());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(dto.getApplication() != null && dto.getApplication().getId() != null){
                item.setApplication(new App());
                item.getApplication().setId(dto.getApplication().getId());
                item.getApplication().setId(dto.getApplication().getId());
            }

            if(this.applicationUserState && dto.getApplicationUserState()!=null)
                item.setApplicationUserState(applicationUserStateConverter.toItem(dto.getApplicationUserState())) ;




        return item;
        }
    }


    public ApplicationUserDto toDto(ApplicationUser item) {
        if (item == null) {
            return null;
        } else {
            ApplicationUserDto dto = new ApplicationUserDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getFirstName()))
                dto.setFirstName(item.getFirstName());
            if(StringUtil.isNotEmpty(item.getLastName()))
                dto.setLastName(item.getLastName());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(this.application && item.getApplication()!=null) {
                dto.setApplication(appConverter.toDto(item.getApplication())) ;

            }
            if(this.applicationUserState && item.getApplicationUserState()!=null) {
                dto.setApplicationUserState(applicationUserStateConverter.toDto(item.getApplicationUserState())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.application = value;
        this.applicationUserState = value;
    }
	
    public List<ApplicationUser> toItem(List<ApplicationUserDto> dtos) {
        List<ApplicationUser> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ApplicationUserDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ApplicationUserDto> toDto(List<ApplicationUser> items) {
        List<ApplicationUserDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ApplicationUser item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ApplicationUserDto dto, ApplicationUser t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getApplication() == null  && dto.getApplication() != null){
            t.setApplication(new App());
        }else if (t.getApplication() != null  && dto.getApplication() != null){
            t.setApplication(null);
            t.setApplication(new App());
        }
        if(t.getApplicationUserState() == null  && dto.getApplicationUserState() != null){
            t.setApplicationUserState(new ApplicationUserState());
        }else if (t.getApplicationUserState() != null  && dto.getApplicationUserState() != null){
            t.setApplicationUserState(null);
            t.setApplicationUserState(new ApplicationUserState());
        }
        if (dto.getApplication() != null)
        appConverter.copy(dto.getApplication(), t.getApplication());
        if (dto.getApplicationUserState() != null)
        applicationUserStateConverter.copy(dto.getApplicationUserState(), t.getApplicationUserState());
    }

    public List<ApplicationUser> copy(List<ApplicationUserDto> dtos) {
        List<ApplicationUser> result = new ArrayList<>();
        if (dtos != null) {
            for (ApplicationUserDto dto : dtos) {
                ApplicationUser instance = new ApplicationUser();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public AppConverter getAppConverter(){
        return this.appConverter;
    }
    public void setAppConverter(AppConverter appConverter ){
        this.appConverter = appConverter;
    }
    public ApplicationUserStateConverter getApplicationUserStateConverter(){
        return this.applicationUserStateConverter;
    }
    public void setApplicationUserStateConverter(ApplicationUserStateConverter applicationUserStateConverter ){
        this.applicationUserStateConverter = applicationUserStateConverter;
    }
    public boolean  isApplication(){
        return this.application;
    }
    public void  setApplication(boolean application){
        this.application = application;
    }
    public boolean  isApplicationUserState(){
        return this.applicationUserState;
    }
    public void  setApplicationUserState(boolean applicationUserState){
        this.applicationUserState = applicationUserState;
    }
}
