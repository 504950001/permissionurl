package top.zemal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import top.zemal.content.ResponseConstants;
import top.zemal.content.Responses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-29 11:25
 */
@RequestMapping("/")
@Controller
public class PageController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "sign-in";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String index() {
        return "users";
    }

    @RequestMapping(value = "role", method = RequestMethod.GET)
    public String role() {
        return "user";
    }

    @RequestMapping(value = "permission", method = RequestMethod.GET)
    public String permission() {
        return "gallery";
    }

    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public String menu() {
        return "calendar";
    }
}
