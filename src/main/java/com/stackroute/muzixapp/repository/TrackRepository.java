package com.stackroute.muzixapp.repository;

import com.stackroute.muzixapp.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TrackRepository extends MongoRepository<Track, Integer> {
    @Query("{ 'name' : ?0}")
    public List<Track> findByName(String name);
}
