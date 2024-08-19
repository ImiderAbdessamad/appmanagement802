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

import ma.zyn.app.bean.core.app.AppState;
import ma.zyn.app.dao.criteria.core.app.AppStateCriteria;
import ma.zyn.app.service.facade.admin.app.AppStateAdminService;
import ma.zyn.app.ws.converter.app.AppStateConverter;
import ma.zyn.app.ws.dto.app.AppStateDto;
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
@RequestMapping("/api/admin/appState/")
public class AppStateRestAdmin {




    @Operation(summary = "Finds a list of all appStates")
    @GetMapping("")
    public ResponseEntity<List<AppStateDto>> findAll() throws Exception {
        ResponseEntity<List<AppStateDto>> res = null;
        List<AppState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AppStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all appStates")
    @GetMapping("optimized")
    public ResponseEntity<List<AppStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<AppStateDto>> res = null;
        List<AppState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AppStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a appState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AppStateDto> findById(@PathVariable Long id) {
        AppState t = service.findById(id);
        if (t != null) {
            AppStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a appState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<AppStateDto> findByLibelle(@PathVariable String libelle) {
	    AppState t = service.findByReferenceEntity(new AppState(libelle));
        if (t != null) {
            AppStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  appState")
    @PostMapping("")
    public ResponseEntity<AppStateDto> save(@RequestBody AppStateDto dto) throws Exception {
        if(dto!=null){
            AppState myT = converter.toItem(dto);
            AppState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AppStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  appState")
    @PutMapping("")
    public ResponseEntity<AppStateDto> update(@RequestBody AppStateDto dto) throws Exception {
        ResponseEntity<AppStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            AppState t = service.findById(dto.getId());
            converter.copy(dto,t);
            AppState updated = service.update(t);
            AppStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of appState")
    @PostMapping("multiple")
    public ResponseEntity<List<AppStateDto>> delete(@RequestBody List<AppStateDto> dtos) throws Exception {
        ResponseEntity<List<AppStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<AppState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified appState")
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


    @Operation(summary = "Finds a appState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AppStateDto> findWithAssociatedLists(@PathVariable Long id) {
        AppState loaded =  service.findWithAssociatedLists(id);
        AppStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds appStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AppStateDto>> findByCriteria(@RequestBody AppStateCriteria criteria) throws Exception {
        ResponseEntity<List<AppStateDto>> res = null;
        List<AppState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AppStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated appStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AppStateCriteria criteria) throws Exception {
        List<AppState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<AppStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets appState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AppStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AppStateDto> findDtos(List<AppState> list){
        List<AppStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AppStateDto> getDtoResponseEntity(AppStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public AppStateRestAdmin(AppStateAdminService service, AppStateConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final AppStateAdminService service;
    private final AppStateConverter converter;





}
