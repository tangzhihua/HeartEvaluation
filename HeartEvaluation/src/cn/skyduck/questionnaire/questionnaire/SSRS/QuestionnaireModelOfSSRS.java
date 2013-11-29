package cn.skyduck.questionnaire.questionnaire.SSRS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.question_frame_fragment.ssrs.SSRSQuestion10And11FragmentFactoryMethod;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfSSRS extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 "1、您有多少关系密切，可以得到支持和帮助的朋友?",
   "2、近一年来您：",
   "3、您与邻居：",
   "4、您与同事：",
   "5、从夫妻(恋人)得到的支持和照顾",
   "6、从父母得到的支持和照顾",
   "7、从儿女得到的支持和照顾",
   "8、从兄弟姐妹得到的支持和照顾",
   "9、从家庭其他成员(如嫂子)得到的支持和照顾",
   "10、过去，在您遇到急难情况时，曾经得到的经济支持和解决实际问题的帮助的来源有：",
   "11、过去，在您遇到急难情况时，曾经得到的安慰和关心的来源有：",
   "12、您遇到烦恼时的倾诉方式：",
   "13、您遇到烦恼时的求助方式：",
   "14、对于团体(如党团组织、宗教组织、工会、学生会等)组织活动，您："
 };
 
 	//备选答案
	private static final String[][] kAnswerArray = new String[][]{
		//1
    {" (1) —个也没有。",
     " (2) 1—2个。",
     " (3) 3—5个。",
     " (4) 6个或6个以上。"},
    //2
    {" (1) 远离家人，且独居—室。",
     " (2) 住处经常变动，多数时间和陌生人住在一起。",
     " (3) 和同学、同事或朋友住在一起。",
     " (4) 和家人住在一起。"},
 		//3
    {" (1) 相互之间从不关心，只是点头之交。",
     " (2) 遇到困难可能稍微关心。",
     " (3) 有些邻居很关心您。",
     " (4) 大多数邻居都很关心您。"},
 		//4
    {" (1) 相互之间从不关心，只是点头之交。",
     " (2) 遇到困难可能稍微关心。",
     " (3) 有些同事很关心您。",
     " (4) 大多数同事都很关心您。"},
 		//5
    {" (1) 无",
     " (2) 极少",
     " (3) 一般",
     " (4) 全力支持"},
 		//6
    {" (1) 无",
     " (2) 极少",
     " (3) 一般",
     " (4) 全力支持"},
 		//7
 		{" (1) 无",
     " (2) 极少",
     " (3) 一般",
     " (4) 全力支持"},
 		//8
 		{" (1) 无",
     " (2) 极少",
     " (3) 一般",
     " (4) 全力支持"}, 
    //9
    {" (1) 无",
     " (2) 极少",
     " (3) 一般",
     " (4) 全力支持"},
   	//10
	  {""},
   	//11
 		{""}, 	
    //12
    {" (1) 从不向任何人诉述。",
     " (2) 只向关系极为密切的1—2个人诉述。",
     " (3) 如果朋友主动询问您会说出来。",
     " (4) 主动诉述自己的烦恼，以获得支持和理解。"},
    //13
 		{" (1) 只靠自己，不接受别人帮助。",
     " (2) 很少请求别人帮助。",
     " (3) 有时请求别人帮助。",
     " (4) 有困难时经常向家人、亲友、组织求援。"},
 		//14
    {" (1) 从不参加。",
     " (2) 偶尔参加。",
     " (3) 经常参加。",
     " (4) 主动参加并积极活动。"}
  };

 	//指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:下面的问题用于反映您在社会中所获得的支持，请按各个问题的具体要求，根据您的实际情况选择，谢谢您的合作。" };
	
	public QuestionnaireModelOfSSRS() {
		super(QuestionnaireCodeEnum.SSRS);
		
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
		final IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryNormal = new SingleAnswerFragmentFactoryMethod();
		final IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactorySpecial = new SSRSQuestion10And11FragmentFactoryMethod();

		for (int i = 0; i < kQuestionArray.length; i++) {

			if (i == 9 || i == 10) {
				final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactorySpecial);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(kQuestionArray[i]);
				super.frameModelList.add(questionFrameModel);
			} else {

				final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
				final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
				final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactoryNormal);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionDataSource);
				questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
				super.frameModelList.add(questionFrameModel);
			}
		}
	}
	
	public byte[] getPartOneAnswerResults() {
		return PackAnswerDataTools.getPartOneAnswerResultsOfSSRS(this);
	}

	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfSSRS(option, this);
	}
}
