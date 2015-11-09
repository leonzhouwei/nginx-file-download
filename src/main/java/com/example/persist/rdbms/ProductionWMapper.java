package com.example.persist.rdbms;

import com.example.domain.Production;

public interface ProductionWMapper {
	
	public void insert(Production p);
	
	public void updateById(Production p);

}
