package com.cribl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cribl.model.SearchFilter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

public class SearchServiceTest {
    private static final String DECORATOR_SEPARATOR = "<br/>";

    final SearchService searchService = new SearchService();

    @Test
    public void testSearch() {
        final SearchFilter searchFilter = getSearchFilter("log", null, 100);

        final String[] lines = getLog(searchFilter);

        final String[] expectedLines = new String[] {
                "Lines 3", "Line 5", "Line 4", "Lines 2", "Lines 1", "Line 3", "Line 2", "Line 1"};

        assertEquals(expectedLines.length, lines.length);
        assertIfEqual(expectedLines, lines);
    }

    @Test
    public void testSearchWithMatchCount() {
        final SearchFilter searchFilter = getSearchFilter("log", null, 3);

        final String[] lines = getLog(searchFilter);

        final String[] expectedLines = new String[] {
                "Lines 3", "Line 5", "Line 4"};

        assertEquals(expectedLines.length, lines.length);
        assertIfEqual(expectedLines, lines);
    }

    @Test
    public void testSearchWithPattern() {
        final SearchFilter searchFilter = getSearchFilter("log", "Line 4", 3);

        final String[] lines = getLog(searchFilter);

        final String[] expectedLines = new String[] {
                "Line 4"};

        assertEquals(expectedLines.length, lines.length);
        assertIfEqual(expectedLines, lines);
    }

    @Test
    public void testSearchWithRegexPattern() {
        final SearchFilter searchFilter = getSearchFilter("log", "Lines..", 10);

        final String[] lines = getLog(searchFilter);

        final String[] expectedLines = new String[] {
                "Lines 3", "Lines 2", "Lines 1"};

        assertEquals(expectedLines.length, lines.length);
        assertIfEqual(expectedLines, lines);
    }

    @Test
    public void testSearchWithRegexPatternAndMatchCount() {
        final SearchFilter searchFilter = getSearchFilter("log", "Lines..", 2);

        final String[] lines = getLog(searchFilter);

        final String[] expectedLines = new String[] {
                "Lines 3", "Lines 2"};

        assertEquals(expectedLines.length, lines.length);
        assertIfEqual(expectedLines, lines);
    }

    @Test
    public void testSearchWithZeroMatchCount() {
        final SearchFilter searchFilter = getSearchFilter("log", "Lines..", 0);

        final String[] lines = getLog(searchFilter);

        final String[] expectedLines = new String[] {};

        assertEquals(expectedLines.length, lines.length);
    }

    private String[] getLog(final SearchFilter searchFilter) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        searchService.setProdStage(false);
        searchService.search(searchFilter, byteArrayOutputStream);
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (byteArray.length == 0) {
            return new String[] {};
        }
        final String[] lines = new String(byteArray).split(DECORATOR_SEPARATOR);
        return lines;
    }

    private SearchFilter getSearchFilter(final String filename, final String pattern,
                                         final Integer matchCount) {
        return SearchFilter.builder()
                .fileName(filename)
                .pattern(pattern)
                .matchCount(matchCount)
                .build();
    }

    private void assertIfEqual(final String[] expected, final String[] actual) {
        int count = 0;
        for (final String expectedValue : expected) {
            assertEquals(expectedValue, actual[count]);
            count++;
        }
    }

}
