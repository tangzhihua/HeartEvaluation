package cn.skyduck.questionnaire.questionnaire.ITS;

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
public class QuestionnaireModelOfITS extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 	 "1．在我们的社会里虚伪的现象越来越多了。 ",
     "2．与陌生人打交道时，你最好小心，除非他们拿出可以证明其值得信任的依据。",
     "3．除非我们吸引更多的人进政界，这个国家的前途十分黯淡。",
     "4．阻止多数人触犯法律的是恐惧、社会廉耻或惩罚而不是良心。",
     "5．考试时老师不到监考可能会导致更多的人作弊。",
     "6．通常父母在遵守诺言方面是可以信赖的。",
     "7．联合国永远也不会成为维持世界和平的有效力量。",
     "8．法院是我们都能受到公正对待的场所。",
     "9．如果得知公众听到和看到的新闻有多少已被扭曲，多数人会感到震惊的。",
     "10．不管人们怎样表白，最好还是认为多数人主要关心其自身幸福。",
     "11．尽管在报纸、收音机和电视中均可看到新闻，但我们很难得到关于公共事件的客观报道。",
     "12．未来似乎很有希望。",
     "13．如果真正了解到国际上正在发生的政治事件，那么公众有理由比现在更加担心。",
     "14．多数获选官员在竞选中的承诺是诚恳的。",
     "15．许多重大的全国性体育比赛均受到某种形式的操纵和利用。",
     "16．多数专家有关其知识局限性的表白是可信的。",
     "17．多数父母关于实施惩罚的威胁是可信的。",
     "18．多数人如果说出自己的打算就一定会去实现。",
     "19．在这个竞争的年代里，如果不保持警惕别人就可能占你的便宜。",
     "20．多数理想主义者是诚恳的并按照他们自己所宣扬的信条行事。",
     "21．多数推销人员在描述他们的产品时是诚实的。",
     "22．多数学生即使在有把握不会被发现时也不作弊",
     "23．多数维修人员即使认为你不懂其专业知识也不会多收费。",
     "24．对保险公司的控告有相当一部分是假的。",
     "25．多数人诚实地回答民意测验的问题。"
 };
 
 	//备选答案
 	private static final String[] kAnswerArray = new String[]{
	    "完全同意",
	    "部分同意",
	    "中间态度",
	    "部分不同意",
	    "完全不同意 "
 	};

 	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语:使用以下标准表明你对下列每一陈述同意或不同意的程度。"
		};
	
	public QuestionnaireModelOfITS() {
		super(QuestionnaireCodeEnum.ITS);
		

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
