package cn.skyduck.questionnaire.questionnaire.MMPI;

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
public class QuestionnaireModelOfMMPI extends FullQuestionnaireModel implements Serializable{

	// 问题
	private static final String[] kQuestionArray = new String[] {
		"1、我喜欢看机械方面的杂志。",
	   "2、我的胃口很好。",
	   "3、我早上起来的时候，多半觉得睡眠充足，头脑清醒。",
	   "4、我想我会喜欢图书管理员的工作。",
	   "5、我很容易被吵醒。",
	   "6、我喜欢看报纸上的犯罪新闻。",
	   "7、我的手脚经常是很暖和的。",
	   "8、我的日常生活中，充满了使我感兴趣的事情。",
	   "9、我现在工作（学习）的能力，和从前差不多。",
	   "10、我的喉咙里总好像有一块东西堵着似的。",
	   "11、一个人应该去了解自己的梦，并从中得到指导和警告。",
	   "12、我喜欢侦探小说或神秘小说。",
	   "13、我总是在很紧张的情况下工作。",
	   "14、我每个月至少有一、二次拉肚子。",
	   "15、偶尔我会想到一些坏得说不出口的事。",
	   "16、我深信生活对我是残酷的。",
	   "17、我的父亲是一个好人。",
	   "18、我很少有大便不通的毛病。",
	   "19、当我干一件新的工作时，总喜欢别人告诉我，我应该接近谁。",
	   "20、我的性生活是满意的。",
	   "21、有时我非常想离开家。",
	   "22、有时我会哭一阵笑一阵，连自己也不能控制。",
	   "23、恶心和呕吐的毛病使我苦恼。",
	   "24、似乎没有一个人了解我。",
	   "25、我想当一个歌唱家。",
	   "26、当我处境困难的时候，我觉得最好是不开口。",
	   "27、有时我觉得有鬼神附在我身上。",
	   "28、当别人惹了我时，我觉得只要有机会就应报复，这是理所当然的。",
	   "29、我有胃酸过多的毛病，一星期要犯好几次，使我苦恼。",
	   "30、有时我真想骂人。",
	   "31、每隔几个晚上我就作恶梦。",
	   "32、我发现我很难把注意力集中到一件工作上。",
	   "33、我曾经有过很特别，很奇怪的体验。",
	   "34、我时常咳嗽。",
	   "35、假如不是有人和我作对，我一定会有更大的成就。",
	   "36、我很少担心自己的健康。",
	   "37、我从来没有为了我的性方面的行为出过事。",
	   "38、我小的时候，有一段时间我干过小偷小摸的事。",
	   "39、有时我真想摔东西。",
	   "40、有很多时候我宁愿坐着空想，而不愿做任何事情。",
	   "41、我曾一连几天、几个星期、几个月什么也不想干，因为我总是提不起精神。",
	   "42、我家里人对我已选择的工作（或将要选择的职业）不满意。",
	   "43、我睡得不安，容易被惊醒。",
	   "44、我觉得我的头到处都疼。",
	   "45、有时我也讲假话。",
	   "46、我的判断力比以往任何时候都好。",
	   "47、每星期至少一、二次，我突然觉得无缘无故地全身发热。",
	   "48、当我与人相处的时候听到别人谈论稀奇古怪的事，我就心烦。",
	   "49、最好是把所有的法律全都不要。",
	   "50、有时我觉得我的灵魂离开了我的身体。",
	   "51、我的身体和我大多数朋友一样的健康。",
	   "52、遇到同学或不常见的朋友，除非他们先向我打招呼，不然我就装作没看见。",
	   "53、一位牧师（和尚、道士、神父、阿訇等教士）能用祈祷和把手放在病人头上来治病。",
	   "54、认识我的人差不多都喜欢我。",
	   "55、我从来没有因为胸部痛和心痛而感到苦恼。",
	   "56、我小时候，曾经因为胡闹而受过学校的处分。",
	   "57、我和别人一见面就熟了（自来熟）。",
	   "58、一切事情都由老天爷安排好了。",
	   "59、我时常得听从某些人的指挥，其实他们还不如我高明。",
	   "60、我不是每天都看报纸上的每一篇社论。",
	   "61、我从未有过正常的生活。",
	   "62、我身体某些部分常有像火烧、刺痛、虫爬、麻木的感觉。",
	   "63、我的大便正常，不难控制。",
	   "64、有时我会不停地做一件事，直到别人不耐烦为止。",
	   "65、我爱我的父亲。",
	   "66、我能在我周围看到其他人所看不到的东西、动物和人。",
	   "67、希望我能像别人那样快乐。",
	   "68、我从未感到脖子（颈）后面疼痛。",
	   "69、和我性别相同的人对我有强烈的吸引力。",
	   "70、我过去经常喜欢玩“丢手帕”的游戏。",
	   "71、我觉得许多人喜欢夸大自己的不幸，以便得到别人的同情和帮助。",
	   "72、我为每隔几天或经常感到心口（胃）不舒服而烦恼。",
	   "73、我是个重要人物。",
	   
	   // 74题男性女性问题不同
	   "74、",
	   // 74题男性女性问题不同
	   "75、我有时发怒。",
	   "76、我时常感到悲观失望。",
	   "77、我喜欢看爱情小说。",
	   "78、我喜欢诗。",
	   "79、我的感情不容易收伤害。",
	   "80、我有时捉弄动物。",
	   "81、我想我会喜欢干森林管理员那一类的工作。",
	   "82、和人争辩的时候，我常争不过别人。",
	   "83、任何人只要他有能力，而且愿意努力工作是能成功的。",
	   "84、近来，我觉得很容易放弃对某些事物的希望。",
	   "85、有时我被别人的东西，如鞋、手套等等所强烈吸引，\n虽然这些东西对我毫无用处，但我总想摸摸它或把它偷来。",
	   "86、我确实缺少自信心。",
	   "87、我愿意做一名花匠。",
	   "88、我总觉得人生是有价值的。",
	   "89、要使大多数人相信事实的真相，是要经过一番辩论的。",
	   "90、有时我将今天应该做的事，拖到明天去做。",
	   "91、我不在乎别人拿我开玩笑。",
	   "92、我想当个护士。",
	   "93、我觉得大多数人是为了向上爬而不惜说谎的。",
	   "94、许多事情，我做过以后就后悔了。",
	   "95、我几乎每星期都去教堂（或常去寺庙）。",
	   "96、我几乎没有和家里人吵过嘴。",
	   "97、有时我有一种强烈的冲动，去做一些惊人或有害的事。",
	   "98、我相信善有善报，恶有恶报。",
	   "99、我喜欢参加热闹的聚会。",
	   "100、我碰到一些千头万绪的问题，使我感到犹豫不决。",
	   "101、我认为女的在性生活方面，应该和男的有同等的自由。",
	   "102、我认为最难的是控制我自己。",
	   "103、我很少有肌肉抽筋或颤抖的毛病。",
	   "104、我似乎对什么事情都不在乎。",
	   "105、我身体不舒服的时候，我有时发脾气。",
	   "106、我总觉得我自己好像作错了什么事或犯了什么罪。",
	   "107、我经常是快乐的。",
	   "108、我时常觉得头胀鼻塞似的。",
	   "109、有些人太霸道，即使我明知他们是对的，也要和他们对着干。",
	   "110、有人想害我。",
	   "111、我从来没有为寻求刺激而去做危险的事。",
	   "112、我时常认为必须坚持那些我认为正确的事。",
	   "113、我相信法制。",
	   "114、我常觉得头上好像向有一根绷得紧紧的带子。",
	   "115、我相信人死后还会有“来世”。",
	   "116、我更喜欢我下了堵的比赛和游戏。",
	   "117、大部分人之所以是诚实的，主要是因为怕被人识破。",
	   "118、我在上学的时候，有时因胡闹而被领导叫去。",
	   "119、我说话总是那样不快也不慢，不含糊也不嘶哑。",
	   "120、我在外边和朋友们一起吃饭的时候，比在家规矩得多。",
	   "121、我相信有人暗算我。",
	   "122、我似乎和我周围的人一样精明能干。",
	   "123、我相信有人在跟踪我。",
	   "124、大多数人不惜用不正当的手段谋取利益，而不愿失掉机会。",
	   "125、我的胃有很多毛病。",
	   "126、我喜欢戏剧。",
	   "127、我知道我的烦恼是谁造成的。",
	   "128、看到血的时候，我既不怕，也不难受。",
	   "129、我自己时常弄不清为什么会这样爱生气和发牢骚。",
	   "130、我从来没有吐过血，或咳过血。",
	   "131、我不为得病而担心。",
	   "132、我喜欢栽花或采集花草。",
	   "133、我从来没有放纵自己发生过任何不正常的性行为。",
	   "134、有时我的思想跑得太快都来不及表达出来。",
	   "135、假如我能不买票白看电影，而且不会被人发觉，我可能会去做的。",
	   "136、如果别人待我好，我常常怀疑他们别有用心。",
	   "137、我相信我的家庭生活，和我所认识的许多人一样幸福快乐。",
	   "138、批评和责骂都使我非常伤心。",
	   "139、有时我仿佛觉得我必须伤害自己或别人。",
	   "140、我喜欢做饭烧菜。",
	   "141、我的行为多半受我周围人的习惯所支配。",
	   "142、有时我觉得我真是毫无用处。",
	   "143、小时候我曾加入过一个团伙，有福共享，有祸同当。",
	   "144、我喜欢当兵。",
	   "145、有时我想借故和别人打架。",
	   "146、我喜欢到处乱逛，如果不行，我就不高兴。",
	   "147、由于我经常不能当机立断，因而失去许多良机。",
	   "148、当我正在做一件重要事情的时候，如果有人向我请教或打扰我，我会不耐烦的。",
	   "149、我以前写过日记。",
	   "150、做游戏的时候，我只愿赢而不愿输。",
	   "151、有人一直想毒死我。",
	   "152、大多数晚上我睡觉时，不受什么思想干扰。",
	   "153、近几年来大部分时间，我的身体都很好。",
	   "154、我从来没有过抽风的毛病。",
	   "155、现在我的体重既没有增加也没有减轻。",
	   "156、有一段时间，我自己做过的事情全不记得了。",
	   "157、我觉得我时常无缘无故的受到惩罚。",
	   "158、我容易哭。",
	   "159、我不能像从前那样理解我所读的东西了。",
	   "160、在我一生中，我从来没有感觉到象现在这么好。",
	   "161、有时候我觉得我的头顶一碰就疼。",
	   "162、我痛恨别人以不正当的手段捉弄我，使我不得不认输。",
	   "163、我不容易疲倦。",
	   "164、我喜欢研究和阅读与我目前工作有关的东西。",
	   "165、我喜欢结识一些重要人物，这样会使我感到自己也很重要。",
	   "166、我很害怕从高处往下看。",
	   "167、即使我家里有人犯法，我也不会紧张。",
	   "168、我的脑子有点毛病。",
	   "169、我不怕管钱。",
	   "170、我不在乎别人对我有什么看法。",
	   "171、在聚会当中，尽管有人出风头，如果让我也这样做，我会感到很不舒服。",
	   "172、我时常需要努力使自己不显出怕羞的样子。",
	   "173、我过去喜欢上学。",
	   "174、我从来没有昏倒过。",
	   "175、我很少头昏眼花。",
	   "176、我不大怕蛇。",
	   "177、我母亲是个好人。",
	   "178、我的记忆力似乎还不错。",
	   "179、有关性方面的问题，使我烦恼。",
	   "180、我觉得我遇到生人的时候就不知道说什么好了。",
	   "181、无聊的时候，我就会惹事寻求开心。",
	   "182、我怕自己会发疯。",
	   "183、我反对把钱给乞丐。",
	   "184、我时常听到说话的声音，不知道它是从哪里来的。",
	   "185、我的听觉显然和大多数人一样好。",
	   "186、当我要做一件事的时候，我常发觉我的手在发抖。",
	   "187、我的双手并没有变得笨拙不灵。",
	   "188、我能阅读好长的时间，而眼睛不觉得累。",
	   "189、许多时候，我觉得浑身无力。",
	   "190、我很少头痛。",
	   "191、有时，当我难为情的时候，会出很多的汗，这使我非常苦恼。",
	   "192、我走路时保持平稳，并不困难。",
	   "193、我没哮喘这一类病。",
	   "194、我曾经有过几次突然不能控制自己的行动或言语，但当时我的头脑还很清醒。",
	   "195、我所认识的人里不是个个我都喜欢。",
	   "196、我喜欢到我从来没有到过的地方去游览。",
	   "197、有人一直想抢我的东西。",
	   "198、我很少空想。",
	   "199、我们应该把有关“性”方面的主要知识告诉孩子。",
	   "200、有人想窃取我的思想和计划。",
	   "201、但愿我不象现在这样的害羞。",
	   "202、我相信我是一个被谴责的人。",
	   "203、假若我是一个新闻记者，我将喜欢报导戏剧界的新闻。",
	   "204、我喜欢做一个新闻记者。",
	   "205、有时我控制不住想要偷点东西。",
	   "206、我很信神，程度超过多数人。",
	   "207、我喜欢很多不同种类的游戏和娱乐。",
	   "208、我喜欢和异性说笑。",
	   "209、我相信我的罪恶是不可饶恕。",
	   "210、每一种东西吃起来味道都一样。",
	   "211、我白天睡觉，晚上却睡不着。",
	   "212、我家里的人不把我当做小孩子，而把我当做大人看待。",
	   "213、走路时，我很小心地跨过人行道上的接缝。",
	   "214、我从来没有为皮肤上长点东西而烦恼。",
	   "215、我曾经饮酒过度。",
	   "216、和别人的家庭比较，我的家庭缺乏爱和温暖。",
	   "217、我时常感到自己在为某些事而担忧。",
	   "218、当看到动物受折磨的时候，我并不是特别难受。",
	   "219、我想我会喜欢建筑承包的工作。",
	   "220、我爱我的母亲。",
	   "221、我喜欢科学。",
	   "222、即使我以后不能报答恩惠，我也愿向朋友求助。",
	   "223、我很喜欢打猎。",
	   "224、我父母经常反对那些和我交往的人。",
	   "225、有时我也会说说人家的闲话。",
	   "226、我家里有些人的习惯，使我非常讨厌。",
	   "227、人家告诉我，我在睡觉中起来走路（梦游）。",
	   "228、有时我觉得我非常容易地做出决定。",
	   "229、我喜欢同时参加几个团体。",
	   "230、我从来没有感到心慌气短。",
	   "231、我喜欢谈论两性方面的事。",
	   "232、我曾经立志要过一种以责任为重的生活，我一直照此谨慎从事。",
	   "233、我有时阻止别人做某些事，并不是因为那种事，有多大影响，而是在“道义”上我应该干预他。",
	   "234、我很容易生气，但很快就平静下来。",
	   "235、我已独立自主，不受家庭的约束。",
	   "236、我有很多心事。",
	   "237、我的亲属几乎全都同情我。",
	   "238、有时我十分烦躁，坐立不安。",
	   "239、我曾经失恋过。",
	   "240、我从来不为我的外貌而发愁。",
	   "241、我常梦到一些不可告人的事。",
	   "242、我相信我并不比别人更为神经过敏。",
	   "243、我几乎没有什么地方疼痛。",
	   "244、我的做事方法容易被人误解。",
	   "245、我的父母和家里人对我过去挑剔。",
	   "246、我脖子（颈）上时常出现红斑。",
	   "247、我有理由妒忌我家里的某些人。",
	   "248、我有时无缘无故地，甚至在不顺利的时候也会觉得非常快乐。",
	   "249、我相信阴间有魔鬼和地狱。",
	   "250、有人想把世界上所能得到的东西都夺到手，我决不责怪他。",
	   "251、我曾经发呆（发愣）停止活动，不知道周围发生了什么事情。",
	   "252、谁也不关心谁的遭遇。",
	   "253、有些人所做的事，虽然我认为是错的，但我仍然能够友好地对待他们。",
	   "254、我喜欢和一些能互相开玩笑的人在一起。",
	   "255、在选举的时候，有时我会选出我不熟悉的人。",
	   "256、报纸上只有“漫画”最有趣。",
	   "257、凡是我所做的事，我都指望能够成功。",
	   "258、我相信有神。",
	   "259、做什么事情，我都感到难以开头。",
	   "260、在学校里，我是个笨学生。",
	   "261、如果我是个画家，我喜欢画花。",
	   "262、我虽然相貌不好看，也不因此而苦恼。",
	   "263、即使在冷天我也很容易出汗。",
	   "264、我十分自信。",
	   "265、对任何人都不信任，是比较安全的。",
	   "266、每星期至少有一两次我十分兴奋。",
	   "267、人多的时候，我不知道说些什么话好。",
	   "268、在我心情不好的时候，总会有一些事使我高兴起来。",
	   "269、我能很容易使人怕我，有时我故意这样作来寻开心。",
	   "270、我离家外出的时候，从来不担心家里门窗是否关好锁好了。",
	   "271、我不会责怪一个人欺负自找没趣的人。",
	   "272、我有时精力充沛。",
	   "273、我的皮肤上有一两处麻木了。",
	   "274、我的视力和往年一样好。",
	   "275、有人控制着我的思想。",
	   "276、我喜欢小孩子。",
	   "277、有时我非常欣赏骗子的机智，我甚至希望他能侥幸混过去。",
	   "278、我时常觉得有些陌生人用挑剔的眼光盯着我。",
	   "279、我每天喝特别多的水。",
	   "280、大多数人交朋友，是因为朋友对他们有用。",
	   "281、我很少注意我的耳鸣。",
	   "282、通常我爱家里人，偶尔也恨他们。",
	   "283、假使我是一个新闻记者，我将很愿意报导体育新闻。",
	   "284、我确信别人正在议论我。",
	   "285、偶尔我听了下流的笑话也会发笑。",
	   "286、我独自一个人的时候，感到更快乐。",
	   "287、使我害怕的事比我的朋友们少得多。",
	   "288、恶心呕吐的毛病使我苦恼。",
	   "289、当一个罪犯可以通过能言善辩的律师开脱罪责时，我对法律感到厌恶。",
	   "290、我总是在很紧张的情况下工作的。",
	   "291、在我这一生中，至少有一两次我觉得有人用催眠术指使我做了一些事。",
	   "292、我不愿意同人讲话，除非他先开口。",
	   "293、有人一直想影响我的思想。",
	   "294、我从来没有犯过法。",
	   "295、我喜欢看《红楼梦》这一类的小说。",
	   "296、有些时候，我会无缘无故地觉得非常愉快。",
	   "297、我希望我不再受那种和性方面有关的念头所困扰。",
	   "298、假若有几个人闯了祸，他们最好先编一套假话，而且不改口。",
	   "299、我认为我比大多数人更重感情。",
	   "300、在我的一生当中，从来没有喜欢过洋娃娃。",
	   "301、许多时候，生活对我来说是一件吃力的事。",
	   "302、我从来没有为了我的性方面的行为出过事。",
	   "303、对于某些事情我很敏感，以至使我不能提起。",
	   "304、在学校里，要我在班上发言，是非常困难的。",
	   "305、即使和人们在一起，我还是经常感到孤单。",
	   "306、应得的同情，我全得到了。",
	   "307、我拒绝玩那些我玩得不好的游戏。",
	   "308、有时我非常想离开家。",
	   "309、我交朋友差不多和别人一样地容易。",
	   "310、我的性生活是满意的。",
	   "311、我小的时候，有一段时间我干过小偷小摸的事。",
	   "312、我不喜欢有人在我身旁。",
	   "313、有人不将自己的贵重物品保管好因而引起别人偷窃，这种人和小偷一样应受责备。",
	   "314、偶尔我会想到一些坏得说不出口的事。",
	   "315、我深信生活对我是残酷的。",
	   "316、我想差不多每个人，都会为了避免麻烦说点假话。",
	   "317、我比大多数人更敏感。",
	   "318、我的日常生活中，充满着使我感兴趣的事情。",
	   "319、大多数人，都是内心不愿意挺身而出去帮助别人的。",
	   "320、我的梦有好些是关于性方面的事。",
	   "321、我很容易感到不知所措。",
	   "322、我为金钱和事业忧虑。",
	   "323、我曾经有过很特别，很奇怪的体验。",
	   "324、我从来没有爱上过任何人。",
	   "325、我家里有些人所做的事，使我吃惊。",
	   "326、有时我会哭一阵、笑一阵，连自己也不能控制。",
	   "327、我的母亲或父亲时常要我服从他。甚至我认为是不合理的。",
	   "328、我发现我很难把注意力集中到一件工作上。",
	   "329、我几乎从不做梦。",
	   "330、我从来没有瘫痪过。",
	   "331、假如不是有人和我作对，我一定会有更大的成就。",
	   "332、即使我没有感冒，我有时也会不出声音或声音改变。",
	   "333、似乎没有人能了解我。",
	   "334、有时我会闻到奇怪的气味。",
	   "335、我不能专心于一件事情上。",
	   "336、我很容易对人感到不耐烦。",
	   "337、我几乎整天都在为某件事或某个人而焦虑。",
	   "338、我所操心的事，远远超过了我所应该操心的。",
	   "339、大部分时间，我觉得我还是死了的好。",
	   "340、有时我会兴奋得难以入睡。",
	   "341、有时我的听觉太灵敏了，反而使我感到烦恼。",
	   "342、别人对我所说的话，我立刻就忘记了。",
	   "343、哪怕是琐碎的小事，我也再三考虑后才去做。",
	   "344、有时为了避免和某些人相遇，我会绕道而行。",
	   "345、我常常感到好像一切都不是真的。",
	   "346、我有一个习惯，喜欢点数一些不重要的东西，象路上的电线杆等等。",
	   "347、我没有真正想伤害我的仇人。",
	   "348、我提防那些对我过分亲近的人。",
	   "349、我有一些奇怪和特别的念头。",
	   "350、在我独处的时候，我听到奇怪的声音。",
	   "351、当我必须短期离家出门的时候，我会感到心神不定。",
	   "352、我怕一些东西或人，虽然我明知他们是不会伤害我的。",
	   "353、如果屋子里已经有人聚在一起谈话。这时要我一个人进去，我是一点也不怕的。",
	   "354、我害怕使用刀子或任何尖利的东西。",
	   "355、有时我喜欢折磨我所爱的人。",
	   "356、我似乎比别人更难于集中注意力。",
	   "357、有好几次我放弃正在做的事，因为我感到自己的能力太差了。",
	   "358、我脑子里出现一些坏的常常是可怕的字眼，却又无法摆脱它们。",
	   "359、有时一些无关紧要的念头缠着我，使我好多天都感到不安。",
	   "360、几乎每天都有使我害怕的事情发生。",
	   "361、我总是将事情看得严重些。",
	   "362、我比大多数人更敏感。",
	   "363、有时我喜欢受到我心爱的人的折磨。",
	   "364、有人用侮辱性的和下流的话议论我。",
	   "365、我呆在屋里总感到不安。",
	   "366、即使和人们在一起，我仍经常感到孤单。",
	   "367、我并不特别害羞拘谨。",
	   "368、有时我的头脑似乎比平时迟钝。",
	   "369、在社交场合，我多半是一个人坐着，或者只跟令一个人坐在一起，而不到人群里去。",
	   "370、人们常使我失望。",
	   "371、我很喜欢参加舞会。",
	   "372、有时我常感到困难重重，无法克服。",
	   "373、我常想：“我要能再成为一个孩子就好了。”",
	   "374、如果给我机会，我一定能做些对世界大有益处的事。",
	   "375、我时常遇见一些所谓的专家，他们并不比我高明。",
	   "376、当我听说我所熟悉的人成功了，我就觉得自己失败了。",
	   "377、如果有机会，我一定能成为一个人民的好领袖。",
	   "378、下流的故事使我感到不好意思。",
	   "379、一般来说人们要求别人尊重他们的权利比较多，而他们却很少尊重别人的权利。",
	   "380、我总想把好的故事记住，讲给别人听。",
	   "381、我喜欢搞输赢不大的赌博。",
	   "382、为了可以和人们在一起，我喜欢社交活动。",
	   "383、我喜欢人多热闹的场合。",
	   "384、当我和一群活泼的朋友在一起的时候，我的烦恼就消失了。",
	   "385、当人们说我的班组的闲话时，我从来不参与。",
	   "386、只要我开始做一件事，就很难放下，哪怕是暂时的。",
	   "387、我的小便不困难，也不难控制。",
	   "388、我常发现别人妒忌我的好主意，因为他们没能先想到。",
	   "389、只要有可能，我就避开人群。",
	   "390、我不怕见生人。",
	   "391、记得我曾经为了不想做某件事而装过病。",
	   "392、在火车和公共汽车上，我常跟陌生人交谈。",
	   "393、当事情不顺利的时候，我就想立即放弃。",
	   "394、我不愿意让人家知道我对于事物的态度。",
	   "395、有些时间，我感到劲头十足，以至一连好几天都不需要睡觉。",
	   "396、在人群中，如果叫我带头发言，或对我所熟悉的事情发表意见，我并不感到不好意思。",
	   "397、我喜欢聚会和社交活动。",
	   "398、面对困难或危险的时候，我总退缩不前。",
	   "399、我原来想做的事，假若别人认为不值得做，我很容易放弃。",
	   "400、我不怕火。",
	   "401、我不怕水。",
	   "402、我常常是仔细考虑之后才做出决定。",
	   "403、生活在这个丰富多彩的时代里是多么美好。",
	   "404、当我想纠正别人的错误和帮助他们的时候，我的好意常被误解。",
	   "405、我吞咽没有困难。",
	   "406、我有时回避见人，因为我怕我会做出或讲出一些事后令我懊悔的事。",
	   "407、我通常很镇静，不容易激动。",
	   "408、我不轻易流露自己的感情，以至于人家得罪了我，他自己还不知道。",
	   "409、有时我因为承担的事情太多，以至精疲力竭。",
	   "410、我当然乐于以其人之道还治其人之身。",
	   "411、宗教不使我烦恼。",
	   "412、我生病或受伤的时候，不怕找医生。",
	   "413、我有罪，应受重罚。",
	   "414、我把失望的事看得太重，以至于总忘不了。",
	   "415、我很不喜欢匆匆忙忙地赶工作。",
	   "416、虽然我明知自己能把事做好，但是我也怕别人看着我做。",
	   "417、在排队的时候如果有人插到我的前面去，我会感到恼火而指责他。",
	   "418、有时我觉得自己一无是处。",
	   "419、小时候我时常逃学。",
	   "420、我曾经有过很不寻常的宗教体验。",
	   "421、我家里有人很神经过敏。",
	   "422、我因为家里有的人所从事的职业而感到不好意思。",
	   "423、我很喜欢（或者喜欢过）钓鱼。",
	   "424、我几乎总感到肚子饿。",
	   "425、我经常做梦。",
	   "426、我有时只好用不客气的态度去对付那些粗鲁或令人厌恶的人。",
	   "427、我倾向于对各种不同爱好发生兴趣，而不愿意长期坚持其中的某一种。",
	   "428、我喜欢阅读报纸的社论。",
	   "429、我喜欢听主题严肃的演说。",
	   "430、我易受异性的吸引。",
	   "431、我相当担心那引起可能发生的不幸。",
	   "432、我有着坚定的政治见解。",
	   "433、我曾经有过想像的同伴。",
	   "434、我希望能成为一个摩托车运动员。",
	   "435、我通常喜欢和妇女一起工作。",
	   "436、我觉得只有一种宗教是真的。",
	   "437、只要你不是真正的犯法，在法律的漏洞中取巧是可以的。",
	   "438、有些人讨厌极了，我会因为他们自食其果而暗中高兴。",
	   "439、要我等待我就紧张。",
	   "440、当我兴高采烈的时候，见到别人忧郁消沉就使我大为扫兴。",
	   "441、我喜欢身材高的女人。",
	   "442、有些时期我因忧虑而失眠。",
	   "443、假若别人认为我对某些事的做法不妥当的话。我很容易放弃。",
	   "444、我不想去纠正那些发表愚昧无知见解的人。",
	   "445、我年轻（童年）的时候，喜欢热闹。",
	   "446、警察通常是诚实的。",
	   "447、当别人反对我意见时，我会不惜一切去说服他。",
	   "448、在街上、车上、或在商店里，如果有人注视我，我会觉得不安。",
	   "449、我不喜欢看到妇女吸烟。",
	   "450、我很少有忧郁的毛病。",
	   "451、如果有人对我所熟悉的事情发表愚蠢和无知的意见。我总是设法纠正他。",
	   "452、我喜欢开别人的玩笑。",
	   "453、我小时候对是否参加团伙无所谓。",
	   "454、独自住在深山或老林的小木屋里，我也会觉得快乐。",
	   "455、许多人都说我是急性子。",
	   "456、如果一个人触犯了一条他认为不合理的法律，他是不应该受到惩罚的。",
	   "457、我认为一个人决不应该喝酒。",
	   "458、小时候和我关心密切的人（父亲、继父等）对我十分严厉。",
	   "459、我有几种坏习惯，已经根深蒂固，难于改正。",
	   "460、我只适量的喝一点酒（或者一点也不喝）。",
	   "461、我希望我能避免因为破口伤人而引起的烦恼。",
	   "462、我觉得不能把自己的一切都告诉别人。",
	   "463、我从前喜欢玩“跳房子”（或跳皮筋）的游戏。",
	   "464、我从来没有见过幻象。",
	   "465、对于我的终身职业，我已经几次改变过主意。",
	   "466、除了医生的嘱咐，我从来不服用任何药物或安眠药。",
	   "467、我时常默记一些无关重要的号码（加汽车牌照等）",
	   "468、我时常因为自己爱发脾气和抱怨而感到懊悔。",
	   "469、闪电是我害怕的东西中的一种。",
	   "470、有关性方面的事使我厌恶。",
	   "471、在学校中老师对我的品行评定总是很不好。",
	   "472、火对我有一种吸引力。",
	   "473、我喜欢让别人猜测我下一步的行动。",
	   "474、我的小便次数不比别人多。",
	   "475、万不得已的时候，我只吐露一些无损于自己的那部分真情。",
	   "476、我是神派来的特使。",
	   "477、假如我和几个朋友有着同样的过错，我宁可一人承担而不愿连累别人。",
	   "478、我还从没有因为家里人惹了事而自己感到特别紧张。",
	   "479、人与人间的相互欺骗是我所知道的唯一奇迹。",
	   "480、我常常怕黑暗。",
	   "481、我害怕一个人单独呆在黑暗中。",
	   "482、我的计划看起总是困难重重，使我不得不一一放弃。",
	   "483、神创造奇迹。",
	   "484、有些缺点，我只好承认并设法加以控制，但无法消除。",
	   "485、一个男人和一个女人相处的时候，他通常想到的是关于她的性方面的事。",
	   "486、我从来没有发现我尿中有血。",
	   "487、当我试图使别人不犯错误而做的事被人误解的时候，我往往感到十分难过。",
	   "488、每星期我祈祷几次。",
	   "489、我同情那些不能摆脱苦恼和忧愁的人。",
	   "490、我每星期念几次经。",
	   "491、对认为世界上只有一种宗教是真的那些人，我感到不耐烦。",
	   "492、我想起地震就害怕。",
	   "493、我喜欢那种需要集中注意力的工作，而不喜欢省心（不费劲）的工作。",
	   "494、我怕自己被关在小房间里或紧闭的小地方。",
	   "495、对那些我想帮助他们改正或提高的人，我总是坦率地交底。",
	   "496、我从来没有过将一件东西看成两件（复视现象）。",
	   "497、我喜欢探险小说。",
	   "498、坦率永远是一件好事。",
	   "499、我必须承认我有时会不合理地担心一些无关紧要的事情。",
	   "500、我很乐意百分之百地接受一个好意见。",
	   "501、我一向总是靠自己解决问题，而不是找人教我怎样做。",
	   "502、风暴使我惊慌。",
	   "503、我不经常对别人的行动表示强烈的赞成或反对。",
	   "504、我不想隐瞒我对一个人的坏印象或同情，以至他不知道我对他的看法。",
	   "505、我认为“不肯拉车的马应该受到鞭打”。",
	   "506、我是个神经高度紧张的人。",
	   "507、我经常遇到一些领导人，他们把功劳归于自己，把错误推给下级。",
	   "508、我相信我的嗅觉和别人一样好。",
	   "509、因为我太拘谨，所以有时我难以坚持自己的正确意见。",
	   "510、肮脏使我害怕或恶心。",
	   "511、我有一种不愿告诉别人的梦幻生活。",
	   "512、我不喜欢洗澡。",
	   "513、我认为为别人谋求幸福比为自己争取自由更为伟大。",
	   "514、我喜欢有男子气的女人。",
	   "515、我们家总是不愁吃不愁穿。",
	   "516、我家里有些人脾气急躁。",
	   "517、我无论什么事情都做不好。",
	   "518、我经常感到惭愧，因为我对事情想的和做的不一样。",
	   "519、我的性器官有点毛病。",
	   "520、我的原则是坚持强烈维护自己的意见。",
	   "521、我常常向别人请教。",
	   "522、我不害怕蜘蛛。",
	   "523、我从来不脸红。",
	   "524、我不怕从门把上传染上疾病。",
	   "525、有些动物使我神经紧张。",
	   "526、我的前途似乎没有希望。",
	   "527、我家里人和近亲们相处得很好。",
	   "528、我并不容易比人脸红。",
	   "529、我喜欢穿高档的衣服。",
	   "530、我常常会担心自己会脸红。",
	   "531、即使我以为自己对某件事已经打定了主意，别人也很容易使我变卦。",
	   "532、我和别人一样能够经受同量的痛苦。",
	   "533、我并不因为常常打嗝（呃迸）而觉得很烦恼。",
	   "534、有好几次都是我一个人坚持到底，最后才放弃了所做的事。",
	   "535、我几乎整天感到口干。",
	   "536、只要有人催我，我就生气。",
	   "537、我想去深山野林中打老虎。",
	   "538、我想我会喜欢裁缝的工作。",
	   "539、我不怕老鼠。",
	   "540、我的面部从来没有麻痹过。",
	   "541、我的皮肤似乎对触觉特别敏感。",
	   "542、我从来没有过象柏油一样的黑粪便。",
	   "543、每星期我总有几次觉得好像有可怕的事情要发生。",
	   "544、我大部分时间都感到疲倦。",
	   "545、有时我一再做同样的梦。",
	   "546、我喜欢阅读有关历史的书籍。",
	   "547、未来是变化无常的，一个人很难做出认真的安排。",
	   "548、如果可以避免，我决不去看色情的表演。",
	   "549、许多时候，即使一切顺利，我对任何事情都觉得无所谓。",
	   "550、我喜欢修理门锁。",
	   "551、有时我可以肯定别人知道我在想什么。",
	   "552、我喜欢阅读有关科学的书籍。",
	   "553、我害怕单独呆在空旷的地方。",
	   "554、假如我是个画家，我喜欢画小孩子。",
	   "555、有时我觉得我就要垮了。",
	   "556、我很注意我的衣着式样。",
	   "557、我喜欢当一个私人秘书。",
	   "558、许多人都因为有过不良的性行为而感到惭愧。",
	   "559、我经常在半夜里受到惊吓。",
	   "560、我经常因为记不清把东西放在哪里而感到苦恼。",
	   "561、我很喜欢骑马。",
	   "562、小时候，我最依恋和钦佩的是一个女人（母亲、姐姐、姑、婶、姨，等等）。",
	   "563、我喜欢探险小说胜过爱情小说。",
	   "564、我不轻易生气。",
	   "565、当我站在高处的时候，我就很想往下跳。",
	   "566、我喜欢电影里的爱情镜头。"
	   
	};
	
