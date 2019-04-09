package com.engine.tools;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.engine.handle.EngineData;

import com.experian.stratman.datasources.runtime.IData;
import com.experian.stratman.decisionagent.business.DAManagementInterface;
import com.experian.stratman.decisionagent.business.DAManagementInterfaceTester;
import com.experian.stratman.decisionagent.business.OS390.Batch.BatchJSEMObjectInterface;

public class test {
	public static void main(String[] args) {
		EngineData ed0 = new EngineData("OCONTROL");//控制模块
		ed0.setValue("ALIAS", "access");
		ed0.setValue("SIGNATURE", "access");
		Map map=new HashMap();
		String str="{\"loan_name_his[1]\":\"萨摩耶—提钱花\",\"coopr_org_no_his[1]\":\"027\",\"coopr_cont_no_his[1]\":\"XMXY20170928904690\",\"is_white_cust\":\"N\",\"idno_wcloan_number\":0,\"idno_ztloan_days\":\"N\",\"id_typ\":\"20\",\"cust_name\":\"陆杨\",\"cont_sts_his[1]\":\"200\",\"loan_typ_his[1]\":\"X201701789\",\"mobile_ztloan_days\":\"N\",\"mobile_ztloan_qx\":\"N\",\"age\":29,\"crt_dt_his[1]\":\"2017-12-04\",\"is_current_yq\":\"N\",\"loan_typ\":\"X201803589\",\"idno_ztloan_jj\":\"N\",\"cont_balance_his[1]\":0,\"cont_tnr_his[1]\":\"24\",\"cont_tnr_typ_his[1]\":\"24\",\"is_inside_black\":\"N\",\"app_origin\":\"1601\",\"loan_syamt\":0,\"lmt_sts\":\"F\",\"test_id\":1253028561,\"app_origin_his[1]\":\"16\",\"appl_cde_his[1]\":\"162017120410310164036\",\"apply_amt_his[1]\":20000,\"ztloan_sts_his[1]\":\"N\",\"cont_amt_his[1]\":25000,\"apply_amt\":0,\"apply_sts_his[1]\":\"997\",\"mobile_wcloan_number\":0,\"idno_ztloan_qx\":\"N\",\"mobile_ztloan_jj\":\"N\",\"id_no\":\"620101198702230075\"}";
		map=JSONObject.parseObject(str);
		EngineData ed1 = new EngineData("input");//ServicePublish.Input);//输入
		ed1.setContents(map);
		//针对规则引擎内字段不对应部分进行修改
		//......
		
		EngineData ed2 = new EngineData("temp");//ServicePublish.Temp);//中间变量
		EngineData ed3 = new EngineData("output");//ServicePublish.Output);//输出
		
		IData[] iDatas = {ed0,ed1,ed2,ed3};
		System.out.println(JSONArray.toJSONString(iDatas));
		
	
		System.out.println("Strategy before:"+com.experian.stratman.decisionagent.business.DAManagementInterface.getLoadedStrategies());
		//System.out.println("Strategy loading:"+com.experian.stratman.decisionagent.business.DAManagementInterface.loadStrategy("access"));
//		BatchJSEMObjectInterface.instance().execute(iDatas,31);
		Map<String, Object> res = ((EngineData)iDatas[3]).getContents();
		System.out.println("Strategy after:"+com.experian.stratman.decisionagent.business.DAManagementInterface.getStrategyInfo("access"));
		System.out.println("Strategy loaded list:"+com.experian.stratman.decisionagent.business.DAManagementInterface.getLoadedStrategies());
		
		System.out.println("Strategy new loading:"+com.experian.stratman.decisionagent.business.DAManagementInterface.loadStrategy("access"));

		System.out.println("Strategy new info:"+com.experian.stratman.decisionagent.business.DAManagementInterface.getStrategyInfo("access"));
		
		System.out.println(res.toString());
	}
}
