import {AppCriteria} from './AppCriteria.model';
import {ApplicationUserStateCriteria} from './ApplicationUserStateCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class ApplicationUserCriteria extends BaseCriteria {

    public id: number;
    public firstName: string;
    public firstNameLike: string;
    public lastName: string;
    public lastNameLike: string;
    public email: string;
    public emailLike: string;
  public application: AppCriteria ;
  public applications: Array<AppCriteria> ;

}
