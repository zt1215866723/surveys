package com.lfxwkj.sur.service.impl;

import cn.hutool.system.SystemUtil;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.auth.context.LoginContextHolder;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.config.FileUploadConfig;
import com.lfxwkj.sur.entity.*;
import com.lfxwkj.sur.mapper.*;
import com.lfxwkj.sur.model.params.ItemParam;
import com.lfxwkj.sur.model.result.ItemResult;
import com.lfxwkj.sur.model.result.SubDetailResult;
import com.lfxwkj.sur.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfxwkj.sur.sys.modular.system.entity.Dict;
import com.lfxwkj.sur.sys.modular.system.service.DictService;
import com.lfxwkj.sur.util.AsyncResult;
import com.lfxwkj.sur.util.CoordinatesUtil;
import com.lfxwkj.sur.util.GPSConverterUtils;
import com.lfxwkj.sur.util.ReadMdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    private ItemSubMapper itemSubMapper;
    @Autowired
    private SubDetailMapper subDetailMapper;
    @Autowired
    private DrillingMapper drillingMapper;
    @Autowired
    private DictService dictService;
    @Autowired
    private ReadMdb readMdb;
    @Autowired
    private DrillingService drillingService;
    @Autowired
    private LineService lineService;
    @Autowired
    private StandardService standardService;
    @Autowired
    private StandardFormationService standardFormationService;
    @Autowired
    private StandardPenetrationService standardPenetrationService;
    @Autowired
    private StaticTestService staticTestService;
    @Autowired
    private WaterLevelService waterLevelService;
    @Autowired
    private SampleService sampleService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private LineMapper lineMapper;
    @Autowired
    private StandardMapper standardMapper;
    @Autowired
    private StandardFormationMapper standardFormationMapper;
    @Autowired
    private StandardPenetrationMapper standardPenetrationMapper;
    @Autowired
    private StaticTestMapper staticTestMapper;
    @Autowired
    private WaterLevelMapper waterLevelMapper;
    @Autowired
    private FocusMapper focusMapper;
    @Autowired
    private IndexMapper indexMapper;
    @Override
    @Transactional
    public void add(ItemParam param) {
        if (ToolUtil.isNotEmpty(param.getKcXaxis())&& ToolUtil.isNotEmpty(param.getKcYaxis())) {
            //格式化double类型数据，不用科学计数法表示
            DecimalFormat df = new DecimalFormat("0");
            String format = df.format(param.getKcXaxis());
            //判断坐标是什么坐标系的
            if ( format.length() == 8 || format.length() == 6) {
                //xian 80 guass kruger 3degree zone 39 (也就是说X轴坐标是39开头小数点前是八位的)
                //xian 80 6度分带中央经线117E (也就是说X轴坐标是小数点前是六位的)
                double[] doubles = CoordinatesUtil.GaussToBL(param.getKcXaxis(), param.getKcYaxis(),param.getCoorSystem());
//                String s = GPSConverterUtils.changgeXY(String.valueOf(doubles[0]), String.valueOf(doubles[1]));
//                String[] split = s.split(",");
                param.setXaxis(String.valueOf(doubles[0]));
                param.setYaxis(String.valueOf(doubles[1]));
            }else{
                //X轴坐标开头4位代表以项目中的一个点为基准，无法转换为经纬度，不在地图中显示
            }
        }
        Item entity = getEntity(param);
        entity.setState(0);
        this.save(entity);
        //添加工程信息同时添加文档信息
        ItemSub itemSub = new ItemSub();
        itemSub.setItemId(entity.getId());
        itemSub.setSurName(entity.getItemName());
        itemSub.setSurNum(entity.getItemCode());
        itemSub.setAddUser(LoginContextHolder.getContext().getUserId());
        itemSub.setAddTime(new Date());
        itemSub.setState(0);
        itemSub.setIsBorrow(0);
        this.itemSubMapper.insert(itemSub);

        QueryWrapper<Focus> focusQueryWrapper = new QueryWrapper<>();
        focusQueryWrapper.eq("type", 0);
        focusQueryWrapper.select("id");
        List<Focus> focusList = focusMapper.selectList(focusQueryWrapper);
        List<Long> doubleFocusIds = focusList.stream().map(Focus::getId).collect(Collectors.toList());
        if(param.getFocus() != null){
            for(Map.Entry<Long, String> entry : param.getFocus().entrySet()){
                if(!"".equals(entry.getValue())){
                    Index index = new Index();
                    index.setFocusId(entry.getKey());
                    index.setState(0);
                    index.setSubId(entity.getId());
                    if(doubleFocusIds.contains(entry.getKey())){
                        index.setNouValue("".equals(entry.getValue())? null : Double.valueOf(entry.getValue()));
                    }else{
                        index.setStrValue(entry.getValue());
                    }
                    indexMapper.insert(index);
                }
            }
        }
    }

    @Override
    public void delete(ItemParam param) {
        param.setState(1);
        this.update(param);
    }

    @Override
    public void update(ItemParam param) {
        Item oldEntity = getOldEntity(param);
        Item newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);

        if(param.getFocus() != null){
            QueryWrapper<Focus> focusQueryWrapper = new QueryWrapper<>();
            focusQueryWrapper.eq("type", 0);
            focusQueryWrapper.select("id");
            List<Focus> focusList = focusMapper.selectList(focusQueryWrapper);
            List<Long> doubleFocusIds = focusList.stream().map(Focus::getId).collect(Collectors.toList());
            for(Map.Entry<Long, String> entry : param.getFocus().entrySet()){
                QueryWrapper<Index> indexQueryWrapper = new QueryWrapper<>();
                indexQueryWrapper.eq("sub_id", param.getId());
                indexQueryWrapper.eq("focus_id", entry.getKey());
                indexQueryWrapper.eq("state", 0);
                Index index = indexMapper.selectOne(indexQueryWrapper);
                if(index == null){
                    index = new Index();
                    index.setSubId(param.getId());
                    index.setFocusId(entry.getKey());
                    index.setState(0);
                }
                if(doubleFocusIds.contains(entry.getKey())){
                    index.setNouValue(Double.valueOf(entry.getValue()));
                }else{
                    index.setStrValue(entry.getValue());
                }
                if(index.getId() == null){
                    indexMapper.insert(index);
                }else{
                    indexMapper.updateById(index);
                }
            }
        }
    }

    @Override
    public ItemResult findBySpec(ItemParam param) {
        return null;
    }

    @Override
    public List<ItemResult> findListBySpec(ItemParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ItemParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public List<Item> getList(ItemParam itemParam) {
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("state", 0);
        return this.list(itemQueryWrapper);
    }

    @Override
    public List<LayuiTreeNode> getTree(Long itemId) {
        List<LayuiTreeNode> layuiTreeNodeList = new ArrayList<>();
        QueryWrapper<ItemSub> itemSubQueryWrapper = new QueryWrapper<>();
        itemSubQueryWrapper.eq("item_id", itemId);
        itemSubQueryWrapper.eq("state", 0);
        List<ItemSub> itemSubList = itemSubMapper.selectList(itemSubQueryWrapper);
        for (ItemSub itemSub : itemSubList) {
            List<LayuiTreeNode> layuiTreeNodeList1 = subDetailMapper.getTree(itemSub.getId());
            LayuiTreeNode layuiTreeNode = new LayuiTreeNode();
            layuiTreeNode.setTitle(itemSub.getSurName());
            layuiTreeNode.setId(itemSub.getId());
            layuiTreeNode.setPid(-1L);
            layuiTreeNode.setSpread(true);
            layuiTreeNodeList1.add(layuiTreeNode);
            layuiTreeNodeList.addAll(layuiTreeNodeList1);
        }
        return layuiTreeNodeList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData synchronous(Long itemId, String fileUrl) {
        Item item = this.getById(itemId);
        item.setSynchronousState(1);
        this.updateById(item);
        //暂定同步（钻孔，静探，标贯，土层，水位，工程，抛线，土层标准）
        String[] tableNames = {"z_ZuanKong", "z_y_JingTan", "z_y_BiaoGuan", "z_g_TuCeng", "z_g_ShuiWei", "x_GongCheng", "p_PouXian", "g_STuCengGC", "z_c_quyang"};
        try {
            readMdb.selectData(itemId, fileUrl, tableNames);
        } catch (RejectedExecutionException | ParseException | ClassNotFoundException e) {
            e.printStackTrace();
            item.setSynchronousState(0);
            this.updateById(item);
            return ResponseData.error("当前处理任务过多，请稍后重试。");
        }
        return ResponseData.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 3000)
    public void saveData(Long itemId, boolean sign, Map<String, List<Map>> data) throws ParseException {
        Item item = this.getById(itemId);
        if (sign) {
            //删除当前工程下的所有理正数据
            QueryWrapper<Drilling> drillingQueryWrapper = new QueryWrapper<>();
            drillingQueryWrapper.eq("item_id", itemId);
            drillingMapper.delete(drillingQueryWrapper);
            QueryWrapper<Line> lineQueryWrapper = new QueryWrapper<>();
            lineQueryWrapper.eq("item_id", itemId);
            lineMapper.delete(lineQueryWrapper);
            QueryWrapper<Standard> standardQueryWrapper = new QueryWrapper<>();
            standardQueryWrapper.eq("item_id", itemId);
            standardMapper.delete(standardQueryWrapper);
            QueryWrapper<StandardFormation> standardFormationQueryWrapper = new QueryWrapper<>();
            standardFormationQueryWrapper.eq("item_id", itemId);
            standardFormationMapper.delete(standardFormationQueryWrapper);
            QueryWrapper<StandardPenetration> standardPenetrationQueryWrapper = new QueryWrapper<>();
            standardPenetrationQueryWrapper.eq("item_id", itemId);
            standardPenetrationMapper.delete(standardPenetrationQueryWrapper);
            QueryWrapper<StaticTest> staticTestQueryWrapper = new QueryWrapper<>();
            staticTestQueryWrapper.eq("item_id", itemId);
            staticTestMapper.delete(staticTestQueryWrapper);
            QueryWrapper<WaterLevel> waterLevelQueryWrapper = new QueryWrapper<>();
            waterLevelQueryWrapper.eq("item_id", itemId);
            waterLevelMapper.delete(waterLevelQueryWrapper);
            for (Map.Entry<String, List<Map>> entry : data.entrySet()) {
                String tableName = entry.getKey();
                List<Map> tableData = entry.getValue();
                if (tableName.equals("z_ZuanKong")) {
                    //同步钻孔信息
                    List<Drilling> drillings1 = new ArrayList<>();
                    for (Map map : tableData) {
                        Drilling drilling = new Drilling();
                        drilling.setItemId(itemId);
                        drilling.setHoleCode(map.get("ZKBH") == null ? null : (String) map.get("ZKBH"));
                        drilling.setType(map.get("ZKLX") == null ? null : (String) map.get("ZKLX"));
                        drilling.setZkx(map.get("ZKX") == null ? null : new BigDecimal((String) map.get("ZKX")).doubleValue());
                        drilling.setZky(map.get("ZKY") == null ? null : new BigDecimal((String) map.get("ZKY")).doubleValue());
                        drilling.setJzdd(map.get("JZDD") == null ? null : (String) map.get("JZDD"));
                        drilling.setZkdh(map.get("ZKDH") == null ? null : (String) map.get("ZKDH"));
                        drilling.setZklc(map.get("ZKLC") == null ? null : new BigDecimal((String) map.get("ZKLC")).doubleValue());
                        drilling.setZkpil(map.get("ZKPIL") == null ? null : new BigDecimal((String) map.get("ZKPIL")).doubleValue());
                        drilling.setZkbg(map.get("ZKBG") == null ? null : new BigDecimal((String) map.get("ZKBG")).doubleValue());
                        drilling.setZkhsbg(map.get("ZKHSBG") == null ? null : new BigDecimal((String) map.get("ZKHSBG")).doubleValue());
                        drilling.setDepth(map.get("ZKSD") == null ? null : new BigDecimal((String) map.get("ZKSD")).doubleValue());
                        drilling.setZktjsd(map.get("ZKTJSD") == null ? null : new BigDecimal((String) map.get("ZKTJSD")).doubleValue());
                        drilling.setZkzj(map.get("ZKZJ") == null ? null : new BigDecimal((String) map.get("ZKZJ")).doubleValue());
                        drilling.setZkdclc(map.get("ZKDCLC") == null ? null : (String) map.get("ZKDCLC"));
                        drilling.setZkdcjj(map.get("ZKDCJJ") == null ? null : new BigDecimal((String) map.get("ZKDCJJ")).doubleValue());
                        drilling.setZkdcqsd(map.get("ZKDCQSD") == null ? null : new BigDecimal((String) map.get("ZKDCQSD")).doubleValue());
                        drilling.setZkdczsd(map.get("ZKDCZSD") == null ? null : new BigDecimal((String) map.get("ZKDCZSD")).doubleValue());
                        drilling.setZkdcpm(map.get("ZKDCPM") == null ? null : (String) map.get("ZKDCPM"));
                        drilling.setZkxn(map.get("ZKXN") == null ? null : (String) map.get("ZKXN"));
                        drilling.setZksfcy(map.get("ZKSFCY") == null ? null : Integer.parseInt(String.valueOf(map.get("ZKSFCY"))));
                        drilling.setZkjyk(map.get("ZKJYK") == null ? null : Integer.parseInt(String.valueOf(map.get("ZKJYK"))));
                        drilling.setZkdxsw(map.get("ZKDXSW") == null ? null : new BigDecimal((String) map.get("ZKDXSW")).doubleValue());
                        drilling.setZktcjd(map.get("ZKTCJD") == null ? null : new BigDecimal((String) map.get("ZKTCJD")).doubleValue());
                        drilling.setZkksrq(map.get("ZKKSRQ") == null ? null : this.timeConversion(String.valueOf(map.get("ZKKSRQ"))));
                        drilling.setZkzzrq(map.get("ZKZZRQ") == null ? null : this.timeConversion(String.valueOf(map.get("ZKZZRQ"))));
                        drilling.setZkdj(map.get("ZKDJ") == null ? null : Integer.parseInt(String.valueOf(map.get("ZKDJ"))));
                        drilling.setZkpztsdxs(map.get("ZKPZTSDXS") == null ? null : new BigDecimal((String) map.get("ZKPZTSDXS")).doubleValue());
                        drilling.setZkpzdqyxsd(map.get("ZKPZDQYXSD") == null ? null : new BigDecimal((String) map.get("ZKPZDQYXSD")).doubleValue());
                        drilling.setBz(map.get("BZ") == null ? null : (String) map.get("BZ"));
                        drilling.setZksc(map.get("ZKSC") == null ? null : Integer.parseInt(String.valueOf(map.get("ZKSC"))));
                        drilling.setZkh(map.get("ZKH") == null ? null : (String) map.get("ZKH"));
                        drilling.setZkv(map.get("ZKV") == null ? null : Integer.parseInt(String.valueOf(map.get("ZKV"))));
                        drilling.setZkyhzs(map.get("ZKYHZS") == null ? null : (String) map.get("ZKYHZS"));
                        drilling.setSxdj(map.get("SXDJ") == null ? null : Integer.parseInt(String.valueOf(map.get("SXDJ"))));
                        drilling.setSxlx(map.get("SXLX") == null ? null : Integer.parseInt(String.valueOf(map.get("SXLX"))));
                        drilling.setZkpzqs(map.get("ZKPZQS") == null ? null : Integer.parseInt(String.valueOf(map.get("ZKPZQS"))));
                        drilling.setZkpzdj(map.get("ZKPZDJ") == null ? null : Integer.parseInt(String.valueOf(map.get("ZKPZDJ"))));
                        drilling.setZkymchsl(map.get("ZKYMCHSL") == null ? null : new BigDecimal((String) map.get("ZKYMCHSL")).doubleValue());
                        drilling.setZkymcsxhsl(map.get("ZKYMCSXHSL") == null ? null : new BigDecimal((String) map.get("ZKYMCSXHSL")).doubleValue());
                        drilling.setDtable(map.get("DTABLE") == null ? null : (String) map.get("DTABLE"));
                        drilling.setYsgcbh(map.get("YSGCBH") == null ? null : (String) map.get("YSGCBH"));
                        drilling.setZkcolor(map.get("ZKCOLOR") == null ? null : Integer.parseInt(String.valueOf(map.get("ZKCOLOR"))));
                        drilling.setZkzbh(map.get("ZKZBH") == null ? null : (String) map.get("ZKZBH"));
                        drilling.setZkszff(map.get("ZKSZFF") == null ? null : (String) map.get("ZKSZFF"));
                        drilling.setZkzjlx(map.get("ZKZJLX") == null ? null : (String) map.get("ZKZJLX"));
                        drilling.setZkztdw(map.get("ZKZTDW") == null ? null : (String) map.get("ZKZTDW"));
                        drilling.setZkgdmc(map.get("ZKGDMC") == null ? null : (String) map.get("ZKGDMC"));
                        drilling.setZkdmbg(map.get("ZKDMBG") == null ? null : new BigDecimal((String) map.get("ZKDMBG")).doubleValue());
                        drilling.setJyjcgc(map.get("JYJCGC") == null ? null : new BigDecimal((String) map.get("JYJCGC")).doubleValue());
                        drilling.setSortkey(map.get("SORTKEY") == null ? null : Integer.parseInt(String.valueOf(map.get("SORTKEY"))));
                        drillings1.add(drilling);
                    }
                    drillingService.saveBatch(drillings1);
                } else if (tableName.equals("z_y_JingTan")) {
                    //同步静探信息
                    List<StaticTest> staticTests = new ArrayList<>();
                    for (Map map : tableData) {
                        StaticTest staticTest = new StaticTest();
                        staticTest.setItemId(itemId);
                        staticTest.setHoleCode(map.get("ZKBH") == null ? null : (String) map.get("ZKBH"));
                        staticTest.setDepth(map.get("JTDSD") == null ? null : new BigDecimal((String) map.get("JTDSD")).doubleValue());
                        staticTest.setJtlx(map.get("JTLX") == null ? null : Integer.parseInt(String.valueOf(map.get("JTLX"))));
                        staticTest.setLength(map.get("JTCD") == null ? null : new BigDecimal((String) map.get("JTCD")).doubleValue());
                        staticTest.setJtbgrzl(map.get("JTBGRZL") == null ? null : new BigDecimal((String) map.get("JTBGRZL")).doubleValue());
                        staticTest.setJtztzl(map.get("JTZTZL") == null ? null : new BigDecimal((String) map.get("JTZTZL")).doubleValue());
                        staticTest.setJtcmz(map.get("JTCMZ") == null ? null : new BigDecimal((String) map.get("JTCMZ")).doubleValue());
                        staticTest.setJtmzb(map.get("JTMZB") == null ? null : new BigDecimal((String) map.get("JTMZB")).doubleValue());
                        staticTest.setJtkxyl(map.get("JTKXYL") == null ? null : new BigDecimal((String) map.get("JTKXYL")).doubleValue());
                        staticTest.setCy(map.get("CY") == null ? null : Integer.parseInt(String.valueOf(map.get("CY"))));
                        staticTest.setJtbgrzlx(map.get("JTBGRZL_") == null ? null : Integer.parseInt(String.valueOf(map.get("JTBGRZL_"))));
                        staticTest.setJtbgrzltjx(map.get("JTBGRZLTJ_") == null ? null : Integer.parseInt(String.valueOf(map.get("JTBGRZLTJ_"))));
                        staticTest.setJtztzlx(map.get("JTZTZL_") == null ? null : Integer.parseInt(String.valueOf(map.get("JTZTZL_"))));
                        staticTest.setJtztzltjx(map.get("JTZTZLTJ_") == null ? null : Integer.parseInt(String.valueOf(map.get("JTZTZLTJ_"))));
                        staticTest.setJtcmzx(map.get("JTCMZ_") == null ? null : Integer.parseInt(String.valueOf(map.get("JTCMZ_"))));
                        staticTest.setJtcmztjx(map.get("JTCMZTJ_") == null ? null : Integer.parseInt(String.valueOf(map.get("JTCMZTJ_"))));
                        staticTest.setJtmzbx(map.get("JTMZB_") == null ? null : Integer.parseInt(String.valueOf(map.get("JTMZB_"))));
                        staticTest.setJtmzbtjx(map.get("JTMZBTJ_") == null ? null : Integer.parseInt(String.valueOf(map.get("JTMZBTJ_"))));
                        staticTest.setJtkxylx(map.get("JTKXYL_") == null ? null : Integer.parseInt(String.valueOf(map.get("JTKXYL_"))));
                        staticTest.setJtkxyltjx(map.get("JTKXYLTJ_") == null ? null : Integer.parseInt(String.valueOf(map.get("JTKXYLTJ_"))));
                        staticTests.add(staticTest);
                    }
                    staticTestService.saveBatch(staticTests);
                } else if (tableName.equals("z_y_BiaoGuan")) {
                    //同步标贯信息
                    List<StandardPenetration> standardPenetrations = new ArrayList<>();
                    for (Map map : tableData) {
                        StandardPenetration standardPenetration = new StandardPenetration();
                        standardPenetration.setItemId(itemId);
                        standardPenetration.setHoleCode(map.get("ZKBH") == null ? null : (String) map.get("ZKBH"));
                        standardPenetration.setDepth(map.get("BGDSD") == null ? null : new BigDecimal((String) map.get("BGDSD")).doubleValue());
                        standardPenetration.setBglx(map.get("BGLX") == null ? null : Integer.parseInt(String.valueOf(map.get("BGLX"))));
                        standardPenetration.setBgtzz(map.get("BGTZZ") == null ? null : Integer.parseInt(String.valueOf(map.get("BGTZZ"))));
                        standardPenetration.setLength(map.get("BGGC") == null ? null : new BigDecimal((String) map.get("BGGC")).doubleValue());
                        standardPenetration.setBgyzcd(map.get("BGYZCD") == null ? null : new BigDecimal((String) map.get("BGYZCD")).doubleValue());
                        standardPenetration.setBgyzjs(map.get("BGYZJS") == null ? null : new BigDecimal((String) map.get("BGYZJS")).doubleValue());
                        standardPenetration.setBgjs(map.get("BGJS") == null ? null : new BigDecimal((String) map.get("BGJS")).doubleValue());
                        standardPenetration.setBgxs(map.get("BGXS") == null ? null : new BigDecimal((String) map.get("BGXS")).doubleValue());
                        standardPenetration.setBgxzjs(map.get("BGXZJS") == null ? null : new BigDecimal((String) map.get("BGXZJS")).doubleValue());
                        standardPenetration.setBgsxz(map.get("BGSXZ") == null ? null : Integer.parseInt(String.valueOf(map.get("BGSXZ"))));
                        standardPenetration.setCy(map.get("CY") == null ? null : Integer.parseInt(String.valueOf(map.get("CY"))));
                        standardPenetration.setBgxjsx(map.get("BGXJS_") == null ? null : Integer.parseInt(String.valueOf(map.get("BGXJS_"))));
                        standardPenetration.setBgxjstjx(map.get("BGXJSTJ_") == null ? null : Integer.parseInt(String.valueOf(map.get("BGXJSTJ_"))));
                        standardPenetration.setBgjsx(map.get("BGJS_") == null ? null : Integer.parseInt(String.valueOf(map.get("BGJS_"))));
                        standardPenetration.setBgjstjx(map.get("BGJSTJ_") == null ? null : Integer.parseInt(String.valueOf(map.get("BGJSTJ_"))));
                        standardPenetrations.add(standardPenetration);
                    }
                    standardPenetrationService.saveBatch(standardPenetrations);
                } else if (tableName.equals("z_g_TuCeng")) {
                    //同步土层信息
                    List<Standard> standards = new ArrayList<>();
                    for (Map map : tableData) {
                        Standard standard = new Standard();
                        standard.setItemId(itemId);
                        standard.setHoleCode(map.get("ZKBH") == null ? null : (String) map.get("ZKBH"));
                        standard.setDepth(map.get("TCCDSD") == null ? null : new BigDecimal((String) map.get("TCCDSD")).doubleValue());
                        standard.setTcxh(map.get("TCXH") == null ? null : Integer.parseInt(String.valueOf(map.get("TCXH"))));
                        standard.setMainCode(map.get("TCZCBH") == null ? null : (String) map.get("TCZCBH"));
                        standard.setSecondaryCode(map.get("TCYCBH") == null ? null : (String) map.get("TCYCBH"));
                        standard.setThirdCode(map.get("TCCYCBH") == null ? null : (String) map.get("TCCYCBH"));
                        standard.setTcdzsd(map.get("TCDZSD") == null ? null : (String) map.get("TCDZSD"));
                        standard.setTcdzcy(map.get("TCDZCY") == null ? null : (String) map.get("TCDZCY"));
                        standard.setTcdchd(map.get("TCDCHD") == null ? null : new BigDecimal((String) map.get("TCDCHD")).doubleValue());
                        standard.setTchd(map.get("TCHD") == null ? null : new BigDecimal((String) map.get("TCHD")).doubleValue());
                        standard.setType(map.get("TCLM") == null ? null : (String) map.get("TCLM"));
                        standard.setName(map.get("TCMC") == null ? null : (String) map.get("TCMC"));
                        standard.setTcmdh(map.get("TCMDH") == null ? null : (String) map.get("TCMDH"));
                        standard.setTcymc(map.get("TCYMC") == null ? null : (String) map.get("TCYMC"));
                        standard.setTcys(map.get("TCYS") == null ? null : (String) map.get("TCYS"));
                        standard.setTcmsd(map.get("TCMSD") == null ? null : (String) map.get("TCMSD"));
                        standard.setTcsid(map.get("TCSID") == null ? null : (String) map.get("TCSID"));
                        standard.setTcksx(map.get("TCKSX") == null ? null : (String) map.get("TCKSX"));
                        standard.setTchyd(map.get("TCHYD") == null ? null : (String) map.get("TCHYD"));
                        standard.setTcjyx(map.get("TCJYX") == null ? null : (String) map.get("TCJYX"));
                        standard.setTcfhcd(map.get("TCFHCD") == null ? null : (String) map.get("TCFHCD"));
                        standard.setTcysqx(map.get("TCYSQX") == null ? null : new BigDecimal((String) map.get("TCYSQX")).doubleValue());
                        standard.setTcysqj(map.get("TCYSQJ") == null ? null : new BigDecimal((String) map.get("TCYSQJ")).doubleValue());
                        standard.setTckwcf(map.get("TCKWCF") == null ? null : (String) map.get("TCKWCF"));
                        standard.setTcjggz(map.get("TCJGGZ") == null ? null : (String) map.get("TCJGGZ"));
                        standard.setTcbhw(map.get("TCBHW") == null ? null : (String) map.get("TCBHW"));
                        standard.setTcqw(map.get("TCQW") == null ? null : (String) map.get("TCQW"));
                        standard.setTcms(map.get("TCMS") == null ? null : (String) map.get("TCMS"));
                        standard.setTcztx(map.get("TCZTX") == null ? null : (String) map.get("TCZTX"));
                        standard.setTcjycd(map.get("TCJYCD") == null ? null : (String) map.get("TCJYCD"));
                        standard.setTcpl(map.get("TCPL") == null ? null : (String) map.get("TCPL"));
                        standard.setTcjlfy(map.get("TCJLFY") == null ? null : (String) map.get("TCJLFY"));
                        standard.setTcdcms(map.get("TCDCMS") == null ? null : (String) map.get("TCDCMS"));
                        standard.setTcjljj(map.get("TCJLJJ") == null ? null : new BigDecimal((String) map.get("TCJLJJ")).doubleValue());
                        standard.setTcpxjd(map.get("TCPXJD") == null ? null : new BigDecimal((String) map.get("TCPXJD")).doubleValue());
                        standard.setTcqttz(map.get("TCQTTZ") == null ? null : (String) map.get("TCQTTZ"));
                        standard.setTczrzd(map.get("TCZRZD") == null ? null : new BigDecimal((String) map.get("TCZRZD")).doubleValue());
                        standard.setTcnjl(map.get("TCNJL") == null ? null : new BigDecimal((String) map.get("TCNJL")).doubleValue());
                        standard.setTcnmcj(map.get("TCNMCJ") == null ? null : new BigDecimal((String) map.get("TCNMCJ")).doubleValue());
                        standard.setTcczl(map.get("TCCZL") == null ? null : new BigDecimal((String) map.get("TCCZL")).doubleValue());
                        standard.setTcysml(map.get("TCYSML") == null ? null : new BigDecimal((String) map.get("TCYSML")).doubleValue());
                        standard.setTcyzzCzltzz(map.get("TCYZZ_CZLTZZ") == null ? null : new BigDecimal((String) map.get("TCYZZ_CZLTZZ")).doubleValue());
                        standard.setTcyzzDzltzz(map.get("TCYZZ_DZLTZZ") == null ? null : new BigDecimal((String) map.get("TCYZZ_DZLTZZ")).doubleValue());
                        standard.setTcyzzCzlbzz(map.get("TCYZZ_CZLBZZ") == null ? null : new BigDecimal((String) map.get("TCYZZ_CZLBZZ")).doubleValue());
                        standard.setTcyzzDzlbzz(map.get("TCYZZ_DZLBZZ") == null ? null : new BigDecimal((String) map.get("TCYZZ_DZLBZZ")).doubleValue());
                        standard.setTcckzCzltzz(map.get("TCCKZ_CZLTZZ") == null ? null : new BigDecimal((String) map.get("TCCKZ_CZLTZZ")).doubleValue());
                        standard.setTcckzDzltzz(map.get("TCCKZ_DZLTZZ") == null ? null : new BigDecimal((String) map.get("TCCKZ_DZLTZZ")).doubleValue());
                        standard.setTcckzCzlbzz(map.get("TCCKZ_CZLBZZ") == null ? null : new BigDecimal((String) map.get("TCCKZ_CZLBZZ")).doubleValue());
                        standard.setTcckzDzlbzz(map.get("TCCKZ_DZLBZZ") == null ? null : new BigDecimal((String) map.get("TCCKZ_DZLBZZ")).doubleValue());
                        standard.setTcuserdefine1(map.get("TCUSERDEFINE1") == null ? null : (String) map.get("TCUSERDEFINE1"));
                        standard.setTcuserdefine2(map.get("TCUSERDEFINE2") == null ? null : (String) map.get("TCUSERDEFINE2"));
                        standard.setTcuserdefine3(map.get("TCUSERDEFINE3") == null ? null : (String) map.get("TCUSERDEFINE3"));
                        standards.add(standard);
                    }
                    standardService.saveBatch(standards);
                } else if (tableName.equals("z_g_ShuiWei")) {
                    //同步水位信息
                    List<WaterLevel> waterLevels = new ArrayList<>();
                    for (Map map : tableData) {
                        WaterLevel waterLevel = new WaterLevel();
                        waterLevel.setItemId(itemId);
                        waterLevel.setHoleCode(map.get("ZKBH") == null ? null : (String) map.get("ZKBH"));
                        waterLevel.setDepth(map.get("SWSD") == null ? null : new BigDecimal((String) map.get("SWSD")).doubleValue());
                        waterLevel.setSwlx(map.get("SWLX") == null ? null : Integer.parseInt(String.valueOf(map.get("SWLX"))));
                        waterLevel.setSwch(map.get("SWCH") == null ? null : Integer.parseInt(String.valueOf(map.get("SWCH"))));
                        try{
                            waterLevel.setSwcsrq(map.get("SWCSRQ") == null ? null : this.timeConversion(String.valueOf(map.get("SWCSRQ"))));
                        }catch (ParseException e){
                            waterLevel.setSwcsrq(map.get("SWCSRQ") == null ? null : this.timeConversion(String.valueOf(map.get("SWCSRQ"))));
                        }
                        waterLevel.setSwdxsw(map.get("SWDXSW") == null ? null : new BigDecimal((String) map.get("SWDXSW")).doubleValue());
                        waterLevel.setSwfw(map.get("SWFW") == null ? null : (String) map.get("SWFW"));
                        waterLevel.setSwxz(map.get("SWXZ") == null ? null : Integer.parseInt(String.valueOf(map.get("SWXZ"))));
                        waterLevel.setCy(map.get("CY") == null ? null : Integer.parseInt(String.valueOf(map.get("CY"))));
                        waterLevels.add(waterLevel);
                    }
                    waterLevelService.saveBatch(waterLevels);
                } else if (tableName.equals("p_PouXian")) {
                    //同步剖线信息
                    List<Line> lines = new ArrayList<>();
                    for (Map map : tableData) {
                        Line line = new Line();
                        line.setItemId(itemId);
                        line.setCode(map.get("PXBH") == null ? null : (String) map.get("PXBH"));
                        line.setHoleCodes(map.get("PXKH") == null ? null : (String) map.get("PXKH"));
                        line.setPxkj(map.get("PXKJ") == null ? null : new BigDecimal((String) map.get("PXKJ")).doubleValue());
                        line.setPxfwj(map.get("PXFWJ") == null ? null : new BigDecimal((String) map.get("PXFWJ")).doubleValue());
                        line.setPxshx(map.get("PXSHX") == null ? null : Integer.parseInt(String.valueOf(map.get("PXSHX"))));
                        line.setPxqfx(map.get("PXQFX") == null ? null : (String) map.get("PXQFX"));
                        line.setPxzfx(map.get("PXZFX") == null ? null : (String) map.get("PXZFX"));
                        line.setPxsc(map.get("PXSC") == null ? null : Integer.parseInt(String.valueOf(map.get("PXSC"))));
                        line.setYsgcbh(map.get("YSGCBH") == null ? null : (String) map.get("YSGCBH"));
                        line.setPxqsdx(map.get("PXQSDX") == null ? null : new BigDecimal((String) map.get("PXQSDX")).doubleValue());
                        line.setPxqsdy(map.get("PXQSDY") == null ? null : new BigDecimal((String) map.get("PXQSDY")).doubleValue());
                        line.setPxzzdx(map.get("PXZZDX") == null ? null : new BigDecimal((String) map.get("PXZZDX")).doubleValue());
                        line.setPxzzdy(map.get("PXZZDY") == null ? null : new BigDecimal((String) map.get("PXZZDY")).doubleValue());
                        line.setPxqsdjl(map.get("PXQSDJL") == null ? null : new BigDecimal((String) map.get("PXQSDJL")).doubleValue());
                        line.setPxqsdjd(map.get("PXQSDJD") == null ? null : new BigDecimal((String) map.get("PXQSDJD")).doubleValue());
                        line.setPxzzdjl(map.get("PXZZDJL") == null ? null : new BigDecimal((String) map.get("PXZZDJL")).doubleValue());
                        line.setPxzzdjd(map.get("PXZZDJD") == null ? null : new BigDecimal((String) map.get("PXZZDJD")).doubleValue());
                        line.setZkh(map.get("ZKH") == null ? null : Integer.parseInt(String.valueOf(map.get("ZKH"))));
                        line.setZkv(map.get("ZKV") == null ? null : Integer.parseInt(String.valueOf(map.get("ZKV"))));
                        lines.add(line);
                    }
                    lineService.saveBatch(lines);
                } else if (tableName.equals("g_STuCengGC")) {
                    //同步图层标准信息
                    List<StandardFormation> standardFormations = new ArrayList<>();
                    for (Map map : tableData) {
                        StandardFormation standardFormation = new StandardFormation();
                        standardFormation.setItemId(itemId);
                        standardFormation.setMainCode(map.get("TCZCBH") == null ? null : (String) map.get("TCZCBH"));
                        standardFormation.setSecondaryCode(map.get("TCYCBH") == null ? null : (String) map.get("TCYCBH"));
                        standardFormation.setThirdCode(map.get("TCCYCBH") == null ? null : (String) map.get("TCCYCBH"));
                        standardFormation.setTcdzsd(map.get("TCDZSD") == null ? null : (String) map.get("TCDZSD"));
                        standardFormation.setTcdzcy(map.get("TCDZCY") == null ? null : (String) map.get("TCDZCY"));
                        standardFormation.setType(map.get("TCLM") == null ? null : (String) map.get("TCLM"));
                        standardFormation.setName(map.get("TCMC") == null ? null : (String) map.get("TCMC"));
                        standardFormation.setTcymc(map.get("TCYMC") == null ? null : (String) map.get("TCYMC"));
                        standardFormation.setTcys(map.get("TCYS") == null ? null : (String) map.get("TCYS"));
                        standardFormation.setTcmsd(map.get("TCMSD") == null ? null : (String) map.get("TCMSD"));
                        standardFormation.setTcsid(map.get("TCSID") == null ? null : (String) map.get("TCSID"));
                        standardFormation.setTcksx(map.get("TCKSX") == null ? null : (String) map.get("TCKSX"));
                        standardFormation.setTchyd(map.get("TCHYD") == null ? null : (String) map.get("TCHYD"));
                        standardFormation.setTcjyx(map.get("TCJYX") == null ? null : (String) map.get("TCJYX"));
                        standardFormation.setTcfhcd(map.get("TCFHCD") == null ? null : (String) map.get("TCFHCD"));
                        standardFormation.setTcysqx(map.get("TCYSQX") == null ? null : new BigDecimal((String) map.get("TCYSQX")).doubleValue());
                        standardFormation.setTcysqj(map.get("TCYSQJ") == null ? null : new BigDecimal((String) map.get("TCYSQJ")).doubleValue());
                        standardFormation.setTckwcf(map.get("TCKWCF") == null ? null : (String) map.get("TCKWCF"));
                        standardFormation.setTcjggz(map.get("TCJGGZ") == null ? null : (String) map.get("TCJGGZ"));
                        standardFormation.setTcbhw(map.get("TCBHW") == null ? null : (String) map.get("TCBHW"));
                        standardFormation.setTcqw(map.get("TCQW") == null ? null : (String) map.get("TCQW"));
                        standardFormation.setTcms(map.get("TCMS") == null ? null : (String) map.get("TCMS"));
                        standardFormation.setTcztx(map.get("TCZTX") == null ? null : (String) map.get("TCZTX"));
                        standardFormation.setTcjycd(map.get("TCJYCD") == null ? null : (String) map.get("TCJYCD"));
                        standardFormation.setTcpl(map.get("TCPL") == null ? null : (String) map.get("TCPL"));
                        standardFormation.setTcjlfy(map.get("TCJLFY") == null ? null : (String) map.get("TCJLFY"));
                        standardFormation.setTcjljj(map.get("TCJLJJ") == null ? null : new BigDecimal((String) map.get("TCJLJJ")).doubleValue());
                        standardFormation.setTcpxjd(map.get("TCPXJD") == null ? null : new BigDecimal((String) map.get("TCPXJD")).doubleValue());
                        standardFormation.setTcqttz(map.get("TCQTTZ") == null ? null : (String) map.get("TCQTTZ"));
                        standardFormations.add(standardFormation);
                    }
                    standardFormationService.saveBatch(standardFormations);
                } else if (tableName.equals("z_c_quyang")) {
                    //同步试样取样信息
                    List<Sample> samples = new ArrayList<>();
                    for (Map map : tableData) {
                        Sample sample = new Sample();
                        sample.setItemId(itemId);
                        sample.setHoleCode(map.get("ZKBH") == null ? null : (String) map.get("ZKBH"));
                        sample.setQybh(map.get("QYBH") == null ? null : (String) map.get("QYBH"));
                        sample.setQysd(map.get("QYSD") == null ? null : new BigDecimal((String) map.get("QYSD")).floatValue());
                        sample.setQyhd(map.get("QYHD") == null ? null : new BigDecimal((String) map.get("QYSD")).floatValue());
                        sample.setQydc(map.get("QYDC") == null ? null : (String) map.get("QYDC"));
                        sample.setQylx(map.get("QYLX") == null ? null : Integer.parseInt(String.valueOf(map.get("QYLX"))));
                        sample.setQyzlmd(map.get("QYZLMD") == null ? null : new BigDecimal((String) map.get("QYZLMD")).floatValue());
                        sample.setQybz(map.get("QYBZ") == null ? null : new BigDecimal((String) map.get("QYBZ")).floatValue());
                        sample.setQyhsl(map.get("QYHSL") == null ? null : new BigDecimal((String) map.get("QYHSL")).floatValue());
                        sample.setQyyx(map.get("QYYX") == null ? null : new BigDecimal((String) map.get("QYYX")).floatValue());
                        sample.setQysx(map.get("QYSY") == null ? null : new BigDecimal((String) map.get("QYSY")).floatValue());
                        samples.add(sample);
                    }
                    sampleService.saveBatch(samples);
                }
            }
        }
        //状态设置为同步完成
        item.setSynchronousState(0);
        itemService.updateById(item);
    }

    /**
     * 时间格式转换
     */
    private Date timeConversion(String time) throws ParseException {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format2 = new SimpleDateFormat("yyyy.MM.dd");
        if(time.contains(".")){
            return format2.parse(time);
        }else{
            return format1.parse(time);
        }
    }

    /**
     * @param : * @param itemParam :
     * @return : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @throws :
     * @Description ：在地图上展示所有工程信息(+关注项)
     * @methodName : getItemOnTheMap
     * @author : 张童
     */
    @Override
    public List<ItemResult> getItemOnTheMapAddGZ(ItemParam itemParam) {
        if (itemParam.getItemTypes() != null && !itemParam.getItemTypes().equals("")) {
            String s1 = itemParam.getItemTypes().substring(0, itemParam.getItemTypes().length() - 1);
            String[] split = s1.split(",");
            itemParam.setTypeArray(split);
        }
        if (itemParam.getItemPlans() != null && !itemParam.getItemPlans().equals("")) {
            String s2 = itemParam.getItemPlans().substring(0, itemParam.getItemPlans().length() - 1);
            String[] split1 = s2.split(",");
            itemParam.setPlanArray(split1);
        }
        List<ItemResult> itemOnTheMap = this.baseMapper.getItemOnTheMapAddGZ(itemParam);
        return itemOnTheMap;
    }

    private Serializable getKey(ItemParam param) {
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Item getOldEntity(ItemParam param) {
        return this.getById(getKey(param));
    }

    private Item getEntity(ItemParam param) {
        Item entity = new Item();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    /**
     * @param : * @param itemParam :
     * @return : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @throws :
     * @Description ：在地图上展示所有工程信息
     * @methodName : getItemOnTheMap
     * @author : 张童
     */
    @Override
    public List<ItemResult> getItemOnTheMap(ItemParam itemParam) {
        if (itemParam.getItemTypes() != null && !itemParam.getItemTypes().equals("")) {
            String s1 = itemParam.getItemTypes().substring(0, itemParam.getItemTypes().length() - 1);
            String[] split = s1.split(",");
            itemParam.setTypeArray(split);
        }
        if (itemParam.getItemPlans() != null && !itemParam.getItemPlans().equals("")) {
            String s2 = itemParam.getItemPlans().substring(0, itemParam.getItemPlans().length() - 1);
            String[] split1 = s2.split(",");
            itemParam.setPlanArray(split1);
        }
        List<ItemResult> itemOnTheMap = this.baseMapper.getItemOnTheMap(itemParam);
        return itemOnTheMap;
    }

    /**
     * 查看详情接口
     *
     * @author zt
     * @Date 2020-09-23
     */
    @Override
    public ItemResult getItemDetail(Long id) {
        return this.baseMapper.getItemDetail(id);
    }

    /**
     * @return : cn.stylefeng.roses.kernel.model.response.ResponseData
     * @throws :
     * @Description ：首页工程echarts图
     * @methodName : itemECharts
     * @author : 张童
     */
    @Override
    public List<Map<String, String>> itemECharts() {

        //四个分类：勘察工程；测量工程；岩土设计；其他工程。（先写死吧，首页展示没发动态啊）
        List<Map<String, String>> result = new ArrayList<>();
        //勘察工程
        String count1 = this.baseMapper.getCountByType(2L);
        Map<String, String> a1 = new HashMap<>();
        a1.put("name", "勘察工程");
        a1.put("value", count1);
        result.add(a1);
        //测量工程
        String count2 = this.baseMapper.getCountByType(4L);
        Map<String, String> a2 = new HashMap<>();
        a2.put("name", "测量工程");
        a2.put("value", count2);
        result.add(a2);
        //岩土设计
        String count3 = this.baseMapper.getCountByType(3L);
        String count4 = this.baseMapper.getCountByType(5L);
        Integer integer = Integer.parseInt(count3) + Integer.parseInt(count4);
        Map<String, String> a3 = new HashMap<>();
        a3.put("name", "岩土设计");
        a3.put("value", integer.toString());
        result.add(a3);
        //其他工程
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 0);
        Integer count5 = this.baseMapper.selectCount(queryWrapper);
        count5 = count5 - integer - Integer.parseInt(count1) + Integer.parseInt(count2);
        Map<String, String> a = new HashMap<>();
        a.put("name", "其他工程");
        a.put("value", count5.toString());
        result.add(a);
        return result;
    }
}
