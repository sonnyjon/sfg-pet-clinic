package dev.sonnyjon.sfgpetclinic.services;

import dev.sonnyjon.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
