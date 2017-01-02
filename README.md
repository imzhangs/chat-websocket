# WebSocket Chat + kindeditor  Simple Demo

-------------------

[TOC]
##1.1 ʹ��jdk websocket api ���������
���maven ����

		<dependency>
			<groupId>javax.websocket</groupId>
			<artifactId>javax.websocket-api</artifactId>
			<version>1.1</version>
		</dependency>
	
�����ָ���ڵ�����·�����ṹ����
``` java
@ServerEndpoint(value = "/ws/chat/{token}/{nickName}")
public class ChatServer {

	@OnOpen
	public void onOpen(Session session){
		...
	}
	
	@OnClose
	public void onClose() {
		...
	}

	@OnMessage
	public void onMessage(String message){
		...
	}
	
	@OnError
	public void onError(Throwable throwable) {
		...
	}
}
``` 

##1.2  ʹ�� html javascript �� client

``` javascript
var connect = function(serverURI, username,token) {
	ws = new WebSocket("ws://" + serverURI + "/ws/chat/"+token+"/" + username);
	ws.onopen = function() {
	};
	ws.onmessage = function(evt) {
	};
	ws.onclose = function(evt) {
		ws.close();
	};
	ws.onerror = function(evt) {
		//console.log("WebSocketError!");
	};
}
```
##1.3 ���� Kindeditor-4.1.7
``` vbscript-html
<script type="text/javascript" src="js/kindeditor-4.1.7/kindeditor.js"></script>
<link rel="stylesheet" href="js/kindeditor-4.1.7/themes/default/default.css" />
<script charset="utf-8" src="js/kindeditor-4.1.7/kindeditor-min.js"></script>
<script charset="utf-8" src="js/kindeditor-4.1.7/lang/zh_CN.js"></script>
``` 

����Kindeditor �༭��������
``` javascript
 var editor;
  /****/
  KindEditor.ready(function(K) {
    editor = K.create('#txt', {
       resizeType : 1, 
       cssPath :"js/kindeditor-4.1.7/plugins/code/prettify.css",
       uploadJson : '<%=request.getContextPath() %>/FileUploadServlet',
       width:'755px',
       height:'80px',
	   allowFileManager : true,
	   allowUpload: true,
	   items : [ 'emoticons','|','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', '|', 'image', '|', 'messageRecord' ],
	   colorTable : [
		  [ '#E53333', '#E56600', '#FF9900', '#64451D', '#DFC5A4', '#FFE500' ],
		  [ '#009900', '#006600', '#99BB00', '#B8D100', '#60D978', '#00D5FF' ],
		  [ '#337FE5', '#003399', '#4C33E5', '#9933E5', '#CC33E5', '#EE33EE' ],
		  [ '#FFFFFF', '#CCCCCC', '#999999', '#666666', '#333333', '#000000' ]
	   ],
	   afterCreate : function() {
		   var self=this; 
		   KindEditor.ctrl(self.edit.doc, 13, function() { 
			   self.sync(); 
			   $("#btnSend").click();
		   }); 
       }
    });
  });
```
ָ���ļ��ϴ�·��
** uploadJson : '<%=request.getContextPath() %>/FileUploadServlet', **

##1.4 Test
chrom  �� firefox ����������ַ
http://localhost:8080/chat-web/
1. �����ǳƣ� ����
2. �࿪����ҳ�� �ظ� 1
3. ʹ�ù����� �����ϴ�ͼƬ�ļ� 