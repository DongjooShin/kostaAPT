package kosta.apt.domain.member;


import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class sampleContent {
    //private static String url ="http://feeds.feedburner.com/rtong";
	private static String url ="http://www.hani.co.kr/rss/";
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        
        URL feedUrl = new URL(url);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndFeed = input.build(new XmlReader(feedUrl));
        
        /*RSS*/
        System.out.println("### getFeedType 	[" + syndFeed.getFeedType() +"]");
        System.out.println("### getLanguage 	[" + syndFeed.getLanguage() +"]");
        System.out.println("### getTitle 		[" + syndFeed.getTitle() +"]");
        System.out.println("### getPublishedDate 	[" + syndFeed.getPublishedDate() +"]");

        List<SyndEntry> entries = syndFeed.getEntries();        
        SyndEntry entry = null;
        
        /*발행정보*/
        for(int i=0, j=entries.size(); i<j ; i++) {
            entry = entries.get(i);
            System.out.println("### getTitle 		[" + entry.getTitle() +"]");
            System.out.println("### getUri 			[" + entry.getUri() +"]");
            System.out.println("### getDescription 	[" + entry.getDescription().getValue() +"]");
            System.out.println("### getPublishedDate[" + entry.getPublishedDate().getTime() +"]");

            /*카테고리*/
            StringBuffer cate = new StringBuffer();
            if(entry.getCategories()!=null && entry.getCategories().size()>0){
            	for(int ii=0,jj=entry.getCategories().size(); ii<jj; ii++){
            		 SyndCategoryImpl ss = (SyndCategoryImpl)entry.getCategories().get(ii);
            		 //cate.append(CommonUtils.replace(ss.getName(), " ", "") ).append(",");
            	}
        }
           // System.out.println("### category		[" + cate.toString() +"]");
        }        
    }
   
}