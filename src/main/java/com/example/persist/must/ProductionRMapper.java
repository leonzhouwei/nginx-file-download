package com.example.persist.must;

import java.util.List;

import com.example.domain.Production;

public interface ProductionRMapper {
	
	public List<Production> selectAll();

	public Production selectById(long id);
	
}
