import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {EditorModule} from "primeng/editor";

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";
import {TagModule} from "primeng/tag";

import { AppCreateAdminComponent } from './app/create/app-create-admin.component';
import { AppEditAdminComponent } from './app/edit/app-edit-admin.component';
import { AppViewAdminComponent } from './app/view/app-view-admin.component';
import { AppListAdminComponent } from './app/list/app-list-admin.component';
import { ApplicationUserCreateAdminComponent } from './application-user/create/application-user-create-admin.component';
import { ApplicationUserEditAdminComponent } from './application-user/edit/application-user-edit-admin.component';
import { ApplicationUserViewAdminComponent } from './application-user/view/application-user-view-admin.component';
import { ApplicationUserListAdminComponent } from './application-user/list/application-user-list-admin.component';
import { AppStateCreateAdminComponent } from './app-state/create/app-state-create-admin.component';
import { AppStateEditAdminComponent } from './app-state/edit/app-state-edit-admin.component';
import { AppStateViewAdminComponent } from './app-state/view/app-state-view-admin.component';
import { AppStateListAdminComponent } from './app-state/list/app-state-list-admin.component';
import { AppOwnerCreateAdminComponent } from './app-owner/create/app-owner-create-admin.component';
import { AppOwnerEditAdminComponent } from './app-owner/edit/app-owner-edit-admin.component';
import { AppOwnerViewAdminComponent } from './app-owner/view/app-owner-view-admin.component';
import { AppOwnerListAdminComponent } from './app-owner/list/app-owner-list-admin.component';
import { ApplicationUserStateCreateAdminComponent } from './application-user-state/create/application-user-state-create-admin.component';
import { ApplicationUserStateEditAdminComponent } from './application-user-state/edit/application-user-state-edit-admin.component';
import { ApplicationUserStateViewAdminComponent } from './application-user-state/view/application-user-state-view-admin.component';
import { ApplicationUserStateListAdminComponent } from './application-user-state/list/application-user-state-list-admin.component';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';



@NgModule({
  declarations: [

    AppCreateAdminComponent,
    AppListAdminComponent,
    AppViewAdminComponent,
    AppEditAdminComponent,
    ApplicationUserCreateAdminComponent,
    ApplicationUserListAdminComponent,
    ApplicationUserViewAdminComponent,
    ApplicationUserEditAdminComponent,
    AppStateCreateAdminComponent,
    AppStateListAdminComponent,
    AppStateViewAdminComponent,
    AppStateEditAdminComponent,
    AppOwnerCreateAdminComponent,
    AppOwnerListAdminComponent,
    AppOwnerViewAdminComponent,
    AppOwnerEditAdminComponent,
    ApplicationUserStateCreateAdminComponent,
    ApplicationUserStateListAdminComponent,
    ApplicationUserStateViewAdminComponent,
    ApplicationUserStateEditAdminComponent,
  ],
  imports: [
    CommonModule,
    ToastModule,
    ToolbarModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SplitButtonModule,
    BrowserAnimationsModule,
    DropdownModule,
    TabViewModule,
    InputSwitchModule,
    InputTextareaModule,
    CalendarModule,
    PanelModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    BadgeModule,
    MultiSelectModule,
    PaginatorModule,
    TranslateModule,
    FileUploadModule,
    FullCalendarModule,
    CardModule,
    EditorModule,
    TagModule,


  ],
  exports: [
  AppCreateAdminComponent,
  AppListAdminComponent,
  AppViewAdminComponent,
  AppEditAdminComponent,
  ApplicationUserCreateAdminComponent,
  ApplicationUserListAdminComponent,
  ApplicationUserViewAdminComponent,
  ApplicationUserEditAdminComponent,
  AppStateCreateAdminComponent,
  AppStateListAdminComponent,
  AppStateViewAdminComponent,
  AppStateEditAdminComponent,
  AppOwnerCreateAdminComponent,
  AppOwnerListAdminComponent,
  AppOwnerViewAdminComponent,
  AppOwnerEditAdminComponent,
  ApplicationUserStateCreateAdminComponent,
  ApplicationUserStateListAdminComponent,
  ApplicationUserStateViewAdminComponent,
  ApplicationUserStateEditAdminComponent,
  ],
})
export class AppAdminModule { }
