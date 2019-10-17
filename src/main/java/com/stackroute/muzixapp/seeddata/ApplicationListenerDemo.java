package com.stackroute.muzixapp.seeddata;

import com.stackroute.muzixapp.configurationproperty.MyConfigurationProperty;
import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
@EnableConfigurationProperties(MyConfigurationProperty.class)
public class ApplicationListenerDemo implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${id1}")
    private int trackId;

    @Value("${name1}")
    private String trackName;

    @Value("${comment1}")
    private String trackComment;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    MyConfigurationProperty myConfigurationProperty;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        trackRepository.save(new Track(trackId, trackName, trackComment));
        trackRepository.save(new Track(myConfigurationProperty.getId(), myConfigurationProperty.getName(), myConfigurationProperty.getComment()));
    }
}