import {ApplicationUserCriteria} from './ApplicationUserCriteria.model';
import {AppStateCriteria} from './AppStateCriteria.model';
import {AppOwnerCriteria} from './AppOwnerCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class AppCriteria extends BaseCriteria {

    public id: number;
    public name: string;
    public nameLike: string;
    public description: string;
    public descriptionLike: string;
      public applicationUser: Array<ApplicationUserCriteria>;

}
