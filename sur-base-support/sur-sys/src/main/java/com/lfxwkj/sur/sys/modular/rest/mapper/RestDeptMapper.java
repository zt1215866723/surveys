package com.lfxwkj.sur.sys.modular.rest.mapper;

import com.lfxwkj.sur.base.pojo.node.TreeviewNode;
import com.lfxwkj.sur.base.pojo.node.ZTreeNode;
import com.lfxwkj.sur.sys.modular.rest.entity.RestDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
public interface RestDeptMapper extends BaseMapper<RestDept> {

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();

    /**
     * 获取所有部门列表
     */
    Page<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition, @Param("deptId") Long deptId);

    /**
     * 获取所有部门列表(不分页)
     */
    List<Map<String, Object>> listNotPage(@Param("condition") String condition, @Param("deptId") Long deptId);

    /**
     * 获取所有部门树列表
     */
    List<TreeviewNode> treeviewNodes();

    /**
     * where pids like ''
     */
    List<RestDept> likePids(@Param("deptId") Long deptId);

    List<RestDept> collegeList(@Param("pid")String pid, @Param("deptId")Long deptId);

}
