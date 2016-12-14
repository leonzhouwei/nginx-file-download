package com.example.persist.must;

import com.example.domain.Production;

public interface ProductionWMapper {
	
	public void insert(Production p);
	
	public void update(Production p);
	
	public void updateEnabled(Production p);
	
	public void delete(Production p);

}
