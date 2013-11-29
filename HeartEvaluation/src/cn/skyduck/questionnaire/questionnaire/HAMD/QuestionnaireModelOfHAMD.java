package cn.skyduck.questionnaire.questionnaire.HAMD;

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
public class QuestionnaireModelOfHAMD extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 "1、抑郁情绪：",
   "2、有罪感：",
   "3、自杀：",
   "4、入睡困难：",
   "5、睡眠不深：",
   "6、早醒：",
   "7、工作和兴趣：",
   "8、阻滞：指思维和言语缓慢，注意力难以集中，主动性减退。",
   "9、激越：",
   "10、精神性焦虑：",
   "11、躯体性焦虑：指焦虑的生理症状，包括口干、腹胀、腹泻、打呢、腹绞痛、心悸、头痛、过度换气和叹息，以及尿频和出汗等。",
   "12、胃肠道症状：",
   "13、全身症状：",
   "14、性症状：指性欲减退，月经紊乱等。",
   "15、疑病：",
   "16、体重减轻：",
   "17、自知力: ",
   "18、日夜变化：症状在早晨加重的严重，还是傍晚会加重的严重？并按其变化程度评分。",
   "19、人格解体或现实解体：指非真实感或虚无妄想。",
   "20、偏执症状：",
   "21、强迫症状：指强迫思维和强迫行为。",
   "22、能力减退感：",
   "23、绝望感：",
   "24、自卑感："
 };
 
