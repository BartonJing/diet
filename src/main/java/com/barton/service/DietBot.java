package com.barton.service;

import com.baidu.dueros.bot.BaseBot;
import com.baidu.dueros.data.request.IntentRequest;
import com.baidu.dueros.data.request.LaunchRequest;
import com.baidu.dueros.data.request.SessionEndedRequest;
import com.baidu.dueros.data.response.OutputSpeech;
import com.baidu.dueros.data.response.Reprompt;
import com.baidu.dueros.data.response.card.TextCard;
import com.baidu.dueros.model.Response;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
public class DietBot extends BaseBot {

    //意图
    public final static String INTENT = "diet_recommendation";
    //槽位-身体状态
    public final static String SLOT_BODY_STATUS = "sys_body_status";
    //槽位-自己做还是购买
    public final static String SLOT_DO_STATUS= "sys_do_status";
    //槽位-什么餐（早，午，晚）
    public final static String SLOT_EAT_STATUS= "sys_eat_status";
    //槽位-位置
    public final static String SLOT_BODY_NAVGATION = "sys.location-navigation";
    /**
     * 重写BaseBot构造方法
     */
    public DietBot(HttpServletRequest request) throws IOException {
        super(request);
    }
    /**
     * 重写onLaunch方法，处理onLaunch对话事件
     */
    @Override
    protected Response onLaunch(LaunchRequest launchRequest) {

        // 新建文本卡片
        TextCard textCard = new TextCard("饮食推荐为您服务");
        // 设置链接地址
        textCard.setUrl("www:....");
        // 设置链接内容
        textCard.setAnchorText("setAnchorText");
        // 添加引导话术
        textCard.addCueWord("欢迎进入");

        // 新建返回的语音内容
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "饮食推荐为您服务");

        // 构造返回的Response
        Response response = new Response(outputSpeech, textCard);