	//指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语：本量表由许多涉及生活实际的陈述句组成，请仔细阅读每一句话的内容，并与您自己的实际情况相对照，作出“是”或“否”的判断。\n"+
		"\t\t\t\t作答时请您根据第一印象作出选择，不要对题目反复思索。每一陈述句的选择答案没有正确与错误，唯一标准就看它的内容与您的实际情况是否基本符合。\n"+
		"\t\t\t\t当实在无法回答时，可以点“下一页”跳过。但是，未回答的题目太多可能会导致测验结果无效（尽量不要超过10个）。"
  };
	
	public QuestionnaireModelOfMMPI() {
		super(QuestionnaireCodeEnum.MMPI);
		

		// 问题总数
		super.questionTotal = kQuestionArray.length;

		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(成人)
		final RespondentsInformationOfAdults answerDataSource = new RespondentsInformationOfAdults(getQuestionnaireCodeEnum());
		answerDataSource.setAgeRange(16, 200);
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
			if (i == 73) {
				final Object questionDataSourceOfMale = new SingleAnswerPageDataSource("74、我总希望我是个女的。", null);
				final Object questionDataSourceOfFemale = new SingleAnswerPageDataSource("74、我从不因为我是女的而遗憾。", null);
				questionFrameModel.setQuestionDataSourceWithMaleAndFemale(questionDataSourceOfMale, questionDataSourceOfFemale);
			} else {
				// 74 题之外不区分男女
				final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], null);
				questionFrameModel.setQuestionDataSource(questionDataSource);
			}
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
		return true;
	}

	@Override
	public boolean isCanEndTestInAdvance() {
		// MMPI 当处于 400-566题时, 就可以提前结束测试
		Object frameModel = getFrameModel(getCurrentFrameIndex());
		if (frameModel == null || !(frameModel instanceof QuestionFramePageModel)) {
			// 异常
			return true;
		}

		QuestionFramePageModel questionFramePageModel = (QuestionFramePageModel) frameModel;
		if (questionFramePageModel.getQuestionIndex() >= 399) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 跟据问题索引 和 答案数据源 得到该问题答案值
	 * 
	 * @param answerDataSource
	 * @param questionIndex
	 * @return
	 */
	public byte getAnswerValueResult(final Object answerDataSource, final int questionIndex) {
		if (!(answerDataSource instanceof Integer)) {
			// 用户答案非法, 或者用户没有选择答案, 该题就要略过
			return 2;// 2 : 指放弃
		}

		// 答案选项
		final Integer answerOption = (Integer) answerDataSource;
		
		// 答案选项是从 0 开始的.
		int answerValue = 0;
		if (answerOption == 0) {
			answerValue = 1;
		} else {
			answerValue = 0;
		}

		return (byte) answerValue;
	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfBoolean(answerDataSource, 2);
	}
	
	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfMMPI(option, this);
	}
}
