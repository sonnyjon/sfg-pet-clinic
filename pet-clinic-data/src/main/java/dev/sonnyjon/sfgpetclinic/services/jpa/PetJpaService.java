package dev.sonnyjon.sfgpetclinic.services.jpa;

import dev.sonnyjon.sfgpetclinic.model.Pet;
import dev.sonnyjon.sfgpetclinic.repos.PetRepository;
import dev.sonnyjon.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sonny on 6/1/2022.
 */
@Service
@Profile("springdatajpa")
public class PetJpaService implements PetService
{
    private final PetRepository petRepository;

    public PetJpaService(PetRepository petRepository)
    {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll()
    {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id)
    {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet object)
    {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object)
    {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        petRepository.deleteById(id);
    }
}
