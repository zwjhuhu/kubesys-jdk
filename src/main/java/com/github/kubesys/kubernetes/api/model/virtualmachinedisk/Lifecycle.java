/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.kubernetes.api.model.virtualmachinedisk;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.kubesys.kubernetes.annotations.FunctionDescriber;
import com.github.kubesys.kubernetes.annotations.ParameterDescriber;
import com.github.kubesys.kubernetes.annotations.ClassDescriber;
import com.github.kubesys.kubernetes.utils.AnnotationUtils;
import com.github.kubesys.kubernetes.utils.RegExpUtils;

/**
 * @author  wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.0.0
 * @since   2019/9/4
 * 
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@ClassDescriber(value = "VirtualMachineDisk", desc = "云盘是指未格式化的云盘")
public class Lifecycle {

	@FunctionDescriber(shortName = "删除云盘", description = "删除云盘，" 
			+ AnnotationUtils.DESC_FUNCTION_DESC, 
		prerequisite = AnnotationUtils.DESC_FUNCTION_VMD, 
		exception = AnnotationUtils.DESC_FUNCTION_EXEC)
	protected DeleteDisk deleteDisk;

	@FunctionDescriber(shortName = "调整云盘大小", description = "调整云盘大小，" 
			+ AnnotationUtils.DESC_FUNCTION_DESC, 
		prerequisite = AnnotationUtils.DESC_FUNCTION_VMD, 
		exception = AnnotationUtils.DESC_FUNCTION_EXEC)
	protected ResizeDisk resizeDisk;

	@FunctionDescriber(shortName = "创建云盘", description = "创建云盘，" 
			+ AnnotationUtils.DESC_FUNCTION_DESC, 
		prerequisite = "", 
		exception = AnnotationUtils.DESC_FUNCTION_EXEC)
	protected CreateDisk createDisk;
	
	@FunctionDescriber(shortName = "从镜像创建云盘", description = "从镜像创建云盘，" 
			+ AnnotationUtils.DESC_FUNCTION_DESC, 
		prerequisite = "", 
		exception = AnnotationUtils.DESC_FUNCTION_EXEC)
	protected CreateDiskFromDiskImage createDiskFromDiskImage;
	
	@FunctionDescriber(shortName = "克隆云盘", description = "克隆云盘，" 
			+ AnnotationUtils.DESC_FUNCTION_DESC, 
		prerequisite = AnnotationUtils.DESC_FUNCTION_VMD, 
		exception = AnnotationUtils.DESC_FUNCTION_EXEC)
	protected CloneDisk cloneDisk;
	
	@FunctionDescriber(shortName = "创建云盘内部快照", description = "创建云盘内部快照，" 
			+ AnnotationUtils.DESC_FUNCTION_DESC, 
		prerequisite = AnnotationUtils.DESC_FUNCTION_VMD, 
		exception = AnnotationUtils.DESC_FUNCTION_EXEC)
	@Deprecated
	protected CreateDiskInternalSnapshot createDiskInternalSnapshot;
	
	@FunctionDescriber(shortName = "从云盘内部快照恢复", description = "从云盘内部快照恢复，" 
			+ AnnotationUtils.DESC_FUNCTION_DESC, 
		prerequisite = AnnotationUtils.DESC_FUNCTION_VMD, 
		exception = AnnotationUtils.DESC_FUNCTION_EXEC)
	@Deprecated
	protected RevertDiskInternalSnapshot revertDiskInternalSnapshot;
	
	@FunctionDescriber(shortName = "删除云盘内部快照", description = "删除云盘内部快照，" 
			+ AnnotationUtils.DESC_FUNCTION_DESC, 
		prerequisite = AnnotationUtils.DESC_FUNCTION_VMD, 
		exception = AnnotationUtils.DESC_FUNCTION_EXEC)
	@Deprecated
	protected DeleteDiskInternalSnapshot deleteDiskInternalSnapshot;

	public CreateDiskInternalSnapshot getCreateDiskSnapshot() {
		return createDiskInternalSnapshot;
	}

	public void setCreateDiskSnapshot(CreateDiskInternalSnapshot createDiskSnapshot) {
		this.createDiskInternalSnapshot = createDiskSnapshot;
	}

	public RevertDiskInternalSnapshot getRevertDiskSnapshot() {
		return revertDiskInternalSnapshot;
	}

	public void setRevertDiskSnapshot(RevertDiskInternalSnapshot revertDiskSnapshot) {
		this.revertDiskInternalSnapshot = revertDiskSnapshot;
	}

	public DeleteDiskInternalSnapshot getDeleteDiskSnapshot() {
		return deleteDiskInternalSnapshot;
	}

	public void setDeleteDiskSnapshot(DeleteDiskInternalSnapshot deleteDiskSnapshot) {
		this.deleteDiskInternalSnapshot = deleteDiskSnapshot;
	}
	
	public CreateDiskFromDiskImage getCreateDiskFromDiskImage() {
		return createDiskFromDiskImage;
	}
	
	public void setCreateDiskFromDiskImage(CreateDiskFromDiskImage createDiskFromDiskImage) {
		this.createDiskFromDiskImage = createDiskFromDiskImage;
	}
	
	public DeleteDisk getDeleteDisk() {
		return deleteDisk;
	}

	public void setDeleteDisk(DeleteDisk deleteDisk) {
		this.deleteDisk = deleteDisk;
	}

	public ResizeDisk getResizeDisk() {
		return resizeDisk;
	}

	public void setResizeDisk(ResizeDisk resizeDisk) {
		this.resizeDisk = resizeDisk;
	}

	public CreateDisk getCreateDisk() {
		return createDisk;
	}

	public void setCreateDisk(CreateDisk createDisk) {
		this.createDisk = createDisk;
	}

	public CloneDisk getCloneDisk() {
		return cloneDisk;
	}

	public void setCloneDisk(CloneDisk cloneDisk) {
		this.cloneDisk = cloneDisk;
	}

	public CreateDiskInternalSnapshot getCreateDiskInternalSnapshot() {
		return createDiskInternalSnapshot;
	}

	public void setCreateDiskInternalSnapshot(CreateDiskInternalSnapshot createDiskInternalSnapshot) {
		this.createDiskInternalSnapshot = createDiskInternalSnapshot;
	}

	public RevertDiskInternalSnapshot getRevertDiskInternalSnapshot() {
		return revertDiskInternalSnapshot;
	}

	public void setRevertDiskInternalSnapshot(RevertDiskInternalSnapshot revertDiskInternalSnapshot) {
		this.revertDiskInternalSnapshot = revertDiskInternalSnapshot;
	}

	public DeleteDiskInternalSnapshot getDeleteDiskInternalSnapshot() {
		return deleteDiskInternalSnapshot;
	}

	public void setDeleteDiskInternalSnapshot(DeleteDiskInternalSnapshot deleteDiskInternalSnapshot) {
		this.deleteDiskInternalSnapshot = deleteDiskInternalSnapshot;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
	public static class CreateDisk {

		@ParameterDescriber(required = true, description = "存储池的类型", constraint = "只能是localfs，vdiskfs，uus，nfs，glusterfs之一", example = "localfs")
		@Pattern(regexp = RegExpUtils.POOL_TYPE_PATTERN)
		protected String type;

		protected String allocation;

		protected Boolean prealloc_metadata;

		@ParameterDescriber(required = true, description = "云盘文件的类型", constraint = "qcow2", example = "qcow2")
		@Pattern(regexp = RegExpUtils.DISK_TYPE_PATTERN)
		protected String format;

		@ParameterDescriber(required = true, description = "创建云盘使用的存储池名", constraint = "已创建出的存储池", example = "pool2")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String pool;


		@ParameterDescriber(required = true, description = "云盘空间大小,1G到1T", constraint = "1000000000-999999999999（单位：Byte）", example = "‭10,737,418,240‬")
		@Pattern(regexp = RegExpUtils.DISK_SIZE_PATTERN)
		protected String capacity;

		public CreateDisk() {

		}

		public void setAllocation(String allocation) {
			this.allocation = allocation;
		}

		public String getAllocation() {
			return this.allocation;
		}

		public void setPrealloc_metadata(Boolean prealloc_metadata) {
			this.prealloc_metadata = prealloc_metadata;
		}

		public Boolean getPrealloc_metadata() {
			return this.prealloc_metadata;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		public String getFormat() {
			return this.format;
		}

		public void setPool(String pool) {
			this.pool = pool;
		}

		public String getPool() {
			return this.pool;
		}

		public void setCapacity(String capacity) {
			this.capacity = capacity;
		}

		public String getCapacity() {
			return this.capacity;
		}


		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
	public static class DeleteDisk {

		@ParameterDescriber(required = true, description = "存储池的类型", constraint = "只能是localfs，vdiskfs，uus，nfs，glusterfs之一", example = "localfs")
		@Pattern(regexp = RegExpUtils.POOL_TYPE_PATTERN)
		protected String type;

//		@ParameterDescriber(required = false, description = "删除所有快照", constraint = AnnotationUtils.DESC_BOOLEAN, example = "true")
//		protected Boolean delete_snapshots;

		@ParameterDescriber(required = true, description = "云盘所在的存储池名", constraint = "已创建出的存储池", example = "pool2")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String pool;

//		public void setDelete_snapshots(Boolean delete_snapshots) {
//			this.delete_snapshots = delete_snapshots;
//		}
//
//		public Boolean getDelete_snapshots() {
//			return this.delete_snapshots;
//		}

		public void setPool(String pool) {
			this.pool = pool;
		}

		public String getPool() {
			return this.pool;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
	public static class ResizeDisk {

		@ParameterDescriber(required = true, description = "存储池的类型", constraint = "只能是localfs，vdiskfs，nfs，glusterfs之一", example = "localfs")
		@Pattern(regexp = RegExpUtils.POOL_TYPE_NOT_SUPPORT_UUS)
		protected String type;

		protected Boolean allocate;

		protected Boolean shrink;

		protected Boolean delta;

		@ParameterDescriber(required = true, description = "云盘所在的存储池名", constraint = "已创建出的存储池", example = "pool2")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String pool;

		@ParameterDescriber(required = true, description = "扩容后的云盘空间大小, 1G到1T", constraint = "1000000000-999999999999（单位：Byte），需要比以前的云盘空间大", example = "‭10,737,418,240‬")
		@Pattern(regexp = RegExpUtils.DISK_SIZE_PATTERN)
		protected String capacity;

		public void setAllocate(Boolean allocate) {
			this.allocate = allocate;
		}

		public Boolean getAllocate() {
			return this.allocate;
		}

		public void setShrink(Boolean shrink) {
			this.shrink = shrink;
		}

		public Boolean getShrink() {
			return this.shrink;
		}

		public void setDelta(Boolean delta) {
			this.delta = delta;
		}

		public Boolean getDelta() {
			return this.delta;
		}

		public void setPool(String pool) {
			this.pool = pool;
		}

		public String getPool() {
			return this.pool;
		}

		public void setCapacity(String capacity) {
			this.capacity = capacity;
		}

		public String getCapacity() {
			return this.capacity;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
	public static class CreateDiskFromDiskImage {
		
		@ParameterDescriber(required = true, description = "存储池的类型", constraint = "只能是localfs，vdiskfs，nfs，glusterfs之一", example = "localfs")
		@Pattern(regexp = RegExpUtils.POOL_TYPE_NOT_SUPPORT_UUS)
		protected String type;

		@ParameterDescriber(required = true, description = "目标存储池名", constraint = "由4-100位的数字和小写字母组成，已创建出的存储池", example = "pool2")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String targetPool;

		@ParameterDescriber(required = true, description = "云盘镜像的路径", constraint = "路径必须在/var/lib/libvirt下，18-1024位，只允许小写、字母、中划线和圆点", example = "/var/lib/libvirt/test.qcow2")
		@Pattern(regexp = RegExpUtils.PATH_PATTERN)
		protected String source;

		@ParameterDescriber(required = false, description = "默认为从快照创建，true为全拷贝", constraint = "默认为从快照创建，true为全拷贝", example = "true")
		protected boolean full_copy;
		
		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getTargetPool() {
			return targetPool;
		}

		public void setTargetPool(String targetPool) {
			this.targetPool = targetPool;
		}

		public boolean getFull_copy() {
			return full_copy;
		}

		public void setFull_copy(boolean full_copy) {
			this.full_copy = full_copy;
		}
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
	public static class CloneDisk {

		@ParameterDescriber(required = true, description = "存储池的类型", constraint = "只能是localfs, vdiskfs，nfs，glusterfs之一", example = "localfs")
		@Pattern(regexp = RegExpUtils.POOL_TYPE_NOT_SUPPORT_UUS)
		protected String type;

		protected Boolean reflink;

		protected Boolean prealloc_metadata;

		@ParameterDescriber(required = true, description = "云盘所在的存储池名", constraint = "由4-100位的数字和小写字母组成，已创建出的存储池", example = "pool2")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String pool;

		@ParameterDescriber(required = true, description = "新云盘的名字", constraint = "由4-100位的数字和小写字母组成", example = "newdisk")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String newname;

		@ParameterDescriber(required = true, description = "云盘文件的类型", constraint = "qcow2", example = "qcow2")
		@Pattern(regexp = RegExpUtils.DISK_TYPE_PATTERN)
		protected String format;

		public CloneDisk() {

		}

		public void setReflink(Boolean reflink) {
			this.reflink = reflink;
		}

		public Boolean getReflink() {
			return this.reflink;
		}

		public void setPrealloc_metadata(Boolean prealloc_metadata) {
			this.prealloc_metadata = prealloc_metadata;
		}

		public Boolean getPrealloc_metadata() {
			return this.prealloc_metadata;
		}

		public void setPool(String pool) {
			this.pool = pool;
		}

		public String getPool() {
			return this.pool;
		}

		public void setNewname(String newname) {
			this.newname = newname;
		}

		public String getNewname() {
			return this.newname;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
	public static class ConvertImageToVM {
		
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
	@Deprecated
	public static class CreateDiskInternalSnapshot {
		
		@ParameterDescriber(required = true, description = "存储池的类型", constraint = "只能是localfs，nfs，glusterfs, vdiskfs之一", example = "localfs")
		@Pattern(regexp = RegExpUtils.POOL_TYPE_NOT_SUPPORT_UUS)
		protected String type;
		
		@ParameterDescriber(required = true, description = "云盘所在的存储池名", constraint = "由4-100位的数字和小写字母组成，已创建出的存储池", example = "pool2")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String pool;
		
		@ParameterDescriber(required = true, description = "快照的名字", constraint = "由4-100位的数字和小写字母组成", example = "snap1")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String snapshotname;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getPool() {
			return pool;
		}

		public void setPool(String pool) {
			this.pool = pool;
		}

		public String getSnapshotname() {
			return snapshotname;
		}

		public void setSnapshotname(String snapshotname) {
			this.snapshotname = snapshotname;
		}
		
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
	@Deprecated
	public static class RevertDiskInternalSnapshot {
		
		@ParameterDescriber(required = true, description = "存储池的类型", constraint = "只能是localfs, vdiskfs，nfs，glusterfs之一", example = "localfs")
		@Pattern(regexp = RegExpUtils.POOL_TYPE_NOT_SUPPORT_UUS)
		protected String type;
		
		@ParameterDescriber(required = true, description = "云盘所在的存储池名", constraint = "由4-100位的数字和小写字母组成，已创建出的存储池", example = "pool2")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String pool;
		
		@ParameterDescriber(required = true, description = "快照的名字", constraint = "由4-100位的数字和小写字母组成", example = "snap1")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String snapshotname;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getPool() {
			return pool;
		}

		public void setPool(String pool) {
			this.pool = pool;
		}

		public String getSnapshotname() {
			return snapshotname;
		}

		public void setSnapshotname(String snapshotname) {
			this.snapshotname = snapshotname;
		}
		
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
	@Deprecated
	public static class DeleteDiskInternalSnapshot {
		
		@ParameterDescriber(required = true, description = "存储池的类型", constraint = "只能是localfs, vdiskfs，nfs，glusterfs之一", example = "localfs")
		@Pattern(regexp = RegExpUtils.POOL_TYPE_NOT_SUPPORT_UUS)
		protected String type;
		
		@ParameterDescriber(required = true, description = "云盘所在的存储池名", constraint = "由4-100位的数字和小写字母组成，已创建出的存储池", example = "pool2")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String pool;
		
		@ParameterDescriber(required = true, description = "快照的名字", constraint = "由4-100位的数字和小写字母组成", example = "snap1")
		@Pattern(regexp = RegExpUtils.NAME_PATTERN)
		protected String snapshotname;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getPool() {
			return pool;
		}

		public void setPool(String pool) {
			this.pool = pool;
		}

		public String getSnapshotname() {
			return snapshotname;
		}

		public void setSnapshotname(String snapshotname) {
			this.snapshotname = snapshotname;
		}
		
	}
	
}
