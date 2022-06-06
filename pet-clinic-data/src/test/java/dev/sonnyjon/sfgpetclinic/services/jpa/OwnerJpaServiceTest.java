package dev.sonnyjon.sfgpetclinic.services.jpa;

import dev.sonnyjon.sfgpetclinic.model.Owner;
import dev.sonnyjon.sfgpetclinic.repos.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Sonny on 6/5/2022.
 */
@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest
{
    public static final String LAST_NAME = "Smith";

    @Mock
    OwnerRepository repository;

    @InjectMocks
    OwnerJpaService service;

    Owner returnOwner;

    @BeforeEach
    void setUp()
    {
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll()
    {
        Set<Owner> returnSet = new HashSet<>();
        returnSet.add(Owner.builder().id(1L).build());
        returnSet.add(Owner.builder().id(2L).build());

        when(repository.findAll()).thenReturn(returnSet);

        Set<Owner> owners = service.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById()
    {
        when(repository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound()
    {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = service.findById(1L);

        assertNull(owner);
    }

    @Test
    void findByLastName()
    {
        when(repository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = service.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, smith.getLastName());
        verify(repository).findByLastName(any());   // <- redundant?
    }

    @Test
    void save()
    {
        Owner owner = Owner.builder().id(1L).build();
        when(repository.save(any())).thenReturn(returnOwner);

        Owner saved = service.save(owner);
        assertNotNull(saved);
        verify(repository).save(any());
    }

    @Test
    void delete()
    {
        service.delete(returnOwner);
        verify(repository).delete(any());
    }

    @Test
    void deleteById()
    {
        service.deleteById(1L);
        verify(repository).deleteById(anyLong());
    }
}