package cn.skyduck.questionnaire.questionnaire.EPQ_Children;

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
public class QuestionnaireModelOfEPQ_Children extends FullQuestionnaireModel implements Serializable{

 //问题
 private static final String[] kQuestionArray = new String[]{
	 "1、你喜欢周围有许多使你高兴的事情吗？",
   "2、你爱生气吗？",
   "3、你喜欢伤害你喜欢的人吗？",
   "4、你贪图过别人的便宜吗？",
   "5、与别人交谈时，你几乎总是很快地回答别人的问题吗？",
   "6、你很容易感到厌烦吗？",
   "7、有时你喜欢开一些的确使人伤心的玩笑吗？",
   "8、你总是立即按别人的吩咐去做吗？",
   "9、你宁愿单独一人而不愿和其它小朋友在一道玩吗？",
   "10、有很多念头占据你的头脑使你不能入睡吗？",
   "11、你在学校曾违反过规章吗？",
   "12、你喜欢其他小朋友怕你吗？",
   "13、你很活泼吗？",
   "14、有许多事情使你烦恼吗？",
   "15、在上生物课时你喜欢杀动物吗？",
   "16、你曾拿过别人的东西（甚至一个大头针、一粒钮扣）吗？",
   "17、你有许多朋友吗？",
   "18、你无缘无故地觉得“真是难受”吗？",
   "19、有时你喜欢逗弄动物吗？",
   "20、别人叫你时，你有过装作没听见的事吗？",
   "21、你喜欢在古老的闹鬼的岩洞中探险吗？",
   "22、你常感觉生活非常无味吗？",
   "23、你比大多数小孩更爱吵嘴打架吗？",
   "24、你总是完成家庭作业后才去玩耍吗？",
   "25、你喜欢做一些动作要快的事情吗？",
   "26、你担心会发生一些可怕的事情吗？",
   "27、当听到别的孩子骂怪话，你制止他们吗？ ",
   "28、你能使一个晚会顺利开下去吗？",
   "29、当人们发现你的错误或你工作中的缺点时，你容易伤心吗？",
   "30、看到一只刚辗死的小狗你会难过吗？",
   "31、当你粗鲁失礼时总要向别人道歉吗？",
   "32、是不是有人认为你做了对他们不起的事，他们一直想报复你吗？",
   "33、你认为滑雪好玩吗？",
   "34、你常无缘无故觉得疲乏吗？",
   "35、你很喜欢取笑其他小朋友吗？",
   "36、成人谈话时，你总是保持安静吗？",
   "37、交新朋友时，通常是你采取主动吗？",
   "38、你为某些事情发脾气吗？",
   "39、你常打架吗？",
   "40、你说过别人的坏话或下流话吗？",
   "41、你喜欢给你的朋友讲笑话或滑稽故事吗？",
   "42、你有一阵阵头晕的感觉吗？",
   "43、在学校里，你比大多数儿童更易受罚吗？",
   "44、通常你会拾起别人扔在教室地板上的废纸和垃圾吗？",
   "45、你有许多课余爱好和娱乐吗？",
   "46、你的感情很脆弱吗？",
   "47、你喜欢捉弄别人吗？",
   "48、你总要在饭前洗手吗？",
   "49、在文娱活动中，你宁愿坐着看而不愿亲自参加吗？",
   "50、你常常感到厌倦吗？",
   "51、有时看到一伙人取笑或欺侮一个小孩时，你感到很好玩吗？",
   "52、课堂上你常保持安静，甚至老师不在教室也如此吗？",
   "53、你喜欢干点吓唬人的事吗？",
   "54、你有时不安，以致不能在椅子上静静地坐一会吗？",
   "55、你愿意单独上月球去吗？",
   "56、开会时别人唱歌，你也总是一道唱吗？",
   "57、你喜欢与别的小孩合群吗？",
   "58、你做许多恶梦吗？",
   "59、你的父母对你非常严厉吗？",
   "60、你喜欢不告诉任何人独自离家到外面去漫游吗？",
   "61、你喜欢跳降落伞吗？",
   "62、你如果觉得自己干了件蠢事，你会后悔很久吗？",
   "63、你是否常常觉得人生非常无味？",
   "64、在热闹的晚会上，你能主动参加并尽情玩耍吗？",
   "65、有时你觉得不值得活下去吗？",
   "66、你会为落入猎人陷井的动物而难过吗？",
   "67、你有不尊重父母的行为吗？",
   "68、你常常突然下决心要干很多事情吗？",
   "69、做作业时，你思想开小差吗？",
   "70、当别人孩子对你吼叫时，你也用吼叫来回报他们吗？",
   "71、你喜欢潜水或跳水吗？",
   "72、夜间你因为一些事情苦恼而有过失眠吗？",
   "73、你在学校或图书馆的书上乱写乱画吗？",
   "74、你在家中是否好象老是感到苦恼吗？",
   "75、别人认为你很活泼吗？",
   "76、你常觉得很孤单吗？",
   "77、你对别人的东西总是特别小心爱护吗？ ",
   "78、你总是将自己的全部糖果与别人分吃吗？",
   "79、你很喜欢外出玩耍吗？",
   "80、你在游戏中有过弄虚作假吗？",
   "81、有时你无缘无故感到特别高兴，而有时又无缘无故感到特别悲伤吗？",
   "82、找不到废纸筐时你把废纸扔在地上吗？",
   "83、你经常感到幸福和愉快吗？",
   "84、你做事情往往不先想一想吗？",
   "85、你认为自己是一个无忧无虑的人吗？",
   "86、你常需要热心的朋友与你在一起使你高兴吗？",
   "87、你曾经损坏或遗失过别人的东西吗？",
   "88、你喜欢乘坐开得很快的摩托车吗？" 
 };
 
	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：请如实、尽快的回答下列问题。每个答案都无所谓正确与错误，也无需反复思考，只回答你平时是怎样的。每题都要回答，请不要漏答。" };

	public QuestionnaireModelOfEPQ_Children() {
		super(QuestionnaireCodeEnum.EPQ_Children);

		// 问题总数
		super.questionTotal = kQuestionArray.length;

		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(成人)
		final RespondentsInformationOfAdults answerDataSource = new RespondentsInformationOfAdults(getQuestionnaireCodeEnum());
		answerDataSource.setAgeRange(4, 16);
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
