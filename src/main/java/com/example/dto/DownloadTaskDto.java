package com.example.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.example.common.DateTimeTool;
import com.example.domain.Account;
import com.example.domain.DownloadTask;
import com.example.domain.File;
import com.google.common.collect.Lists;

public class DownloadTaskDto extends BaseDto {
	
	public Long id;
	public Account user;
	public File file;
	public String clientIp;
	public String expiredAt;
	public Long timeCostMillis;
	public String lastDldedAt;
	public String uuid;
	
	public static List<DownloadTaskDto> toList(Collection<DownloadTask> c) {
		List<DownloadTaskDto> ret = Lists.newArrayList();
		if (c == null) {
			return ret;
		}
		for (DownloadTask e : c) {
			DownloadTaskDto dto = new DownloadTaskDto(e);
			ret.add(dto);
		}
		return ret;
	}
	
	public DownloadTaskDto() {
		super();
	}
	
	public DownloadTaskDto(DownloadTask e) {
		super(e);
		this.id = e.getId();
		this.user = new Account();
		this.user.setId(e.getUserId());
		this.file = new File();
		this.file.setId(e.getFileId());
		this.clientIp = e.getClientIp();
		
		Date expiredAt = e.getExpiredAt();
		if (expiredAt != null) {
			this.expiredAt = DateTimeTool.toIso8601(expiredAt);
		}
		
		this.timeCostMillis = e.getTimeCostMillis();
		
		Date lastDldedAt = e.getLastDldedAt();
		if (lastDldedAt != null) {
			this.lastDldedAt = DateTimeTool.toIso8601(lastDldedAt);
		}
		
		this.uuid = e.getUuid();
	}

}
