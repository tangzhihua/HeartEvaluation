package cn.skyduck.questionnaire.questionnaire.SCCS;

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
public class QuestionnaireModelOfSCCS extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 	 "1、我周围的人往往觉得我对自己的看法有些矛盾",
     "2、有时我会对自己在某些地方的表现不满意",
     "3、每当遇到困难，我总是首先分析造成困难的原因",
     "4、我很难恰当表达我对别人的情感反应",
     "5、我对很多事情都有自己的观点，但我并不要求别人也与我一样",
     "6、我一旦形成对事物的看法，就不会再改变",
     "7、我经常对自己的行为不满意",
     "8、尽管有时候做一些不愿意的事，但我基本上是按自己意愿办事的",
     "9、一件事好是好，不好是不好，没有什么可含糊的",
     "10、如果我在某件事上不顺利，我就往往会怀疑自己的能力",
     "11、我至少有几个知心朋友",
     "12、我觉得我所做的很多事情都是不该做的",
     "13、不论别人怎么说，我的观点决不改变",
     "14、别人常常会误解我对他们好意",
     "15、很多情况下我不得不对自己的能力表示怀疑",
     "16、我朋友中有些是与我截然不同的人，这并不影响我们的关系",
     "17、与朋友交往过多容易暴露自己的隐私",
     "18、我很了解自己对周围人的情感",
     "19、我觉得自己目前的处境与我的要求相距太远",
     "20、我很少去想自己所做的事情是否应该",
     "21、我所遇到的很多问题都无法自己解决",
     "22、我很清楚自己是什么样的人",
     "23、我很能自如地表达自己所要表达的意思",
     "24、如果有足够的证据，我也可以改变自己的观点",
     "25、我很少考虑自己是一个什么样的人",
     "26、把心理话告诉别人不仅得不到帮助，还可能招致麻烦",
     "27、在遇到问题时，我总觉得别人都离我很远",
     "28、我觉得很难发挥出自己应有的水平",
     "29、我很担心自己的所作所为会引起别人的误解",
     "30、如果我发现自己某些方面表现不佳，总希望尽快弥补",
     "31、每个人都在忙自己的事，很难与他们沟通",
     "32、我认为能力再强的人也可能遇上难题",
     "33、我经常感到自己是孤独无援的",
     "34、一旦遇到麻烦，无论怎么做都无济于事",
     "35、我总能清楚地了解自己的感受",
 };
 
 	//备选答案
 	private static final String[] kAnswerArray = new String[]{
	    "完全不符合 ",
	    "比较不符合",
	    "不确定",
	    "比较符合",
	    "完全符合"
 	};

 	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语:下面是一些个人对自己看法的陈述。填答时，请您看清每句话的意思，然后选择一个答案，以代表该句话与您现在对自己的看法相符合的程度。每个人对自己的看法都有其独特性，因此答案是没有对错的，您只要如实回答就行"
		};
	
	public QuestionnaireModelOfSCCS() {
		super(QuestionnaireCodeEnum.SCCS);
		

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
