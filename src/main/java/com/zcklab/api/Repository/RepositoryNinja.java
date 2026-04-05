package com.zcklab.api.Repository;

import com.zcklab.api.Model.Ninja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//analogia almoxarifado

@Repository
public interface RepositoryNinja extends JpaRepository<Ninja, Long> {
}
