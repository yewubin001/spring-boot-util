package com.example.springboot.mock;

/**
 * @Auther: yewub
 * @Date: 2019/2/25 14:47
 * @Description:
 */
public interface PersonDao {
    Person getPerson(int id);

    boolean update(Person person);
}
