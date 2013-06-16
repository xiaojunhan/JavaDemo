package com.foo.app.jvm;

import java.util.ArrayList;
import java.util.List;

public class OOMObject {

	static class TestObject{
		public byte[] palaceholder = new byte[64 * 1024];
	}
	
	public static void fillHeap(int num) throws InterruptedException{
		List<TestObject> list = new ArrayList<TestObject>();
		for (int i = 0; i < num; i++) {
			Thread.sleep(100);
			list.add(new TestObject());
		}
		
		System.gc();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------");
		try {
			fillHeap(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
