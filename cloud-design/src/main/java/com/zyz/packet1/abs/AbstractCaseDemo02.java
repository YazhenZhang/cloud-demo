package com.zyz.packet1.abs;

abstract class Person{
    private String name ;       // 定义name属性
    private int age ;           // 定义age属性
    public Person(String name,int age){
        this.name = name ;
        this.age = age ;
    }
    public String getName(){
        return this.name ;
    }
    public int getAge(){
        return this.age ;
    }
    public void say(){      // 人说话是一个具体的功能
        System.out.println(this.getContent()) ; // 输出内容
    }
    public abstract String getContent() ;   // 说话的内容由子类决定
}

class Student extends Person{
    private float score ;
    public Student(String name,int age,float score){
        super(name,age) ;   // 调用父类中的构造方法
        this.score = score ;
    }
    @Override
    public String getContent(){
        return  "学生信息 --> 姓名：" + super.getName() + 
                "；年龄：" + super.getAge() + 
                "；成绩：" + this.score ;
    }
}

class Worker extends Person{
    private float salary ;
    public Worker(String name,int age,float salary){
        super(name,age) ;   // 调用父类中的构造方法
        this.salary = salary ;
    }
    @Override
    public String getContent(){
        return  "工人信息 --> 姓名：" + super.getName() + 
                "；年龄：" + super.getAge() + 
                "；工资：" + this.salary ;
    }
}

public class AbstractCaseDemo02{
    public static void main(String args[]){
        Person per1 = null ;    // 声明Person对象
        Person per2 = null ;    // 声明Person对象
        per1 = new Student("张三",20,99.0f) ; // 学生是一个人
        per2 = new Worker("李四",30,3000.0f) ;    // 工人是一个人
        per1.say() ;    // 学生说学生的话
        per2.say() ;    // 工人说工人的话
    }
}