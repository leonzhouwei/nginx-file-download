package com.example.persist.must;

import java.util.List;

import com.example.domain.Production;

public interface ProductionRMapper {
	
	public List<Production> selectAll();

	public List<Production> selectAllIgnoreEnabled();
	
	public Production selectById(long id);
	
	public Production selectByIdIgnoreEnabled(long id);
	

}
