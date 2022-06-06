package dev.sonnyjon.sfgpetclinic.services.map;

import dev.sonnyjon.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Sonny on 6/5/2022.
 */
class OwnerMapServiceTest
{
    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp()
    {
        Owner owner = Owner.builder()
                            .id(ownerId)
                            .lastName(lastName)
                            .build();

        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(owner);
    }

    @Test
    void findAll()
    {
        Set<Owner> owners =ownerMapService.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findByLastName()
    {
        Owner smith = ownerMapService.findByLastName(lastName);

        assertNotNull(smith);
        assertEquals(ownerId, smith.getId());
    }

    @Test
    void findById()
    {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingOwner()
    {
        Long id = 2L;
        Owner owner = Owner.builder().id(id).build();
        Owner saved = ownerMapService.save(owner);

        assertEquals(id, saved.getId());
    }

    @Test
    void saveNewOwner()
    {
        Owner newOwner = Owner.builder().build();
        Owner saved = ownerMapService.save(newOwner);

        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    void delete()
    {
        Owner owner = ownerMapService.findById(ownerId);
        ownerMapService.delete(owner);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById()
    {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }
}