package com.cslc.eils.gameControl.cache;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import com.cslc.eils.gameControl.pojo.Ticket;

public class ArrayListTest {

	private Vector<Ticket> v;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testAdd() {
		Integer[]  onSales = new Integer[10] ;
		System.out.println("length :"+onSales.length);
		for(int i=0;i<10;i++){
			onSales[i]=i;
		}
		System.out.println("length :"+onSales.length);
		System.out.println(onSales[5]);
		onSales[5] = 0;
		System.out.println(onSales[6]);
		System.out.println(onSales[5]);
		
		
		v = new Vector<Ticket>();
		
		for(int i=0;i<10;i++){
			Ticket Ticket = new Ticket(String.valueOf(i),"");
			v.add(Ticket);
			//System.out.println(v.get(i).getLotterySn());
		}
		
		System.out.println(v.remove(5));
		System.out.println(v.get(5).getLotterySn());
	}

}
