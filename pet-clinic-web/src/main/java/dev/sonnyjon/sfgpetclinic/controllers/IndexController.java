package dev.sonnyjon.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String index()
    {
        return "index";
    }

    @RequestMapping("/oups")
    public String errorLink()
    {
        return "not-implemented";
    }
}
