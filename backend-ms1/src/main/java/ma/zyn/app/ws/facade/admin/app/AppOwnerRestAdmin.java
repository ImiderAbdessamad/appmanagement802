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

import ma.zyn.app.bean.core.app.AppOwner;
import ma.zyn.app.dao.criteria.core.app.AppOwnerCriteria;
import ma.zyn.app.service.facade.admin.app.AppOwnerAdminService;
import ma.zyn.app.ws.converter.app.AppOwnerConverter;
import ma.zyn.app.ws.dto.app.AppOwnerDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import ma.zyn.app.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/appOwner/")
public class AppOwnerRestAdmin {




    @Operation(summary = "Finds a list of all appOwners")
    @GetMapping("")
    public ResponseEntity<List<AppOwnerDto>> findAll() throws Exception {
        ResponseEntity<List<AppOwnerDto>> res = null;
        List<AppOwner> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AppOwnerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a appOwner by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AppOwnerDto> findById(@PathVariable Long id) {
        AppOwner t = service.findById(id);
        if (t != null) {
            AppOwnerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  appOwner")
    @PostMapping("")
    public ResponseEntity<AppOwnerDto> save(@RequestBody AppOwnerDto dto) throws Exception {
        if(dto!=null){
            AppOwner myT = converter.toItem(dto);
            AppOwner t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AppOwnerDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  appOwner")
    @PutMapping("")
    public ResponseEntity<AppOwnerDto> update(@RequestBody AppOwnerDto dto) throws Exception {
        ResponseEntity<AppOwnerDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            AppOwner t = service.findById(dto.getId());
            converter.copy(dto,t);
            AppOwner updated = service.update(t);
            AppOwnerDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of appOwner")
    @PostMapping("multiple")
    public ResponseEntity<List<AppOwnerDto>> delete(@RequestBody List<AppOwnerDto> dtos) throws Exception {
        ResponseEntity<List<AppOwnerDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<AppOwner> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified appOwner")
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


    @Operation(summary = "Finds a appOwner and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AppOwnerDto> findWithAssociatedLists(@PathVariable Long id) {
        AppOwner loaded =  service.findWithAssociatedLists(id);
        AppOwnerDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds appOwners by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AppOwnerDto>> findByCriteria(@RequestBody AppOwnerCriteria criteria) throws Exception {
        ResponseEntity<List<AppOwnerDto>> res = null;
        List<AppOwner> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AppOwnerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated appOwners by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AppOwnerCriteria criteria) throws Exception {
        List<AppOwner> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<AppOwnerDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets appOwner data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AppOwnerCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AppOwnerDto> findDtos(List<AppOwner> list){
        List<AppOwnerDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AppOwnerDto> getDtoResponseEntity(AppOwnerDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public AppOwnerRestAdmin(AppOwnerAdminService service, AppOwnerConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final AppOwnerAdminService service;
    private final AppOwnerConverter converter;





}
