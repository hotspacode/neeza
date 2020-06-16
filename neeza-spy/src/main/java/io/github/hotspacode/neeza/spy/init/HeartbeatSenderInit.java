package io.github.hotspacode.neeza.spy.init;

import io.github.hotspacode.neeza.base.concurrent.NamedThreadFactory;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.spy.transport.NettyHttpHeartbeatSender;
import io.github.hotspacode.neeza.transport.api.HeartbeatSender;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HeartbeatSenderInit {
    private ScheduledExecutorService pool = null;

    private void initSchedulerIfNeeded() {
        if (pool == null) {
            pool = new ScheduledThreadPoolExecutor(1,
                    new NamedThreadFactory("nezza-heartbeat-send-task", true),
                    new ThreadPoolExecutor.DiscardOldestPolicy());
        }
    }

    public void init(){
        HeartbeatSender heartbeatSender = new NettyHttpHeartbeatSender();
        scheduleHeartbeatTask(heartbeatSender);
    }

    private void scheduleHeartbeatTask(final HeartbeatSender sender) {
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    sender.sendHeartbeat();
                } catch (Throwable e) {
                    NeezaLog.warn("[HeartbeatSender] Send heartbeat error", e);
                }
            }
        }, 5000, sender.intervalMilliseconds(), TimeUnit.MILLISECONDS);
    }

}
