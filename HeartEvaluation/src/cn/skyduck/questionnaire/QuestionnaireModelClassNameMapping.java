package cn.skyduck.questionnaire;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.model.StrategyClassNameMappingBase;
import cn.skyduck.questionnaire.questionnaire.ADL.QuestionnaireModelOfADL;
import cn.skyduck.questionnaire.questionnaire.AIS.QuestionnaireModelOfAIS;
import cn.skyduck.questionnaire.questionnaire.ASLEC.QuestionnaireModelOfASLEC;
import cn.skyduck.questionnaire.questionnaire.BAI.QuestionnaireModelOfBAI;
import cn.skyduck.questionnaire.questionnaire.BDI.QuestionnaireModelOfBDI;
import cn.skyduck.questionnaire.questionnaire.BPRS.QuestionnaireModelOfBPRS;
import cn.skyduck.questionnaire.questionnaire.BRMS.QuestionnaireModelOfBRMS;
import cn.skyduck.questionnaire.questionnaire.BSSD.QuestionnaireModelOfBSSD;
import cn.skyduck.questionnaire.questionnaire.CBCL.QuestionnaireModelOfCBCL;
import cn.skyduck.questionnaire.questionnaire.CES_D.QuestionnaireModelOfCES_D;
import cn.skyduck.questionnaire.questionnaire.CIH.QuestionnaireModelOfCIH;
import cn.skyduck.questionnaire.questionnaire.CMI.QuestionnaireModelOfCMI;
import cn.skyduck.questionnaire.questionnaire.CPRS.QuestionnaireModelOfCPRS;
import cn.skyduck.questionnaire.questionnaire.DSQ.QuestionnaireModelOfDSQ;
import cn.skyduck.questionnaire.questionnaire.EMBU.QuestionnaireModelOfEMBU;
import cn.skyduck.questionnaire.questionnaire.ENRICH.QuestionnaireModelOfENRICH;
import cn.skyduck.questionnaire.questionnaire.EPQ_Adults.QuestionnaireModelOfEPQ_Adults;
import cn.skyduck.questionnaire.questionnaire.EPQ_Children.QuestionnaireModelOfEPQ_Children;
import cn.skyduck.questionnaire.questionnaire.ESLI.QuestionnaireModelOfESLI;
import cn.skyduck.questionnaire.questionnaire.FES.QuestionnaireModelOfFES;
import cn.skyduck.questionnaire.questionnaire.FS_14.QuestionnaireModelOfFS_14;
import cn.skyduck.questionnaire.questionnaire.GDS.QuestionnaireModelOfGDS;
import cn.skyduck.questionnaire.questionnaire.GWB.QuestionnaireModelOfGWB;
import cn.skyduck.questionnaire.questionnaire.HAMA.QuestionnaireModelOfHAMA;
import cn.skyduck.questionnaire.questionnaire.HAMD.QuestionnaireModelOfHAMD;
import cn.skyduck.questionnaire.questionnaire.HDS.QuestionnaireModelOfHDS;
import cn.skyduck.questionnaire.questionnaire.HIS.QuestionnaireModelOfHIS;
import cn.skyduck.questionnaire.questionnaire.HPII.QuestionnaireModelOfHPII;
import cn.skyduck.questionnaire.questionnaire.ITS.QuestionnaireModelOfITS;
import cn.skyduck.questionnaire.questionnaire.LES.QuestionnaireModelOfLES;
import cn.skyduck.questionnaire.questionnaire.LSAS.QuestionnaireModelOfLSAS;
import cn.skyduck.questionnaire.questionnaire.LSIA.QuestionnaireModelOfLSIA;
import cn.skyduck.questionnaire.questionnaire.LSIB.QuestionnaireModelOfLSIB;
import cn.skyduck.questionnaire.questionnaire.LSR.QuestionnaireModelOfLSR;
import cn.skyduck.questionnaire.questionnaire.MAST.QuestionnaireModelOfMAST;
import cn.skyduck.questionnaire.questionnaire.MMPI.QuestionnaireModelOfMMPI;
import cn.skyduck.questionnaire.questionnaire.MMSE.QuestionnaireModelOfMMSE;
import cn.skyduck.questionnaire.questionnaire.MUNSH.QuestionnaireModelOfMUNSH;
import cn.skyduck.questionnaire.questionnaire.NOSIE.QuestionnaireModelOfNOSIE;
import cn.skyduck.questionnaire.questionnaire.PANSS.QuestionnaireModelOfPANSS;
import cn.skyduck.questionnaire.questionnaire.PSESE.QuestionnaireModelOfPSESE;
import cn.skyduck.questionnaire.questionnaire.PSQ.QuestionnaireModelOfPSQ;
import cn.skyduck.questionnaire.questionnaire.PSQI.QuestionnaireModelOfPSQI;
import cn.skyduck.questionnaire.questionnaire.RCBC.QuestionnaireModelOfRCBC;
import cn.skyduck.questionnaire.questionnaire.SAD.QuestionnaireModelOfSAD;
import cn.skyduck.questionnaire.questionnaire.SAI.QuestionnaireModelOfSAI;
import cn.skyduck.questionnaire.questionnaire.SANS.QuestionnaireModelOfSANS;
import cn.skyduck.questionnaire.questionnaire.SAPS.QuestionnaireModelOfSAPS;
import cn.skyduck.questionnaire.questionnaire.SAS.QuestionnaireModelOfSAS;
import cn.skyduck.questionnaire.questionnaire.SCCS.QuestionnaireModelOfSCCS;
import cn.skyduck.questionnaire.questionnaire.SCL_90.QuestionnaireModelOfSCL_90;
import cn.skyduck.questionnaire.questionnaire.SCSQ.QuestionnaireModelOfSCSQ;
import cn.skyduck.questionnaire.questionnaire.SDS.QuestionnaireModelOfSDS;
import cn.skyduck.questionnaire.questionnaire.SDSS.QuestionnaireModelOfSDSS;
import cn.skyduck.questionnaire.questionnaire.SERS.QuestionnaireModelOfSERS;
import cn.skyduck.questionnaire.questionnaire.SF_36.QuestionnaireModelOfSF_36;
import cn.skyduck.questionnaire.questionnaire.SPM.QuestionnaireModelOfSPM;
import cn.skyduck.questionnaire.questionnaire.SSRS.QuestionnaireModelOfSSRS;
import cn.skyduck.questionnaire.questionnaire.STAI.QuestionnaireModelOfSTAI;
import cn.skyduck.questionnaire.questionnaire.TABP.QuestionnaireModelOfTABP;
import cn.skyduck.questionnaire.questionnaire.TAS.QuestionnaireModelOfTAS;
import cn.skyduck.questionnaire.questionnaire.UCLA.QuestionnaireModelOfUCLA;
import cn.skyduck.questionnaire.questionnaire.Y_BOCS.QuestionnaireModelOfY_BOCS;
import cn.skyduck.questionnaire.questionnaire._16PF.QuestionnaireModelOf16PF;

