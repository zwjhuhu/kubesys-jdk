/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.kubernetes.impl;

import java.util.regex.Pattern;

import com.github.kubesys.kubernetes.api.model.VirtualMachineDiskImage;
import com.github.kubesys.kubernetes.api.model.VirtualMachineDiskImageList;
import com.github.kubesys.kubernetes.api.model.VirtualMachineDiskImageSpec;
import com.github.kubesys.kubernetes.api.model.virtualmachinediskimage.Lifecycle;
import com.github.kubesys.kubernetes.api.model.virtualmachinediskimage.Lifecycle.ConvertDiskImageToDisk;
import com.github.kubesys.kubernetes.api.model.virtualmachinediskimage.Lifecycle.CreateDiskImage;
import com.github.kubesys.kubernetes.api.model.virtualmachinediskimage.Lifecycle.DeleteDiskImage;
import com.github.kubesys.kubernetes.utils.RegExpUtils;

/**
 * @author  wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.0.0
 * @since   2019/9/1
 **/
public class VirtualMachineDiskImageImpl extends AbstractImpl<VirtualMachineDiskImage, VirtualMachineDiskImageList, VirtualMachineDiskImageSpec> {


	@Override
	public Object getLifecycle() {
		return new Lifecycle();
	}

	@Override
	public VirtualMachineDiskImage getModel() {
		return new VirtualMachineDiskImage();
	}

	@Override
	public VirtualMachineDiskImageSpec getSpec() {
		return new VirtualMachineDiskImageSpec();
	}

	@Override
	public VirtualMachineDiskImageSpec getSpec(VirtualMachineDiskImage r) {
		return r.getSpec();
	}


	/*************************************************
	 * 
	 * Generated by <code>MethodGenerator<code>
	 * 
	 **************************************************/

	public boolean convertDiskImageToDisk(String name, ConvertDiskImageToDisk convertDiskImageToDisk) throws Exception {
		return convertDiskImageToDisk(name, convertDiskImageToDisk, null);
	}

	public boolean convertDiskImageToDisk(String name,ConvertDiskImageToDisk convertDiskImageToDisk, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("名称是字符串类型，长度是6到128位，只允许数字、小写字母、中划线、以及圆点");
		}
		return update(name, updateMetadata(name, eventId), convertDiskImageToDisk);
	}

	public boolean deleteDiskImage(String name, DeleteDiskImage deleteDiskImage) throws Exception {
		return deleteDiskImage(name, deleteDiskImage, null);
	}

	public boolean deleteDiskImage(String name,DeleteDiskImage deleteDiskImage, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("名称是字符串类型，长度是6到128位，只允许数字、小写字母、中划线、以及圆点");
		}
		return delete(name, updateMetadata(name, eventId), deleteDiskImage);
	}

	public boolean createDiskImage(String name, CreateDiskImage createDiskImage) throws Exception {
		return createDiskImage(name, null, createDiskImage, null);
	}

	public boolean createDiskImage(String name, String nodeName, CreateDiskImage createDiskImage) throws Exception {
		return createDiskImage(name, nodeName, createDiskImage, null);
	}

	public boolean createDiskImage(String name, CreateDiskImage createDiskImage, String eventId) throws Exception {
		return createDiskImage(name, null, createDiskImage, eventId);
	}

	public boolean createDiskImage(String name, String nodeName,CreateDiskImage createDiskImage, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("名称是字符串类型，长度是6到128位，只允许数字、小写字母、中划线、以及圆点");
		}
		return create(getModel(), createMetadata(name, nodeName, eventId), 
				createSpec(nodeName, createLifecycle(createDiskImage)));
	}
	
}
