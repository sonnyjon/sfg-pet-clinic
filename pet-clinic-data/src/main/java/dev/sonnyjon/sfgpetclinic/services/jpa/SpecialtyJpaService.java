package dev.sonnyjon.sfgpetclinic.services.jpa;

import dev.sonnyjon.sfgpetclinic.model.Specialty;
import dev.sonnyjon.sfgpetclinic.repos.SpecialtyRepository;
import dev.sonnyjon.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sonny on 6/1/2022.
 */
@Service
@Profile("springdatajpa")
public class SpecialtyJpaService implements SpecialtyService
{
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyJpaService(SpecialtyRepository specialtyRepository)
    {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll()
    {
        Set<Specialty> specialties = new HashSet<>();
        specialtyRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(Long id)
    {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty object)
    {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Specialty object)
    {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        specialtyRepository.deleteById(id);
    }
}
