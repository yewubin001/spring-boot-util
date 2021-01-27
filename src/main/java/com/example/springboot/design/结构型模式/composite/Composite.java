package com.example.springboot.design.结构型模式.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/11/11 16:37
 */
public class Composite extends Component {

    /**
     * 示意属性，组件的名字
     */
    private String name;

    public Composite(String name) {
        this.name = name;
    }

    private List<Component> childComponents = null;

    @Override
    public void someOperation(String preStr) {
        // 先把自己输出
        System.out.println(preStr + "+" + name);

        if(childComponents != null){
            // 添加一个空格，表示向后缩进一个空格
            preStr += "   ";
            // 输出当前对象的子组件对象
            for (Component component : childComponents) {
                // 递归地进行子组件相应方法的调用，输出每个子组件对象
                component.someOperation(preStr);
            }
        }


    }

    @Override
    public void addChild(Component child) {
        // 延迟初始化
        if (null == childComponents) {
            childComponents = new ArrayList<>();
        }
        childComponents.add(child);
    }

    @Override
    public void removeChild(Component child) {
        if (null != childComponents){
            Iterator<Component> iterator = childComponents.iterator();
            while (iterator.hasNext()) {
                Component next = iterator.next();
                if(next == child) {
                    iterator.remove();
                }
            }
            iterator.remove();
        }
    }

    @Override
    public Component getChildren(int index) {
        if (null != childComponents) {
            if (index >= 0 && index < childComponents.size()) {
                return childComponents.get(index);
            }
        }
        return null;
    }
}
