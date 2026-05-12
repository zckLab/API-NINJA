package com.zcklab.api.repository;

import com.zcklab.api.model.Clan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClanRepository extends JpaRepository<Clan, Long> {
}
