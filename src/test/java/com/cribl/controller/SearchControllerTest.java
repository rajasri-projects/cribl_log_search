package com.cribl.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cribl.helper.SearchHelper;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class SearchControllerTest {

    @Mock
    private HttpServletRequest mockedHttpServletRequest;

    @Mock
    private HttpServletResponse mockedHttpServletResponse;

    @Spy
    private SearchHelper searchHelper = new SearchHelper();

    @InjectMocks
    final SearchController searchController = new SearchController();

    @Test
    public void testSearch() {
        final ResponseEntity<StreamingResponseBody> streamingResponseBodyResponseEntity =
                searchController.search(StringUtils.EMPTY, mockedHttpServletRequest, mockedHttpServletResponse);

        assertEquals(HttpStatus.OK, streamingResponseBodyResponseEntity.getStatusCode());
    }

}
