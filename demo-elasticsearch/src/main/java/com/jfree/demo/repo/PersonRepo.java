package com.jfree.demo.repo;

import com.jfree.demo.entity.Person;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PersonRepo extends ElasticsearchRepository<Person, Long> {

    List<Person> findBySex(String sex);

}
