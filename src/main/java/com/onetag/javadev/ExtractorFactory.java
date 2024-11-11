package com.onetag.javadev;


/**
 * Factory for Extractor generation
 */
public class ExtractorFactory {

    public static Extractor getExtractor() {
        // TODO: create Class that implements Extractor and return it
        return new OscarExtractor();
    }
}
