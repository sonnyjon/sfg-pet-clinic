package dev.sonnyjon.sfgpetclinic.services;

import dev.sonnyjon.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>
{
    Owner findByLastName(String lastName);

    List<Owner> findByLastNameContaining(String lastName);
}
