package com.vckadam.oopdesign.hr.test.dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.hr.dao.job.JobDao;
import com.vckadam.oopdesign.hr.dao.job.JobDaoImpl;
import com.vckadam.oopdesign.hr.model.Job;

public class JobDaoTest {
	
	private JobDao jobDao;
	
	@Before
	public void beforeMethod() throws IOException {
		this.jobDao = new JobDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.jobDao = null;
	}
	
	@Test
	public void getJobListTest() {
		List<Job> jobList = this.jobDao.getJobList();
		Set<String> actualSet = new HashSet<String>();
		for(Job job : jobList) {
			actualSet.add(job.getJobId());
		}
		
		Set<String> expectedSet = new HashSet<String>();
		expectedSet.add("AD_PRES"); expectedSet.add("AD_VP"); expectedSet.add("AD_ASST");
		expectedSet.add("FI_MGR"); expectedSet.add("FI_ACCOUNT"); expectedSet.add("AC_MGR");
		expectedSet.add("AC_ACCOUNT"); expectedSet.add("SA_MAN"); expectedSet.add("SA_REP");
		expectedSet.add("PU_MAN"); expectedSet.add("ST_CLERK"); expectedSet.add("ST_MAN");
		expectedSet.add("IT_PROG"); expectedSet.add("PU_CLERK"); expectedSet.add("SH_CLERK");
		expectedSet.add("MK_MAN"); expectedSet.add("MK_REP"); expectedSet.add("HR_REP");
		expectedSet.add("PR_REP");
		
		assertEquals(expectedSet.size(), jobList.size());
		assertEquals(expectedSet, actualSet);
	}
	
	@Test
	public void getJobByIdTest() {
		Job job = this.jobDao.getJobById("FI_MGR");
		assertEquals("Finance Manager",job.getJobTitle());
	}
}
