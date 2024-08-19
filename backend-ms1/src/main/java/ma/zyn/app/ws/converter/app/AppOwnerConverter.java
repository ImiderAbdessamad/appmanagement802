package  ma.zyn.app.ws.converter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.app.AppOwner;
import ma.zyn.app.ws.dto.app.AppOwnerDto;

@Component
public class AppOwnerConverter {



    public AppOwner toItem(AppOwnerDto dto) {
        if (dto == null) {
            return null;
        } else {
        AppOwner item = new AppOwner();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setEnabled(dto.getEnabled());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            item.setPasswordChanged(dto.getPasswordChanged());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(StringUtil.isNotEmpty(dto.getAvatar()))
                item.setAvatar(dto.getAvatar());
            if(StringUtil.isNotEmpty(dto.getFullName()))
                item.setFullName(dto.getFullName());
            if(StringUtil.isNotEmpty(dto.getAbout()))
                item.setAbout(dto.getAbout());


            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public AppOwnerDto toDto(AppOwner item) {
        if (item == null) {
            return null;
        } else {
            AppOwnerDto dto = new AppOwnerDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getAvatar()))
                dto.setAvatar(item.getAvatar());
            if(StringUtil.isNotEmpty(item.getFullName()))
                dto.setFullName(item.getFullName());
            if(StringUtil.isNotEmpty(item.getAbout()))
                dto.setAbout(item.getAbout());


        return dto;
        }
    }


	
    public List<AppOwner> toItem(List<AppOwnerDto> dtos) {
        List<AppOwner> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AppOwnerDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AppOwnerDto> toDto(List<AppOwner> items) {
        List<AppOwnerDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (AppOwner item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AppOwnerDto dto, AppOwner t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<AppOwner> copy(List<AppOwnerDto> dtos) {
        List<AppOwner> result = new ArrayList<>();
        if (dtos != null) {
            for (AppOwnerDto dto : dtos) {
                AppOwner instance = new AppOwner();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
