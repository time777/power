package com.yunshuju.common.utils.ids;


public class MeWorkerIdGenerator implements IIdGenerator {

	private IdWorker idWorker;

	public MeWorkerIdGenerator(){
		this.idWorker = IdWorker.getFlowIdWorkerInstance();
	}

	public MeWorkerIdGenerator(long workerId){
		this.idWorker = IdWorker.getFlowIdWorkerInstance(workerId);
	}

	@Override
	public synchronized Long nextId() {
		return this.idWorker.nextId();
	}
}
