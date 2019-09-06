package org.mushi.modules.controller;

import lombok.extern.slf4j.Slf4j;
import org.mushi.support.OauthConstants;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
@SessionAttributes("authorizationRequest")
public class OauthMainController {

    /**
     * 登录页面跳转
     * @param model
     * @return
     */
    @GetMapping("/auth/login")
    public String loginPage(Model model){
        model.addAttribute("loginProcessUrl", OauthConstants.LOGIN_PROCESS_URL);
        return "base-login";
    }

    /**
     * 授权页面
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/custom/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView view = new ModelAndView();
        view.setViewName("base-grant");
        view.addObject("clientId", authorizationRequest.getClientId());
        view.addObject("scopes",authorizationRequest.getScope());
        return view;
    }

}
