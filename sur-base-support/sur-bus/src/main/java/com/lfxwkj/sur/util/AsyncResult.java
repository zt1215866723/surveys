package com.lfxwkj.sur.util;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyncResult<V> implements Future<V> {
    private final V value;

    public AsyncResult(V value) {
        this.value = value;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }

    public V get() {
        return this.value;
    }

    public V get(long timeout, TimeUnit unit) {
        return this.value;
    }
}
