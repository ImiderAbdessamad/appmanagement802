import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {AppStateAdminService} from 'src/app/shared/service/admin/app/AppStateAdmin.service';
import {AppStateDto} from 'src/app/shared/model/app/AppState.model';
import {AppStateCriteria} from 'src/app/shared/criteria/app/AppStateCriteria.model';
@Component({
  selector: 'app-app-state-create-admin',
  templateUrl: './app-state-create-admin.component.html'
})
export class AppStateCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validAppStateCode = true;
   private _validAppStateLibelle = true;
   private _validAppStateStyle = true;

	constructor(private service: AppStateAdminService , @Inject(PLATFORM_ID) private platformId? ) {
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
                this.item = new AppStateDto();
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
        this.validAppStateCode = value;
        this.validAppStateLibelle = value;
        this.validAppStateStyle = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateAppStateCode();
        this.validateAppStateLibelle();
        this.validateAppStateStyle();
    }

    public validateAppStateCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validAppStateCode = false;
        } else {
            this.validAppStateCode = true;
        }
    }
    public validateAppStateLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validAppStateLibelle = false;
        } else {
            this.validAppStateLibelle = true;
        }
    }
    public validateAppStateStyle(){
        if (this.stringUtilService.isEmpty(this.item.style)) {
        this.errorMessages.push('Style non valide');
        this.validAppStateStyle = false;
        } else {
            this.validAppStateStyle = true;
        }
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



    get items(): Array<AppStateDto> {
        return this.service.items;
    }

    set items(value: Array<AppStateDto>) {
        this.service.items = value;
    }

    get item(): AppStateDto {
        return this.service.item;
    }

    set item(value: AppStateDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): AppStateCriteria {
        return this.service.criteria;
    }

    set criteria(value: AppStateCriteria) {
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
