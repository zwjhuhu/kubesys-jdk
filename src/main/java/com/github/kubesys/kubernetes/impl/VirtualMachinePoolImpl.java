/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.kubernetes.impl;

import java.util.regex.Pattern;

import com.github.kubesys.kubernetes.api.model.VirtualMachinePool;
import com.github.kubesys.kubernetes.api.model.VirtualMachinePoolList;
import com.github.kubesys.kubernetes.api.model.VirtualMachinePoolSpec;
import com.github.kubesys.kubernetes.api.model.virtualmachinepool.Lifecycle;
import com.github.kubesys.kubernetes.api.model.virtualmachinepool.Lifecycle.AutoStartPool;
import com.github.kubesys.kubernetes.api.model.virtualmachinepool.Lifecycle.CreatePool;
import com.github.kubesys.kubernetes.api.model.virtualmachinepool.Lifecycle.DeletePool;
//import com.github.kubesys.kubernetes.api.model.virtualmachinepool.Lifecycle.RegisterPool;
import com.github.kubesys.kubernetes.api.model.virtualmachinepool.Lifecycle.StartPool;
import com.github.kubesys.kubernetes.api.model.virtualmachinepool.Lifecycle.StopPool;
//import com.github.kubesys.kubernetes.api.model.virtualmachinepool.Lifecycle.UnregisterPool;
import com.github.kubesys.kubernetes.utils.RegExpUtils;

/**
 * @author  wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.0.0
 * @since   2019/9/1
 **/
public class VirtualMachinePoolImpl extends AbstractImpl<VirtualMachinePool, VirtualMachinePoolList, VirtualMachinePoolSpec> {

	@Override
	public VirtualMachinePool getModel() {
		return new VirtualMachinePool();
	}

	@Override
	public VirtualMachinePoolSpec getSpec() {
		return new VirtualMachinePoolSpec();
	}
	
	@Override
	public Object getLifecycle() {
		return new Lifecycle();
	}

	
	@Override
	public VirtualMachinePoolSpec getSpec(VirtualMachinePool r) {
		return r.getSpec();
	}

	/*************************************************
	 * 
	 * Generated by <code>MethodGenerator<code>
	 * 
	 **************************************************/
	public boolean autoStartPool(String name, AutoStartPool autoStartPool) throws Exception {
		return autoStartPool(name, autoStartPool, null);
	}

	public boolean autoStartPool(String name, AutoStartPool autoStartPool, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return update(name, updateMetadata(name, eventId), autoStartPool);
	}

	public boolean autoStartPool(String name, String nodeName, AutoStartPool autoStartPool) throws Exception {
		updateHost(name, nodeName);
		return autoStartPool(name, autoStartPool, null);
	}

	public boolean autoStartPool(String name, String nodeName, AutoStartPool autoStartPool, String eventId) throws Exception {
		updateHost(name, nodeName);
		return autoStartPool(name, autoStartPool, eventId);
	}

	public boolean createPool(String name, CreatePool createPool) throws Exception {
		return createPool(name, null, createPool, null);
	}

	public boolean createPool(String name, String nodeName, CreatePool createPool) throws Exception {
		return createPool(name, nodeName, createPool, null);
	}

	public boolean createPool(String name, CreatePool createPool, String eventId) throws Exception {
		return createPool(name, null, createPool, eventId);
	}

	public boolean createPool(String name, String nodeName,CreatePool createPool, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return create(getModel(), createMetadata(name, nodeName, eventId), 
				createSpec(nodeName, createLifecycle(createPool)));
	}

	public boolean startPool(String name, StartPool startPool) throws Exception {
		return startPool(name, startPool, null);
	}

	public boolean startPool(String name, StartPool startPool, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return update(name, updateMetadata(name, eventId), startPool);
	}

	public boolean startPool(String name, String nodeName, StartPool startPool) throws Exception {
		updateHost(name, nodeName);
		return startPool(name, startPool, null);
	}

	public boolean startPool(String name, String nodeName, StartPool startPool, String eventId) throws Exception {
		updateHost(name, nodeName);
		return startPool(name, startPool, eventId);
	}

	public boolean stopPool(String name, StopPool stopPool) throws Exception {
		return stopPool(name, stopPool, null);
	}

	public boolean stopPool(String name, StopPool stopPool, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return update(name, updateMetadata(name, eventId), stopPool);
	}

	public boolean stopPool(String name, String nodeName, StopPool stopPool) throws Exception {
		updateHost(name, nodeName);
		return stopPool(name, stopPool, null);
	}

	public boolean stopPool(String name, String nodeName, StopPool stopPool, String eventId) throws Exception {
		updateHost(name, nodeName);
		return stopPool(name, stopPool, eventId);
	}

	public boolean deletePool(String name, DeletePool deletePool) throws Exception {
		return deletePool(name, deletePool, null);
	}

	public boolean deletePool(String name, DeletePool deletePool, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 4 and 100, and it can only includes a-z, 0-9 and -.");
		}
		return delete(name, updateMetadata(name, eventId), deletePool);
	}

	public boolean deletePool(String name, String nodeName, DeletePool deletePool) throws Exception {
		updateHost(name, nodeName);
		return deletePool(name, deletePool, null);
	}

	public boolean deletePool(String name, String nodeName, DeletePool deletePool, String eventId) throws Exception {
		updateHost(name, nodeName);
		return deletePool(name, deletePool, eventId);
	}

}