//问题 提示
private static final String[] kQuestionPromptArray = new String[]{
	// 1
	"提示 : 依据病人口头叙述以及对病人的观察",
	// 2
	"提示 : 依据病人口头叙述",
	// 3
	"提示 : 依据病人口头叙述",
	// 4
	"提示 : 依据病人口头叙述",
	// 5
	"提示 : 依据病人口头叙述",
	// 6
	"提示 : 依据病人口头叙述",
	// 7
	"提示 : 依据病人口头叙述，以及向家属或病房工作人员收集资料",
	// 8
	"提示 : 依据对病人的观察",
	// 9
	"提示 : 依据对病人的观察",
	// 10
	"提示 : 依据病人口头叙述",
	// 11
	"提示 : 依据对病人的观察",
	// 12
	"提示 : 依据病人口头叙述",
	// 13
	"提示 : 依据病人口头叙述",
	// 14
	"提示 : 依据病人口头叙述",
	// 15
	"提示 : 依据病人口头叙述",
	// 16
	"提示 : 依据病人口述，最好根据实际体重记录或向家属或病房工作人员收集资料",
	// 17
	"提示 : 依据病人口头叙述",
	// 18
	"提示 : 依据病人口头叙述",
	// 19
	"提示 : 依据病人口头叙述",
	// 20
	"提示 : 依据病人口头叙述",
	// 21
	"提示 : 依据病人口头叙述",
	// 22
	"提示 : 依据病人口头叙述，以及向家属或病房工作人员收集资料",
	// 23
	"提示 : 依据病人口头叙述",
	// 24
	"提示 : 依据病人口头叙述"
};
 	//备选答案
 	private static final String[][] kAnswerArray = new String[][]{
 		//
 		{" (0) 无",
     " (1) 轻度：只在问到时才诉述",
     " (2) 中度：在谈话中自发地表达",
     " (3) 重度：不用言语也可以从表情、姿势、声音或欲哭中流露出这种情绪",
     " (4) 很重：病人的自发语言和非言语表达（表情、动作），几乎完全表现为这种情绪"},
 		//
 		{" (0) 无",
     " (1) 轻度：责备自己，感到自己已连累他人",
     " (2) 中度：认为自己犯了罪，或反复思考以往的过失和错误",
     " (3) 重度：认为目前的疾病是对自己错误的惩罚，或有罪恶妄想",
     " (4) 很重：罪恶妄想伴有指责或威胁性幻觉"},
 		//
 		{" (0) 无",
     " (1) 轻度：觉得活着没有意思",
     " (2) 中度：希望自己已经死去，或常想到与死有关的事",
     " (3) 重度：消极观念（自杀念头）",
     " (4) 很重：有严重自杀行为"},
 		//
 		{" (0) 无",
     " (1) 轻~中度：主诉有时有入睡困难，即上床后半小时仍不能入睡",
     " (2) 重度：主诉每晚均入睡困难"},
 		//
 		{" (0) 无",
     " (1) 轻~中度：睡眠浅多恶梦",
     " (2) 重度：半夜曾醒来(不包括上厕所)"},
 		//
 		{" (0) 无",
     " (1) 轻~中度：有早醒，比平时早醒1小时，但能重新人睡",
     " (2) 重度：早醒后无法重新人睡。"},
 		//
 		{" (0) 无",
     " (1) 轻度：提问时才诉述",
     " (2) 中度：自发地直接或间接表达对活动、工作或学习失去兴趣，如感到没精打彩、犹豫不决，不能坚持或需强迫才能工作或活动",
     " (3) 重度：病室劳动或娱乐不满3小时",
     " (4) 很重：因目前的疾病而停止工作，住院者不参加任何活动或者没有他人帮助便不能完成室日常事务"},
 		//
 		{" (0) 无",
     " (1) 轻度：精神检查中发现轻度迟缓",
     " (2) 中度：精神检查中发现明显的迟缓",
     " (3) 重度：精神检查困难",
     " (4) 很重：完全不能回答问题(木僵)"},
 		//
 		{" (0) 无",
     " (1) 轻度：检查时表现得有些心神不定",
     " (2) 中度：明显的心神不定或小动作多",
     " (3) 重度：不能静坐，检查中曾起立",
     " (4) 很重：搓手、咬手指、扯头发、咬嘴唇"},
 		//
 		{" (0) 无",
     " (1) 轻度：问及时诉述",
     " (2) 中度：自发地表达",
     " (3) 重度：表情和言谈流露出明显忧虑",
     " (4) 很重：明显惊恐"},
 		//
 		{" (0) 无",
     " (1) 轻度",
     " (2) 中度：有肯定的上述症述",
     " (3) 重度：上述症状严重，影响生活或需加处理",
     " (4) 很重：严重影响生活和活动"},
 		//
 		{" (0) 无",
     " (1) 轻~中度：食欲减退，但不需他人鼓励便自行进食",
     " (2) 重度：进食需他人催促或请求或需要应用泻药或助消化药"},
 		//
 		{" (0) 无",
     " (1) 轻~中度：四肢、背部或颈部沉重感，背痛、头痛、肌肉疼痛，全身乏力或疲倦",
     " (2) 重度：症状明显评"},
 		//
 		{" (0) 无，或该项对受测者不适合",
     " (1) 轻度",
     " (2) 重度"//,
     //" (3) 不能肯定，或该项对被评者不适合（不计入总分）"
     },
 		//
 		{" (0) 无",
     " (1) 轻度：对身体过分关注",
     " (2) 中度：反复思考健康问题",
     " (3) 重度：有疑病妄想",
     " (4) 很重：伴幻觉的疑病妄想"},
 		//
 		{" (0) 无",
     " (1) 轻~中度：一周内体重减轻1斤以上",
     " (2) 重度：一周内体重减轻2斤以上"},
 		//
 		{" (0) 知道自己有病，表现为忧郁",
     " (1) 轻~中度：知道自己有病，但归于伙食太差、环境问题、工作过\n\t 忙、病毒感染或需要休息等",
     " (2) 重度：完全否认有病"},
 		//
 		{" (0) 早晨或傍晚均无加重",
     " (1) 早晨(或傍晚)轻度加重",
     " (2)  早晨(或傍晚)重度加重"},
 		//
 		{" (0) 无",
     " (1) 轻度：问及时才诉述",
     " (2) 中度：自发诉述",
     " (3) 重度：有虚无妄想",
     " (4) 很重：伴幻觉的虚无妄想"},
 		//
 		{" (0) 无",
     " (1) 轻度：有猜疑",
     " (2) 中度：有牵连观念",
     " (3) 重度：有关系妄想或被害妄想",
     " (4) 很重：伴有幻觉的关系妄想或被害妄想"},
 		//
 		{" (0) 无",
     " (1) 轻~中度：问及时才诉述",
     " (2) 重度：自发诉述"},
 		//
 		{" (0) 无",
     " (1) 轻度：仅于提问时方引出主观体验",
     " (2) 中度：病人主动表示有能力减退感",
     " (3) 重度：需鼓励、指导和安慰才能完成病室日常事务或个人卫生",
     " (4) 很重：穿衣、梳洗、进食、铺床或个人卫生均需要他人协助"},
 		//
 		{" (0) 无",
     " (1) 轻度：有时怀疑“情况是否会好转”，但解释后能接受",
     " (2) 中度：持续感到“没有希望”，但解释后能接受",
     " (3) 重度：对未来感到灰心、悲观和绝望，解释后不能排除",
     " (4) 很重：自动反复诉述“我的病不会好了”或诸如此类的情况"},
 		// 24
 		{" (0) 无",
     " (1) 轻度：仅在询问时诉述有自卑感",
     " (2) 中度：自动诉述有自卑感（我不如他人）",
     " (3) 重度：病人主动诉述：“我一无是处”或“低人一等”，与评2分者只是程度的差别",
     " (4) 很重：自卑感达妄想的程度，例如“我是废物”或类似情况"}
 	};

 	//指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：本量表为他评量表。准备就绪后请点击“确定”键开始。" };
	
	public QuestionnaireModelOfHAMD() {
		super(QuestionnaireCodeEnum.HAMD);
		
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
			QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
	}


}
