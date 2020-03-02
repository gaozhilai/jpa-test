package com.jpa.test.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Data;

/**
 * No Description
 * @author GaoZhilai
 * @date 2020/2/20 15:15
 */

@Data
@Entity
@Table(name = "test_entity")
public class TestEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String sex;
    private String email;
    private Integer age;
    private Boolean valid;
    private Date birthday;
    @Version
    private int version;
}
