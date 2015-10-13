package com.example.persist.ssdb.driver;

public interface SsdbConnPoolConfig {

	public Integer getTimeout();

	public Integer getMaxActive();

	public Boolean getTestWhileIdle();

	public String getHost();

	public int getPort();

	public String getAuth();
	
}
