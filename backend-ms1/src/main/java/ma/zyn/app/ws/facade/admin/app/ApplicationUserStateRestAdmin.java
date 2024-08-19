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

import ma.zyn.app.bean.core.app.ApplicationUserState;
import ma.zyn.app.dao.criteria.core.app.ApplicationUserStateCriteria;
import ma.zyn.app.service.facade.admin.app.ApplicationUserStateAdminService;
import ma.zyn.app.ws.converter.app.ApplicationUserStateConverter;
import ma.zyn.app.ws.dto.app.ApplicationUserStateDto;
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
@RequestMapping("/api/admin/applicationUserState/")
public class ApplicationUserStateRestAdmin {




    @Operation(summary = "Finds a list of all applicationUserStates")
    @GetMapping("")
    public ResponseEntity<List<ApplicationUserStateDto>> findAll() throws Exception {
        ResponseEntity<List<ApplicationUserStateDto>> res = null;
        List<ApplicationUserState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ApplicationUserStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all applicationUserStates")
    @GetMapping("optimized")
    public ResponseEntity<List<ApplicationUserStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ApplicationUserStateDto>> res = null;
        List<ApplicationUserState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ApplicationUserStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a applicationUserState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ApplicationUserStateDto> findById(@PathVariable Long id) {
        ApplicationUserState t = service.findById(id);
        if (t != null) {
            ApplicationUserStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a applicationUserState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ApplicationUserStateDto> findByLibelle(@PathVariable String libelle) {
	    ApplicationUserState t = service.findByReferenceEntity(new ApplicationUserState(libelle));
        if (t != null) {
            ApplicationUserStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  applicationUserState")
    @PostMapping("")
    public ResponseEntity<ApplicationUserStateDto> save(@RequestBody ApplicationUserStateDto dto) throws Exception {
        if(dto!=null){
            ApplicationUserState myT = converter.toItem(dto);
            ApplicationUserState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ApplicationUserStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  applicationUserState")
    @PutMapping("")
    public ResponseEntity<ApplicationUserStateDto> update(@RequestBody ApplicationUserStateDto dto) throws Exception {
        ResponseEntity<ApplicationUserStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ApplicationUserState t = service.findById(dto.getId());
            converter.copy(dto,t);
            ApplicationUserState updated = service.update(t);
            ApplicationUserStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of applicationUserState")
    @PostMapping("multiple")
    public ResponseEntity<List<ApplicationUserStateDto>> delete(@RequestBody List<ApplicationUserStateDto> dtos) throws Exception {
        ResponseEntity<List<ApplicationUserStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ApplicationUserState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified applicationUserState")
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


    @Operation(summary = "Finds a applicationUserState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ApplicationUserStateDto> findWithAssociatedLists(@PathVariable Long id) {
        ApplicationUserState loaded =  service.findWithAssociatedLists(id);
        ApplicationUserStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds applicationUserStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ApplicationUserStateDto>> findByCriteria(@RequestBody ApplicationUserStateCriteria criteria) throws Exception {
        ResponseEntity<List<ApplicationUserStateDto>> res = null;
        List<ApplicationUserState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ApplicationUserStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated applicationUserStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ApplicationUserStateCriteria criteria) throws Exception {
        List<ApplicationUserState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ApplicationUserStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets applicationUserState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ApplicationUserStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ApplicationUserStateDto> findDtos(List<ApplicationUserState> list){
        List<ApplicationUserStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ApplicationUserStateDto> getDtoResponseEntity(ApplicationUserStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ApplicationUserStateRestAdmin(ApplicationUserStateAdminService service, ApplicationUserStateConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ApplicationUserStateAdminService service;
    private final ApplicationUserStateConverter converter;





}
