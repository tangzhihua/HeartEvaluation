package cn.skyduck.questionnaire.questionnaire.EMBU;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.embu.EMBUPartOneFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.embu.EMBUQuestionFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfEMBU extends FullQuestionnaireModel implements Serializable {

	// 单亲家庭问卷调整(可能隐藏父亲相关问题, 可能隐藏母亲相关问题)
	private SingleParentFamiliesQuestionnaireAdjust singleParentFamiliesQuestionnaireAdjust = new SingleParentFamiliesQuestionnaireAdjust();
	
  public SingleParentFamiliesQuestionnaireAdjust getSingleParentFamiliesQuestionnaireAdjust() {
		return singleParentFamiliesQuestionnaireAdjust;
	}

	// 问题
  private static final String[] kQuestionArray = new String[]{
    "1．我觉得我父母干涉我做的任何一件事。",
    "2．我能通过父母的言谈、表情感受他（她）很喜欢我。",
    "3．与我的兄弟姐妹相比，父母更宠爱我。",
    "4．我能感受到父母对我的喜爱。",
    "5．即使是很小的过错，父母也惩罚我。",
    "6．父母总试图潜移默化的影响我，使我成为出类拔萃的人。",
    "7．我觉得父母允许我在某些方面有独到之处。",
    "8．父母能让我得到其他兄弟姐妹得不到的东西。",
    "9．父母对我的惩罚是公平的。",
    "10．我觉得父母对我很严厉。",
    "11．父母总是左右我该穿什么衣服活该打扮成什么样子。",
    "12．父母不允许我做一些其他孩子可以做的事情，因为他们害怕我会出事。",
    "13．在我小时候，父母曾经当着别人的面打我或训我。",
    "14．父母总是很关心我晚上干什么。",
    "15．当遇到不顺心的事时，我能感到父母在尽量鼓励我，使我得到一些发展。",
    "16．父母总是过分担心我的健康。",
    "17．父母对我惩罚往往超过我应受的程度。",
    "18．如果我在家里不听吩咐，父母就会恼火。",
    "19．如果我做错了什么事，父母总是以一种伤心的样子使我有一种犯罪感或负疚感。",
    "20．我觉得父母难以接近。",
    "21．父母曾在别人面前唠叨一些我说过的话或作过的事，这时我感到很难堪。",
    "22．我觉得父母更喜欢我，而不是我的兄弟姐妹。",
    "23．在满足我需要的东西，父母是很小气的。",
    "24．父母常常很在乎我取得分数。",
    "25．如果我面临一项困难的任务，我能感到来自父母的支持。",
    "26．我在家里往往被当作“替罪羊”或“害群之马”。",
    "27．父母总是挑剔我所喜欢的朋友。",
    "28．父母总是以为它们的不快是由我引起的",
    "29．父母总试图鼓励我，使我成为佼佼者。",
    "30．父母总向我表示他们是爱我的。",
    "31．父母对我很信任且允许我独自完成某些事。",
    "32．我觉父母很尊重我的观点。",
    "33．我觉得父母很愿意跟我在一起。",
    "34．我觉得父母对我很小气、很吝啬。",
    "35．父母总是向我说类似这样的话“如果你这样做我会很伤心”。",
    "36．父母要求我回到家里必须得向他们说明我在做的事情。",
    "37．我觉得父母在尽量使我的青春更有意义和丰富多彩。",
    "38．父母经常向我表述类似这样的话“这就是我们为你整日操劳而得到的报答吗？”",
    "39．父母常以不能娇惯我为借口不满足我的要求。",
    "40．如果不按父母所期望的去做，就会使我良心上感到不安。",
    "41．我觉得父母对我的学习成绩，体育活动或类似的事情有较高的要求。",
    "42．当我感到伤心的时候可以从父母那儿得到安慰。",
    "43．父母曾无缘无故的惩罚我。",
    "44．父母允许我做一些我的朋友们做的事情。",
    "45．父母经常对我说他们不喜欢我在家的表现。",
    "46．每当我吃饭时，父母就劝我或强迫我再多吃一些。",
    "47．父母经常当着别人的面批评我既懒惰，又无用。",
    "48．父母常常关注我交什么样的朋友。",
    "49．如果发什么事情，我常常是兄弟姐妹中唯一受责备的一个。",
    "50．父母能让我顺其自然的发展。",
    "51．父母经常对我粗俗无礼。",
    "52．有时甚至为一点儿鸡毛蒜皮的小事，父母也会严厉的惩罚我。",
    "53．父母曾无缘无故的打我。",
    "54．父母通常会参与我的业余爱好活动。",
    "55．我经常挨父母的打。",
    "56．父母常常允许我到我喜欢去的地方，而他们又不会过分担心。",
    "57．父母对我该做什么、不该做什么都有严格的限制而决不让步。",
    "58．父母以一种使我难堪的方式对待我。",
    "59．我觉得父母对我可能出事的担心是夸大的、过分的。",
    "60．我觉得与父母之间存在一种温暖、体贴和亲热的感觉。",
    "61．父母能容忍我与他们有不同的见解。",
    "62．父母常常在我不知道原因的情况下对我大发脾气。",
    "63．当我做的事情取得成功时，我觉得父母为我很自豪。",
    "64．与我的兄弟姐妹相比，父母常常偏爱我。",
    "65．有时即使错误在我，父母也把责任归咎于兄弟姐妹。",
    "66．父母经常拥抱我。"
  };
 

 	// 指导语
  private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语：在回答问卷之前，请您认真阅读下列的指导语:\n" + 
    "\t\t\t\t父母的教养方式对子女的发展和成长是至关重要的。让您确切回忆小时候父母对您说教的每一细节是很困难的。但我们每个人都对我们成长过程中父母对待我们的方式有深刻印象。回答这一问卷，就是请您努力回想小时后留下的这些印象。\n" + 
    "\t\t\t\t问卷有很多题目，每个题目均有四个选项。请您分别在最适合您父亲和您母亲的选项上面选择。您父亲和母亲对您的教养方式可能是相同的，也可能是不同的。请您实事求是的分别回答。\n" + 
    "\t\t\t\t如果您是独生子女，没有兄弟姐妹，相关的题目可以不答。如果您幼小的时候父母不全，可以只回答父亲或母亲一栏（在相应的位置点“不适合选择”）。"
  };
	
	public QuestionnaireModelOfEMBU() {
		super(QuestionnaireCodeEnum.EMBU);
		

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
		
		
		// 第一部分 
		framePageFragmentFactory = new EMBUPartOneFragmentFactoryMethod();
		for (int i = 0; i < 4; i++) {
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			switch (i) {
			case 0:
				questionFrameModel.setFragmentStyle(EMBUPartOneFragmentFactoryMethod.FragmentStyleEnum.page_1);
				break;
			case 1:
				questionFrameModel.setFragmentStyle(EMBUPartOneFragmentFactoryMethod.FragmentStyleEnum.page_2);
				break;
			case 2:
				questionFrameModel.setFragmentStyle(EMBUPartOneFragmentFactoryMethod.FragmentStyleEnum.page_3);
				break;
			case 3:
				questionFrameModel.setFragmentStyle(EMBUPartOneFragmentFactoryMethod.FragmentStyleEnum.page_4);
				break;
			default:
				break;
			}
			
			super.frameModelList.add(questionFrameModel);
		}

		// 第二部分
		int questionIndex = 0;
		framePageFragmentFactory = new EMBUQuestionFragmentFactoryMethod();
		for (int i = 0; i < kQuestionArray.length; i++) {
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(kQuestionArray[i]);
			super.frameModelList.add(questionFrameModel);
		}
	}

	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfEMBU(option, this);
	}
}
