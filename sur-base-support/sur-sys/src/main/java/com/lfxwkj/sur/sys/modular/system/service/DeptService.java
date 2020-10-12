package com.lfxwkj.sur.sys.modular.system.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfxwkj.sur.base.pojo.node.LayuiTreeNode;
import com.lfxwkj.sur.base.pojo.node.ZTreeNode;
import com.lfxwkj.sur.base.pojo.page.LayuiPageFactory;
import com.lfxwkj.sur.sys.core.exception.enums.BizExceptionEnum;
import com.lfxwkj.sur.sys.modular.system.entity.Dept;
import com.lfxwkj.sur.sys.modular.system.mapper.DeptMapper;
import com.lfxwkj.sur.sys.modular.system.model.CollegeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
@Service
public class DeptService extends ServiceImpl<DeptMapper, Dept> {

    @Resource
    private DeptMapper deptMapper;

    /**
     * 新增部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDept(Dept dept) {
        if (ToolUtil.isOneEmpty(dept, dept.getSimpleName(), dept.getPid())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //完善pids,根据pid拿到pid的pids
        this.deptSetPids(dept);
        this.save(dept);
    }

    /**
     * 修改部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editDept(Dept dept) {
        if (ToolUtil.isOneEmpty(dept, dept.getDeptId(), dept.getSimpleName(), dept.getPid())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //完善pids,根据pid拿到pid的pids
        this.deptSetPids(dept);
        this.updateById(dept);
    }

    /**
     * 删除部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    @Transactional
    public void deleteDept(Long deptId) {
        Dept dept = deptMapper.selectById(deptId);
        //根据like查询删除所有级联的部门
        List<Dept> subDepts = deptMapper.likePids(dept.getDeptId());
        for (Dept temp : subDepts) {
            this.removeById(temp.getDeptId());
        }
        this.removeById(dept.getDeptId());
    }

    /**
     * 获取ztree的节点列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public List<ZTreeNode> tree(String deptId) {
        return this.baseMapper.tree(deptId);
    }

    /**
     * 获取layuiTree的节点列表
     *
     * @author fengshuonan
     * @Date 2019-8-27 15:23
     */
    public List<LayuiTreeNode> layuiTree(String deptId) {
        return this.baseMapper.layuiTree(deptId);
    }

    /**
     * 获取所有部门列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public Page<Map<String, Object>> list(String condition, Long deptId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page, condition, deptId);
    }

    /**
     * 设置部门的父级ids
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:58 PM
     */
    private void deptSetPids(Dept dept) {
        if (ToolUtil.isEmpty(dept.getPid()) || dept.getPid().equals(0L)) {
            dept.setPid(0L);
            dept.setPids("[0],");
        } else {
            Long pid = dept.getPid();
            Dept temp = this.getById(pid);
            String pids = temp.getPids();
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }

    /**
     * 获取的牧场的列表   （后台在用）
     *
     * deptId 后台用户登陆的当前的账号所在部门
     *
     * @return
     */
    public List<CollegeDto> collegeList(String deptId) {
        return this.baseMapper.collegeList(deptId);
    }

    /**
     * 获取的牧场的列表   （后台在用）
     *
     * deptId 后台用户登陆的当前的账号所在部门
     *
     * @return
     */
    public List<CollegeDto> collegeAndChildList(String deptId) {
        return this.baseMapper.collegeAndChildList(deptId);
    }

}
