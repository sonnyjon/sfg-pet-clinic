package dev.sonnyjon.sfgpetclinic.controllers;

import dev.sonnyjon.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Sonny on 5/19/2022.
 */
@RequestMapping("/owners")
@Controller
public class OwnerController
{
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService)
    {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model)
    {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping("/find")
    public String findOwners()
    {
        return "not-implemented";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId)
    {
        ModelAndView mav = new ModelAndView("owners/owner-details");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
}
