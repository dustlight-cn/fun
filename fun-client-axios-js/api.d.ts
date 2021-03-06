/**
 * 函数服务
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v1
 * Contact: hansin@dustlight.cn
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { Configuration } from './configuration';
import { AxiosPromise, AxiosInstance } from 'axios';
import { RequestArgs, BaseAPI } from './base';
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
    createdAt?: string;
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
    clientId?: string;
}
/**
 * ConfigsApi - axios parameter creator
 * @export
 */
export declare const ConfigsApiAxiosParamCreator: (configuration?: Configuration) => {
    /**
     *
     * @summary 获取配置
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getConfiguration: (options?: any) => Promise<RequestArgs>;
};
/**
 * ConfigsApi - functional programming interface
 * @export
 */
export declare const ConfigsApiFp: (configuration?: Configuration) => {
    /**
     *
     * @summary 获取配置
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getConfiguration(options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Config>>;
};
/**
 * ConfigsApi - factory interface
 * @export
 */
export declare const ConfigsApiFactory: (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) => {
    /**
     *
     * @summary 获取配置
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getConfiguration(options?: any): AxiosPromise<Config>;
};
/**
 * ConfigsApi - object-oriented interface
 * @export
 * @class ConfigsApi
 * @extends {BaseAPI}
 */
export declare class ConfigsApi extends BaseAPI {
    /**
     *
     * @summary 获取配置
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof ConfigsApi
     */
    getConfiguration(options?: any): Promise<import("axios").AxiosResponse<Config>>;
}
/**
 * FunctionsApi - axios parameter creator
 * @export
 */
export declare const FunctionsApiAxiosParamCreator: (configuration?: Configuration) => {
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
    createFunction: (name: string, requestBody: Array<string>, runtime?: string, handler?: string, cid?: string, options?: any) => Promise<RequestArgs>;
    /**
     *
     * @summary 删除函数
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    deleteFunction: (name: string, cid?: string, options?: any) => Promise<RequestArgs>;
    /**
     *
     * @summary 获取函数
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getFunction: (name: string, cid?: string, options?: any) => Promise<RequestArgs>;
    /**
     *
     * @summary 获取函数代码
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getFunctionData: (name: string, cid?: string, options?: any) => Promise<RequestArgs>;
    /**
     *
     * @summary 查询支持的运行环境
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getRuntimes: (options?: any) => Promise<RequestArgs>;
    /**
     *
     * @summary 列出函数
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    listFunctions: (cid?: string, options?: any) => Promise<RequestArgs>;
};
/**
 * FunctionsApi - functional programming interface
 * @export
 */
export declare const FunctionsApiFp: (configuration?: Configuration) => {
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
    createFunction(name: string, requestBody: Array<string>, runtime?: string, handler?: string, cid?: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Function>>;
    /**
     *
     * @summary 删除函数
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    deleteFunction(name: string, cid?: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>>;
    /**
     *
     * @summary 获取函数
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getFunction(name: string, cid?: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Function>>;
    /**
     *
     * @summary 获取函数代码
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getFunctionData(name: string, cid?: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<object>>;
    /**
     *
     * @summary 查询支持的运行环境
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getRuntimes(options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Array<string>>>;
    /**
     *
     * @summary 列出函数
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    listFunctions(cid?: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Array<Function>>>;
};
/**
 * FunctionsApi - factory interface
 * @export
 */
export declare const FunctionsApiFactory: (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) => {
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
    createFunction(name: string, requestBody: Array<string>, runtime?: string, handler?: string, cid?: string, options?: any): AxiosPromise<Function>;
    /**
     *
     * @summary 删除函数
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    deleteFunction(name: string, cid?: string, options?: any): AxiosPromise<void>;
    /**
     *
     * @summary 获取函数
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getFunction(name: string, cid?: string, options?: any): AxiosPromise<Function>;
    /**
     *
     * @summary 获取函数代码
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getFunctionData(name: string, cid?: string, options?: any): AxiosPromise<object>;
    /**
     *
     * @summary 查询支持的运行环境
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    getRuntimes(options?: any): AxiosPromise<Array<string>>;
    /**
     *
     * @summary 列出函数
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     */
    listFunctions(cid?: string, options?: any): AxiosPromise<Array<Function>>;
};
/**
 * FunctionsApi - object-oriented interface
 * @export
 * @class FunctionsApi
 * @extends {BaseAPI}
 */
export declare class FunctionsApi extends BaseAPI {
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
    createFunction(name: string, requestBody: Array<string>, runtime?: string, handler?: string, cid?: string, options?: any): Promise<import("axios").AxiosResponse<Function>>;
    /**
     *
     * @summary 删除函数
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof FunctionsApi
     */
    deleteFunction(name: string, cid?: string, options?: any): Promise<import("axios").AxiosResponse<void>>;
    /**
     *
     * @summary 获取函数
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof FunctionsApi
     */
    getFunction(name: string, cid?: string, options?: any): Promise<import("axios").AxiosResponse<Function>>;
    /**
     *
     * @summary 获取函数代码
     * @param {string} name
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof FunctionsApi
     */
    getFunctionData(name: string, cid?: string, options?: any): Promise<import("axios").AxiosResponse<object>>;
    /**
     *
     * @summary 查询支持的运行环境
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof FunctionsApi
     */
    getRuntimes(options?: any): Promise<import("axios").AxiosResponse<string[]>>;
    /**
     *
     * @summary 列出函数
     * @param {string} [cid]
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof FunctionsApi
     */
    listFunctions(cid?: string, options?: any): Promise<import("axios").AxiosResponse<Function[]>>;
}
