package com.experian.da;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.experian.stratman.decisionagent.business.IStrategyLoader;
import com.experian.stratman.decisionagent.runtime.DecisionAgentConfig;

/**
 * 鏈湴绛栫暐鍔犺浇鍣�
 * 涓� com.experian.stratman.decisionagent.business.AlternateStrategyLoader 鏁堟灉涓�鏍�
 * 
 * @author GuoZH
 * @version 1.0, 2016-12-27
 */
public class LocalStrategyLoader implements IStrategyLoader {
	
	private static final String STRATEGY_STRAT_PATH = "strategy.path";

	@Override
	public InputStream getStream(String strategyName) throws IOException {
		
		System.out.println("Strategy Name:"+strategyName);
		
		if(strategyName.startsWith("/")){
			strategyName = strategyName.substring(1);
		}
		
		System.out.println("Strategy Name after:"+strategyName);
		InputStream inputStream = null;
		int i = 1;
		while(true){
			String strategyPath = DecisionAgentConfig.instance().getProperty(STRATEGY_STRAT_PATH + i++, "none");
			System.out.println("Strategy path:"+strategyPath);
			
			if(strategyPath.equals("none")){
				break;
			}else{
				if(!strategyPath.endsWith("/")){
					strategyPath = strategyPath + "/";
				}
				
				System.out.println("Strategy path2:"+strategyPath);
				
				File file = new File(strategyPath, strategyName);
				
				System.out.println("Strategy here1");
				
				if(file.exists()){
					System.out.println("Strategy here2");
					inputStream = new FileInputStream(file);
					break;
				}
			}
		}
		if(inputStream == null){
			System.out.println("Strategy here3");
			throw new FileNotFoundException("File not found in all configured directories");
		}
		System.out.println("Strategy here4");
		return inputStream;
	}

}
