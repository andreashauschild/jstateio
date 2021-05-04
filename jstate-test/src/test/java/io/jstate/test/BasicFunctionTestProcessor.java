package io.jstate.test;

import io.jstate.spi.ProcessInstance;
import io.jstate.spi.Processor;
import io.jstate.spi.State;

import java.util.Map;
import java.util.logging.Logger;

public class BasicFunctionTestProcessor implements Processor {

    static final Logger logger = Logger.getLogger(BasicFunctionTestProcessor.class.getName());

    public static final String CLEANUP_WORKING_DIR = "cleanupWorkingDir";
    public static final String UPLOAD_MPEG_TO_SERVER = "uploadMpegToServer";
    public static final String CREATE_MPEG_FROM_IMAGES = "createMpegFromImages";
    public static final String COPY_IMAGES_TO_WORKING_DIR = "copyImagesToWorkingDir";

    @Override
    public State process(ProcessInstance processInstance) {

        if (processInstance.getCurrentState() == null) {
            logger.info("No state set yet will copy files to directory");
            return state(COPY_IMAGES_TO_WORKING_DIR, null);
        }
        return null;
    }

    private State state(String id, Map<String, String> properties) {

        return new State().setId(id).setProperties(properties);
    }
}
