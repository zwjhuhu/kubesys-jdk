/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.alibaba.fastjson.JSON;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class GetVirtualMachineTest {
	
	public static void main(String[] args) throws Exception {
		System.out.println(AbstractTest
				.getVMByName("vm003"));
//		System.out.println(
//				JSON.toJSONString(
//						AbstractTest.getVMByName("350646e8c17a49d0b83c1c797811a084").ge, true));
	}
	
}
