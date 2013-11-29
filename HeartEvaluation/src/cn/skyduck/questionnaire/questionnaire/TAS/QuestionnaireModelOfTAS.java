package cn.skyduck.questionnaire.questionnaire.TAS;

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
public class QuestionnaireModelOfTAS extends FullQuestionnaireModel implements Serializable{

 //问题
 private static final String[] kQuestionArray = new String[]{
	 "1、当一次重大考试就要来临时，我总是在想别人比我聪明得多。",
   "2、如果我将要做一次智能测试，在做之前我会非常焦虑。",
   "3、如果我知道将会有一次智能测试，在此之前我感到很自信、很轻松。 ",
   "4、参加重大考试时，我会出很多汗。",
   "5、考试期间，我发现自己总是在想一些和考试内容无关的事。",
   "6、当一次突然袭击式的考试来到时，我感到很怕。",
   "7、考试期间我经常想到会失败。",
   "8、重大考试后，我经常感到紧张，以致胃不舒服。",
   "9、我对智能考试和期末考试之类的事总感到发怵。",
   "10、在1次考试中取得好成绩似乎并不能增加我在第2次考试中的信心。",
   "11、在重大考试期间，我有时感到心跳很快。",
   "12、考试完毕后我总是觉得可以比实际上做得更好。",
   "13、考试完毕后我总是感到很抑郁。",
   "14、每次期末考试之前，我总有一种紧张不安的感觉。",
   "15、考试时，我的情绪反应不会干扰我考试。",
   "16、考试期间，我经常很紧张，以致本来知道的东西也忘了。",
   "17、复习重要的考试对我来说似乎是一个很大的挑战。",
   "18、对某一门考试，我越努力复习越感到困惑。",
   "19、某门考试一结束，我试图停止有关担忧，但做不到。",
   "20、考试期间，我有时会想我能完成大学学业。",
   "21、我宁愿写一篇论文，而不是参加一次考试，作为某门课程的成绩。",
   "22、我真希望考试不要那么烦人。",
   "23、我相信，如果我单独参加考试而且没有时间限制的话，我会考得更好。",
   "24、想着我在考试中能得多少分影响了我的复习和考试。",
   "25、如果考试能废除的话，我想我能学得更多。",
   "26、我对考试抱这样的态度：“虽然我现在不懂，但我并不担心”。",
   "27、我真不明白为什么有些人对考试那么紧张。",
   "28、我很差劲的想法会干扰我在考试中的表现。",
   "29、我复习期末考试并不比复习平时考试更卖力。",
   "30、尽管我对某门考试复习很好，但我仍然感到焦虑。",
   "31、在重大考试之前，我吃不香。",
   "32、在重大考试前，我发现我的手臂会颤抖。",
   "33、在考试前，我很少有“临时抱佛脚”的需要。",
   "34、校方应该认识到有些学生对考试较为焦虑，而这会影响他们的考试成绩。",
   "35、我认为，考试期间似乎不应该搞得那么紧张。",
   "36、一接触到发下的试卷，我就觉得很不自在。",
   "37、我讨厌老师喜欢搞“突然袭击”式考试的课程。", 
 };
 

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:下列37个句子描述人们对参加考试的感受，请你阅读每一个句子，然后根据你的实际情况(感受)，选择“是”或者“否”。答案没有对错、好坏之分，只求按实际情况填写。可尽量快些作答，但切勿遗漏。" };

	public QuestionnaireModelOfTAS() {
		super(QuestionnaireCodeEnum.TAS);

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
