package cn.skyduck.questionnaire.questionnaire.MMSE;

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
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOneFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOnePageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfMMSE extends FullQuestionnaireModel implements Serializable{

 //问题
 public static final int[] kQuestionPictureArray = new int[]{
     R.drawable.mmsepicture14,
     R.drawable.mmsepicture14_2,
     R.drawable.mmsepicture16,
     R.drawable.mmsepicture19
 };
 
  //问题
  private static final String[] kQuestionArray = new String[]{
	   "1、今年的年份是？",
     "2、现在是什么季节？",
     "3、今天是几号？",
     "4、今天是星期几？",
     "5、现在是几月份？",
     "6、你能告诉我，我们在哪里？例如，我们在哪个省、市？",
     "7、你住在什么区（县）？",
     "8、你住在什么街道（乡）？",
     "9、我们现在是第几层楼？",
     "10、这是什么地方？（地址或名称）",
     "11、 现在我要说三样东西的名称，在我讲完之后，请你重复说一遍，请你好好记住这三样东西，因为等一下要再问你的\t（请仔细说清楚，每一样东西一秒钟）。\n\t\t“皮球”、“国旗”、“树木”，请你把这三样东西说一遍(可以不按照顺序回答)。\n\n 11-1、第一项是否正确？(皮球、国旗、树木)",
     "11-2、第二项是否正确？(皮球、国旗、树木)",
     "11-3、第三项是否正确？(皮球、国旗、树木)",
     "12、 现在请你从100减去7，然后从所得的数目再减去7，如此一直计算下去，把每一个答案都告诉我，直到我说“停”为止。\n\n12-1、100第1次减7等于93",
     "12-2、100第2次减7等于86（若上次错，这次对，算“正确”）",
     "12-3、100第3次减7等于79（若上次错，这次对，算“正确”）",
     "12-4、100第4次减7等于72（若上次错，这次对，算“正确”）",
     "12-5、100第5次减7等于65（若上次错，这次对，算“正确”）",
     "13、现在请你告诉我，刚才我要你记住的三样东西是什么? \n\n" +
     "13-1、第一样 （答案：皮球/国旗/树木，可以不按照顺序说）",
     "13-2、第二样 （答案：皮球/国旗/树木，可以不按照顺序说）",
     "13-3、第三样   （答案：皮球/国旗/树木，可以不按照顺序说）",
     "14-1、请问这是什么？",
     "14-2、请问这是什么？",
     "15、现在我要说一句话，请清楚地重复一遍\n，这句话是：“四十四只石狮子”（只许说一遍，只有正确咬字清楚的才记1分）。",
     "16、请照着这卡片所写的去做。",
     "17、 （ 请准备一张白纸，交给受测者。然后说下面一段话。不要重复说明，也不要示范 ）请用右手拿这张纸，再用双手把纸对折，然后将纸放在你的大腿上。\n\n17-1、用右手拿纸",
     "17-2、把纸对折",
     "17-3、放在大腿上",
     "18、请你说一句完整的，有意义的句子(句子必须有主语，动词)",
     "19、这是一张图，请你在纸上照样把它画出来。（让受测者照着下图画）",
};

	//备选答案
	private static final String[][] kAnswerArray = new String[][] {
	{ "正  确", "错  误" ,"拒绝回答" ,"说不会做"},
	{ "合乎标准", "不合乎标准" ,"拒绝" ,"不会做"}};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语:本量表为他评量表。是检查认知智力功能的一些问题，请直接向被试者询问，并根据被试者的实际表现和回答结果进行选择。注意：测验时，不要让其他人干扰检查。\n\n备注：需要准备笔和两张白纸。" 
		};

	public QuestionnaireModelOfMMSE() {
		super(QuestionnaireCodeEnum.MMSE);

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

			if (i == 28) {
				final List<String> anwserList = Arrays.asList(kAnswerArray[1]);
				final Object questionDataSource = new InputAndSingleOnePageDataSource(kQuestionArray[i], anwserList, "记下所叙述句子的全文");
				framePageFragmentFactory = new InputAndSingleOneFragmentFactoryMethod();
				final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionDataSource);
				super.frameModelList.add(questionFrameModel);
			} else {
				final List<String> anwserList = Arrays.asList(kAnswerArray[0]);
				int pictureResId = 0;

				if (i == 21) {
					pictureResId = kQuestionPictureArray[0];
				} else if (i == 22) {
					pictureResId = kQuestionPictureArray[1];

				} else if (i == 24) {
					pictureResId = kQuestionPictureArray[2];

				} else if (i == 29) {
					pictureResId = kQuestionPictureArray[3];
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

	}

	public byte[] getPartOneAnswerResults() {
		return PackAnswerDataTools.getPartOneAnswerResultsOfMMSE(this);
	}
	
	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfMMSE(option, this);
	}
}
