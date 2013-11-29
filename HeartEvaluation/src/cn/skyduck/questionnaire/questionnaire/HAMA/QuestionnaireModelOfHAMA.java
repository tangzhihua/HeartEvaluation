package cn.skyduck.questionnaire.questionnaire.HAMA;

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
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfHAMA extends FullQuestionnaireModel implements Serializable{

	// 问题 标题
	private static final String[] kQuestionArray = new String[]{
 	 "1、焦虑心境：担心、担优，感到有最坏的事将要发生，容易激惹。",
   "2、紧张：紧张感、易疲劳、不能放松，情绪反应，易哭、\n颤抖、感到不安。",
   "3、害怕：害怕黑暗、陌生人、一人独处、动物、乘车或旅行及人多的场合。",
   "4、失眠：难以入睡、易醒、睡得不深、多梦、夜惊、醒后感疲倦。",
   "5、认知功能：或称记忆、注意障碍，注意力不能集中，记忆力差。",
   "6、抑郁心境：丧失兴趣、对以往爱好缺乏快感、抑郁、早醒、昼重夜轻。",
   "7、躯体性焦虑--肌肉系统：肌肉酸痛、活动不灵活、肌肉抽动、肢体抽动、牙齿打颤、声音发抖。",
   "8、躯体性焦虑--感觉系统：视物模糊、发冷发热、软弱无力感、浑身刺痛。",
   "9、心血管系统症状：心动过速、心悸、胸痛、血管跳动感、昏倒感、心搏脱漏。",
   "10、呼吸系统症状：胸闷、窒息感、叹息、呼吸困难。 ",
   "11、 胃肠道症状： 吞咽困难、嗳气、消化不良（进食后腹痛腹胀、恶心、胃部饱感）、肠动感、肠鸣、腹泻、体重减轻、便秘。",
   "12、生殖泌尿系统症状：尿意频数、尿急、停经、性冷淡、早泄、阳萎。",
   "13、植物神经系统症状：口干、潮红、苍白、易出汗、起鸡皮疙瘩、紧张性头痛、毛发竖起。",
   "14、会谈时行为表现：\n" +
   "\t\t\t一般表现：紧张、不能松弛、忐忑不安，咬手指、紧紧握拳、摸弄手帕， 面肌抽动、不宁顿足、手发抖、皱眉、\n表情僵硬、肌张力高，叹手样呼吸、面色苍白。\n" +
   "\t\t\t生理表现：吞咽、打嗝，安静时心率快，呼吸快（20次/分以上）、健反射亢进、震颤、瞳孔放大、眼睑跳动、易出汗、眼球突出。"
	};
	
	// 问题 提示
	private static final String[] kQuestionPromptArray = new String[]{
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述",
		"提示 : 依据病人口头叙述及对病人的观察"
	};
 
	// 备选答案
	private static final String[] kAnswerArray = new String[]{
   "(0) 无症状",
   "(1) 症状轻微",
   "(2) 有肯定的症状，但不影响生活与活动",
   "(3) 症状重，需加处理，或已影响生活和活动",
   "(4) 症状极重，严重影响其生活"
	};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：本量表为他评量表。准备就绪后请点击“确定”键开始。" };

	public QuestionnaireModelOfHAMA() {
		super(QuestionnaireCodeEnum.HAMA);
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
		List<String> anwserList = Arrays.asList(kAnswerArray);

		for (int i = 0; i < kQuestionArray.length; i++) {
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			questionDataSource.setPrompt(kQuestionPromptArray[i]);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
	}


}
