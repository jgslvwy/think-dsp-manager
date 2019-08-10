package com.dts.manager.concurrent;

import java.util.concurrent.ThreadFactory;
import lombok.NoArgsConstructor;
import org.springframework.util.CustomizableThreadCreator;

@NoArgsConstructor
public class DtsThreadFactory extends CustomizableThreadCreator implements ThreadFactory {
    public DtsThreadFactory(String threadNamePrefix) {
        super(threadNamePrefix);
    }

    @Override public Thread newThread(Runnable r) {
        return this.createThread(r);
    }
}
