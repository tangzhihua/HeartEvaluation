package cn.skyduck.questionnaire.questionnaire.CMI;

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
public class QuestionnaireModelOfCMI extends FullQuestionnaireModel implements Serializable{

 //问题
 private static final String[] kQuestionArray = new String[]{
	 "1、你读报时需要戴眼镜吗？",
   "2、你看远处时需要戴眼镜吗？",
   "3、你是否经常有一时性的眼前发黑（视力下降或看不见东西）的现象？",
   "4、你是否有频繁的眨眼和流泪？",
   "5、你的眼睛是否经常很疼(或出现看物模糊的现象)？",
   "6、你的眼睛是否经常发红或发炎？",
   "7、你是否耳背（听力差）？",
   "8、你是否有过中耳炎、耳朵流脓？",
   "9、你是否经常耳鸣？（耳中自觉有各种声响，以致影响听觉）",
   "10、你常常不得不为清嗓子而轻咳吗？",
   "11、你经常有嗓子发堵的感觉（感觉喉咙里有东西）吗？",
   "12、你经常连续打喷嚏吗？",
   "13、你是否觉得鼻子老是堵？",
   "14、你经常流鼻涕吗？",
   "15、你是否有时鼻子出血很厉害？",
   "16、你是否经常得重感冒（或嗓子痛，扁桃体肿大）？",
   "17、你是否经常有严重的慢性支气管炎（在感冒时咳嗽，吐痰拖很长时间）？",
   "18、你在得感冒时总是必须要卧床（或经常吐痰）吗？",
   "19、是否经常感冒使你一冬天都很难受？",
   "20、你是否有过敏型哮喘？（以某些过敏因素，如花粉等为诱因的哮喘）",
   "21、你是否有哮喘（反复发作的，暂时性的伴有喘音的呼吸困难）",
   "22、你是否经常因咳嗽而感到烦恼？",
   "23、你是否有过咳血？",
   "24、你是否有较重的盗汗（睡时出汗、醒时终止）？",
   "25、你除结核外是否患过慢性呼吸道疾病（或有低烧（热）37-38度）？",
   "26、你是否得过结核病？",
   "27、你与得结核病的人在一起住过吗？",
   "28、医生说过你血压很高吗？",
   "29、医生说过你血压很低吗？",
   "30、你有胸部或心区疼痛吗？",
   "31、你是否经常心动过速（心跳过快）吗？",
   "32、你是否经常心悸（平静时有心脏跳动的感觉）或（感到脉搏有停跳）？",
   "33、你是否经常感到呼吸困难？",
   "34、你是否比别人更容易发生气短（喘不上气）？",
   "35、你既使在坐着的情况下有时也会感到气短吗？",
   "36、你是否经常有严重的下肢浮肿？",
   "37、你即使在热天也因手脚发凉而烦恼吗？",
   "38、你是否经常腿抽筋？",
   "39、医生说过你心脏有毛病吗？",
   "40、你的家属中是否有心脏病人？",
   "41、你是否已脱落了一半以上的牙齿？",
   "42、你是否因牙龈（牙床）出血而烦恼？",
   "43、你是否有经常的牙痛？",
   "44、是否你的舌苔常常很厚？",
   "45、你是否总是食欲不好（不想吃东西）？",
   "46、你是否经常吃零食？",
   "47、你是否吃东西时总是狼吞虎咽？",
   "48、你是否经常胃部不舒服（或有时恶心呕吐）？",
   "49、你饭后是否经常有胀满（腹部膨胀）的感觉？",
   "50、你饭后是否经常打饱嗝（或烧心吐酸水）？",
   "51、你是否经常犯胃病？",
   "52、你是否有消化不良？",
   "53、是否严重胃痛使你常常不得不弯着身子？",
   "54、你是否感到胃部持续不舒服？",
   "55、你的家属中有患胃病的人吗？",
   "56、医生说过你有胃或十二指肠溃疡病（或饭后、空腹时常感到胃痛）？",
   "57、你是否经常腹泻（拉肚子）？",
   "58、你腹泻时是否有严重血便或粘液（粪便发黑、有血液或沾稠物质）？",
   "59、你是否因曾有过肠道寄生虫而感到烦恼？",
   "60、你是否常有严重便秘（大便干燥）？",
   "61、你是否有痔疮（大便时肛门疼痛不适，大便表面带血或便后滴血）？",
   "62、你是否曾患过黄疸（眼、皮肤、尿发黄）？",
   "63、你是否得过严重肝胆疾病？",
   "64、你是否经常有关节肿痛？",
   "65、你的肌肉和关节经常感到发僵或僵硬吗？",
   "66、你的胳膊或腿是否经常感到严重疼痛？",
   "67、严重的风湿病使你丧失活动能力（或有肩、脖子肌肉发紧的现象）？",
   "68、你的家属中是否有人患风湿病？",
   "69、脚发软、疼痛使你的生活严重不便（或经常感到腿、脚发酸）？",
   "70、腰背痛是否达到使你不能持续工作的程度？",
   "71、你是否因身体有严重的功能丧失或畸形（形态异常）而感到烦恼？",
   "72、你的皮肤对温度、疼痛十分敏感，有压痛（或有皮下小出血点）？",
   "73、你皮肤上的切口通常不易愈合（长好）吗？",
   "74、你是否经常脸很红？",
   "75、即使在冷天你也大量出汗吗？",
   "76、是否严重的皮肤搔痒（发痒）使你感到烦恼？",
   "77、你是否经常出皮疹（风疙瘩或疹子）或（有时脸部浮肿）？",
   "78、你是否经常因生疖肿（肿包）而感到烦恼？",
   "79、你是否经常由于严重头痛而感到十分难受？",
   "80、你是否经常由于头痛、头发沉而感到生活痛苦？",
   "81、你的家属中头痛常见吗？",
   "82、你是否有一阵发热、一阵发冷的现象？",
   "83、你经常有一阵阵严重头晕的感觉吗？",
   "84、你是否经常晕倒？",
   "85、你是否晕倒过两次以上？",
   "86、你身体某部分是否有经常麻木或震颤的感觉？",
   "87、你身体某部分曾经瘫痪（感觉和运动能力完全或部分丧失）过吗？",
   "88、你是否有被撞击后失去知觉（什么都不知道了）的现象？",
   "89、你头、面、肩部是否有时有抽搐（突然而迅速的肌肉抽动）的感觉？",
   "90、你是否抽过疯（癫痫发作，也叫抽羊角疯）？",
   "91、你的家属中有无癫痫病人？",
   "92、你是否有严重的咬指甲的习惯？",
   "93、你是否因说话结巴或口吃而烦恼（或因舌头不灵活而导致说话困难）？",
   "94、你是否有梦游症（睡眠时走来走去，事后不能回忆所做的事情）？",
   "95、你是否尿床？",
   "96、在8--14岁（小学和中学）阶段你是否尿床？",
   
   // begin 这里和男性女性问卷不同
   "97、",
   "98、",
   "99、",
   "100、",
   "101、",
   "102、",
   // end 这里和男性问卷不同
   
   "103、你是否每天夜里因小便起床？",
   "104、你是否经常白天小便次数频繁？",
   "105、你是否小便时经常有烧灼感（火烧样的疼痛）？",
   "106、你是否有时有尿失控（不能由意识来控制排尿）？",
   "107、是否医生说过你的肾、膀胱有病？",
   "108、你是否经常感到一阵一阵很疲劳？",
   "109、是否工作使你感到筋疲力竭？",
   "110、你是否经常早晨起床后即感到疲倦和筋疲力尽？",
   "111、你是否稍做一点工作就感到累？",
   "112、你是否经常因累而吃不下饭？",
   "113、你是否有严重的神经衰弱？",
   "114、你的家属中是否有患神经衰弱的人？",
   "115、你是否经常患病？",
   "116、你是否经常由于患病而卧床？",
   "117、你是否总是健康不良？",
   "118、是否别人认为你体弱多病？",
   "119、你的家属中是否有患病的人？",
   "120、你是否曾经因严重的疼痛而不能工作？",
   "121、你是否总是因为担心自己的健康而受不了？",
   "122、你是否总是有病而且不愉快？",
   "123、你是否经常由于健康不好而感到不幸？",
   "124、你得过猩红热吗？",
   "125、你小时候是否得过风湿热、四肢疼痛？",
   "126、你曾患过疟疾吗？",
   "127、你由于严重贫血而接受过治疗吗？",
   "128、你接受过性病治疗吗？",
   "129、你是否有糖尿病？",
   "130、是否医生曾说过你有甲状腺肿（粗脖子病）？",
   "131、你是否接受过肿瘤或癌的治疗？",
   "132、你是否有什么慢性病（或曾接受过原子辐射）？",
   "133、你是否过瘦（体重减轻）？",
   "134、你是否过胖（体重增加）？",
   "135、是否有医生说过你有腿部静脉曲张（腿部青筋暴露）？",
   "136、你是否住院做过手术？",
   "137、你曾有过严重的外伤吗？",
   "138、你是否经常发生小的事故或外伤？",
   "139、你是否有入睡很困难或睡眠不深易醒（或经常做梦）的现象？",
   "140、你是否不能做到每天有规律地放松一下（休息）？",
   "141、你是否不容易做到每天有规律地锻炼？",
   "142、你是否每天吸20支以上的纸烟？",
   "143、你是否喝茶或喝咖啡比一般的人要多？",
   "144、你是否每天喝两次以上的白酒？",
   "145、当你考试或被提问时是否出汗很多或颤抖的很厉害？",
   "146、接近你的主管上级时是否紧张和发抖？",
   "147、当你的上级看着你工作时，你是否不知所措？",
   "148、当必须快速做事情时，你是否有头脑完全混乱的现象？",
   "149、为了避免出错，你做事必须很慢吗？",
   "150、你经常把指令或意图体会（理解）错吗？",
   "151、是否生疏的人或场所使你感到害怕？",
   "152、身边没有熟人时你是否因孤单而恐慌？",
   "153、你是否总是难以下决心（犹豫不决）？",
   "154、你是否总是希望有人在你身边给你出主意？",
   "155、别人认为你是一个很笨的人吗？",
   "156、除了在你自己家以外，在其它任何地方吃东西都感到烦扰吗？",
   "157、你在聚会中也感到孤独和悲伤吗？",
   "158、你是否经常感到不愉快和情绪抑郁（情绪低落）？",
   "159、你是否经常哭？",
   "160、你是否总是感到凄惨与沮丧（灰心失望）？",
   "161、是否你对生活感到完全绝望？",
   "162、你是否经常想死（一死了事）？",
   "163、你是否经常烦恼（愁眉不展）？",
   "164、你的家属中是否有愁眉不展的人？",
   "165、是否稍遇任何一件小事都使你紧张和疲惫？",
   "166、是否别人认为你是一个神经质（紧张不安，易激动）的人？",
   "167、你的家属中是否有神经质的人？",
   "168、你曾患过精神崩溃吗？",
   "169、你的家属中曾有过精神崩溃的人吗？",
   "170、你在精神病院看过病吗（因为你精神方面的问题）？",
   "171、你的家属中是否有人到精神病院看过病（因为精神方面的问题）？",
   "172、你是否经常害羞和神经过敏？",
   "173、你的家属中是否有害羞和神经过敏的人？",
   "174、是否你的感情容易受到伤害？",
   "175、是否你在受到批评时总是心烦意乱？",
   "176、别人认为你是爱挑剔的人吗？",
   "177、你是否经常被人误解？",
   "178、你即使对朋友也必须存戒心吗（不放松警惕）？",
   "179、你是否总是凭一时冲动做事情？",
   "180、你是否容易烦恼和激怒？",
   "181、你若不持续克制自己精神就垮了吗？",
   "182、是否一点不快就使你紧张和发脾气？",
   "183、在别人支使你时是否易生气？",
   "184、别人常使你不快和激怒你吗？",
   "185、当你不能马上得到你所需要的东西时就发脾气吗？",
   "186、你是否经常大发脾气？",
   "187、你是否经常发抖和战栗？",
   "188、你是否经常紧张焦急？",
   "189、你是否会被突然的声音吓一大跳（跳起或发抖得厉害）？",
   "190、是否不管何时，当别人大声对你时，你都被吓得发抖和发软？",
   "191、你对夜间突然的动静是否感到恐惧（害怕）？",
   "192、你是否经常因恶梦而惊醒？",
   "193、你是否头脑中经常反复出现某种恐怖（可怕的）想法？",
   "194、你是否常常毫无理由地突然感到畏惧（害怕）？",
   "195、你是否经常有突然出冷汗的情况？"
 };
 

