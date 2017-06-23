package com.common.system.service.impl;

import com.common.system.entity.RcPermission;
import com.common.system.entity.RcPermissionExample;
import com.common.system.entity.RcRoleExample;
import com.common.system.mapper.RcPermissionMapper;
import com.common.system.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/22.
 * Time:11:10
 * ProjectName:Common-admin
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RcPermissionMapper permissionMapper;

    @Override
    public List<RcPermission> getPermissions(List<Integer> idList) {
        RcPermissionExample example = new RcPermissionExample();
        RcPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        return permissionMapper.selectByExample(example);
    }

    @Override
    public PageInfo<RcPermission> listForPage(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<RcPermission> permissionList = permissionMapper.getPermissions();
        return new PageInfo<>(permissionList);
    }

    @Override
    public List<RcPermission> getPermissionsByRoleId(Integer id) {
        RcPermissionExample example = new RcPermissionExample();
        RcPermissionExample.Criteria criteria = example.createCriteria();
        return null;
    }
}
