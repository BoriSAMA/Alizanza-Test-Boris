package com.alianza.clientes.api.entity;

import java.time.LocalDateTime;

// @Entity
// @Table(name = "CLIENTE")
// @EntityListeners(AuditingEntityListener.class)
// @Where(clause = "S_DELETE = 0")
public class ClienteEntity extends Entity<Long> {

	public interface Attributes {
		String SHARED_KEY = "sharedKey";
		String NAME = "name";
		String PHONE = "phone";
		String EMAIL = "email";
		String START_DATE = "startDate";		
		String END_DATE = "endDate";
	}

	private Long id;
	private String sharedKey;
	private String name;
	private String phone;
	private String email;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSharedKey() {
		return sharedKey;
	}

	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

}
