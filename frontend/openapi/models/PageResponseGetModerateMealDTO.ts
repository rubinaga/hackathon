/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { GetModerateMealDTO } from './GetModerateMealDTO';

export type PageResponseGetModerateMealDTO = {
    currentPage?: number;
    totalItems?: number;
    totalPages?: number;
    items?: Array<GetModerateMealDTO>;
};

