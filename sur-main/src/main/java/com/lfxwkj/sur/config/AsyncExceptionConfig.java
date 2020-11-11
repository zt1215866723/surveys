package com.lfxwkj.sur.config;

import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.service.ItemService;
import com.lfxwkj.sur.service.impl.ItemServiceImpl;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncExceptionConfig implements AsyncConfigurer {

    @Bean
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(15);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("Async-Service-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SpringAsyncExceptionHandler();
    }

    class SpringAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            System.out.println(throwable.getMessage());
            String methodName = method.getName();
            if(methodName.equals("selectData")){
                Long itemId = (Long)objects[0];
                Item item = new Item();
                item.setId(itemId);
                item.setSynchronousState(0);
                ItemService itemService = SpringUtils.getBean(ItemService.class);
                itemService.updateById(item);
            }
        }
    }
}
