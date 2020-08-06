/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.SetBridgeVlan;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class SetBridgeVlanTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachineNetworks()
				.setBridgeVlan("br-native", get());
		System.out.println(successful);
	}

	protected static SetBridgeVlan get() {
		SetBridgeVlan setBridgeVlan = new SetBridgeVlan();
		setBridgeVlan.setVlan("1");
		setBridgeVlan.setName("wyw1");
		return setBridgeVlan;
	}
}
