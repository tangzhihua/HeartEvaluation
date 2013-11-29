package cn.skyduck.questionnaire.questionnaire.BPRS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfBPRS extends FullQuestionnaireModel implements Serializable{

	// 问题 标题
	private static final String[] kQuestionArray = new String[]{
		 "1、关心躯体健康：指对自身健康的过分关心，不考虑其主诉有无客观基础。",
     "2、焦虑：指精神性焦虑，即对当前及未来情况的担心，恐惧或过分关注。",
     "3、情感交流障碍：指与检查者之间如同存在无形隔膜，无法实现正常的情感交流。",
     "4、概念紊乱：指联想散漫、零乱和解体的程度。",
     "5、罪恶观念：指对以往言行的过分关心内疚和悔恨。",
     "6、紧张：指焦虑性运动表现。",
     "7、装相和作态：指不寻常的或不自然的运动性行为。",
     "8、夸大：即过分自负，确信具有不寻常的才能和权力等。",
     "9、心境抑郁：即心境不佳、悲伤、沮丧或情绪低落的程度。",
     "10、敌对性：指对他人（不包括检查者）的仇恨、敌对和蔑视。",
     "11、猜疑：指检查当时认为有人正在或曾经来意地对待他。",
     "12、幻觉：指没有相应外界刺激的感知。",
     "13、动作迟缓：指言语、动作和行为的减少和缓慢。",
     "14、不合作：指会谈时对检查者的对立、不友好、不满意或不合作。",
     "15、不寻常思维内容：即荒谬古怪的思维内容。",
     "16、情感平淡：指情感基调低，明显缺乏相应的正常情感反应。",
     "17、兴奋：指情感基调增高，激动，对外界反应增强。",
     "18、定向障碍：指对人物、地点或时间分辨不清。",
     "X1、自知力障碍：指对自身精神疾病、精神症状或不正常言行缺乏认识。",
     "X2、工作不能：指对日常工作或活动的影响。"
	};
	
	// 问题 提示
	private static final String[] kQuestionPromptArray = new String[]{
		// 1
		"提示 : 依据病人口头叙述",
		// 2
		"提示 : 依据病人口头叙述",
		// 3
		"提示 : 依据病人观察",
		// 4
		"提示 : 依据病人口头叙述",
		// 5
		"提示 : 依据病人口头叙述",
		// 6
		"提示 : 依据病人观察",
		// 7
		"提示 : 依据病人观察",
		// 8
		"提示 : 依据病人口头叙述",
		// 9
		"提示 : 依据病人口头叙述",
		// 10
		"提示 : 依据病人口头叙述",
		// 11
		"提示 : 依据病人口头叙述",
		// 12
		"提示 : 依据病人口头叙述",
		// 13
		"提示 : 依据病人观察",
		// 14
		"提示 : 依据病人观察",
		// 15
		"提示 : 依据病人口头叙述",
		// 16
		"提示 : 依据病人观察",
		// 17
		"提示 : 依据病人观察",
		// 18
		"提示 : 依据病人口头叙述",
		// X1
		"",
		// X2
		""
	};
 
	// 备选答案
	private static final String[][] kAnswerArray = new String[][]{
		// 1
		{ " (1) 无",
      " (2) 多少提到自身健康情况，但临床意义不能肯定",
      " (3) 过分关心自身健康的情况虽轻，但临床意义已可肯定",
      " (4) 显然对自己健康过分关心或有疑病观念",
      " (5) 明显突出的疑病观念或部分性疑病妄想",
      " (6) 疑病妄想",
      " (7) 疑病妄想明显影响行为"},
		// 2
		{ " (1) 无",
      " (2) 多少有些精神性焦虑体验，但其临床意义不肯定",
      " (3) 精神性焦虑虽轻，但临床意义已可肯定",
      " (4) 显然有精神性焦虑，但不很突出",
      " (5) 明显突出的精神性焦虑，如大部分时间存在精神性焦虑或有时存在明显的精神性焦虑，因此感到痛苦",
      " (6) 比(5)更严重持久，如大部分的时间都存在明显的精神性焦虑",
      " (7) 几乎所有时间都存在精神性焦虑"},
		// 3
		{ " (1) 无",
      " (2) 多少观察到一点情感交流障碍，但临床意义不肯定",
      " (3) 情感交流障碍虽轻，但临床意义已可肯定",
      " (4) 显然观察到受检者缺乏情感交流和感受到相互间的隔膜感，但情感交流无明显困难",
      " (5) 明显突出的情感交流障碍，例如交流中应答基本切 ，但很少眼神交流，受检者眼睛往往看着地板或面向一侧",
      " (6) 比(5)更严重持久，几乎使交谈难以进行",
      " (7) 情感交流的麻痹状态，例如表现得对交谈漠不关心或不参与交谈，有时“两眼凝神不动”"},
		// 4
		{ " (1) 无",
      " (2) 似乎有点联想障碍，但不能肯定其临床意义",
      " (3) 联想障碍虽轻，但临床意义忆可肯定",
      " (4) 显然有联想松弛，但不很突出",
      " (5) 明显突出的联想松弛或可以查得并有临床意义的思维破裂",
      " (6) 典型的思维破裂",
      " (7) 思维破裂导致交谈很困难或言语不连贯"},
		// 5
		{ " (1) 无",
      " (2) 似乎有点自责自罪，但临床意义不肯定",
      " (3) 自责自罪虽轻，但临床意义已可肯定",
      " (4) 显然有自责自罪观念，但不很突出",
      " (5) 明显突出的自责自罪观念或罪恶妄想为总分妄想",
      " (6) 典型的罪恶妄想",
      " (7) 极重：罪恶妄想明显影响行为，如引起绝食等"},
		// 6
		{ " (1) 无",
      " (2) 似乎有点焦虑性运动表现，但临床意义不能肯定",
      " (3) 焦虑性运动表现虽轻，但临床意义可肯定",
      " (4) 有静坐不能，常有手脚不停的表现，拧手、拉扯衣服和伸屈下肢等",
      " (5) 较(4)的频度与强度明显增加，并在交谈中多次站立",
      " (6) 来回踱步，使交谈明显受到影响",
      " (7) 焦虑性运动使交谈几乎无法进行"},
		// 7
		{ " (1) 无",
      " (2) 多少有点装相作态，但临床意义不能肯定",
      " (3) 装相作态虽然很轻，但临床意义可以肯定",
      " (4) 显而易见的装相作态，例如有时肢体置于不自然的位置或伸舌或扮鬼脸或摇摆身体等",
      " (5) 明显突出的装相作态",
      " (6) 比(5)更频繁更严重的装相作态，例如交谈过程几乎一直可见到怪异动作与姿势",
      " (7) 突出而且持续的装相作态几乎使交谈无法进行"},
		// 8
		{ " (1) 无",
      " (2) 多少有点自负，但临床意义不能肯定",
      " (3) 自负夸大虽然很轻，但临床意义已可肯定",
      " (4) 有夸大观念",
      " (5) 明显突出的夸大观念或部分性夸大妄想",
      " (6) 典型的夸大妄想",
      " (7) 夸大妄想明显影响行为"},
		// 9
		{ " (1) 无",
      " (2) 似乎有点抑郁，但临床意义不能肯定",
      " (3) 抑郁虽轻，但临床意义已肯定",
      " (4) 显而易见的抑郁体验，例如自述经常感到心境抑郁，有时哭泣",
      " (5) 明显突出的心境抑郁，例如较持久的抑郁或有时感到很抑郁为此极为痛苦",
      " (6) 比(5)更严重持久，例如几乎一直感到很抑郁，因此极为痛苦",
      " (7) 严重的心境抑郁体验或表现明显影响行为，例如交谈中抑郁哭泣明显影响交谈"},
		// 10
		{ " (1) 无",
      " (2) 似乎对交谈者以外的别人有点敌意，但临床意义不能肯定",
      " (3) 敌意虽轻，但临床意义可以肯定",
      " (4) 交谈内容明显谈到对别人的敌意性并感到愤恨",
      " (5) 经常对别人感到愤恨并策划过报复计划",
      " (6) 严重：较(5)更严重和更经常，或已经有过多次咒骂或一、二次斗殴打架，但无需医学处理的损伤性后果",
      " (7) 敌意性明显影响行为，例如多次殴斗打架或造成需要医学处理的后果"},
		// 11
		{ " (1) 无",
      " (2) 多少有点猜疑，但临床意义不能肯定",
      " (3) 猜疑体验虽轻，但临床意义已可肯定",
      " (4) 有牵连观念或被害观念",
      " (5) 明显突出的牵连观念或被害观念或关系妄想、或部分性被害妄想",
      " (6) 典型的关系妄想或被害妄想",
      " (7) 关系妄想或被害妄想明显影响行为"},
		// 12
		{ " (1) 无",
      " (2) 可疑的幻觉，但临床意义不能肯定",
      " (3) 幻觉虽少，但临床意义可肯定",
      " (4) 幻觉检验清晰，且一周内至少有过3天曾出现幻觉",
      " (5) 一周内至少有过4天出现清晰的幻觉",
      " (6) 一周内至少有5天出现清晰的幻觉，并对其行粗相当影响，例如难以集中思想以致影响工作",
      " (7) 频繁幻觉明显影响其行为。例如受命令性幻听支配产生自杀行为或攻击别人"},
		// 13
		{ " (1) 无",
      " (2) 多少有点动作迟缓，但临床意义不肯定",
      " (3) 动作迟缓虽轻，但临床意义可肯定",
      " (4) 显而易见的动作迟缓，例如语流减慢，动作减少较明显，但并非很不自然",
      " (5) 明显突出的动作迟缓，言语迟缓，使交谈发生困难",
      " (6) 比(5)更为严重和持久，使交谈很困难",
      " (7) 缄默木僵，使交谈几乎无法进行或不能进行"},
		// 14
		{ " (1) 无",
      " (2) 多少有点不合作，但临床意义不能肯定",
      " (3) 不合作的表现虽轻，但临床意义已可肯定",
      " (4) 显而易见的不合作，如交谈不愿作自发的交谈，应答显得勉强简单，易感到对交谈者和交谈场合的不友好",
      " (5) 明显突出的不合作，在整个交谈中都显得不友好，使交谈发生困难",
      " (6) 比(5)更为严重，使交谈很困难，例如拒绝回答很多问题，不但表现不友好，而且公然抗拒和表现针锋相对的愤恨",
      " (7) 不合作使交谈几乎无法进行"},
		// 15
		{ " (1) 无",
      " (2) 多少有点异常思维内容，但临床意义不肯定",
      " (3) 异常思维内容程度虽轻，但临床意义已可肯定",
      " (4) 显然存在观念性异常思维内容，但不很突出",
      " (5) 明显突出的观念性异常思维内容或部分妄想",
      " (6) 典型的妄想",
      " (7) 妄想明显支配行为"},
		// 16
		{ " (1) 无",
      " (2) 多少有点情感平淡，但临床意义不能肯定",
      " (3) 情感平淡虽轻，但临床意义已可肯定",
      " (4) 显而易见的情感平淡，如面部表情减弱，语调较低平，手势较少",
      " (5) 明显突出的情感平淡，如表情呆板、语声单调和手势少",
      " (6) 交谈中对大部分事情均漠不关心、无动于衷",
      " (7) 为情感流露的麻痹状态，例如整个交谈中，完全缺乏表情姿势，语声极为单调，对任何事物漠不心关、无动于衷"},
		// 17
		{ " (1) 无",
      " (2) 多少有点兴奋，但临床意义不肯定",
      " (3) 兴奋虽轻，但临床意义已可肯定",
      " (4) 显而易见的兴奋，但不很突出",
      " (5) 兴奋明显突出，如情绪高涨，语声高，手势增多，有时易激惹，使交谈发生困难",
      " (6) 比(5)更严重持久，使交谈很困难",
      " (7) 情况激怒或欣快自得，言行明显增多，使交谈不得不终止"},
		// 18
		{ " (1) 无",
      " (2) 似有定向错误，但临床意义不太肯定",
      " (3) 定向障碍虽轻，但临床意义已可肯定",
      " (4) 显而易见的定向错误，但不很突出",
      " (5) 明显突出的定向错误",
      " (6) 严重：比(5)更严重持久的定向错误，如交谈发现时间、地点、人物定向几乎无一正确",
      " (7) 定向障碍而无法进行交谈"},
		// X1
		{ " (1) 无",
      " (2) 似乎有点自知力障碍但临床意义不能肯定",
      " (3) 自知力障碍虽轻，但临床意义已可肯定",
      " (4) 显然有自知力障碍，但不很突出",
      " (5) 大部分自知力丧失",
      " (6) 自知力基本丧失",
      " (7) 完全无自知力"},
		// X2
		{	" (1) 无",
      " (2) 多少有点工作不能，但临床意义不肯定",
      " (3) 工作不能虽轻，但临床意义已可确定",
      " (4) 工作学习兴趣丧失，不能坚持正常工作学习，住院时参加活动比其它病人少",
      " (5) 明显突出的工作不能。如工作学习时间减少，成效明显降低，住院者活动明显减少",
      " (6) 比(5)更严重持久，例如基本停止工作学习。住院者大部分时间不能加活动",
      " (7) 停止工作学习，住院者不参加所有活动"}
	};

	//指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：此量表为他评量表。一般评定时间范围为最近1周。" };
	
	public QuestionnaireModelOfBPRS() {
		super(QuestionnaireCodeEnum.BPRS);
		
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
		
		int questionIndex = 0;
		framePageFragmentFactory = new SingleAnswerFragmentFactoryMethod();

		for (int i = 0; i < kQuestionArray.length; i++) {
			List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			questionDataSource.setPrompt(kQuestionPromptArray[i]);
			QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
	}

	@Override
	public boolean isCanBeIgnoredCurrentQuestion() {
		return true;
	}

	@Override
	public boolean isTestEffectively() {
		return true;
	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfIntegerType1(answerDataSource);
	}
}
