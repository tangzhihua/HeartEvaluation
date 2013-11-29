package cn.skyduck.questionnaire.questionnaire.LSR;

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
public class QuestionnaireModelOfLSR extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
     "表A．热情与冷漠",
     "表B．决心与不屈服",
     "表C．愿望与已实现目标的统一",
     "表D．自我评价",
     "表E．心境 "
 };
 
 // 备选答案
	private static final String[][] kAnswerArray = new String[][] {
			// 表A
			{ "5分 充满热情地谈到若干项活动及交往。感觉\"当前\"是一生中最美好的时光。喜爱做事情，甚至呆在家里也感到愉快。乐于结交新朋友，追求自我完善。对生活的多个领域表现出热情。",
				"4分 有热情，但仅限于一、二项特殊的兴趣，或仅限于某个阶段。当事情出现差错并可能妨碍其积极享受生活时可表现出失望或生气。即使是很短的时间也要预先做出计划。", 
				"3分 对生活淡泊。似乎从所从事的活动中得不到什么乐趣。追求轻松和有限度的参与。可能与许多活动、事物或人完全隔离。", 
				"2分 认为生活的绝大部分是单调的，可能会抱怨感到疲乏。对许多事感到厌烦。即使参与某项活动也几乎体会不到意义或乐趣。", 
				"1分 生活就象例行公事，认为没有任何事情值得去做。"},
			// 表B
			{ "5分 奋斗不息的态度：宁可流血也不低头。有抗争精神：抵抗到底、决不放弃。积极的人格：坏事和好事都能承受，尽力而为之。不愿改变过去。",
				"4分 能够面对现实。\"我对自己的遭遇没有怨言\"、\"我随时准备承担责任\"、\"只要去寻找就一定能发现生活中美好的一面\"。不介意谈论生活中的困难，但也不过分渲染之。\"人不得不有所放弃\"。", 
				"3分 自述：\"我曾经攀上顶峰也曾跌入低谷，我有时在峰顶、有时却在谷底\"。对生活中遇到的困难流露出遭受外在惩罚及内在惩罚的感觉。", 
				"2分 感到由于得不到休息而未能将事情办得更好，感觉现在的生活与45岁时截然不同，越来越糟了。\"我努力工作，却什么也没有得到\"。", 
				"1分 谈论自己未能承受的打击(外在惩罚)，反复责怪自己(内在惩罚)。被生活所压倒。"},
			// 表C
			{ "5分 感到已完成了自己想做的一切。已经实现或即将实现自己的人生目标。",
				"4分 对生活中失去的机遇感到有些懊悔。\"也许我应该更好地把握住那些机会\"。尽管如此，仍感到生活中自己想做的事情均已完成得相当成功。", 
				"3分 失去的机遇和把握住的机遇各占一半。如果能重新开始人生，宁愿意干一些不同的事情，或许该接受更多的教育。", 
				"2分 为失去重要的机遇而懊悔，但对自己在某一领域(也许是其专业)中所取得的成绩感到满足。", 
				"1分 感到失去了生活中的大多数机遇。"},
			// 表D
			{ "5分 感觉正处在自己的最佳时期。\"我现在做事比以往任何时候做得都好\"，\"没有比现在更美好的时光了\"。认为自己聪明、完美、有吸引力；认为自己对别人很重要。认为有资格随心所欲。",
				"4分 感觉自己比一般人幸运。有把握适应生活的各种艰辛。\"退休只是换个事情做而已\"。对健康方面出现的任何问题均能正确对待。感到有资格随心所欲。\"我想做的事情均能去做，但不会过度劳累自己\"。感到能处理好自己与周围环境的关系。", 
				"3分 认为自己至少能够胜任某一领域，例如工作。但对能否胜任其它领域持怀疑态度。意识到自己已经失去了年轻时的活力，但能够面对现实。感到自己不那么重要了，但并不十分介意。感到自己有所得，也有所付出。随着年纪变老感到身体各方面的状况普遍下降，但并非严重下降。认为自己的健康情况好于平均水平。", 
				"2分 感到别人看不起自己，谈到人变老时往往感到绝望。试图抵御岁月的侵袭。", 
				"1分 感到老了、没有用了，或者快没有用了。贬低自己。\"我已经成了别人的累赘\"。"},
			// 表E
			{ "5分 \"现在是我一生中最美好的时光\"。几乎总是愉快的、乐观的。在旁人眼里其快乐似乎有些脱离现实，但又不象是装模作样。",
				"4分 在生活中寻找快乐，知道快乐之所在并把快乐表现出来。有许多似乎属于青年人的特点。通常是正性的、乐观的情感。", 
				"3分 宛若一艘性情平和的船在缓缓地移动，一些不愉快均被正性心境所中和。总体上为中性到正性的情感，偶尔可表现出急躁。", 
				"2分 希望事情宁静、平和。总体上为中性到负性情感。有轻度的忧郁。 ", 
				"1分 悲观、抱怨、痛苦，感到孤独，许多时间里感到忧郁，有时在与人接触时会发脾气。"}, };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:此量表为他评量表。下面的一些陈述是人们对生活的不同感受，请参照以下陈述，选择比较符合被试实际情况的项目。" };

	public QuestionnaireModelOfLSR() {
		super(QuestionnaireCodeEnum.LSR);
		

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
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}

	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfLSR(answerDataSource);
	}
}
