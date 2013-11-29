package cn.skyduck.questionnaire.questionnaire.HIS;

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
public class QuestionnaireModelOfHIS extends FullQuestionnaireModel implements Serializable{

 //问题
 private static final String[] kQuestionArray = new String[]{
	   "1、急性起病",
     "2、阶梯式恶化\n" +
     "\t\t\t指疾病或痴呆发生后，病情停留在一个水平上，然后病情又" +
     "加重，接着又停留在一个水平上，多见于多次梗塞时\n",
     "3、波动性病程\n" +
     "\t\t\t指病情好转后又恶化的情况。",
     "4、夜间意识模糊？",
     "5、人格相对保持完整",
     "6、情绪低落",
     "7、身体诉述\n" +
     "\t\t\t指病人有任何躯体不适的诉述，如头痛、耳鸣、眩晕等。",
     "8、情感失禁\n" +
     "\t\t\t指情感的控制能力减弱，易哭、易笑、易怒，但情感的维持时间很短。",
     "9、有高血压或高血压史",
     "10、中风史\n" +
     "\t\t\t包括“短暂性脑缺血发作”。",
     "11、动脉硬化\n" +
     "\t\t\t主要指冠状动脉、肾动脉、眼底动脉的硬化，ECG、眼底检查或脑血流图检查的证据等。",
     "12、局灶神经系症状\n" +
     "\t\t\t指提示定位性的神经系症状。",
     "13、局灶神经系体征\n" +
     "\t\t\t指提示定位性的神经系体征。"   
 };
 

 	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"指导语:" + 
		"\n\t\t\t本量表为他评量表，评定注意事项如下。" +
		"\n\t\t\t1、HIS仅用于血管性痴呆和老年性痴呆的鉴别诊断。" + 
		"\n\t\t\t2、评定须在痴呆诊断确认后进行。" +
		"\n\t\t\t3、评分时的主要依据来源于病史收集、体格检查和精神检查。"
  };
	
	public QuestionnaireModelOfHIS() {
		super(QuestionnaireCodeEnum.HIS);

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

		for (int i = 0; i < kQuestionArray.length; i++) {
			final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], null);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.true_or_false);
			super.frameModelList.add(questionFrameModel);
		}

	}

	/**
	 * 检测当前问题是否能够被忽略(能被忽略的问题有很多种情况 : 1.此问题已经回答过; 2.当前帧不是问题帧; 3.已经回答的题目数量已经达到一定数量)
	 * 
	 * @return
	 */
	public boolean isCanBeIgnoredCurrentQuestion() {
		return true;
	}

	/**
	 * 检测本次测试是否有效
	 * 
	 * @return
	 */
	public boolean isTestEffectively() {
		do {
			if (super.getUnansweredQuestionNumberList().size() > 30) {
				// 注意当未答的题目大于等于30个时，不能让受测者点确定，因为那样就白答了
				break;
			}
			return true;
		} while (false);
		return false;
	}
	
	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfBoolean(answerDataSource, -1);
	}
}
