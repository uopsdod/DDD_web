package com.emp.model;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotelrep.model.HotelRepVO;


public class EmpVO implements java.io.Serializable{
	private String bs64;
	
	private static final long serialVersionUID = 1L;
	private String empId;
	private String empName;
	private String empAccount;
	private String empPwd;
	private String empPhone;
	private Date empHireDate;
	private Date empFireDate;
	private String empStatus;
	private Date empBirthDate;
	private byte[] empProfile;
	private String empROCId;
	private String empAddress;
	
	@JsonIgnore
	private Set<HotelRepVO> empHotelReps = new LinkedHashSet<HotelRepVO>();
		
	public String getBs64() {
		return this.bs64;
	}
	public void setBs64(String aBs64) {
		this.bs64 = aBs64;
	}
	public String getEmpId() {
		return this.empId;
	}
	public void setEmpId(String aEmpId) {
		this.empId = aEmpId;
	}
	public String getEmpName() {
		return this.empName;
	}
	public void setEmpName(String aEmpName) {
		this.empName = aEmpName;
	}
	public String getEmpAccount() {
		return this.empAccount;
	}
	public void setEmpAccount(String aEmpAccount) {
		this.empAccount = aEmpAccount;
	}
	public String getEmpPwd() {
		return this.empPwd;
	}
	public void setEmpPwd(String aEmpPwd) {
		this.empPwd = aEmpPwd;
	}
	public String getEmpPhone() {
		return this.empPhone;
	}
	public void setEmpPhone(String aEmpPhone) {
		this.empPhone = aEmpPhone;
	}
	public Date getEmpHireDate() {
		return this.empHireDate;
	}
	public void setEmpHireDate(Date aEmpHireDate) {
		this.empHireDate = aEmpHireDate;
	}
	public Date getEmpFireDate() {
		return this.empFireDate;
	}
	public void setEmpFireDate(Date aEmpFireDate) {
		this.empFireDate = aEmpFireDate;
	}
	public String getEmpStatus() {
		return this.empStatus;
	}
	public void setEmpStatus(String aEmpStatus) {
		this.empStatus = aEmpStatus;
	}
	public Date getEmpBirthDate() {
		return this.empBirthDate;
	}
	public void setEmpBirthDate(Date aEmpBirthDate) {
		this.empBirthDate = aEmpBirthDate;
	}
	public byte[] getEmpProfile() {
		return this.empProfile;
	}
	public void setEmpProfile(byte[] aEmpProfile) {
		this.empProfile = aEmpProfile;
	}
	public String getEmpROCId() {
		return this.empROCId;
	}
	public void setEmpROCId(String aEmpROCId) {
		this.empROCId = aEmpROCId;
	}
	public String getEmpAddress() {
		return this.empAddress;
	}
	public void setEmpAddress(String aEmpAddress) {
		this.empAddress = aEmpAddress;
	}
	
	/* 韓哥hibernate練習用 */
	public Set<HotelRepVO> getEmpHotelReps() {
		return this.empHotelReps;
	}
	public void setEmpHotelReps(Set<HotelRepVO> aEmpHotelReps) {
		this.empHotelReps = aEmpHotelReps;
	}
	
}