public class QuestionnaireModelClassNameMapping extends StrategyClassNameMappingBase {
	public QuestionnaireModelClassNameMapping() {

		/**
		 * 1 : 症状自评量表(SCL-90)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SCL_90.name(), QuestionnaireModelOfSCL_90.class.getName());

		/**
		 * 2 : 焦虑自评量表(SAS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SAS.name(), QuestionnaireModelOfSAS.class.getName());

		/**
		 * 3 : 抑郁自评量表(SDS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SDS.name(), QuestionnaireModelOfSDS.class.getName());

		/**
		 * 4 : 明尼苏达多项人格测验(MMPI)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.MMPI.name(), QuestionnaireModelOfMMPI.class.getName());

		/**
		 * 5 : Yale-Brown强迫量表(Y-BOCS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.Y_BOCS.name(), QuestionnaireModelOfY_BOCS.class.getName());

		/**
		 * 6 : 社交焦虑量表(LSAS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.LSAS.name(), QuestionnaireModelOfLSAS.class.getName());

		/**
		 * 7 : 汉密尔顿焦虑量表(HAMA)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.HAMA.name(), QuestionnaireModelOfHAMA.class.getName());

		/**
		 * 8 : 汉密尔顿抑郁量表(HAMD)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.HAMD.name(), QuestionnaireModelOfHAMD.class.getName());

		/**
		 * 9 : 简明精神病评定量表(BPRS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.BPRS.name(), QuestionnaireModelOfBPRS.class.getName());

		/**
		 * 10 : Bech-Rafaelsen躁狂量表(BRMS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.BRMS.name(), QuestionnaireModelOfBRMS.class.getName());

		/**
		 * 11 : 艾森克人格问卷(EPQ)(成人)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.EPQ_Adults.name(), QuestionnaireModelOfEPQ_Adults.class.getName());

		/**
		 * 12 : 艾森克人格问卷(EPQ)(儿童)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.EPQ_Children.name(), QuestionnaireModelOfEPQ_Children.class.getName());

		/**
		 * 13 : 流调中心用抑郁量表(CES-D)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.CES_D.name(), QuestionnaireModelOfCES_D.class.getName());

		/**
		 * 14 : 瑞文标准推理测验(SPM)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SPM.name(), QuestionnaireModelOfSPM.class.getName());

		/**
		 * 15 : Achenbach儿童行为量表(CBCL)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.CBCL.name(), QuestionnaireModelOfCBCL.class.getName());

		/**
		 * 16 : Olson婚姻质量问卷(ENRICH)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.ENRICH.name(), QuestionnaireModelOfENRICH.class.getName());

		/**
		 * 17 : 卡特尔16种人格因素测验(16PF)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum._16PF.name(), QuestionnaireModelOf16PF.class.getName());

		/**
		 * 18 : 康奈尔医学指数(CMI)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.CMI.name(), QuestionnaireModelOfCMI.class.getName());

		/**
		 * 19 : 匹茨堡睡眠质量指数(PSQI)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.PSQI.name(), QuestionnaireModelOfPSQI.class.getName());

		/**
		 * 20 : 阳性与阴性症状量表(PANSS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.PANSS.name(), QuestionnaireModelOfPANSS.class.getName());

		/**
		 * 21 : 自杀态度问卷(SAI)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SAI.name(), QuestionnaireModelOfSAI.class.getName());

		/**
		 * 22 : A型行为类型问卷(TABP)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.TABP.name(), QuestionnaireModelOfTABP.class.getName());

		/**
		 * 23 : Conners儿童行为问卷(父母用)(PSQ)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.PSQ.name(), QuestionnaireModelOfPSQ.class.getName());

		/**
		 * 24 : Rutter儿童行为问卷(RCBC)(父母用)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.RCBC.name(), QuestionnaireModelOfRCBC.class.getName());

		/**
		 * 25 : 痴呆简易筛查量表(BSSD)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.BSSD.name(), QuestionnaireModelOfBSSD.class.getName());

		/**
		 * 26 : 护士用住院病人观察量表(NOSIE)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.NOSIE.name(), QuestionnaireModelOfNOSIE.class.getName());

		/**
		 * 27 : 简易智力状态检查(MMSE)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.MMSE.name(), QuestionnaireModelOfMMSE.class.getName());

		/**
		 * 28 : 社会功能缺陷筛选量表(SDSS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SDSS.name(), QuestionnaireModelOfSDSS.class.getName());

		/**
		 * 29 : 生活事件量表(LES)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.LES.name(), QuestionnaireModelOfLES.class.getName());

		/**
		 * 30 : 阳性症状量表(SAPS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SAPS.name(), QuestionnaireModelOfSAPS.class.getName());

		/**
		 * 31 : 阴性症状量表(SANS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SANS.name(), QuestionnaireModelOfSANS.class.getName());

		/**
		 * 32 : 锥体外系副反应量表(PSESE)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.PSESE.name(), QuestionnaireModelOfPSESE.class.getName());

		/**
		 * 33 : BECK抑郁自评量表(BDI)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.BDI.name(), QuestionnaireModelOfBDI.class.getName());

		/**
		 * 34 : 多动指数(CIH)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.CIH.name(), QuestionnaireModelOfCIH.class.getName());

		/**
		 * 35 : 防御方式问卷(DSQ)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.DSQ.name(), QuestionnaireModelOfDSQ.class.getName());

		/**
		 * 36 : 密西根酒精依赖调查表(MAST)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.MAST.name(), QuestionnaireModelOfMAST.class.getName());

		/**
		 * 37 : 社会支持评定量表(SSRS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SSRS.name(), QuestionnaireModelOfSSRS.class.getName());

		/**
		 * 38 : 状态-特质焦虑问卷(STAI)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.STAI.name(), QuestionnaireModelOfSTAI.class.getName());

		/**
		 * 39 : Hachinski缺血指数(HIS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.HIS.name(), QuestionnaireModelOfHIS.class.getName());

		/**
		 * 40 : UCLA孤独量表(UCLA)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.UCLA.name(), QuestionnaireModelOfUCLA.class.getName());

		/**
		 * 41 : 家庭环境量表(FES)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.FES.name(), QuestionnaireModelOfFES.class.getName());

		/**
		 * 42 : 长谷川痴呆量表(HDS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.HDS.name(), QuestionnaireModelOfHDS.class.getName());

		/**
		 * 43 : 精神症状全面量表(CPRS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.CPRS.name(), QuestionnaireModelOfCPRS.class.getName());

		/**
		 * 44 : sarason考试焦虑量表(TAS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.TAS.name(), QuestionnaireModelOfTAS.class.getName());

		/**
		 * 45 : 纽芬兰纪念大学幸福度量表(MUNSH)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.MUNSH.name(), QuestionnaireModelOfMUNSH.class.getName());

		/**
		 * 46 : 生活满意度评定量表(LSR)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.LSR.name(), QuestionnaireModelOfLSR.class.getName());

		/**
		 * 47 : 总体幸福感量表(GWB)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.GWB.name(), QuestionnaireModelOfGWB.class.getName());

		/**
		 * 48 : 阿森斯失眠量表(AIS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.AIS.name(), QuestionnaireModelOfAIS.class.getName());

		/**
		 * 49 : 抗抑郁药副反应量表(SERS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SERS.name(), QuestionnaireModelOfSERS.class.getName());

		/**
		 * 50 : 疲劳量表-14(FS-14)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.FS_14.name(), QuestionnaireModelOfFS_14.class.getName());

		/**
		 * 51 : 日常生活能力量表(ADL)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.ADL.name(), QuestionnaireModelOfADL.class.getName());

		/**
		 * 52 : 自我和谐量表(SCCS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SCCS.name(), QuestionnaireModelOfSCCS.class.getName());

		/**
		 * 53 : 生活满意度指数A(LSIA)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.LSIA.name(), QuestionnaireModelOfLSIA.class.getName());

		/**
		 * 54 : 生活满意度指数B(LSIB)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.LSIB.name(), QuestionnaireModelOfLSIB.class.getName());

		/**
		 * 55 : BECK焦虑自评量表(BAI)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.BAI.name(), QuestionnaireModelOfBAI.class.getName());

		/**
		 * 56 : 父母教养方式评价量表(EMBU)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.EMBU.name(), QuestionnaireModelOfEMBU.class.getName());

		/**
		 * 57 : 霍兰德职业兴趣测试(HPII)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.HPII.name(), QuestionnaireModelOfHPII.class.getName());

		/**
		 * 58 : 简易应对方式问卷(SCSQ)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SCSQ.name(), QuestionnaireModelOfSCSQ.class.getName());

		/**
		 * 59 : 健康状况调查问卷(SF-36)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SF_36.name(), QuestionnaireModelOfSF_36.class.getName());

		/**
		 * 60 : 老年抑郁量表(GDS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.GDS.name(), QuestionnaireModelOfGDS.class.getName());

		/**
		 * 61 : 青少年生活事件量表(ASLEC)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.ASLEC.name(), QuestionnaireModelOfASLEC.class.getName());

		/**
		 * 62 : 情绪-社交孤独问卷(ESLI)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.ESLI.name(), QuestionnaireModelOfESLI.class.getName());

		/**
		 * 63 : 人际信任量表(ITS)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.ITS.name(), QuestionnaireModelOfITS.class.getName());

		/**
		 * 64 : 社交回避及苦恼量表(SAD)
		 */
		strategyClassesNameMappingList.put(QuestionnaireCodeEnum.SAD.name(), QuestionnaireModelOfSAD.class.getName());
	}
}
