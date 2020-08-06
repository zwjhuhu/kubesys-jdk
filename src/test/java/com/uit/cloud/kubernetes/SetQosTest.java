/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachine.Lifecycle.SetQoS;
import com.github.kubesys.kubernetes.api.model.virtualmachinedisk.Lifecycle.CreateDisk;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class SetQosTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachines().setQoS("254e360510084784af6f66cb611038c8", get());
		System.out.println(successful);
	}

	protected static SetQoS get() {
	    SetQoS p = new SetQoS();
		p.setBurst("1000");
		p.setPriority("0");
		p.setRate("1000");
		p.setRule("'tcp.src == 80'");
		p.setSwName("8c8870106d974458b282500916a59358");
		p.setType("from");
		return p;
	}
}
