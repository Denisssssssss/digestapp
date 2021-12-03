package com.example.digestapp.repositories;

import com.example.digestapp.models.data.Digest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigestRepository extends MongoRepository<Digest, String> {
}
