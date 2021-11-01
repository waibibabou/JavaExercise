package generic;

import java.util.ArrayList;
import java.util.List;

public class Order<T> {
    String name;
    int orderId;
    T orderT;

    public Order() {
    }

    public Order(String name, int orderId, T orderT) {
        this.name = name;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }

    public <E>List<E> copy(E[] a){
        List<E> list=new ArrayList<>();
        for(int i=0;i<a.length;i++){
            list.add(a[i]);
        }
        return list;
    }

}
