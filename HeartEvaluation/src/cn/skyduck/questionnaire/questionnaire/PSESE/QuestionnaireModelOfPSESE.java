package cn.skyduck.questionnaire.questionnaire.PSESE;

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
public class QuestionnaireModelOfPSESE extends FullQuestionnaireModel implements Serializable{

	// 问题 标题
	private static final String[] kQuestionArray = new String[]{
	  //第1题        
    "1、步态：当病人走进检查室时，对病人进行检查。病人的步态，双臂" +
    "的摆动，他的一般姿势，这是对这项目全面评分的基础。按以下评分：",
    //第2题     
    "2、落臂：让被检查者同时抬起他们的双臂齐肩，然后让其放下落到身体两侧。",
    //第3题    
    "3、摇肩：将病人的双臂在肘部弯曲成一直角，每次检查一侧胳臂，检" +
    "查者一只手抓住其手，同时将另一手紧握病人肘部，把病人的上臂前后" +
    "来回推动，并且将肱骨外旋，此抵抗性从正常到极端僵直的程度，按以" +
    "下记分：",
    //第4题     
    "4、肘强直：分别将肘关节弯成直角，同时被动地伸展和弯曲关节以及" +
    "观察并触摸病人的肱二头肌。",
    //第5题   
    "5、固定姿势或腕强直：检查者用一只手举起病人的手腕同时用另一只" +
    "手屈伸手腕，并将手腕向尺侧和挠侧活动。",
    //第6题         
    "6、腿的摆动：病人坐在桌上，腿垂下并且自由摆动。检查者握住他的踝" +
    "部，同时将其抬高直到膝部部分地仲展，然后让腿落下。落下有抵抗和缺" +
    "乏摆动是本项记分的根据：",
    //第7题         
    "7、失颈部运动：病人躺在一张有良好褥垫的台上，检查者用手抬起他的" +
    "头，然后撤手让头落下。按以下记分：",
    //第8题      
    "8、眉间轻敲：告诉病人睁大眼睛，不要眨眼，持续地，迅速轻敲病人的" +
    "眉间。注意连续眨眼的次数。",
    //第9题       
    "9、震颤：",
    //第10题    
    "10、流涎：当与病人谈话时进行观察，然后要求其张开嘴并且抬高舌头。给予以下评分："
	};
	
 
	// 备选答案
	private static final String[][] kAnswerArray = new String[][]{
		// 1
		{ " (0) 正常",
      " (1) 病人步行时，臂的摆动减少",
      " (2) 双臂摆动明显减少，伴有明显的手臂僵直",
      " (3) 僵直的步态，伴有双臂强直放在腹部前面",
      " (4) 弯腰驼背拖足而行的步态，伴有前冲或后倾"},
		// 2
		{ " (0) 正常，双臂自由落下时具有粗重的拍击声，有轻微的回跳",
      " (1) 落下稍缓慢，可听到较轻的拍击声，有轻微的回跳",
      " (2) 落下缓慢，没有回跳",
      " (3) 明显缓慢，全没有拍击声",
      " (4) 双臂落下好像有抵抗一样，好像胶粘着一样"},
		// 3
		{ " (0) 正常",
      " (1) 轻度发挺和抵抗",
      " (2) 中度发挺和抵抗",
      " (3) 明显的僵直，伴被动运动困难",
      " (4) 极端的发挺和强直，肩膀几乎像冻僵了一样"},
		// 4
		{ " (0) 正常",
      " (1) 轻度发挺和抵抗",
      " (2) 中度发挺和抵抗",
      " (3) 明显的僵直，伴被动运动困难",
      " (4) 极端的发挺和强直，肘部几乎像冻僵了一样"},
		// 5
		{ " (0) 正常",
      " (1) 轻度发挺和抵抗",
      " (2) 中度发挺和抵抗",
      " (3) 明显的僵直，伴被动运动困难",
      " (4) 极端的发挺和强直"},
		// 6
		{ " (0) 双腿自由摆动",
      " (1) 双腿摆动轻度减少",
      " (2) 摆动中度减少",
      " (3) 明显的抵抗和摆动的减幅",
      " (4) 摆动完全消失"},
		// 7
		{ " (0) 头自然落在诊查床上",
      " (1) 头落下稍缓慢，主要注意头部碰在诊查床上时没有扑咚声",
      " (2) 用眼睛可以观察到头落下时中度缓慢",
      " (3) 头落下时非常缓慢和僵硬",
      " (4) 头达不到诊查床上"},
		// 8
		{ " (0) 0~5次眨眼",
      " (1) 6~10次眨眼",
      " (2) 11~15次眨眼",
      " (3) 16~20次眨眼",
      " (4) 21次以上眨眼"},
		// 9
		{ " (0) 正常",
      " (1) 轻度的手指震颤，看起来或触起来是明显的",
      " (2) 间断地发生的手或臂的震颤",
      " (3) 一个肢体或一个以上肢体持续性的震颤",
      " (4) 全身震颤"},
		// 10
		{ " (0) 正常",
      " (1) 病人张口抬舌，有口涎积聚",
      " (2) 有口涎过多，偶有可能造成说话困难",
      " (3) 因为口涎过多而说话困难",
      " (4) 口涎外流无法控制"}
	};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：下面有二十条文字，请仔细阅读每一条，把意思弄明白，然后根据您最近一星期的实际情况来选择。" };
	
	public QuestionnaireModelOfPSESE() {
		super(QuestionnaireCodeEnum.PSESE);
		
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


}
