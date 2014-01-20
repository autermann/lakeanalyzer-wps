package com.github.autermann.wps.lakeanalyzer;

import com.github.autermann.wps.matlab.MatlabAlgorithmRepository;
import com.github.autermann.wps.matlab.MatlabFileHandler;
import com.github.autermann.wps.streaming.StreamingWPS;
import com.github.autermann.wps.streaming.delegate.DelegatingStreamingAlgorithm;
import com.github.autermann.wps.streaming.delegate.ProcessDescriptionParser;
import com.github.autermann.wps.streaming.delegate.StaticInputParser;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann
 */
public class Main {

    public static void main(String[] args) throws Exception {
        SetMultimap<String, String> properties = HashMultimap.create();
        properties.put(MatlabAlgorithmRepository.CONFIG_PROPERTY,
                       Main.class.getResource("/lakeanalyzer.yml").getFile());
        StreamingWPS wps = new StreamingWPS("localhost", 12121);
        wps.addAlgorithmRepository(MatlabAlgorithmRepository.class, properties);
        wps.addGenerator(MatlabFileHandler.class);
        wps.addAlgorithm(DelegatingStreamingAlgorithm.class);
        wps.addParser(ProcessDescriptionParser.class);
        wps.addParser(StaticInputParser.class);
        wps.addParser(MatlabFileHandler.class);
        wps.start();
    }
}
