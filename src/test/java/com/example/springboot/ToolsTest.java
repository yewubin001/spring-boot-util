package com.example.springboot;

import com.google.common.collect.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author yewubin
 * <p>
 * Date 2021-05-02 21:48
 */
public class ToolsTest {

    public static void main(String[] args) throws ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.out.println("----如何把list集合拼接成以逗号分隔的字符串 a,b,c");
        List<String> list = Arrays.asList("a", "b", "c");
        // 第一种方法，可以用stream流
        String join = list.stream().collect(Collectors.joining(","));
        System.out.println(join); // 输出 a,b,c
        // 第二种方法，其实String也有join方法可以实现这个功能
        String join2 = String.join(",", list);
        System.out.println(join2); // 输出 a,b,c

        System.out.println("----两个List集合取交集");
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("d");
        list1.retainAll(list2);
        System.out.println(list1); // 输出[a, b]

        System.out.println("----首字母大写");
        String str = "yideng";
        String capitalize = StringUtils.capitalize(str);
        System.out.println(capitalize); // 输出Yideng


        System.out.println("----时间类型转换");
        // Date类型转String类型
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(date); // 输出 2021-05-01 01:01:01

        // String类型转Date类型
        Date date2 = DateUtils.parseDate("2021-05-01 01:01:01", "yyyy-MM-dd HH:mm:ss");

        // 计算一个小时后的日期
        Date date3 = DateUtils.addHours(new Date(), 1);


        System.out.println("----包装临时对象");
        // 当一个方法需要返回两个及以上字段时，我们一般会封装成一个临时对象返回，现在有了Pair和Triple就不需要了
        // 返回两个字段
        ImmutablePair<Integer, String> yideng = ImmutablePair.of(1, "yideng");
        Pair<Integer, String> duanyu = Pair.of(1, "duanyu");
        System.out.println(yideng.getLeft() + "," + yideng.getRight());
        // 返回三个字段
        Triple<Integer, String, Date> triple = Triple.of(1, "yideng", new Date());
        System.out.println(triple.getLeft() + "," + triple.getMiddle() + "," + triple.getRight());

        System.out.println("----集合操作");
        // 两个集合取交集
        Collection<String> collection1 = CollectionUtils.retainAll(list1, list2);
        // 两个集合取并集
        Collection<String> collection2 = CollectionUtils.union(list1, list2);
        // 两个集合取差集
        Collection<String> collection3 = CollectionUtils.subtract(list1, list2);

        System.out.println("----对象转换");
        // 设置对象属性
        User user = new User();
        BeanUtils.setProperty(user, "id", 1);
        BeanUtils.setProperty(user, "name", "yideng");
        System.out.println(user); // 输出 {"id":1,"name":"yideng"}
        // 对象和map互转
        // 对象转map
        Map<String, String> map = BeanUtils.describe(user);
        System.out.println(map); // 输出 {"id":"1","name":"yideng"}
        // map转对象
        User newUser = new User();
        BeanUtils.populate(newUser, map);
        System.out.println(newUser); // 输出 {"id":1,"name":"yideng"}


        System.out.println("----Google Guava 工具类库");
//        List<String> list22 = Lists.newArrayList();
        List<Integer> list22 = Lists.newArrayList(1, 2, 3);
        // 反转list
        List<Integer> reverse = Lists.reverse(list22);
        System.out.println(reverse); // 输出 [3, 2, 1]
        // list集合元素太多，可以分成若干个集合，每个集合10个元素
        List<List<Integer>> partition = Lists.partition(list22, 10);

        Map<String, String> maps = Maps.newHashMap();
        Set<String> set = Sets.newHashSet();

        System.out.println("----黑科技集合");
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("key", 1);
        multimap.put("key", 2);
        Collection<Integer> values = multimap.get("key");
        System.out.println(multimap); // 输出 {"key":[1,2]}
        // 还能返回以前使用的臃肿的Map
        Map<String, Collection<Integer>> collectionMap = multimap.asMap();
        // 多省事，多简洁，省得你再创建 Map<String, List<Integer>>

        System.out.println("-----Table 一种有两个key的HashMap----");
        // 一批用户，同时按年龄和性别分组
        Table<Integer, String, String> table = HashBasedTable.create();
        table.put(18, "男", "yideng");
        table.put(18, "女", "Lily");
        System.out.println(table.get(18, "男")); // 输出 yideng
        // 这其实是一个二维的Map，可以查看行数据
        Map<String, String> row = table.row(18);
        System.out.println(row); // 输出 {"男":"yideng","女":"Lily"}
        // 查看列数据
        Map<Integer, String> column = table.column("男");
        System.out.println(column); // 输出 {18:"yideng"}


    }
}
 class User {
    private Integer id;
    private String name;

     @Override
     public String toString() {
         return "User{" +
                 "id=" + id +
                 ", name='" + name + '\'' +
                 '}';
     }
 }
