package com.example.dto;

import java.util.Collection;
import java.util.List;

import com.example.common.DateTimeTool;
import com.example.domain.DownloadTask;
import com.google.common.collect.Lists;

public class DownloadTaskDto extends Base {
	
	public Long id;
	public AccountDto user;
	public FileDto file;
	public String clientIp;
	public String expiredAt;
	public Long timeCostMillis;
	public String lastDldedAt;
	public String uuid;
	
	public static List<DownloadTaskDto> toList(Collection<DownloadTask> c) {
		List<DownloadTaskDto> ret = Lists.newArrayList();
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
		this.user = new AccountDto();
		this.user.id = e.getUserId();
		this.file = new FileDto();
		this.file.id = e.getFileId();
		this.clientIp = e.getClientIp();
		this.expiredAt = DateTimeTool.toIso8601(e.getExpiredAt());
		this.timeCostMillis = e.getTimeCostMillis();
		this.lastDldedAt = DateTimeTool.toIso8601(e.getLastDldedAt());
		this.uuid = e.getUuid();
	}

}
