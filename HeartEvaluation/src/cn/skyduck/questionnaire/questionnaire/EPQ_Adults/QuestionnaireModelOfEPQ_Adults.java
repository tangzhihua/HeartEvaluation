package cn.skyduck.questionnaire.questionnaire.EPQ_Adults;

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
public class QuestionnaireModelOfEPQ_Adults extends FullQuestionnaireModel implements Serializable{

 //问题
 private static final String[] kQuestionArray = new String[]{
	 "1、你是否有许多不同的业余爱好？",
   "2、你是否在做任何事情以前都要停下来仔细思考？",
   "3、你的心境是否常有起伏？",
   "4、你曾有过明知是别人的功劳而你去接受奖励的事吗？",
   "5、你是否健谈？",
   "6、欠债会使你不安吗？",
   "7、你曾无缘无故觉得“真是难受”吗？",
   "8、你曾贪图过份外之物吗？",
   "9、你是否在晚上小心翼翼地关好门窗？",
   "10、你是否比较活跃？",
   "11、你在见到一小孩或一动物受折磨时是否会感到非常难过？",
   "12、你是否常常为自己不该作而作了的事，不该说而说了的话而紧张吗？",
   "13、你喜欢跳降落伞吗？",
   "14、通常你能在热闹联欢会中尽情地玩吗？",
   "15、你容易激动吗？",
   "16、你曾经将自己的过错推给别人吗？",
   "17、你喜欢会见陌生人吗？",
   "18、你是否相信保险制度是一种好办法？",
   "19、你是一个容易伤感情的人吗？",
   "20、你所有的习惯都是好的吗？",
   "21、在社交场合你是否总不愿露头角？",
   "22、你会服用奇异或危险作用的药物吗？",
   "23、你常有“厌倦”之感吗？",
   "24、你曾拿过别人的东西吗（那怕一针一线）？",
   "25、你是否常爱外出？",
   "26、你是否从伤害你所宠爱的人而感到乐趣？",
   "27、你常为有罪恶之感所苦恼吗？ ",
   "28、你在谈论中是否有时不懂装懂？",
   "29、你是否宁愿去看书而不愿去多见人？",
   "30、你有要伤害你的仇人吗？",
   "31、你觉得自己是一个神经过敏的人吗？",
   "32、对人有所失礼时你是否经常要表示歉意？",
   "33、你有许多朋友吗？",
   "34、你是否喜爱讲些有时确能伤害人的笑话？",
   "35、你是一个多忧多虑的人吗？",
   "36、你在童年是否按照吩咐要做什么便做什么，毫无怨言？",
   "37、你认为你是一个乐天派吗？",
   "38、你很讲究礼貌和整洁吗？",
   "39、你是否总在担心会发生可怕的事情？",
   "40、你曾损坏或遗失过别人的东西吗？",
   "41、交新朋友时一般是你采取主动吗？",
   "42、当别人向你诉苦时，你是否容易理解他们的苦哀？",
   "43、你认为自己很紧张，如同“拉紧的弦”一样吗？",
   "44、在没有废纸篓时，你是否将废纸扔在地板上？",
   "45、当你与别人在一起时，你是否言语很少？",
   "46、你是否认为结婚制度是过时了，应该废止？",
   "47、你是否有时感到自己可怜？",
   "48、你是否有时有点自夸？",
   "49、你是否很容易将一个沉寂的集会搞得活跃起来？",
   "50、你是否讨厌那种小心翼翼地开车的人？",
   "51、你为你的健康担忧吗？",
   "52、你曾讲过什么人的坏话吗？",
   "53、你是否喜欢对朋友讲笑话和有趣的故事？ ",
   "54、你小时候曾对父母粗暴无礼吗？",
   "55、你是否喜欢与人混在一起？",
   "56、你如知道自己工作有错误，这会使你感到难过吗？",
   "57、你患失眠吗？",
   "58、你吃饭前必定洗手吗？",
   "59、你常无缘无故感到无精打采和倦怠吗？",
   "60、和别人玩游戏时，你有过欺骗行为吗？",
   "61、你是否喜欢从事一些动作迅速的工作？",
   "62、你的母亲是一位善良的妇人吗？",
   "63、你是否常常觉得人生非常无味？",
   "64、你曾利用过某人为自己取得好处吗？",
   "65、你是否常常参加许多活动，超过你的时间所允许？",
   "66、是否有几个人总在躲避你？",
   "67、你是否为你的容貌而非常烦恼？",
   "68、你是否觉得人们为了未来有保障而办理储蓄和保险所花的时间太多？",
   "69、你曾有过不如死了为好的愿望吗？",
   "70、如果有把握永远不会被别人发现，你会逃税吗？",
   "71、你能使一个集会顺利进行吗？",
   "72、你能克制自己不对人无礼吗？",
   "73、遇到一次难堪的经历后，你是否在一段很长的时间内还感到难受？",
   "74、你患有“神经过敏”吗？",
   "75、你曾经故意说些什么来伤害别人的感情吗？",
   "76、你与别人的友谊是否容易破裂，虽然不是你的过错？",
   "77、你常感到孤单吗？ ",
   "78、当人家寻你的差错,找你工作中的缺点时,你是否容易在精神上受挫伤？",
   "79、你赴约会或上班曾迟到过吗？",
   "80、你喜欢忙忙碌碌地过日子吗？",
   "81、你愿意别人怕你吗？",
   "82、你是否觉得有时浑身是劲，而有时又是懒洋洋的吗？",
   "83、你有时把今天应做的事拖到明天去做吗？",
   "84、别人认为你是生气勃勃吗？",
   "85、别人是否对你说了许多谎话？",
   "86、你是否容易对某些事物容易冒火？",
   "87、当你犯了错误时，你是否常常愿意承认它？",
   "88、你会为一动物落入圈套被捉拿而感到很难过吗？"    
 };
 
  //指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：请如实、尽快的回答下列问题。每个答案都无所谓正确与错误，也无需反复思考，只回答你平时是怎样的。每题都要回答，请不要漏答。" };
	
	public QuestionnaireModelOfEPQ_Adults() {
		super(QuestionnaireCodeEnum.EPQ_Adults);
		

		// 问题总数
		super.questionTotal = kQuestionArray.length;

		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(成人)
		final RespondentsInformationOfAdults answerDataSource = new RespondentsInformationOfAdults(getQuestionnaireCodeEnum());
		answerDataSource.setAgeRange(17, 200);
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
			final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], null);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.true_or_false);
			super.frameModelList.add(questionFrameModel);
		}

	}

	/**
	 * 检测当前问题是否能够被忽略(能被忽略的问题有很多种情况 : 1.此问题已经回答过; 2.当前帧不是问题帧; 3.已经回答的题目数量已经达到一定数量)
	 * 
	 * @return
	 */
	public boolean isCanBeIgnoredCurrentQuestion() {
		return true;
	}

	/**
	 * 检测本次测试是否有效
	 * 
	 * @return
	 */
	public boolean isTestEffectively() {
		do {
			if (super.getUnansweredQuestionNumberList().size() > 30) {
				// 注意当未答的题目大于等于30个时，不能让受测者点确定，因为那样就白答了
				break;
			}
			return true;
		} while (false);
		return false;
	}
	
	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfBoolean(answerDataSource, 2);
	}
}
