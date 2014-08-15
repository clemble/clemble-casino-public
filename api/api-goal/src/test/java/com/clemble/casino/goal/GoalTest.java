package com.clemble.casino.goal;

import com.clemble.casino.json.ObjectMapperUtils;
import com.clemble.casino.player.PlayerProfile;
//import fi.vincit.jmobster.JMobsterFactory;
//import fi.vincit.jmobster.ModelGenerator;
//
//import fi.vincit.jmobster.processor.FieldScanMode;
//import fi.vincit.jmobster.processor.FieldValueConverter;
//import fi.vincit.jmobster.processor.ModelFactory;
//import fi.vincit.jmobster.processor.defaults.DefaultValidatorScanner;
//import fi.vincit.jmobster.processor.defaults.validator.ValidatorWriterSet;
//import fi.vincit.jmobster.processor.frameworks.backbone.BackboneModelProcessor;
//import fi.vincit.jmobster.processor.frameworks.backbone.DefaultValueProcessor;
//import fi.vincit.jmobster.processor.frameworks.backbone.ValidatorProcessor;
//import fi.vincit.jmobster.processor.frameworks.backbone.validator.writer.JSR303Validators;
//import fi.vincit.jmobster.processor.languages.javascript.JavaScriptContext;
//import fi.vincit.jmobster.processor.languages.javascript.JavaToJSValueConverter;
//import fi.vincit.jmobster.processor.languages.javascript.valueconverters.EnumConverter;
//import fi.vincit.jmobster.processor.languages.javascript.writer.OutputMode;
//import fi.vincit.jmobster.processor.model.Model;
//import fi.vincit.jmobster.util.ConverterMode;
//import fi.vincit.jmobster.util.writer.FileDataWriter;
//import fi.vincit.jmobster.util.writer.StringBufferWriter;
import org.junit.Test;

/**
 * Created by mavarazy on 14/03/14.
 */
public class GoalTest {

    final private String GOAL_JSON = "{ \"description\": \"Run 10K\",\n" +
        "\"dueDate\": \"2014-08-22T17:04:58.337Z\",\n" +
        "\"goalKey\": null,\n" +
        "\"player\": null,\n" +
        "\"state\": null,\n" +
        "\"statuses\": [] }";

    @Test
    public void testGoalFormat() throws Exception {
        Goal readJson = ObjectMapperUtils.OBJECT_MAPPER.readValue(GOAL_JSON, Goal.class);
    }

}
