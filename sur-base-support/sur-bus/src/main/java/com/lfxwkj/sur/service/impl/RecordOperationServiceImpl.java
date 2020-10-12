package com.lfxwkj.sur.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfxwkj.sur.entity.Drilling;
import com.lfxwkj.sur.entity.RecordOperation;
import com.lfxwkj.sur.mapper.DrillingMapper;
import com.lfxwkj.sur.mapper.RecordOperationMapper;
import com.lfxwkj.sur.service.DrillingService;
import com.lfxwkj.sur.service.RecordOperationService;
import org.springframework.stereotype.Service;

@Service
public class RecordOperationServiceImpl extends ServiceImpl<RecordOperationMapper, RecordOperation> implements RecordOperationService {

}
