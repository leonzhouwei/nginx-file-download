package com.example.domain;

import java.util.Date;

public class Base {
	
	private Date createdAt;
	private Date updatedAt;
	private Boolean enabled;
	
	public Base() {
		// no operations here
	}
	
	public void resetBy(Base another) {
		setCreatedAt(another.getCreatedAt());
		setUpdatedAt(another.getUpdatedAt());
		setEnabled(another.getEnabled());
	}
	
	public void reset() {
		final Date date = new Date();
		setCreatedAt(date);
		setUpdatedAt(date);
		setEnabled(true);
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void resetCreatedAt() {
		setCreatedAt(new Date());
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void resetUpdatedAt() {
		setUpdatedAt(new Date());
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public void enable() {
		setEnabled(true);
		resetUpdatedAt();
	}
	
	public void disable() {
		setEnabled(false);
		resetUpdatedAt();
	}
	
}
