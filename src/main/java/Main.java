import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class Main {
    private static Thread th;


    public static void main(String[] args) {
        Runner();

    }
    public static boolean isHostReachable()
    {
        try{
            Socket socket = new Socket("TerraHost.ddns.net",7777);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
    public static void Send(final String value){
        try {
            URL url = new URL("https://api.vk.com/method/messages.send?chat_id=3&message="+value+"&v=5.80&access_token=77e3af29f86fe63995cc3920f4e0b677115076f5a7da82aa7bfd2692e595f62b02d66f25ff17e786397a1");
            BufferedReader in = new BufferedReader(new InputStreamReader((url.openStream())));
            String vv = in.readLine();
            System.out.println(vv);
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public static void Runner()
    {
        th = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    int i = 0;
                    while (!th.isInterrupted())
                    {
                        if(isHostReachable() ==true && i==0){
                            Send("Запущен%20сервер%20TerraHost.ddns.net");
                            i = 1;
                        }
                        if(isHostReachable()==false&&i==1)
                        {
                            Send("Остановлен%20сервер%20TerraHost.ddns.net"); i = 0;
                        }
                        Thread.sleep(4000);
                    }










                }catch (Exception e){

                }
            }
        });
        th.start();
    }
}
