/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachine.Lifecycle.PlugNIC;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class PlugNICTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachines()
				.plugNIC("fc2031e94bb0463ca80e26a10bf143ed", get());
		System.out.println(successful);
	}
	
	public static PlugNIC get() {
		PlugNIC plugNIC = new PlugNIC();
		
		/*
		 * libvirt default bridge
		 * Parameters:
		 * 	source
		 * 		network source name
		 */
//		plugNIC.setType("bridge"); 
//		plugNIC.setSource("source=virbr0");
		
		/*
		 * l2 network example
		 * Parameters:
		 * 	source
		 * 		network source name
		 */
		plugNIC.setType("l2bridge"); 
		plugNIC.setSource("source=vx10841dd");
		
		
		/*
		 * l3 network example
		 * Parameters:
		 * 	source
		 * 		network source name
		 * 	ip (optional)
		 * 		ip address for l3 network, default is "dynamic" from DHCP
		 * 	switch
		 * 		switch name
		 */
//		plugNIC.setType("l3bridge"); 
//		plugNIC.setSource("source=br-int,switch=ll3test");
		
		/*
		 * 	mac address
		 * 		if no mac, create a random mac
		 * 		Note! Mac address is unique and does not support a value that start with "fe:" (e.g. fe:54:00:05:37:b3)
		 */
		plugNIC.setMac("52:54:00:20:d0:81");
		// inbound bandwidth limitation in KB, default is no limitation
//		plugNIC.setInbound("102400");
		// outbound bandwidth limitation in KB, default is no limitation
//		plugNIC.setOutbound("102400");
//		plugNIC.setLive(true);
		plugNIC.setPersistent(true);
		return plugNIC;
	}
}
