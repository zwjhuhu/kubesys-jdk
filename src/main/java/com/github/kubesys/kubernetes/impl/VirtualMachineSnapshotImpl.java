/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.kubernetes.impl;


import java.util.regex.Pattern;

import com.github.kubesys.kubernetes.api.model.VirtualMachineSnapshot;
import com.github.kubesys.kubernetes.api.model.VirtualMachineSnapshotList;
import com.github.kubesys.kubernetes.api.model.VirtualMachineSnapshotSpec;
import com.github.kubesys.kubernetes.api.model.virtualmachinesnapshot.Lifecycle;
import com.github.kubesys.kubernetes.api.model.virtualmachinesnapshot.Lifecycle.CreateSnapshot;
import com.github.kubesys.kubernetes.api.model.virtualmachinesnapshot.Lifecycle.DeleteSnapshot;
import com.github.kubesys.kubernetes.api.model.virtualmachinesnapshot.Lifecycle.RevertVirtualMachine;
import com.github.kubesys.kubernetes.utils.RegExpUtils;

/**
 * @author  wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.0.0
 * @since   2019/9/1
 **/
public class VirtualMachineSnapshotImpl extends AbstractImpl<VirtualMachineSnapshot, VirtualMachineSnapshotList, VirtualMachineSnapshotSpec> {

	@Override
	public VirtualMachineSnapshot getModel() {
		return new VirtualMachineSnapshot();
	}

	@Override
	public VirtualMachineSnapshotSpec getSpec() {
		return new VirtualMachineSnapshotSpec();
	}

	@Override
	public Object getLifecycle() {
		return new Lifecycle();
	}

	
	@Override
	public VirtualMachineSnapshotSpec getSpec(VirtualMachineSnapshot r) {
		return r.getSpec();
	}

	/*************************************************
	 * 
	 * Generated by <code>ImplMethodGenerator<code>
	 * 
	 **************************************************/

	public boolean deleteSnapshot(String name, DeleteSnapshot deleteSnapshot) throws Exception {
		return deleteSnapshot(name, deleteSnapshot, null);
	}

	public boolean deleteSnapshot(String name,DeleteSnapshot deleteSnapshot, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 8 and 43, and it can only includes a-z, 0-9 and -.");
		}
		return delete(name, updateMetadata(name, eventId), deleteSnapshot);
	}

	public boolean createSnapshot(String name, CreateSnapshot createSnapshot) throws Exception {
		return createSnapshot(name, null, createSnapshot, null);
	}

	public boolean createSnapshot(String name, String nodeName, CreateSnapshot createSnapshot) throws Exception {
		return createSnapshot(name, nodeName, createSnapshot, null);
	}

	public boolean createSnapshot(String name, CreateSnapshot createSnapshot, String eventId) throws Exception {
		return createSnapshot(name, null, createSnapshot, eventId);
	}

	public boolean createSnapshot(String name, String nodeName,CreateSnapshot createSnapshot, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 8 and 43, and it can only includes a-z, 0-9 and -.");
		}
		return create(getModel(), createMetadata(name, nodeName, eventId), 
				createSpec(nodeName, createLifecycle(createSnapshot)));
	}

	public boolean revertVirtualMachine(String name, RevertVirtualMachine revertVirtualMachine) throws Exception {
		return revertVirtualMachine(name, revertVirtualMachine, null);
	}

	public boolean revertVirtualMachine(String name,RevertVirtualMachine revertVirtualMachine, String eventId) throws Exception {
		Pattern pattern = Pattern.compile(RegExpUtils.NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("the length must be between 8 and 43, and it can only includes a-z, 0-9 and -.");
		}
		return update(name, updateMetadata(name, eventId), revertVirtualMachine);
	}
}
