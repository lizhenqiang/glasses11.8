package com.remote.glasses.utils;

import com.remote.glasses.bean.QuestionsBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feics on 2016/10/31.
 */
public class PullParserUtils
{
    public static List<QuestionsBean> parserXmlByPull(InputStream inputStream) throws Exception
    {
        List<QuestionsBean> questionsBeanList = null;
        List<QuestionsBean.Question> questionList = null;
        List<QuestionsBean.Question.Options> optionsList = null;

        QuestionsBean questionsBean = null;
        QuestionsBean.Question question = null;
        QuestionsBean.Question.Options option = null;

        //    创建XmlPullParserFactory解析工厂
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        //    通过XmlPullParserFactory工厂类实例化一个XmlPullParser解析类
        XmlPullParser parser = factory.newPullParser();
        //    根据指定的编码来解析xml文档
        parser.setInput(inputStream, "utf-8");

        //    得到当前的事件类型
        int eventType = parser.getEventType();
        //    只要没有解析到xml的文档结束，就一直解析
        while(eventType != XmlPullParser.END_DOCUMENT)
        {
            String name = parser.getName();
            switch (eventType)
            {
                //    解析到文档开始的时候
                case XmlPullParser.START_DOCUMENT:
                    questionsBeanList = new ArrayList<>();
                    break;
                //    解析到xml标签的时候
                case XmlPullParser.START_TAG:
                    if("category".equals(name))
                    {
                        questionsBean = new QuestionsBean();
                        questionList = new ArrayList<>();
                        questionsBean.setName(parser.getAttributeValue(0));
                    }
                    else if("question".equals(name))
                    {
                        question = questionsBean.new Question();

                    }
                    else if("title".equals(name))
                    {
                        question.setTitle(parser.nextText());
                    }
                    else if("res-url".equals(name))
                    {
                        question.setMedia(parser.nextText());
                    }
                    else if("options".equals(name))
                    {
                        optionsList = new ArrayList<>();
                    }
                    else if("option".equals(name))
                    {
                        option = question.new Options();
                    }
                    else if("desc".equals(name))
                    {
                        option.setDesc(parser.nextText());
                    }
                    else if("remark".equals(name))
                    {
                        option.setRemark(parser.nextText());
                    }
                    else if("media".equals(name))
                    {
                        option.setMedia(parser.nextText());
                    }

                    break;
                //    解析到xml标签结束的时候
                case XmlPullParser.END_TAG:
                    if("category".equals(name))
                    {
                        questionsBean.setQuestion(questionList);
                        questionsBeanList.add(questionsBean);
                        questionList = null;
                        questionsBean = null;
                    }else if ("question".equals(name)){
                        questionList.add(question);
                        question = null;
                    }else if ("option".equals(name)){
                        optionsList.add(option);
                        option = null;
                    }
                    else if ("options".equals(name)){
                        question.setOptions(optionsList);
                        optionsList = null;
                    }
                    break;
            }
            //    通过next()方法触发下一个事件
            eventType = parser.next();
        }

        return questionsBeanList;
    }
}
