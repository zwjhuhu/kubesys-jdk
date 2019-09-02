# 1. kubeext-jdk
Java Client for [kubevmm](https://github.com/syswu/kubevmm), which is a private project.

Note: kubeext-jdk is a heavy work in progress.

**Authors**
- wuheng@otcaix.iscas.ac.cn
- wuyuewen@otcaix.iscas.ac.cn
- liuhe18@otcaix.iscas.ac.cn

# 2. Introduce KubeVMM 

## 2.1 KubeVMM brief

**KubeVMM** is a Kubernetes-based virtual machine management platform, which extends [Kubernetes](https://kubernetes.io/) by adding
additional virtualization resource types through
[Kubernetes's Custom Resource Definitions API](https://kubernetes.io/docs/tasks/access-kubernetes-api/custom-resources/custom-resource-definitions/). By using this mechanism, the Kubernetes API can be used to manage these `VM`
resources alongside all other resources Kubernetes provides: 

- VirtualMachine: kubectl get vm
- VirtualMachinePool: kubectl get vmp
- VirtualMachineDisk: kubectl get vmd
- VirtualMachineImage: kubectl get vmi
- VirtualMachineSnapshot: kubectl get vmsn
- VirtualMachineNetwork: kubectl get vmn

**Note:** KubeVMM is a heavy work in progress.

# 2.2 kubeVMM Architecture

![avatar](docs/images/arch.png)

- **[Controller(aka kubeext-controller-manager)](https://github.com/kubesys/kubeext-controller-manager)**: extend Kubernetes to support VirtualMachine resource (Java).
- **[Scheduler(aka kubeext-scheduler)](https://github.com/kubesys/kubeext-scheduler)**:  extend Kubernetes to schedule VirtualMachine (Go).
- **[Executor(aka kubeext-excutor)](https://github.com/kubesys/kubeext-excutor)**:  manage VM's lifecycle (Python, Shell).

