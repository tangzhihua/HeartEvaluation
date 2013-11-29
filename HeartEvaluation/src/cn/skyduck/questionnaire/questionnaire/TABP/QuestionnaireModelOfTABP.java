package cn.skyduck.questionnaire.questionnaire.TABP;

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
public class QuestionnaireModelOfTABP extends FullQuestionnaireModel implements Serializable{

 //问题
 private static final String[] kQuestionArray = new String[]{
	 "1、我觉得自己是一个无忧无虑、悠闲自在的人",
   "2、即使没有什么要紧的事，我走路也快",
   "3、我经常感到应该做的事太多，有压力",
   "4、我自己决定的事，别人很难让我改变主意",
   "5、有些人和事常常使我十分恼火",
   "6、我急需买东西但又要排长队时，我宁愿不买",
   "7、有些工作我根本安排不过来，只能临时挤时间去做",
   "8、上班或赴约会时，我从来不迟到",
   "9、当我正在做事，谁要是打扰我，不管有意无意，我总是感到恼火",
   "10、我总看不惯那些慢条斯理、不紧不慢的人",
   "11、我常常忙得透不过气来，因为该做的事情太多了",
   "12、即使跟别人合作，我也总想单独完成一些更重要的部分",
   "13、有时我真想骂人",
   "14、我做事总是喜欢慢慢来，而且思前想后，拿不定主意",
   "15、排队买东西，要是有人加塞，我就忍不住要指责他或出来干涉",
   "16、我总是力图说服别人同意我的观点",
   "17、有时连我自己都觉得，我所操心的事远远超过我应该操心的范围",
   "18、无论做什么事，即使比别人差，我也无所谓",
   "19、做什么事我也不着急，着急也没有用，不着急也误不了事",
   "20、我从来没想过要按自己的想法办事",
   "21、每天的事情都使我精神十分紧张",
   "22、就是去玩，如逛公园等，我也总是先看完，等着同来的人",
   "23、我常常不能宽容别人的缺点和毛病",
   "24、在我认识的人里，个个我都喜欢",
   "25、听到别人发表不正确的见解，我总想立即就去纠正他",
   "26、无论做什么事，我都比别人快一些",
   "27、人们认为我是一个干脆、利落、高效率的人",
   "28、我总觉得我有能力把一切事情办好",
   "29、聊天时，我也总是急于说出自己的想法，甚至打断别人的话",
   "30、人们认为我是个安静、沉着、有耐性的人",
   "31、我觉得在我认识的人之中值得我信任和佩服的人实在不多",
   "32、对未来我有许多想法和打算，并总想都能尽快实现",
   "33、有时我也会说人家的闲话",
   "34、尽管时间很宽裕，我吃饭也快",
   "35、听人讲话或报告如讲得不好，我就非常着急，总想还不如来讲哩！",
   "36、即使有人欺侮了我，我也不在乎",
   "37、我有时会把今天该做的事拖到明天去做",
   "38、当别人对我无礼时，我对他也不客气",
   "39、有人对我或我的工作吹毛求疵时，很容易挫伤我的积极性",
   "40、我常常感到时间已经晚了，可一看表还早呢",
   "41、我觉得我是一个对人对事都非常敏感的人",
   "42、我做事总是匆匆忙忙的，力图用最少的时间办尽量多的事情",
   "43、如果犯有错误，不管大小，我全都主动承认",
   "44、坐公共汽车时，尽管车开得快我也常常感到车开得太慢",
   "45、无论做什么事，即使看着别人做不好，我也不想拿来替他做",
   "46、我常常为工作没做完，一天又过去了而感到忧虑",
   "47、很多事情如果由我来负责，情况要比现在好得多",
   "48、有时我会想到一些说不出口的坏念头",
   "49、即使领导我的人能力差、水平低，不怎么样，我也能服从和合作",
   "50、必须等待什么的时候，我总是心急如焚，缺乏耐心",
   "51、我常常感到自己能力不够，所以在做事遇到不顺利时就想放弃不干了",
   "52、我每天都看电视，同时也看电影，不然心里就不舒服",
   "53、别人托我办的事，只要答应了，我从不拖延",
   "54、人们都说我很有耐性，干什么事都不着急",
   "55、外出乘车、船或跟人约定时间办事时，我很少迟到，如对方耽误我就恼火",
   "56、偶尔我也会说一两句假话",
   "57、许多事本来可以大家分担，可我喜欢一个人去干",
   "58、我觉得别人对我的话理解太慢，甚至理解不了我的意思似的",
   "59、我是一个性子暴躁的人",
   "60、我常常容易看到别人的短处而忽视别人的长处"    
 };
 

 	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：请根据您过去的情况回答下列问题。凡是符合您的情况的请选择“是”；凡是不符合您的情况的请选择“否”。每个问题必须回答，答案无所谓对与不对、好与不好。请尽快回答，不要在每道题目上太多思索。回答时不要考虑“应该怎样”，只回答您平时“是怎样的”就行了。" };
	
	public QuestionnaireModelOfTABP() {
		super(QuestionnaireCodeEnum.TABP);
		

		// 问题总数
		super.questionTotal = kQuestionArray.length;

		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(成人)
		final RespondentsInformationOfAdults answerDataSource = new RespondentsInformationOfAdults(getQuestionnaireCodeEnum());
		// 居住区域(属于 南方人还是北方人)
		answerDataSource.addExpandOption(RespondentsInformationOfAdults.ExpandOptionEnum.residentialArea);
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
			final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], null);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.true_or_false);
			super.frameModelList.add(questionFrameModel);
		}

	}
	
	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfBoolean(answerDataSource, 2);
	}
	
	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfTABP(option, this);
	}
}
