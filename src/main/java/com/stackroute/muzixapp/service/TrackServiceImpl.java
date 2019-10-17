package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService{

    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository){
        System.out.println("Inside TrackServiceImpl class");
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getId()))
            throw new TrackAlreadyExistsException("Track already exists");

        Track savedTrack = trackRepository.save(track);

        if(savedTrack == null)
            throw new TrackAlreadyExistsException("Track is null");

        return savedTrack;
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        Optional<Track> track = trackRepository.findById(id);
        if(track.isPresent()){
            return track.get();
        } else {
            throw new TrackNotFoundException("Track Id "+id+" is not found");
        }
    }

    @Override
    public Track deleteTrack(int id) throws TrackNotFoundException {
        Optional<Track> track = trackRepository.findById(id);
        if(track.isPresent()) {
            trackRepository.delete(getTrackById(id));
            return track.get();
        } else {
            throw new TrackNotFoundException("Track Id "+id+" is not found");
        }
    }

    @Override
    public List<Track> deleteAllTracks(){
        List<Track> trackList = trackRepository.findAll();
        trackRepository.deleteAll();
        return trackList;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track updateTrack(int id, String comment) throws TrackNotFoundException{
        Optional<Track> track = trackRepository.findById(id);
        if(track.isPresent()){
            Track track1 = track.get();
            track1.setComment(comment);
            Track savedTrack = trackRepository.save(track1);
            return savedTrack;
        } else {
            throw new TrackNotFoundException("Track Id "+id+" is not found");
        }

    }

    @Override
    public List<Track> getTrackByName(String name) {
        return trackRepository.findByName(name);
    }
}

