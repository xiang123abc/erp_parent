package cn.ecit.erp.entity;

import java.io.Serializable;

public class Dep  implements Serializable{
private String uuid;//部门编号
private String name;//部门名称
private String tele;//部门电话
private String depcode;
public String getDepcode() {
	return depcode;
}
public void setDepcode(String depcode) {
	this.depcode = depcode;
}
public String getUuid() {
return uuid;
}
public void setUuid(String uuid) {
this.uuid = uuid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTele() {
	return tele;
}
public void setTele(String tele) {
	this.tele = tele;
}
}