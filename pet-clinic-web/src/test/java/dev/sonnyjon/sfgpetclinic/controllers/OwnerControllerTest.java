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

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    void listOwners() throws Exception
    {
        when(service.findAll()).thenReturn(owners);
        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void listOwnersByIndex() throws Exception
    {
        when(service.findAll()).thenReturn(owners);
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("not-implemented"));

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
}