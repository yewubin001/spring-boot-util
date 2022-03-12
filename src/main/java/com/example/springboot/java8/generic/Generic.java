package com.example.springboot.java8.generic;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/9/8 18:35
 */
//此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定T的具体类型
public class Generic<T> {
    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey() { //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

//    public static void showKeyValue(Generic<Number> obj) {
//        System.out.println("泛型, key value is " + obj.getKey());
//    }

    //泛型方法
     public static <E extends Number> void showKeyValue(Generic<E> obj){
         System.out.println("泛型, key value is " + obj.getKey());
     }

    /**
     * 可以解决当具体类型不确定的时候，这个通配符就是 ?
     *
     * @param obj
     */
    public static void showKeyValue2(Generic<?> obj) {
        System.out.println("通配符, key value is " + obj.getKey());
    }

    public static void main(String[] args) {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer
        Generic<Integer> tGeneric = new Generic<>(1234);
         showKeyValue(tGeneric); //Generic<Integer>不能被看作为`Generic<Number>的子类
        // 为了解决上面报错问题出现了泛型通配符
        showKeyValue2(tGeneric);
    }
}
