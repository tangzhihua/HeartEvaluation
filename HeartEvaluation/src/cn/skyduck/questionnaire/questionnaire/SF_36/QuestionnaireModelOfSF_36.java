package cn.skyduck.questionnaire.questionnaire.SF_36;

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
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfSF_36 extends FullQuestionnaireModel implements Serializable{

	private static final String[] kQuestionArray = new String[]{
        //第1题        1
        "1．总体来讲，您的健康状况是：",
        //第2题       2
        "2．跟一年前相比，您觉得您现在的健康状况是：",
        //第一部分 需要过渡界面
        //第3题      3
        "3.1 重体力活动（如跑步、举重物、激烈运动等）",
        //第4题       4
        "3.2 适度活动（如移桌子、扫地、做操等）",
        //第5题          5
        "3.3 手提日杂用品(如买菜、购物等) ",
        //第6题          6
        "3.4 上几层楼梯 ",
        //第7题      7
        "3.5 上一层楼梯",
        //第8题        8
        "3.6 弯腰、曲膝、下蹲",
        //第9题        9
        "3.7 步行1500米左右的路程",
        //第10题           10
        "3.8 步行800米左右的路程",
        //第11题            11
        "3.9 步行约100米的路程",
        //第12题         12
        "3.10自己洗澡、穿衣",
        //第二部分 需要过渡界面
        //第13题           13
        "4.1 减少了工作或其他活动的时间",
        //第14题           14
        "4.2 本来想要做的事情只能完成一部分",
        //第15题          15
        "4.3 想要做的工作或活动的种类受到限制",
        //第16题            16
        "4.4 完成工作或其他活动有困难(比如，需要额外的努力)",
        //第三部分   需要过渡界面          
        //第17题             17
        "5.1 减少了工作或其他活动的时间",
        //第18题      18
        "5.2本来想要做的事情只能完成一部分",
        //第19题         19
        "5.3做工作或者其它活动能够不如平时仔细",
        //第20题         20
        "6．在过去四个星期里，您的身体健康或情绪不好在多大程度上影响了您与家人、朋友、邻居或集体的正常社交活动？",
        //第21题          21
        "7．在过去四个星期里，您有身体上的疼痛吗？",
        //第22题           22
        "8．在过去四个星期里，身体上的疼痛影响您的正常工作吗(包括上班工作和家务活动)？",
        //第四部分 需要过渡界面
        //第23题          23
        "9.1 您觉得生活充实吗? ",
        //第24题          24
        "9.2 您是一个精神紧张的人吗?",
        //第25题          25
        "9.3 您感到垂头丧气,什么事都不能 使您振作起来吗? ",
        //第26题          26
        "9.4 您觉得平静吗",
        //第27题          27
        "9.5 您精力充沛吗?",
        //第28题          28
        "9.6 您的情绪低落吗? ",
        //第29题          29
        "9.7 您觉得筋疲力尽吗?",
        //第30题          30
        "9.8 您是个快乐的人吗? ",
        //第31题          31
        "9.9 您感觉疲劳吗? ",
        //第32题          32
        "9.10您的健康限制了您的社交活动(如走亲访友)吗?	",
        //第五部分 需要过渡界面
        //第33题          33
        "10.1 我好像比人容易生病",
        //第34题          34
        "10.2 我跟我认识的人一样健康",
        //第35题          35
        "10.3 我认为我的健康状况在变坏",
        //第36题          36
        "10.4 我的健康状况非常好",
	};
 
	// 答案
	private static final String[][] kAnswerArray = new String[][] {
			// 1
			{" ①非常好",
		   " ②很好",
	     " ③好",
       " ④一般",
   	   " ⑤差"},
			// 2
			{" ①比一年前好多了",
       " ②比一年前好一些",
       " ③和一年前差不多",
       " ④比一年前差一些",
       " ⑤比一年前差多了"},
			// 3
			{" ①有很多限制",
       " ②有一点限制",
       " ③根本没限制"},
			// 4
			{" ①有很多限制",
       " ②有一点限制",
       " ③根本没限制"},
			// 5
			{" ①有很多限制",
       " ②有一点限制",
       " ③根本没限制"},
			// 6
			{" ①有很多限制",
       " ②有一点限制",
       " ③根本没限制" },
			// 7
			{" ①有很多限制",
       " ②有一点限制",
       " ③根本没限制"},
			// 8
			{" ①有很多限制",
       " ②有一点限制",
       " ③根本没限制" },
			// 9
			{" ①有很多限制",
       " ②有一点限制",
       " ③根本没限制"},
			// 10
			{" ①有很多限制",
       " ②有一点限制",
       " ③根本没限制"},
			// 11
			{" ①有很多限制",
       " ②有一点限制",
       " ③根本没限制"},
			// 12
			{" ①有很多限制",
       " ②有一点限制",
       " ③根本没限制"},
			// 13
			{" ①有",
       " ②没有"},
			// 14
			{" ①有",
       " ②没有" },
			// 15
			{" ①有",
       " ②没有" },
			// 16
			{" ①有",
       " ②没有" },
			// 17
			{" ①有",
       " ②没有" },
			// 18
			{" ①有",
       " ②没有" },
			// 19
			{" ①有",
       " ②没有" },
			// 20
			{" ①根本没有影响",
       " ②很少有影响",
       " ③有中度影响",
       " ④有较大影响",
       " ⑤有极大影响"},
			// 21
			{" ①根本没有疼痛",
       " ②有很轻微疼痛",
       " ③有轻微疼痛",
       " ④有中度疼痛",
       " ⑤有严重疼痛",
       " ⑥有很严重的疼痛" },
		   // 22
			{" ①根本没有影响",
       " ②有一点影响",
       " ③有中度影响",
       " ④有较大影响",
       " ⑤有极大影响"},
			// 23
			{" ①所有的时间",
       " ②大部分时间",
       " ③比较多的时间",
       " ④一部分时间",
       " ⑤小部分时间",
       " ⑥没有此感觉"},
			// 24
			{" ①所有的时间",
       " ②大部分时间",
       " ③比较多的时间",
       " ④一部分时间",
       " ⑤小部分时间",
       " ⑥没有此感觉"},
      // 25
  		{" ①所有的时间",
       " ②大部分时间",
       " ③比较多的时间",
       " ④一部分时间",
       " ⑤小部分时间",
       " ⑥没有此感觉"},
      // 26
			{" ①所有的时间",
       " ②大部分时间",
       " ③比较多的时间",
       " ④一部分时间",
       " ⑤小部分时间",
       " ⑥没有此感觉"},
      // 27
  		{" ①所有的时间",
       " ②大部分时间",
       " ③比较多的时间",
       " ④一部分时间",
       " ⑤小部分时间",
       " ⑥没有此感觉"},
      // 28
			{" ①所有的时间",
       " ②大部分时间",
       " ③比较多的时间",
       " ④一部分时间",
       " ⑤小部分时间",
       " ⑥没有此感觉"},
      // 29
  		{" ①所有的时间",
       " ②大部分时间",
       " ③比较多的时间",
       " ④一部分时间",
       " ⑤小部分时间",
       " ⑥没有此感觉"},
      // 30
			{" ①所有的时间",
       " ②大部分时间",
       " ③比较多的时间",
       " ④一部分时间",
       " ⑤小部分时间",
       " ⑥没有此感觉"},
      // 31
  		{" ①所有的时间",
       " ②大部分时间",
       " ③比较多的时间",
       " ④一部分时间",
       " ⑤小部分时间",
       " ⑥没有此感觉"},
      // 32
			{" ①所有的时间",
       " ②大部分时间",
       " ③比较多的时间",
       " ④一部分时间",
       " ⑤小部分时间",
       " ⑥没有此感觉"},
      // 33
  		{" ①绝对正确",
       " ②大部分正确",
       " ③不能肯定",
       " ④大部分错误",
       " ⑤绝对错误"},
      // 34
			{" ①绝对正确",
       " ②大部分正确",
       " ③不能肯定",
       " ④大部分错误",
       " ⑤绝对错误"},
      // 35
  		{" ①绝对正确",
       " ②大部分正确",
       " ③不能肯定",
       " ④大部分错误",
       " ⑤绝对错误"},
      // 36
			{" ①绝对正确",
       " ②大部分正确",
       " ③不能肯定",
       " ④大部分错误",
       " ⑤绝对错误"}
	};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：下面的问题是询问您对自己健康状况的看法、您的感觉如何以及您进行日常活动的能力如何。如果您没有把握如何回答问题，尽量选择一个最好的答案。" };

	public QuestionnaireModelOfSF_36() {
		super(QuestionnaireCodeEnum.SF_36);

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

		// 第1,2问题
		for (int i = 0; i < 2; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}

		// 第三大题的过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage1 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel1 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage1);
		transitionPageFrameModel1.setQuestionDataSource("\t\t\t\t3．健康和日常活动 \n 以下的问题都与日常活动有关。您的健康状况是否限制了这些活动？如果有限制，程度如何？\n\n");
		super.frameModelList.add(transitionPageFrameModel1);

		// 第三大题 问题 3-12
		for (int i = 2; i < 12; i++) {
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
		transitionPageFrameModel3.setQuestionDataSource("\t\t\t\t4．在过去四个星期里，您的工作和日常活动有没有因为身体健康的原因而出现以下这些问题？\n\n");
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
		transitionPageFrameModel4.setQuestionDataSource("\t\t\t\t5．在过去四个星期里，您的工作和日常活动有没有因为情绪(如感到消沉或者忧虑)而出现以下问题？\n\n");
		super.frameModelList.add(transitionPageFrameModel4);

		// 第四部分 问题
		for (int i = 16; i < 22; i++) {
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
		transitionPageFrameModel5.setQuestionDataSource("\t\t\t\t9．有以下这些问题有关过去一个月里您的感觉如何以及您的情况如何，请选出最接近您的答案。\n\n");
		super.frameModelList.add(transitionPageFrameModel5);

		// 第五部分 问题
		for (int i = 22; i < 32; i++) {//32
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
		// 第五部分过渡界面
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactoryOfTransitionPage6 = new TransitionPageOneFragmentFactoryMethod();
		final NonQuestionFramePageModel transitionPageFrameModel6 = new NonQuestionFramePageModel(framePageFragmentFactoryOfTransitionPage6);
		transitionPageFrameModel6.setQuestionDataSource("\t\t\t\t10．总的健康情况 \n 请对下面的每一句话，选出最符合您情况的答案\n\n");
		super.frameModelList.add(transitionPageFrameModel6);

		// 第五部分 问题
		for (int i = 32; i < kQuestionArray.length; i++) {
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
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
