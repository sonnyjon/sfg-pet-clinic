package dev.sonnyjon.sfgpetclinic.services.jpa;

import dev.sonnyjon.sfgpetclinic.model.PetType;
import dev.sonnyjon.sfgpetclinic.repos.PetTypeRepository;
import dev.sonnyjon.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sonny on 6/1/2022.
 */
@Service
@Profile("springdatajpa")
public class PetTypeJpaService implements PetTypeService
{
    private final PetTypeRepository petTypeRepository;

    public PetTypeJpaService(PetTypeRepository petTypeRepository)
    {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll()
    {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long id)
    {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType object)
    {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object)
    {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        petTypeRepository.deleteById(id);
    }
}
