package edu.pnu.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.pnu.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
