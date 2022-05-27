package mpp.project.core.repository;

import mpp.project.core.model.Address;
import mpp.project.core.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
