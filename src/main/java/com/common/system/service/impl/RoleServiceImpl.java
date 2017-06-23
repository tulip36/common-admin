package com.common.system.service.impl;

import com.common.system.entity.RcPermission;
import com.common.system.entity.RcRelation;
import com.common.system.entity.RcRole;
import com.common.system.entity.RcRoleExample;
import com.common.system.mapper.RcRoleMapper;
import com.common.system.service.PermissionService;
import com.common.system.service.RelationService;
import com.common.system.service.RoleService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/22.
 * Time:14:17
 * ProjectName:Common-admin
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RcRoleMapper roleMapper;
    @Autowired
    private RelationService relationService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public PageInfo<RcRole> listForPage(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<RcRole> roleList = roleMapper.getRoleList();
        return new PageInfo<>(roleList);
    }

    @Override
    public int deleteById(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RcRole selectByRoleName(String roleName) {
        RcRoleExample roleExample = new RcRoleExample();
        RcRoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andNameEqualTo(roleName);
        List<RcRole> resultData = roleMapper.selectByExample(roleExample);
        if (resultData.size() < 1) {
            return null;
        }
        return resultData.get(0);
    }
    @Override
    public RcRole selectByRoleValue(String roleValue) {
        RcRoleExample roleExample = new RcRoleExample();
        RcRoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andValueEqualTo(roleValue);
        List<RcRole> resultData = roleMapper.selectByExample(roleExample);
        if (resultData.size() < 1) {
            return null;
        }
        return resultData.get(0);
    }
    @Override
    public Result<Integer> save(RcRole role, List<Integer> permissionIds) {
        Result<Integer> result;
        roleMapper.insert(role);
        role = selectByRoleName(role.getName());
        result = relationService.save(role.getId(),permissionIds);
        return result;
    }

    @Override
    public Result<RcRole> selectById(Integer id) {
        Result<RcRole> result = new Result<>();
        RcRole role = roleMapper.selectByPrimaryKey(id);
        if (role == null){
            result.setStatus(false);
            result.setCode(MsgCode.FAILED);
            result.setMsg("没有该角色");
            return result;
        }
        result.setData(role);
        List<RcRelation> relationList = relationService.getByRoleId(id);
        if (relationList == null || relationList.size()==0){
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            return result;
        }
        List<Integer> permissionIds = new ArrayList<>();
        for (RcRelation r:relationList
             ) {
            permissionIds.add(r.getPermissionid());
        }
        List<RcPermission> pList = permissionService.getPermissions(permissionIds);
        role.setPermissionList(pList);
        return result ;
    }
}
