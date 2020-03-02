package com.jpa.test.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.test.entity.TestEntity;

/**
 * No Description
 * @author GaoZhilai
 * @date 2020/2/20 15:11
 */
public interface TestJpaRepository extends JpaRepository<TestEntity, Long> {

    List<TestEntity> findByLastName(String lastName);

    List<TestEntity> findByLastNameIs(String lastName);

    List<TestEntity> findByLastNameEquals(String lastName);

    TestEntity findByLastNameAndFirstName(String lastName, String firstName);

    List<TestEntity> findByLastNameOrFirstName(String lastName, String firstName);

    List<TestEntity> findByAgeBetween(Integer start, Integer end);

    List<TestEntity> findByAgeGreaterThanEqualAndAgeLessThanEqual(Integer start, Integer end);

    List<TestEntity> findByEmailIsNull();

    List<TestEntity> findByBirthdayAfterAndBirthdayBefore(Date after, Date before);

    List<TestEntity> findByAgeIsNotNull();

    List<TestEntity> findByFirstNameLike(String keyword);

    List<TestEntity> findByFirstNameStartingWith(String keyword);

    List<TestEntity> findByFirstNameEndingWith(String keyword);

    List<TestEntity> findByFirstNameContaining(String keyword);

    List<TestEntity> findByAgeGreaterThanOrderByAgeDescLastNameDesc(Integer greater);

    List<TestEntity> findByAgeNot(Integer not);

    List<TestEntity> findByAgeIn(List<Integer> ages);

    List<TestEntity> findByAgeNotIn(List<Integer> ages);

    List<TestEntity> findByValidTrue();

    List<TestEntity> findByValidFalse();

    TestEntity findByEmailIgnoreCase(String email);

    List<TestEntity> findTop2BySex(String sex);
}
