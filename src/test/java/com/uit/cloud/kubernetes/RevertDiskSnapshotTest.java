/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachinedisk.Lifecycle.RevertDiskSnapshot;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class RevertDiskSnapshotTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachineDisks()
				.revertDiskSnapshot("test1.qcow2", get(), "abc");
		System.out.println(successful);
	}

	protected static RevertDiskSnapshot get() {
		RevertDiskSnapshot revertDiskSnapshot = new RevertDiskSnapshot();
		revertDiskSnapshot.setPool("default");
		revertDiskSnapshot.setSnapshotname("snap6");
		return revertDiskSnapshot;
	}
}