        return response;
    }

    /**
     * 重写onSessionEnded事件，处理onSessionEnded对话事件
     */
    @Override
    protected Response onSessionEnded(SessionEndedRequest sessionEndedRequest) {

        // 构造TextCard
        TextCard textCard = new TextCard("感谢使用饮食推荐服务");
        textCard.setAnchorText("setAnchorText");
        textCard.addCueWord("欢迎再次使用");

        // 构造OutputSpeech
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "欢迎再次使用饮食推荐服务");

        // 构造Response
        Response response = new Response(outputSpeech, textCard);

        return response;
    }

    /**
     * 重写onInent方法，处理onInent对话事件
     */
    @Override
    protected Response onInent(IntentRequest intentRequest) {
        String monthlysalary = this.getSlot(SLOT_BODY_STATUS);
        String location = this.getSlot(SLOT_DO_STATUS);
        String type = this.getSlot(SLOT_EAT_STATUS);
        // 判断NLU解析的意图名称是否匹配
        if (INTENT.equals(intentRequest.getIntentName())) {
            // 判断NLU解析解析后是否存在这个槽位
            if (getSlot(SLOT_BODY_STATUS) == null) {
                // 询问生体状况槽位
                ask(SLOT_BODY_STATUS);
                return askBodyStatus();
            } else if (getSlot(SLOT_DO_STATUS) == null) {
                // 询问做还是购买槽位
                ask(SLOT_DO_STATUS);
                return askDoStatus();
            } else if (getSlot(SLOT_EAT_STATUS) == null) {
                // 询问查询种类槽位
                ask(SLOT_EAT_STATUS);
                return askEatStatus();
            } else {
                // 具体计算方法
                compute();
            }
        }

        return null;
    }

    /**
     * 身体状况
     * @return
     */
    private Response askBodyStatus(){
        TextCard textCard = new TextCard("请问您最近是否有特殊情况如在减肥，高血压，哺乳等?");
        textCard.setUrl("www:......");
        textCard.setAnchorText("链接文本");
        textCard.addCueWord("请问您最近是否有特殊情况如在减肥，高血压，哺乳等?");
        this.setSessionAttribute("key_1", "value_1");
        this.setSessionAttribute("key_2", "value_2");
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "请问您最近是否有特殊情况如在减肥，高血压，哺乳等?");
        Reprompt reprompt = new Reprompt(outputSpeech);
        Response response = new Response(outputSpeech, textCard, reprompt);
        return response;
    }

    /**
     * 做还是买
     * @return
     */
    private Response askDoStatus(){
        TextCard textCard = new TextCard("您是要自己做还是去购买?");
        textCard.setUrl("www:......");
        textCard.setAnchorText("链接文本");
        textCard.addCueWord("您是要自己做还是去购买?");
        this.setSessionAttribute("key_1", "value_1");
        this.setSessionAttribute("key_2", "value_2");
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "您是要自己做还是去购买?");
        Reprompt reprompt = new Reprompt(outputSpeech);
        Response response = new Response(outputSpeech, textCard, reprompt);
        return response;
    }

    /**
     * 餐饭种类
     * @return
     */
    private Response askEatStatus(){
        TextCard textCard = new TextCard("你要推荐早餐，午餐还是晚餐?");
        textCard.setUrl("www:......");
        textCard.setAnchorText("链接文本");
        textCard.addCueWord("你要推荐早餐，午餐还是晚餐?");
        this.setSessionAttribute("key_1", "value_1");
        this.setSessionAttribute("key_2", "value_2");
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "你要推荐早餐，午餐还是晚餐?");
        Reprompt reprompt = new Reprompt(outputSpeech);
        Response response = new Response(outputSpeech, textCard, reprompt);
        return response;
    }





    /*private Response askSalary() {
        TextCard textCard = new TextCard("您的税前工资是多少呢?");
        textCard.setUrl("www:......");
        textCard.setAnchorText("链接文本");
        textCard.addCueWord("您的税前工资是多少呢?");
        this.setSessionAttribute("key_1", "value_1");
        this.setSessionAttribute("key_2", "value_2");
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "您的税前工资是多少呢?");
        Reprompt reprompt = new Reprompt(outputSpeech);
        Response response = new Response(outputSpeech, textCard, reprompt);
        return response;
    }

    private Response askComputeType() {
        TextCard textCard = new TextCard("请选择您要查询的种类");
        textCard.setAnchorText("setAnchorText");
        textCard.addCueWord("请选择您要查询的种类");
        this.setSessionAttribute("key_1", "value_1");
        this.setSessionAttribute("key_2", "value_2");
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "请选择您要查询的种类");
        Reprompt reprompt = new Reprompt(outputSpeech);
        Response response = new Response(outputSpeech, textCard, reprompt);
        return response;
    }*/

    private Response compute() {
        String monthlysalary = this.getSlot(SLOT_BODY_STATUS);
        String location = this.getSlot(SLOT_DO_STATUS);
        String type = this.getSlot(SLOT_EAT_STATUS);
        String ret = "月薪" + monthlysalary + "城市" + location + "个税种类" + type;
        TextCard textCard = new TextCard(ret);
        textCard.setAnchorText("setAnchorText");
        textCard.addCueWord("查询成功");
        this.setSessionAttribute("key_1", "value_1");
        this.setSessionAttribute("key_2", "value_2");
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, ret);
        Reprompt reprompt = new Reprompt(outputSpeech);
        this.endDialog();
        Response response = new Response(outputSpeech, textCard, reprompt);
        return response;
    }
    /*private Response askLocation() {
        TextCard textCard = new TextCard("您所在的城市是哪里呢?");
        textCard.setUrl("www:......");
        textCard.setAnchorText("setAnchorText");
        textCard.addCueWord("您所在的城市是哪里呢?");
        this.setSessionAttribute("key_1", "value_1");
        this.setSessionAttribute("key_2", "value_2");
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "您所在的城市是哪里呢?");
        Reprompt reprompt = new Reprompt(outputSpeech);
        Response response = new Response(outputSpeech, textCard, reprompt);
        return response;
    }*/
}
