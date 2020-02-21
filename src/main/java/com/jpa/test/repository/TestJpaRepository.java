package com.jpa.test.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.test.entity.JpaTest;

/**
 * No Description
 * @author GaoZhilai
 * @date 2020/2/20 15:11
 */
public interface TestJpaRepository extends JpaRepository<JpaTest, Long> {

    List<JpaTest> findByLastName(String lastName);

    List<JpaTest> findByLastNameIs(String lastName);

    List<JpaTest> findByLastNameEquals(String lastName);

    JpaTest findByLastNameAndFirstName(String lastName, String firstName);

    List<JpaTest> findByLastNameOrFirstName(String lastName, String firstName);

    List<JpaTest> findByAgeBetween(Integer start, Integer end);

    List<JpaTest> findByAgeGreaterThanEqualAndAgeLessThanEqual(Integer start, Integer end);

    List<JpaTest> findByEmailIsNull();

    List<JpaTest> findByBirthdayAfterAndBirthdayBefore(Date after, Date before);

    List<JpaTest> findByAgeIsNotNull();

    List<JpaTest> findByFirstNameLike(String keyword);

    List<JpaTest> findByFirstNameStartingWith(String keyword);

    List<JpaTest> findByFirstNameEndingWith(String keyword);

    List<JpaTest> findByFirstNameContaining(String keyword);

    List<JpaTest> findByAgeGreaterThanOrderByAgeDescLastNameDesc(Integer greater);

    List<JpaTest> findByAgeNot(Integer not);

    List<JpaTest> findByAgeIn(List<Integer> ages);

    List<JpaTest> findByAgeNotIn(List<Integer> ages);

    List<JpaTest> findByValidTrue();

    List<JpaTest> findByValidFalse();

    JpaTest findByEmailIgnoreCase(String email);
}
