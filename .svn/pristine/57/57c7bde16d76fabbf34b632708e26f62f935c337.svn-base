package com.cslc.eils.gameControl.cache;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.entity.TTicketImported;

public class OnSalePoolTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testAddAll() {
		InitSystem.loadLog4jProps();
		OnSalePool OnSalePool = new OnSalePool(100,"test");
		List<TTicketImported> tickets = new ArrayList<TTicketImported>();
		TTicketImported TTicketImported = new TTicketImported();
		TTicketImported.setGroupSn(100001l);
		TTicketImported.setTicketContent("output");
		for(int i= 0;i<10;i++)
			tickets.add(TTicketImported);
		OnSalePool.addAll(tickets);
	}

}