 	//指导语
 	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：以下有一些有关您健康的情况描述，请您根据自己的实际情况选择“是”“否”，以帮助我们对您的健康做进一步的分析，现在开始吧！" };

	public QuestionnaireModelOfCMI() {
		super(QuestionnaireCodeEnum.CMI);
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
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);

			Object questionDataSourceOfMale = null;
			Object questionDataSourceOfFemale = null;
			
			switch (i) {
			case 96:
				questionDataSourceOfMale = new SingleAnswerPageDataSource("97、你的生殖器是否有过某种严重毛病？", null);
				questionDataSourceOfFemale = new SingleAnswerPageDataSource("97、你是否经常痛经（月经期间及前后小肚子疼）？", null);
				questionFrameModel.setQuestionDataSourceWithMaleAndFemale(questionDataSourceOfMale, questionDataSourceOfFemale);
				break;
			case 97:
				questionDataSourceOfMale = new SingleAnswerPageDataSource("98、你是否经常有生殖器疼痛或触痛（一碰就痛）的现象？", null);
				questionDataSourceOfFemale = new SingleAnswerPageDataSource("98.	你是否在月经期经常得病或感到虚弱？", null);
				questionFrameModel.setQuestionDataSourceWithMaleAndFemale(questionDataSourceOfMale, questionDataSourceOfFemale);
				break;
			case 98:
				questionDataSourceOfMale = new SingleAnswerPageDataSource("99、你是否曾接受过生殖器的治疗？", null);
				questionDataSourceOfFemale = new SingleAnswerPageDataSource("99.	你是否经常有月经期卧床（或经期外，有阴道流血）？", null);
				questionFrameModel.setQuestionDataSourceWithMaleAndFemale(questionDataSourceOfMale, questionDataSourceOfFemale);
				break;
			case 99:
				questionDataSourceOfMale = new SingleAnswerPageDataSource("100、医生说过你有脱肛（直肠脱出肛门以外）吗？", null);
				questionDataSourceOfFemale = new SingleAnswerPageDataSource("100.	你是否经常有持续严重的脸部潮红和出汗？", null);
				questionFrameModel.setQuestionDataSourceWithMaleAndFemale(questionDataSourceOfMale, questionDataSourceOfFemale);
				break;
			case 100:
				questionDataSourceOfMale = new SingleAnswerPageDataSource("101、你是否有过尿血（无痛性的）？", null);
				questionDataSourceOfFemale = new SingleAnswerPageDataSource("101.	你在月经期是否经常有焦躁情绪？", null);
				questionFrameModel.setQuestionDataSourceWithMaleAndFemale(questionDataSourceOfMale, questionDataSourceOfFemale);
				break;
			case 101:
				questionDataSourceOfMale = new SingleAnswerPageDataSource("102、你是否曾因排尿困难而烦恼？", null);
				questionDataSourceOfFemale = new SingleAnswerPageDataSource("102.	你是否经常因白带（阴道白色粘液）异常而烦恼？", null);
				questionFrameModel.setQuestionDataSourceWithMaleAndFemale(questionDataSourceOfMale, questionDataSourceOfFemale);
				break;
			default:
				final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], null);
				questionFrameModel.setQuestionDataSource(questionDataSource);
				break;
			}

			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.true_or_false);
			super.frameModelList.add(questionFrameModel);
		}

	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfBoolean(answerDataSource, 2);
	}
}
