package mpp.project.core.service;

import mpp.project.core.model.Address;
import mpp.project.core.model.Member;
import mpp.project.core.exceptions.InvalidArgumentException;
import mpp.project.core.exceptions.ValidatorException;
import mpp.project.core.model.Team;
import mpp.project.core.model.validators.MemberValidator;
import mpp.project.core.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public static final Logger log = LoggerFactory.getLogger(MemberService.class);
    private MemberValidator validator = new MemberValidator();

    private void validateEntity(Member entity) throws ValidatorException {
        validator.validate(entity);
    }

    public void addEntity(Member entity) throws ValidatorException {
        log.info("add member -- method entered");

        this.validateEntity(entity);
        Address address = addressRepository.findById(entity.getAddress().getId()).orElseThrow(
                () -> new InvalidArgumentException("Invalid ID"));

        entity.setAddress(address);
        address.setMember(entity);
        memberRepository.save(entity);

        log.info("add member -- method finished");
    }

    @Transactional
    public void updateEntity(Member entity) throws ValidatorException, InvalidArgumentException{
        log.info("update member -- method entered");

        this.validateEntity(entity);
        Member updateMember = memberRepository.findById(entity.getId()).orElseThrow(() -> new InvalidArgumentException("Invalid ID"));
        updateMember.setName(entity.getName());
        updateMember.setDateOfBirth(entity.getDateOfBirth());
        memberRepository.save(updateMember);

        log.info("update member -- method finished");
    }

    public void deleteEntity(Long id) throws InvalidArgumentException {
        log.info("delete member -- method entered");

        Member member = memberRepository.findById(id).orElseThrow(() -> new InvalidArgumentException("Invalid ID"));

        projectRepository.findByCreatedBy(member)
                        .forEach((project) -> {
                            project.getTeamProjects().clear();
                            projectRepository.deleteById(project.getId());
                        });

        memberRepository.deleteById(id);

        log.info("delete member -- method finished");
    }

    public Page<Member> getPagedSortedEntities(Pageable pageable) {
        log.info("get page sorted members -- method entered, page={}", pageable.getPageNumber());

        List<Member> members = memberRepository.findAllOrderByNameAsc();
        System.out.println(members);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = pageSize * currentPage;

        int lastItem = Math.min(startItem + pageSize, members.size());

        List<Member> slicedMembers = members.subList(startItem, lastItem);
        log.info("get page sorted members: result={}", members);

        return new PageImpl<>(slicedMembers, PageRequest.of(currentPage, pageSize), members.size());
    }

    public Page<Member> getPagedEntities(Pageable pageable) {
        log.info("get page members -- method entered, page={}", pageable.getPageNumber());

        int currentPage = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        List<Member> members = memberRepository.findMembersWithAddress();

        List<Member> slicedMembers = members.stream().skip((long)currentPage * pageSize).limit(pageSize)
                        .collect(Collectors.toList());

        log.info("get page members: result={}", members);

        return new PageImpl<>(slicedMembers, PageRequest.of(currentPage, pageSize), members.size());
    }
}
