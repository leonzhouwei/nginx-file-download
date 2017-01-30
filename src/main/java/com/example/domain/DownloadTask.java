package com.example.domain;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public class DownloadTask extends Base {

	private Long id;
	private Account user = new Account();
	private File file = new File();
	private String clientIp;
	private Date expiredAt;
	private Long timeCostMillis;
	private Date lastDldedAt;
	private String uuid;
	
	public void reset() {
		super.reset();
		DateTime dateTime = new DateTime(getCreatedAt());
		DateTime expired = dateTime.plusDays(7);
		setExpiredAt(expired.toDate());
		setTimeCostMillis(0L);
		setLastDldedAt(getCreatedAt());
		setUser(new Account());
		setFile(new File());
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		if (clientIp == null) {
			this.clientIp = StringUtils.EMPTY;
			return;
		}
		
		this.clientIp = clientIp;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTimeCostMillis() {
		return timeCostMillis;
	}

	public void setTimeCostMillis(Long timeCostMillis) {
		this.timeCostMillis = timeCostMillis;
	}

	public Date getLastDldedAt() {
		return lastDldedAt;
	}

	public void setLastDldedAt(Date lastDldedAt) {
		this.lastDldedAt = lastDldedAt;
	}
	
	public void resetLastDldedAt() {
		setLastDldedAt(new Date());
	}
	
	public void resetTimeCostMillis() {
		setTimeCostMillis(lastDldedAt.getTime() - getCreatedAt().getTime());
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
