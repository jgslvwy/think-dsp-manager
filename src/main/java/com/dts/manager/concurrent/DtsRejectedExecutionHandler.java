package com.dts.manager.concurrent;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class DtsRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

    }
}
