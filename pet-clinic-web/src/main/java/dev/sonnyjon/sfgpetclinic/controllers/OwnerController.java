package dev.sonnyjon.sfgpetclinic.controllers;

import dev.sonnyjon.sfgpetclinic.model.Owner;
import dev.sonnyjon.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

import java.util.List;

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

    @RequestMapping("/find")
    public String findOwners(Model model)
    {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/find-owners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId)
    {
        ModelAndView mav = new ModelAndView("owners/owner-details");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String processFindForm(Owner owner, BindingResult result, Model model)
    {
        // allow parameterless GET request for /owners to return all records
        // empty string signifies no filter
        if (owner.getLastName() == null) owner.setLastName("");

        // find owners by last name
        List<Owner> results = ownerService.findByLastNameContaining(owner.getLastName());

        if (results.isEmpty())          // no owners found
        {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/find-owners";
        }
        else if (results.size() == 1)   // 1 owner found
        {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        else                            // multiple owners found
        {
            model.addAttribute("selections", results);
            return "owners/owners-list";
        }
    }

    @GetMapping("/new")
    public String initCreationForm(Model model)
    {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/owner-form";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result)
    {
        if (result.hasErrors()) return "owners/owner-form";

        Owner savedOwner =  ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model)
    {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/owner-form";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId)
    {
        if (result.hasErrors()) return "owners/owner-form";

        owner.setId(ownerId);
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }
}
