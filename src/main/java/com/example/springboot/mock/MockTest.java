package com.example.springboot.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @Auther: yewub
 * @Date: 2019/2/25 16:26
 * @Description:
 */
public class MockTest {

    @Mock
    private List<String> mockList;

    @Before
    public void setUp() {
        //必须,否则注解无效
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock() {
        mockList.add("one");

        verify(mockList).add("one");
    }



    /**
     * doReturn()|doThrow()| doAnswer()|doNothing()|doCallRealMethod()等用法
     */
    @Test
    public void testDoxxx(){
        LinkedList mockList = mock(LinkedList.class);

        doThrow(new RuntimeException()).when(mockList).clear();

        mockList.clear();
    }
}
