/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.CreateSwitch;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class CreateL3SwitchTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachineNetworks()
				.createSwitch("l2l3", "vm.k8s-22", get());
		System.out.println(successful);
	}

	protected static CreateSwitch get() {
		CreateSwitch vxlan = new CreateSwitch();
		vxlan.setSubnet("133.133.135.0/24");
		vxlan.setExcludeIPs("133.133.135.1..133.133.135.200");
		vxlan.setGateway("133.133.135.40");
		vxlan.setMtu("1480");
		return vxlan;
	}
}
