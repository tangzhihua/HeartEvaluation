package cn.skyduck.questionnaire.questionnaire.CBCL;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.children.FillTesterPersonalInformationFragmentOfChildrenFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.children.RespondentsInformationOfChildren;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.question_frame_fragment.transition_page_1.TransitionPageOneFragmentFactoryMethod;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
/**
 * 儿童量表
 * @author skyduck
 *
 */
public class QuestionnaireModelOfCBCL extends FullQuestionnaireModel implements Serializable{

	private static final String[] kQuestionArray = new String[]{
		    // 第一部分 问题
		    "1、您的孩子有几种最爱好的体育运动项目（例如游泳，棒球等）",
        "2、与同龄儿童相比，他（她）在这些项目上花去时间多少？",
        "3、与同龄儿童相比，他（她）的运动水平如何？",
        "4、在体育运动以外，您的孩子有几种爱好（例如象棋、书法等，不包括看电视、玩电脑）",
        "5、与同龄儿童相比，他（她）在这些项目上花去时间多少？",
        "6、与同龄儿童相比，他（她）的这些爱好水平如何？",
        "7、您的孩子参加的组织、俱乐部、团队或小组有几个？",
        "8、与同龄儿童相比，他（她）在这些组织中的活跃程度如何？",
        "9、您的孩子有无干活或自愿劳动等情况（例如，做家务、到敬老院劳动）",
        "10、与同龄儿童相比，他（她）工作的质量如何？",
        "11、您的孩子有几个要好的朋友",
        "12、您的孩子与这些朋友每周大概在一起几次？",
        "13、与同龄儿童相比，您的孩子在与兄弟姐妹相处时表现如何？",
        "14、与同龄儿童相比，您的孩子在与其他儿童相处时表现如何？",
        "15、与同龄儿童相比，您的孩子在对父母的行为方面表现如何？",
        "16、与同龄儿童相比，您的孩子在自己的工作和游戏方面表现如何？",
        "17、综合各个科目考虑，您的孩子学习成绩属于",
        "18、您的孩子是否在特殊班级（例如，照顾学习吃力的学生的班级）",
        "19、您的孩子是否留过级？",
        "20、你孩子在学校里有无学习或其他问题？（不包括上面三个问题）",
        // 第二部分 问题
        "21、行为幼稚与其年龄不符",
        "22、过敏性症状",
        "23、喜欢争论",
        "24、哮喘病",
        "25、举动像异性",
        "26、随地大便",
        "27、喜欢吹牛或自夸",
        "28、精神不能集中，注意力不能持久",
        "29、老是想某些事情，不能摆脱，强迫观念",
        "30、坐立不安活动过多",
        "31、喜欢缠着大人或过分依赖",
        "32、常说感到寂寞",
        "33、胡里胡涂，如在云里雾中",
        "34、常常哭叫",
        "35、虐待动物",
        "36、虐待、欺侮别人或吝啬",
        "37、好做白日梦或呆想",
        "38、故意伤害自己或企图自杀",
        "39、需要别人经常注意自己",
        "40、破坏自己的东西",
        "41、破坏家里或其他儿童的东西",
        "42、在家不听话",
        "43、在学校不听话",
        "44、不肯好好吃饭",
        "45、不与其他儿童相处",
        "46、有不良行为后不感到内疚",
        "47、易嫉妒",
        "48、好吃不能作为食物的东西",
        "49、除怕上学外，还害怕某些动物、处境或地方",
        "50、怕上学",
        "51、怕自己想坏念头或做坏事",
        "52、觉得自己必须十全十美",
        "53、觉得或抱怨没有人喜欢自己",
        "54、觉得别人存心捉弄自己",
        "55、觉得自己无用或有自卑感",
        "56、身体经常弄伤，容易出事故",
        "57、经常打架",
        "58、常被人戏弄",
        "59、爱和出麻烦的儿童在一起",
        "60、听到某些实际上没有的声音",
        "61、冲动或行为粗鲁",
        "62、喜欢孤独",
        "63、撒谎或欺骗",
        "64、咬指甲",
        "65、神经过敏，容易激动或紧张",
        "66、动作紧张或带有抽动性",
        "67、做恶梦",
        "68、不被其他儿童喜欢",
        "69、便秘",
        "70、过度恐惧或担心",
        "71、感到头昏",
        "72、过份内疚",
        "73、吃得过多",
        "74、过份疲劳",
        "75、身体过重",
        "76、找不到原因的躯体症状：疼痛",
        "77、找不到原因的躯体症状：头痛",
        "78、找不到原因的躯体症状：恶心想吐",
        "79、找不到原因的躯体症状：眼睛问题（不包括近视及器质性眼病）",
        "80、找不到原因的躯体症状：发疹或其它皮肤病",
        "81、找不到原因的躯体症状：腹部疼痛或绞痛",
        "82、找不到原因的躯体症状：呕吐",
        "83、找不到原因的躯体症状：其它以上未提及的躯体症状",
        "84、对别人身体进行攻击",
        "85、挖鼻孔、皮肤或身体其他部分",
        "86、公开玩弄自己的生殖器",
        "87、过多地玩弄自己的生殖器",
        "88、功课差",
        "89、动作不灵活",
        "90、喜欢和年龄较大的儿童在一起",
        "91、喜欢和年龄较小的儿童在一起",
        "92、不肯说话",
        "93、不断重复某些动作，强迫行为",
        "94、离家出走",
        "95、经常尖叫",
        "96、守口如瓶，有事不说出来",
        "97、看到某些实际上没有的东西",
        "98、感到不自然或容易发窘",
        "99、玩火（包括玩火柴或打火机）",
        "100、性方面的问题",
        "101、夸耀自己或胡闹",
        "102、害羞或胆小",
        "103、比大多数孩子睡得少",
        "104、比大多数孩子睡得多（不包括赖床）",
        "105、玩弄粪便",
        "106、言语问题（例如，口吃）",
        "107、茫然凝视",
        "108、在家偷东西",
        "109、在外偷东西",
        "110、收藏自己不需要的东西（不包括集邮等良性爱好）",
        "111、怪异行为（不包括其它条已提到的）",
        "112、怪异想法（不包括其它条已提到的）",
        "113、固执、绷着脸或容易激怒",
        "114、情绪突然变化",
        "115、常常生气",
        "116、多疑",
        "117、咒骂或讲粗话",
        "118、声言要自杀",
        "119、说梦话或有梦游",
        "120、话太多",
        "121、常戏弄他人",
        "122、乱发脾气或脾气暴躁",
        "123、对性的问题想得太多",
        "124、威胁他人",
        "125、吮吸大拇指",
        "126、过分要求整齐清洁",
        "127、睡眠不好",
        "128、逃学",
        "129、不够活跃，动作迟钝或精力不足",
        "130、闷闷不乐，悲伤或抑郁",
        "131、说话声音特别大",
        "132、喝酒或使用成瘾药",
        "133、损坏公物",
        "134、白天遗尿",
        "135、夜间遗尿",
        "136、爱哭诉",
        "137、希望成为异性",
        "138、孤独、不合群",
        "139、忧虑重重",
        "140、是否有上面未提及的其它问题？情况怎样？"
	};
 
