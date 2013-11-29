package cn.skyduck.questionnaire.questionnaire.SAPS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOneFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOnePageDataSource;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.question_frame_fragment.transition_page_1.TransitionPageOneFragmentFactoryMethod;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfSAPS extends FullQuestionnaireModel implements Serializable{

	private static final String[] kQuestionArray = new String[]{
			  //第一部分
        //第1题        1
				"1、听幻觉：病人声称听到语声、杂音或其他声音，最常见的听幻觉包" +
		    "括听到对病人讲话或叫他名字的声音。语声可分为男性或女性，熟悉或" +
		    "不熟悉，批评性的或恭维性的。典型精神分裂症病人的幻听往往是不愉" +
		    "快的、否定的语声。\n除语声以外的杂音或音乐等声音，其特征性较差，" +
		    "严重程度也较轻。 ",
        //第2题       2
		    "2、评论性幻听：指患者听到一种语声对其当时的行为或思想进行实况" +
        "评述。如果病人只有这一种幻听，那就不作前一项评分，而只评此项。" +
        "必须注意的是作者强调了“语声对其当时的行为或思想进行实况评述。”" +
        "假如“病人体验到他的思想成为一种外界的语声”，除了在“17、思想被" +
        "广播”需评分外，本项也应评分。本项定义与普遍熟悉的有所不同\n，必" +
        "须加以注意。",
        //第3题      3
        "3、对话性幻听：指患者听到两人或更多人的声音在对话，\n通常是讨论有" +
        "关病人的事情。需注意不要在“2、评论性幻听”评分。",
        //第4题       4
        "4、躯体或触幻觉：患者体验到特殊的躯体感觉，包括烧灼感、刺痛感，" +
        "以及感到身体的形状或大小发生了变化。因此，需注意对自身的感知综" +
        "合障碍也在本项进行评分。",
        //第5题        5
        "5、嗅幻觉：病人体验到令其极不愉快的气味。病人如确实认为只有他" +
        "自己才能嗅到这种气味，便可予以评分；但如病人认为其它人能嗅到气" +
        "味，则应在妄想项予以评分。",
        //第6题          6
        "6、视幻觉：病人看见实际上并不存在的人或事物。有时是一些简单的形状" +
        "或颜色，但大多为人或人样的对象，也可带有宗教色彩，如魔鬼或耶稣，视" +
        "幻觉若带有宗教色彩，\n评定时则应考虑病人的文化背景。正常人常有的人睡" +
        "前和醒前视幻觉，也应排除在外。",
        //第7题          7
        "7、幻觉总评：评分应依据幻觉的持续时间和严重程度，沉湎于幻觉和相信" +
        "程度，以及对其行为影响进行评定，也要考虑幻觉的荒谬性。未曾提及的如" +
        "味幻觉，也可在此评分",
        //第二部分
        //第8题      8
        "8、被害妄想：患者认为他人正以某种方式，在阴谋迫害他。应根据病情和" +
        "复杂性来评定妄想的严重程度。 ",
        //第9题        9
        "9、嫉妒妄想：病人认为其配偶与某人有不正当的男女关系\n，将各方面的蛛" +
        "丝马迹都作为“证据”，病人往往竭尽全力以求证实；搜寻睡衣上的头发，剃" +
        "须膏残留的气味，衣服上的烟味，或者为情人买礼物的收据等等。有时还设" +
        "下计谋企图捉奸。",
        //第10题        10
        "10、罪恶或过失妄想：病人自认犯有某些可怕的罪行或做了一些不可饶恕的" +
        "事情。有时病人过分地或不适当地沉湎于童年时所做的错事，如手淫。有时" +
        "病人自认为对造成某些灾祸负有责任，如失火或意外事故，而实际上他与这" +
        "些事毫无关系。有时这些妄想带有宗教色彩，例如认为所犯罪行不可饶恕，" +
        "终将受到上帝的惩罚，有时病人简单地认为他应受到社会的惩罚，病人会花" +
        "费大量时间向任何愿意倾听的人忏悔。",
        //第11题           11
        "11、夸大妄想：病人认为他自己有特殊的权力或能力。作者提到有时病人" +
        "怀疑有人想要窃取他的发明。我们根据继发的被窃妄想严重性，在“8、被" +
        "害妄想”也应按相应标准给予评分。",
        //第12题            12
        "12、宗教妄想：病人沉湎于带宗教色彩的错误信念中，有时这些信念在传统" +
        "的宗教系统范围之内，例如关于基督再临\n、假基督或魔鬼附身。有时则完全" +
        "是一种新的宗教系统。\n一般说，宗教妄想超出了患者文化和宗教背景的正常" +
        "范围",
        //第13题         13
        "13、躯体妄想：病人认为其身体有病、不正常或有变化。例如：他的胃或大" +
        "脑正在腐烂，他的手或阴茎变大，或他的面部形状不同往常（变形恐怖）。" +
        "有时躯体妄想伴有幻触或其它幻觉，此时均应予评分。我们进一步规定有明" +
        "确的自身躯体感知综合障碍时，也应在“4、躯体或触幻觉”相应评分。",
        //第14题           14
        "14、关系观念和关系妄想：病人认为那些无关紧要的谈话、\n评述或事件都与" +
        "他有关，或者对他有特殊意义。如果病人有猜疑，而却知道这种想法是错误" +
        "的，那就叫做关系观念\n。如果病人确实相信那些评述或事件是针对他的，则" +
        "称关系妄想。",
        //第15题           15
        "15、被控制妄想：病人主观体验到他的感情或行动被某种外界力量所控制。" +
        "这种妄想的关键在于一种真正而强烈的受到控制的主观体验。例如病人描述" +
        "他的身体被外来力量所占据，以致其身体以特殊的方式移动，或无线电波将" +
        "某种信息送入他的大脑，使他体验到一种并不属于他自己的特殊感情。",
        //第16题          16
        "16、读心妄想：病人认为人们能读出他的心理或知道他的思想（被洞悉感）" +
        "。这不同于思想被广播，病人主观上体验并认识到别人都知道他的思想，但" +
        "他并不认为自己的思想能被人们清楚地听到。",
        //第17题            17
        "17、思想被广播：病人相信其思想被电台广播，因而他自己或其它人都能听到。" +
        "有时病人体验到他的思想成为一种外界的语声，这既是一种幻听又是一种妄想。" +
        "（在“2、评论性幻听”中也作相应评分）。有时病人感到他的思想正在被广播，" +
        "尽管他自己听不见。有时病人相信自己的思想，通过扩音器、电台或电视播放出去。",
        //第18题             18
        "18、思想插入：病人相信有一种并不属于他自己的思想插进他的脑中。",
        //第19题      19
        "19、思想被夺：病人相信其思想被夺走，他开始思考后不久在主观上体验到，" +
        "思想突然被某种外界力量抽掉。",
        //第20题         20
        "20、妄想总评：此项评分应根据妄想持续的时间，是否沉湎于妄想，相信的程度，" +
        "以及妄想对病人行动的影响，来进行评分。也要考虑妄想的荒谬性质。未曾提及的" +
        "妄想也应在此评分。",
        //第三部分 
        //第21题         21
        "21、衣着和外表：病人衣着奇特或以其它稀奇古怪的方式来改变其外观。例如：他" +
        "可能将头发都剃光，或将身体涂成不同颜色。他的衣着很不一般，例如他的装扮往" +
        "往很不适宜或令人难以接受，他可能穿着幻想性的服装，代表历史上某个名人或天" +
        "外来客。他的衣着可能完全不合当时气候，\n如盛夏时穿着棉袄。",
        //第五部分       
        //第22题          22
        "22、社会行为和性行为：病人可能做出一些与社会一般规范不相称的事。例如，当" +
        "众手淫，在不适当的地方大小便或暴露自己的外生殖器。他可能在街上喃喃自语，" +
        "或对一个他从未见过的人谈起自己的私人生活（如在公共汽车里或其它公共场合）" +
        "。他可能在人群中间跪在地上祈祷喊叫，或突然盘腿坐在地上，他可能对陌生异性" +
        "作出不适当的性挑逗行为或言语。",
        //第23题           23
        "23、攻击性和激越性行为：病人行为方式具有攻击性和激越性，常常难以预料。他" +
        "可能不合时宜地同朋友或家人进行争论，或在街上同陌生人攀谈，并忿怒地斥责他" +
        "们。他可能给政府官员或其它与之有过争吵的人写恐吓信。有时，\n病人可能以暴力" +
        "伤害或折磨动物或企图伤人或杀人。",
        //第24题          24
        "24、重复或刻板行为：病人搞出一套重复性或仪式性的动作，反复地做个不停。他" +
        "往往认为这些动作具有象征性意义，或者可以影响别人，或者可以使自己免受影响。" +
        "例如\n，他可能每晚都吃水果软糖，认为水果软糖的不同颜色会产生各种不同结果。" +
        "他可能以一种特殊的顺序进食、穿衣或按某种方式摆置物品。他可能反复地给自己" +
        "或别人写信，有时甚至使用一些不同寻常的或玄奥的语言。",
         //第25题           25
        "25、怪异行为总评：评定时应考虑怪异行为的类型、偏离社会规范的程度、病人对" +
        "其行为偏离正常的认识，以及行为明显怪异的程度。",
        //第四部分 
        //第26题         26
        "26、出轨（联想散漫）：自发性言语从一个主题脱离原先轨道滑到另一个间接有关" +
        "或完全无关的主题上去，常将一些并无明显关系的事情联起来，或从一个观点突然" +
        "转到另一个观点。概念之间的联系常很模糊，有时竟毫无关系。这种类型的言语常" +
        "给人一种“缺乏联系”的印象。",
        //第27题            27
        "27、言语不切题：对问题的回答显得含糊、不切题、甚至无关，可能与所问的内容" +
        "有一段距离或完全无关。过去有时将不切题与联想散漫或出轨笼统地等同起来，近" +
        "年来对不切题的概念已重新下了定义，专指回答问题时的表现，而不是自发性言语" +
        "中的话题转移。",
        //第28题           28
        "28、言语不连贯（语词杂拌）：这种言语常使人根本无法理解。言语不连贯常伴有" +
        "出轨，但与出轨不同，是指每句句子里的词或短语之间没有联系（笔者注：包括思" +
        "维破裂），而出轨指句子与句子间的联系模糊混乱。排除：轻度不合语法结构或特" +
        "殊文化或宗教所有的习惯用语，缺少教育或智力低下。",
        //第29题         29
        "29、逻辑障碍：这种言语的推理结论明显不合逻辑，在上句与下句之间没有逻辑关" +
        "系。可能是错误的归纳推理，也可以是按照错误前提所获得的结论，但并非妄想。" +
        "逻辑障碍可导致妄想，也可能是妄想的结果，因此在妄想系统中呈现的非逻辑性思" +
        "维应包括在妄想项下，不予单独评分。\n因文化、宗教信仰或智力缺损所引起的非逻" +
        "辑性思维也不在此评分。",
        //第30题           30
        "30、赘述：病人表达主题时极其迂回曲折，迟迟才达到目标。在解释某事的过程中，" +
        "病人有时会讲出冗长乏味的细节，有时会作出附加说明。如果不打断他或督促他突出" +
        "要点，这种赘述性回答或叙述会长达几十分钟。检查者往往不得不打断他的讲话以便" +
        "在指定的时间内完成病史询问。\n赘述可与语言内容缺乏或丢失谈话目标同时存在，但" +
        "它与言语内容贫乏不同，含有过多细节。也不同于失去谈话目\n标，如果给病人足够的" +
        "时间讲话，最终仍能达到中心话题\n。它也不同于出轨，其所述细节与某特定目标关系" +
        "密切，\n或者最终仍能达到这种特定的中心思想。",
        //第31题           31
        "31、言语云集：与日常习惯相比，自发性的语量明显较多，\n病人讲得很快，并难以" +
        "打断。有时为了急于表达一个新概念，有些句子往往未能讲完。有些只需几个词或" +
        "几句话就能回答的简单问题，却要讲很长时间，几分钟而不是几秒钟，如果不打断" +
        "他的话就根本不会停止。即使打断他，病人也常会继续讲下去。语声较大而且有力。" +
        "有时严重者会在毫无外界刺激或者无人听的情况下讲个没完（包括意念飘忽与言语" +
        "紧迫）。服用了吩噻嗪类或锂盐的病人常因药物作用讲话会慢下来，那么只有根据" +
        "语量、音量以及与环境的协调性来进行评定。评定时只根据症状具体表现，而不考" +
        "虑是否药物所致进行评分。言语云集可伴有出轨、不切题或不连贯，但并不等同。",
        //第32题          32
        "3、言语随境转移：在讨论或交谈的过程中，病人的话讲到一半就停下来，转移到" +
        "有关周围事物的主题上去，如书桌上的东西、检查者的衣着或外表等等。",
        //第33题            33
        "33、音联：是一种根据词音而不是词意来选用词汇的言语方式。因此，言语显得" +
        "含糊、难以理解，或引进不少多余的词汇。除了同韵外，还可以同音，于是一个" +
        "发音相似的词会引出完全不同的概念。",
        //第34题         34
        "34、阳性思维形式障碍总评：评定时应考虑异常类型，对病人交流能力的影响，" +
        "异常频度与严重程度。"
	};
 
	// 答案
	private static final String[][] kAnswerArray = new String[][] {
			// 1
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，病人仅偶尔听到杂音或单个的词",
        " (3) 中度，清楚的言语声，至少每星期都出现",
        " (4) 显著，常常听到清楚的言语声",
        " (5) 严重，几乎每天都听到有言语声"},
			// 2
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，出现一次或二次",
        " (3) 中度，至少每星期都出现",
        " (4) 显著，常常出现",
        " (5) 严重，几乎天天都出现" },
			// 3
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，出现一次或二次",
        " (3) 中度，至少每星期都出现",
        " (4) 显著，常常出现",
        " (5) 严重，几乎天天都出现" },
			// 4
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，出现一次或二次",
        " (3) 中度，至少每星期都出现",
        " (4) 显著，常常出现",
        " (5) 严重，几乎天天都出现" },
			// 5
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，出现一次或二次",
        " (3) 中度，至少每星期都出现",
        " (4) 显著，常常出现",
        " (5) 严重，几乎天天都出现"},
			// 6
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，出现一次或二次",
        " (3) 中度，至少每星期都出现",
        " (4) 显著，常常出现",
        " (5) 严重，几乎天天都出现" },
			// 7
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，幻觉肯定存在，但很少出现，有时病人会怀疑它们是否存在",
        " (3) 中度，幻觉很清楚，但偶尔出现，对病人生活有一定程度的影响",
        " (4) 显著，幻觉非常清楚，常常出现，并影响其生活",
        " (5) 严重，幻觉几乎天天出现，并且有时显得奇特、生动，明显干扰病人生活"},
			// 8
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄想有怀疑",
        " (3) 中度，妄想清楚、持续、病人坚信不疑",
        " (4) 显著，妄想持续存在，病人坚信不疑",
        " (5) 严重，妄想内容复杂而完整，并影响病人的行为，病人大多数时间都沉湎于妄想" +
        "之中，妄想的某些方面或病人的反应可能相当怪异" },
			// 9
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄想有怀疑",
        " (3) 中度，妄想清楚、持续、病人坚信不疑",
        " (4) 显著，妄想持续存在，病人坚信不疑",
        " (5) 严重，妄想内容复杂而完整，并影响病人的行为，病人大多数时间都沉湎于妄想" +
        "之中，妄想的某些方面或病人的反应可能相当怪异"},
			// 10
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄想有怀疑",
        " (3) 中度，妄想清楚、持续、病人坚信不疑",
        " (4) 显著，妄想持续存在，病人坚信不疑",
        " (5) 严重，妄想内容复杂而完整，并影响病人的行为，病人大多数时间都沉湎于妄想" +
        "之中，妄想的某些方面或病人的反应可能相当怪异" },
			// 11
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄想有怀疑",
        " (3) 中度，妄想清楚、持续、病人坚信不疑",
        " (4) 显著，妄想持续存在，病人坚信不疑",
        " (5) 严重，妄想内容复杂而完整，并影响病人的行为，病人大多数时间都沉湎于妄想" +
        "之中，妄想的某些方面或病人的反应可能相当怪异"},
			// 12
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄想有怀疑",
        " (3) 中度，妄想清楚、持续、病人坚信不疑",
        " (4) 显著，妄想持续存在，病人坚信不疑",
        " (5) 严重，妄想内容复杂而完整，并影响病人的行为，病人大多数时间都沉湎于妄想" +
        "之中，妄想的某些方面或病人的反应可能相当怪异"},
			// 13
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄想有怀疑",
        " (3) 中度，妄想清楚、持续、病人坚信不疑",
        " (4) 显著，妄想持续存在，病人坚信不疑",
        " (5) 严重，妄想内容复杂而完整，并影响病人的行为，病人大多数时间都沉湎于妄想" +
        "之中，妄想的某些方面或病人的反应可能相当怪异"},
			// 14
			{ " (0) 无",
        " (1) 可疑",
        " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄想有怀疑",
        " (3) 中度，出现过几次",
        " (4) 显著，至少每星期都出现",
        " (5) 严重，频繁出现" },
			// 15
			{ " (0) 无",
       	" (1) 可疑",
        " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄想有怀疑",
        " (3) 中度，受控制的体验清楚，出现过两次或三次",
        " (4) 显著，受控制的体验清楚，频繁出现，行为可能受影响",
        " (5) 受控制的体验清楚，频繁出现，渗入病人的生活，并常常影响其行为"},
			// 16
			{ " (0) 无",
        " (1) 可疑 ",
        " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄想有怀疑",
        " (3) 中度，体验清楚，出现过两次或三次",
        " (4) 显著，体验清楚，频繁出现，行为可能受影响",
        " (5) 体验清楚，频繁出现，渗入病人的生活，并常常影响其行为" },
			// 17
			{ " (0) 无；",
        " (1) 可疑 ",
        " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄\t\t\t 想有怀疑",
        " (3) 中度，体验清楚，出现过两次或三次",
        " (4) 显著，体验清楚，频繁出现，行为可能受影响",
        " (5) 体验清楚，频繁出现，渗入病人的生活，并常常影响其行为" },
			// 18
			{" (0) 无；",
       " (1) 可疑 ",
       " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄\t\t\t 想有怀疑",
       " (3) 中度，体验清楚，出现过两次或三次",
       " (4) 显著，体验清楚，频繁出现，行为可能受影响",
			 " (5) 体验清楚，频繁出现，渗入病人的生活，并常常影响其行为"},
			// 19
			{" (0) 无；",
       " (1) 可疑 ",
       " (2) 轻度，妄想简单，可有几种不同的形式，病人有时可能会对妄想有怀疑",
       " (3) 中度，体验清楚，出现过两次或三次",
       " (4) 显著，体验清楚，频繁出现，行为可能受影响",
       " (5) 体验清楚，频繁出现，渗入病人的生活，并常常影响其行为" },
			// 20
			{" (0) 无",
       " (1) 可疑",
       " (2) 轻度，妄想肯定存在，但病人常常对此有怀疑",
       " (3) 中度，病人对妄想坚信不疑，但可能偶尔出现并且对其行为影响甚小",
       " (4) 显著，妄想牢固，频繁出现并影响病人行为",
       " (5) 严重，妄想复杂、完整并泛化，妄想牢固并严重影响病人行为，妄想有时有些奇特或不寻常"},
			// 21
			{" (0) 无",
       " (1) 可疑",
       " (2) 轻度，衣着和外表偶尔表现古怪",
       " (3) 中度，外表或外观显然与众不同并吸引他人注意",
       " (4) 显著，外表或外观特别古怪",
       " (5) 严重，病人的外表或外观非常古怪或奇特" },
		   // 22
			{" (0) 无",
       " (1) 可疑",
       " (2) 轻度，偶尔出现古怪行为",
       " (3) 中度，常常出现古怪行为",
       " (4) 显著，行为非常古怪，如当众手淫",
       " (5) 严重，行为极其古怪奇特" },
			// 23
			{" (0) 无",
       " (1) 可疑",
       " (2) 轻度，偶尔出现攻击性和激越性行为",
       " (3) 中度，如给陌生人写恐吓信",
       " (4) 显著，如恐吓别人，当众高谈阔论地长篇演说",
       " (5) 严重，如虐待残害动物"},
			// 24
			{" (0) 无",
       " (1) 可疑",
       " (2) 轻度，偶尔出现重复或刻板行为",
       " (3) 中度，如缺乏象征意义的吃或穿衣的仪式性动作",
       " (4) 显著，如具有象征意义的吃或穿衣的仪式性动作",
       " (5) 严重，如以一种令人不能理解的语言写日记"},
      // 25
      {" (0) 无",
       " (1) 可疑",
       " (2) 轻度，偶尔出现与众不同或明显古怪的行为，通常有些自知力",
       " (3) 中度，行为明显偏离社会规范并有些怪异，可能有些自知力",
       " (4) 显著，行为显著偏离社会规范和怪异，可能有些自知力",
       " (5) 严重，行为极其奇特或怪异。可能包括某些偏激的行动如谋杀，缺乏自知力"},
      // 26
      {" (0) 无",
       " (1) 可疑",
       " (2) 轻度，偶尔出现出轨，话题仅仅稍有变换",
       " (3) 中度，严重脱轨，病人有时难以继续",
       " (4) 显著，出轨频繁出现，病人常常难以继续",
       " (5) 严重，出轨频繁而严重，病人的言语几乎令人不可理解"},
      // 27
      {" (0) 无",
       " (1) 可疑",
       " (2) 轻度，一或二次不切题",
       " (3) 中度，偶尔不切题",
       " (4) 显著，频繁不切题",
       " (5) 严重，严重不切题以致与病人交谈极其困难"},
      // 28
      {" (0) 无",
       " (1) 可疑 ",
       " (2) 轻度，偶尔不连贯",
       " (3) 中度，频繁突然出现不连贯",
       " (4) 显著，病人言语不可理解",
       " (5) 严重，病人的大多数言语不可理解"},
      // 29
      {" (0) 无",
       " (1) 可疑",
       " (2) 轻度，偶尔出现逻辑障碍",
       " (3) 中度，频繁出现逻辑障碍",
       " (4) 显著，病人的许多言语不合逻辑",
       " (5) 严重，病人的大多数言语不合逻辑"},
      // 30
      {" (0) 无",
       " (1) 可疑",
       " (2) 轻度，偶尔出现赘述",
       " (3) 中度，频繁出现赘述",
       " (4) 显著，病人的许多言语表现为赘述",
       " (5) 严重，病人的大多数言语表现为赘述"},
      // 31
      {" (0) 无",
       " (1) 可疑",
       " (2) 轻度，偶尔出现言语云集",
       " (3) 中度，对一个简单的问题常要回答数分钟，即使无人听也会讲，讲话快而声大",
       " (4) 显著，对一个简单问题通常要回答三分钟，有时在没有外界刺激的情况下也会开始谈话，难以打断",
       " (5) 严重，病人几乎一直在讲话，根本不能打断，或者声音很大，遮盖了别人的讲话声"},
      // 32
      {" (0) 无",
       " (1) 可疑",
       " (2) 轻度，偶尔出现言语随境转移",
       " (3) 中度，出现过2~4次",
       " (4) 显著，出现过5~10次",
       " (5) 严重，出现过10次以上"},
      // 33
      {" (0) 无",
       " (1) 可疑",
       " (2) 轻度，偶尔出现",
       " (3) 中度，出现过2~4次",
       " (4) 显著，出现过5~10次",
       " (5) 严重，出现过10次以上"},
      // 34
      {" (0) 无",
       " (1) 可疑",
       " (2) 轻度，偶尔出现",
       " (3) 中度，频繁出现，有时令人难以理解",
       " (4) 显著，常常令人难以理解",
       " (5) 严重，不能理解"}
                                           
	};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] {
		  // 第一段
			"\t\t\t\t指导语：本量表评定的时间范围为最近一月的表现。如应用于药理学研究，每周评定一次，以观察治疗效应。交谈时间一般为45分钟到1小时。" + "应该采用标准的临床精神检查来评定症状。在评定阳性思维形式障碍时，可先就一个比较中性的题目与患者交谈5~10分钟，以便观察病人讲话和回答的方式。然后再针对各种阳性症状提出具体问题。"
					+ "除了临床精神检查外，还应从其它方面收集资料，例如直接观察病人家属的反应、护士的报告及病人自己的申述。一般说，就幻觉和妄想而言，如果他能够合作交谈的话，病人自己的申述是比较可靠的资料来源。但是，建议依据观察和他人的报告来评定怪异行为和阳性思维形式障碍。",
					
			// 第二段
			"\t\t\t\t各阳性症状群的最后一项是因子分评定，必须全面评定各症状群的性质和严重度。在有些情况下，即使该症状群中的其它症状并不存在，单单一个症状（如极其严重的被害妄想）也可评高分。 " };

	public QuestionnaireModelOfSAPS() {
		super(QuestionnaireCodeEnum.SAPS);

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
		transitionPageFrameModel1.setQuestionDataSource("\t\t\t\tPartⅠ幻觉\n\n" + "\t\t\t提示：真正的幻觉应与错觉（对外界刺激的错误感知）、入睡前和醒前的体验（在正要入睡或醒来时发生），或极其生动的正常思维过程相区分。如果幻觉带有宗教色彩，则应按照社会和文化背景鉴别是否正常。在酒精、药物或严重躯体疾病的直接影响下发生的幻觉，不应列入本表。应请病人详述幻觉的细节。");
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
		transitionPageFrameModel2.setQuestionDataSource("\t\t\t\tPartⅡ妄想\n\n" + "\t\t\t提示：对每种妄想的严重程度的评分，以及妄想总评分均应考虑其持续性和复杂性。病人按照妄想采取行动与否，病人对妄想是否怀疑，以及妄想信念偏离正常的程度。如作妄想评分，应有具体实例。");
		super.frameModelList.add(transitionPageFrameModel2);

		// 第二部分 问题
		for (int i = 7; i < 20; i++) {
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
		transitionPageFrameModel3.setQuestionDataSource("\t\t\t\tPartⅢ 怪异行为\n\n" + "\t\t\t提示：病人的行为很不寻常、怪异或带幻想性。例如，病人在糖罐里小便，将自己的身体左右两半涂上不同颜色，或将一窝猪在墙上撞死。有时根据病人自己的诉述，有时依靠别的来源，有时可直接观察到。应排除酒精或药物直接引起的怪异行为。一般在评定时还要考虑到社会和文化准则，并应每例注明详情。\n\n");
		super.frameModelList.add(transitionPageFrameModel3);

		// 第三部分 问题
		for (int i = 20; i < 24; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			IQuestionnaireFramePageFragmentFactoryMethod questionTypeThreeFragmentFactory = new InputAndSingleOneFragmentFactoryMethod();
			final InputAndSingleOnePageDataSource questionPartTwoDataSource = new InputAndSingleOnePageDataSource(kQuestionArray[i], anwserList, "备注 :");
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(questionTypeThreeFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionPartTwoDataSource);
			super.frameModelList.add(questionFrameModel);
		}
		// 第四部分过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage4 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel4 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage4);
		transitionPageFrameModel4.setQuestionDataSource("\t\t\t\tPartⅣ 阳性思维形式障碍\n\n" + "\t\t\t提示：为了评价思维障碍，应让病人就某话题讲上5~10分钟，尤其是与其精神病情无关的话题。检查者应密切观察概念的前后关系，另外，如果概念模糊或令人难以理解，检查者应该让病人解释清楚或进一步发挥。还应密切观察病人如何回答各种不同类型问题，从简单（你出生在什么地方？）到复杂（你怎么看待当今政府所做的一切？）。应该与病人交谈45分钟左右，如果时间短，评分应作相应调整。");
		super.frameModelList.add(transitionPageFrameModel4);

		// 第四部分 问题
		for (int i = 24; i < kQuestionArray.length; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			QuestionFramePageModel questionFrameModel = null;
			if (i == 24) {
				IQuestionnaireFramePageFragmentFactoryMethod questionTypeFourFragmentFactory = new InputAndSingleOneFragmentFactoryMethod();
				final InputAndSingleOnePageDataSource questionPartTwoDataSource = new InputAndSingleOnePageDataSource(kQuestionArray[i], anwserList, "备注 :");
				questionFrameModel = new QuestionFramePageModel(questionTypeFourFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionPartTwoDataSource);
			} else {
				final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
				questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionDataSource);
				questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			}
			super.frameModelList.add(questionFrameModel);
		}
	}

	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfSAPS(option, this);
	}
}
