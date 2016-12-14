package com.example.persist.must;

import java.util.List;

import com.example.domain.Production;

public interface ProductionRMapper {
	
	public Production selectById(long id);
	
	public Production selectEnabledById(long id);
	
	public List<Production> selectAll();
	
	public List<Production> selectAllEnabled();
	
}
