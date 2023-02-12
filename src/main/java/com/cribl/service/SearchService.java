package com.cribl.service;

import com.cribl.model.SearchFilter;
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
    // Replace with the path on your file system, as described in the README
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
            int currentMatchCount = 0;
            while (true) {
                final String line = reversedLinesFileReader.readLine();
                if (StringUtils.isBlank(line)) {
                    break;
                }

                if (shouldWrite(searchFilter, line)) {
                    currentMatchCount++;
                    outputStream.write(getDecoratedLine(line).getBytes(StandardCharsets.UTF_8));
                }

                if (currentMatchCount >= searchFilter.getMatchCount()) {
                    break;
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Determines if the retrieved log line should be written to the output.
     *
     * The log line will be written only if the user has not specified a pattern
     * or has specified a pattern and that pattern matches with the information in the
     * log line.
     *
     * For a match, the log line should either contain that pattern or should match
     * with the regex provided by the user.
     *
     * @param searchFilter - the search filters provided by the user or defaults if absent
     * @param line - the log line to match against
     * @return boolean - indicates if the log line should be written or not
     */
    private boolean shouldWrite(final SearchFilter searchFilter, final String line) {
        return StringUtils.isBlank(searchFilter.getPattern()) ||
                (StringUtils.isNotBlank(searchFilter.getPattern())
                        && (line.contains(searchFilter.getPattern()) || line.matches(searchFilter.getPattern())));
    }

    private String getDecoratedLine(final String line) {
        return line + "<br/>";
    }

    /* Package */ void setProdStage(final boolean prodStage) {
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
