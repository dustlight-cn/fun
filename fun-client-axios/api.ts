/* tslint:disable */
/* eslint-disable */
/**
 * 函数服务
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v1
 * Contact: hansin@goodvoice.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import { Configuration } from './configuration';
import globalAxios, { AxiosPromise, AxiosInstance } from 'axios';
// Some imports not used depending on template conditions
// @ts-ignore
import { DUMMY_BASE_URL, assertParamExists, setApiKeyToObject, setBasicAuthToObject, setBearerAuthToObject, setOAuthToObject, setSearchParams, serializeDataIfNeeded, toPathString, createRequestFunction } from './common';
// @ts-ignore
import { BASE_PATH, COLLECTION_FORMATS, RequestArgs, BaseAPI, RequiredError } from './base';

/**
 * 
 * @export
 * @interface Config
 */
export interface Config {
    /**
     * 
     * @type {string}
     * @memberof Config
     */
    authEndpoint?: string;
    /**
     * 
     * @type {string}
     * @memberof Config
     */
    hostFormat?: string;
    /**
     * 
     * @type {string}
     * @memberof Config
     */
    tokenUri?: string;
    /**
     * 
     * @type {string}
     * @memberof Config
     */
    authorizationUri?: string;
}
/**
 * 
 * @export
 * @interface Function
 */
export interface Function {
    /**
     * 
     * @type {string}
     * @memberof Function
     */
    runtime?: string;
    /**
     * 
     * @type {string}
     * @memberof Function
     */
    name?: string;
    /**
     * 
     * @type {string}
     * @memberof Function
     */
    owner?: string;
    /**
     * 
     * @type {string}
     * @memberof Function
     */
    handler?: string;
    /**
     * 
     * @type {string}
     * @memberof Function
     */
    clientId?: string;
    /**
     * 
     * @type {string}
     * @memberof Function
     */
    updatedAt?: string;
    /**
     * 
     * @type {string}
     * @memberof Function
     */
    createdAt?: string;
}

/**
 * ConfigsApi - axios parameter creator
 * @export
 */
