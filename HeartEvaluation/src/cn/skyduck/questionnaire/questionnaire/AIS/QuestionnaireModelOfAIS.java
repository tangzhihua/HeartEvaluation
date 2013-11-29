package cn.skyduck.questionnaire.questionnaire.AIS;

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
public class QuestionnaireModelOfAIS extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 "1.入睡时间(关灯后到睡着的时间)",
	 "2.夜间苏醒",
	 "3.比期望的时间早醒",
	 "4.总睡眠时间",
	 "5.总睡眠质量(无论睡多长)",
	 "6.白天情绪",
	 "7.白天身体功能(体力和精神，如记忆力、认识和注意力等",
	 "8.白天思睡"
 };
 
 	//备选答案
 	private static final String[][] kAnswerArray = new String[][]{
 		//1
 		{"（0）没问题",
     "（1）轻微延迟",
     "（2）显著延迟",
     "（3）延迟严重或没有睡觉"},
 		//2
   {"（0）没问题",
     "（1）轻微影响",
     "（2）显著影响 ",
     "（3）严重影响或没有睡觉"},
 		//3
   {"（0）没问题",
     "（1）轻微提早",
     "（2）显著提早",
     "（3）严重提早或没有睡觉"},
 		//4
   {"（0）足够",
     "（1）轻微不足",
     "（2）显著不足",
     "（3）严重不足或没有睡觉"},
 		//5
   {"（0）满意",
     "（1）轻微不满",
     "（2）显著不满",
     "（3）严重不满或没有睡觉"},
 		//6
   {"（0）正常",
     "（1）轻微低落",
     "（2）显著低落",
     "（3）严重低落"},
 		//7
 		{"（0）足够",
     "（1）轻微影响",
     "（2）显著影响",
     "（3）严重影响"},
 		//8
 		{"（0）无思睡",
     "（1）轻微思睡",
     "（2）显著思睡",
     "（3）严重思睡"} 		
 	};

 	//指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:本量表主要用于记录您对遇到过的睡眠障碍的自我评估。对于以下列出的问题，如果在过去1个月内每星期至少发生3次在您身上，就请您选择相应的自我评估结果项目。" };
	
	public QuestionnaireModelOfAIS() {
		super(QuestionnaireCodeEnum.AIS);
		
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
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
	}


}
