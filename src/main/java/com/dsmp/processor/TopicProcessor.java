package com.dsmp.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsmp.mapper.TbOptionMapper;
import com.dsmp.mapper.TbTopicMapper;
import com.dsmp.pojo.TbOption;
import com.dsmp.pojo.TbTopic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class TopicProcessor implements PageProcessor {

	@Autowired
	private TbTopicMapper tbTopicMapper;
	@Autowired
	private TbOptionMapper tbOptionMapper;

	@Override
	public void process(Page page) {

		for (int i = 1; i <= 5; i++) {
			TbTopic tbTopic = new TbTopic();
			List<TbOption> opts = new ArrayList<>();
			
			String topic = page.getHtml().xpath("//*[@id=\"q_" + i + "\"]/p/text()").get();
			int index = topic.lastIndexOf(".");
			topic = topic.substring(index + 2);

			System.out.println("第" + i + "题题目:" + topic);

			page.putField("topic", topic);
			tbTopic.setTopTopic(topic);

			String imageUrl = page.getHtml().xpath("//*[@id=\"q_" + i + "\"]/div/div[2]/div").css("img", "src").get();
			if (null != imageUrl) {
				imageUrl = "http://www.jppt.com.cn" + imageUrl;
				String img = "第" + i + "题.png";
				System.out.println(imageUrl);
				page.putField("img", img);
				tbTopic.setTopImg(img);
//				try {
//					DownloadUtil.download(imageUrl, fileName, "D:/data/image");
//					System.out.println(fileName+"下载成功");
//					page.putField("第"+i+"题imageUrl",imageUrl);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
			// 1306题开始，答案的input节点的id发生变化
			String answer = "";
			if (i >= 1306 && i < 1312) {
				int b = 9554 + i;
				answer = page.getHtml().xpath("//*[@id=\"answer_" + b + "\"]").css("input", "value").get();
			} else if (i >= 1312) {
				// 1312题，答案的id再次发生变化
				int c = 9558 + i;
				answer = page.getHtml().xpath("//*[@id=\"answer_" + c + "\"]").css("input", "value").get();
			} else {
				answer = page.getHtml().xpath("//*[@id=\"answer_" + i + "\"]").css("input", "value").get();
			}

			switch (answer) {
			case "16":
				answer = "A";
				break;
			case "32":
				answer = "B";
				break;
			case "64":
				answer = "C";
				break;
			case "128":
				answer = "D";
				break;
			}

			tbTopic.setTopAnswer(answer);
			System.out.println("第" + i + "题答案：" + answer);
			page.putField("answer", answer);
			String explain = page.getHtml().xpath("//*[@id=\"explain_" + i + "\"]").css("div", "data-explain").get();

			System.out.println("第" + i + "题解析：" + explain);
			page.putField("explain", explain);

			tbTopic.setTopAnswerDetail(explain);

			String optA = page.getHtml().xpath("//*[@id=\"p_16_" + i + "\"]/span/text()").get();
			System.out.println("第" + i + "题选项A:" + optA);
			TbOption optionA = new TbOption();
			
			optionA.setTopId(i);
			optionA.setOptOption(optA);
			if("A".equals(answer)) {
				optionA.setOptStatus("yes");
			}else {
				optionA.setOptStatus("no");
			}
			page.putField("optA", optA);
			
			opts.add(optionA);
			String optB = page.getHtml().xpath("//*[@id=\"p_32_" + i + "\"]/span/text()").get();
			System.out.println("第" + i + "题选项B:" + optB);
			TbOption optionB = new TbOption();
			page.putField("optB", optB);
			optionB.setTopId(i);
			optionB.setOptOption(optB);
			if("B".equals(answer)) {
				optionB.setOptStatus("yes");
			}else {
				optionB.setOptStatus("no");
			}
			opts.add(optionB);
			
			
			String optC = page.getHtml().xpath("//*[@id=\"p_64_" + i + "\"]/span/text()").get();

			if (null != optC) {
				System.out.println("第" + i + "题选项C:" + optC);
				TbOption optionC = new TbOption();
				page.putField("optC", optC);
				optionC.setTopId(i);
				optionC.setOptOption(optC);
				if("C".equals(answer)) {
					optionC.setOptStatus("yes");
				}else {
					optionC.setOptStatus("no");
				}
				opts.add(optionC);
			}

			String optD = page.getHtml().xpath("//*[@id=\"p_128_" + i + "\"]/span/text()").get();

			if (null != optD) {
				System.out.println("第" + i + "题选项D:" + optD);
				TbOption optionD = new TbOption();
				page.putField("optD", optD);
				optionD.setTopId(i);
				optionD.setOptOption(optD);
				if("D".equals(answer)) {
					optionD.setOptStatus("yes");
				}else {
					optionD.setOptStatus("no");
				}
				opts.add(optionD);
			
			}
			
			tbTopic.setSubId(1);
			System.out.println(tbTopic);
			System.out.println(tbTopicMapper);
			System.out.println(tbTopicMapper.insertTopic(tbTopic));
			System.out.println(opts.size());
			
			Integer topId = tbTopicMapper.selectNewTopId();
			
			for (TbOption tbOption : opts) {
				System.out.println(tbOptionMapper);
				tbOption.setTopId(topId);
				System.out.println(tbOptionMapper.insertOption(tbOption));
			}
			
		}
	}

	@Override
	public Site getSite() {
		return Site.me().setSleepTime(100).setRetryTimes(3);
	}

}
