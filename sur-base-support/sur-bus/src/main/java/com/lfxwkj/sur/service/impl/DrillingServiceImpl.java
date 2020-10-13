package com.lfxwkj.sur.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.Drilling;
import com.lfxwkj.sur.mapper.DrillingMapper;
import com.lfxwkj.sur.model.params.DrillingParam;
import com.lfxwkj.sur.model.result.DrillingResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfxwkj.sur.model.result.DrillingVo;
import com.lfxwkj.sur.service.DrillingService;
import com.lfxwkj.sur.util.CoordinatesUtil;
import com.lfxwkj.sur.util.GPSConverterUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

/**
 * <p>
 * 勘探点数据表 服务实现类
 * </p>
 *
 * @author 张童
 * @since 2020-09-15
 */
@Service
public class DrillingServiceImpl extends ServiceImpl<DrillingMapper, Drilling> implements DrillingService {

    @Override
    public void add(DrillingParam param){
        Drilling entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(DrillingParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(DrillingParam param){
        Drilling oldEntity = getOldEntity(param);
        Drilling newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public DrillingResult findBySpec(DrillingParam param){
        return null;
    }

    @Override
    public List<DrillingResult> findListBySpec(DrillingParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(DrillingParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DrillingParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Drilling getOldEntity(DrillingParam param) {
        return this.getById(getKey(param));
    }

    private Drilling getEntity(DrillingParam param) {
        Drilling entity = new Drilling();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    /**
     * 选择多个勘探点查询列表
     *
     * @author 张童
     * @Date 2020-09-15
     */
    @Override
    public  List<DrillingResult> selectDrillingByIds(String holeCode,Long itemId) {
        //钻孔编号
        String[] strings = holeCode.split(",");
        return this.baseMapper.selectDrillingByIds(strings,itemId);
    }

    /**
     * 地图上点击某个工程查询该工程的钻孔信息
     *
     * @author 张童
     * @Date 2020-09-17
     */
    @Override
    public List<DrillingVo> selectDrillingByItemId(DrillingParam drillingParam) {
        List<DrillingVo> drillingVos = this.baseMapper.selectDrillingByItemId(drillingParam);
        //坐标转换
        for (DrillingVo d: drillingVos) {
            //格式化double类型数据，不用科学计数法表示
            DecimalFormat df = new DecimalFormat("0");
            String format = df.format(d.getZkx());
            //判断坐标是什么坐标系的
            if ( format.length() == 8 || format.length() == 6) {
                //xian 80 guass kruger 3degree zone 39 (也就是说X轴坐标是39开头小数点前是八位的)
                //xian 80 6度分带中央经线117E (也就是说X轴坐标是小数点前是六位的)
                double[] doubles = CoordinatesUtil.GaussToBL(d.getZkx(), d.getZky());
                String s = GPSConverterUtils.changgeXY(String.valueOf(doubles[0]), String.valueOf(doubles[1]));
                String[] split = s.split(",");
                d.setXaxis(split[0]);
                d.setYaxis(split[1]);
            }else{
                //X轴坐标开头4位代表以项目中的一个点为基准，无法转换为经纬度，不在地图中显示
            }
        }
        return drillingVos;
    }

}
