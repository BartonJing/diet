package com.barton.service.impl;

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

public class DietServiceImpl extends BaseBot{
    /**
     * 重写BaseBot构造方法
     */
    public DietServiceImpl(HttpServletRequest request) throws IOException {
        super(request);
    }

    /**
     * 重写onLaunch方法，处理onLaunch对话事件
     */
    @Override
    protected Response onLaunch(LaunchRequest launchRequest) {

        // 新建文本卡片
        TextCard textCard = new TextCard("所得税为您服务");
        // 设置链接地址
        textCard.setUrl("www:....");
        // 设置链接内容
        textCard.setAnchorText("setAnchorText");
        // 添加引导话术
        textCard.addCueWord("欢迎进入");

        // 新建返回的语音内容
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "所得税为您服务");

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
        TextCard textCard = new TextCard("感谢使用所得税服务");
        textCard.setAnchorText("setAnchorText");
        textCard.addCueWord("欢迎再次使用");

        // 构造OutputSpeech
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "欢迎再次使用所得税服务");

        // 构造Response
        Response response = new Response(outputSpeech, textCard);

        return response;
    }

    /**
     * 重写onInent方法，处理onInent对话事件
     */
    @Override
    protected Response onInent(IntentRequest intentRequest) {

        // 判断NLU解析的意图名称是否匹配
        if ("myself".equals(intentRequest.getIntentName())) {
            // 判断NLU解析解析后是否存在这个槽位
            if (getSlot("monthlysalary") == null) {
                // 询问月薪槽位
                ask("monthlysalary");
                return askSalary();
            } else if (getSlot("location") == null) {
                // 询问城市槽位
                ask("location");
                return askLocation();
            } else if (getSlot("compute_type") == null) {
                // 询问查询种类槽位
                ask("compute_type");
                return askComputeType();
            } else {
                // 具体计算方法
                compute();
            }
        }

        return null;
    }

    private Response askSalary() {
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
    }

    private Response compute() {
        String monthlysalary = this.getSlot("monthlysalary");
        String location = this.getSlot("location");
        String type = this.getSlot("compute_type");
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
    private Response askLocation() {
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
    }
}
