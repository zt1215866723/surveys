package com.lfxwkj.sur.sys.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.node.TreeviewNode;
import com.lfxwkj.sur.base.pojo.node.ZTreeNode;
import com.lfxwkj.sur.sys.modular.system.entity.Dept;
import com.lfxwkj.sur.sys.modular.system.model.CollegeDto;
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
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree(@Param("deptId") String deptId);

    /**
     * 获取layui树形节点
     */
    List<LayuiTreeNode> layuiTree(String deptId);

    /**
     * 获取所有部门列表
     */
    Page<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition, @Param("deptId") Long deptId);

    /**
     * 获取所有部门树列表
     */
    List<TreeviewNode> treeviewNodes();

    /**
     * where pids like ''
     */
    List<Dept> likePids(@Param("deptId") Long deptId);

    /**
     * 获取牧场的列表
     *
     * @return
     */
    List<CollegeDto> collegeList(@Param("deptId") String deptId);

    /**
     * 获取牧场的列表
     *
     * @return
     */
    List<CollegeDto> collegeAndChildList(@Param("deptId") String deptId);


}
