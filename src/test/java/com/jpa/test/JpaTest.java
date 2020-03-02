package com.jpa.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jpa.test.entity.TestEntity;
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
        Optional<TestEntity> byId = testJpaRepository.findById(6L);
        log.info("通过id查找{}", byId.get());
        long count = testJpaRepository.count();
        log.info("计算总数{}", count);
    }

    /**
     * Example是由查询参数(Probe)和查询条件(ExampleMatcher)组成的
     * Probe是包含查询参数的实体
     * Example含有查询条件信息
     *
     * 注意Example不支持条件分组查询例如 AND a = ? AND (b = ? OR c = ?)
     */
    @Test
    public void testExample() {
        TestEntity testEntity = new TestEntity();
        testEntity.setLastName("朱");
        ExampleMatcher lastName = ExampleMatcher.matching().withMatcher("lastName", GenericPropertyMatchers.exact());
        Example<TestEntity> of = Example.of(testEntity, lastName);
        List<TestEntity> all = testJpaRepository.findAll(of);
        log.info("查询内容为{}", all);
    }

    @Test
    public void testPageable() {
        Page<TestEntity> all = testJpaRepository.findAll(PageRequest.of(0, 2, Sort.by(Direction.DESC, "age")));
        log.info("查询内容为{}", all.get().collect(Collectors.toList()));
    }

    /**
     * 相当于 last_name = '朱'
     */
    @Test
    public void testByLastName() {
        List<TestEntity> byLastName = testJpaRepository.findByLastName("朱");
        log.info("查询内容为{}", byLastName);
        List<TestEntity> byLastNameIs = testJpaRepository.findByLastNameIs("朱");
        log.info("查询内容为{}", byLastNameIs);
        List<TestEntity> byLastNameEquals = testJpaRepository.findByLastNameEquals("朱");
        log.info("查询内容为{}", byLastNameEquals);
    }

    /**
     * 相当于 last_name = '朱' AND first_name = '元璋'
     */
    @Test
    public void testAnd() {
        TestEntity byLastNameAndFirstName = testJpaRepository.findByLastNameAndFirstName("朱", "元璋");
        log.info("查询内容为{}", byLastNameAndFirstName);
    }

    /**
     * 相当于 last_name = '张' OR first_name = '桃红'
     */
    @Test
    public void testOr() {
        List<TestEntity> byLastNameOrFirstName = testJpaRepository.findByLastNameOrFirstName("张", "桃红");
        log.info("查询内容为{}", byLastNameOrFirstName);
    }

    /**
     * 相当于 age BETWEEN 23 AND 25, 注意范围不包含23和25本身
     */
    @Test
    public void testBetween() {
        List<TestEntity> byAgeBetween = testJpaRepository.findByAgeBetween(23, 25);
        log.info("查询内容为{}", byAgeBetween);
    }

    /**
     * 相当于 age >= 23 AND age <= 25
     */
    @Test
    public void testLteAndGte() {
        List<TestEntity> byAgeGreaterThanEqualAndAgeLessThanEqual = testJpaRepository
                .findByAgeGreaterThanEqualAndAgeLessThanEqual(23, 25);
        log.info("查询内容为{}", byAgeGreaterThanEqualAndAgeLessThanEqual);
    }

    /**
     * 相当于 email IS NULL
     */
    @Test
    public void testIsNull() {
        List<TestEntity> byEmailIsNull = testJpaRepository.findByEmailIsNull();
        log.info("查询内容为{}", byEmailIsNull);
    }

    /**
     * 相当于birthday > ? AND birthday < ?
     */
    @Test
    public void testAfterAndBefore() {
        Date after = new DateTime(2020, 2, 20, 11, 28).toDate();
        Date before = new DateTime(2020, 2, 22, 11, 28).toDate();
        List<TestEntity> byBirthdayAfterAndBirthdayBefore = testJpaRepository
                .findByBirthdayAfterAndBirthdayBefore(after, before);
        log.info("查询内容为{}", byBirthdayAfterAndBirthdayBefore);
    }

    /**
     * 相当于 age IS NOT NULL
     */
    @Test
    public void testIsNotNull() {
        List<TestEntity> byAgeIsNotNull = testJpaRepository.findByAgeIsNotNull();
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
        List<TestEntity> byFirstNameLike = testJpaRepository.findByFirstNameLike("丹");
        log.info("查询内容为{}", byFirstNameLike);
        List<TestEntity> byFirstNameStartingWith = testJpaRepository.findByFirstNameStartingWith("斯");
        log.info("查询内容为{}", byFirstNameStartingWith);
        List<TestEntity> byFirstNameEndingWith = testJpaRepository.findByFirstNameEndingWith("妮");
        log.info("查询内容为{}", byFirstNameEndingWith);
        List<TestEntity> byFirstNameContaining = testJpaRepository.findByFirstNameContaining("丹");
        log.info("查询内容为{}", byFirstNameContaining);
    }

    /**
     * 相当于 age > 12 ORDER BY age DESC , last_name DESC
     */
    @Test
    public void testOrder() {
        List<TestEntity> byAgeGreaterThanOrderByAgeDesc = testJpaRepository
                .findByAgeGreaterThanOrderByAgeDescLastNameDesc(12);
        log.info("查询内容为{}", byAgeGreaterThanOrderByAgeDesc);
    }

    /**
     * 相当于 age <> ?
     */
    @Test
    public void testNot() {
        List<TestEntity> byAgeNot = testJpaRepository.findByAgeNot(999);
        log.info("查询内容为{}", byAgeNot);
    }

    /**
     * 相当于
     * age in (? , ?)
     * age not in  (? , ?)
     */
    @Test
    public void testIn() {
        List<TestEntity> byAgeIn = testJpaRepository.findByAgeIn(Arrays.asList(new Integer[]{25, 32}));
        log.info("查询内容为{}", byAgeIn);
        List<TestEntity> byAgeNotIn = testJpaRepository.findByAgeNotIn(Arrays.asList(new Integer[]{25, 32}));
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
        List<TestEntity> byValidTrue = testJpaRepository.findByValidTrue();
        log.info("查询内容为{}", byValidTrue);
        List<TestEntity> byValidFalse = testJpaRepository.findByValidFalse();
        log.info("查询内容为{}", byValidFalse);
    }

    /**
     * 相当于
     * upper(email)=upper(?)
     */
    @Test
    public void testIgnoreCase() {
        TestEntity byEmailIgnoreCase = testJpaRepository.findByEmailIgnoreCase("lisidanni@qq.com");
        log.info("查询内容为{}", byEmailIgnoreCase);
    }

    @Test
    public void testLimit() {
        List<TestEntity> top2BySex = testJpaRepository.findTop2BySex("男");
        log.info("查询内容为{}", top2BySex);
    }
}
