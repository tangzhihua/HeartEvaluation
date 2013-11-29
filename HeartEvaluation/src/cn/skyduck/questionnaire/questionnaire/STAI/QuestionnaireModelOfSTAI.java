package cn.skyduck.questionnaire.questionnaire.STAI;

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
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfSTAI extends FullQuestionnaireModel implements Serializable{

	// 问题 标题
	private static final String[] kQuestionArray = new String[]{
		    "1、我感觉心情平静。",
        "2、我感到安全。",
        "3、我是紧张的。",
        "4、我感到被限制。",
        "5、我感到安逸。",
        "6、我感到烦乱。",
        "7、我现在正在为可能发生的不幸而烦恼。",
        "8、我感到满意。",
        "9、我感到害怕。",
        "10、我感到舒适。",
        "11、我有自信心。",
        "12、我觉得神经过敏。",
        "13、我极度紧张不安。",
        "14、我优柔寡断。",
        "15、我是轻松的。",
        "16、我感到心满意足。",
        "17、我是烦恼的。",
        "18、我感到慌乱。",
        "19、我感到镇定。",
        "20、我感到愉快。",
        "21、我是愉快的。",
        "22、我感到神经过敏和不安。",
        "23、我感到自我满足。",
        "24、我希望像别人那样的高兴。",
        "25、我感到像个失败者。",
        "26、我感到得宁静。",
        "27、我是“平静、冷静、和镇定自若的”。",
        "28、我感到困难成堆无法克服。",
        "29、我过分忧虑那些无关紧要的事。",
        "30、我是高兴的。",
        "31、我的思想混乱。",
        "32、我缺乏自信。",
        "33、我感到安全。",
        "34、我容易作出决断。",
        "35、我感到不太好。",
        "36、我是满足的。",
        "37、我为一些不重要的想法缠绕困扰。",
        "38、我如此沮丧，无法摆脱。",
        "39、我是个很稳定的人。",
        "40、我一想到当前的事情和利益，就会变得心烦意乱和紧张。"
	};
 
	// 备选答案
	private static final String[] kAnswerArray = new String[]{
			   "完全没有",               
			   "有些",
			   "中等程度",
			   "非常明显"
	};

	//指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语（S-AI）：以下列出的是人们常常用来描述自己的陈述，请阅读每一个陈述，然后根据你此时此刻最恰当的感觉选择。" + 
		"\n\t\t\t\t没有对或错的回答，不要对任何一个陈述花太多的时间去考虑，但所给的答案应该是你现在最恰当的感觉。"
  };
	
	public QuestionnaireModelOfSTAI() {
		super(QuestionnaireCodeEnum.STAI);
		
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
		framePageFragmentFactory = new SingleAnswerFragmentFactoryMethod();
		final List<String> anwserList = Arrays.asList(kAnswerArray);

		for (int i = 0; i < kQuestionArray.length; i++) {
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.horizontal);
			super.frameModelList.add(questionFrameModel);
		}
	}
	
	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfIntegerType1(answerDataSource);
	}
}
