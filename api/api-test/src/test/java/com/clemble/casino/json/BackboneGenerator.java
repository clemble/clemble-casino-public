package com.clemble.casino.json;

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
public class BackboneGenerator {

    @Test
    public void generateBackbone() throws Exception {
//        StringBufferWriter writer = new StringBufferWriter();
//
//        ModelFactory factory = JMobsterFactory.getModelFactoryBuilder()
//            .setFieldScanMode(FieldScanMode.DIRECT_FIELD_ACCESS)
//            .setValidatorScanner(new DefaultValidatorScanner())
//            .build();
//        Collection<Model> models = factory.createAll(PlayerProfile.class, PlayerPresence.class);
//
//        // Setup writers
//        JavaScriptContext context = new JavaScriptContext(writer, OutputMode.JSON);
//
//        // Setup generator
//        FieldValueConverter converter = new JavaToJSValueConverter(
//            ConverterMode.NULL_AS_DEFAULT,
//            EnumConverter.EnumMode.STRING,
//            JavaToJSValueConverter.ISO_8601_DATE_TIME_TZ_PATTERN
//        );
//
//        BackboneModelProcessor.Builder backboneModelProcessor = new BackboneModelProcessor.Builder(context);
//        backboneModelProcessor.setModelProcessors(new DefaultValueProcessor.Builder().setValueConverter(converter).build());
//
//        ModelGenerator generator = JMobsterFactory.getModelGenerator(backboneModelProcessor.build());
//        generator.processAll(models);
//
//        System.out.println("Results: " + writer.toString());
    }

}
