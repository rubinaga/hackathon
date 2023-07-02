/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { PageResponseGetModerateMealDTO } from '../models/PageResponseGetModerateMealDTO';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class MealControllerService {

    /**
     * @param username
     * @param mealId
     * @param requestBody
     * @returns any OK
     * @throws ApiError
     */
    public static uploadImage(
        username: string,
        mealId: number,
        requestBody?: {
            file: Blob;
        },
    ): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/user/{username}/meals/uploadImage/{mealId}',
            path: {
                'username': username,
                'mealId': mealId,
            },
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                415: `Unsupported Media Type`,
            },
        });
    }

    /**
     * @param pageSize
     * @param pageNumber
     * @returns PageResponseGetModerateMealDTO OK
     * @throws ApiError
     */
    public static getAllMeals(
        pageSize: number = 30,
        pageNumber?: number,
    ): CancelablePromise<PageResponseGetModerateMealDTO> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/public/meal',
            query: {
                'pageSize': pageSize,
                'pageNumber': pageNumber,
            },
            errors: {
                415: `Unsupported Media Type`,
            },
        });
    }

}
