/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

export type CreateAppUserDTO = {
    name: string;
    surname: string;
    email: string;
    password: string;
    phoneNumber?: string;
    registerAsFoodProvider?: boolean;
    locale?: {
        language?: string;
        script?: string;
        variant?: string;
        displayName?: string;
        country?: string;
        unicodeLocaleAttributes?: Array<string>;
        unicodeLocaleKeys?: Array<string>;
        displayLanguage?: string;
        displayScript?: string;
        displayCountry?: string;
        displayVariant?: string;
        extensionKeys?: Array<string>;
        iso3Language?: string;
        iso3Country?: string;
    };
};

