package cn.skyduck.questionnaire.questionnaire.BDI;

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
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfBDI extends FullQuestionnaireModel implements Serializable{ 
	private static final String[] kQuestionArray = new String[] {
		   "第一题",
		   "第二题",
		   "第三题",
		   "第四题",
		   "第五题",
		   "第六题",
		   "第七题",
		   "第八题",
		   "第九题",
		   "第十题 ",
		   "第十一题",
		   "第十二题",
		   "第十三题" 
		   };

	// 备选答案
	private static final String[][] kAnswerArray = new String[][] {
			//1
			{ "1、我不感到忧郁 " ,
			  "2、我感到忧郁或沮丧 " ,
			  "3、我整天忧郁，无法摆脱 " ,
			  "4、我十分忧郁，已经忍受不住" },
			//2
			{ "1、我对未来并不悲观失望 " ,
			  "2、我感到前途不太乐观 " ,
			  "3、我感到我对前途不抱希望 " ,
			  "4、我感到今后毫无希望，不可能有所好转" },
			//3
			{ "1、我并无失败的感觉 " ,
			  "2、我觉得和大多数人相比我是失败的 " ,
			  "3、回顾我的一生，我觉得那是一连串的失败 " ,
			  "4、我觉得我是个彻底失败的人" },
			//4
			{ "1、我并不觉得有什么不满意 " ,
			  "2、我觉得我不能象平时那样享受生活 " ,
			  "3、任何事情都不能使我感到满意一些 " ,
			  "4、我对所有的事情都不满意"},
			//5
			{ "1、我没有特殊的内疚感 " ,
			  "2、我有时感到内疚或觉得自己没价值 " ,
			  "3、我感到非常内疚 " ,
			  "4、我觉得自己非常坏，一钱不值"},
			//6
			{ "1、我没有对自己感到失望 " ,
			  "2、我对自己感到失望 " ,
			  "3、我讨厌自己 " ,
			  "4、我憎恨自己"},
			//7
			{ "1、我没有要伤害自己的想法 " ,
			  "2、我感到还是死掉的好 " ,
			  "3、我考虑过自杀 " ,
			  "4、如果有机会，我还会杀了自己" },
			//8
			{ "1、我没失去和他人交往的兴趣 " ,
			  "2、和平时相比，我和他人交往的兴趣有所减退 " ,
			  "3、我已失去大部分和人交往的兴趣，我对他们没有感情 " ,
			  "4、我对他人全无兴趣，也完全不理睬别人"},
			//9
			{ "1、我能象平时一样做出决断 " ,
			  "2、我尝试避免做决定 " ,
			  "3、对我而言，做出决断十分困难 " ,
			  "4、我无法做出任何决断" },
			//10
			{ "1、我觉得我的形象一点也不比过去糟 " ,
			  "2、我担心我看起来老了，不吸引人了 " ,
			  "3、我觉得我的外表肯定变了，变得不具吸引力 " ,
			  "4、我感到我的形象丑陋且讨人厌 " },
			//11
			{ "1、我能象平时那样工作 " ,
			  "2、我做事时，要花额外的努力才能开始 " ,
			  "3、我必须努力强迫自己，我方能干事 " ,
			  "4、我完全不能做事情"},
			//12
			{ "1、和以往相比，我并不容易疲倦 " ,
			  "2、我比过去容易觉得疲乏 " ,
			  "3、我做任何事都感到疲乏 " ,
			  "4、我太易疲乏了，不能干任何事" },
			//13
			{ "1、我的胃口不比过去差 " ,
			  "2、我的胃口没有过去那样好 " ,
			  "3、现在我的胃口比过去差多了 " ,
			  "4、我一点食欲都没有"},
		};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：以下问卷由13道题组成，每一道题均有4个选项。请您仔细阅读每道题的所有选项，并从中选出一个最能反映你今天或此刻情况的选项。" };
	
	public QuestionnaireModelOfBDI() {
		super(QuestionnaireCodeEnum.BDI);

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

		for (int i = 0; i < kQuestionArray.length; i++) {
			List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
	}
}
