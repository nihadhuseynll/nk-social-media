package com.nkcode.nksocialmedia.dao.repository;

import com.nkcode.nksocialmedia.dao.entity.Photo;
import com.nkcode.nksocialmedia.dao.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, UUID> {
}
