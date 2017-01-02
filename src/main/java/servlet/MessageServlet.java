/*package servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WsOutbound;

import com.alibaba.fastjson.JSONObject;

*//**
 * Servlet implementation class MessageServlet
 *//*
@SuppressWarnings("deprecation")
@WebServlet("/MessageServlet")
public class MessageServlet extends org.apache.catalina.websocket.WebSocketServlet {
 
    public MessageServlet(){
        listeningInterept();
    }
    
    private Logger logger = Logger.getLogger(MessageServlet.class.getSimpleName());

    private static final long serialVersionUID = 1L;
    private static Map<String,WsOutbound> userMap=new HashMap<String,WsOutbound>();
    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
         String uName=request.getParameter("u");
        if(null==uName || "".equals(uName.trim())){
            uName="unknow";
        }
        
        try {
            uName=new String(uName.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e1) {
            uName="unknow";
        }
        final String userName=" 【"+uName+"】";
        final String unameKey=" 【"+uName+"】@"+request.getRemoteAddr();
        logger.info(userName + " join ");
        
        MessageInbound messageInbound= new MessageInbound() {
            @Override
            protected void onClose(int status) {
                logger.info(";web socket closed :" + status);
                logger.info("user logout ! current online size:"+userMap.size());
                testValidConnect();
            }

            @Override
            protected void onOpen(WsOutbound outbound) {
                userMap.put(unameKey, outbound);
                try {
                    logInfo(getDateStr()+"_user_log.txt", getDateTimeStr()+"\t"+unameKey+"\r\n");
                    logger.info("new user join ! current online size:"+userMap.size());
                    sendMessage("system",1,userMap.keySet().toArray(new String[]{}));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            @Override
            protected void onBinaryMessage(ByteBuffer buff) throws IOException {
                // TODO Auto-generated method stub
                logger.info("Received binary msg: " + new String(buff.array()));
            }

            @Override
            protected void onTextMessage(CharBuffer buff) throws IOException {
                logger.info(" Received text msg :" + userName+"=>"+ buff.toString());
                String time=new  SimpleDateFormat("HH:mm:ss").format(new Date());
                sendMessage(userName+"  "+time,2,buff.toString());
            }
        };
        return messageInbound;
    }
    
    *//**
     * 发送消息
     * @author ZhangShuai
     * @version 1.0
     * @created Aug 4, 2015 2:10:44 PM
     * @param sender
     * @param type
     * @param buff
     * @throws IOException
     *//*
    private void sendMessage(String sender,int type,Object objMsg) throws IOException{
        String date =getDateStr();
        String time =getDateTimeStr();
        logInfo(date+"_send_log.txt",sender+"\t"+ time+"\t"+objMsg+"\r\n");
        JSONObject json=new JSONObject();
        json.put("type",type);
        json.put("sender", sender);
        json.put("msg",objMsg);
        Iterator<Entry<String, WsOutbound>> it=userMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, WsOutbound> entry=it.next();
            try{
                WsOutbound ws=entry.getValue();
                ws.writeTextMessage(CharBuffer.wrap(json.toString()));
                ws.flush();
            }catch(Throwable e){
                userMap.remove(entry.getKey());
                sendMessage("system",1,userMap.keySet().toArray(new String[]{}));
            }
        }
    }
    
    *//**
     * 定时清除无效连接
     * @author ZhangShuai
     * @version 1.0
     * @created Aug 4, 2015 3:41:32 PM
     *//*
    private void listeningInterept(){
        Thread thread=new Thread(){
            public void run(){
                while(true){
                    try {
                        Thread.sleep(60*1000);
                        logger.info("startup  invaild connect clearing...");
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    testValidConnect();
                }
            }
        };
        thread.setPriority(Thread.MAX_PRIORITY);
//        thread.start();
    }
    
    *//**
     * 发送空消息,测试连接是否有效的
     * 
     * @author ZhangShuai
     * @version 1.0
     * @created Aug 4, 2015 3:54:36 PM
     *//*
    protected void testValidConnect(){
        Iterator<Entry<String, WsOutbound>> it=userMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, WsOutbound> entry=null;
            try{
                entry=it.next();
                WsOutbound ws=entry.getValue();
                ws.writeTextMessage(CharBuffer.wrap(" "));
            }catch(Throwable e){
                try {
                    if(entry!=null){
                        userMap.remove(entry.getKey());
                        sendMessage("system",1,userMap.keySet().toArray(new String[]{}));
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    
    private static void logInfo(String path,String info) throws IOException{
        File file=new File(path); //
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw=new FileWriter(file);
        fw.append(info); //
        fw.close();
    }
    
    public static String getDateTimeStr(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    public static String getDateStr(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    
//    public static void main(String sgsg[]) throws IOException{
//        logInfo("123123.txt","11");
//    }
}
*/