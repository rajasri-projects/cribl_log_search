package com.cribl.helper;

import com.cribl.model.SearchFilter;
import com.cribl.model.SearchFilterKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class SearchHelper {
    private static final String DEFAULT_FILENAME = "application_log";

    public SearchFilter getSearchFilter(final String query) {
        final SearchFilter searchFilter = new SearchFilter();
        if (StringUtils.isBlank(query)) {
            setDefaults(searchFilter);
            return searchFilter;
        }
        final String[] queryFilters = query.split(" ");
        for (final String queryFilter : queryFilters) {
            final String[] queryFilterKeyValue = queryFilter.split(":");

            if (SearchFilterKey.filename.name().equalsIgnoreCase(queryFilterKeyValue[0])) {
                searchFilter.setFileName(queryFilterKeyValue[1]);
            } else if (SearchFilterKey.pattern.name().equalsIgnoreCase(queryFilterKeyValue[0])) {
                searchFilter.setPattern(queryFilterKeyValue[1]);
            } else if (SearchFilterKey.match_count.name().equalsIgnoreCase(queryFilterKeyValue[0])) {
                searchFilter.setMatchCount(Integer.valueOf(queryFilterKeyValue[1]));
            }
        }

        setDefaults(searchFilter);
        return searchFilter;
    }

    private void setDefaults(final SearchFilter searchFilter) {
        searchFilter.setFileName(StringUtils.defaultIfBlank(searchFilter.getFileName(), DEFAULT_FILENAME));
        searchFilter.setMatchCount(searchFilter.getMatchCount() == null || searchFilter.getMatchCount() == 0? Integer.MAX_VALUE : searchFilter.getMatchCount());
    }
}
