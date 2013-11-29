package cn.skyduck.questionnaire.questionnaire.SCSQ;

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
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfSCSQ extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 	 "1．通过工作学习或一些其他活动解脱 ",
     "2．与人交谈，倾诉内心烦恼",
     "3．尽量看到事物好的一面",
     "4．改变自己的想法，重新发现生活中什么重要",
     "5．不把问题看得太严重",
     "6．坚持自己的立场，为自己想得到的斗争",
     "7．找出几种不同的解决问题的方法",
     "8．向亲戚朋友或同学寻求建议",
     "9．改变原来的一些做法或自己的一些问题",
     "10．借鉴他人处理类似困难情景的办法",
     "11．寻求业余爱好，积极参加文体活动",
     "12．尽量克制自己的失望、悔恨、悲伤和愤怒",
     "13．试图休息或休假，暂时把问题（烦恼）抛开",
     "14．通过吸烟、喝酒、服药和吃东西来解除烦恼",
     "15．认为时间会改变现状，唯一要做的便是等待",
     "16．试图忘记整个事情",
     "17．依靠别人解决问题",
     "18．接受现实，因为没有其它办法",
     "19．幻想可能会发生某种奇迹改变现状",
     "20．自己安慰自己"
 };
 
 	//备选答案
 	private static final String[] kAnswerArray = new String[]{
	    "不采取",
	    "偶尔采取",
	    "有时采取",
	    "经常采取"
 	};

 	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:以下列出的是当你在生活中经受到挫折打击，或遇到困难时可能采取的态度和做法。请你仔细阅读每一项，然后选择最适合你的答案。" };
	
	public QuestionnaireModelOfSCSQ() {
		super(QuestionnaireCodeEnum.SCSQ);
		

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
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.horizontal);
			super.frameModelList.add(questionFrameModel);
		}

	}


}
