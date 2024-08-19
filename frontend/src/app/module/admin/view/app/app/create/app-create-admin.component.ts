import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




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
  selector: 'app-app-create-admin',
  templateUrl: './app-create-admin.component.html'
})
export class AppCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;

    private _applicationUserElement = new ApplicationUserDto();


    private _validAppStateCode = true;
    private _validAppStateLibelle = true;
    private _validAppStateStyle = true;

	constructor(private service: AppAdminService , private applicationUserService: ApplicationUserAdminService, private appStateService: AppStateAdminService, private appOwnerService: AppOwnerAdminService, private applicationUserStateService: ApplicationUserStateAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.applicationUserElement.applicationUserState = new ApplicationUserStateDto();
        this.applicationUserStateService.findAll().subscribe((data) => this.applicationUserStates = data);
        this.appStateService.findAll().subscribe((data) => this.appStates = data);
        this.appOwnerService.findAll().subscribe((data) => this.appOwners = data);
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
                this.item = new AppDto();
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



    validateApplicationUser(){
        this.errorMessages = new Array();
    }


    public  setValidation(value: boolean){
    }

    public addApplicationUser() {
        if( this.item.applicationUser == null )
            this.item.applicationUser = new Array<ApplicationUserDto>();
       this.validateApplicationUser();
       if (this.errorMessages.length === 0) {
              this.item.applicationUser.push({... this.applicationUserElement});
              this.applicationUserElement = new ApplicationUserDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }


    public deleteapplicationUser(p: ApplicationUserDto) {
        this.item.applicationUser.forEach((element, index) => {
            if (element === p) { this.item.applicationUser.splice(index, 1); }
        });
    }

    public editapplicationUser(p: ApplicationUserDto) {
        this.applicationUserElement = {... p};
        this.activeTab = 0;
    }


    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }



    public async openCreateAppOwner(appOwner: string) {
    const isPermistted = await this.roleService.isPermitted('AppOwner', 'add');
    if(isPermistted) {
         this.appOwner = new AppOwnerDto();
         this.createAppOwnerDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
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
    public async openCreateAppState(appState: string) {
    const isPermistted = await this.roleService.isPermitted('AppState', 'add');
    if(isPermistted) {
         this.appState = new AppStateDto();
         this.createAppStateDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
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
    get createAppOwnerDialog(): boolean {
        return this.appOwnerService.createDialog;
    }
    set createAppOwnerDialog(value: boolean) {
        this.appOwnerService.createDialog= value;
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
    get createAppStateDialog(): boolean {
        return this.appStateService.createDialog;
    }
    set createAppStateDialog(value: boolean) {
        this.appStateService.createDialog= value;
    }




    get validAppStateCode(): boolean {
        return this._validAppStateCode;
    }
    set validAppStateCode(value: boolean) {
        this._validAppStateCode = value;
    }
    get validAppStateLibelle(): boolean {
        return this._validAppStateLibelle;
    }
    set validAppStateLibelle(value: boolean) {
        this._validAppStateLibelle = value;
    }
    get validAppStateStyle(): boolean {
        return this._validAppStateStyle;
    }
    set validAppStateStyle(value: boolean) {
        this._validAppStateStyle = value;
    }

    get applicationUserElement(): ApplicationUserDto {
        if( this._applicationUserElement == null )
            this._applicationUserElement = new ApplicationUserDto();
        return this._applicationUserElement;
    }

    set applicationUserElement(value: ApplicationUserDto) {
        this._applicationUserElement = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): AppCriteria {
        return this.service.criteria;
    }

    set criteria(value: AppCriteria) {
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
