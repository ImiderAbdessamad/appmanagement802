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
import ma.zyn.app.bean.core.app.AppState;
import ma.zyn.app.ws.dto.app.AppStateDto;

@Component
public class AppStateConverter {



    public AppState toItem(AppStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        AppState item = new AppState();
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


    public AppStateDto toDto(AppState item) {
        if (item == null) {
            return null;
        } else {
            AppStateDto dto = new AppStateDto();
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


	
    public List<AppState> toItem(List<AppStateDto> dtos) {
        List<AppState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AppStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AppStateDto> toDto(List<AppState> items) {
        List<AppStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (AppState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AppStateDto dto, AppState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<AppState> copy(List<AppStateDto> dtos) {
        List<AppState> result = new ArrayList<>();
        if (dtos != null) {
            for (AppStateDto dto : dtos) {
                AppState instance = new AppState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
