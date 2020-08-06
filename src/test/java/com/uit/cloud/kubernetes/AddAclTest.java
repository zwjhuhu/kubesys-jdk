/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachine.Lifecycle.AddACL;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class AddAclTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		AddACL addACL = new AddACL();
		addACL.setOperator("drop");
		addACL.setPriority("0");
		addACL.setRule("'ip'");
		addACL.setSwName("66e516c498104a239b44266c3671226c");
		addACL.setType("from");
		addACL.setVmmac("fa:b6:52:cb:b8:00");
		boolean successful = client.virtualMachines().addACL("3ca7b452387845d38e7ecd91cc079d5a", addACL);
	}
	
}
