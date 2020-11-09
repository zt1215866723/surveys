package com.lfxwkj.sur.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lfxwkj.sur.entity.Item;
import com.lfxwkj.sur.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartListener implements ApplicationListener<ApplicationReadyEvent>{
    @Autowired
    private ItemService itemService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Item item = new Item();
        item.setSynchronousState(0);
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("synchronous_state", 1);
        itemService.update(item, itemQueryWrapper);
    }
}
