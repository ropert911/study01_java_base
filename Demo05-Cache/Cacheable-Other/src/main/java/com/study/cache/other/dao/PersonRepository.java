package com.study.cache.other.dao;

import com.study.cache.other.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
	

}
