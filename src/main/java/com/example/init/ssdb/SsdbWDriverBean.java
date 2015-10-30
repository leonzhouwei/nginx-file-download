package com.example.init.ssdb;

import java.io.IOException;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import com.example.persist.nosql.ssdb.driver.impl.SsdbWDriverImpl;

@Component
public class SsdbWDriverBean extends SsdbWDriverImpl implements DisposableBean {

	private SsdbConnPoolConfigBean ssdbConnPoolConfig;
	
	@Override
	public void destroy() throws IOException {
		super.closeSsdb();
	}

	public SsdbConnPoolConfigBean getSsdbConnPoolConfig() {
		return ssdbConnPoolConfig;
	}

	public void setSsdbConnPoolConfig(SsdbConnPoolConfigBean ssdbConnPoolConfig)
			throws IOException {
		this.ssdbConnPoolConfig = ssdbConnPoolConfig;
		super.init(ssdbConnPoolConfig);
	}

}
