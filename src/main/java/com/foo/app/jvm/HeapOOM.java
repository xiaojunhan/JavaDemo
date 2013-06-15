package com.foo.app.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 在Run/Run Configurations.../Java Application /arguments/vm arguments 
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @author Administrator
 *
 */
public class HeapOOM {

	static class OOMObject{
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<OOMObject> list = new ArrayList<OOMObject>();
		
		while(true){
			list.add(new OOMObject());
		}
	}

}
