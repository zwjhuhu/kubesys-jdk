/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachinesnapshot.Lifecycle.RevertVirtualMachine;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class RevertSnapshotTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachineSnapshots()
				.revertVirtualMachine("snapshota", get(), "123456");
		System.out.println(successful);
	}

	protected static RevertVirtualMachine get() {
		RevertVirtualMachine revertVM = new RevertVirtualMachine();
		// domain name
		revertVM.setDomain("950646e8c17a49d0b83c1c797811e001");
		// after reverting, change state to running
		revertVM.setRunning(true);
		// external snapshot should add this parameter
		revertVM.setIsExternal(true);
		return revertVM;
	}
	
}
