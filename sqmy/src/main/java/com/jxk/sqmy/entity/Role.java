package com.jxk.sqmy.entity;

public class Role {
	private Integer roleId;
	private String roleName;

	public Role(Integer roleId) {
		this.roleId = roleId;
	}

	public Role(Integer roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Role() {
	}

	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoloName() {
		return roleName;
	}
	public void setRoloName(String roloName) {
		this.roleName = roloName;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roloName=" + roleName + "]";
	}
	

}
