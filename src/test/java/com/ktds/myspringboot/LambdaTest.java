package com.ktds.myspringboot;

import com.ktds.myspringboot.dto.Customer;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Consumer;

public class LambdaTest {
    @Test
    void runnable(){
        //Anonymous Inner class
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Inner class");;
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> System.out.println("t1 = " + t1));
        t2.start();
    }

    @Test
    void consumer(){
        List<String> stringList = List.of("JAVA", "Python", "Kotlin") ; // Immutable List
        // stringList.add("bbb"); 에러 발생

        stringList.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s = " + s);
            }
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return 0;
            }
        });

        PriorityQueue<int[]> pq2 = new PriorityQueue<>((o1, o2) -> o1[1] == o2[1]?1:-1);



        //lambda

        stringList.forEach(s -> System.out.println("stringList = " + s));
        stringList.forEach(System.out::print);


    }


    @Test
    void consumer_test() {
        //Immutable List
        List<Customer> customerList =
                List.of(new Customer("boot", 10),
                        new Customer("msa", 20),
                        new Customer("ktds", 50),
                        new Customer("wifi", 70));

        //1. Anonymous Inner Class
        customerList.forEach(new Consumer<Customer>() {
            @Override
            public void accept(Customer customer) {
                System.out.println(customer);
            }
        });
        //2. Lambda Expression
        customerList.forEach(cust -> System.out.println(cust));
        //3. Method Reference
        customerList.forEach(System.out::println);

        //Customer의 age 합계를 계산하기 (age >= 50)
        int sumOfAge =
                customerList.stream() //Stream<Customer>
                        .filter(customer -> customer.getAge() >= 50) //Stream<Customer>
                        //.mapToInt(customer -> customer.getAge()) //Stream<Integer>
                        .mapToInt(Customer::getAge)//IntStream
                        .sum();
        System.out.println("나이 합계 " + sumOfAge);

        int asInt = customerList.stream().mapToInt(value -> value.getAge())
                .max()
                .getAsInt();

        System.out.println("asInt = " + asInt);


    }
}
