package cn.skyduck.questionnaire.questionnaire.ENRICH;

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
public class QuestionnaireModelOfENRICH extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 "1、夫妻双方都喜爱同一类的社会活动",
   "2、向配偶表达我真实的感受是非常容易的",
   "3、对我所受到的有关宗教信仰的教育，我很难全盘接受",
   "4、为了尽早结束争吵，我常立即让步",
   "5、在我们家里,父亲和孩子呆在一起所花的时间不够",
   "6、当夫妻间出现矛盾时,我的配偶常沉默不语",
   "7、亲友中一些人使我们的婚姻变得紧张",
   "8、我的配偶过于挑剔或经常持否定的观点",
   "9、我完全满意配偶对我的感情",
   "10、我和配偶就如何采取最佳方法解决矛盾常常意见不一 ",
   "11、我认为夫妻双方对宗教应有相同的理解",
   "12、我认为妇女主要应呆在家里",
   "13、有时,我对配偶的脾气很在意",
   "14、我不喜欢配偶的性格和个人习惯",
   "15、为了使性关系保持乐趣,我们尝试找一些新的方法",
   "16、有时,我希望配偶别乱花钱",
   "17、我的配偶似乎缺少时间与精力与我一起娱乐",
   "18、我宁愿做别的任何事情,也不愿独自呆一个晚上",
   "19、我非常满意夫妻双方在婚姻中承担的责任",
   "20、我和配偶对怎样花钱总是意见一致",
   "21、我很满意我们对抚养子女的责任分工",
   "22、共同的信仰有助于我们的关系发展",
   "23、如果夫妻双方都有工作,丈夫应该与妻子承担同样多的家务劳动",
   "24、有时,我对配偶显得不愉快和孤僻感到担心",
   "25、我担心配偶可能在性方面对我不感兴趣",
   "26、我们很难在经济安排上作出决定",
   "27、我们为亲友花费的时间很恰当",
   "28、对配偶兴趣或爱好过少,我很在意",
   "29、除非经济上需要,我的妻子不应外出工作",
   "30、我配偶抽烟和/或饮酒成问题",
   "31、与配偶参加社交活动,我很少感到压力",
   "32、我不满意夫妻间的交流,我的配偶并不理解我",
   "33、对于我们家怎样和在何处度假，我总是觉得满意",
   "34、我们夫妻间完全相互理解",
   "35、在管教子女方面,夫妻意见一致",
   "36、我非常满意我们作决定和解决冲突的方式",
   "37、有时,我的配偶不依赖我,不总是人云亦云",
   "38、对于家庭应储蓄多少钱的决定,我感到满意",
   "39、当讨论某一问题时,我通常感到配偶是理解我的",
   "40、我的配偶有时发表一些贬低我的意见",
   "41、与配偶谈论性问题,对我来说是很容易和轻松的",
   "42、我的配偶对我的每一次情绪变化都完全理解并有相同的感受",
   "43、在我们的婚姻中,妻子应更加顺从丈夫的愿望",
   "44、当我们与别人共处时,有时我为配偶的行为感到不安",
   "45、我们都知道我们所欠的债务,而且它不成问题",
   "46、我的宗教信仰是影响我们婚姻的一个重要部分",
   "47、有时,我担心配偶会有寻求婚外性关系的想法",
   "48、我认为配偶与他/她的家里过于密切或受其影响太大",
   "49、子女似乎是我们婚姻中矛盾的一个主要来源",
   "50、我们对所需子女的数量意见一致",
   "51、我们按我们的经济实力有规律地花钱",
   "52、我不满意我们的经济地位和决定经济事务的方法",
   "53、我非常满意我们的业余活动和夫妻一起度过的时间",
   "54、有时,我不敢找配偶要我需要的东西",
   "55、即使妻子在外工作,也应该负担管理家务的责任",
   "56、夫妻双方在与宗教信仰有关的活动中意见不一",
   "57、与我的或配偶家的亲戚在一起,我感到不愉快",
   "58、当我遇到困难时,我总是告诉配偶",
   "59、我的配偶对子女的关注超过对我们的婚姻,这使我不舒服",
   "60、我觉得我们的假期与旅游过得很好",
   "61、我们家丈夫是一家之主",
   "62、对我来说,我们的性关系是满意与完美的",
   "63、有时,我的配偶太固执",
   "64、我们的婚姻是非常成功的",
   "65、与配偶一起祈祷,对我很重要",
   "66、我希望配偶更愿意与我分享他/她的感受",
   "67、有了孩子,使我们的婚姻关系更密切",
   "68、我的配偶喜欢我所有的朋友",
   "69、我不愿对配偶表现出很温柔,因为它经常被误解为是一种性的表示",
   "70、我觉得我们的婚姻关系缺少某些东西",
   "71、有时在一些不重要的问题上我们常产生严重的争执",
   "72、我感到夫妻双方没有花费足够的时间一起度过业余空暇",
   "73、有时,我很难相信配偶告诉我的每一件事",
   "74、我尽量避免与配偶发生冲突",
   "75、对于我们来说,丈夫的职业较妻子的职业更重要",
   "76、我觉得我们的婚姻受到宗教观念影响",
   "77、我们的经济已变得紧张,如赊帐过多",
   "78、配偶经常拖拖拉拉,使我很烦恼",
   "79、有时,我觉得夫妻之间的争执没完没了,从得不到解决",
   "80、如果家里有很小的子女，妻子不应外出工作",
   "81、我经常不把我的感受告诉配偶，因为他/她应该体会得到",
   "82、对于我们夫妻之间怎样表达情感与性有关的事，我很满意",
   "83、当夫妻间出现意见不一时，我们开诚布公地交流感受和决定怎样来解决它",
   "84、除非与配偶在一起，否则我很少开玩笑",
   "85、我们很注重决定怎样把钱花在最重要的事情上",
   "86、有时我的配偶与朋友在一起的时间太多",
   "87、我和配偶在对子女进行宗教教育方面有不同的意见",
   "88、对于承担做父母的责任分工上，我不满意",
   "89、爱配偶，使我更深地体会到：上帝是慈爱的",
   "90、我觉得双方的父母过高地期望得到我们的关心和帮助",
   "91、我非常满意夫妻之间相互谈话的方式",
   "92、我觉得我们的父母给我们的婚姻造成问题",
   "93、我很烦恼，没有配偶的允许我不能花钱",
   "94、自从有了孩子，夫妻间很少有时间单独在一起",
   "95、对于配偶的喜怒无常，有时我感到束手无策",
   "96、我经常感到配偶没有认真对待我们的分岐",
   "97、在我们家里，丈夫在大多数重要的事情上应有最后的决定权",
   "98、因为担心配偶发脾气，所以我不总是把心里的一些烦恼告诉他/她",
   "99、我不满意我们与双方父母、朋友的关系",
   "100、我和配偶对我所受的宗教方面的教育意见不一",
   "101、我从不后悔与我父母的关系，哪怕是一瞬间",
   "102、应该为子女做多少事，是我们发生冲突的一个原因",
   "103、我确实很高兴与配偶所有的朋友来往",
   "104、因为我们的宗教信仰，我和配偶觉得很亲密",
   "105、妻子在重要问题上应该相信与接受丈夫的判断",
   "106、有时，我很在意配偶的性兴趣与我不一致",
   "107、我很满意关于家庭计划和生育子女数的决定",
   "108、我不在意配偶与异性朋友在一起",
   "109、我说话时，配偶总是认真听着",
   "110、我很在意谁管钱",
   "111、配偶应用不公平的方式同意或拒绝性生活，使我很烦恼",
   "112、当我们争吵时，我通常不去想这是我的过错",
   "113、对于我们的宗教信仰与价值观，我觉得很好",
   "114、我和配偶在一起和分开度过的业余时间分配很公平",
   "115、有时，我认为配偶过于盛气凌人",
   "116、我认为任何生活在一起的配偶都没有我们夫妻和睦",
   "117、有时我觉得对配偶感觉不到爱和感情",
   "118、有时配偶做一些使我不愉快的事",
   "119、如果配偶有何过错，我也没意识到",
   "120、即使世界上每一个异性都愿与我结婚，我也不能做出比现在婚姻更好的选择",
   "121、我们夫妻比世界上任何人都相互适应得好",
   "122、关于配偶的每一件新鲜事都使我高兴",
   "123、我们的关系比它应有的状况更好",
   "124、当我和配偶在一起时，我觉得任何人都不可能比我们幸福"
 };
 
 	//备选答案
 	private static final String[] kAnswerArray = new String[]{
 		"A 确实是这样",
 		"B 可能是这样",
 		"C 不同意也不反对",
 		"D 可能不是这样",
 		"E 确实不是这样"
 	};

 	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：以下的问题是了解您的婚姻状态的，它可以发现您婚姻中可能存在和需要指导的问题，有助于得到专家的解决，希望您能如实填写，不用征求他人的意见，独立完成。请注意，题目中的“我们”均是指您和您的配偶，现在开始吧！" };
	
	public QuestionnaireModelOfENRICH() {
		super(QuestionnaireCodeEnum.ENRICH);
		

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
		final List<String> anwserList = Arrays.asList(kAnswerArray);

		for (int i = 0; i < kQuestionArray.length; i++) {
			final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}

	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfIntegerType1(answerDataSource);
	}
}
