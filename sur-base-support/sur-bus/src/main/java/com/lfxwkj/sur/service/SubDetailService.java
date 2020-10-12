package com.lfxwkj.sur.service;

import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageInfo;
import com.lfxwkj.sur.entity.SubDetail;
import com.lfxwkj.sur.model.params.SubDetailParam;
import com.lfxwkj.sur.model.result.SubDetailResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfxwkj.sur.sys.modular.system.entity.Dict;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 文档的目录详情。 服务类
 * </p>
 *
 * @author 郭晓东
 * @since 2020-08-18
 */
public interface SubDetailService extends IService<SubDetail> {

    /**
     * 新增
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    void add(SubDetailParam param);

    /**
     * 删除
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    void delete(SubDetailParam param);

    /**
     * 更新
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    void update(SubDetailParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    SubDetailResult findBySpec(SubDetailParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
    List<SubDetailResult> findListBySpec(SubDetailParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 郭晓东
     * @Date 2020-08-18
     */
     LayuiPageInfo findPageBySpec(SubDetailParam param);

    /**
     * 文件上传
     * @param file
     * @param subId
     * @return
     */
    ResponseData fileUpload(MultipartFile file, Long subId) throws IOException;

    /**
     * 获取资料种类
     * @param itemType
     * @return
     */
    List<Dict> getCatas(Long itemType);

    /**
     * 获取树列表
     * @param subId
     * @return
     */
    List<LayuiTreeNode> getTree(Long subId);

}
