package cn.skyduck.questionnaire.questionnaire.GWB;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.question_frame_fragment.single_answer_expansion_1.SingleAnswerExpansionOneFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer_expansion_1.SingleAnswerExpansionOnePageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfGWB extends FullQuestionnaireModel implements Serializable {

	// 问题
	private static final String[] kQuestionArray = new String[] { 
		"一、你的总体感觉怎样(在过去的一个月里)?", 
		"二、你是否为自己的神经质或“神经病”感到烦恼(在过去的一个月里)?", 
		"三、你是否一直牢牢地控制着自己的行为、思维、情感或感觉(在过去的一个月里)?",
		"四、你是否由于悲哀、失去信心、失望或有许多麻烦而怀疑还有任何事情值得去做(在过去的一月里)?", 
		"五、你是否正在受到或曾经受到任何约束、刺激或压力(在过去的一个月里)?", 
		"六、你的生活是否幸福、满足或愉快(在过去的一个月里)?", 
		"七、你是否有理由怀疑自己曾经失去理智、或对行为、谈话、思维或记忆失去控制(在过去的一个月里)？",
		"八、你是否感到焦虑、担心或不安(在过去的一个月里)?", 
		"九、你睡醒之后是否感到头脑清晰和精力充沛(在过去的一个月里)?", 
		"十、你是否因为疾病、身体的不适、疼痛或对患病的恐惧而烦恼(在过去一个月里)?", 
		"十一、你每天的生活中是否充满了让你感兴趣的事情(在过去的一个月里)?", 
		"十二、你是否感到沮丧和忧郁(在过去的一个月里)?",
		"十三、你是否情绪稳定并能把握住自己(在过去的一个月里)?", 
		"十四、你是否感到疲劳、过累、无力或筋疲力竭(在过去的一个月里)?", 
		"十五、你对自己健康关心或担忧的程度如何(在过去的一个月里)?", 
		"十六、你感到放松或紧张的程度如何(在过去的一个月里)?", 
		"十七、你感觉自己的精力、精神和活力如何(在过去的一个月里)?",
		"十八、你忧郁或快乐的程度如何(在过去的一个月里)?", 
		"十九、你是否由于严重的性格、情感、行为或精神问题而感到需要帮助(在过去的一年里)?", 
		"二十、你是否曾感到将要精神崩溃或接近于精神崩溃?", 
		"二十一、你是否曾有过精神崩溃?", 
		"二十二、你是否曾因为性格、情感、行为或精神问题在精神病院、综合医院精神病科病房或精神卫生诊所治疗?",
		"二十三、你是否曾因为性格、情感、行为或精神问题求助于精神医生、心理学家？", 
		"二十四、你是否因自己的一些问题求助于普通医生(真正的躯体疾病或常规检查除外)？", 
		"二十五、你是否因自己的一些问题求助于脑科或神经外科专家？", 
		"二十六、你是否因自己的一些问题求助于护士(一般内科疾病除外)？", 
		"二十七、你是否因自己的一些问题求助于律师(常规的法律问题除外)？",
		"二十八、你是否因自己的一些问题求助于警察(单纯的交通违章除外)？", 
		"二十九、你是否因自己的一些问题求助于牧师、神父等各种神职人员？", 
		"三十、你是否因自己的一些问题求助于婚姻咨询专家？", 
		"三十一、你是否因自己的一些问题求助于社会工作者？", 
		"三十二、你是否因自己的一些问题寻求过其它正式的帮助？", 
		"三十三、你是否曾与家庭成员或朋友谈论自己的问题?" 
	};

	// 备选答案
	private static final String[][] kAnswerArray = new String[][] {
			// 1
			{ "1、极端烦恼", "2、相当烦恼", "3、有些烦恼", "4、很少烦恼", "5、一点也不烦恼" },
			// 2
			{ "1、绝对的", "2、大部分是的", "3、一般来说是的", "4、控制得不太好", "5、有些混乱", "6、非常混乱" },
			// 3
			{ "1、极端烦恼", "2、相当烦恼", "3、有些烦恼", "4、很少烦恼", "5、一点也不烦恼" },
			// 4
			{ "1、极端怀疑", "2、非常怀疑", "3、相当怀疑", "5、略微怀疑", "5、一点也不烦恼", "6、一点也不怀疑" },
			// 5
			{ "1、相当多", "2、不少", "3、有些", "4、不多", "5、没有" },
			// 6
			{ "1、非常幸福", "2、相当幸福", "3、满足", "4、略有些不满足", "5、非常不满足" },
			// 7
			{ "1、一点也没有", "2、只有一点点", "3、有些，不严重", "4、有些，相当严重", "5、是的，非常严重" },
			// 8
			{ "1、极端严重", "2、非常严重", "3、相当严重", "4、有些", "5、很少", "6、无" },
			// 9
			{ "1、天天如此", "2、几乎天天", "3、相当频繁 ", "4、不多", "5、很少", "6、无" },
			// 10
			{ "1、所有的时间", "2、大部分时间", "3、很多时间", "4、有时", "5、偶尔", "6、无" },
			// 11
			{ "1、所有的时间", "2、大部分时间", "3、很多时间", "4、有时", "5、偶尔", "6、无" },
			// 12
			{ "1、所有的时间", "2、大部分时间", "3、很多时间", "4、有时", "5、偶尔", "6、无" },
			// 13
			{ "1、所有的时间", "2、大部分时间", "3、很多时间", "4、有时", "5、偶尔", "6、无" },
			// 14
			{ "1、所有的时间", "2、大部分时间", "3、很多时间", "4、有时", "5、偶尔", "6、无" },
			// 15
			{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" },
			// 16
			{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" },
			// 17
			{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" },
			// 18
			{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" },
			// 19
			{ "1、是的，曾寻求帮助", "2、是的，但未寻求帮助", "3、有严重的问题", "4、几乎没有问题", "5、没有问题" },
			// 20
			{ "1、是的，在过去的一年里", "2、是的，在一年以前", "3、无" },
			// 21
			{ "1、是的，在过去的一年里", "2、是的，在一年以前", "3、无" },
			// 22
			{ "1、是的，在过去的一年里", "2、是的，在一年以前", "3、无" },
			// 23
			{ "1、是的，在过去的一年里", "2、是的，在一年以前", "3、无" },
			// 24
			{ "是", "否" },
			// 25
			{ "是", "否" },
			// 26
			{ "是", "否" },
			// 27
			{ "是", "否" },
			// 28
			{ "是", "否" },
			// 29
			{ "是", "否" },
			// 30
			{ "是", "否" },
			// 31
			{ "是", "否" },
			// 32
			{ "是", "否" },
			// 33
			{ "1、是的，很有帮助", "2、是的，有些帮助", "3、是的，但没有帮助", "4、否，没有人可与之谈论", "5、否，没有人愿意与我谈论", "7、没有问题" } };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:以下问题涉及到您对生活的感受与看法，无好坏之分，根据自己的现实情况和切身体验回答。" };

	public QuestionnaireModelOfGWB() {
		super(QuestionnaireCodeEnum.GWB);

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
		IQuestionnaireFramePageFragmentFactoryMethod questionFragmentFactoryOfNormal = new SingleAnswerFragmentFactoryMethod();
		IQuestionnaireFramePageFragmentFactoryMethod questionFragmentFactoryOfSpecial = new SingleAnswerExpansionOneFragmentFactoryMethod();

		for (int i = 0; i < kQuestionArray.length; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			Object questionDataSource = null;
			IQuestionnaireFramePageFragmentFactoryMethod questionFragmentFactory = null;
			Object fragmentStyle = null;
			switch (i) {
			case 14:
				questionFragmentFactory = questionFragmentFactoryOfSpecial;
				questionDataSource = new SingleAnswerExpansionOnePageDataSource(kQuestionArray[i], anwserList, " 不关心", "非常关心");
				break;
			case 15:
				questionFragmentFactory = questionFragmentFactoryOfSpecial;
				questionDataSource = new SingleAnswerExpansionOnePageDataSource(kQuestionArray[i], anwserList, "  松弛", "紧张  ");
				break;
			case 16:
				questionFragmentFactory = questionFragmentFactoryOfSpecial;
				questionDataSource = new SingleAnswerExpansionOnePageDataSource(kQuestionArray[i], anwserList, "无精打采", "精力充沛");
				break;
			case 17:
				questionFragmentFactory = questionFragmentFactoryOfSpecial;
				questionDataSource = new SingleAnswerExpansionOnePageDataSource(kQuestionArray[i], anwserList, "非常忧郁", "非常快乐");
				break;
			default:
				questionFragmentFactory = questionFragmentFactoryOfNormal;
				
				if (i >= 23 && i <= 31) {
					fragmentStyle = SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.true_or_false;
					questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], null);
				} else {
					fragmentStyle = SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical;
					questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
				}
				break;
			}

			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(questionFragmentFactory);
			questionFrameModel.setFragmentStyle(fragmentStyle);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			super.frameModelList.add(questionFrameModel);
		}
	}

	public byte[] getPartOneAnswerResults() {
		return PackAnswerDataTools.getPartOneAnswerResultsOfGWB(this);
	}
}
