package org.example;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {

        builder.withDetail("currentTime", LocalDateTime.now().toString());

        builder.withDetail("customInfo", "This is a custom info field");
    }
}
