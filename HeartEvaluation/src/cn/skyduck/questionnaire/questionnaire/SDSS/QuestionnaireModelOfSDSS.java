package cn.skyduck.questionnaire.questionnaire.SDSS;

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
public class QuestionnaireModelOfSDSS extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 	 "1、职业和工作：指工作和职业活动的能力、质量和效率，遵守劳动纪律和规章制度，完成生产任务，在工作中与他人合作等。",
     "2、婚姻职能（仅评已婚者)：指夫妻间相互交流，共同处理家务，对对方负责、相互间的爱、支持和鼓励对方。",
     "3、父母职能（仅评有子女者)：指对子女的生活照顾，情感交流，共同活动，以及关心子女的健康和成长。",
     "4、社会性退缩：指主动回避和他人交往。",
     "5、家庭外的社会活动：指和其它家庭及社会的接触和活动，以及参加集体活动的情况。",
     "6、家庭内活动过少：指在家庭中不干事也不与人说话的情况。",
     "7、家庭职能：指日常家庭活动中应起的作用，如分担家务，参加家庭娱乐，讨论家庭事务等。",
     "8、个人生活自理：指保持个人身体、衣饰、住处的整洁，\n大小便习惯，进食等。",
     "9、对外界的兴趣和关心：了解和关心单位、周围、当地和全国的重要消息和新闻。",
     "10、责任心和计划性：关心本人及家庭成员的进步，努力完成任务，发展新的兴趣或计划。"
 };
 
	// 备选答案
	private static final String[][] kAnswerArray = new String[][] {
			// 1
			{ " (0) 无异常或仅有不引起抱怨或问题的极轻微缺陷", " (1) 水平明显下降，出现问题，或需减轻工作", " (2) 无法工作，或在工作中发生严重问题，可能或已经被处分" },
			// 2
			{ " (0) 无异常或仅有不引起抱怨或问题的极轻微缺陷", " (1) 有争吵，不交流，不支持，逃避责任", " (2) 经常争吵，完全不理对方，或夫妻关系濒于破裂" },
			// 3
			{ " (0) 无异常或仅有不引起抱怨或问题的极轻微缺陷", " (1) 对子女不关心或缺乏兴趣", " (2) 根本不负责任，或不得不由别人替他照顾孩子" },
			// 4
			{ " (0) 无异常或仅有不引起抱怨或问题的极轻微缺陷", " (1) 确有回避他人的情况，经说服仍可克服", " (2) 严重退缩，说服无效" },
			// 5
			{ " (0) 无异常或仅有不引起抱怨或问题的极轻微缺陷", " (1) 不参加某些应该且可能参加的社会活动", " (2) 不参加任何社会活动" },
			// 6
			{ " (0) 无异常或仅有不引起抱怨或问题的极轻微缺陷", " (1) 多数日子至少每天有2 小时什么也不干", " (2) 几乎整天什么都不干" },
			// 7
			{ " (0) 无异常或仅有不引起抱怨或问题的极轻微缺陷", " (1) 不履行家庭义务，较少参加家庭活动", " (2) 几乎不参加家庭活动，不理家人" },
			// 8
			{ " (0) 无异常或仅有不引起抱怨或问题的极轻微缺陷", " (1) 生活自理差", " (2) 生活不能自理，影响自己和他人" },
			// 9
			{ " (0) 无异常或仅有不引起抱怨或问题的极轻微缺陷", " (1) 不大关心", " (2) 完全不问不闻" },
			// 10
			{ " (0) 无异常或仅有不引起抱怨或问题的极轻微缺陷", " (1) 对进步和未来不关心", " (2) 完全不关心进步和未来，没有主动性，对未来不考虑" } 
	};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：以下是一些简单问题，目的是了解受验者在家中和工作单位的一些情况，他（她）能不能做到他应该做的，在以下这些方面是否存在问题或困难。" };
	
	public QuestionnaireModelOfSDSS() {
		super(QuestionnaireCodeEnum.SDSS);
		

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

}
