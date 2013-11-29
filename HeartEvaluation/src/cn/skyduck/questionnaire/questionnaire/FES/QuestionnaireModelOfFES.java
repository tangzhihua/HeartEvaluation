package cn.skyduck.questionnaire.questionnaire.FES;

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
public class QuestionnaireModelOfFES extends FullQuestionnaireModel implements Serializable{

 //问题
 private static final String[] kQuestionArray = new String[]{
	   "1、我们家庭成员都总是互相给予最大的帮助和支持",
     "2、家庭成员总是把自己的感情藏在心里不向其他家庭成员透露 ",
     "3、家中经常吵架 ",
     "4、在家中我们很少自己单独活动",
     "5、家庭成员无论做什么事都是尽力而为的",
     "6、我们家经常谈论政治和社会问题",
     "7、大多数周末和晚上家庭成员都是在家中渡过，而不外出参加社交或娱乐活动",
     "8、我们都认为不管有多大的困难，子女应该首先满足老人的各种需求",
     "9、家中较大的活动都是经过仔细安排的",
     "10、家里人很少强求其他家庭成员遵守家规",
     "11、在家里我们感到很无聊",
     "12、在家里我们想说什么就可以说什么",
     "13、家庭成员彼此之间很少公开发怒",
     "14、我们都非常鼓励家里人具有独立精神",
     "15、为了有好的前途，家庭成员都花了几乎所有的精力",
     "16、我们很少外出听讲座、看戏或去博物馆以及看展览",
     "17、家庭成员常外出到朋友家去玩并在一起吃饭",
     "18、家庭成员都认为做事应顺应社会风气",
     "19、一般来说，我们大家都注意把家收拾得井井有条",
     "20、家中很少有固定的生活规律和家规",
     "21、家庭成员愿意花很大的精力做家里的事",
     "22、在家中诉苦很容易使家人厌烦",
     "23、有时家庭成员发怒时摔东西",
     "24、家庭成员都独立思考问题",
     "25、家庭成员都认为使生活水平提高比其他任何事情都重要",
     "26、我们都认为学会新的知识比其它任何事都重要",
     "27、家中没人参加各种体育活动",
     "28、家庭成员在生活上经常帮助周围的老年人和残疾人",
     "29、在我们家，当需要用某些东西时却常常找不到",
     "30、在我们家吃饭和睡觉的时间都是一成不变的",
     "31、在我们家，有一种和谐一致的气氛",
     "32、家中每个人都可以诉说自己的困难和烦恼",
     "33、家庭成员之间极少发脾气",
     "34、我们家的每个人出入是完全自由的",
     "35、我们都相信在任何情况下竞争是好事",
     "36、我们对文化活动不那么感兴趣",
     "37、我们常看电影或体育比赛，外出郊游等",
     "38、我们认为行贿是一种可以接受的现象",
     "39、在我们家很重视做事要准时",
     "40、我们家做任何事都有固定的方式",
     "41、家里有事时，很少有人自愿去做",
     "42、家庭成员经常公开地表达相互之间的感情",
     "43、家庭成员之间常互相责备和批评",
     "44、家庭成员做事时很少考虑家里其他人的意见",
     "45、我们总是不断反省自己，强迫自己尽力把事情做得一次比一次好",
     "46、我们很少讨论有关科技知识方面的问题",
     "47、我们家每个人都对1～2项娱乐活动特别感兴趣",
     "48、我们认为无论怎么样，晚辈都应该接受长辈的劝导",
     "49、我们家的人常常改变他们的计划",
     "50、我们家非常强调要遵守固定的生活规律和家规",
     "51、家庭成员都总是衷心的互相支持",
     "52、如果在家里说出对家事的不满，会有人觉得不舒服",
     "53、家庭成员有时互相打架",
     "54、家庭成员都依赖家人的帮助去解决他们遇到的困难",
     "55、家庭成员不太关心职务升迁、学习成绩等问题",
     "56、家中有人玩乐器",
     "57、家庭成员除工作学习外，不常进行娱乐活动",
     "58、家庭成员都自愿去做公共环境卫生",
     "59、家庭成员认真地保持自己房间的整洁",
     "60、家庭成员夜间可以随意外出，不必事先与家人商量",
     "61、我们家的集体精神很少",
     "62、我们家可以公开地谈论家里的经济问题",
     "63、家庭成员的意见产生分歧时，我们一直都回避它以保持和气",
     "64、家庭成员希望家里人独立解决问题",
     "65、我们家的人对获得成就并不那么积极",
     "66、家庭成员常去图书馆",
     "67、家庭成员有时按个人爱好或兴趣参加娱乐性学习",
     "68、家庭成员都认为要死守道德教条去办事",
     "69、在我们家，每个人的分工是明确的",
     "70、在我们家，没有严格的规则来约束我们",
     "71、家庭成员彼此之间都一直合得来",
     "72、家庭成员之间讲话时都很注意避免伤害对方的感情",
     "73、家庭成员常彼此想胜过对方",
     "74、如果家庭成员经常独自活动，会伤家里其他人的感情",
     "75、先工作后享受是我们家的老习惯",
     "76、在我们家看电视比读书更重要",
     "77、家庭成员常在业余时间参加家庭以外的社交活动",
     "78、我们认为无论怎么样，离婚是不道德的",
     "79、我们家花钱没有计划",
     "80、我们家的生活规律或家规是不能改变的",
     "81、家庭的每个成员都一直得到充分的关心",
     "82、我们家经常自发地讨论家人很敏感的问题",
     "83、家人有矛盾时，有时会大声争吵",
     "84、在我们家确实鼓励成员都自由活动",
     "85、家庭成员常常与别人比较，看谁的工作学习好",
     "86、家庭成员很喜欢音乐、艺术和文学",
     "87、我们娱乐活动的主要方式是看电视、听广播而不是外出活动",
     "88、我们认为提高家里的生活水平比严守道德标准还要重要",
     "89、我们家饭后必须立即有人去洗碗",
     "90、在家里违反家规者会受到严厉的批评"
 };
 
	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:以下有一些关于家庭情况的问题，请您仔细阅读，并在符合您家庭情况的答案上进行选择，如果有些问题对大部分家庭成员符合，就选择“是”，如果大部分不符合，就选择“否”。" };

	public QuestionnaireModelOfFES() {
		super(QuestionnaireCodeEnum.FES);

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
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfBoolean(answerDataSource, -1);
	}
}
