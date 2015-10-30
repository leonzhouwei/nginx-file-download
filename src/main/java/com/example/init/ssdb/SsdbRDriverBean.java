package com.example.init.ssdb;

import java.io.IOException;

import org.springframework.beans.factory.DisposableBean;

import com.example.persist.nosql.ssdb.driver.impl.SsdbRDriverImpl;

public class SsdbRDriverBean extends SsdbRDriverImpl implements DisposableBean {

	private SsdbConnPoolConfigBean ssdbConnPoolConfig;
	
	@Override
	public void destroy() throws IOException {
		super.closeSsdb();
	}

	// external dependencies start ---------------------------------------------
	public SsdbConnPoolConfigBean getSsdbConnPoolConfig() {
		return ssdbConnPoolConfig;
	}

	public void setSsdbConnPoolConfig(SsdbConnPoolConfigBean ssdbConnPoolConfig)
			throws IOException {
		this.ssdbConnPoolConfig = ssdbConnPoolConfig;
		super.init(ssdbConnPoolConfig);
	}
	// external dependencies end ----------------------------------------------

}
