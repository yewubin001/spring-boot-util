package com.example.springboot.java8.optional;

import org.junit.Test;

import java.util.*;

/**
 *
 */
public class OptionalTest {

    /**
     * of: 为非null的值创建一个Optional
     */
    @Test
    public void of() {
        Optional.of("1234");
        //报错 NullPointerException
        Optional.of(null);
    }

    /**
     * ofNullable：为指定的值创建一个Optional，如果指定的值为null，则返回一个空的Optional。
     */
    @Test
    public void ofNullable() {
        Optional.ofNullable("1234");
        Optional.ofNullable(null);
    }

    /**
     * isPresent: 如果值存在返回true，否则返回false。
     */
    @Test
    public void isPresent() {
        Optional<String> optional = Optional.of("1234");
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
    }

    /**
     * get: 如果Optional有值则将其返回，否则抛出NoSuchElementException。
     */
    @Test
    public void get() {
        //同上
    }

    /**
     * 存在即返回, 无则提供默认值
     */
    @Test
    public void test() {
        Optional<User> optional = Optional.of(new User("yewubin", "123456"));
        System.out.println(optional.orElse(null));
    }

    /**
     * ifPresent:
     * 如果Optional实例有值则为其调用consumer，否则不做处理
     * 此处不可以有返回值
     */
    @Test
    public void ifPresent() {
        Optional<User> optional = Optional.of(new User("yewubin", "123456"));
        optional.ifPresent(System.out::println);
        optional.ifPresent(s -> s.setName("guikaili"));
        System.out.println(optional.get().getName());
    }

    /**
     * orElse:
     * 如果有值则将其返回，否则返回指定的其它值。
     */
    @Test
    public void orElse() {
        Optional<String> name = Optional.empty();
        System.out.println(name.orElse("There is some value!"));
    }

    /**
     * orElseGet
     * orElseGet与orElse方法类似，区别在于得到的默认值。orElse方法将传入的字符串作为默认值，
     * orElseGet方法可以接受Supplier接口的实现用来生成默认值。
     */
    @Test
    public void orElseGet() {
        Optional<String> name = Optional.empty();
        System.out.println(name.orElseGet(() -> "Sanaulla".toUpperCase()));
    }

    /**
     * orElseThrow:
     * 如果有值则将其返回，否则抛出supplier接口创建的异常。
     */
    public void orElseThrow() {

    }

    /**
     * map
     * 如果有值，则对其执行调用mapping函数得到返回值。
     * 如果返回值不为null，则创建包含mapping返回值的Optional作为map方法返回值，否则返回空Optional。
     */
    @Test
    public void map() {
        Optional<User> optional = Optional.of(new User("yewubin", "123456"));
        List<String> list = optional.map(User::getOrders).orElse(Collections.emptyList());
        System.out.println(list);

        System.out.println("----------------");
        String name = optional.map(User::getName).map(n -> n.toUpperCase()).orElse(null);
        System.out.println(name);

    }

    /**
     * flatMap:
     * 如果有值，为其执行mapping函数返回Optional类型返回值，否则返回空Optional。
     * flatMap与map（Funtion）方法类似，区别在于flatMap中的mapper返回值必须是Optional。调用结束时，flatMap不会对结果用Optional封装。
     * <p>
     * 说明：flatMap方法与map方法类似，区别在于mapping函数的返回值不同。map方法的mapping函数返回值可以是任何类型T，
     * 而flatMap方法的mapping函数必须是Optional。
     */
    @Test
    public void flatMap() {
        Optional<String> upperName = Optional.of("yewubin");
        upperName = upperName.flatMap(value -> Optional.of(value.toUpperCase()));
        System.out.println(upperName.orElse("No value found"));
    }


    /**
     * filter:
     * 如果有值并且满足断言条件返回包含该值的Optional，否则返回空Optional。
     */
    @Test
    public void filter() {
        Optional<String> name = Optional.of("叶物滨VS科比");
        Optional<String> longName = name.filter((value) -> value.length() > 6);
        //输出Sanaulla
        System.out.println(longName.orElse("The name is less than 6 characters"));
    }

    /**
     * 对象::实例方法
     * 类::静态方法
     * 类::实例方法
     */


    class User {
        private String name;
        private String password;
        private List<String> orders = Arrays.asList("dowork", "running");

        public User(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<String> getOrders() {
            return orders;
        }

        public void setOrders(List<String> orders) {
            this.orders = orders;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", orders=" + orders +
                    '}';
        }
    }
}
