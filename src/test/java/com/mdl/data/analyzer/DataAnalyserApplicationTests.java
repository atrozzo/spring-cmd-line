package com.mdl.data.analyzer;

import com.mdl.data.analyzer.alg.MLDNumbersAnalyzer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MLDApp.class)
public class DataAnalyserApplicationTests {

	private BufferedReader br;
	private  Reader source;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private MLDNumbersAnalyzer mldNumbersAnalyzer;

	private Resource resource ;
	private BufferedReader reader;
	@InjectMocks
	private MLDApp mldApp;

	private BufferedReader noIntReader;

	@Before
	public void loadTestData(){
		InputStream is = null;
		try {
			 resource = resourceLoader.getResource("classpath:data.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			source = reader;
		} catch (IOException e) {
			e.printStackTrace();
		}
		String mockCsvData = "12.3;15.4;wqqw;";
		noIntReader = new BufferedReader(new StringReader(mockCsvData));

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldLoadJustIntegersInRange(){
		try {
			List<Integer> result = mldNumbersAnalyzer.
					getAllAvailableIntegers(mldNumbersAnalyzer.getAllAvailableIntegersPerLine(reader));
			Assert.notEmpty(result);
			Assert.isTrue(result.size() == 9);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void shouldLoadAllIntegersInMultipleLines(){
		try {
			Map<Integer, List<Integer>> allInes = mldNumbersAnalyzer.getAllAvailableIntegersPerLine(reader);
			Assert.notEmpty(allInes);
			Assert.isTrue(allInes.size() == 4 );
			Assert.isTrue((allInes.get(1).size()== 2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldLoadNone(){ // No integer values in this file
		try {
			Assert.isTrue(mldNumbersAnalyzer.getAllAvailableIntegersPerLine(noIntReader).size()==0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}





}
