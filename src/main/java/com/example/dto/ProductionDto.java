package com.example.dto;

import java.util.Collection;
import java.util.List;

import com.example.domain.Production;
import com.google.common.collect.Lists;

public class ProductionDto extends Base {
	
	public Long id;
	public String name;
	public String description;
	public String dir;
	
	public static List<ProductionDto> toList(Collection<Production> c) {
		List<ProductionDto> ret = Lists.newArrayList();
		for (Production e : c) {
			ProductionDto dto = new ProductionDto(e);
			ret.add(dto);
		}
		return ret;
	}
	
	public ProductionDto() {
		super();
	}
	
	public ProductionDto(Production e) {
		super(e);
		this.id = e.getId();
		this.name = e.getName();
		this.description = e.getDescription();
		this.dir = e.getDir();
	}

}
