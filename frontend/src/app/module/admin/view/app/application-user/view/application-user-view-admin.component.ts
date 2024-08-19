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


import {ApplicationUserAdminService} from 'src/app/shared/service/admin/app/ApplicationUserAdmin.service';
import {ApplicationUserDto} from 'src/app/shared/model/app/ApplicationUser.model';
import {ApplicationUserCriteria} from 'src/app/shared/criteria/app/ApplicationUserCriteria.model';

import {AppDto} from 'src/app/shared/model/app/App.model';
import {AppAdminService} from 'src/app/shared/service/admin/app/AppAdmin.service';
import {ApplicationUserStateDto} from 'src/app/shared/model/app/ApplicationUserState.model';
import {ApplicationUserStateAdminService} from 'src/app/shared/service/admin/app/ApplicationUserStateAdmin.service';
@Component({
  selector: 'app-application-user-view-admin',
  templateUrl: './application-user-view-admin.component.html'
})
export class ApplicationUserViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: ApplicationUserAdminService, private appService: AppAdminService, private applicationUserStateService: ApplicationUserStateAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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
    get application(): AppDto {
        return this.appService.item;
    }
    set application(value: AppDto) {
        this.appService.item = value;
    }
    get applications(): Array<AppDto> {
        return this.appService.items;
    }
    set applications(value: Array<AppDto>) {
        this.appService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<ApplicationUserDto> {
        return this.service.items;
    }

    set items(value: Array<ApplicationUserDto>) {
        this.service.items = value;
    }

    get item(): ApplicationUserDto {
        return this.service.item;
    }

    set item(value: ApplicationUserDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): ApplicationUserCriteria {
        return this.service.criteria;
    }

    set criteria(value: ApplicationUserCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
