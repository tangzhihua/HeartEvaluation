package cn.skyduck.questionnaire.questionnaire.HDS;

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
public class QuestionnaireModelOfHDS extends FullQuestionnaireModel implements Serializable{

 //问题
 public static final int[] kQuestionPictureArray = new int[]{
     R.drawable.hdspicture1,
     R.drawable.hdspicture2,
     R.drawable.hdspicture3,
     R.drawable.hdspicture4,
     R.drawable.hdspicture5,
     0,
 };
 
//问题
private static final String[] kQuestionArray = new String[]{
	"1、今天是几月几号(或星期几)(任意一个回答正确即可)",
    "2、这是什么地方",
    "3、您多大岁数(±3年为正确)",
    "4、最近发生什么事情(请事先询问知情者)",
    "5、你出生在哪里",
    "6、中华人民共和国成立年份(±3年为正确)",
    "7、一年有几个月(或一小时有多少分钟)\n(任意一个回答正确即可)",
    "8、国家现任总理是谁",
    "9、问：“100-7等于多少？93-7等于多少？”",
    "10、到背数字\n" +
    "第一组：6    8    2\n" +
    "第二组：3    5    2    9",
    "11、请看一组图片,并说出它们是什么？",
};

	//备选答案
	private static final String[][] kAnswerArray = new String[][] {
	//1
	{ "正  确", "错  误" }, 
	//2
	{ "正  确", "错  误" },
	//3
	{ "正  确", "错  误" },
	//4
	{ "正  确", "错  误" },
	//5
	{ "正  确", "错  误" },
	//6
	{ "正  确", "错  误" },
	//7
	{ "正  确", "错  误" },
	//8
	{ "正  确", "错  误" },
	//9
	{ "答对1个题 ", "答对2个题","都不对" },
	//10
	{ "答对一组", "答对两组","都不对" },
	//11
	{ "对了0或1项", "正确2项","正确3项" ,"正确4项","全部正确"}};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:这是一个他评量表，由医生通过提问的方式对被试进行评定，对被试说明“下面我要问你一些非常简便的问题，测验一下你的注意力和记忆力，请你不要紧张，尽力完成。”" };

	public QuestionnaireModelOfHDS() {
		super(QuestionnaireCodeEnum.HDS);

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
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			int pictureResId = 0;
			if (i == kQuestionArray.length - 1) {
				for (int j = 0; j < kQuestionPictureArray.length; j++) {

					if (j != kQuestionPictureArray.length - 1) {
						final Object questionDataSource = new BSSDPicturePageDataSource(kQuestionArray[i], kQuestionPictureArray[j], Arrays.asList(new String("下一幅图")));
						framePageFragmentFactory = new BSSDPictureFactoryMethod();
						final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
						questionFrameModel.setQuestionIndex(questionIndex);
						questionFrameModel.setQuestionDataSource(questionDataSource);
						super.frameModelList.add(questionFrameModel);
					} else {
						final Object questionDataSource = new BSSDPicturePageDataSource("11．现在回忆一下刚才看到的东西。\n（纸烟、火柴、钥匙、手表、钢笔）", kQuestionPictureArray[j], anwserList);
						framePageFragmentFactory = new BSSDPictureFactoryMethod();
						final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
						questionFrameModel.setQuestionIndex(questionIndex);
						questionFrameModel.setQuestionDataSource(questionDataSource);
						super.frameModelList.add(questionFrameModel);
					}
				}
			} else {
				final Object questionDataSource = new BSSDPicturePageDataSource(kQuestionArray[i], pictureResId, anwserList);
				framePageFragmentFactory = new BSSDPictureFactoryMethod();
				final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionDataSource);
				super.frameModelList.add(questionFrameModel);
			}
		}
	}

	public byte[] getPartOneAnswerResults() {
		return PackAnswerDataTools.getPartOneAnswerResultsOfHDS(this);
	}
}
