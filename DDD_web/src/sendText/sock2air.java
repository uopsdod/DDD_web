package sendText;
import java.io.*;
import java.net.*;
import java.lang.String.*;

public class sock2air {

private Socket sock ;
private DataInputStream  din ;
private DataOutputStream dout ;
private String ret_message = "" ;

  public sock2air() {} ;

  //建立Socket連線，並做帳號密碼檢查
  public int create_conn(String host, int port, String user, String passwd) {

    //---設定送出訊息訊息的buffer
    byte out_buffer[] = new byte[258]; //傳送長度為258

    //---設定接收訊息的buffer
    byte ret_code = 99;
    byte ret_content[] = new byte[128];

     try {
         //---建 socket
         this.sock = new Socket(host , port);

         this.din  = new DataInputStream(this.sock.getInputStream());
         this.dout = new DataOutputStream(this.sock.getOutputStream());

        //---開始帳號密碼檢查
        int i;
        //----清除 buffer
        for( i=0 ; i < 258 ; i++ ) out_buffer[i] = 0 ;
        for( i=0 ; i < 128 ; i++ ) ret_content[i] = 0 ;

        //---設定帳號與密碼
        String acc_pwd_str = user.trim() + "\0" + passwd.trim() + "\0" ;
        byte   acc_pwd_byte[] = acc_pwd_str.getBytes();
        byte   acc_pwd_size = (byte)acc_pwd_byte.length ;
 
        out_buffer[0] = 0; //interface_type=0
        out_buffer[1] = 0; //msg_type=0,檢查密碼
        out_buffer[2] = acc_pwd_size ; // msg_content_len
	//設定msg_set 內容 "帳號"+"密碼"
        for( i = 0; i < acc_pwd_size ; i++ )
              out_buffer[i + 3] = acc_pwd_byte[i] ;
        
        //----送出訊息
        //this.dout.write(out_buffer , 0 , acc_pwd_size + 3 );
        this.dout.write(out_buffer );

        //---讀 return code
        ret_code = this.din.readByte();
        
        //---讀 return message
        this.din.read(ret_content,0,128);
        this.ret_message = new String(ret_content);
        return ret_code ;

     } catch( UnknownHostException e) {
          this.ret_message = "Cannot find the host!";
          return 70 ;
     } catch( IOException ex) {
          this.ret_message = "Socket Error: " + ex.getMessage();
          return 71 ;
     }

  }//end of function

  //結束Socket連線
  public void close_conn() {
     try {
         if( this.din  != null) this.din.close();
         if( this.dout != null) this.dout.close();
         if( this.sock != null) this.sock.close();

         this.din = null ;
         this.dout = null;
         this.sock = null ;

     } catch( UnknownHostException e) {
          this.ret_message = "Cannot find the host!";
     } catch( IOException ex) {
          this.ret_message = "Socket Error: " + ex.getMessage();
     }

  }//end of function


  //傳送文字簡訊 (即時傳送)
  public int send_message( String sms_tel, String message) {

    //---設定送出訊息訊息的buffer
    byte out_buffer[] = new byte[258]; //傳送長度為258

    //----設定接收的buffer
    byte ret_code = 99;
    byte ret_content[] = new byte[128];

    if(message.length() > 160){
       this.ret_message = "msg_content > max limit!";
       return 80 ;
    }

    try {
        int i ;
        //----清除 buffer
        for( i=0 ; i < 258 ; i++ ) out_buffer[i] = 0 ;
        for( i=0 ; i < 128 ; i++ ) ret_content[i] = 0 ;
      
        //---設定傳送訊息的內容 01:即時傳送
        String msg_content = sms_tel.trim() + "\0" + message.trim() + "\0" ;
        byte msg_content_byte[] = msg_content.getBytes("Big5"); //需指定轉碼為Big5，不然會印出??
        int msg_content_size = msg_content_byte.length;

        //---設定送出訊息的 buffer
        out_buffer[0] = 0; //interface_type default=0
        out_buffer[1] = 1; //msg_type=1,傳送文字訊息
        out_buffer[2] = (byte)(msg_content_size+2); //(msg_content_byte length + send_type length + '\0' length)
	//設定msg_content 內容 "訊息內容"
        for( i = 0; i < msg_content_size ; i++ )
              out_buffer[i+3] = msg_content_byte[i] ;

        //---設定即時傳送 send_type = 100
        out_buffer[msg_content_size + 3 ] = 100;
        out_buffer[msg_content_size + 4 ] = 0 ; // ='\0'

        //----送出訊息
        this.dout.write(out_buffer);

        //---讀 return code
        ret_code = this.din.readByte();
       
        //---讀 return message
        this.din.read(ret_content,0,128);
        this.ret_message = new String(ret_content);
        this.ret_message = this.ret_message.trim();
        return ret_code ;

    } catch( UnknownHostException eu) {
         this.ret_message = "Cannot find the host!";
         return 70 ;
    } catch( IOException ex) {
         this.ret_message = " Socket Error: " + ex.getMessage();
         return 71 ;
    }
  }//end of function


