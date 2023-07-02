/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { CreateAppUserDTO } from '../models/CreateAppUserDTO';
import type { DetailedTokenDetailsDTO } from '../models/DetailedTokenDetailsDTO';
import type { GetAppUserDTO } from '../models/GetAppUserDTO';
import type { UpdateAppUserDTO } from '../models/UpdateAppUserDTO';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class AppUserControllerService {

    /**
     * @param username
     * @param requestBody
     * @returns any OK
     * @throws ApiError
     */
    public static uploadImage1(
        username: string,
        requestBody?: {
            file: Blob;
        },
    ): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/user/users/{username}/uploadImage',
            path: {
                'username': username,
            },
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                415: `Unsupported Media Type`,
            },
        });
    }

    /**
     * @param requestBody
     * @returns DetailedTokenDetailsDTO OK
     * @throws ApiError
     */
    public static registerUser(
        requestBody: CreateAppUserDTO,
    ): CancelablePromise<DetailedTokenDetailsDTO> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/public/users/register',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                415: `Unsupported Media Type`,
            },
        });
    }

    /**
     * @param username
     * @returns GetAppUserDTO OK
     * @throws ApiError
     */
    public static getUserByUsername(
        username: string,
    ): CancelablePromise<GetAppUserDTO> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/user/users/{username}',
            path: {
                'username': username,
            },
            errors: {
                415: `Unsupported Media Type`,
            },
        });
    }

    /**
     * @param username
     * @returns any OK
     * @throws ApiError
     */
    public static deleteUserByUsername(
        username: string,
    ): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/api/user/users/{username}',
            path: {
                'username': username,
            },
            errors: {
                415: `Unsupported Media Type`,
            },
        });
    }

    /**
     * @param username
     * @param requestBody
     * @returns GetAppUserDTO OK
     * @throws ApiError
     */
    public static updateUserByUsername(
        username: string,
        requestBody: UpdateAppUserDTO,
    ): CancelablePromise<GetAppUserDTO> {
        return __request(OpenAPI, {
            method: 'PATCH',
            url: '/api/user/users/{username}',
            path: {
                'username': username,
            },
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                415: `Unsupported Media Type`,
            },
        });
    }

    /**
     * @returns GetAppUserDTO OK
     * @throws ApiError
     */
    public static getUsers(): CancelablePromise<Array<GetAppUserDTO>> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/admin/users',
            errors: {
                415: `Unsupported Media Type`,
            },
        });
    }

    /**
     * @param ids
     * @returns any OK
     * @throws ApiError
     */
    public static deleteUsers(
        ids: Array<number>,
    ): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/api/admin/users/{ids}',
            path: {
                'ids': ids,
            },
            errors: {
                415: `Unsupported Media Type`,
            },
        });
    }

}
