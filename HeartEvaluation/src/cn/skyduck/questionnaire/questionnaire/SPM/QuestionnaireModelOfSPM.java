package cn.skyduck.questionnaire.questionnaire.SPM;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.picture_single_answer.PictureSingleAnswerFactoryMethod;
import cn.skyduck.question_frame_fragment.picture_single_answer.PictureSingleAnswerPageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfSPM extends FullQuestionnaireModel implements Serializable{

 //问题
 public static final int[] kQuestionPictureArray = new int[]{
     R.drawable.picture1,
     R.drawable.picture2,
     R.drawable.picture3,
     R.drawable.picture4,
     R.drawable.picture5,
     R.drawable.picture6,
     R.drawable.picture7,
     R.drawable.picture8,
     R.drawable.picture9,
     R.drawable.picture10,
     R.drawable.picture11,
     R.drawable.picture12,
     R.drawable.picture13,
     R.drawable.picture14,
     R.drawable.picture15,
     R.drawable.picture16,
     R.drawable.picture17,
     R.drawable.picture18,
     R.drawable.picture19,
     R.drawable.picture20,
     R.drawable.picture21,
     R.drawable.picture22,
     R.drawable.picture23,
     R.drawable.picture24,
     R.drawable.picture25,
     R.drawable.picture26,
     R.drawable.picture27,
     R.drawable.picture28,
     R.drawable.picture29,
     R.drawable.picture30,
     R.drawable.picture31,
     R.drawable.picture32,
     R.drawable.picture33,
     R.drawable.picture34,
     R.drawable.picture35,
     R.drawable.picture36,
     R.drawable.picture37,
     R.drawable.picture38,
     R.drawable.picture39,
     R.drawable.picture40,
     R.drawable.picture41,
     R.drawable.picture42,
     R.drawable.picture43,
     R.drawable.picture44,
     R.drawable.picture45,
     R.drawable.picture46,
     R.drawable.picture47,
     R.drawable.picture48,
     R.drawable.picture49,
     R.drawable.picture50,
     R.drawable.picture51,
     R.drawable.picture52,
     R.drawable.picture53,
     R.drawable.picture54,
     R.drawable.picture55,
     R.drawable.picture56,
     R.drawable.picture57,
     R.drawable.picture58,
     R.drawable.picture59,
     R.drawable.picture60

 };
	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:以下每个题目都有一定的主题，但是每张大的主题图中都缺少一部分，主题图以下有6-8张小图片，若填补在主题图的缺失部分，可以使整个图案合理与完整，请从每题下面所给出的小图片中找出适合大图案的一张。" };
	
	public QuestionnaireModelOfSPM() {
		super(QuestionnaireCodeEnum.SPM);
		

		// 问题总数
		super.questionTotal = kQuestionPictureArray.length;

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
		framePageFragmentFactory = new PictureSingleAnswerFactoryMethod();

		for (int i = 0; i < kQuestionPictureArray.length; i++) {
			int totalAnswer = 0;
			if (i >= 24) {
				totalAnswer = 8;
			}else {
				totalAnswer = 6;
			}
			final Object questionDataSource = new PictureSingleAnswerPageDataSource(kQuestionPictureArray[i], totalAnswer);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			super.frameModelList.add(questionFrameModel);
		}

	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfIntegerType1(answerDataSource);
	}
}
