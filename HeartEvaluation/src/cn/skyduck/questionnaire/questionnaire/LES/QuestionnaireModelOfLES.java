package cn.skyduck.questionnaire.questionnaire.LES;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.les.LES1MoreSelectionFactoryMethod;
import cn.skyduck.question_frame_fragment.les.LES2MoreSelectionFactoryMethod;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfLES extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 	 "1、恋爱或订婚",
     "1、恋爱或订婚",
     "2、恋爱失败、破裂",
     "2、恋爱失败、破裂",
     "3、结婚",
     "3、结婚",
     "4、自己（爱人）怀孕",
     "4、自己（爱人）怀孕",
     "5、自己（爱人）流产",
     "5、自己（爱人）流产",
     "6、家庭增添新成员",
     "6、家庭增添新成员",
     "7、与爱人父母不和",
     "7、与爱人父母不和",
     "8、夫妻感情不好",
     "8、夫妻感情不好",
     "9、夫妻分居（因不和）",
     "9、夫妻分居（因不和）",
     "10、夫妻两地分居（工作需要）",
     "10、夫妻两地分居（工作需要）",
     "11、性生活不满意或独身",
     "11、性生活不满意或独身",
     "12、配偶一方有外遇",
     "12、配偶一方有外遇",
     "13、夫妻重归于好",
     "13、夫妻重归于好",
     "14、超指标生育",
     "14、超指标生育",
     "15、本人（爱人）作绝育手术",
     "15、本人（爱人）作绝育手术",
     "16、配偶死亡",
     "16、配偶死亡",
     "17、离婚",
     "17、离婚",
     "18、子女升学（就业）失败",
     "18、子女升学（就业）失败",
     "19、子女管教困难",
     "19、子女管教困难",
     "20、子女长期离家",
     "20、子女长期离家",
     "21、父母不和",
     "21、父母不和",
     "22、家庭经济困难",
     "22、家庭经济困难",
     "23、欠债1万元以上",
     "23、欠债1万元以上",
     "24、经济情况显著改善",
     "24、经济情况显著改善",
     "25、家庭成员重病、重伤",
     "25、家庭成员重病、重伤",
     "26、家庭成员死亡",
     "26、家庭成员死亡",
     "27、本人重病或重伤",
     "27、本人重病或重伤",
     "28、住房紧张",
     "28、住房紧张",
     "29、待业、无业",
     "29、待业、无业",
     "30、开始就业",
     "30、开始就业",
     "31、高考失败",
     "31、高考失败",
     "32、扣发资金或罚款",
     "32、扣发资金或罚款",
     "33、突出的个人成就",
     "33、突出的个人成就",
     "34、晋升、提级",
     "34、晋升、提级",
     "35、对现职工作不满意",
     "35、对现职工作不满意",
     "36、工作学习中压力大（如成绩不好）",
     "36、工作学习中压力大（如成绩不好）",
     "37、与上级关系紧张",
     "37、与上级关系紧张",
     "38、与同事邻居不和",
     "38、与同事邻居不和",
     "39、第一次远走他乡异国",
     "39、第一次远走他乡异国",
     "40、生活规律重大变动（饮食睡眠规律改变）",
     "40、生活规律重大变动（饮食睡眠规律改变）",
     "41、本入退休离休或未安排具体工作",
     "41、本入退休离休或未安排具体工作",
     "42、好友重病或重伤",
     "42、好友重病或重伤",
     "43、好友死亡",
     "43、好友死亡",
     "44、被人误会、错怪、诬告、议论",
     "44、被人误会、错怪、诬告、议论",
     "45、介入民事法律纠纷",
     "45、介入民事法律纠纷",
     "46、被拘留、受审",
     "46、被拘留、受审",
     "47、失窃、财产损失",
     "47、失窃、财产损失",
     "48、意外惊吓、发生事故、自然灾害",
     "48、意外惊吓、发生事故、自然灾害"
 };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语：下面是每个人都有可能遇到的一些日常生活事件，究竟是好事还是坏事，可根据个人情况自行判断。这些事件可能对个人有精神上的影响（体验为紧张、压力、兴奋或苦恼等），影响的轻重程度是各不相同的，影响持续的时间也不一样。请您根据自己的情况，实事求是地回答下列问题。" 
		};
	
	public QuestionnaireModelOfLES() {
		super(QuestionnaireCodeEnum.LES);

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

		for (int i = 0; i < kQuestionArray.length; i++) {
			if (i % 2 == 0) {
				framePageFragmentFactory = new LES1MoreSelectionFactoryMethod();
				final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(kQuestionArray[i].toString());
				super.frameModelList.add(questionFrameModel);
			} else {
				framePageFragmentFactory = new LES2MoreSelectionFactoryMethod();
				final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(kQuestionArray[i].toString());
				super.frameModelList.add(questionFrameModel);
			}
		}

	}


}
