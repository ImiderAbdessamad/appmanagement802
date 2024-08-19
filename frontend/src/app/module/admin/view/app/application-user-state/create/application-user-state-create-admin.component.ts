import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {ApplicationUserStateAdminService} from 'src/app/shared/service/admin/app/ApplicationUserStateAdmin.service';
import {ApplicationUserStateDto} from 'src/app/shared/model/app/ApplicationUserState.model';
import {ApplicationUserStateCriteria} from 'src/app/shared/criteria/app/ApplicationUserStateCriteria.model';
@Component({
  selector: 'app-application-user-state-create-admin',
  templateUrl: './application-user-state-create-admin.component.html'
})
export class ApplicationUserStateCreateAdminComponent  implements OnInit {

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

	constructor(private service: ApplicationUserStateAdminService , @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
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
                this.item = new ApplicationUserStateDto();
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
        this.validApplicationUserStateCode = value;
        this.validApplicationUserStateLibelle = value;
        this.validApplicationUserStateStyle = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateApplicationUserStateCode();
        this.validateApplicationUserStateLibelle();
        this.validateApplicationUserStateStyle();
    }

    public validateApplicationUserStateCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validApplicationUserStateCode = false;
        } else {
            this.validApplicationUserStateCode = true;
        }
    }
    public validateApplicationUserStateLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validApplicationUserStateLibelle = false;
        } else {
            this.validApplicationUserStateLibelle = true;
        }
    }
    public validateApplicationUserStateStyle(){
        if (this.stringUtilService.isEmpty(this.item.style)) {
        this.errorMessages.push('Style non valide');
        this.validApplicationUserStateStyle = false;
        } else {
            this.validApplicationUserStateStyle = true;
        }
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



    get items(): Array<ApplicationUserStateDto> {
        return this.service.items;
    }

    set items(value: Array<ApplicationUserStateDto>) {
        this.service.items = value;
    }

    get item(): ApplicationUserStateDto {
        return this.service.item;
    }

    set item(value: ApplicationUserStateDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): ApplicationUserStateCriteria {
        return this.service.criteria;
    }

    set criteria(value: ApplicationUserStateCriteria) {
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
