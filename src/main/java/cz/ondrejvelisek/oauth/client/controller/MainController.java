package cz.ondrejvelisek.oauth.client.controller;

import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Controller
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    private RestOperations perun;

    @RequestMapping(value = "/")
    public String github(Model model, HttpServletRequest request) throws Exception {

        System.out.println("request.getRequestURI(): "+request.getRequestURI() +"?"+ request.getQueryString());
        System.out.println("params: "+request.getParameterMap());

        ObjectNode result = perun.getForObject("https://perun.elixir-czech.cz/fed/rpc/json/authzResolver/getPerunPrincipal", ObjectNode.class);

        System.out.println("request.getRequestURI(): "+request.getRequestURI());

        model.addAttribute("response", result.toString());

        return "index";
    }


}
