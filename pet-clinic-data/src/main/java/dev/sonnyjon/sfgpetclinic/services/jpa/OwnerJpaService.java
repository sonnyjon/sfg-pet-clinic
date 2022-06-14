package dev.sonnyjon.sfgpetclinic.services.jpa;

import dev.sonnyjon.sfgpetclinic.model.Owner;
import dev.sonnyjon.sfgpetclinic.repos.OwnerRepository;
import dev.sonnyjon.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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

    public OwnerJpaService(OwnerRepository ownerRepository)
    {
        this.ownerRepository = ownerRepository;
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
    public List<Owner> findByLastNameContaining(String lastName)
    {
        return ownerRepository.findByLastNameLike(lastName);
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
