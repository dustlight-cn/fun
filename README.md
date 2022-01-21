# Fun
[简介](#简介) | [构建](#构建) | [部署](#部署)

## 简介
无服务器函数服务。

Serverless Function Service.

## 构建
```shell
mvn package
```

## 部署
### Helm 部署
选择此部署方式必须先安装 [Helm](https://helm.sh)。  
请查看 Helm 的 [文档](https://helm.sh/docs) 获取更多信息。

当 Helm 安装完毕后，使用下面命令添加仓库：

    helm repo add fun https://dustlight-cn.github.io/fun

若您已经添加仓库，执行命令 `helm repo update` 获取最新的包。
您可以通过命令 `helm search repo fun` 来查看他们的 charts。

安装 Fun：

    helm install my-fun fun/fun

卸载 Fun：

    helm delete my-fun

### Kubeless 安装
目前 Fun 依赖 Kubeless，请执行下面命令安装。
```bash
export RELEASE=$(curl -s -L https://api.github.com/repos/vmware-archive/kubeless/releases/latest | grep tag_name | cut -d '"' -f 4)
kubectl create ns kubeless
kubectl create -f https://github.com/kubeless/kubeless/releases/download/$RELEASE/kubeless-$RELEASE.yaml
```
