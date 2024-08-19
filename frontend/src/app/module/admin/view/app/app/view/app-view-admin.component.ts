import {Component, OnInit} from '@angular/core';


import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


import {AppAdminService} from 'src/app/shared/service/admin/app/AppAdmin.service';
import {AppDto} from 'src/app/shared/model/app/App.model';
import {AppCriteria} from 'src/app/shared/criteria/app/AppCriteria.model';

import {ApplicationUserDto} from 'src/app/shared/model/app/ApplicationUser.model';
import {ApplicationUserAdminService} from 'src/app/shared/service/admin/app/ApplicationUserAdmin.service';
import {AppStateDto} from 'src/app/shared/model/app/AppState.model';
import {AppStateAdminService} from 'src/app/shared/service/admin/app/AppStateAdmin.service';
import {AppOwnerDto} from 'src/app/shared/model/app/AppOwner.model';
import {AppOwnerAdminService} from 'src/app/shared/service/admin/app/AppOwnerAdmin.service';
import {ApplicationUserStateDto} from 'src/app/shared/model/app/ApplicationUserState.model';
import {ApplicationUserStateAdminService} from 'src/app/shared/service/admin/app/ApplicationUserStateAdmin.service';
@Component({
  selector: 'app-app-view-admin',
  templateUrl: './app-view-admin.component.html'
})
export class AppViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    applicationUser = new ApplicationUserDto();
    applicationUsers: Array<ApplicationUserDto> = [];

    constructor(private service: AppAdminService, private applicationUserService: ApplicationUserAdminService, private appStateService: AppStateAdminService, private appOwnerService: AppOwnerAdminService, private applicationUserStateService: ApplicationUserStateAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get appOwner(): AppOwnerDto {
        return this.appOwnerService.item;
    }
    set appOwner(value: AppOwnerDto) {
        this.appOwnerService.item = value;
    }
    get appOwners(): Array<AppOwnerDto> {
        return this.appOwnerService.items;
    }
    set appOwners(value: Array<AppOwnerDto>) {
        this.appOwnerService.items = value;
    }
    get applicationUserState(): ApplicationUserStateDto {
        return this.applicationUserStateService.item;
    }
    set applicationUserState(value: ApplicationUserStateDto) {
        this.applicationUserStateService.item = value;
    }
    get applicationUserStates(): Array<ApplicationUserStateDto> {
        return this.applicationUserStateService.items;
    }
    set applicationUserStates(value: Array<ApplicationUserStateDto>) {
        this.applicationUserStateService.items = value;
    }
    get appState(): AppStateDto {
        return this.appStateService.item;
    }
    set appState(value: AppStateDto) {
        this.appStateService.item = value;
    }
    get appStates(): Array<AppStateDto> {
        return this.appStateService.items;
    }
    set appStates(value: Array<AppStateDto>) {
        this.appStateService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<AppDto> {
        return this.service.items;
    }

    set items(value: Array<AppDto>) {
        this.service.items = value;
    }

    get item(): AppDto {
        return this.service.item;
    }

    set item(value: AppDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): AppCriteria {
        return this.service.criteria;
    }

    set criteria(value: AppCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
