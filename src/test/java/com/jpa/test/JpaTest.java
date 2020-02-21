package com.jpa.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa.test.repository.TestJpaRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * JPA常见语法测试
 * @author GaoZhilai
 * @date 2020/2/20 15:10
 */
@SpringBootTest
@Slf4j
public class JpaTest {

    @Resource
    private TestJpaRepository testJpaRepository;

    @Test
    public void testJpa() {
        Optional<com.jpa.test.entity.JpaTest> byId = testJpaRepository.findById(6L);
        log.info("查询内容为{}", byId.get());
        long count = testJpaRepository.count();
        log.info("查询内容为{}", count);
    }

    /**
     * 相当于 last_name = '朱'
     */
    @Test
    public void testByLastName() {
        List<com.jpa.test.entity.JpaTest> byLastName = testJpaRepository.findByLastName("朱");
        log.info("查询内容为{}", byLastName);
        List<com.jpa.test.entity.JpaTest> byLastNameIs = testJpaRepository.findByLastNameIs("朱");
        log.info("查询内容为{}", byLastNameIs);
        List<com.jpa.test.entity.JpaTest> byLastNameEquals = testJpaRepository.findByLastNameEquals("朱");
        log.info("查询内容为{}", byLastNameEquals);
    }

    /**
     * 相当于 last_name = '朱' AND first_name = '元璋'
     */
    @Test
    public void testAnd() {
        com.jpa.test.entity.JpaTest byLastNameAndFirstName = testJpaRepository.findByLastNameAndFirstName("朱", "元璋");
        log.info("查询内容为{}", byLastNameAndFirstName);
    }

    /**
     * 相当于 last_name = '张' OR first_name = '桃红'
     */
    @Test
    public void testOr() {
        List<com.jpa.test.entity.JpaTest> byLastNameOrFirstName = testJpaRepository.findByLastNameOrFirstName("张", "桃红");
        log.info("查询内容为{}", byLastNameOrFirstName);
    }

    /**
     * 相当于 age BETWEEN 23 AND 25, 注意范围不包含23和25本身
     */
    @Test
    public void testBetween() {
        List<com.jpa.test.entity.JpaTest> byAgeBetween = testJpaRepository.findByAgeBetween(23, 25);
        log.info("查询内容为{}", byAgeBetween);
    }

    /**
     * 相当于 age >= 23 AND age <= 25
     */
    @Test
    public void testLteAndGte() {
        List<com.jpa.test.entity.JpaTest> byAgeGreaterThanEqualAndAgeLessThanEqual = testJpaRepository
                .findByAgeGreaterThanEqualAndAgeLessThanEqual(23, 25);
        log.info("查询内容为{}", byAgeGreaterThanEqualAndAgeLessThanEqual);
    }

    /**
     * 相当于 email IS NULL
     */
    @Test
    public void testIsNull() {
        List<com.jpa.test.entity.JpaTest> byEmailIsNull = testJpaRepository.findByEmailIsNull();
        log.info("查询内容为{}", byEmailIsNull);
    }

    /**
     * 相当于birthday > ? AND birthday < ?
     */
    @Test
    public void testAfterAndBefore() {
        Date after = new DateTime(2020, 2, 20, 11, 28).toDate();
        Date before = new DateTime(2020, 2, 22, 11, 28).toDate();
        List<com.jpa.test.entity.JpaTest> byBirthdayAfterAndBirthdayBefore = testJpaRepository
                .findByBirthdayAfterAndBirthdayBefore(after, before);
        log.info("查询内容为{}", byBirthdayAfterAndBirthdayBefore);
    }

    /**
     * 相当于 age IS NOT NULL
     */
    @Test
    public void testIsNotNull() {
        List<com.jpa.test.entity.JpaTest> byAgeIsNotNull = testJpaRepository.findByAgeIsNotNull();
        log.info("查询内容为{}", byAgeIsNotNull);
    }

    /**
     * 相当于
     * first_name like '丹'
     * first_name like '斯%'
     * first_name like '%妮'
     * first_name like '%丹%'
     */
    @Test
    public void testLike() {
        List<com.jpa.test.entity.JpaTest> byFirstNameLike = testJpaRepository.findByFirstNameLike("丹");
        log.info("查询内容为{}", byFirstNameLike);
        List<com.jpa.test.entity.JpaTest> byFirstNameStartingWith = testJpaRepository.findByFirstNameStartingWith("斯");
        log.info("查询内容为{}", byFirstNameStartingWith);
        List<com.jpa.test.entity.JpaTest> byFirstNameEndingWith = testJpaRepository.findByFirstNameEndingWith("妮");
        log.info("查询内容为{}", byFirstNameEndingWith);
        List<com.jpa.test.entity.JpaTest> byFirstNameContaining = testJpaRepository.findByFirstNameContaining("丹");
        log.info("查询内容为{}", byFirstNameContaining);
    }

    /**
     * 相当于 age > 12 ORDER BY age DESC , last_name DESC
     */
    @Test
    public void testOrder() {
        List<com.jpa.test.entity.JpaTest> byAgeGreaterThanOrderByAgeDesc = testJpaRepository
                .findByAgeGreaterThanOrderByAgeDescLastNameDesc(12);
        log.info("查询内容为{}", byAgeGreaterThanOrderByAgeDesc);
    }

    /**
     * 相当于 age <> ?
     */
    @Test
    public void testNot() {
        List<com.jpa.test.entity.JpaTest> byAgeNot = testJpaRepository.findByAgeNot(999);
        log.info("查询内容为{}", byAgeNot);
    }

    /**
     * 相当于
     * age in (? , ?)
     * age not in  (? , ?)
     */
    @Test
    public void testIn() {
        List<com.jpa.test.entity.JpaTest> byAgeIn = testJpaRepository.findByAgeIn(Arrays.asList(new Integer[]{25, 32}));
        log.info("查询内容为{}", byAgeIn);
        List<com.jpa.test.entity.JpaTest> byAgeNotIn = testJpaRepository.findByAgeNotIn(Arrays.asList(new Integer[]{25, 32}));
        log.info("查询内容为{}", byAgeNotIn);
    }

    /**
     * 测试 = true 和 != true 条件, 注意null不等同于false
     * 相当于
     * valid=1
     * valid=0
     */
    @Test
    public void testTrue() {
        List<com.jpa.test.entity.JpaTest> byValidTrue = testJpaRepository.findByValidTrue();
        log.info("查询内容为{}", byValidTrue);
        List<com.jpa.test.entity.JpaTest> byValidFalse = testJpaRepository.findByValidFalse();
        log.info("查询内容为{}", byValidFalse);
    }

    /**
     * 相当于
     * upper(email)=upper(?)
     */
    @Test
    public void testIgnoreCase() {
        com.jpa.test.entity.JpaTest byEmailIgnoreCase = testJpaRepository.findByEmailIgnoreCase("lisidanni@qq.com");
        log.info("查询内容为{}", byEmailIgnoreCase);
    }
}
