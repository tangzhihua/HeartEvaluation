package cn.skyduck.questionnaire.questionnaire.ESLI;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.question_frame_fragment.transition_page_1.TransitionPageOneFragmentFactoryMethod;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfESLI extends FullQuestionnaireModel implements Serializable {

	// 问题
	private static final String[] kQuestionArray = new String[] { 
		"1.我没有挚友", "2.跟别人一道时，人家想占我的便宜", 
		"3.我没有伴侣（或男／女朋友）", 
		"4.我不愿因自己的困难而让别人感到有负担", 
		"5.在我的生活中没有人依赖我", 
		"6.任何人跟我都不交心", 
		"7.生活中没有人想要了解我", 
		"8.生活中没有人愿意受到我的连累", 
		"9.我有许多时间独自呆着", 
		"10.我未加入任何社团或组织", 
		"11.我今天跟任何人都未说话", 
		"12.我跟周围的人没有共同话题可谈",
		"13.与别人相处时我并不更多地坦露自己", 
		"14.我不冒社交之险", 
		"15.人们不觉得我有趣", 
		"16.我没觉得有挚友", 
		"17.我害怕相信别人", 
		"18.我没觉得我有伴侣（或男／女朋友）", 
		"19.当分担我的困难时，我的好友觉得是个负担", 
		"20.我觉得别人不依赖我，也不觉得我重要", 
		"21.我觉得我无法跟任何人交心", 
		"22.我觉得不被理解", 
		"23.我觉得求别人并不安全", 
		"24.我感到孤独", 
		"25.我不觉得是任何社团或组织中的一员", 
		"26.我觉得今天跟任何人都没接触", 
		"27.我觉得与别人无话可说",
		"28.我觉得跟别人相处时不再是本来的我", 
		"29.与别人相处时我感到难堪", 
		"30.我不觉得自己有趣"

	};

	// 备选答案
	private static final String[] kAnswerArray = new String[] { "偶尔如此", "有时如此", "经常如此", "通常如此" };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:本问卷的目的是帮助你了解生活中的实际状况以及你对此时的体验如何。请根据最近两周的情况回答。" };

	public QuestionnaireModelOfESLI() {
		super(QuestionnaireCodeEnum.ESLI);

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

		// 上半部分
		for (int i = 0; i < 15; i++) {
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			questionDataSource.setPrompt("提示 : 根据生活中的状况回答");
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.horizontal);
			super.frameModelList.add(questionFrameModel);
		}

		// 过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage);
		transitionPageFrameModel.setQuestionDataSource("下面的15道题根据生活中的体验回答");
		super.frameModelList.add(transitionPageFrameModel);

		// 下半部分
		for (int i = 15; i < kQuestionArray.length; i++) {
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			questionDataSource.setPrompt("提示 : 根据生活中的体验回答");
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.horizontal);
			super.frameModelList.add(questionFrameModel);
		}

	}

	@Override
	public boolean isCanBeIgnoredCurrentQuestion() {
		do {
			if (super.isCanBeIgnoredCurrentQuestion()) {
				break;
			}

			if (getCurrentFrameIndex() == (1 + 1 + 15)) {
				// 过渡界面
				break;
			}

			return false;
		} while (false);
		return true;
	}


}
