package cn.skyduck.questionnaire.questionnaire.LSAS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.lsas.LSASFactoryMethod;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfLSAS extends FullQuestionnaireModel implements Serializable{

	// 问题
	private static final String[] kQuestionArray = new String[]{
		"1、公众场合打电话",
    "2、参加小组活动",
    "3、公众场所吃东西",
    "4、公共场合与人共饮",
    "5、与重要人物谈话",
    "6、在听众前表演、演示或演讲",
    "7、参加聚会",
    "8、在有人注视下工作",
    "9、被人注视下书写",
    "10、与不太熟悉的人打电话 ",
    "11、与不太熟悉的人交谈",
    "12、与陌生人会面",
    "13、在公共卫生间小便",
    "14、进入已有人就座的房间",
    "15、成为观注的中心",
    "16、会议上发言",
    "17、参加测试",
    "18、对不太熟悉的人表达不同的观点和看法",
    "19、与不大熟悉的人目光对视",
    "20、在小组中汇报",
    "21、试着搭识某人",
    "22、去商店退货",
    "23、组织聚会",
    "24、拒绝推销员的强制推销"
 };
 

 	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语:下面列出了一些社交情境。请仔细阅读每一种情境，然后假设您身在其中，并回答“害怕及焦虑感”和“回避程度”两个问题。"
		};
	
	public QuestionnaireModelOfLSAS() {
		super(QuestionnaireCodeEnum.LSAS);
		
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
		framePageFragmentFactory = new LSASFactoryMethod();

		for (int i = 0; i < kQuestionArray.length; i++) {
			final Object questionDataSource = kQuestionArray[i];
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			super.frameModelList.add(questionFrameModel);
		}

	}

	public byte[] getPartOneAnswerResults() {
		return PackAnswerDataTools.getPartOneAnswerResultsOfLSAS(this);
	}
}
