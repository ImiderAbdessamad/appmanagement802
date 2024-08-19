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
import ma.zyn.app.bean.core.app.ApplicationUserState;
import ma.zyn.app.ws.dto.app.ApplicationUserStateDto;

@Component
public class ApplicationUserStateConverter {



    public ApplicationUserState toItem(ApplicationUserStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        ApplicationUserState item = new ApplicationUserState();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());



        return item;
        }
    }


    public ApplicationUserStateDto toDto(ApplicationUserState item) {
        if (item == null) {
            return null;
        } else {
            ApplicationUserStateDto dto = new ApplicationUserStateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());


        return dto;
        }
    }


	
    public List<ApplicationUserState> toItem(List<ApplicationUserStateDto> dtos) {
        List<ApplicationUserState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ApplicationUserStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ApplicationUserStateDto> toDto(List<ApplicationUserState> items) {
        List<ApplicationUserStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ApplicationUserState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ApplicationUserStateDto dto, ApplicationUserState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ApplicationUserState> copy(List<ApplicationUserStateDto> dtos) {
        List<ApplicationUserState> result = new ArrayList<>();
        if (dtos != null) {
            for (ApplicationUserStateDto dto : dtos) {
                ApplicationUserState instance = new ApplicationUserState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
