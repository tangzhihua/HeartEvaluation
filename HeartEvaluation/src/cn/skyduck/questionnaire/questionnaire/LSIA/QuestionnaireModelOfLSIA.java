package cn.skyduck.questionnaire.questionnaire.LSIA;

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
public class QuestionnaireModelOfLSIA extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 	 "1. 当我老了以后发现事情似乎要比原先想象得好。",
     "2. 与我所认识的多数人相比，我更好地把握了生活中的机遇。",
     "3. 现在是我一生中最沉闷的时期。",
     "4. 我现在和年轻时一样幸福。",
     "5. 我的生活原本应该更好些。",
     "6. 现在是我一生中最美好的时光。",
     "7. 我所做的事多半是令人厌烦和单调乏味的。",
     "8. 我估计最近能遇到一些有趣的和令人愉快的事。",
     "9. 我现在做的事和以前做的事一样有趣。",
     "10.	我感到老了、有些累了。",
     "11.	我感到自己确实上了年纪，但我并不为此而烦恼。",
     "12.	回首往事，我相当满足.",
     "13.	即使能改变自己的过去，我也不愿有所改变。",
     "14.	与其它同龄人相比，我曾做出过较多愚蠢的决定。",
     "15.	与其它同龄人相比，我的外表较年轻。",
     "16.	我已经为一个月甚至一年后该做的事制订了计划。",
     "17.	回首往事，我有许多想得到的东西均未得到。",
     "18.	与其它人相比，我惨遭失败的次数太多了。",
     "19.	我在生活中得到了相当多我所期望的东西。",
     "20.	不管人们怎样说，许多普通人是越过越糟，而不是越过越好了。",
 };
 
 	//备选答案
 	private static final String[] kAnswerArray = new String[]{
	    "同意",
	    "不同意",
 	};

 	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语:此量表为自评量表。下面的一些陈述涉及人们对生活的不同感受。请阅读下列陈述，如果你同意该观点，就请选择“同意”；如果不同意该观点，请选择“不同意”；如果无法肯定是否同意，则选择“?”。请务必回答每一个问题。"
		};
	
	public QuestionnaireModelOfLSIA() {
		super(QuestionnaireCodeEnum.LSIA);
		

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
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.true_or_false);
			super.frameModelList.add(questionFrameModel);
		}

	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfBoolean(answerDataSource, 2);
	}

}
