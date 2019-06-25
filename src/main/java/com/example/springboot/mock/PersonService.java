package com.example.springboot.mock;

/**
 * @Auther: yewub
 * @Date: 2019/2/25 14:48
 * @Description:
 */
public class PersonService {

    private final PersonDao personDao;

    public PersonService(PersonDao personDao){
        this.personDao = personDao;
    }

    public boolean update(int id, String name){
        Person person = personDao.getPerson(id);
        if(person == null){
            return false;
        }
        Person personUpdate = new Person(person.getId(), name);
        return personDao.update(personUpdate);
    }

}
