package io.jprocess.boot;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessTemplateRepository;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.logging.Level.SEVERE;

public class AppLifecycleBean {

    @Inject
    ProcessTemplateRepository processTemplateRepository;

    private static final Logger LOG = Logger.getLogger(AppLifecycleBean.class.getName());

    void onStart(@Observes StartupEvent ev) {
        LOG.info("The application is starting...");
        loadProcessTemplates("BasicTestProcessDefinition.json");
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOG.info("The application is stopping...");
    }

    private void loadProcessTemplates(String name) {
        try {
            String template = new String(this.getClass().getResourceAsStream("/process-templates/" + name).readAllBytes(), UTF_8);
            processTemplateRepository.createProcessTemplate(new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(template, ProcessTemplate.class));
        } catch (IOException e) {
            LOG.log(SEVERE, "Failed to load templates", e);
        }
    }


}
