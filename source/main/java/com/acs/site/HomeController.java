package com.acs.site;

import com.acs.util.Encrytion;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Controller
public class HomeController
{
    @RequestMapping("/")
    public View home(Map<String, Object> model)
    {
        model.put("userUrl", "user");
        return new RedirectView("/{userUrl}", true);
    }

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public String dashboard(Map<String, Object> model, HttpServletRequest request)
    {
        model.put("text", " "+Encrytion.getIP(request));

        return "home/ip";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(Map<String, Object> model) throws IOException {
        StandardPBEStringEncryptor encryptor = (StandardPBEStringEncryptor) Encrytion.stringEncryptor("MyPassword");
        Properties props = new EncryptableProperties(encryptor);
        props.load(this.getClass().getResourceAsStream("message.properties"));
        User user = new User();
        user.setUsername("john");
        user.setPassword(props.getProperty("password"));
        return user;
    }



}
