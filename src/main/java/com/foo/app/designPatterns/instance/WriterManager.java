package com.foo.app.designPatterns.instance;

public class WriterManager{
	//单例  
    private WriterManager(){}  
      
    public static WriterManager getInstance(){  
        return WriterManagerInstance.instance;  
    }  
    private static class WriterManagerInstance{  
          
        private static WriterManager instance = new WriterManager();  
          
    } 
}
