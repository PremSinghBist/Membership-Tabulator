package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberFileProcessor;
import bqtest.service.MemberImporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class MemberFileProcessorImpl extends MemberFileProcessor {

	@Autowired MemberImporter memberImporter;
    /*
     * Implement methods here
     */

    @Override
    protected MemberImporter getMemberImporter() {
        return memberImporter;
    }

    @Override
    protected List<Member> getNonDuplicateMembers(List<Member> membersFromFile) {
    	List<Member> newList = membersFromFile.stream()
                .distinct() 
                .collect(Collectors.toList());
        return newList;
    }

    @Override
    protected Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
    	Map<String, List<Member>> membersByState = validMembers.stream().collect(Collectors.groupingBy(Member::getState));
    	return membersByState;
    }

}
