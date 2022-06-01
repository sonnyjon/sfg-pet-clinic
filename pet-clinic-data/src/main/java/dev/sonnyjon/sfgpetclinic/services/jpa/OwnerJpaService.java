package dev.sonnyjon.sfgpetclinic.services.jpa;

import dev.sonnyjon.sfgpetclinic.model.Owner;
import dev.sonnyjon.sfgpetclinic.repos.OwnerRepository;
import dev.sonnyjon.sfgpetclinic.repos.PetRepository;
import dev.sonnyjon.sfgpetclinic.repos.PetTypeRepository;
import dev.sonnyjon.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Sonny on 6/1/2022.
 */
@Service
@Profile("springdatajpa")
public class OwnerJpaService implements OwnerService
{
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerJpaService(OwnerRepository ownerRepository,
                           PetRepository petRepository,
                           PetTypeRepository petTypeRepository)
    {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<Owner> findAll()
    {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id)
    {
        Optional<Owner> owner = ownerRepository.findById(id);
        return owner.orElse(null);
    }

    @Override
    public Owner findByLastName(String lastName)
    {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Owner save(Owner object)
    {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object)
    {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        ownerRepository.deleteById(id);
    }
}
