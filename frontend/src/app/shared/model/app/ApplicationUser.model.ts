import {AppDto} from './App.model';
import {ApplicationUserStateDto} from './ApplicationUserState.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class ApplicationUserDto extends BaseDto{

    public firstName: string;

    public lastName: string;

    public email: string;

    public application: AppDto ;
    public applicationUserState: ApplicationUserStateDto ;


    constructor() {
        super();

        this.firstName = '';
        this.lastName = '';
        this.email = '';
        this.application = new AppDto() ;

        }

}
