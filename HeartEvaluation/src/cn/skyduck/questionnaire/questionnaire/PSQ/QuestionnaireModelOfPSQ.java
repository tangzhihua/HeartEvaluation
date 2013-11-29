package cn.skyduck.questionnaire.questionnaire.PSQ;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.children.FillTesterPersonalInformationFragmentOfChildrenFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.children.RespondentsInformationOfChildren;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
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
public class QuestionnaireModelOfPSQ extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 "1、某种小动作（咬指甲、吸手指拉头发、拉衣服上的布毛）",
   "2、对大人粗鲁无礼",
   "3、在交朋友或保持友谊上存在问题",
   "4、易兴奋、易冲动",
   "5、爱指手划脚",
   "6、吸吮或咬嚼（拇指、衣服、毯子）",
   "7、容易或经常哭叫",
   "8、脾气很大",
   "9、白日梦 ",
   "10、学习困难",
   "11、扭动不安",
   "12、惧怕（新环境、陌生人、陌生地方、上学）",
   "13、坐立不安、经常“忙碌”",
   "14、破坏性 ",
   "15、撒谎或捏造情节 ",
   "16、怕羞",
   "17、造成的麻烦比同龄孩子多",
   "18、说话与同龄儿童不同（像婴儿、口吃、别人不易听懂）",
   "19、抵赖错误或归罪他人",
   "20、好争吵",
   "21、撅嘴和生气",
   "22、偷窃",
   "23、不服从或勉强服从",
   "24、忧虑比别人多（忧虑孤独、疾病、死亡）",
   "25、做事有始无终",
   "26、感情易受损害",
   "27、欺凌别人",
   "28、不能停止重复性活动",
   "29、残忍",
   "30、稚气或不成熟（自己会的事要人帮忙、依缠别人、常需别人鼓励、支持）",
   "31、容易分心或注意力不集中",
   "32、头痛",
   "33、情绪变化迅速剧烈",
   "34、不喜欢或不遵从纪律或约束",
   "35、经常打架",
   "36、与兄弟姊妹不能很好相处",
   "37、在努力中容易泄气",
   "38、妨害其他儿童",
   "39、基本上是一个不愉快的小孩 ",
   "40、有饮食问题（食欲不佳、进食中常跑开）",
   "41、胃痛",
   "42、有睡眠问题（不能入睡、早醒或夜间起床）",
   "43、其他疼痛",
   "44、呕吐或恶心",
   "45、感到在家庭圈子中被欺骗",
   "46、自夸或吹牛",
   "47、让自己受别人欺骗",
   "48、有大便问题（腹泻、排便不规则、便秘）"
 };
 
	// 备选答案
	private static final String[] kAnswerArray = new String[] { "A.无", "B.稍有", "C.相当多", "D.很多" };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：本量表适用于4~17岁儿童及青少年。以下有一些有关您的孩子平时或一贯表现情况的描述，请您仔细阅读，并对适合您孩子情况的答案进行选择。" };
	
	
	public QuestionnaireModelOfPSQ() {
		super(QuestionnaireCodeEnum.PSQ);
		
		// 在这里进行初始化

		
		// 问题总数
		super.questionTotal = kQuestionArray.length;
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(儿童)
		final RespondentsInformationOfChildren answerDataSource = new RespondentsInformationOfChildren(getQuestionnaireCodeEnum());
		answerDataSource.setAgeRange(4, 17);
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
		IQuestionnaireFramePageFragmentFactoryMethod questionFragmentFactory = new SingleAnswerFragmentFactoryMethod();
		final List<String> anwserList = Arrays.asList(kAnswerArray);

		for (int i = 0; i < kQuestionArray.length; i++) {
			final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(questionFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
	}

	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfDefaultChildren(option, this);
	}
}