  //傳送文字簡訊 (即時傳送)
  public int send_message( String sms_tel, String message, String order_time) {

    //---設定送出訊息訊息的buffer
    byte out_buffer[] = new byte[258]; //傳送長度為258

    //----設定接收的buffer
    byte ret_code = 99;
    byte ret_content[] = new byte[128];

    if(message.length() > 160){
       this.ret_message = "msg_content > max limit!";
       return 80 ;
    }

    try {
        int i ;
        //----清除 buffer
        for( i=0 ; i < 258 ; i++ ) out_buffer[i] = 0 ;
        for( i=0 ; i < 128 ; i++ ) ret_content[i] = 0 ;
      
        //---設定傳送訊息的內容
        String msg_content = sms_tel.trim() + "\0" + message.trim() + "\0" ;
        byte msg_content_byte[] = msg_content.getBytes("Big5"); //需指定轉碼為Big5，不然會印出??
        int msg_content_size = msg_content_byte.length;

       //---設定預約傳送時間
        String send_time = order_time.trim() + "\0";
        byte send_time_byte[] = send_time.getBytes();
        int send_time_size = send_time_byte.length ;

        //---設定送出訊息的 buffer
        out_buffer[0] = 0; //interface_type default=0
        out_buffer[1] = 1; //msg_type=1,傳送文字訊息
        out_buffer[2] = (byte)(msg_content_size+2+send_time_size); //(msg_content_byte length + send_type length + '\0' length)
	//設定msg_content 內容 "訊息內容"
        for( i = 0; i < msg_content_size ; i++ )
           out_buffer[i+3] = msg_content_byte[i] ;

        //---設定即時傳送 send_type = 100
        out_buffer[msg_content_size + 3 ] = 101;
        out_buffer[msg_content_size + 4 ] = 0 ; // ='\0'

        //---設定欲約時間
        for( i = 0; i < send_time_size ; i++ )
           out_buffer[msg_content_size + 5 + i ] = send_time_byte[i] ;

        //----送出訊息
        this.dout.write(out_buffer);

        //---讀 return code
        ret_code = this.din.readByte();
       
        //---讀 return message
        this.din.read(ret_content,0,128);
        this.ret_message = new String(ret_content);
        this.ret_message = this.ret_message.trim();
        return ret_code ;

    } catch( UnknownHostException eu) {
         this.ret_message = "Cannot find the host!";
         return 70 ;
    } catch( IOException ex) {
         this.ret_message = " Socket Error: " + ex.getMessage();
         return 71 ;
    }
  }//end of function


  //查詢文字簡訊的傳送結果
  public int query_message(String sms_tel, String messageid) {
    //---設定送出訊息訊息的buffer
    byte out_buffer[] = new byte[258]; //傳送長度為258

    //----設定接收的buffer
    byte ret_code = 99;
    byte ret_content[] = new byte[128];

    try {
        int i ;
        //----清除 buffer
        for( i=0 ; i < 258 ; i++ ) out_buffer[i] = 0 ;
        for( i=0 ; i < 128 ; i++ ) ret_content[i] = 0 ;
      
        //---設定傳送訊息的內容 01:即時傳送
        String msg_content = sms_tel.trim() + "\0" + messageid.trim() + "\0" ;
        byte msg_content_byte[] = msg_content.getBytes();
        int msg_content_size = msg_content_byte.length;

        //---設定送出訊息的 buffer
        out_buffer[0] = 0; //interface_type default=0
        out_buffer[1] = 2; //msg_type=2,查詢文字簡訊傳送結果
        out_buffer[2] = (byte)(msg_content_size);
	//設定msg_content 內容 "訊息內容"
        for( i = 0; i < msg_content_size ; i++ )
              out_buffer[i+3] = msg_content_byte[i] ;

        //----送出訊息
        this.dout.write(out_buffer);

        //---讀 return code
        ret_code = this.din.readByte();
       
        //---讀 return message
        this.din.read(ret_content,0,128);
        this.ret_message = new String(ret_content);
        this.ret_message = this.ret_message.trim();
        return ret_code ;

    } catch( UnknownHostException eu) {
         this.ret_message = "Cannot find the host!";
         return 70 ;
    } catch( IOException ex) {
         this.ret_message = " Socket Error: " + ex.getMessage();
         return 71 ;
    }
    
  }//end of function

  public String get_message() {

     return ret_message;
  }


  //主函式 - 使用範例
  public static void main(String[] args) throws Exception {

  try {
      String server  = "203.66.172.131"; //Socket to Air Gateway IP
      int port	     = 8000;            //Socket to Air Gateway Port

      if(args.length<4){
         System.out.println("Use: java sock2air id passwd tel url message");
         System.out.println(" Ex: java sock2air test test123 0910123xxx HiNet簡訊!");
         return;
      }
      String user    = args[0]; //帳號
      String passwd  = args[1]; //密碼
      String tel     = args[2]; //手機號碼
      String message = new String(args[3].getBytes(),"big5"); //簡訊內容

      //----建立連線 and 檢查帳號密碼是否錯誤
      sock2air mysms = new sock2air();
      int ret_code = mysms.create_conn(server,port,user,passwd) ;
      if( ret_code == 0 ) {
           System.out.println("帳號密碼Login OK!");
      } else {
      	   System.out.println("帳號密碼Login Fail!");
           System.out.println("ret_code="+ret_code + ",ret_content=" + mysms.get_message());
           //結束連線
           mysms.close_conn();
           return ;
      }

      //傳送文字簡訊
      ret_code=mysms.send_message(tel,message);
      if( ret_code == 0 ) {
           System.out.println("簡訊已送到簡訊中心!");
           System.out.println("MessageID="+mysms.get_message()); //取得MessageID
      } else {
           System.out.println("簡訊傳送發生錯誤!");
           System.out.print("ret_code="+ret_code+",");
           System.out.println("ret_content="+mysms.get_message());//取得錯誤的訊息
           //結束連線
           mysms.close_conn();
           return ;
      }

      //結束連線
      mysms.close_conn();

  }catch (Exception e)  {

      System.out.println("I/O Exception : " + e);
   }
 }

}//end of class
