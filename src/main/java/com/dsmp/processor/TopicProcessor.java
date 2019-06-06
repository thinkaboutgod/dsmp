package com.dsmp.processor;

import java.io.IOException;

import com.dsmp.utils.DownloadUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class TopicProcessor implements PageProcessor {

	@Override
	public void process(Page page) {
		
//		page.addTargetRequests(
//				page.getHtml().links().regex("https://blog.csdn.net/"+
//						"[a‐z 0‐9 ‐]+/article/details/[0‐9]{8}").all());//将当前页面里的所有链接都添加到目标页面中
//		page.putField("title",page.getHtml().xpath("//*"+
//				"[@id=\"mainBox\"]/main/div[1]/div[1]/h1/text()").toString());

		
		for(int i =1306;i<=1332;i++) {
			
			String title = page.getHtml().xpath("//*[@id=\"q_"+i+"\"]/p/text()").get();
			int index = title.lastIndexOf(".");
			title = title.substring(index+2);
			System.out.println("第"+i+"题题目:"+title);
			page.putField("第"+i+"题题目",title);
			String imageUrl = page.getHtml().xpath("//*[@id=\"q_"+i+"\"]/div/div[2]/div").css("img","src").get();
			
			if(null!=imageUrl) {
				imageUrl="http://www.jppt.com.cn"+imageUrl;
				String fileName = "第"+i+"题.png";
				System.out.println(imageUrl);
				try {
					DownloadUtil.download(imageUrl, fileName, "D:/data/image");
					System.out.println(fileName+"下载成功");
					page.putField("第"+i+"题imageUrl",imageUrl);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			String optA = page.getHtml().xpath("//*[@id=\"p_16_"+i+"\"]/span/text()").get();
			System.out.println("第"+i+"题选项A:"+optA);
			page.putField("第"+i+"题选项A",optA);
			String optB = page.getHtml().xpath("//*[@id=\"p_32_"+i+"\"]/span/text()").get();
			System.out.println("第"+i+"题选项B:"+optB);
			page.putField("第"+i+"题选项B",optB);
			String optC = page.getHtml().xpath("//*[@id=\"p_64_"+i+"\"]/span/text()").get();
			
			if(null!=optC) {
				System.out.println("第"+i+"题选项C:"+optC);
				page.putField("第"+i+"题选项C",optC);
			}
			
			
			String optD = page.getHtml().xpath("//*[@id=\"p_128_"+i+"\"]/span/text()").get();
			
			if(null!=optD) {
				System.out.println("第"+i+"题选项D:"+optD);
				page.putField("第"+i+"题选项D",optD);
			}
			
			//1306题开始，答案的input节点的id发生变化
			String answer="";
			if(i>=1306&&i<1312) {
				int b = 9554 +i;
				answer =  page.getHtml().xpath("//*[@id=\"answer_"+b+"\"]").css("input","value").get();
			}else if(i>=1312) {
				//1312题，答案的id再次发生变化
				int c = 9558 +i;
				answer =  page.getHtml().xpath("//*[@id=\"answer_"+c+"\"]").css("input","value").get();
			}else {
				answer =  page.getHtml().xpath("//*[@id=\"answer_"+i+"\"]").css("input","value").get();
			}
			
			
			switch(answer) {
				case"16":
					answer = optA;
				break;
				case"32":
					answer = optB;
				break;
				case"64":
					answer = optC;
				break;
				case"128":
					answer = optD;
				break;
				
			}
			
			System.out.println("第"+i+"题答案："+answer);
			page.putField("第"+i+"题答案",answer);
			String explain =  page.getHtml().xpath("//*[@id=\"explain_"+i+"\"]").css("div","data-explain").get();
			
			System.out.println("第"+i+"题解析："+explain);
			page.putField("第"+i+"题解析",explain);
			
			
		}

	}

	@Override
	public Site getSite() {
		return Site.me().setSleepTime(100).setRetryTimes(3);
	}

	public static void main(String[] args) {
		
		Spider.create( new TopicProcessor() )
		.addUrl("http://www.jppt.com.cn/theory/queue-C1-1.html")
		.addPipeline(new ConsolePipeline())
		.addPipeline(new FilePipeline("D:/data"))//以文件方式保存
		.run();
		
	}
	
	
}
