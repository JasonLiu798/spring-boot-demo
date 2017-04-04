package com.jason798.config;

/**
 * Created by async on 2017/4/4.
 */
public class ConfigServiceImpl implements ConfigService{
	@Override
	public Integer getIntValueByKey(String key,Integer dft) {
		return dft;
	}
}
