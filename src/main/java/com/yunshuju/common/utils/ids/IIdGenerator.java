package com.yunshuju.common.utils.ids;

import java.nio.ByteBuffer;

import com.yunshuju.common.utils.MeStringUtils;

/**
 * 数据库主键生成器接口
 */
public interface IIdGenerator {
	Long nextId();

	default String nextStringId(){
		/**
		 * 0123456789abcdefghijklmnopqrstuvwxyz
		 * 36进制后，不足12位的高位补0
		 */
		return MeStringUtils.highFill0(Long.toString(this.nextId(), 36), 12);
	}

	/**
	 * 暂时没用，将删除
	 * @param l
	 * @return
	 */
	@Deprecated
	default byte[] longToBytes(Long l) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(l);
		return buffer.array();
	}
}
