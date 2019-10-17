package com.stackroute.muzixapp.seeddata;

import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class CommandLineRunnerDemo implements CommandLineRunner {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    Environment environment;

    @Override
    public void run(String... args) throws Exception {
        trackRepository.save(new Track(Integer.parseInt(environment.getProperty("id2")), environment.getProperty("name2"), environment.getProperty("comment2")));
    }
}
