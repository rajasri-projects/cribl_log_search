package com.cribl.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cribl.model.SearchFilter;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

public class SearchHelperTest {

    final SearchHelper searchHelper = new SearchHelper();

    @Test
    public void testSearchFilterWithBlankQuery() {
        final SearchFilter searchFilter = searchHelper.getSearchFilter(StringUtils.EMPTY);

        assertEquals("application_log", searchFilter.getFileName());
        assertEquals(Integer.MAX_VALUE, searchFilter.getMatchCount());
    }

    @Test
    public void testSearchFilterWithFilename() {
        final SearchFilter searchFilter = searchHelper.getSearchFilter("filename:log");

        assertEquals("log", searchFilter.getFileName());
        assertEquals(Integer.MAX_VALUE, searchFilter.getMatchCount());
    }

    @Test
    public void testSearchFilterWithMatchCount() {
        final SearchFilter searchFilter = searchHelper.getSearchFilter("filename:log match_count:3");

        assertEquals("log", searchFilter.getFileName());
        assertEquals(3, searchFilter.getMatchCount());
    }

    @Test
    public void testSearchFilterWithPattern() {
        final SearchFilter searchFilter = searchHelper.getSearchFilter("filename:log match_count:3 pattern:Line");

        assertEquals("log", searchFilter.getFileName());
        assertEquals(3, searchFilter.getMatchCount());
        assertEquals("Line", searchFilter.getPattern());
    }
}
