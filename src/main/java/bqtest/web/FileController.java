package bqtest.web;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bqtest.service.Member;
import bqtest.service.MemberFileProcessor;

@RestController
public class FileController {

	@Autowired MemberFileProcessor memberFileProcessor;
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @GetMapping(value = "/api/load-data")
    public Map<String, List<Member>> loadData(@Value("${config.file-to-load}") String fileToLoad) throws Exception {

        log.info("Got an request to load file {}", fileToLoad);

        File uploadedFile = new File(fileToLoad);

        Map<String, List<Member>> groupedMembers = processFile(uploadedFile);

        log.info("Successfully grouped to {} states ", groupedMembers.keySet().size());

        return groupedMembers;
    }

    private Map<String, List<Member>> processFile(File uploadedFile) {
    	Map<String, List<Member>> processFile  = null;
        /**
         * Use MemberFileProcessor to process the file
         */
    	try {
			 processFile = memberFileProcessor.processFile(uploadedFile);
		} catch (Exception e) {
	        log.error("Error while processing member list file"
	        , e);

		}
        return processFile;
    }
}
