package com.example.persist.must;

import com.example.domain.Production;

public interface AdminProductionWMapper {
	
	public Integer insert(Production p);
	
	public void update(Production p);
	
	public void updateEnabled(Production p);

}
