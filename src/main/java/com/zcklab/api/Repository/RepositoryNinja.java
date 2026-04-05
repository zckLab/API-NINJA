package com.zcklab.api.Repository;

import com.zcklab.api.Model.Ninja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//It works like a warehouse, respects the logic that comes from the Service and delivers the necessary
// products from the database

@Repository
public interface RepositoryNinja extends JpaRepository<Ninja, Long> {

}
