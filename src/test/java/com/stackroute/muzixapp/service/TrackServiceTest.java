package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    Track track;

    //Create a mock for TrackRepository
    @Mock
    TrackRepository trackRepository;

    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setName("Waake");
        track.setId(101);
        track.setComment("Excellent");
        list = new ArrayList<>();

        list.add(track);
    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        assertEquals(track,savedTrack);

        verify(trackRepository,times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        assertEquals(track,savedTrack);

        verify(trackRepository,times(1)).save(track);
    }

    @Test
    public void getAllTracks(){

        when(trackRepository.findAll()).thenReturn(list);
        List<Track> trackslist = trackService.getAllTracks();
        assertEquals(list,trackslist);

        verify(trackRepository,times(1)).findAll();
    }

    @Test
    public void getTrackByIdSuccess() throws TrackNotFoundException {

        when(trackRepository.findById(anyInt())).thenReturn(Optional.ofNullable(track));
        Track getTrack = trackService.getTrackById(track.getId());
        assertEquals(track,getTrack);

        verify(trackRepository,times(1)).findById(anyInt());
    }

    @Test(expected = TrackNotFoundException.class)
    public void getTrackByIdFailure() throws TrackNotFoundException {

        when(trackRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        Track getTrack = trackService.getTrackById(track.getId());
        assertEquals(track,getTrack);

        verify(trackRepository,times(1)).findById(anyInt());
    }

    @Test
    public  void deleteTrackByIdSuccess() throws TrackNotFoundException{

        when(trackRepository.findById(anyInt())).thenReturn(Optional.ofNullable(track));
        Track deletedTrack = trackService.deleteTrack(track.getId());
        assertEquals(track, deletedTrack);

        verify(trackRepository,times(1)).delete((Track) any());
    }

    @Test(expected = TrackNotFoundException.class)
    public  void deleteTrackByIdFailure() throws TrackNotFoundException{

        when(trackRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        Track deletedTrack = trackService.deleteTrack(track.getId());
        assertEquals(track, deletedTrack);

        verify(trackRepository,times(1)).delete((Track) any());
    }

    @Test
    public void deleteAllTracks(){

        when(trackRepository.findAll()).thenReturn(list);
        List<Track> trackslist = trackService.deleteAllTracks();
        assertEquals(list,trackslist);

        verify(trackRepository,times(1)).deleteAll();
    }

    @Test
    public void getTrackByName(){

        when(trackRepository.findByName(anyString())).thenReturn(list);
        List<Track> trackList = trackService.getTrackByName(track.getName());
        assertEquals(list, trackList);

        verify(trackRepository,times(1)).findByName(anyString());
    }

    @Test
    public void updateTrackSuccess() throws TrackNotFoundException{

        when(trackRepository.findById(anyInt())).thenReturn(Optional.ofNullable(track));
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track updatedTrack = trackService.updateTrack(track.getId(),anyString());
        assertEquals(track, updatedTrack);

        verify(trackRepository,times(1)).findById(anyInt());
        verify(trackRepository,times(1)).save((Track) any());
    }

    @Test(expected = TrackNotFoundException.class)
    public void updateTrackFailure() throws TrackNotFoundException{

        when(trackRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track updatedTrack = trackService.updateTrack(track.getId(),anyString());
        assertEquals(track, updatedTrack);

        verify(trackRepository,times(1)).findById(anyInt());
        verify(trackRepository,times(1)).save((Track) any());
    }
}
