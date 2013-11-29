package cn.skyduck.global_data_cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.EnumSet;

import cn.skyduck.global_data_cache.GlobalConstant.NormalModeTypeEnum;
import cn.skyduck.global_data_cache.GlobalConstant.PrintTypeEnum;
import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireCategoryEnum;

public enum QuestionnaireCodeEnum {
		// 1 : 症状自评量表(SCL-90)
		SCL_90(2, "SCL_90", "症状自评量表(SCL-90)", 
				QuestionnaireCategoryEnum.COMPLEX, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT, PrintTypeEnum.FULL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 2 : 焦虑自评量表(SAS)
		SAS(3, "SAS", "焦虑自评量表(SAS)", QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT, PrintTypeEnum.FULL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 3 : 抑郁自评量表(SDS)
		SDS(4, "SDS", "抑郁自评量表(SDS)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT, PrintTypeEnum.FULL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 4 : 明尼苏达多项人格测验(MMPI)
		MMPI(5, "MMPI", "明尼苏达多项人格测验(MMPI)", 
				QuestionnaireCategoryEnum.PERSONALITY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT), 
				true, 
				NormalModeTypeEnum.SEX.getValue() | NormalModeTypeEnum.PROFESSION.getValue()),
		// 5 : Yale-Brown强迫量表(Y-BOCS)
		Y_BOCS(6, "Y_BOCS", "Yale-Brown强迫量表(Y-BOCS)", 
				QuestionnaireCategoryEnum.MENTAL, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT, PrintTypeEnum.FULL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 6 : 社交焦虑量表(LSAS)
		LSAS(7, "LSAS", "社交焦虑量表(LSAS)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.FULL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 7 : 汉密尔顿焦虑量表(HAMA)
		HAMA(8, "HAMA", "汉密尔顿焦虑量表(HAMA)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT, PrintTypeEnum.FULL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 8 : 汉密尔顿抑郁量表(HAMD)
		HAMD(9, "HAMD", "汉密尔顿抑郁量表(HAMD)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT, PrintTypeEnum.FULL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 9 : 简明精神病评定量表(BPRS)
		BPRS(10, "BPRS", "简明精神病评定量表(BPRS)", 
				QuestionnaireCategoryEnum.MENTAL, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.FULL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 10 : Bech-Rafaelsen躁狂量表(BRMS)
		BRMS(11, "BRMS", "Bech-Rafaelsen躁狂量表(BRMS)", 
				QuestionnaireCategoryEnum.MENTAL, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT, PrintTypeEnum.FULL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 11 : 艾森克人格问卷(EPQ)(成人)
		EPQ_Adults(12, "EPQ-成人", "艾森克人格问卷(EPQ)(成人)", 
				QuestionnaireCategoryEnum.PERSONALITY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 12 : 艾森克人格问卷(EPQ)(儿童)
		EPQ_Children(12, "EPQ-儿童", "艾森克人格问卷(EPQ)(儿童)", 
				QuestionnaireCategoryEnum.PERSONALITY, 
				EnumSet.of(PrintTypeEnum.DETAIL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 13 : 流调中心用抑郁量表(CES-D)
		CES_D(13, "CES_D", "流调中心用抑郁量表(CES-D)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 14 : 瑞文标准推理测验(SPM)
		SPM(14, "SPM", "瑞文标准推理测验(SPM)", 
				QuestionnaireCategoryEnum.OTHER, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 15 : Achenbach儿童行为量表(CBCL)
		CBCL(15, "CBCL", "Achenbach儿童行为量表(CBCL)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 16 : Olson婚姻质量问卷(ENRICH)
		ENRICH(16, "ENRICH", "Olson婚姻质量问卷(ENRICH)", 
				QuestionnaireCategoryEnum.FAMILY_LIFE, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 17 : 卡特尔16种人格因素测验(16PF)
		_16PF(17, "16PF", "卡特尔16种人格因素测验(16PF)", 
				QuestionnaireCategoryEnum.PERSONALITY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 18 : 康奈尔医学指数(CMI)
		CMI(18, "CMI", "康奈尔医学指数(CMI)", 
				QuestionnaireCategoryEnum.COMPLEX, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 19 : 匹茨堡睡眠质量指数(PSQI)
		PSQI(19, "PSQI", "匹茨堡睡眠质量指数(PSQI)", 
				QuestionnaireCategoryEnum.OTHER, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 20 : 阳性与阴性症状量表(PANSS)
		PANSS(20, "PANSS", "阳性与阴性症状量表(PANSS)", 
				QuestionnaireCategoryEnum.MENTAL, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 21 : 自杀态度问卷(SAI)
		SAI(21, "SAI", "自杀态度问卷(SAI)", 
				QuestionnaireCategoryEnum.OTHER, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 22 : A型行为类型问卷(TABP)
		TABP(22, "TABP", "A型行为类型问卷(TABP)", 
				QuestionnaireCategoryEnum.PERSONALITY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 23 : Conners儿童行为问卷(父母用)(PSQ)
		PSQ(23, "PSQ", "Conners儿童行为问卷(父母用)(PSQ)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 24 : Rutter儿童行为问卷(RCBC)(父母用)
		RCBC(24, "RCBC", "Rutter儿童行为问卷(RCBC)(父母用)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 25 : 痴呆简易筛查量表(BSSD)
		BSSD(25, "BSSD", "痴呆简易筛查量表(BSSD)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 26 : 护士用住院病人观察量表(NOSIE)
		NOSIE(26, "NOSIE", "护士用住院病人观察量表(NOSIE)", 
				QuestionnaireCategoryEnum.MENTAL, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 27 : 简易智力状态检查(MMSE)
		MMSE(27, "MMSE", "简易智力状态检查(MMSE)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.FULL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 28 : 社会功能缺陷筛选量表(SDSS)
		SDSS(28, "SDSS", "社会功能缺陷筛选量表(SDSS)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 29 : 生活事件量表(LES)
		LES(29, "LES", "生活事件量表(LES)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 30 : 阳性症状量表(SAPS)
		SAPS(30, "SAPS", "阳性症状量表(SAPS)", 
				QuestionnaireCategoryEnum.MENTAL, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 31 : 阴性症状量表(SANS)
		SANS(31, "SANS", "阴性症状量表(SANS)", 
				QuestionnaireCategoryEnum.MENTAL, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 32 : 锥体外系副反应量表(PSESE)
		PSESE(32, "PSESE", "锥体外系副反应量表(PSESE)", 
				QuestionnaireCategoryEnum.MENTAL, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 33 : BECK抑郁自评量表(BDI)
		BDI(33, "BDI", "BECK抑郁自评量表(BDI)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT, PrintTypeEnum.DETAIL_PRINT, PrintTypeEnum.FULL_PRINT), 
				true, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 34 : 多动指数(CIH)
		CIH(34, "CIH", "多动指数(CIH)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 35 : 防御方式问卷(DSQ)
		DSQ(35, "DSQ", "防御方式问卷(DSQ)", 
				QuestionnaireCategoryEnum.OTHER, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 36 : 密西根酒精依赖调查表(MAST)
		MAST(36, "MAST", "密西根酒精依赖调查表(MAST)", 
				QuestionnaireCategoryEnum.OTHER, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 37 : 社会支持评定量表(SSRS)
		SSRS(37, "SSRS", "社会支持评定量表(SSRS)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 38 : 状态-特质焦虑问卷(STAI)
		STAI(38, "STAI", "状态-特质焦虑问卷(STAI)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 39 : Hachinski缺血指数(HIS)
		HIS(39, "HIS", "Hachinski缺血指数(HIS)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 40 : UCLA孤独量表(UCLA)
		UCLA(40, "UCLA", "UCLA孤独量表(UCLA)", 
				QuestionnaireCategoryEnum.OTHER, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 41 : 家庭环境量表(FES)
		FES(41, "FES", "家庭环境量表(FES)", 
				QuestionnaireCategoryEnum.FAMILY_LIFE, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 42 : 长谷川痴呆量表(HDS)
		HDS(42, "HDS", "长谷川痴呆量表(HDS)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 43 : 精神症状全面量表(CPRS)
		CPRS(43, "CPRS", "精神症状全面量表(CPRS)", 
				QuestionnaireCategoryEnum.MENTAL, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 44 : sarason考试焦虑量表(TAS)
		TAS(44, "TAS", "sarason考试焦虑量表(TAS)", 
				QuestionnaireCategoryEnum.OTHER, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 45 : 纽芬兰纪念大学幸福度量表(MUNSH)
		MUNSH(45, "MUNSH", "纽芬兰纪念大学幸福度量表(MUNSH)", 
				QuestionnaireCategoryEnum.COMPLEX, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 46 : 生活满意度评定量表(LSR)
		LSR(46, "LSR", "生活满意度评定量表(LSR)", 
				QuestionnaireCategoryEnum.COMPLEX, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 47 : 总体幸福感量表(GWB)
		GWB(47, "GWB", "总体幸福感量表(GWB)", 
				QuestionnaireCategoryEnum.COMPLEX, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 48 : 阿森斯失眠量表(AIS)
		AIS(48, "AIS", "阿森斯失眠量表(AIS)", 
				QuestionnaireCategoryEnum.OTHER, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false,
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 49 : 抗抑郁药副反应量表(SERS)
		SERS(49, "SERS", "抗抑郁药副反应量表(SERS)", 
				QuestionnaireCategoryEnum.MENTAL, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 50 : 疲劳量表-14(FS-14)
		FS_14(50, "FS_14", "疲劳量表-14(FS-14)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 51 : 日常生活能力量表(ADL)
		ADL(51, "ADL", "日常生活能力量表(ADL)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 52 : 自我和谐量表(SCCS)
		SCCS(52, "SCCS", "自我和谐量表(SCCS)", 
				QuestionnaireCategoryEnum.COMPLEX, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 53 : 简易应对方式问卷(SCSQ)
		SCSQ(53, "SCSQ", "简易应对方式问卷(SCSQ)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 54 : 老年抑郁量表(GDS)
		GDS(54, "GDS", "老年抑郁量表(GDS)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),	
			// 55 : 情绪-社交孤独问卷(ESLI)
		ESLI(55, "ESLI", "情绪-社交孤独问卷(ESLI)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),	
		// 56 : 生活满意度指数A(LSIA)
		LSIA(56, "LSIA", "生活满意度指数A(LSIA)", 
				QuestionnaireCategoryEnum.COMPLEX, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 57 : 生活满意度指数B(LSIB)
		LSIB(57, "LSIB", "生活满意度指数B(LSIB)", 
				QuestionnaireCategoryEnum.COMPLEX, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 58 : 父母教养方式评价量表(EMBU)
		EMBU(58, "EMBU", "父母教养方式评价量表(EMBU)", 
				QuestionnaireCategoryEnum.OTHER, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),				
		// 59 : BECK焦虑自评量表(BAI)
		BAI(59, "BAI", "BECK焦虑自评量表(BAI)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 60 : 健康状况调查问卷(SF-36)
		SF_36(60, "SF_36", "健康状况调查问卷(SF-36)", 
				QuestionnaireCategoryEnum.COMPLEX, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 61 : 人际信任量表(ITS)
		ITS(61, "ITS", "人际信任量表(ITS)", 
				QuestionnaireCategoryEnum.OTHER, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),		
		// 62 : 霍兰德职业兴趣测试(HPII)
		HPII(62, "HPII", "霍兰德职业兴趣测试(HPII)", 
				QuestionnaireCategoryEnum.OTHER, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		
		// 63 : 青少年生活事件量表(ASLEC)
		ASLEC(63, "ASLEC", "青少年生活事件量表(ASLEC)", 
				QuestionnaireCategoryEnum.CHILDREN_ELDERLY, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()),
		// 64 : 社交回避及苦恼量表(SAD)
		SAD(64, "SAD", "社交回避及苦恼量表(SAD)", 
				QuestionnaireCategoryEnum.EMOTIONS_STRESS, 
				EnumSet.of(PrintTypeEnum.NORMAL_PRINT), 
				false, 
				NormalModeTypeEnum.ENTIRE_COUNTRY.getValue());

	// 当前测试量表缩略名(例如 : 症状自评量表(SCL-90) 的缩略名是 SCL-90)
	private final String shortName;

	public String getShortName() {
		return shortName;
	}

	// 当前测试量表全称(例如 : 症状自评量表(SCL-90) )
	private final String fullName;

	public String getFullName() {
		return fullName;
	}

	// 当前测试量表归属类别(例如 : 症状自评量表(SCL-90) 属于 "综合类测验" )
	private final QuestionnaireCategoryEnum categoryEnum;

	public QuestionnaireCategoryEnum getCategoryEnum() {
		return categoryEnum;
	}

	// 当前量表支持的打印类型(这里使用 EnumSet 代替了 "位域")
	private final Set<GlobalConstant.PrintTypeEnum> printTypeSet;

	public Set<GlobalConstant.PrintTypeEnum> getPrintTypeSet() {
		// printTypeSet 对外要求是只读的
		return ((EnumSet<GlobalConstant.PrintTypeEnum>) printTypeSet).clone();
	}

	// 这里要求 EnumSet 中数据有序, 所以使用一个 List来转接数据
	private final List<GlobalConstant.PrintTypeEnum> printTypeList;

	public GlobalConstant.PrintTypeEnum getDefaultPrintType() {
		return printTypeList.get(0);
	}

	// 判断当前量表是否支持这种 打印类型
	public boolean isSupportThisPrintType(PrintTypeEnum printTypeEnum) {
		do {
			if (printTypeEnum == null) {
				break;
			}
			if (!printTypeSet.contains(printTypeEnum)) {
				break;
			}

			return true;
		} while (false);

		return false;
	}

	// 是否是默认的 "常用测试"(例如 : 症状自评量表(SCL-90) 就是默认的常用测试量表)
	private boolean isDefaultCommonTest;

	public boolean isDefaultCommonTest() {
		return isDefaultCommonTest;
	}

	// "常模" 类型(例如 : 全国常模) , 这里使用普通的 "位域" 方法
	private final List<GlobalConstant.NormalModeTypeEnum> normalModeTypeList = new ArrayList<GlobalConstant.NormalModeTypeEnum>(10);

	public List<GlobalConstant.NormalModeTypeEnum> getNormalModeTypeList() {
		// 要求对外只读
		return new ArrayList<GlobalConstant.NormalModeTypeEnum>(normalModeTypeList);
	}

	private void setNormalModeTypeList(int normalModeTypeListInt) {
		if ((normalModeTypeListInt & NormalModeTypeEnum.ENTIRE_COUNTRY.getValue()) != 0) {
			this.normalModeTypeList.add(NormalModeTypeEnum.ENTIRE_COUNTRY);
		}
		if ((normalModeTypeListInt & NormalModeTypeEnum.SEX.getValue()) != 0) {
			this.normalModeTypeList.add(NormalModeTypeEnum.SEX);
		}
		if ((normalModeTypeListInt & NormalModeTypeEnum.PROFESSION.getValue()) != 0) {
			this.normalModeTypeList.add(NormalModeTypeEnum.PROFESSION);
		}
	}

	private int questionnaireNumber;

	public int getQuestionnaireNumber() {
		return questionnaireNumber;
	}

	private QuestionnaireCodeEnum(int questionnaireNumber, String shortName, String fullName, QuestionnaireCategoryEnum categoryEnum, Set<GlobalConstant.PrintTypeEnum> printTypeSet,
			boolean isDefaultCommonTest, int normalModeTypeListInt) {
		this.questionnaireNumber = questionnaireNumber;
		this.shortName = shortName;
		this.fullName = fullName;
		this.categoryEnum = categoryEnum;
		this.isDefaultCommonTest = isDefaultCommonTest;
		this.printTypeSet = printTypeSet;
		this.printTypeList = new ArrayList<GlobalConstant.PrintTypeEnum>(printTypeSet);
		// 
		Collections.sort(this.printTypeList, new Comparator<GlobalConstant.PrintTypeEnum>() {

			@Override
			public int compare(PrintTypeEnum lhs, PrintTypeEnum rhs) {
				return lhs.compareTo(rhs);
			}
		});
		setNormalModeTypeList(normalModeTypeListInt);
	}
}
