/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachine.Lifecycle.CreateAndStartVMFromISO;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class CreateAndStartSingleDiskFromISOTest {
	
	
	public static void main(String[] args) throws Exception {
		ExtendedKubernetesClient client = AbstractTest.getClient();
		CreateAndStartVMFromISO createAndStartVMFromISO = get();
		// name
		boolean successful = client.virtualMachines()
				.createAndStartVMFromISO("wyw1234", "vm.k8s-22", createAndStartVMFromISO, "01");
		System.out.println(successful);
	}
	
	
	public static CreateAndStartVMFromISO get() throws Exception {
		
		CreateAndStartVMFromISO createAndStartVMFromISO = new CreateAndStartVMFromISO();
		// default value
//		createAndStartVMFromISO.setMetadata("uuid=950646e8-c17a-49d0-b83c-1c797811vm001");
		createAndStartVMFromISO.setVirt_type("kvm"); 
		// @see https://github.com/uit-plus/api/blob/master/src/main/java/com/github/uitplus/utils/OSDistroUtils.java
		createAndStartVMFromISO.setOs_variant("centos7.0");
		createAndStartVMFromISO.setNoautoconsole(true); 
		
		// calculationSpecification
		calculationSpecification(createAndStartVMFromISO);  
		
		// Disk and QoS for 1 disk and many disks
		// path /var/lib/libvirt/images/test11 can be get by CreateDiskTest
		// cdrom
//		createAndStartVMFromISO.setCdrom("/var/lib/libvirt/cstor/076fe6aa813842d3ba141f172e3f8eb6/076fe6aa813842d3ba141f172e3f8eb6/f045e85ed4f84034907f60172891c72b.iso"); 
		// Disk and QoS for 1 disk and many disks
		createAndStartVMFromISO.setDisk(""
		    + "/mnt/localfs/skywin/b3fb2421512e484198ee232f7fc7f4d7/testinit/testinit "); 
		    //"--disk /mnt/localfs/skywin/b3fb2421512e484198ee232f7fc7f4d7/testconfigd1/testconfigd1,device=cdrom,format=raw,perms=ro,bus=scsi");
				

		/*
		 * libivrt default bridge
		 * Parameters:
		 * 	type
		 * 		type of network support values: "bridge", "l2bridge" and "l3bridge"
		 * 	source
		 * 		network source name
		 * 	inbound (optional)
		 * 		inbound bandwidth in KB
		 * 	outbound (optional)
		 * 		outbound bandwidth in KB
		 * 	mac (optional)
		 * 		if no mac, create a random mac
		 * 		Note! Mac address is unique and does not support a value that start with "fe:" (e.g. fe:54:00:05:37:b3)
		 */
//		createAndStartVMFromISO.setNetwork("type=l3bridge,source=br-int,ip=192.168.10.14,switch=vxlan");
		createAndStartVMFromISO.setNetwork("type=bridge,source=virbr0");
//      if you want to use l3bridge, please first execute the command on your master node, 'kubeovn-adm create-switch --name switch8888 --subnet 192.168.5.0/24' 		
//		createAndStartVMFromISO.setNetwork("type=l3bridge,source=br-int,switch=l2l3,inbound=102400,outbound=102400");  
		
		/*
		 * l2 network example
		 * Parameters:
		 * 	type
		 * 		type of network support values: "bridge", "l2bridge" and "l3bridge"
		 * 	source
		 * 		network source name
		 * 	inbound (optional)
		 * 		inbound bandwidth in KB
		 * 	outbound (optional)
		 * 		outbound bandwidth in KB
		 * 	mac (optional)
		 * 		if no mac, create a random mac
		 * 		Note! Mac address is unique and does not support a value that start with "fe:" (e.g. fe:54:00:05:37:b3)
		 */
//		createAndStartVMFromISO.setNetwork("type=l2bridge,source=br-native,inbound=102400,outbound=102400");
		
		/*
		 * l3 network example
		 * Parameters:
		 * 	type
		 * 		type of network support values: "bridge", "l2bridge" and "l3bridge"
		 * 	source
		 * 		network source name
		 * 	inbound (optional)
		 * 		inbound bandwidth limitation in KB, default is no limitation
		 * 	outbound (optional)
		 * 		outbound bandwidth limitation in KB, default is no limitation
		 * 	mac (optional)
		 * 		if no mac, create a random mac
		 * 		Note! Mac address is unique and does not support a value that start with "fe:" (e.g. fe:54:00:05:37:b3)
		 * 	ip (optional)
		 * 		ip address for l3 network, default is "dynamic" from DHCP
		 * 	switch
		 * 		switch name
		 */
		
//		createAndStartVMFromISO.setNetwork("type=l3bridge,source=br-int,inbound=102400,outbound=102400,ip=192.168.5.9,switch=switch");  
		
		// consoleMode amd passowrd
		createAndStartVMFromISO.setGraphics("vnc,listen=0.0.0.0");
//		createAndStartVMFromISO.setGraphics("spice,listen=0.0.0.0" + getconsolePassword("567890")); 
		createAndStartVMFromISO.setBoot("hd");
		return createAndStartVMFromISO;
	}


	protected static void calculationSpecification(CreateAndStartVMFromISO createAndStartVMFromISO) {
		createAndStartVMFromISO.setMemory("2048");    
//		createAndStartVMFromISO.setVcpus("4");
//		createAndStartVMFromISO.setCputune("vcpupin0.vcpu=0,vcpupin0.cpuset=0-3,vcpupin1.vcpu=1,vcpupin1.cpuset=4");
	}
	
	protected static String getCPUSet(String cpuset) {
		return (cpuset == null || cpuset.length() == 0) 
				? "" :",cpuset=" + cpuset;
	}
	
	protected static String getconsolePassword(String pwd) {
		return (pwd == null || pwd.length() == 0) ? "" : ",password=111111";
	}
	
	protected static String nameToUUID(String name) {
		StringBuffer sb = new StringBuffer();
		sb.append(name.substring(0, 8)).append("-")
				.append(name.substring(8, 12)).append("-")
				.append(name.substring(12, 16)).append("-")
				.append(name.substring(16, 20)).append("-")
				.append(name.substring(20, 32));
		return sb.toString();
	}
	
}
