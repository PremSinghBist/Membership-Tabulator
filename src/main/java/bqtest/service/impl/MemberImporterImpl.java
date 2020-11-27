package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberImporter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MemberImporterImpl implements MemberImporter {

    public List<Member> importMembers(File inputFile) throws IOException {
        return Files.lines(inputFile.toPath())
                .map(line -> {
                	return member(line);
                }).collect(Collectors.toList());
    }
    /**
     * 
     * @param line : Member Data received from Member.txt
     * @return
     */
    private Member member(String line) {
    	Member member = new Member();
    	String[] data  = line.split("\\s+");
    	member.setId(data[0]);
    	member.setLastName(data[1]);
    	member.setFirstName(data[2]);
    	member.setAddress(data[3] + " " + data[4] + " "  + data[5]);
    	member.setCity(data[6]);
    	member.setState(data[7]);
    	member.setZip(data[8]);
    	
        return member;

    }

}
