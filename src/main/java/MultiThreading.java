import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class Customer {

    private String id;
    private String name;
    private String mobile;
    private int salary;


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }



    public Customer(String id, String name, String mobile, int salary) {
        System.out.println("Customer Created Fully " +Thread.currentThread());
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.salary = salary;
    }

    public Customer() {
        System.out.println("Customer Created Empty " +Thread.currentThread());
    }



    @SneakyThrows
    public void buildMobile() {
        System.out.println("buildMobile called " +Thread.currentThread());
        Thread.sleep(2000);
        mobile="0524044665";
    }


    @SneakyThrows
    public void buildName() {
        System.out.println("buildName called " +Thread.currentThread());
        Thread.sleep(2000);
        name="ahmad";
    }


    @SneakyThrows
    public void buildId() {
        System.out.println("buildId called " +Thread.currentThread());
        Thread.sleep(2000);
        id="066778989";
    }

    @SneakyThrows
    public String printCustomer() {
        System.out.println ("ID:"+id+" name:"+name+" mobile:"+mobile+" salary:"+salary);
        return ("ID:"+id+" name:"+name+" mobile:"+mobile);
    }






}

public class MultiThreading {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long startDate= Calendar.getInstance().getTimeInMillis();

        List<CompletableFuture> allLists= new ArrayList<>();
        Customer customer= new Customer();

        /** single thread
         customer.buildId();
         customer.buildName();
         customer.buildMobile();
         */

        //multi thread
        allLists.add(CompletableFuture.runAsync(customer::buildMobile));
        allLists.add(CompletableFuture.runAsync(customer::buildId));
        allLists.add(CompletableFuture.runAsync(customer::buildName));


        CompletableFuture.allOf(allLists.toArray(new CompletableFuture[allLists.size()])).get();


        
        Long endDate=Calendar.getInstance().getTimeInMillis();
        System.out.println(Thread.currentThread()+" Customer"+ customer.printCustomer() +" executed time :" + (endDate.longValue()-startDate.longValue()));

    }
}
