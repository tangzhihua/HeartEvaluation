package cn.skyduck.questionnaire.questionnaire.HPII;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.hpii.HPIIPartOneFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.hpii.HPIIPartFiveFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.hpii.HPIIPartSixFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.question_frame_fragment.transition_page_1.TransitionPageOneFragmentFactoryMethod;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;
@SuppressWarnings("serial")
public class QuestionnaireModelOfHPII extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 // 第一部分  第一部分  你心目中的理想职业(专业)
	 // 1
	 "",
	 // 第二部分  第二部分 你所感兴趣的活动（一共包含6组）
	 // 第一组
	 "1．您是否对装配修理电器或玩具感兴趣？",
	 "2．您是否对修理自行车感兴趣？",
	 "3．您是否对用木头做东西感兴趣？",
	 "4．您是否对开汽车或摩托车感兴趣？",
	 "5．您是否对用机器做东西感兴趣？",
	 "6．您是否对参加木工技术学习班感兴趣？",
	 "7．您是否对参加制图描图学习班感兴趣？",
	 "8．您是否对驾驶卡车或拖拉机感兴趣？",
	 "9．您是否对参加机械和电器学习班感兴趣？",
	 "10．您是否对装配和修理机器感兴趣？",
	 // 第二组
	 "1．您是否对素描／制图或绘画感兴趣？",
	 "2．您是否对参加话剧／戏剧感兴趣？",
	 "3．您是否对设计家具／布置室内感兴趣？",
	 "4．您是否对练习乐器／参加乐队感兴趣？",
	 "5．您是否对欣赏音乐或戏剧感兴趣？",
	 "6．您是否对看小说／读剧本感兴趣？",
	 "7．您是否对从事摄影创作感兴趣？",
	 "8．您是否对写诗或吟诗感兴趣？",
	 "9．您是否对进艺术(美术／音乐)培训感兴趣？",
	 "10．您是否对练习书法感兴趣？",
	 // 第三组
	 "1．您是否对读科技图书和杂志感兴趣？",
	 "2．您是否对在实验室工作感兴趣？",
	 "3．您是否对改良水果品种，培育新的水果感兴趣？",
	 "4．您是否对调查了解土和金属等物质的成分感兴趣？",
	 "5．您是否对研究自己选择的特殊问题感兴趣？",
	 "6．您是否对解算术或玩数学游戏感兴趣？",
	 "7．您是否对物理课感兴趣？",
	 "8．您是否对化学课感兴趣？",
	 "9．您是否对几何课感兴趣？",
	 "10．您是否对生物课感兴趣？",
	 // 第四组
	 "1．您是否对学校或单位组织的正式活动感兴趣？",
	 "2．您是否对参加某个社会团体或俱乐部活动感兴趣？",
	 "3．您是否对帮助别人解决困难感兴趣？",
	 "4．您是否对照顾儿童感兴趣？",
	 "5．您是否对出席晚会、联欢会、茶话会感兴趣？",
	 "6．您是否对和大家一起出去郊游感兴趣？",
	 "7．您是否对想获得关于心理方面的知识感兴趣？",
	 "8．您是否对参加讲座会或辩论会感兴趣？",
	 "9．您是否对观看或参加体育比赛和运动会感兴趣？",
	 "10．您是否对结交新朋友感兴趣？",
	 // 第五组
	 "1．您是否对说服鼓动他人感兴趣？",
	 "2．您是否对卖东西感兴趣？",
	 "3．您是否对谈论政治感兴趣？",
	 "4．您是否对制定计划、参加会议感兴趣？",
	 "5．您是否对以自己的意志影响别人的行为感兴趣？",
	 "6．您是否对在社会团体中担任职务感兴趣？",
	 "7．您是否对检查与评价别人的工作感兴趣？",
	 "8．您是否对结交名流感兴趣？",
	 "9．您是否对指导有某种目标的团体感兴趣？",
	 "10．您是否对参与政治活动感兴趣？",
	 // 第六组
	 "1．您是否对整理好桌面和房间感兴趣？",
	 "2．您是否对抄写文件和信件感兴趣？",
	 "3．您是否对为领导写报告或公务信函感兴趣？",
	 "4．您是否对检查个人收支情况感兴趣？",
	 "5．您是否对打字培训班感兴趣？",
	 "6．您是否对参加算盘、文秘等实务培训感兴趣？",
	 "7．您是否对参加商业会计培训班感兴趣？",
	 "8．您是否对参加情报处理培训班感兴趣？",
	 "9．您是否对整理信件、报告、记录等感兴趣？",
	 "10．您是否对写商业贸易信感兴趣？",
	 // 第三部分 你所擅长或胜任的事情（一共包含6组）
	 // 第一组
	 "1．能使用电锯、电钻和锉刀等木工工具",
	 "2．知道万用表的使用方法",
	 "3．能够修理自行车或其他机械",
	 "4．能够使用电钻床、磨床或缝纫机",
	 "5．能给家具和木制品刷漆",
	 "6．能看建筑设计图",
	 "7．能够修理简单的电气用品",
	 "8．能修理家具",
	 "9．能修理收录机",
	 "10．能简单地修理水管",
	 // 第二组
	 "1．能演奏乐器",
	 "2．能参加二部或四部合唱",
	 "3．独唱或独奏",
	 "4．扮演剧中角色",
	 "5．能创作简单的乐曲",
	 "6．会跳舞",
	 "7．能绘画、素描或书法",
	 "8．能雕刻、剪纸或泥塑",
	 "9．能设计板报、服装或家具",
	 "10．写得一手好文章",
	 // 第三组
	 "1．懂得真空管或晶体管的作用",
	 "2．能够列举三种蛋白质多的食品",
	 "3．理解铀的裂变",
	 "4．能用计算尺、计算器、对数表",
	 "5．会使用显微镜",
	 "6．能找到三个星座",
	 "7．能独立进行调查研究",
	 "8．能解释简单的化学",
	 "9．理解人造卫星为什么不落地",
	 "10．经常参加学术的会议",
	 // 第四组
	 "1．有向各种人说明解释的能力",
	 "2．常参加社会福利活动",
	 "3．能和大家一起友好相处地工作",
	 "4．善于与年长者相处",
	 "5．会邀请人、招待人",
	 "6．能简单易懂地教育儿童",
	 "7．能安排会议等活动顺序",
	 "8．善于体察人心和帮助他人",
	 "9．帮助护理病人和伤员",
	 "10．安排社团组织的各种事务",
	 // 第五组
	 "1．担任过学生干部并且干得不错",
	 "2．工作上能指导和监督他人",
	 "3．做事充满活力和热情",
	 "4．有效利用自身的做法调动他人",
	 "5．销售能力强",
	 "6．曾作为俱乐部或社团的负责人",
	 "7．向领导提出建议或反映意见",
	 "8．有开创事业的能力",
	 "9．知道怎样做能成为一个优秀的领导者",
	 "10．健谈善辩",
	 // 第六组
	 "1．会熟练的打印中文",
	 "2．会用外文打字机或复印机",
	 "3．能快速记笔记和抄写文章",
	 "4．善于整理保管文件和资料",
	 "5．善于从事事务性的工作",
	 "6．会用算盘",
	 "7．能在短时间内分类和处理大量文件",
	 "8．能使用计算机",
	 "9．能搜集数据",
	 "10．善于为自己或集体做财务预算表",
	  // 第四部分  你所喜欢的职业 （一共包含6组）
	  // 第一组
	 "1．飞机机械师",
	 "2．野生动物专家",
	 "3．汽车维修工",
	 "4．木匠",
	 "5．测量工程师",
	 "6．无线电报务员",
	 "7．园艺师",
	 "8．长途公共汽车司机",
	 "9．火车司机",
	 "10．电工",
	  // 第二组
	 "1．乐队指挥",
	 "2．演奏家",
	 "3．作家",
	 "4．摄影家",
	 "5．记者",
	 "6．画家、书法家",
	 "7．歌唱家",
	 "8．作曲家",
	 "9．电影电视演员",
	 "10. 电视节目主持人",
	 // 第三组
	 "1．气象学或天文学者",
	 "2．生物学者",
	 "3．医学实验室的技术人员",
	 "4．人类学者",
	 "5．动物学者",
	 "6．化学学者",
	 "7．数学学者",
	 "8．科学杂志的编辑或作家",
	 "9．地质学者",
	 "10．物理学者",
	 // 第四组
	 "1．街道、工会或妇联干部",
	 "2．小学、中学教师",
	 "3．精神病医生",
	 "4．婚姻介绍所工作人员",
	 "5．体育教练",
	 "6．福利机构负责人",
	 "7．心理咨询员",
	 "8．共青团干部",
	 "9．导游",
	 "10．国家机关工作人员",
	 // 第五组
	 "1．厂长",
	 "2．电视片编制人",
	 "3．公司经理",
	 "4．销售员",
	 "5．不动产推销员",
	 "6．广告部长",
	 "7．体育活动主办者",
	 "8．销售部长",
	 "9．个体工商业者",
	 "10．企业管理咨询人员",
	 // 第六组
	 "1．会计师",
	 "2．银行出纳员",
	 "3．税收管理员",
	 "4．计算机操作员",
	 "5．簿记人员",
	 "6．成本核算员",
	 "7．文书档案管理员",
	 "8．打字员",
	 "9．法庭书记员",
	 "10．人口普查登记员",
	 // 第五部分  对你的能力进行自评
	 "",
	 "",
	 "",
	 "",
	 "",
	 "",
	 "",
	 "",
	 "",
	 "",
	 "",
	 "",
	 // 第六部分  您所看重的东西——职业价值观
	 "",
	 "",
	 "",
	 ""
 };
 
  // 答案1 (只限于带下拉的那个界面)
  private static final String[] kAnswerSpinner1Array = new String[]{
   "R型：机械操作能力",
 	 "A型：艺术创造能力",
 	 "I型：科学研究能力",
 	 "S型：解释表达能力",
 	 "E型：商业洽谈能力",
 	 "C型：事务执行能力",
  };
 
  
  // 答案2 (只限于带下拉的那个界面)
  private static final String[] kAnswerSpinner2Array = new String[]{
   "R型：体育技能",
 	 "A型：音乐技能",
 	 "I型：数学技能",
 	 "S型：交际技能",
 	 "E型：领导技能",
 	 "C型：办公技能",
  };
  
	// 问题 提示
	private static final String[] kQuestionPromptArray = new String[]{
		"",
		//第二部分第一组
		// 4
		"感兴趣的活动（第1组）",
		// 5
		"感兴趣的活动（第1组）",
		// 6
		"感兴趣的活动（第1组）",
		// 7
		"感兴趣的活动（第1组）",
		// 8
		"感兴趣的活动（第1组）",
		// 9
		"感兴趣的活动（第1组）",
		// 10
		"感兴趣的活动（第1组）",
		// 11
		"感兴趣的活动（第1组）",
		// 12
		"感兴趣的活动（第1组）",
		// 13
		"感兴趣的活动（第1组）",
		//第二部分第二组
		// 14
		"感兴趣的活动（第2组）",
		// 15
		"感兴趣的活动（第2组）",
		// 16
		"感兴趣的活动（第2组）",
		// 17
		"感兴趣的活动（第2组）",
		// 18
		"感兴趣的活动（第2组）",
		// 19
		"感兴趣的活动（第2组）",
		// 20
		"感兴趣的活动（第2组）",
		// 21
		"感兴趣的活动（第2组）",
		// 22
		"感兴趣的活动（第2组）",
		// 23
		"感兴趣的活动（第2组）",
		//第二部分 第三组
		// 24
		"感兴趣的活动（第3组）",
		// 25
		"感兴趣的活动（第3组）",
		// 26
		"感兴趣的活动（第3组）",
		// 27
		"感兴趣的活动（第3组）",
		// 28
		"感兴趣的活动（第3组）",
		// 29
		"感兴趣的活动（第3组）",
		// 30
		"感兴趣的活动（第3组）",
		// 31
		"感兴趣的活动（第3组）",
		// 32
		"感兴趣的活动（第3组）",
		// 33
		"感兴趣的活动（第3组）",
		//第二部分 第四组
		// 34
		"感兴趣的活动（第4组）",
		// 35
		"感兴趣的活动（第4组）",
		// 36
		"感兴趣的活动（第4组）",
		// 37
		"感兴趣的活动（第4组）",
		// 38
		"感兴趣的活动（第4组）",
		// 39
		"感兴趣的活动（第4组）",
		// 40
		"感兴趣的活动（第4组）",
		// 41
		"感兴趣的活动（第4组）",
		// 42
		"感兴趣的活动（第4组）",
		// 43
		"感兴趣的活动（第4组）",
		//第二部分 第五组
		// 44
		"感兴趣的活动（第5组）",
		// 45
		"感兴趣的活动（第5组）",
		// 46
		"感兴趣的活动（第5组）",
		// 47
		"感兴趣的活动（第5组）",
		// 48
		"感兴趣的活动（第5组）",
		// 49
		"感兴趣的活动（第5组）",
		// 50
		"感兴趣的活动（第5组）",
		// 51
		"感兴趣的活动（第5组）",
		// 52
		"感兴趣的活动（第5组）",
		// 53
		"感兴趣的活动（第5组）",
		//第二部分 第六组
		// 54
		"感兴趣的活动（第6组）",
		// 55
		"感兴趣的活动（第6组）",
		// 56
		"感兴趣的活动（第6组）",
		// 57
		"感兴趣的活动（第6组）",
		// 58
		"感兴趣的活动（第6组）",
		// 59
		"感兴趣的活动（第6组）",
		// 60
		"感兴趣的活动（第6组）",
		// 61
		"感兴趣的活动（第6组）",
		// 62
		"感兴趣的活动（第6组）",
		// 63
		"感兴趣的活动（第6组）",
		//第三部分 第一组
		// 64
		"擅长或胜任的事情（第1组）",
		// 65
		"擅长或胜任的事情（第1组）",
		// 66
		"擅长或胜任的事情（第1组）",
		// 67
		"擅长或胜任的事情（第1组）",
		// 68
		"擅长或胜任的事情（第1组）",
		// 69
		"擅长或胜任的事情（第1组）",
		// 70
		"擅长或胜任的事情（第1组）",
		// 71
		"擅长或胜任的事情（第1组）",
		// 72
		"擅长或胜任的事情（第1组）",
		// 73
		"擅长或胜任的事情（第1组）",
		//第二组
		// 74
		"擅长或胜任的事情（第2组）",
		// 75
		"擅长或胜任的事情（第2组）",
		// 76
		"擅长或胜任的事情（第2组）",
		// 77
		"擅长或胜任的事情（第2组）",
		// 78
		"擅长或胜任的事情（第2组）",
		// 79
		"擅长或胜任的事情（第2组）",
		// 80
		"擅长或胜任的事情（第2组）",
		// 81
		"擅长或胜任的事情（第2组）",
		// 82
		"擅长或胜任的事情（第2组）",
		// 83
		"擅长或胜任的事情（第2组）",
		//第三组
		// 84
		"擅长或胜任的事情（第3组）",
		// 85
		"擅长或胜任的事情（第3组）",
		// 86
		"擅长或胜任的事情（第3组）",
		// 87
		"擅长或胜任的事情（第3组）",
		// 88
		"擅长或胜任的事情（第3组）",
		// 89
		"擅长或胜任的事情（第3组）",
		// 90
		"擅长或胜任的事情（第3组）",
		// 91
		"擅长或胜任的事情（第3组）",
		// 92
		"擅长或胜任的事情（第3组）",
		// 93
		"擅长或胜任的事情（第3组）",
		//第四组
		// 94
		"擅长或胜任的事情（第4组）",
		// 95
		"擅长或胜任的事情（第4组）",
		// 96
		"擅长或胜任的事情（第4组）",
		// 97
		"擅长或胜任的事情（第4组）",
		// 98
		"擅长或胜任的事情（第4组）",
		// 99
		"擅长或胜任的事情（第4组）",
		// 100
		"擅长或胜任的事情（第4组）",
		// 101
		"擅长或胜任的事情（第4组）",
		// 102
		"擅长或胜任的事情（第4组）",
		// 103
		"擅长或胜任的事情（第4组）",
		//第五组
		// 104
		"擅长或胜任的事情（第5组）",
		// 105
		"擅长或胜任的事情（第5组）",
		// 106
		"擅长或胜任的事情（第5组）",
		// 107
		"擅长或胜任的事情（第5组）",
		// 108
		"擅长或胜任的事情（第5组）",
		// 109
		"擅长或胜任的事情（第5组）",
		// 110
		"擅长或胜任的事情（第5组）",
		// 111
		"擅长或胜任的事情（第5组）",
		// 112
		"擅长或胜任的事情（第5组）",
		// 113
		"擅长或胜任的事情（第5组）",
		//第六组
		// 114
		"擅长或胜任的事情（第6组）",
		// 115
		"擅长或胜任的事情（第6组）",
		// 116
		"擅长或胜任的事情（第6组）",
		// 117
		"擅长或胜任的事情（第6组）",
		// 118
		"擅长或胜任的事情（第6组）",
		// 119
		"擅长或胜任的事情（第6组）",
		// 120
		"擅长或胜任的事情（第6组）",
		// 121
		"擅长或胜任的事情（第6组）",
		// 122
		"擅长或胜任的事情（第6组）",
		// 123
		"擅长或胜任的事情（第6组）",
		//第四部分 第一组
		// 124
		"喜欢的职业（第1组）",
		// 125
		"喜欢的职业（第1组）",
		// 126
		"喜欢的职业（第1组）",
		// 127
		"喜欢的职业（第1组）",
		// 128
		"喜欢的职业（第1组）",
		// 129
		"喜欢的职业（第1组）",
		// 130
		"喜欢的职业（第1组）",
		// 131
		"喜欢的职业（第1组）",
		// 132
		"喜欢的职业（第1组）",
		// 133
		"喜欢的职业（第1组）",
		//第二组
		// 134
		"喜欢的职业（第2组）",
		// 135
		"喜欢的职业（第2组）",
		// 136
		"喜欢的职业（第2组）",
		// 137
		"喜欢的职业（第2组）",
		// 138
		"喜欢的职业（第2组）",
		// 139
		"喜欢的职业（第2组）",
		// 140
		"喜欢的职业（第2组）",
		// 141
		"喜欢的职业（第2组）",
		// 142
		"喜欢的职业（第2组）",
		// 143
		"喜欢的职业（第2组）",
		//第三组
		// 144
		"喜欢的职业（第3组）",
		// 145
		"喜欢的职业（第3组）",
		// 146
		"喜欢的职业（第3组）",
		// 147
		"喜欢的职业（第3组）",
		// 148
		"喜欢的职业（第3组）",
		// 149
		"喜欢的职业（第3组）",
		// 150
		"喜欢的职业（第3组）",
		// 151
		"喜欢的职业（第3组）",
		// 152
		"喜欢的职业（第3组）",
		// 153
		"喜欢的职业（第3组）",
		//第四组
		// 154
		"喜欢的职业（第4组）",
		// 155
		"喜欢的职业（第4组）",
		// 156
		"喜欢的职业（第4组）",
		// 156
		"喜欢的职业（第4组）",
		// 157
		"喜欢的职业（第4组）",
		// 158
		"喜欢的职业（第4组）",
		// 159
		"喜欢的职业（第4组）",
		// 160
		"喜欢的职业（第4组）",
		// 161
		"喜欢的职业（第4组）",
		// 162
		"喜欢的职业（第4组）",
		//第五组
		// 163
		"喜欢的职业（第5组）",
		// 164
		"喜欢的职业（第5组）",
		// 165
		"喜欢的职业（第5组）",
		// 166
		"喜欢的职业（第5组）",
		// 167
		"喜欢的职业（第5组）",
		// 168
		"喜欢的职业（第5组）",
		// 169
		"喜欢的职业（第5组）",
		// 170
		"喜欢的职业（第5组）",
		// 171
		"喜欢的职业（第5组）",
		// 172
		"喜欢的职业（第5组）",
		//第六组
		// 173
		"喜欢的职业（第6组）",
		// 174
		"喜欢的职业（第6组）",
		// 175
		"喜欢的职业（第6组）",
		// 176
		"喜欢的职业（第6组）",
		// 177
		"喜欢的职业（第6组）",
		// 178
		"喜欢的职业（第6组）",
		// 179
		"喜欢的职业（第6组）",
		// 180
		"喜欢的职业（第6组）",
		// 181
		"喜欢的职业（第6组）",
		// 182
		"喜欢的职业（第6组）"
		
	};
 
	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语:本测验量表将帮助您发现和确定自己的职业兴趣和能力特长，从而更好地做出求职择业的决策。如果您已经考虑好或选择好了自己的职业，" +
		"本测验将使您的这种考虑或选择具有理论基础，或向您展示其他合适的职业；如果您至今尚未确定职业方向，" +
		"本测验将帮助您根据自己的情况选择一个恰当的职业目标。本测验共有六个部分，每部分测验都没有时间限制但请您尽快按要求完成。" };

	public QuestionnaireModelOfHPII() {
		super(QuestionnaireCodeEnum.HPII);

		// 在这里进行初始化

		// 问题总数
		super.questionTotal = kQuestionArray.length;

		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(成人)
		final RespondentsInformationOfAdults answerDataSource = new RespondentsInformationOfAdults(getQuestionnaireCodeEnum());
		framePageFragmentFactory = new FillTesterPersonalInformationOfAdultsFragmentFactoryMethod();
		final NonQuestionFramePageModel fillTesterInfoFramePageModel = new NonQuestionFramePageModel(framePageFragmentFactory);
		fillTesterInfoFramePageModel.setAnswerDataSource(answerDataSource);
		super.frameModelList.add(fillTesterInfoFramePageModel);

		// 指导语界面
		final List<String> guidanceLanguageStrings = Arrays.asList(kGuidanceLanguage);
		framePageFragmentFactory = new GuidanceLanguageFragmentFactoryMethod();
		final NonQuestionFramePageModel guidanceLanguageFramePageModel = new NonQuestionFramePageModel(framePageFragmentFactory);
		guidanceLanguageFramePageModel.setQuestionDataSource(guidanceLanguageStrings);
		super.frameModelList.add(guidanceLanguageFramePageModel);

		// 第一部分的过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage1 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel1 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage1);
		transitionPageFrameModel1.setQuestionDataSource("\t\t\t\t\t\n\n\n第一部分  你心目中的理想职业(专业)）");
		super.frameModelList.add(transitionPageFrameModel1);

		// 第一部分的三个输入框,算三道题
		framePageFragmentFactory = new HPIIPartOneFragmentFactoryMethod();
		final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
		questionFrameModel.setQuestionIndex(0);
		super.frameModelList.add(questionFrameModel);

		// 第二部分的过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage2 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel2 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage2);
		transitionPageFrameModel2.setQuestionDataSource("\t\t\t\t第二部分 你所感兴趣的活动\n\n" + "\t\t\t（一共包含6组）");
		super.frameModelList.add(transitionPageFrameModel2);
		int questionIndex = 3;

		// 第二部分的界面 从第四题开始到63题
		for (int i = 1; i < 61; i++) {
			framePageFragmentFactory = new SingleAnswerFragmentFactoryMethod();
			SingleAnswerPageDataSource questionPart2DataSource = new SingleAnswerPageDataSource(kQuestionArray[i], null);
			questionPart2DataSource.setPrompt(kQuestionPromptArray[i]);
			final QuestionFramePageModel questionPart2FrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionPart2FrameModel.setQuestionIndex(questionIndex++);
			questionPart2FrameModel.setQuestionDataSource(questionPart2DataSource);
			questionPart2FrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.true_or_false);
			super.frameModelList.add(questionPart2FrameModel);

		}
		// 第三部分的过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage3 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel3 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage3);
		transitionPageFrameModel3.setQuestionDataSource("\t\t\t\t第三部分  你所擅长或胜任的事情\n\n" + "\t\t\t（一共包含6组）");
		super.frameModelList.add(transitionPageFrameModel3);

		// 第三部分的界面 从第四题开始到63题
		for (int i = 61; i < 121; i++) {
			framePageFragmentFactory = new SingleAnswerFragmentFactoryMethod();
			SingleAnswerPageDataSource questionPart3DataSource = new SingleAnswerPageDataSource(kQuestionArray[i], null);
			questionPart3DataSource.setPrompt(kQuestionPromptArray[i]);
			final QuestionFramePageModel questionPart3FrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionPart3FrameModel.setQuestionIndex(questionIndex++);
			questionPart3FrameModel.setQuestionDataSource(questionPart3DataSource);
			questionPart3FrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.true_or_false);
			super.frameModelList.add(questionPart3FrameModel);

		}

		// 第四部分的过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage4 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel4 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage4);
		transitionPageFrameModel4.setQuestionDataSource("\t\t\t\t第四部分  你所喜欢的职业\n\n" + "\t\t\t（一共包含6组）");
		super.frameModelList.add(transitionPageFrameModel4);

		// 第四部分的界面 从第四题开始到63题
		for (int i = 121; i < 181; i++) {
			framePageFragmentFactory = new SingleAnswerFragmentFactoryMethod();
			SingleAnswerPageDataSource questionPart4DataSource = new SingleAnswerPageDataSource(kQuestionArray[i], null);
			questionPart4DataSource.setPrompt(kQuestionPromptArray[i]);
			final QuestionFramePageModel questionPart4FrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionPart4FrameModel.setQuestionIndex(questionIndex++);
			questionPart4FrameModel.setQuestionDataSource(questionPart4DataSource);
			questionPart4FrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.true_or_false);
			super.frameModelList.add(questionPart4FrameModel);
		}

		// 第五部分的过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage5 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel5 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage5);
		transitionPageFrameModel5.setQuestionDataSource("\t\t\t\t第五部分  对你的能力进行自评\n\n" + "\t\t\t下面两张表是自我评分表，你可以先与同龄人比较一下自己在每一方面的能力，然后经斟酌以后对自己的能力作一评价。分值越大表示你的能力越强。");
		super.frameModelList.add(transitionPageFrameModel5);

		framePageFragmentFactory = new HPIIPartFiveFragmentFactoryMethod();
		final QuestionFramePageModel questionPart5FrameModel = new QuestionFramePageModel(framePageFragmentFactory);
		questionPart5FrameModel.setQuestionIndex(183);
		questionPart5FrameModel.setQuestionDataSource(Arrays.asList(kAnswerSpinner1Array));
		super.frameModelList.add(questionPart5FrameModel);

		framePageFragmentFactory = new HPIIPartFiveFragmentFactoryMethod();
		final QuestionFramePageModel questionPart6FrameModel = new QuestionFramePageModel(framePageFragmentFactory);
		questionPart6FrameModel.setQuestionIndex(189);
		questionPart6FrameModel.setQuestionDataSource(Arrays.asList(kAnswerSpinner2Array));
		super.frameModelList.add(questionPart6FrameModel);

		// 第六部分的过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage6 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel6 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage6);
		transitionPageFrameModel6.setQuestionDataSource("\t\t\t\t第六部分  您所看重的东西——职业价值观\n\n" + "\t\t\t这一部分测验列出了人们在选择工作时通常会考虑的9种因素，现在请您在其中选择。。");
		super.frameModelList.add(transitionPageFrameModel6);

		framePageFragmentFactory = new HPIIPartSixFragmentFactoryMethod();
		final QuestionFramePageModel questionPart7FrameModel = new QuestionFramePageModel(framePageFragmentFactory);
		questionPart7FrameModel.setQuestionIndex(195);
		super.frameModelList.add(questionPart7FrameModel);

	}

	public byte[] getPartOneAnswerResults() {
		return PackAnswerDataTools.getPartOneAnswerResultsOfHPII(this);
	}

	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfHPII(option, this);
	}
}
