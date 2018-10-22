package com.yunshuju.common.utils.ids;


public interface ICallable<V> {

	V call(Object... args);
}