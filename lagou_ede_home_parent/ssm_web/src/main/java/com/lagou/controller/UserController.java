package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {

        PageInfo allUserByPage = userService.findAllUserByPage(userVo);

        return new ResponseResult(true, 200, "分页多条件查询成功", allUserByPage);
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id, String status) {

        userService.updateUserStatus(id, status);

        return new ResponseResult(true, 200, "更新用户状态成功", status);
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User user2 = userService.login(user);

        if (user2 != null) {

            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", user2.getId());

            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id", user2.getId());

            map.put("user", user2);

            return new ResponseResult(true, 1, "登录成功", map);
        } else {
            return new ResponseResult(true, 400, "账号名或密码错误", null);
        }
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id) {

        List<Role> roleList = userService.findUserRoleById(id);

        return new ResponseResult(true, 200, "根据用户id查询角色信息成功", roleList);
    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {

        userService.userContextRole(userVo);

        return new ResponseResult(true, 200, "分配角色成功", null);
    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {

        // 1.获取请求头中的token
        String header_token = request.getHeader("Authorization");

        // 2.获取session中的token信息
        String session_token = (String) request.getSession().getAttribute("access_token");

        // 3.对比
        if (header_token.equals(session_token)) {
             Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            return userService.getUserPermission(user_id);
        } else {
            return new ResponseResult(false, 400, "获取菜单信息失败", null);
        }
    }
}
