package com.example.springboot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * ArrayList 删除元素正确姿势
 *
 * @Author yewubin
 * Date 2022-05-03 19:17
 */
public class ArrayListDelete {

    public static void main(String[] args) {

        Predicate<String> predicate = p -> p.equals("a") || p.equals("b");

        // 可以正常删除结果正确
        deleteByIterator(getList(), predicate);

        // 可以正常删除结果正确
        deleteByReverseOrder(getList(), predicate);

        // 可以删除 结果不正确
        deleteByOrder(getList(), predicate);

        // 不能删除 报错java.util.ConcurrentModificationException
        deleteByArrayList(getList(), predicate);

        // 不能删除 报错java.util.ConcurrentModificationException
        deleteByForeach(getList(), predicate);

        //不能删除 报错 java.util.ConcurrentModificationException
        deleteByEnhancedForLoop(getList(), predicate);

        // 正常删除数据
        deleteAll(getList(), predicate);

    }

    public static List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("c");
        return list;
    }

    /**
     * 迭代器循环，使用迭代器的remove()方法删除
     * 可以正常删除结果正确
     *
     * @param list
     * @param predicate
     */
    public static void deleteByIterator(List<String> list, Predicate<String> predicate) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
                iterator.remove();
            }
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());
    }

    /**
     * 普通for循环倒序删除
     * 可以正常删除  结果正确
     *
     * @param list
     * @param predicate
     */
    public static void deleteByReverseOrder(List<String> list, Predicate<String> predicate) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (predicate.test(list.get(i))) {
                list.remove(list.get(i));
            }
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());
    }

    /**
     * 普通for循环正序删除
     * 可以删除  结果不正确 会遗漏连续删除的元素。删除完需要让索引下标往前一位  i--;
     *
     * 删除元素'a'，当循环到下标为0的元素的的时候，发现此位置上的元素是'a'，此处元素应该删除，
     * 在删除元素后面的所有元素都要向前移动一个位置，那么移动之后，原来下标为1的元素'b'，此时下标为0，这是在i = 0,时的循环操作，
     * 在下一次的循环中，i = 1，此时就遗漏了第二个元素'b'。
     *
     * System.arraycopy(elementData, index+1, elementData, index, numMoved);
     *
     * @param list
     * @param predicate
     */

    public static void deleteByOrder(List<String> list, Predicate<String> predicate) {
        for (int i = 0; i < list.size(); i++) {
            if (predicate.test(list.get(i))) {
                list.remove(list.get(i));
            }
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());
    }


    /**
     * 迭代器循环，使用ArrayList的remove()方法删除
     * 用Iterator循环删除的时候，调用的是ArrayList里面的remove方法，删除元素后modCount会增加，
     * expectedModCount则不变，这样就造成了expectedModCount ！= modCount，那么就抛出异常了。
     *
     * modCount：用于存储结构修改次数（增删改）
     *
     * @param list
     * @param predicate
     */
    public static void deleteByArrayList(List<String> list, Predicate<String> predicate) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
                list.remove(iterator.next());
            }
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());
    }



    /**
     * java8 forEach方法删除
     *
     * foreach原理是因为这些集合类都实现了Iterable接口，该接口中定义了Iterator迭代器的产生方法，
     * 并且foreach就是通过Iterable接口在序列中进行移动。
     *
     * @param list
     * @param predicate
     */
    public static void deleteByForeach(List<String> list, Predicate<String> predicate) {
        list.forEach(p -> {
            if (predicate.test(p)) {
                list.remove(p);
            }
        });
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());
    }

    /**
     * 增强版for循环删除

     * 在编译的时候编译器会自动将对for这个关键字的使用转化为对目标的迭代器的使用,
     * 明白了原理就跟上述的Iterator删除调用ArrayList中remove一样了。
     *
     * @param list
     * @param predicate
     */
    public static void deleteByEnhancedForLoop(List<String> list, Predicate<String> predicate) {
        for (String string : list) {
            if (predicate.test(string)) {
                list.remove(string);
            }
        }
    }

    /**
     * 调用批量删除方法
     *
     * @param list
     * @param predicate
     */
    public static void deleteAll(List<String> list, Predicate<String> predicate) {
        List<String> removeList = new ArrayList<>();
        for (String string : list) {
            if (predicate.test(string)) {
                removeList.add(string);
            }
        }
        list.removeAll(removeList);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());
    }
}