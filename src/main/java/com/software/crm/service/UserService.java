package com.software.crm.service;

import com.software.crm.base.BaseService;
import com.software.crm.bean.User;
import com.software.crm.bean.UserRole;
import com.software.crm.mapper.UserMapper;
import com.software.crm.mapper.UserRoleMapper;
import com.software.crm.model.UserModel;
import com.software.crm.utils.AssertUtil;
import com.software.crm.utils.Md5Util;
import com.software.crm.utils.PhoneUtil;
import com.software.crm.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends BaseService<User, Integer> {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * @return :
     * @author :wyanjia
     * @parm : 用户登录
     * @date : 2021/9/29 0:53
     */
    public UserModel userLogin(String username, String password) {
        // 判断是否为空
        CheckLogin(username, password);
        // 查询用户 返回用户对象
        User user = userMapper.queryUserByName(username);
        // 判断用户对象是否为空
        AssertUtil.isTrue(user == null, "用户姓名不存在");
        //判断密码是否正确 比较客户端密码和 数据库中的密码
        CheckPwd(password, user.getUserPwd());
        return buildUserInfo(user);
    }

    private void CheckPwd(String password, String userPwd) {
        // 将客户端传递密码 加密
        password = Md5Util.encode(password);
        // 判断密码是否相等
        AssertUtil.isTrue(!password.equals(userPwd), "密码不正确");
    }

    private void CheckLogin(String username, String password) {
        AssertUtil.isTrue(StringUtils.isBlank(username), "用户姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(password), "用户密码不能为空");
    }


    /**
     * 构建返回的用户信息
     *
     * @param user
     * @return
     */
    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        // 设置用户信息
        //加密 userModel
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUsername(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 修改密码
     * @date : 2021/9/30 19:42
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassword(Integer userId, String oldPwd, String newPwd, String repeatPwd) {
        // 获得用户对象
        User user = userMapper.selectByPrimaryKey(userId);
        // 判断用户对象是否存在
        AssertUtil.isTrue(null == user, "待更新记录不存在");
        // 参数校验
        checkPassWordParms(user, oldPwd, newPwd, repeatPwd);
        // 设置新密码
        user.setUserPwd(Md5Util.encode(newPwd));
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1, "修改密码失败");
    }

    private void checkPassWordParms(User user, String oldPwd, String newPwd, String repeatPwd) {
        // 原始密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd), "原始密码不能为空");
        // 判断原始密码是否正确 （查询对象中的密码 是否和原始密码相同)
        AssertUtil.isTrue(!Md5Util.encode(oldPwd).equals(user.getUserPwd()), "原始密码不正确");
        //  判断新密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(newPwd), "新密码不能为空");
        // 判断新密码是否与原始密码一致 (不允许新密码同原始密码相同)
        AssertUtil.isTrue(oldPwd.equals(newPwd), "新密码不能与原始密码相同");
        //确认密码不能为空
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd), "确认密码不能为空");
        // 判断确认密码是否相同
        AssertUtil.isTrue(!repeatPwd.equals(newPwd), "确认密码与新密码不一致");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 查询所有的销售人员
     * @date : 2021/10/8 21:12
     */
    public List<Map<String, Object>> queryAllSales() {
        return userMapper.queryAllSaless();
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 添加用户
     * @date : 2021/10/11 10:48
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        // 1. 参数校验 必填项不能为空   userName   非空 唯一性 邮箱 email  非空 手机号 非空格式正确
        checkUserParams(user.getUserName(), user.getEmail(), user.getPhone(), null);
        // 2. 设置参数默认值 isValid  = 1  创建时间 系统当前时间 更新时间 系统当前时间  设置默认密码 123456  MD5
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setUserPwd(Md5Util.encode("123456"));
        //3. 执行添加操作
        AssertUtil.isTrue(userMapper.insertSelective(user) != 1, "用户添加失败");
        // 4. 用户角色关联
        /**
         * @author  :wyanjia
         * @parm    : 用户ID     用户对象 角色id 传递过来的参数
         * @date    : 2021/10/12 12:19
         * @return  :
         * */
        relationUserRole(user.getId(), user.getRoleIds());
    }

    private void relationUserRole(Integer userId, String roleIds) {
        /**
         * @author  :wyanjia
         * @parm    : 用户角色关联
         * @date    : 2021/10/11 17:07
         * @return  :
         * 添加操作   ==> 添加新的用户
         * 原始角色不存在
         *  1. 不添加新的角色  ， 不操作中间表
         *  2. 添加新的角色记录 给指定用户 绑定相关的角色记录
         * 更新操作
         *   原始角色不存在
         *       1. 不添加新的角色记录 不操作用户角色表
         *       2.  添加新的角色记录  给指定用户绑定相关的角色记录
         *    原始角色存在
         *    1. 添加新的角色记录      判断已经存在的角色信息不添加 添加没有的角色记录
         *    2. 清空所有的角色记录    删除用户绑定的角色记录
         *    3. 移除部分角色记录     删除不存在的角色记录  存在的角色记录保留
         *    4. 移除部分角色       添加新的角色记录 删除不存在的角色记录 存在的角色记录保留 添加新的角色记录
         *    如何进行角色分配？
         *       先判断角色记录是否存在
         *      将用户原有的角色记录删除 ，添加新的角色记录
         *    删除 操作
         *    删除既定用户绑定的角色记录
         * */

        // 1. 通过用户ID 查询角色记录
        Integer count = userRoleMapper.countUserRoleByUserId(userId);
        //  判断角色记录是否存在
        if (count > 0) {// 角色记录 存在 进行删除
            AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId) != count, "用户角色记录删除失败");
        }
        //  判断角色iD 是否存在
        if (StringUtils.isNotBlank(roleIds)) {
            // 执行一个批量添加
            List<UserRole> userRoleList = new ArrayList<>();
            //将角色id 字符串转换成数组进行遍历
            String[] roleIdSArray = roleIds.split(",");
            // 遍历数组
            for (String roleId : roleIdSArray) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(Integer.parseInt(roleId));
                userRole.setUpdateDate(new Date());
                userRole.setUserId(userId);
                userRole.setCreateDate(new Date());
                userRoleList.add(userRole);
            }
            // 批量添加
            AssertUtil.isTrue(userRoleMapper.insertBatch(userRoleList) != userRoleList.size(), "用户角色添加失败");
        }

    }

    private void checkUserParams(String userName, String email, String phone, Integer id) {
        //  判断用户名是否为空
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空");
        // 判断用户名唯一性
        User temp = userMapper.queryUserByName(userName);
        // 如果用户对象为空 则表示用户对象可用 如果不为空则表示不可用
        // 如果是添加操作 数据库中无数据 只要通过名称查到数据 则表示用户名被占用
        // 如果是修改操作 数据库中有对应的记录 通过用户名查到数据 可能是当前记录 也可能是其他记录
        // 如果用户名存在 且与当前修改记录不是同一个， 则表示其他记录占用了该名称， 该名称不可用
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(id)), "用户名已经存在");

        // 判断邮箱
        AssertUtil.isTrue(StringUtils.isBlank(email), "邮箱不能为空");
        // 手机号非空
        AssertUtil.isTrue(StringUtils.isBlank(phone), "手机号不能为空");
        // 手机号格式判断
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号格式不正确");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        //1. 参数校验
        AssertUtil.isTrue(null == user.getId(), "待更新记录不存在");
        // 通过id 查询数据
        // 如果 是添加操作 则数据库中无数据 只要用过名称查到数据
        User temp = userMapper.selectByPrimaryKey(user.getId());
        AssertUtil.isTrue(temp == null, "待更新记录不存在");
        checkUserParams(user.getUserName(), user.getEmail(), user.getPhone(), user.getId());
        // 2. 设置默认值
        user.setUpdateDate(new Date());
        // 3. 执行更新操作
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) != 1, "更新失败");
        // 4. 更新用户角色关联
        relationUserRole(user.getId(), user.getRoleIds());
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 用户删除
     * @date : 2021/10/11 14:32
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByIds(Integer[] ids) {
        AssertUtil.isTrue(ids == null || ids.length == 0, "待删除记录不存在");
        //执行删除操作
        AssertUtil.isTrue(userMapper.deleteBatch(ids) != ids.length, "用户删除失败");
        for (Integer userId : ids) {
            Integer count = userRoleMapper.countUserRoleByUserId(userId);
            if (count > 0) {
                AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId) != count, "用户关联失败");
            }
        }
    }


    /**
     * @return :
     * @author :wyanjia
     * @parm : 查询所有的客户经理
     * @date : 2021/10/19 12:41
     */
    public List<Map<String, Object>> queryAllCustomerManager() {
         return userMapper.queryAllCustomerManager();
    }
}