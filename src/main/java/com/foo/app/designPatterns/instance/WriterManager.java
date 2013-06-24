package com.foo.app.designPatterns.instance;
/**
 * 当单例模式使用内部类维护单例的实例，当WriterManager被加载时候其内部类不会被初始化，故可以确保当
 * WriterManager类被载入JVM时，不会初始化单例类，而当getInstance方法被调用时，才会加载WriterManagerInstance
 * ，从而初始化instance。
 * 使用内部类的方式实现单例，既可以做到延迟加载，也不必使用同步关键字，是一种比较完善的实现。
 * @author Administrator
 *
 */
public class WriterManager{
	
    private WriterManager(){}  
      
    public static WriterManager getInstance(){  
        return WriterManagerInstance.instance;  
    }  
   /**
    * 
    * @author Administrator
    *
    */
    private static class WriterManagerInstance{  
          
        private static WriterManager instance = new WriterManager();  
          
    } 
}
