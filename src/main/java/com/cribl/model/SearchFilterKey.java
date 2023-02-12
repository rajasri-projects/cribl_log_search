package com.cribl.model;

/**
 * SearchFilterKey indicates the types of filters supported by the
 * application.
 *
 * To support any new type of filters in the future (for example timestamp),
 * a new key should be created here.
 *
 */
public enum SearchFilterKey {
    filename, pattern, match_count
}