export const ConfigsApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 获取函数
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getConfiguration: async (options: any = {}): Promise<RequestArgs> => {
            const localVarPath = `/v1/config`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * ConfigsApi - functional programming interface
 * @export
 */
export const ConfigsApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = ConfigsApiAxiosParamCreator(configuration)
    return {
        /**
         * 
         * @summary 获取函数
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async getConfiguration(options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Config>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.getConfiguration(options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
    }
};

/**
 * ConfigsApi - factory interface
 * @export
 */
export const ConfigsApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = ConfigsApiFp(configuration)
    return {
        /**
         * 
         * @summary 获取函数
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getConfiguration(options?: any): AxiosPromise<Config> {
            return localVarFp.getConfiguration(options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * ConfigsApi - object-oriented interface
 * @export
 * @class ConfigsApi
 * @extends {BaseAPI}
 */
export class ConfigsApi extends BaseAPI {
    /**
     * 
     * @summary 获取函数
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof ConfigsApi
     */
    public getConfiguration(options?: any) {
        return ConfigsApiFp(this.configuration).getConfiguration(options).then((request) => request(this.axios, this.basePath));
    }
}


/**
 * FunctionsApi - axios parameter creator
 * @export
 */
export const FunctionsApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 创建函数
         * @param {string} name 
         * @param {Array<string>} requestBody 
         * @param {string} [runtime] 
         * @param {string} [handler] 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createFunction: async (name: string, requestBody: Array<string>, runtime?: string, handler?: string, cid?: string, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'name' is not null or undefined
            assertParamExists('createFunction', 'name', name)
            // verify required parameter 'requestBody' is not null or undefined
            assertParamExists('createFunction', 'requestBody', requestBody)
            const localVarPath = `/v1/function/{name}`
                .replace(`{${"name"}}`, encodeURIComponent(String(name)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            // authentication auth required
            // oauth required
            await setOAuthToObject(localVarHeaderParameter, "auth", [], configuration)

            if (runtime !== undefined) {
                localVarQueryParameter['runtime'] = runtime;
            }

            if (handler !== undefined) {
                localVarQueryParameter['handler'] = handler;
            }

            if (cid !== undefined) {
                localVarQueryParameter['cid'] = cid;
            }


    
            localVarHeaderParameter['Content-Type'] = 'application/octet-stream';

            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = serializeDataIfNeeded(requestBody, localVarRequestOptions, configuration)

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary 删除函数
         * @param {string} name 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        deleteFunction: async (name: string, cid?: string, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'name' is not null or undefined
            assertParamExists('deleteFunction', 'name', name)
            const localVarPath = `/v1/function/{name}`
                .replace(`{${"name"}}`, encodeURIComponent(String(name)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'DELETE', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            // authentication auth required
            // oauth required
            await setOAuthToObject(localVarHeaderParameter, "auth", [], configuration)

            if (cid !== undefined) {
                localVarQueryParameter['cid'] = cid;
            }


    
            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary 获取函数
         * @param {string} name 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getFunction: async (name: string, cid?: string, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'name' is not null or undefined
            assertParamExists('getFunction', 'name', name)
            const localVarPath = `/v1/function/{name}`
                .replace(`{${"name"}}`, encodeURIComponent(String(name)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            // authentication auth required
            // oauth required
            await setOAuthToObject(localVarHeaderParameter, "auth", [], configuration)

            if (cid !== undefined) {
                localVarQueryParameter['cid'] = cid;
            }


    
            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary 获取函数代码
         * @param {string} name 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getFunctionData: async (name: string, cid?: string, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'name' is not null or undefined
            assertParamExists('getFunctionData', 'name', name)
            const localVarPath = `/v1/function/{name}/data`
                .replace(`{${"name"}}`, encodeURIComponent(String(name)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            // authentication auth required
            // oauth required
            await setOAuthToObject(localVarHeaderParameter, "auth", [], configuration)

            if (cid !== undefined) {
                localVarQueryParameter['cid'] = cid;
            }


    
            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary 查询支持的运行环境
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getRuntimes: async (options: any = {}): Promise<RequestArgs> => {
            const localVarPath = `/v1/runtimes`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            // authentication auth required
            // oauth required
            await setOAuthToObject(localVarHeaderParameter, "auth", [], configuration)


    
            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary 列出函数
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        listFunctions: async (cid?: string, options: any = {}): Promise<RequestArgs> => {
            const localVarPath = `/v1/functions`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            // authentication auth required
            // oauth required
            await setOAuthToObject(localVarHeaderParameter, "auth", [], configuration)

            if (cid !== undefined) {
                localVarQueryParameter['cid'] = cid;
            }


    
            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * FunctionsApi - functional programming interface
 * @export
 */
export const FunctionsApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = FunctionsApiAxiosParamCreator(configuration)
    return {
        /**
         * 
         * @summary 创建函数
         * @param {string} name 
         * @param {Array<string>} requestBody 
         * @param {string} [runtime] 
         * @param {string} [handler] 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async createFunction(name: string, requestBody: Array<string>, runtime?: string, handler?: string, cid?: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Function>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.createFunction(name, requestBody, runtime, handler, cid, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary 删除函数
         * @param {string} name 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async deleteFunction(name: string, cid?: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.deleteFunction(name, cid, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary 获取函数
         * @param {string} name 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async getFunction(name: string, cid?: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Function>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.getFunction(name, cid, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary 获取函数代码
         * @param {string} name 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async getFunctionData(name: string, cid?: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<object>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.getFunctionData(name, cid, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary 查询支持的运行环境
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async getRuntimes(options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Array<string>>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.getRuntimes(options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary 列出函数
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async listFunctions(cid?: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Array<Function>>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.listFunctions(cid, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
    }
};

/**
 * FunctionsApi - factory interface
 * @export
 */
export const FunctionsApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = FunctionsApiFp(configuration)
    return {
        /**
         * 
         * @summary 创建函数
         * @param {string} name 
         * @param {Array<string>} requestBody 
         * @param {string} [runtime] 
         * @param {string} [handler] 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createFunction(name: string, requestBody: Array<string>, runtime?: string, handler?: string, cid?: string, options?: any): AxiosPromise<Function> {
            return localVarFp.createFunction(name, requestBody, runtime, handler, cid, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary 删除函数
         * @param {string} name 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        deleteFunction(name: string, cid?: string, options?: any): AxiosPromise<void> {
            return localVarFp.deleteFunction(name, cid, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary 获取函数
         * @param {string} name 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getFunction(name: string, cid?: string, options?: any): AxiosPromise<Function> {
            return localVarFp.getFunction(name, cid, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary 获取函数代码
         * @param {string} name 
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getFunctionData(name: string, cid?: string, options?: any): AxiosPromise<object> {
            return localVarFp.getFunctionData(name, cid, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary 查询支持的运行环境
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getRuntimes(options?: any): AxiosPromise<Array<string>> {
            return localVarFp.getRuntimes(options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary 列出函数
         * @param {string} [cid] 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        listFunctions(cid?: string, options?: any): AxiosPromise<Array<Function>> {
            return localVarFp.listFunctions(cid, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * FunctionsApi - object-oriented interface
 * @export
 * @class FunctionsApi
 * @extends {BaseAPI}
 */
export class FunctionsApi extends BaseAPI {
    /**
     * 
     * @summary 创建函数
     * @param {string} name 
     * @param {Array<string>} requestBody 
     * @param {string} [runtime] 
     * @param {string} [handler] 
     * @param {string} [cid] 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof FunctionsApi
     */
    public createFunction(name: string, requestBody: Array<string>, runtime?: string, handler?: string, cid?: string, options?: any) {
        return FunctionsApiFp(this.configuration).createFunction(name, requestBody, runtime, handler, cid, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary 删除函数
     * @param {string} name 
     * @param {string} [cid] 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof FunctionsApi
     */
    public deleteFunction(name: string, cid?: string, options?: any) {
        return FunctionsApiFp(this.configuration).deleteFunction(name, cid, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary 获取函数
     * @param {string} name 
     * @param {string} [cid] 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof FunctionsApi
     */
    public getFunction(name: string, cid?: string, options?: any) {
        return FunctionsApiFp(this.configuration).getFunction(name, cid, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary 获取函数代码
     * @param {string} name 
     * @param {string} [cid] 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof FunctionsApi
     */
    public getFunctionData(name: string, cid?: string, options?: any) {
        return FunctionsApiFp(this.configuration).getFunctionData(name, cid, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary 查询支持的运行环境
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof FunctionsApi
     */
    public getRuntimes(options?: any) {
        return FunctionsApiFp(this.configuration).getRuntimes(options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary 列出函数
     * @param {string} [cid] 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof FunctionsApi
     */
    public listFunctions(cid?: string, options?: any) {
        return FunctionsApiFp(this.configuration).listFunctions(cid, options).then((request) => request(this.axios, this.basePath));
    }
}


