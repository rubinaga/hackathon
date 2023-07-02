/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { IngredientDTO } from '../models/IngredientDTO';
import type { IngredientsDTO } from '../models/IngredientsDTO';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class IngredientControllerService {

    /**
     * @returns IngredientsDTO OK
     * @throws ApiError
     */
    public static getIngredients(): CancelablePromise<IngredientsDTO> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/public/ingredient',
            errors: {
                415: `Unsupported Media Type`,
            },
        });
    }

    /**
     * @param id
     * @returns IngredientDTO OK
     * @throws ApiError
     */
    public static getIngredient(
        id: number,
    ): CancelablePromise<IngredientDTO> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/public/ingredient/{id}',
            path: {
                'id': id,
            },
            errors: {
                415: `Unsupported Media Type`,
            },
        });
    }

}
