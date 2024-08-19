import {ApplicationUserDto} from './ApplicationUser.model';
import {AppStateDto} from './AppState.model';
import {AppOwnerDto} from './AppOwner.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class AppDto extends BaseDto{

    public name: string;

    public description: string;

    public appState: AppStateDto ;
    public appOwner: AppOwnerDto ;
     public applicationUser: Array<ApplicationUserDto>;


    constructor() {
        super();

        this.name = '';
        this.description = '';
        this.applicationUser = new Array<ApplicationUserDto>();

        }

}
