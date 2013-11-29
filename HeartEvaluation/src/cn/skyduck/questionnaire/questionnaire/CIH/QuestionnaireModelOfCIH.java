package cn.skyduck.questionnaire.questionnaire.CIH;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.children.FillTesterPersonalInformationFragmentOfChildrenFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.children.RespondentsInformationOfChildren;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
/**
 * 儿童量表
 * @author skyduck
 *
 */
public class QuestionnaireModelOfCIH extends FullQuestionnaireModel implements Serializable{

	// 问题
	private static final String[] kQuestionArray = new String[]{
	  "1、扭动不停",
    "2、暴怒，不可预料的行为",
    "3、成为问题的易分心或注意力不集中",
    "4、妨碍其它儿童",
    "5、撅嘴和生气",
    "6、情绪变化迅速剧烈",
    "7、坐立不定经常“忙碌”",
    "8、容易兴奋冲动",
    "9、做事有始无终 ",
    "10、努力中易灰心丧气"
 	};
 
 	//备选答案
 	private static final String[] kAnswerArray = new String[]{
 		"无",
	  "稍有",
	  "相当多",
	  "极多"
 	};

 	//指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：本量表适用于3~17岁儿童及青少年。以下有一些关于孩子平时表现的描述，请您仔细阅读，并对适合被测孩子情况的答案进行选择。" };
	
	public QuestionnaireModelOfCIH() {
		super(QuestionnaireCodeEnum.CIH);
		

		// 问题总数
		super.questionTotal = kQuestionArray.length;

		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(儿童)
		final RespondentsInformationOfChildren answerDataSource = new RespondentsInformationOfChildren(getQuestionnaireCodeEnum());
		framePageFragmentFactory = new FillTesterPersonalInformationFragmentOfChildrenFactoryMethod();
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
		IQuestionnaireFramePageFragmentFactoryMethod questionFragmentFactory = new SingleAnswerFragmentFactoryMethod();
		List<String> anwserList = Arrays.asList(kAnswerArray);

		for (int i = 0; i < kQuestionArray.length; i++) {
			final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(questionFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.horizontal);
			super.frameModelList.add(questionFrameModel);
		}

	}

	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfDefaultChildren(option, this);
	}
}
