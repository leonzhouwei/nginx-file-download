package com.example.persist.rdbms;

import java.util.List;

import com.example.domain.Production;

public interface ProductionRMapper {
	
	public List<Production> selectAll();

}
