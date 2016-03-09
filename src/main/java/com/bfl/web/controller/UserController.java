package com.bfl.web.controller;

/**
 * Created by apple on 16/3/2.
 *
 */

import javax.servlet.http.HttpServletRequest;
import com.bfl.kernel.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {
    //@Resource(name="userService")
    @Autowired
    private IUserService userService;

    @RequestMapping(value="/userInfo/{id}", method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request, Model model,@PathVariable("id") String id) {
        if(StringUtils.isEmpty(id)){
            throw new IllegalArgumentException("id不能为空");
        }
        int userId = Integer.parseInt(id);
        String name = this.userService.getUserById(userId);
        model.addAttribute("user", name);
        return "user";
    }




}