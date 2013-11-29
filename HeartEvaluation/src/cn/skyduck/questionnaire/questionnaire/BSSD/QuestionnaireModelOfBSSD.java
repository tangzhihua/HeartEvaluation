package cn.skyduck.questionnaire.questionnaire.BSSD;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.bssd.BSSDPictureFactoryMethod;
import cn.skyduck.question_frame_fragment.bssd.BSSDPicturePageDataSource;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfBSSD extends FullQuestionnaireModel implements Serializable{

 //问题
 public static final int[] kQuestionPictureArray = new int[]{
     R.drawable.bssdpicture9,
     R.drawable.bssdpicture10,
     R.drawable.bssdpicture11,
     R.drawable.bssdpicture24,
     R.drawable.bssdpicture25,
     R.drawable.bssdpicture26,
     R.drawable.bssdpicture27,

 };
 
//问题
private static final String[] kQuestionArray = new String[]{
	   "1、请问现在是哪一年？",
       "2、几月份？",
       "3、几日？",
       "4、星期几？",
       "5、这里是什么市（省）？",
       "6、什么区（县）？",
       "7、什么街道（乡、镇）？",
       "8、什么路？",
       "9、这是什么？",
       "10、这是什么？",
       "11、这是什么？",
       "12、（移去物品，问：）刚才让您看过哪些东西？\n\n" +
            "五分硬币（可不按照顺序回答）",
       "13、钢笔套（可不按照顺序回答）",
       "14、钥匙扣（可不按照顺序回答）",
       "15、1元用去7分剩多少？",
       "16、再用去7分剩多少？（若上题计算错，但此题计算对，也算“正确”）",
       "17、再用去7分剩多少？（若上题计算错，但此题计算对，也算“正确”）",
       "18、我要讲几句话，请听我把话说完，听清楚并照我说的做。“请您用右手来拿纸，然后将纸对折，再把纸放在桌子上。”\n\n" +
            "被测者用右手拿纸",
       "19、被测者将纸对折",
       "20、被测者把纸放在桌子上",
       "21、请再想一下，让您看过什么东西？\n\n" +
            "第一样是五分硬币（可不按顺序回答）",
       "22、第二样是钢笔套（可不按顺序回答）",
       "23、第三样是钥匙扣（可不按顺序回答）",
       "24、请看这是谁的相片",
       "25、请看这是谁的相片",
       "26、（取出图片）请说出这张图的主题。",
       "27、（取出图片）请说出这张图的主题。",
       "28、我国的总理是谁？",
       "29、一年有多少天？",
       "30、新中国哪一年成立？",
   

};

	//备选答案
	private static final String[][] kAnswerArray = new String[][] {
	{ "正  确", "错  误" } };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:老年人常有记忆和注意等方面的问题，下面有一些问题检查您的记忆和注意能力，都很简单，请听清楚再回答。" };

	public QuestionnaireModelOfBSSD() {
		super(QuestionnaireCodeEnum.BSSD);

		// 问题总数
		super.questionTotal = kQuestionArray.length;

		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(成人)
		final RespondentsInformationOfAdults answerDataSource = new RespondentsInformationOfAdults(getQuestionnaireCodeEnum());
		// 文化程度必填
		answerDataSource.addNecessaryOption(RespondentsInformationOfAdults.NecessaryOptionEnum.education);
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

		for (int i = 0; i < kQuestionArray.length; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[0]);
			int pictureResId = 0;
			if (i == 8) {
				pictureResId = kQuestionPictureArray[0];
			} else if (i == 9) {
				pictureResId = kQuestionPictureArray[1];

			} else if (i == 10) {
				pictureResId = kQuestionPictureArray[2];

			} else if (i == 23) {
				pictureResId = kQuestionPictureArray[3];

			} else if (i == 24) {
				pictureResId = kQuestionPictureArray[4];

			} else if (i == 25) {
				pictureResId = kQuestionPictureArray[5];

			} else if (i == 26) {
				pictureResId = kQuestionPictureArray[6];

			} else {
				pictureResId = -1;
			}

			final Object questionDataSource = new BSSDPicturePageDataSource(kQuestionArray[i], pictureResId, anwserList);
			framePageFragmentFactory = new BSSDPictureFactoryMethod();
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			super.frameModelList.add(questionFrameModel);

		}

	}


	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfBoolean(answerDataSource, -1);
	}
}
