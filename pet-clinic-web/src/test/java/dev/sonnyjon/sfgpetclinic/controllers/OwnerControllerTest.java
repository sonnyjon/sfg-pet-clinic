package dev.sonnyjon.sfgpetclinic.controllers;

import dev.sonnyjon.sfgpetclinic.model.Owner;
import dev.sonnyjon.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by Sonny on 6/6/2022.
 */
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest
{
    @Mock
    OwnerService service;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;
    MockMvc mockMvc;

    @BeforeEach
    void setUp()
    {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findOwners() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/find-owners"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(service);
    }

    @Test
    void displayOwner() throws Exception
    {
        when(service.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owner-details"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }

    @Test
    void processFindFormReturnMany() throws Exception
    {
        Owner owner1 = Owner.builder().id(1L).build();
        Owner owner2 = Owner.builder().id(2L).build();

        when(service.findByLastNameContaining(anyString())).thenReturn(Arrays.asList(owner1, owner2));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owners-list"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception
    {
        Owner owner1 = Owner.builder().id(1L).build();

        when(service.findByLastNameContaining(anyString())).thenReturn(Arrays.asList(owner1));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void processFindFormEmptyReturnMany() throws Exception
    {
        Owner owner1 = Owner.builder().id(1L).build();
        Owner owner2 = Owner.builder().id(2L).build();

        when(service.findByLastNameContaining(anyString())).thenReturn(Arrays.asList(owner1, owner2));

        mockMvc.perform(get("/owners").param("lastName",""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owners-list"))
                .andExpect(model().attribute("selections", hasSize(2)));;
    }
}