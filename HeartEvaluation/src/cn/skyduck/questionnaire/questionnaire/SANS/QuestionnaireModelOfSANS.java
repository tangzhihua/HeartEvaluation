package cn.skyduck.questionnaire.questionnaire.SANS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.question_frame_fragment.transition_page_1.TransitionPageOneFragmentFactoryMethod;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfSANS extends FullQuestionnaireModel implements Serializable{

	private static final String[] kQuestionArray = new String[]{
		//第一部分
        //第1题        1
        "1、面部表情很少变化：面部表情呆板、机械、冷漠、情绪不随谈话内容而" +
        "变化或变化少。由于一些药物可部分造成这种表现，所以在评分时要注意服" +
        "药与否，但不要自行“更改”分值。 ",
        //第2题       2
        "2、自发动作减少：在整个交谈过程中静坐着，很少或完全没有自发动作，" +
        "坐位、姿势或手足都很少变动。",
        //第3题      3
        "3、姿势表情贫乏：在表达自己的思想时不借助手势或躯体的位置变换，例如" +
        "谈及主要话题时身体不向前倾，在松弛时也不向后靠。",
        //第4题       4
        "4、眼神接触差：避免与他人目光接触，也不用眼神以辅助表情。即使在讲话" +
        "时眼睛也茫然凝视前方。",
        //第5题        5
        "5、无情感反应：在说笑话或开玩笑时都不能引出笑容。",
        //第6题          6
        "6、语调缺乏波动：语声常很单调，缺乏正常的抑扬顿挫,\n不用音调或音量的" +
        "变化来强调重要的词汇，在谈到私事时也不减轻声音，在谈到令人兴奋的事情" +
        "时也不提高声调。",
        //第7题          7
        "7、情感平淡总评：全面评定症状的严重性。重点在于无反应、不适切、以及情" +
        "感强度的全面减低。\n" +
        "\t\t\t建议：必须全面评估各症状的严重性。而不是通过计算分量表中各单项分的平均" +
        "分得出总评分。有时，即使症状群中其余症状并不存在，单单一个症状（如，极" +
        "度的情感平淡或迟钝）也足以使总评获\n得高分。",
        //第二部分
        //第8题      8
        "8、语量贫乏：自发言语的语量有限，因而在回答问题时往往很简单，很肤浅，没有" +
        "发挥，很少有自发的补充说明。\n回答的话可能是单词，有时干脆不回答。此时检查者" +
        "往往感到自己必须时时敦促启发患者，鼓励他回答得详细一些。为了解此项表现，检" +
        "查者让患者有足够时间回答和发挥。",
        //第9题        9
        "9、言语内容贫乏：对问题回答的语言虽够，但不能提供充分信息，其内容含糊，过于" +
        "抽象或过于具体、重复或刻板\n。检查者会发现患者的话不少，但并没有问答问题。或者\n，" +
        "他虽能提供足够信息，但却用了太多的词汇，实际上只需一二句便能表达清楚，有时可以" +
        "把这种话称为“空洞的哲学”。排除：与“赘述”的不同，在于赘述可提供不少细节。",
        //第10题        10
        "10、言语中断：在一种思维或一个概念结束之前，语流中断\n。在持续数秒至数分钟" +
        "的沉默期之后，病人表示她/他不能回忆起他讲了些什么或打算讲什么。如果病人主" +
        "动描述表示了自己的思维，或者当检查者问及他时，病人表示那正是停顿的原因，" +
        "这样才可评为言语中断。",
        //第11题           11
        "11、应答迟缓：病人要比平常花费更多时间来回答问题。他看上去“冷淡”。有" +
        "检查者会怀疑他是否听见了这个问题，\n但可发现他实际上知道所提的问题，只是" +
        "难以形成自己的思维来作出适切的回答。",
        //第12题            12
        "12、言语障碍总评：由于思维贫乏的核心症状是语量贫乏和言语内容贫乏，总评" +
        "重点主要在此二项。\n" +
        "\t\t\t建议：必须全面评估各症状的严重性。而不是通过计算分量表中各单项分的平均分" +
        "得出总评分。有时，即使症状群中其余症状并不存在，单单一个症状（如，高度的" +
        "语言内容贫乏）也足以使总评获得\n高分。",
        //第三部分          
        //第13题         13
        "13、衣着及个人卫生差：病人比平常不注意衣着及个人卫生。衣服邋遢，肮脏或污秽。" +
        "可能很少洗澡，也不注意头发、指甲或牙齿的卫生，以致蓬头垢面、手脏、体臭或牙齿" +
        "不洁及口臭。总之，外观不整洁或脏乱不堪，甚至不注意大小便卫生。",
        //第14题           14
        "14、工作或学习不能持久：病人难以找到或维持一个与其年龄、性别相适应的职业（或" +
        "学业）。学生不做家庭作业，甚至不去上课．或者从学习成绩上反映出来，如果是大学生，" +
        "在学期结束前就不得不放弃几门课程或全部课程。如果已达工作年龄，病人往往不能坚持" +
        "工作和明显地不负责任，上班迟到早退，不能完成所指派的任务（如购物或清洗），或者" +
        "很粗心和三心二意。",
        //第15题           15
        "15、躯体少动：病人懒于动弹，可能坐在倚子上一连几小时而没有任何自发活动，即使" +
        "被邀参加集体活动，也是暂时性的，不一会就自行走开，仍独自回到座位上。也可能花" +
        "不少时间在一些相对地不需要脑力或体力的事情，如看电视或一个人玩纸牌。家属可能" +
        "反映病人大多数时间呆在家里，“除了坐着便无所事事”。不管是在家里或在住院病人活" +
        "动室，病人大多数时间都坐在房间里。",
        //第16题          16
        "16、意志缺乏总评：全面评定意志缺乏的严重度，并要考虑病人的年龄和社会地位或出身。" +
        "重点可放在一二个特别引人注意的症状。\n" +
        "\t\t\t建议：必须全面评估各症状的严重性。而不是通过计算分量表中各单项分的平均分得出总" +
        "评分。有时，即使症状群中其余症状并不存在，单单一个症状（如，极重的躯体少动），" +
        "足以使总评获得高分。",
        //第四部分            
        //第17题            17
        "17、娱乐的兴致和活动减少：病人极少或没有任何兴趣或爱好。尽管这一症状可能隐匿" +
        "地或缓慢地发生，但在原来的兴趣和活动的水平上确有明显下降。轻度兴趣丧失的病人多" +
        "表现为被动或无所需求，例如仅偶尔有兴趣看看电视。\n最严重的表现是完全不参加或拒绝" +
        "参加活动。评定时对娱乐兴趣的质和量均要考虑。",
        //第18题             18
        "18、性活动减少：就病人的年龄和婚姻状况而言，其性兴趣和性活动减少。已婚者可能" +
        "表现为对性生活没有兴趣或只是被动地进行性交。严重者根本就不参加性活动。单身者" +
        "可能长期不介入有关性的活动，也不想去满足这种欲望。\n无论是已婚或未婚，主观上都" +
        "感到没有什么性欲，即使在性交或手淫时也没有什么快感。",
        //第19题      19
        "19、亲密感缺乏：感到难以与他人建立起亲密的感情。",
        //第20题         20
        "20、交友兴趣下降：病人与朋友和同龄人之间交往范围狭小。不管是同性或异性，他们" +
        "很少或没有朋友，也不努力去发展这种关系，几乎所有时间都独自一人。",
        //第21题         21
        "21、兴趣或社交缺乏总评：应全面评定兴致缺乏或社交缺乏的严重度。并考虑病人的" +
        "年龄、性别和家庭状况。\n" +
        "\t\t\t建议：必须全面评估各症状的严重性。而不是通过计算分量表中各单项分的平均分得" +
        "出总评分。有时，即使症状群中其余症状并不存在，单单一个症状（如，极重的亲密" +
        "感缺乏），足以使总评获得高分。",
        //第五部分       
        //第22题          22
        "22、不注意社交：病人表现为不注意社会工作或活动，在交谈过程中眼望他处，在讨论" +
        "过程中不接话题，或者表现为不介入或不参与，他可能毫无任何明显原因地突然中止讨" +
        "论。他看来有一种“隔阂感”或是“局外人”。在游戏、阅读或看电视时注意力也不能集中。",
        //第23题           23
        "23、心理测试时注意不集中：尽管病人有相当的文化和智力水平，但简单的智能测试成绩" +
        "却较差。可通过100 减7 （至少念过初中）或100 减3 （至少念过小学）来评定，连续" +
        "减五次。",
        //第24题          24
        "24、注意障碍总评：此项评分应评定病人在临床和测试中总的注意力。\n" +
        "\t\t\t建议：必须全面评估各症状的严重性。而不是通过计算分量表中各单项分的平均分得出" +
        "总评分。有时，即使症状群中其余症状并不存在，单单一个症状（如，极重的不注意社" +
        "交），足以使总评获得高分。",
	};
 
	// 答案
	private static final String[][] kAnswerArray = new String[][] {
			// 1
			{" (0) 无，正常或多变",
		   " (1) 可疑减少",
	     " (2) 轻度，面部反应有些减少",
       " (3) 中度，面部表情确实减少",
   	   " (4) 显著，面部表情显著减少",
		   " (5) 严重，面部表情基本不变"},
			// 2
			{" (0) 正常或动作多",
       " (1) 可疑减少",
       " (2) 轻度，有些减少",
       " (3) 中度，自发动作确实减少",
       " (4) 显著，动作显著减少",
       " (5) 严重，整个交谈过程中病人纹丝不动地坐着" },
			// 3
			{" (0) 无或正常",
       " (1) 可疑减少",
       " (2) 轻度，有些减少",
       " (3) 中度，表情姿势确实减少",
       " (4) 显著，姿势显著减少",
       " (5) 严重，病人从不利用姿势来协助表情" },
			// 4
			{ " (0) 无或正常",
        " (1) 可疑",
        " (2) 轻度，表情姿势有些减少",
        " (3) 中度，表情姿势确实减少",
        " (4) 显著，很少有眼神接触",
        " (5) 严重，病人几乎从不看交谈对象" },
			// 5
			{ " (0) 反应正常",
        " (1) 可疑",
        " (2) 轻度，反应有些减少",
        " (3) 中度，反应确实减少",
        " (4) 显著，很少有反应",
        " (5) 严重，即使在逗引时也基本没有反应"},
			// 6
			{ " (0) 无或正常",
        " (1) 可疑",
        " (2) 轻度，有些缺乏",
        " (3) 中度，确实缺乏",
        " (4) 显著，语调很少有波动",
        " (5) 严重，几乎所有言语都用单一语调" },
			// 7
			{ " (0) 无或正常",
        " (1) 可疑",
        " (2) 轻度，程度虽轻但肯定存在",
        " (3) 中度",
        " (4) 显著",
        " (5) 严重"},
			// 8
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，回答适切，但不能发挥",
        " (3) 中度，有些回答没有适当的发挥，许多回答为单词或很简单\n\t\t（“是”、“不”、“可能”、“我不知道”、“上星期”）",
        " (4) 显著，回答字数很少",
        " (5) 严重，病人讲得较少，有时不回答问题" },
			// 9
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，偶而有些回答太含糊而难以理解或显然可以浓缩",
        " (3) 中度，在会谈时的1/4 时间，回答常常含糊不清或显然可以浓缩",
        " (4) 显著，病人的回答至少有一半是含糊不清或无法理解的",
        " (5) 严重，几乎所有的言语含糊不清、无法理解或显然应予浓缩" },
			// 10
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，15 分钟内出现过一次",
        " (3) 中度，l5分钟内出现过二次",
        " (4) 显著，巧分钟内出现过三次",
        " (5) 严重，出现过三次以上" },
			// 11
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，回答之前偶有短的停顿",
        " (3) 中度；反应时间确实延长",
        " (4) 显著，反应时间显著延长",
        " (5) 严重，几乎所有的回答均需长时间的停顿" },
			// 12
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，轻度而肯定思维贫乏",
        " (3) 中度；思维确实贫乏",
        " (4) 显著，大多数时间思维贫乏",
        " (5) 严重，几乎所有时间思维贫乏"},
			// 13
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，稍微有些不注意外表",
        " (3) 中度，外表有些杂乱",
        " (4) 显著，外表明显杂乱",
        " (5) 严重，外表极其杂乱"},
			// 14
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，工作或学习稍微不能持久",
        " (3) 中度，肯定不能持久",
        " (4) 显著，明显不能持久",
        " (5) 严重，一直不能维持工作或学习" },
			// 15
			{ " (0) 无或正常",
        " (1) 可疑",
        " (2) 轻度，程度虽轻但肯定存在",
        " (3) 中度",
        " (4) 显著",
        " (5) 严重" },
			// 16
			{ " (0) 无或正常",
        " (1) 可疑 ",
        " (2) 轻度，程度虽轻但肯定存在",
        " (3) 中度",
        " (4) 显著",
        " (5) 严重" },
			// 17
			{ " (0) 无或正常",
        " (1) 可疑 ",
        " (2) 轻度，程度虽轻但肯定存在",
        " (3) 中度",
        " (4) 显著",
        " (5) 严重" },
			// 18
			{ " (0) 无或正常",
        " (1) 可疑 ",
        " (2) 轻度，程度虽轻但肯定存在",
        " (3) 中度",
        " (4) 显著",
        " (5) 严重" },
			// 19
			{ " (0) 无或正常",
        " (1) 可疑 ",
        " (2) 轻度，程度虽轻但肯定存在",
        " (3) 中度",
        " (4) 显著",
        " (5) 严重" },
			// 20
			{ " (0) 无或正常",
        " (1) 可疑",
        " (2) 轻度，程度虽轻但肯定存在",
        " (3) 中度",
        " (4) 显著",
        " (5) 严重"},
			// 21
			{ " (0) 无或正常",
        " (1) 可疑",
        " (2) 轻度，程度虽轻但肯定存在",
        " (3) 中度",
        " (4) 显著",
        " (5) 严重" },
		   // 22
			{ " (0) 无或正常",
        " (1) 可疑",
        " (2) 轻度，程度虽轻但肯定存在",
        " (3) 中度",
        " (4) 显著",
        " (5) 严重" },
			// 23
			{ " (0) 无错",
        " (1) 无错但艰难或需修正一次错",
        " (2) 轻度，错一次",
        " (3) 中度，错二次",
        " (4) 显著，错三次",
        " (5) 严重，错3 次以上"},
			// 24
			{ " (0) 无或正常",
        " (1) 可疑",
        " (2) 轻度，程度虽轻但肯定存在",
        " (3) 中度",
        " (4) 显著",
        " (5) 严重"}
  };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：此量表为他评量表。请根据最近一个月，病人的实际情况，选择最正确的答案。本量表为6级评分：\n\t\t\t\t（0）无或正常；\n\t\t\t\t（1）可疑；\n\t\t\t\t（2）轻度，程度虽轻但肯定存在；\n\t\t\t\t（3）中度；\n\t\t\t\t（4）显著；\n\t\t\t\t（5）严重。" };

	public QuestionnaireModelOfSANS() {
		super(QuestionnaireCodeEnum.SANS);

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
		// 第一部分过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage1 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel1 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage1);
		transitionPageFrameModel1.setQuestionDataSource("\t\t\t\tPartⅠ 情感平淡或迟钝\n\n" + "\t\t\t情感平淡或迟钝的表现是，特征表情、感受和反应的贫乏。可以在" + "常规精神检查时观察病人的行为和应答情况后进行评估。本项评分可能受到" + "药物的影响，因为一些药物副反应可以导致面具状睑以及有关表情动作的减" + "少或消失\n。然而情感的其它方面如应答反应与适切性应不受影响。");
		super.frameModelList.add(transitionPageFrameModel1);

		// 第一部分 问题
		for (int i = 0; i < 7; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}

		// 第二部分过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage2 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel2 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage2);
		transitionPageFrameModel2.setQuestionDataSource("\t\t\t\tPartⅡ 思维贫乏\n\n");
		super.frameModelList.add(transitionPageFrameModel2);

		// 第二部分 问题
		for (int i = 7; i < 12; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}

		// 第三部分过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage3 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel3 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage3);
		transitionPageFrameModel3.setQuestionDataSource("\t\t\t\tPartⅢ 意志缺乏\n\n" + "\t\t\t意志缺乏的特征是缺少精力和兴趣。病人不能主动发起或坚持完成各项任务。\n\n");
		super.frameModelList.add(transitionPageFrameModel3);

		// 第三部分 问题
		for (int i = 12; i < 16; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
		// 第四部分过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage4 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel4 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage4);
		transitionPageFrameModel4.setQuestionDataSource("\t\t\t\tPartⅣ 兴趣／社交缺乏\n\n");
		super.frameModelList.add(transitionPageFrameModel4);

		// 第四部分 问题
		for (int i = 16; i < 21; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}

		// 第五部分过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage5 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel5 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage5);
		transitionPageFrameModel5.setQuestionDataSource("\t\t\t\tPartⅤ 注意障碍\n\n");
		super.frameModelList.add(transitionPageFrameModel5);

		// 第五部分 问题
		for (int i = 21; i < kQuestionArray.length; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
	}


}
