package cn.skyduck.questionnaire.questionnaire.MUNSH;

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
public class QuestionnaireModelOfMUNSH extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 "1、最近几个月里，你感到满意到极点。",
   "2、最近几个月里，你感到情绪很好。",
   "3、最近几个月里，你感到对生活特别满意。",
   "4、最近几个月里，你感到很走运。",
   "5、最近几个月里，你感到烦恼。",
   "6、最近几个月里，你感到非常孤独或与人疏远。",
   "7、最近几个月里，你感到忧虑或非常不愉快。",
   "8、最近几个月里，你感到担心，因为不知道将会发生什么情况。",
   "9、最近几个月里，你感到自己的生活处境变得艰苦。",
   "10、最近几个月里，一般说来，生活处境变得使你感到满意。",
   "11、最近几个月里，你感到这是你一生中最难受的时期。",
   "12、最近几个月里，你感到象年轻时一样高兴。",
   "13、最近几个月里，你感到所做的大多数事情都令人厌烦或单调。",
   "14、最近几个月里，你感到做的事象以前一样使自己感兴趣。",
   "15、最近几个月里，你感到当回顾自己的一生时，感到相当满意。",
   "16、最近几个月里，你感到随着年龄的增加，一切事情更加糟糕。",
   "17、最近几个月里，你感到孤独吗?",
   "18、最近几个月里，你感到今年一些事情使我烦恼。",
   "19、最近几个月里，如果你能到你想住的地方去住，你愿意到那儿去住吗?",
   "20、最近几个月里，有时你感到活着没意思。",
   "21、最近几个月里，你感到你现在象年轻时一样高兴。",
   "22、最近几个月里，大多数时候你感到生活是艰苦的。",
   "23、最近几个月里，你对你当前的生活满意吗?",
   "24、最近几个月里，你感到自己的健康情况和同龄人比，与他们相同甚至还好些。"
 };
 
 	//备选答案
 	private static final String[] kAnswerArray = new String[]{
 		"是",
	  "不知道或不确定",
	  "否"
 	};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:我们想问一些关于你的日子过得怎么样的问题。请您如实回答即可。" };

	public QuestionnaireModelOfMUNSH() {
		super(QuestionnaireCodeEnum.MUNSH);

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
		final List<String> anwserList = Arrays.asList(kAnswerArray);

		for (int i = 0; i < kQuestionArray.length; i++) {
			final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.horizontal);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			super.frameModelList.add(questionFrameModel);
		}

	}


	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfMUNSH(answerDataSource);
	}
}
