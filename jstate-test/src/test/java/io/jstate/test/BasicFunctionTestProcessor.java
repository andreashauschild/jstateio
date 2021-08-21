package io.jstate.test;

import io.jstate.core.services.io.jstate.core.state.FinalState;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.Processor;
import io.jstate.spi.State;

import java.util.Map;
import java.util.logging.Logger;

import static io.jstate.spi.DefaultStates.NEW;

public class BasicFunctionTestProcessor extends Processor {

    static final Logger logger = Logger.getLogger(BasicFunctionTestProcessor.class.getName());

    public static final String CLEANUP_WORKING_DIR = "cleanupWorkingDir";
    public static final String UPLOAD_MPEG_TO_SERVER = "uploadMpegToServer";
    public static final String CREATE_MPEG_FROM_IMAGES = "createMpegFromImages";
    public static final String COPY_IMAGES_TO_WORKING_DIR = "copyImagesToWorkingDir";

    @Override
    public State process(ProcessInstance processInstance) {

        if (processInstance.getCurrentState() == null || NEW.equals(processInstance.getCurrentState().getName())) {
            logger.info("1. No state set yet will copy files to directory");
            this.logInfo("info1");
            this.logWarn("warn1");
            this.logError("error1");
            return state(COPY_IMAGES_TO_WORKING_DIR, null);
        } else if (COPY_IMAGES_TO_WORKING_DIR.equals(processInstance.getCurrentState().getName())) {
            logger.info("2. Creating mpeg from images");
            this.logInfo("info2");
            this.logWarn("warn2");
            this.logError("error2");
            return state(CREATE_MPEG_FROM_IMAGES, null);
        } else if (CREATE_MPEG_FROM_IMAGES.equals(processInstance.getCurrentState().getName())) {
            logger.info("3. Upload mpeg to server");
            this.logInfo("info3");
            this.logWarn("warn3");
            this.logError("error3");
            return state(UPLOAD_MPEG_TO_SERVER, null);
        } else if (UPLOAD_MPEG_TO_SERVER.equals(processInstance.getCurrentState().getName())) {
            logger.info("4. Cleanup working directory");
            this.logInfo("info4");
            this.logWarn("warn4");
            this.logError("error4");
            return state(CLEANUP_WORKING_DIR, null);
        } else if (CLEANUP_WORKING_DIR.equals(processInstance.getCurrentState().getName())) {
            logger.info("5. Finished Process");
            this.logInfo("info5");
            this.logWarn("warn5");
            this.logError("error5");
            return new FinalState();
        }
        return null;
    }

    private State state(String stateName, Map<String, String> properties) {

        return new State().setName(stateName).setProperties(properties);
    }
}
