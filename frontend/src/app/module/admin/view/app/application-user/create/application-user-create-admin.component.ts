import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {ApplicationUserAdminService} from 'src/app/shared/service/admin/app/ApplicationUserAdmin.service';
import {ApplicationUserDto} from 'src/app/shared/model/app/ApplicationUser.model';
import {ApplicationUserCriteria} from 'src/app/shared/criteria/app/ApplicationUserCriteria.model';
import {AppDto} from 'src/app/shared/model/app/App.model';
import {AppAdminService} from 'src/app/shared/service/admin/app/AppAdmin.service';
import {ApplicationUserStateDto} from 'src/app/shared/model/app/ApplicationUserState.model';
import {ApplicationUserStateAdminService} from 'src/app/shared/service/admin/app/ApplicationUserStateAdmin.service';
@Component({
  selector: 'app-application-user-create-admin',
  templateUrl: './application-user-create-admin.component.html'
})
export class ApplicationUserCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



    private _validApplicationUserStateCode = true;
    private _validApplicationUserStateLibelle = true;
    private _validApplicationUserStateStyle = true;

	constructor(private service: ApplicationUserAdminService , private appService: AppAdminService, private applicationUserStateService: ApplicationUserStateAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.appService.findAll().subscribe((data) => this.applications = data);
        this.applicationUserStateService.findAll().subscribe((data) => this.applicationUserStates = data);
    }



    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new ApplicationUserDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }





    public  setValidation(value: boolean){
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }



    public async openCreateApplicationUserState(applicationUserState: string) {
    const isPermistted = await this.roleService.isPermitted('ApplicationUserState', 'add');
    if(isPermistted) {
         this.applicationUserState = new ApplicationUserStateDto();
         this.createApplicationUserStateDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }
    public async openCreateApplication(application: string) {
    const isPermistted = await this.roleService.isPermitted('App', 'add');
    if(isPermistted) {
         this.application = new AppDto();
         this.createApplicationDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
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
    get createApplicationUserStateDialog(): boolean {
        return this.applicationUserStateService.createDialog;
    }
    set createApplicationUserStateDialog(value: boolean) {
        this.applicationUserStateService.createDialog= value;
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
    get createApplicationDialog(): boolean {
        return this.appService.createDialog;
    }
    set createApplicationDialog(value: boolean) {
        this.appService.createDialog= value;
    }




    get validApplicationUserStateCode(): boolean {
        return this._validApplicationUserStateCode;
    }
    set validApplicationUserStateCode(value: boolean) {
        this._validApplicationUserStateCode = value;
    }
    get validApplicationUserStateLibelle(): boolean {
        return this._validApplicationUserStateLibelle;
    }
    set validApplicationUserStateLibelle(value: boolean) {
        this._validApplicationUserStateLibelle = value;
    }
    get validApplicationUserStateStyle(): boolean {
        return this._validApplicationUserStateStyle;
    }
    set validApplicationUserStateStyle(value: boolean) {
        this._validApplicationUserStateStyle = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): ApplicationUserCriteria {
        return this.service.criteria;
    }

    set criteria(value: ApplicationUserCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }

}