	// 答案
	private static final String[][] kAnswerArray = new String[][] {
			// 1
			{ "A、无爱好", "B、一种", "C、两种", "D、三种及以上" },
			// 2
			{ "A、较少", "B、一般 ", "C、较多" },
			// 3
			{ "A、较低", "B、一般", "C、较高" },
			// 4
			{ "A、无爱好", "B、一种", "C、两种", "D、三种及以上" },
			// 5
			{ "A、较少", "B、一般", "C、较多" },
			// 6
			{ "A、较低", "B、一般", "C、较高" },
			// 7
			{ "A、无", "B、一个", "C、两个", "D、三个及以上" },
			// 8
			{ "A、较差", "B、一般", "C、较高" },
			// 9
			{ "A、无", "B、一种", "C、两种", "D、三种及以上" },
			// 10
			{ "A、较差", "B、一般", "C、较好" },
			// 11
			{ "A、无", "B、1个", "C、2~3个", "D、4个及以上" },
			// 12
			{ "A、不到1次", "B、1~2次", "C、3次及以上" },
			// 13
			{ "A、较差 ", "B、差不多", "C、较好" },
			// 14
			{ "A、较差 ", "B、差不多", "C、较好" },
			// 15
			{ "A、较差 ", "B、差不多", "C、较好" },
			// 16
			{ "A、较差 ", "B、差不多", "C、较好" },
			// 17
			{ "A、不及格", "B、中等以下", "C、中等", "D、中等以上" },
			// 18
			{ "A、不是", "B、是" },
			// 19
			{ "A、没有", "B、留过" },
			// 20
			{ "A、没有", "B、有过" },
			// 第三部分 所有答案 相同
			{ "A、无此项表现 ", "B、一般", "C、明显有或经常有此项表现" } };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：本量表为家长用量表，适用于6~16岁儿童。" };

	public QuestionnaireModelOfCBCL() {
		super(QuestionnaireCodeEnum.CBCL);

		// 在这里进行初始化

		// 问题总数
		super.questionTotal = kQuestionArray.length;

		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(儿童)
		final RespondentsInformationOfChildren answerDataSource = new RespondentsInformationOfChildren(getQuestionnaireCodeEnum());
		framePageFragmentFactory = new FillTesterPersonalInformationFragmentOfChildrenFactoryMethod();
		final NonQuestionFramePageModel fillTesterInfoFramePageModel = new NonQuestionFramePageModel(framePageFragmentFactory);
		fillTesterInfoFramePageModel.setAnswerDataSource(answerDataSource);
		super.frameModelList.add(fillTesterInfoFramePageModel);

		// 指导语界面
		final List<String> guidanceLanguageStrings = Arrays.asList(kGuidanceLanguage);
		framePageFragmentFactory = new GuidanceLanguageFragmentFactoryMethod();
		final NonQuestionFramePageModel guidanceLanguageFramePageModel = new NonQuestionFramePageModel(framePageFragmentFactory);
		guidanceLanguageFramePageModel.setQuestionDataSource(guidanceLanguageStrings);
		super.frameModelList.add(guidanceLanguageFramePageModel);

		int questionIndex = 0;
		framePageFragmentFactory = new SingleAnswerFragmentFactoryMethod();

		// 第一部分 问题
		for (int i = 0; i < 20; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			questionDataSource.setPrompt("第一部分 社会能力");
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}

		// 过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage);
		transitionPageFrameModel.setQuestionDataSource("\t\t\t\t第二部分 行为问题 \n\n\t\t\t\t以下，是描述您孩子可能有的行为。只根据最近半年内的情况做选择。如果您对某个问题非常关心，请点击“标记此项”按钮。");
		super.frameModelList.add(transitionPageFrameModel);

		// 第二部分 问题
		for (int i = 20; i < kQuestionArray.length; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[kAnswerArray.length - 1]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			questionDataSource.setPrompt("第二部分 行为问题");
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfIntegerType1(answerDataSource);
	}
	
	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfCBCL(option, this);
	}
}
