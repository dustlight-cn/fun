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
import { AxiosPromise, AxiosInstance } from 'axios';
import { RequestArgs, BaseAPI } from './base';
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
