package com.yunshuju.common.utils.ids;

/**
 * id生成器工厂
 */
public final class IdGenerateFactory implements IIdGenerator {
	private IdGenerateFactory(){

	}
	private IIdGenerator idGenerator;

	public IdGenerateFactory(Long workerId){
		this.idGenerator = new MeWorkerIdGenerator(workerId);
	}

	public IdGenerateFactory(Long workerId, Long dataCenterId){
		if(dataCenterId == null){
			this.idGenerator = new MeWorkerIdGenerator(workerId);
		}else{
			this.idGenerator = new SnowflakeIdGenerator(workerId,dataCenterId);
		}
	}

	@Override
	public Long nextId() {
		return this.idGenerator.nextId();
	}
}
