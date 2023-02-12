package com.cribl.controller;

import com.cribl.helper.SearchHelper;
import com.cribl.model.SearchFilter;
import com.cribl.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @Autowired
    private SearchHelper searchHelper;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StreamingResponseBody> search(@RequestParam(name="query", required = false) String query,
                                                        HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        final SearchFilter searchFilter = searchHelper.getSearchFilter(query);
        StreamingResponseBody responseBody = response -> {
            searchService.search(searchFilter, response);
        };
        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(responseBody);
    }
}
