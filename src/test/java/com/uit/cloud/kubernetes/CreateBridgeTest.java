/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.CreateBridge;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class CreateBridgeTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachineNetworks()
				.createBridge("br-enp97s0f1", "vm.node25", get(), "123");
		System.out.println(successful);
	}

	protected static CreateBridge get() {
		CreateBridge createBridge = new CreateBridge();
		createBridge.setNic("enp97s0f1");
		createBridge.setName("br-enp97s0f1");
//		createBridge.setVlan("10");
		return createBridge;
	}
}
