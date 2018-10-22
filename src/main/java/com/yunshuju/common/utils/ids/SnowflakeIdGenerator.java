package com.yunshuju.common.utils.ids;


public class SnowflakeIdGenerator implements IIdGenerator {
	private Snowflake snowflake;

	public SnowflakeIdGenerator(long workerId, long datacenterId){
		this.snowflake = new Snowflake(workerId,datacenterId);
	}

	@Override
	public synchronized Long nextId() {
		return this.snowflake.nextId();
	}
}
