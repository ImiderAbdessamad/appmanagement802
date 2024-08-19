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

import ma.zyn.app.bean.core.app.ApplicationUser;
import ma.zyn.app.dao.criteria.core.app.ApplicationUserCriteria;
import ma.zyn.app.service.facade.admin.app.ApplicationUserAdminService;
import ma.zyn.app.ws.converter.app.ApplicationUserConverter;
import ma.zyn.app.ws.dto.app.ApplicationUserDto;
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
@RequestMapping("/api/admin/applicationUser/")
public class ApplicationUserRestAdmin {




    @Operation(summary = "Finds a list of all applicationUsers")
    @GetMapping("")
    public ResponseEntity<List<ApplicationUserDto>> findAll() throws Exception {
        ResponseEntity<List<ApplicationUserDto>> res = null;
        List<ApplicationUser> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ApplicationUserDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a applicationUser by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ApplicationUserDto> findById(@PathVariable Long id) {
        ApplicationUser t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ApplicationUserDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  applicationUser")
    @PostMapping("")
    public ResponseEntity<ApplicationUserDto> save(@RequestBody ApplicationUserDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ApplicationUser myT = converter.toItem(dto);
            ApplicationUser t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ApplicationUserDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  applicationUser")
    @PutMapping("")
    public ResponseEntity<ApplicationUserDto> update(@RequestBody ApplicationUserDto dto) throws Exception {
        ResponseEntity<ApplicationUserDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ApplicationUser t = service.findById(dto.getId());
            converter.copy(dto,t);
            ApplicationUser updated = service.update(t);
            ApplicationUserDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of applicationUser")
    @PostMapping("multiple")
    public ResponseEntity<List<ApplicationUserDto>> delete(@RequestBody List<ApplicationUserDto> dtos) throws Exception {
        ResponseEntity<List<ApplicationUserDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ApplicationUser> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified applicationUser")
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

    @Operation(summary = "find by application id")
    @GetMapping("application/id/{id}")
    public List<ApplicationUserDto> findByApplicationId(@PathVariable Long id){
        return findDtos(service.findByApplicationId(id));
    }
    @Operation(summary = "delete by application id")
    @DeleteMapping("application/id/{id}")
    public int deleteByApplicationId(@PathVariable Long id){
        return service.deleteByApplicationId(id);
    }

    @Operation(summary = "Finds a applicationUser and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ApplicationUserDto> findWithAssociatedLists(@PathVariable Long id) {
        ApplicationUser loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ApplicationUserDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds applicationUsers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ApplicationUserDto>> findByCriteria(@RequestBody ApplicationUserCriteria criteria) throws Exception {
        ResponseEntity<List<ApplicationUserDto>> res = null;
        List<ApplicationUser> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ApplicationUserDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated applicationUsers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ApplicationUserCriteria criteria) throws Exception {
        List<ApplicationUser> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ApplicationUserDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets applicationUser data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ApplicationUserCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ApplicationUserDto> findDtos(List<ApplicationUser> list){
        converter.initObject(true);
        List<ApplicationUserDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ApplicationUserDto> getDtoResponseEntity(ApplicationUserDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ApplicationUserRestAdmin(ApplicationUserAdminService service, ApplicationUserConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ApplicationUserAdminService service;
    private final ApplicationUserConverter converter;





}
