# Fun
[简介](#简介) | [功能](#功能) | [构建](#构建) | [部署](#部署)

## 简介
Serverless Interface

#### 功能
* 提供无服务接口

#### 技术栈
* Spring Boot
* OAuth2.0
* Serverless

## 构建
```shell
mvn package
```

## 部署
待完善

## Kubeless 安装
```bash
export RELEASE=$(curl -s -L https://api.github.com/repos/vmware-archive/kubeless/releases/latest | grep tag_name | cut -d '"' -f 4)
kubectl create ns kubeless
kubectl create -f https://github.com/kubeless/kubeless/releases/download/$RELEASE/kubeless-$RELEASE.yaml
```
