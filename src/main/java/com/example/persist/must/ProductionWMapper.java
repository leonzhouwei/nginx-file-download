package com.example.persist.must;

import com.example.domain.Production;

public interface ProductionWMapper {
	
	public void insert(Production p);
	
	public void update(Production p);
	
	public void enable(Production p);
	
	public void disable(Production p);
	
}
