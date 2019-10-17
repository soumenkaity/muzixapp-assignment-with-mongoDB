package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public Track getTrackById(int id) throws TrackNotFoundException;

    public Track deleteTrack(int id) throws TrackNotFoundException;

    public List<Track> deleteAllTracks();

    public List<Track> getAllTracks();

    public Track updateTrack(int id, String comment) throws TrackNotFoundException;

    public List<Track> getTrackByName(String name);
}
