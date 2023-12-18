package com.example.hongpark.repository;

import com.example.hongpark.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
}
