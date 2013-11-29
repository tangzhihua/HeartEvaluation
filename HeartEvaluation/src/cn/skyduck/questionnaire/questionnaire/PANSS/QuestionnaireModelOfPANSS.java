package cn.skyduck.questionnaire.questionnaire.PANSS;

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
public class QuestionnaireModelOfPANSS extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 		 "P1、妄想（Delusions），指无事实根据，与现实不符，特异的信念，" +
       "依据会谈中思维自然的表达，及由基层保健工作者或家属提供的其思维对" +
       "社会交往和行为造成的影响评定。",
       "P2、概念紊乱（联想散漫，Conceptual disorganization)\n，指" +
       "思维过程紊乱，其特征为思维的目的性、连贯性破坏，如赘述、离题、" +
       "联想散漫、不连贯、显著的不合逻辑或思维阻隔。依据会谈中对认知语" +
       "言表达过程的观察评定。 ",
       "P3、幻觉行为(Hallucinatory behavior)，指语言表达或行为表" +
       "明其知觉并非通过客观刺激产生，可以听觉、视觉\n、嗅觉或躯体感觉的" +
       "形式出现。依据会谈中语言表达和躯体表现评定，也可由基层保健工作" +
       "者或家属提供。",
       "P4、兴奋（Excitement），指活动过度，表现在动作行为加速，对刺" +
       "激的反应增强。高度警觉或过度的情绪不稳。依据会谈中动作行为的表" +
       "现评定，也可由基层保健工作者或家属提供。",
       "P5、夸大(Grandiosity)，指夸张己见及不现实的优势信念，包括一" +
       "些妄想，如非凡的能力、财富、知识、名望、\n权力和道德正义。依据会" +
       "谈中思维的自然表达，及由基层保健工作者或家属提供的这些想法对其" +
       "行为的影响评定。",
       "P6、猜疑／被害(Suspiciousness /persecution)，指不现实或" +
       "夸大的被害观念，表现在防卫、不信任态度，多疑的高度戒备，或是认" +
       "为他人对其有伤害的非常明显的妄想\n。依据会谈中思维的自然表达，及" +
       "由基层保健工作者或家属提供的这些想法对病人行为的影响评定。",
       "P7、敌对性(Hostility)，指语言或非语言表达出愤怒和怨恨，包括" +
       "讥讽、被动攻击行为、辱骂和袭击。依据会谈中观察其人际行为，及由" +
       "基层保健工作者或家属提供情况评定。",
       "N1、情感迟钝（Blunted affect)，指情绪反应减弱，以面部表情，" +
       "感觉调节及体态语言的减少为特征。依据会谈中观察情感基调和情绪反" +
       "应的躯体表现评定。",
       "N2、情绪退缩(Emotional Withdrawal)，指对生活事件缺乏兴趣、" +
       "参与和情感投人。依据基层保健工作者或家属提供情况，及会谈中观察" +
       "到的人际行为评定。 ",
       "N3、（情感）交流障碍（Poor rapport），指缺乏人际交往中的感情投" +
       "人、交谈时的坦率及亲密感、兴趣或会谈者的投人，表现在人际关系疏远" +
       "及语言和非语言交流的减少。依据会谈中的人际行为评定。 ",
       "N4、被动／淡摸社交退缩(Passive /apathetic social withdrawal)，" +
       "指因被动、淡漠、缺乏精力或意志力使社会交往的兴趣和主动性下降，这" +
       "导致人际投人的减少及对日常活动的忽视。依据基层保健工作者或家属提" +
       "供的病人社会行为的情况评定。",
       "N5、抽象思维困难(Difficulty in abstract thinking)，指抽象" +
       "一象征性思维模式受损，表现在分类、概括及解决间题时超越具体自我中" +
       "心的过程出现困难。依据会谈中回答相似性问题和谚语解释类间题，及使" +
       "用具体抽象模式的情况。",
       "N6、交谈缺乏自发性和流畅性(Lack of spontaneity and flow of" +
       " conversation)，指交谈的正常流畅性下降，伴有淡漠，缺乏意志，防" +
       "卫或认知缺损，表现在交流过程的流畅性和创造性下降。依据会谈中观察" +
       "认知语言过程评定。",
       "N7、刻板思维(Stereotyped thinking)，指思维的流畅性\n、自发性和" +
       "灵活性下降，表现在刻板、重复、或思维内容空洞。依据会谈中观察认知" +
       "语言过程评定。",
       "G1、关注身体健康(Somatic concern)，指诉说躯体不适或坚信有躯体" +
       "疾病或机能失常，其范围从模糊的病感到身患重病的明确的妄想。依据会" +
       "谈中表达的思维内容评定。",
       "G2、焦虑(Anxiety)，指主观体验到神经紧张，担优，恐惧或坐立不安，" +
       "其范围从对现在或将来的过分关心到惊恐的感觉。依据会谈中的语言表达" +
       "和相应的躯体表现评定。",
       "G3、自罪感(Guilt feelings),指为过去真实或想象的过失而后悔或自" +
       "责的感觉。依据会谈中语言表达的罪恶观念及其对态度和思维的影响评定。",
       "G4、紧张(Tension)，指因恐惧、焦虑和激越而表现明显的躯体症状，如" +
       "僵直、震颤、大量出汗和坐立不安。依据会谈中语言表达的焦虑及紧张的躯" +
       "体表现的严重程度评定",
       "G5、装相和作态(Mannerisms and posturing)，指不自然的动作或姿" +
       "势，以笨拙、夸张、紊乱或古怪表现为特征\n。依据会谈中观察躯体表现评定，" +
       "也可由基层保健工作者或家属提供。",
       "G6、抑郁(Depression)，指悲伤、沮丧、无助和悲观厌世的感觉。依据会" +
       "谈中抑郁心境的语言表达，及其对病人态度和行为的影响评定。一也可由基" +
       "层保健工作者或家属提供。",
       "G7、动作迟缓(Motor retardation)指动作的能动性减退，\n表现在动作和" +
       "言语的减慢或减少，对刺激的反应减退及身体（肌肉）的张力降低。依据会谈" +
       "中的表现评定，也可由基层保健工作者或家属提供。",
       "G8、不合作(Uncooperativeness)，指主动拒绝按照重要人物的意愿行事，" +
       "包括会谈者、医院工作人员或家属，可能伴有不信任、防御、顽固、否定、抵" +
       "制权威、敌对或好斗。依据会谈中观察到的人际行动评定，也可由基层保健工" +
       "作者或家属提供。",
       "G9、不寻常思维内容(Unusual thought content)，指奇怪、幻想式或荒" +
       "诞的念头，其范围从离谱或不典型到歪曲的、不合逻辑的和明显荒谬的想法。" +
       "依据会谈中思维内容的表达评定。",
       "G10、定向障碍(Disorientation)，指与环境联系的意识丧失，包括人物、" +
       "地点和时间，可能由意识混乱或戒断引起\n，依据会谈中对定向问题的反应评定。",
       "G11、注意障碍（Poor attention)，指替觉集中障碍，表现为注意力不集" +
       "中，受内外刺激而分散注意力，以及驾御、\n维持或转移注意力到新刺激时存在" +
       "困难。依据会谈的表现评定。",
       "G12、判断和自知力缺乏(Lack of judgment and insight)\n，指对自身" +
       "精神状况和生活处境的认识或理解力受损，表现在不能认识过去或现在的精神" +
       "疾病或症状，否认需要在精神科住院治疗，所做决定的特点是对后果错误的预" +
       "期，\n及不切实际的短期和长期计划。依据会谈中思维内容的表达评定。",
       "G13、意志障碍（Disturbance of voliton），指意志的产生\n，维护及对" +
       "思维、行为、动作、语言的控制障碍。依据会谈中思维内容和行为表现评定。",
       "G14、冲动控制障碍(Poor impulse control)，指对内在冲动反应的调节" +
       "和控制障碍，导致不顾后果的、突然的、无法调节的、武断的或误导的紧张和" +
       "情绪的宣泄。依据会谈中观察到的行为及由基层保健工作者或家属提供的信息" +
       "评定。",
       "G15、先占观念(Preoccupation)，指专注于内在产生的思维和感觉。因内" +
       "向体验而损害现实定向和适应性行为。依据会谈中对人际行为的观察评定。",
       "G16、主动回避社交（Active social avoidance），指社交减少伴有不" +
       "当的恐惧、敌对或不信任。依据基层保健工作者或家属提供的社交功能状况评" +
       "定。",
       "S1、愤怒主观状态为指向他人的不悦和激惹。",
       "S2、延迟满足困难强人所难，坚持立即满足其要求，当需要或渴望被延迟满足是，明显不悦。",
       "S3、情感不稳情绪表达不稳定、波动、不恰当、和/或控制不良。"
 };
 
 // 备选答案
 private static final String[][] kAnswerArray = new String[][]{
	 // P1
	 {"1、无症状或定义不适用于该病人。",
     "2、症状可疑，可能是正常范围的上限。",
     "3、轻度，存在一或两个不明确、不具体、并非顽固坚持的妄想，妄\n\t\t" +
     "想不妨碍思考，社会交往或行为。",
     "4、中度，存在一个多变的，未完全成型的不稳定的妄想组合，或几\n\t\t" +
     "个完全成型的妄想，偶而妨碍思考、社会交往或行为。",
     "5、偏重，存在许多完全成型的且顽固坚持的妄想，偶而妨碍思考、\n\t\t" +
     "社会交往或行为。",
     "6、重度，存在一系列稳定的，具体的妄想，可能系统化，顽固坚\n\t\t" +
     "持，且明显妨碍思考、社会交往和行为。",
     "7、极重度，存在一系列高度系统化或数量众多的稳定的妄想，并支\n\t\t" +
     "配病人生活的主要方面，以至常引起不恰当和不负责任的行动，\n\t\t" +
     "甚至可能因此危及病人或他人的安全。"},
	 // P2
	 {"1、无症状或定义不适用于该病人。",
       "2、症状可疑，可能是正常范围的上限。",
       "3、轻度，思维显赘述，离题或逻辑障碍。思维的目的性有些障碍，\n\t\t" +
       "在压力下显得有些联想散漫。",
       "4、中度，当交谈短暂和有序时尚可集中思维，当交谈较复杂或有轻\n\t\t" +
       "微压力时就变得散漫或离题。",
       "5、偏重，普遍存在构思困难，在无压力时也经常显得离题、不连贯\n\t\t" +
       "或联想散漫。",
       "6、重度，思维严重出轨及自相矛盾，导致明显的离题和思维中断，\n\t\t" +
       "几乎是持续出现。",
       "7、极重度，思维中断到支离破碎的程度，明显的联想散漫，完全无\n\t\t" +
     "法交谈，如“语词杂拌”或缄默。"},
	 // P3 
	 {"1、无症状或定义不适用于该病人。",
       "2、症状可疑，可能是正常范围的上限。",
       "3、轻度，一或两种清晰但不经常出现的幻觉，或若干模糊异常的知\n\t\t，" +
       "觉不引起思维或行为的扭曲。",
       "4、中度，幻觉频繁出现但并不持续，病人的思维和行为仅受轻微的\n\t\t" +
       "影响。",
       "5、偏重，幻觉频繁出现，可能涉及一种以上感觉系统，导致思维扭\n\t\t" +
       "曲和／或妨碍行为，病人可能对这些体验给予妄想性的解释并出\n\t\t" +
       "现情绪反应，偶也出现语言反应。",
       "6、重度，幻觉几乎持续存在，以致严重妨碍思维和行为，病人对这\n\t\t" +
       "些幻觉信以为真，频繁的情绪和语言反应导致功能障碍。",
       "7、极重度，病人对幻觉几乎全神贯注，幻觉实质上支配病人的思维\n\t\t" +
       "和行为，幻觉被赋予固定的妄想性解释，并引起语言和行为反应\n\t\t" +
    "，包括对命令性幻听的服从。"},
	 // P4
	 {"1、无症状或定义不适用于该病人。",
      "2、症状可疑，可能是正常范围的上限。",
      "3、轻度，会谈中轻度的激越，警觉增高，或轻度的激动，但没有明\n\t\t" +
      "显兴奋或情绪不稳的发作，讲话有轻微的紧迫感。",
      "4、中度，会谈中表现出明显的激越或激动，影响语言和一般动作或\n\t\t" +
      "偶有短暂爆发。 ",
      "5、偏重，观察到明显的活动过度或频繁的动作行为爆发，造成病人\n\t\t" +
      "在任何时候都难以保持坐姿超过数分钟。",
      "6、重度，会谈中明显兴奋，注意力受限，在某种程度上影响个人功\n\t\t" +
      "能，诸如饮食和睡眠。",
      "7、极重度，明显的兴奋严重妨碍饮食和睡眠，无法进行人际交往，\n\t\t" +
      "言语和动作行为的加速可能导致言语不连贯和衰竭。"},
	 // P5
	 {"1、无症状或定义不适用于该病人。",
        "2、症状可疑，可能是正常范围的上限。",
        "3、轻度，显出有些自大或自夸，但没有明确的夸大妄想。",
        "4、中度，明确地和不切实际地感到自己比他人优越，有一些尚未定\n\t\t" +
        "型的关于特殊地位或能力的妄想，但并未照此行动。",
        "5、偏重，表达出有明确的关于非凡能力、地位或权力的妄想，影响\n\t\t" +
        "病人的态度，但不影响行为。",
        "6、重度，表达出有明确的优势妄想，涉及到一个以上的项目（财富\n\t\t" +
        "、知识、名望等），显著影响人际交往，并可能付诸行动。",
        "7、极重度，思维，人际交往和行为受多重妄想的支配，这些妄想包\n\t\t" +
        "括惊人的能力、财富、知识、名望、权力和／或道德水平，可能\n\t\t" +
        "具有古怪的性质。"},
	 // P6
	 {"1、无症状或定义不适用于该病人。",
          "2、症状可疑，可能是正常范围的上限。",
          "3、轻度，表现出防卫或甚至公开的不信任态度，但思维，交往和行\n\t\t" +
          "为很少受人影响。",
          "4、中度，明确地显示出不信任感，并妨碍会谈和／或行为，但没有\n\t\t" +
          "被害妄想的证据，或者，可能存在结构松散的被害妄想，但这些\n\t\t" +
          "似乎不影响病人的态度或人际关系。",
          "5、偏重，病人表现出明显的不信任感，以致严重影响人际关系，或\n\t\t" +
          "者还存在明确的被害妄想，对人际关系和行为造成一定程度的影\n\t\t响。",
          "6、重度，明确的泛化的被害妄想，可能是系统化的，显著地妨碍人\n\t\t" +
          "际关系。 ",
          "7、极重度，一整套系统性被害妄想支配病人的思维，社会交往和行\n\t\t为。"},
	 // P7
	 {"1、无症状或定义不适用于该病人。",
            "2、症状可疑，可能是正常范围的上限。",
            "3、轻度，间接地或有限地表示愤怒，如讥讽，不尊敬，表达敌意及\n\t\t" +
            "偶而易激怒。",
            "4、中度，存在明显敌对态度，经常表现易激惹及直接表达愤怒和怨\n\t\t恨。",
            "5、偏重，病人高度易激惹，偶而有辱骂或威胁。",
            "6、重度，不合作辱骂或威胁显著地影响会谈，且严重影响社会交\n\t\t" +
            "往，病人可能具有暴力和破坏性，但没有对他人进行人身攻击。",
            "7、极重度，明显的愤怒造成极度不合作，无法与他人交往或对他人\n\t\t" +
     "进行人身攻击。"},
	 // N1
	 {"1、无症状或定义不适用于该病人。",
       "2、症状可疑，可能是正常范围的上限。",
       "3、轻度，面部表情和体态语言似乎显得呆板、勉强、做作，或缺少\n\t\t" +
       "变化。",
       "4、中度，面部表情和体态语言的减少使病人看上去迟钝。",
       "5、偏重，情感总体上显得“平淡”，面部表情仅偶而有所变化，缺乏\n\t\t" +
       "体态语言。",
       "6、重度，大部分时间表现明显的情感平淡和缺乏情绪表达，可能存\n\t\t" +
       "在无法调控的极端的情感发泄，如兴奋、愤怒或不恰当的无法控\n\t\t" +
       "制的发笑。",
       "7、极重度，完全缺乏面部的表情和体态语言，病人似乎持续地显示\n\t\t" +
       "出木呐的表情或毫无表情。"},
	 // N2
	 {"1、无症状或定义不适用于该病人。",
         "2、症状可疑，可能是正常范围的上限。 ",
         "3、轻度，常缺乏主动性，偶而显得对周围事件缺乏兴趣。",
         "4、中度，病人总体上对环境和环境变化有情绪隔阂，但给予鼓励仍\n\t\t" +
         "可参与。",
         "5、偏重，病人对环境中的人和事件有明显的情绪疏远，抵抗任何参\n\t\t" +
         "与的努力，病人显得疏远、温顺、和漫无目的，但至少可进行短\n\t\t" +
         "暂的交谈，注意个人需求，有时需要帮助。",
         "6、重度，明显的缺乏兴趣和情绪投人，导致与他人只能进行有限的\n\t\t" +
         "交谈，常常忽略个人功能，因此病人需要协助和监督。",
         "7、极重度，极度的兴趣和情绪投人的缺乏导致病人几乎完全退缩，\n\t\t" +
         "无法交谈，并忽略个人需求。"},
	 // N3
	 { "1、无症状或定义不适用于该病人。",
           "2、症状可疑，可能是正常范围的上限。 ",
           "3、轻度，交谈以呆板、紧张或音调不自然为特征，可能缺乏情绪深\n\t\t" +
           "度或停留在非个人的、理智性的水平。",
           "4、中度，病人显出典型的冷淡，人际关系相当疏远、病人可能机械\n\t\t" +
           "地回答问题，表现不耐烦或表示无兴趣。",
           "5、偏重，明显的不投人并妨碍到会谈的词汇表达量，病人可能避开\n\t\t" +
           "眼神的接触或面部表情的交流。",
           "6、重度，病人显得高度冷漠，明显的人际疏远，回答问题敷衍，很\n\t\t" +
           "少有投人会谈的非语言迹象，常常避开眼神的接触和面部表情的\n\t\t交流。",
           "7、极重度，病人完全不投人会谈，显得完全冷摸，会谈中始终回避\n\t\t" +
           "语言和非语言交流。"},
	 // N4
	 {"1、无症状或定义不适用于该病人。",
             "2、症状可疑，可能是正常范围的上限。",
             "3、轻度，显示对社会活动偶有兴趣，但主动性较差，通常只有在他\n\t\t" +
             "人先主动表示时才会参与。",
             "4、中度，被动地参与大部分的社会活动，但以无兴趣或机械的方式\n\t\t" +
             "出现，倾向于退缩到不显眼的地方。",
             "5、偏重，仅被动参与少数社会活动，且显得毫无兴趣或主动性，通\n\t\t" +
             "常只花很少时间与他人相处。",
             "6、重度，趋于淡漠和孤立，极少参与社会活动，偶尔忽视个人需\n\t\t" +
             "求，很少有自发的社会接触。",
    "7、极重度，极度的淡漠，与世隔绝，忽视个人需求。"},
	 // N5
	 {"1、无症状或定义不适用于该病人。",
      "2、症状可疑，可能是正常范围的上限。 ",
      "3、轻度，对较难的谚语倾向于照字面或给予个人化的解释，对极抽\n\t\t" +
      "象和关联偏远的概念有些困难。",
      "4、中度，经常使用具体化的思维模式，对大多数谚语某些分类有困\n\t\t" +
      "难，倾向于被功能性方面和显著特征所迷惑。",
      "5、偏重，以具体化的思维模式为主，对大多数谚语和许多分类有困\n\t\t难。",
      "6、重度，无法领会任何谚语或比喻的抽象意义，仅能对最简单的相\n\t\t" +
      "似事例作公式化的分类，思维空洞贫乏，或固定在功能性方面、\n\t\t" +
      "显著特征和个人特质的解释。",
      "7、极重度，只会使用具体化的思维模式。显示对谚语、一般隐喻或\n\t\t" +
      "明喻及简单的分类无法理解，甚至不会用显著的和功能性的特征\n\t\t" +
      "作为分类的依据，本分级可适用于因显著认知功能缺损而无法与\n\t\t" +
      "主试者进行最低限度交流的情况。"},
	 // N6
	 {"1、无症状或定义不适用于该病人。",
        "2、症状可疑，可能是正常范围的上限。",
        "3、轻度，交谈显示很少有主动性，病人的回答简短且不加修饰，需\n\t\t" +
        "要会谈者给予直接的和引导性的问题。",
        "4、中度，交谈缺乏自然流畅，显得不顺畅或停顿，经常需要引导性\n\t\t" +
        "的问题以诱导出充分的反应和交谈的进程。",
        "5、偏重，病人表现明显的缺乏自发性及坦率，回答会谈者提问时仅\n\t\t" +
        "用一或两个简短的句子。",
        "6、重度，病人的反应仅局限于几个单字或短语，以回避或缩短交\n\t\t" +
        "谈（如“我不知道”，“我没空说”），使交谈发生严重困难，且毫无\n\t\t效果。",
        "7、极重度，语言的交流出最多局限于偶然的吃语，使交谈无法进行"},
	 // N7
	 {"1、无症状或定义不适用于该病人。",
          "2、症状可疑，可能是正常范围的上限。",
          "3、轻度，态度或信念有些僵化，病人可能拒绝考虑另一种见解，或\n\t\t" +
          "难以从一种观点改变成另一种观点。",
          "4、中度，交谈围绕着一个重复的主题，导致改变话题困难。",
          "5、偏重，思维刻板及重复，尽管会谈者努力，交谈仍仅局限于两三\n\t\t" +
          "个受限的主题。",
          "6、重度，无法控制地重复要求、声明、观点或问题，严重地妨碍交\n\t\t谈。",
          "7、极重度，思维、行为和交谈被不断重复的牢固的观点或有限的短\n\t\t" +
     "语所支配，导致病人的交流明显刻板、不恰当并受到限制。"},
	 // G1
	 {"1、无症状或定义不适用于该病人。",
       "2、症状可疑，可能是正常范围的上限。 ",
       "3、轻度，明显关心健康或身体问题，偶而会提出间题并希望得到保\n\t\t证。",
       "4、中度，主诉健康不佳或身体机能失常，但没有达到妄想的确信无\n\t\t" +
       "疑，过度关心可通过保证而减轻。",
       "5、偏重，病人大量或频繁地主诉患躯体疾病或身体机能失常，或显\n\t\t" +
       "示一到两个关于这些主题的妄想，但尚未被其占据。",
       "6、重度，病人被一个或几个明确的关于躯体疾病或器质性机能失常\n\t\t" +
       "的妄想所占据，但情感尚未陷入其中，其思维经会谈者的努力能\n\t\t" +
       "有所转移。",
       "7、极重度，大量而频繁地诉说躯体妄想，或是一些灾难性的躯体妄\n\t\t" +
       "想，完全支配病人的思维和情感。"},
	 // G2
	 {"1、无症状或定义不适用于该病人。",
         "2、症状可疑，可能是正常范围的上限。 ",
         "3、轻度，表示有些担优、过度关心或主观的坐立不安，但没有诉说\n\t\t" +
         "或表现出相应的躯体症状和行为。",
         "4、中度，病人诉说有明显的神经紧张症状，并反映出轻微的躯体症\n\t\t" +
         "状，如手的震颤和过度出汗。",
         "5、偏重，病人诉说有严重的焦虑问题，具有显著的躯体症状和行为\n\t\t" +
         "表现，如明显的肌肉紧张，注意力下降，心悸或睡眠障碍。",
         "6、重度，几乎持续感受到害怕并伴有恐惧，明显的坐立不安，或有\n\t\t" +
         "许多躯体症状。",
         "7、极重度，病人的生活严重地被焦虑困扰，焦虑几乎持续存在，有\n\t\t" +
         "时达到惊恐的程度或表现为惊恐发作。"},
	 // G3
	 {"1、无症状或定义不适用于该病人。",
           "2、症状可疑，可能是正常范围的上限。 ",
           "3、轻度，询问时引出病人对微小事件的模糊的内疚或自责，但病人\n\t\t" +
           "显然并不过分在意。",
           "4、中度，病人明确表示在意他对过去发生的一件真实事件的责任，\n\t\t" +
           "但并未被其占据，态度和行为基本未受影响。",
           "5、偏重，病人表示出强烈的罪恶感，伴有自我责难或认为自己应受\n\t\t" +
           "惩罚，罪恶感可能有妄想基础，可能自发形成，可能来源于某种\n\t\t" +
           "先占观念或抑郁心境，且不易被会谈者缓解。",
           "6、重度，带有妄想性质的强烈的罪恶观念，导致出现绝望感或无价\n\t\t" +
           "值感，病人认为应该为其过失受到严厉惩罚，甚至认为他现在的\n\t\t" +
           "生活处境就是这种惩罚。",
           "7、极重度，病人的生活被不可动摇的罪恶妄想所支配，感到自己应\n\t\t" +
           "受严厉的惩罚，如终生监禁、酷刑、或处死，可能伴有自杀念头\n\t\t" +
           "，或将他人的问题归咎于自己过去的过失。"},
	 // G4
	 {"1、无症状或定义不适用于该病人。",
             "2、症状可疑，可能是正常范围的上限。 ",
             "3、轻度，姿势和动作表现出轻微担优，如轻度僵硬，偶而坐立不\n\t\t" +
             "安，变换姿势或手部轻微快速震颤。",
             "4、中度，明显的紧张表现许多症状，如局促不安，明显的手部震\n\t\t" +
             "颤，过度出汗或紧张性作态。",
             "5、偏重，显著的紧张表现为许多症状，如紧张性颤抖，大量出汗和\n\t\t" +
             "坐立不安，但会谈的进行并未受到明显的影响。",
             "6、重度，显著的紧张妨碍人际交往，如持续的局促不安，无法静坐\n\t\t" +
             "或过度换气。",
             "7、极重度，明显的紧张表现为惊恐症状或显著的动作加速，如快速\n\t\t" +
    "地来回走动和无法静。"},
	 // G5
	 {"1、无症状或定义不适用于该病人。",
      "2、症状可疑，可能是正常范围的上限。 ",
      "3、轻度，动作轻度不自然(awkward)或轻微的姿势僵硬。",
      "4、中度，动作明显不自然(awkward)或不连贯，或短时间保持一种\n\t\t" +
      "不自然的姿势。",
      "5、偏重，观察到偶有古怪的仪式动作或扭曲的姿势，或长时间保持\n\t\t" +
      "一种异常的姿势。",
      "6、重度，经常重复出现古怪的仪式动作、作态或刻板动作，或长时\n\t\t" +
      "间保持一种扭曲的姿势。",
      "7、极重度，持续不断的仪式动作、作态或刻板动作导致功能严重受\n\t\t" +
      "损，或几乎一直保持一种不自然的固定姿势。"},
	 // G6
	 {"1、无症状或定义不适用于该病人。",
        "2、症状可疑，可能是正常范围的上限。 ",
        "3、轻度，只在被问及时表示有些悲伤或失去信心，但总的态度或行\n\t\t" +
        "为举止没有抑郁表现。",
        "4、中度，明显地感到悲伤或无望，可能自发地流露，但抑郁心境未\n\t\t" +
        "对行为或社会功能造成很大损害，病人通常还能高兴起来。",
        "5、偏重，明显的抑郁心境伴有明显的悲伤、悲观庆世、丧失社会兴\n\t\t" +
        "趣，精神运动迟滞和食欲、睡眠障碍，病人不易高兴起来。",
        "6、重度，昵显的抑郁心境伴有持续的痛苦感，偶而哭泣，无望和无\n\t\t" +
        "价值感。另外，对食欲和／或睡眠以及正常动作和社会功能有严\n\t\t" +
        "重影响，可能有自我忽视的症状。",
        "7、极重度，抑郁感觉严重妨碍大多数主要功能，症状包括经常哭\n\t\t" +
        "泣，明显的躯体症状，注意力损害，精神运动迟滞，丧失社会兴\n\t\t" +
        "趣，自我忽视，可能的抑郁或虚无妄想，和／或可能有自杀意\n\t\t念或行为。"},
	 // G7
	 {"1、无症状或定义不适用于该病人。",
          "2、症状可疑，可能是正常范围的上限。 ",
          "3、轻度，轻微的但可观察到的动作或讲话速度减慢，病人的谈话内\n\t\t" +
          "容和姿势有点不足。",
          "4、中度，病人的动作明显减慢，讲话的特点是词汇量不足，包括反\n\t\t" +
          "应期延长，停顿延长或语速缓慢。",
          "5、偏重，动作的能动性明显减退，导致会谈内容非常不足，或影响\n\t\t" +
          "社会和职业功能，常常发现病人呆坐或卧床。",
          "6、重度，动作极其缓慢，导致极少活动和讲话，病人基本上整天呆\n\t\t" +
          "坐或卧床。",
          "7、极重度，病人几乎完全不动，对外界刺激毫无反应。"},
	 // G8
	 {"1、无症状或定义不适用于该病人。",
            "2、症状可疑，可能是正常范围的上限。 ",
            "3、轻度，以一种愤恨，不耐烦或讥讽的态度服从。会谈中可能婉转\n\t\t" +
            "地反对敏感问题。",
            "4、中度，偶而直率地拒绝服从正常的社会要求，如整理自己的床\n\t\t" +
            "铺，参加安排好的活动等。病人可能表现敌对、防御或否定的态\n\t\t" +
            "度，但通常仍可共事。",
            "5、偏重，病人经常不服从周围环境的要求，可能被他人认为是一\n\t\t" +
            "个“流浪者”或有“严重的态度问题”，不合作表现为对会谈者明显\n\t\t" +
            "的防御或易激惹，可能对许多问题不愿回答。",
            "6、重度，病人高度不合作，否定，甚至可能好斗，拒绝服从大部分\n\t\t" +
            "社会要求，可能不愿开始或完成整个会谈。",
            "7、极重度，主动的抗拒严重影响日常功能的大多数方面，病人可能\n\t\t" +
            "拒绝任何社交活动、个人卫生、与家属或工作人员谈话，甚至拒\n\t\t" +
            "绝简短的会谈。"},
	 // G9
	 {"1、无症状或定义不适用于该病人。",
              "2、症状可疑，可能是正常范围的上限。 ",
              "3、轻度，思维内容有些奇怪或特异，或熟悉的观念，却用在古怪的\n\t\t上下文中。",
              "4、中度，观念经常被歪曲，偶而显得非常古怪。",
              "5、偏重，病人表达许多奇怪的幻想的思维内容（如：是国王的养子，\n\t\t" +
              "是死亡名单的逃脱者）或一些明显荒谬的想法（如：有一百个女子，\n\t\t" +
              "通过牙齿填充物收到来自外太空的无线电讯息）。",
              "6、重度，病人表达许多不合逻辑的或荒谬的观念，有些具有非常古\n\t\t" +
              "怪的性质（如：有三个脑袋，是外星人）。",
     "7、极重度，思维充满荒谬、古怪和怪诞的想法。"},
	 // G10
	 { "1、无症状或定义不适用于该病人。",
       "2、症状可疑，可能是正常范围的上限。 ",
       "3、轻度，一般的定向尚可，但精确的定向有些困难，如病人知道他\n\t\t" +
       "在何地，但不知道确切地址；知道医院工作人员的名字，但不知\n\t\t" +
       "道他们的职能；知道月份，但星期几搞错一天，或日期相差二天\n\t\t" +
       "以上，可能有兴趣范围狭窄，表现为熟悉身边的环境但不知道外\n\t\t" +
       "围的环境，如认识工作人员，但不认识市长或总统。",
       "4、中度，只能对时间、地点、人物部分定向，如病人知道他在医院\n\t\t" +
       "里，但不知道医院的名称；知道他所在城市的名称，但不知道村\n\t\t" +
       "镇或行政区的名称；知道他主治人员的名字，但不知道其他直接\n\t\t" +
       "照料者的名字；知道年份和季节，但不知道确切的月份。",
       "5、偏重，人物、时间、地点的定向力大部分受损，病人只有一些模\n\t\t" +
       "糊的概念，如他在何处，似乎对环境中的大多数人都感觉陌生，\n\t\t" +
       "可能会正确或接近地说出年份，但月份、星期几，甚至季节都不\n\t\t知道。",
       "6、重度，人物、地点、时间定向力明显丧失。如：病人不知道身在\n\t\t" +
       "何处，对日期的误差超过一年，仅能说出当前生活中一、两个人\n\t\t名。",
       "7、极重度，病人完全丧失人物、地点、时间定向力，严重混乱，完\n\t\t" +
       "全忽视自己身在何处，现在的年份，甚至最熟悉的人，如父母、\n\t\t" +
       "配偶、朋友和主治人员。"},
	 // G11
	 {"1、无症状或定义不适用于该病人。",
         "2、症状可疑，可能是正常范围的上限。 ",
         "3、轻度，注意力集中受限，偶而容易分心或在会谈将结束时显得注\n\t\t" +
         "意力不集中。",
         "4、中度，会谈因注意力容易分散的倾向而受影响，难以长时间将注\n\t\t" +
         "意力集中在一个主题上，或难以将注意力转向新的主题。",
         "5、偏重，会谈因为注意力不集中，分散和难以适当地转换注意点而\n\t\t" +
         "受到严重影响。",
         "6、重度，病人的注意力由于受内在的或外在的刺激而明显分散，注\n\t\t" +
         "意仅能维持片刻或需作很大努力。",
         "7、极重度，注意力严重障碍，以致简短的交谈都无法进行。"},
	 // G12
	 {"1、无症状或定义不适用于该病人。",
           "2、症状可疑，可能是正常范围的上限。 ",
           "3、轻度，认识到有某种精神障碍，但明显低估其严重性，治疗的意\n\t\t" +
           "义或采取措施以避免复发的重要性，可能对未来计划的构想力差",
           "4、中度，病人表现为对疾病只有模糊或肤浅的认识，对于承认患病\n\t\t" +
           "动摇不定，或对存在的主要症状很少认识，如妄想、思维混乱、\n\t\t" +
           "猜疑和社会退缩，病人可能将需要治疗理解为减轻一些较轻的症\n\t\t" +
           "状，如焦虑、紧张和睡眠困难。",
           "5、偏重，认识到过去但不是现在有精神障碍，如提出质疑，病人可\n\t\t" +
           "能勉强承认一些无关的或不重要的症状，并倾向于以完全错误的\n\t\t" +
           "解释或妄想性思维来加以开脱，同样，认为不需要精神治疗。",
           "6、重度，病人否认曾患精神障碍，病人否认过去或现在存在的任何\n\t\t" +
           "精神症状，尽管尚能顺从，但否认需要治疗和住院。",
           "7、极重度，断然否认过去或现在存在精神疾病，对目前的住院和治\n\t\t" +
           "疗给予妄想性的解释（如因过失而受惩罚，被人迫害等），病人因\n\t\t" +
           "此拒绝配合治疗者、药物或其他治疗。"},
	 // G13
	 {"1、无症状或定义不适用于该病人。",
             "2、症状可疑，可能是正常范围的上限。 ",
             "3、轻度，病人的谈话和思维有些犹豫不决，轻度妨碍言语和认知过\n\t\t程。",
             "4、中度，病人经常出现矛盾症状，做决定有明显的困难，交谈可因\n\t\t" +
             "思维的变化不定而受影响，言语和认知功能明显受损。",
             "5、偏重，意志障碍思维及行为，病人表现严重的犹豫不决，妨碍社\n\t\t" +
             "会和动作活动的产生和持续，也可能表现为言语停顿。",
             "6、重度，意志障碍妨碍简单的、自主的动作功能，如穿衣和梳理，\n\t\t" +
             "明显地影响言语功能。",
             "7、极重度，意志几乎完全丧失，表现为严重的动作和语言抑制，导\n\t\t" +
     "致不动和／或缄默。"},
	 // G14
	 {"1、无症状或定义不适用于该病人。",
       "2、症状可疑，可能是正常范围的上限。 ",
       "3、轻度，当面对应激或不如意时，病人容易出现愤怒和挫折感，但\n\t\t" +
       "很少有冲动行为。",
       "4、中度，病人对轻微的挑衅就会愤怒和谩骂，可能偶而出现威胁、\n\t\t" +
       "破坏或一两次冲突或程度较轻的打架。",
       "5、偏重，病人反复出现冲动，包括漫骂、毁物或身体威胁，可能有\n\t\t" +
       "一次严重的攻击，以致病人需要隔离、身体约束或必要时给予镇\n\t\t静。",
       "6、重度，病人经常不计后果地出现攻击行为、威胁、强人所难和毁\n\t\t" +
       "物，可能有性攻击，可能为对幻听的行为反应。",
       "7、极重度，病人出现致命的攻击，性侵犯，反复的残暴行为或自残\n\t\t" +
       "行为。需要不断地直接监护或约束以控制其危险性冲动。"},
	 // G15
	 {"1、无症状或定义不适用于该病人。",
         "2、症状可疑，可能是正常范围的上限。 ",
         "3、轻度，过分关注个人需要和问题，使会谈转向自我中心的主题，\n\t\t" +
         "对他人缺乏关心。",
         "4、中度，病人偶而表现自我专注，好象在做白日梦或关注内在体\n\t\t" +
         "验，轻度妨碍交往。",
         "5、偏重，病人常表现为专注于内向体验，明显影响社交和会谈功\n\t\t" +
         "能，如出现目光呆滞、喃喃自语或自言自语，或出现刻板的动\n\t\t作模式。",
         "6、重度，明显的内向性思维伴孤独性体验，使注意力、交谈能力及\n\t\t" +
         "对环境的定向力严重受限，病人经常一个人微笑、大笑、喃喃自\n\t\t" +
         "语、自言自语或大叫。",
         "7、极重度，严重地专注于内向体验，极度影响所有重要的行为，病\n\t\t" +
         "人不断的对幻觉做出语言和行为反应，很少注意他人或外部环境"},
	 // G16
	 { "1、无症状或定义不适用于该病人。",
           "2、症状可疑，可能是正常范围的上限。 ",
           "3、轻度，病人与他人相处时似乎显得不自在，喜欢独自消磨时光，\n\t\t" +
           "虽然在要求下仍在参加社会活动。",
           "4、中度，病人非常勉强地参加所有大部分社交活动，但可能需要劝\n\t\t" +
           "说，或可能因焦虑、猜疑或敌对而提早退出。",
           "5、偏重，尽管他人努力邀请他，病人仍恐惧或愤怒地回避许多社会\n\t\t" +
           "交往，倾向于独自消磨空闲时间。",
           "6、重度，病人因恐惧、敌对或不信任而极少参加社交活动，当他人\n\t\t" +
           "接近时，病人表现出强烈的中止交往的倾向。总的来说，他将自\n\t\t" +
           "己与他人隔离。",
           "7、极重度，病人因极恐惧、敌对或被害妄想而不参加社交活动，最\n\t\t" +
           "严重时病人回避所有的交往而与世隔绝。"},
	 // S1
	 { "1、无症状或定义不适用于该病人。",
             "2、症状可疑，可能是正常范围的上限。 ",
             "3、轻度。",
             "4、中度。",
             "5、偏重。",
             "6、重度。",
     "7、极重度。"},
	 // S2
	 {"1、无症状或定义不适用于该病人。",
       "2、症状可疑，可能是正常范围的上限。 ",
       "3、轻度。",
       "4、中度。",
       "5、偏重。",
       "6、重度。",
       "7、极重度。"},
	 // S3
	 {"1、无症状或定义不适用于该病人。",
         "2、症状可疑，可能是正常范围的上限。 ",
         "3、轻度。",
         "4、中度。",
         "5、偏重。",
         "6、重度。",
         "7、极重度。"}
 };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：此量表为他评量表。" };

	public QuestionnaireModelOfPANSS() {
		super(QuestionnaireCodeEnum.PANSS);

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
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
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