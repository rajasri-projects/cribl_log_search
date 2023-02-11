package com.cribl.service;

import com.cribl.model.SearchFilter;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

@Component
public class SearchService {
    private static final String PATH = "/Users/raja/LogFiles/";

    private boolean prodStage = true;

    public void search(final SearchFilter searchFilter, final OutputStream outputStream) {
        if (searchFilter.getMatchCount() == 0) {
            return;
        }
        try {
            final ReversedLinesFileReader reversedLinesFileReader = new ReversedLinesFileReader(
                    getFile(searchFilter.getFileName()),
                    StandardCharsets.UTF_8);
            int count = 0;
            while (true) {
                final String line = reversedLinesFileReader.readLine();
                if (StringUtils.isBlank(line)) {
                    break;
                }

                boolean writeLine = false;
                if (StringUtils.isBlank(searchFilter.getPattern()) ||
                        (StringUtils.isNotBlank(searchFilter.getPattern())
                                && (line.contains(searchFilter.getPattern()) || line.matches(searchFilter.getPattern())))) {
                    writeLine = true;
                }

                if (writeLine) {
                    count++;
                    outputStream.write(getDecoratedLine(line).getBytes(StandardCharsets.UTF_8));
                }

                if (count >= searchFilter.getMatchCount()) {
                    break;
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String getDecoratedLine(final String line) {
        return line + "<br/>";
    }

    void setProdStage(final boolean prodStage) {
        this.prodStage = prodStage;
    }

    private File getFile(final String fileName) throws URISyntaxException {
        if (this.prodStage) {
            return new File(PATH + fileName);
        } else {
            URL resource = SearchService.class.getClassLoader().getResource(fileName);
            return Paths.get(resource.toURI()).toFile();
        }
    }

}
