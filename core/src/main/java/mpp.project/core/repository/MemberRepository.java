package mpp.project.core.repository;

import mpp.project.core.model.Address;
import mpp.project.core.model.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m")
    @EntityGraph(value = "memberWithAddress", type = EntityGraph.EntityGraphType.LOAD)
    List<Member> findMembersWithAddress();

    Optional<Member> findByName(String name);
    @Query("SELECT m FROM Member m ORDER BY m.name")
    List<Member> findAllOrderByNameAsc();
}
