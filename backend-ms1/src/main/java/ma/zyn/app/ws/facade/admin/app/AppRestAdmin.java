package  ma.zyn.app.ws.facade.admin.app;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.app.App;
import ma.zyn.app.dao.criteria.core.app.AppCriteria;
import ma.zyn.app.service.facade.admin.app.AppAdminService;
import ma.zyn.app.ws.converter.app.AppConverter;
import ma.zyn.app.ws.dto.app.AppDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/app/")
public class AppRestAdmin {




    @Operation(summary = "Finds a list of all apps")
    @GetMapping("")
    public ResponseEntity<List<AppDto>> findAll() throws Exception {
        ResponseEntity<List<AppDto>> res = null;
        List<App> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<AppDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a app by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AppDto> findById(@PathVariable Long id) {
        App t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AppDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  app")
    @PostMapping("")
    public ResponseEntity<AppDto> save(@RequestBody AppDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            App myT = converter.toItem(dto);
            App t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AppDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  app")
    @PutMapping("")
    public ResponseEntity<AppDto> update(@RequestBody AppDto dto) throws Exception {
        ResponseEntity<AppDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            App t = service.findById(dto.getId());
            converter.copy(dto,t);
            App updated = service.update(t);
            AppDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of app")
    @PostMapping("multiple")
    public ResponseEntity<List<AppDto>> delete(@RequestBody List<AppDto> dtos) throws Exception {
        ResponseEntity<List<AppDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<App> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified app")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a app and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AppDto> findWithAssociatedLists(@PathVariable Long id) {
        App loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AppDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds apps by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AppDto>> findByCriteria(@RequestBody AppCriteria criteria) throws Exception {
        ResponseEntity<List<AppDto>> res = null;
        List<App> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<AppDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated apps by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AppCriteria criteria) throws Exception {
        List<App> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<AppDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets app data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AppCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AppDto> findDtos(List<App> list){
        converter.initList(false);
        converter.initObject(true);
        List<AppDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AppDto> getDtoResponseEntity(AppDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public AppRestAdmin(AppAdminService service, AppConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final AppAdminService service;
    private final AppConverter converter;





}
