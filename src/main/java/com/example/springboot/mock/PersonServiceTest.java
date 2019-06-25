package com.example.springboot.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

/**
 * @Auther: yewub
 * @Date: 2019/2/25 15:01
 * @Description:
 */
public class PersonServiceTest {
    private PersonDao mockDao;
    private PersonService personService;

    @Before
    public void setUp() {
        //模拟PersonDao对象
        mockDao = mock(PersonDao.class);
        Person person = new Person(1, "Person1");
        //doReturn(person).when(mockDao).getPerson(anyInt());
        //doReturn(true).when(mockDao).update(isA(Person.class));
        when(mockDao.getPerson(anyInt())).thenReturn(person);
        when(mockDao.update(isA(Person.class))).thenReturn(true);
        personService = new PersonService(mockDao);
    }

    @Test
    public void testUpdate() {
        boolean result = personService.update(1, "new name");
        assertTrue("must true", result);

        //验证是否执行过一次getPerson(1)
        verify(mockDao, times(1)).getPerson(eq(1));
        //验证是否执行过一次update
        verify(mockDao, times(1)).update(isA(Person.class));
    }

    @Test
    public void testUpdateNotFind() {
        boolean result = personService.update(2, "new name");
        assertFalse("must true", result);

        //验证是否执行过一次getPerson(1)
        verify(mockDao, times(0)).getPerson(eq(1));
        //验证是否执行过一次update
        verify(mockDao, times(0)).update(isA(Person.class));
    }

    @Test
    public void testStubbing() {
        List mockList = mock(ArrayList.class);

        mockList.add("one");
        mockList.add("two");
        mockList.add("two");
        mockList.clear();
        //验证是否调用过一次 mockedList.add("one")方法，若不是（0次或者大于一次），测试将不通过
        verify(mockList).add("one");
        //验证调用过2次 mockedList.add("two")方法，若不是，测试将不通过
        verify(mockList, times(2)).add("two");
        //验证是否调用过一次 mockedList.clear()方法，若没有（0次或者大于一次），测试将不通过
        verify(mockList, times(1)).clear();
    }

    @Test
    public void testStubbing2() {
        List mockList = mock(ArrayList.class);

        when(mockList.get(0)).thenReturn("first");
        when(mockList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockList.get(0));
        System.out.println(mockList.get(1));
        System.out.println(mockList.get(999));

        verify(mockList).get(0);
    }

    @Test
    public void testArgumentMatcher() {
        LinkedList mockList = mock(LinkedList.class);

        when(mockList.get(anyInt())).thenReturn("element");

        System.out.println(mockList.get(999));
        //你也可以用参数匹配器来验证，此处测试通过
        verify(mockList).get(anyInt());
        //此处测试将不通过，因为没调用get(33)
        verify(mockList).get(333);
    }

    @Test
    public void testVoidMethodsWithExceptions() {
        LinkedList mockList = mock(LinkedList.class);

        doThrow(new RuntimeException()).when(mockList).clear();

        //下面会抛RuntimeException
        mockList.clear();
    }

}
