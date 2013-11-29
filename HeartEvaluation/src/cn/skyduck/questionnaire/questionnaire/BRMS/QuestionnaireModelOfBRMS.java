package cn.skyduck.questionnaire.questionnaire.BRMS;

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
public class QuestionnaireModelOfBRMS extends FullQuestionnaireModel implements Serializable{

	// 问题 标题
	private static final String[] kQuestionArray = new String[]{
		"1、动作：",
    "2、言语：",
    "3、意念飘忽：",
    "4、言语／喧闹程度：",
    "5、敌意／破坏行为：",
    "6、情绪：",
    "7、自我评价：",
    "8、人际接触：",
    "9、睡眠：",
    "10、性兴趣：",
    //"11-1、 工作：\t\t初次评定：",
    //"11-2、 工作：\t\t再次评定：",
    "11、 工作：",
    "X1、幻觉：",
    "X2、妄想：" 
	};
	
	// 问题 提示
	private static final String[] kQuestionPromptArray = new String[]{
		// 1
		"",
		// 2
		"",
		// 3
		"",
		// 4
		"",
		// 5
		"提示 : 该项最好结合家属等知情人员的询问",
		// 6
		"",
		// 7
		"",
		// 8
		"提示 : 该项最好结合家属等知情人员的询问",
		// 9
		"提示 : 建议以过去3天内的平均睡眠时间进行评定",
		// 10
		"提示 : 该项最好结合家属等知情人员的询问",
		// 11-1
		"提示 : 该项最好结合家属等知情人员的询问",
		// 11-2
		//"提示 : 该项最好结合家属等知情人员的询问",
		// X1
		"",
		// X2
		""
	};
 
	// 备选答案
	private static final String[][] kAnswerArray = new String[][]{
		// 1
		{ " (0) 无该项症状或与患者正常时的水平相仿",
      " (1) 动作稍多，表情活跃",
      " (2) 动作多，姿势活跃",
      " (3) 动作极多，会谈时曾起立活动",
      " (4) 动个不停，虽予劝说仍坐不安宁"},
		// 2
		{ " (0) 无该项症状或与患者正常时的水平相仿",
      " (1) 话较多",
      " (2) 话多，几无自动停顿",
      " (3) 很难打断",
      " (4) 无法打断"},
		// 3
		{ " (0) 无该项症状或与患者正常时的水平相仿",
      " (1) 描述、修饰或解释的词句过多",
      " (2) 内容稍散漫或离题，有意联、音联或双关语",
      " (3) 思维散漫无序",
      " (4) 思维不连贯，内容无法理解"},
		// 4
		{ " (0) 无该项症状或与患者正常时的水平相仿",
      " (1) 说话声音高",
      " (2) 大声说话，隔开一段距离仍能听到",
      " (3) 语音极高，夹带歌声或噪音",
      " (4) 呼喊或尖叫"},
		// 5
		{ " (0) 无该项症状或与患者正常时的水平相仿",
      " (1) 稍急躁或易激惹，能控制",
      " (2) 明显急躁，易激惹或易怒",
      " (3) 有威胁性行为，但能被安抚",
      " (4) 狂暴，冲动和破坏行为"},
		// 6
		{ " (0) 无该项症状或与患者正常时的水平相仿",
      " (1) 略高涨，乐观",
      " (2) 高涨，爱开玩笑，易笑",
      " (3) 明显高涨，洋洋自得",
      " (4) 极高涨，和环境不协调"},
		// 7
		{ " (0) 无该项症状或与患者正常时的水平相仿",
      " (1) 略高",
      " (2) 高，常自诩自夸",
      " (3) 有不合实际的夸大观念",
      " (4) 有难以纠正的夸大妄想"},
		// 8
		{ " (0) 无该项症状或与患者正常时的水平相仿",
      " (1) 稍有爱管闲事或指手划脚倾向",
      " (2) 爱管闲事，好争辩",
      " (3) 爱发号施令，指挥他人",
      " (4) 专横，与环境不协调"},
		// 9
		{ " (0) 无该项症状或与患者正常时的水平相仿",
      " (1) 睡眠时间减少25％",
      " (2) 睡眠时间减少50％",
      " (3) 睡眠时间减少75％",
      " (4) 整夜不眠"},
		// 10
		{ " (0) 无该项症状或与患者正常时的水平相仿",
      " (1) 兴趣稍增强，有些轻浮言行",
      " (2) 性兴趣增强，有明显轻浮言行",
      " (3) 性兴趣显著增强，有严重调戏异性，或卖弄风情等言行",
      " (4) 整日专注于性活动"},
		// 11
		{ " 初次评定：(0)无该项症状或与患者正常时的水平相仿 \n 再次评定：(0)恢复正常工作，或可恢复正常工作",
      " 初次评定：(1) 工作质量略有下降\n 再次评定：(1)工作质量差，或减轻工作",
      " 初次评定：(2) 工作质量明显下降，工作时间争吵\n 再次评定：(2)工作质量明显低下，或在监护下工作",
      " 初次评定：(3) 无法继续工作，或在医院内尚能参加活动数小时\n 再次评定：(3)住院或病休，每天活动数小时",
      " 初次评定：(4) 日常活动不能自理，或不能参加病房活动\n 再次评定：(4)不能自理生活，或不能参加任何活动"},
		// 12
//		{ " (0) 恢复正常工作，或可恢复正常工作",
//      " (1) 工作质量差，或减轻工作",
//      " (2) 工作质量明显低下，或在监护下工作",
//      " (3) 住院或病休，每天活动数小时",
//      " (4) 不能自理生活，或不能参加任何活动"},
		// 13
		{ " (0) 恢复正常工作，或可恢复正常工作",
      " (1) 偶有或可疑",
      " (2) 肯定存在，每天≥3次",
      " (3) 经常出现",
      " (4) 行为受幻觉支配"},
		// 14
		{ " (0) 恢复正常工作，或可恢复正常工作",
      " (1) 偶有或可疑(不包括夸大妄想，下同)",
      " (2) 妄想肯定，可用情绪解释",
      " (3) 妄想肯定，难以用情绪解释",
      " (4) 出现幻觉的妄想"}
	};

	//指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：此量表为他评量表。一般评定时间范围为最近1周。" };
	
	public QuestionnaireModelOfBRMS() {
		super(QuestionnaireCodeEnum.BRMS);
		
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

		for (int i = 0; i < kQuestionArray.length; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			questionDataSource.setPrompt(kQuestionPromptArray[i]);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
	}

	@Override
	public boolean isCanBeIgnoredCurrentQuestion() {
		return true;
	}

	@Override
	public boolean isTestEffectively() {
		return true;
	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfDefault(answerDataSource, 0);
	}
}
